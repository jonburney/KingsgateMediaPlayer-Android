/*
 * Kingsgate Media Player
 * Copyright (C) 2016 Jon Burney (jon@version7.co.uk)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package jonburney.version7.kingsgatemediaplayer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import java.util.ArrayList;
import javax.inject.Inject;
import jonburney.version7.kingsgatemediaplayer.Activities.BaseActivity;
import jonburney.version7.kingsgatemediaplayer.Activities.VideoPlayerActivity;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.Presenters.CardPresenter;
import jonburney.version7.kingsgatemediaplayer.Presenters.VideoListPresenter;
import jonburney.version7.kingsgatemediaplayer.Views.IVideoListView;

/**
 * Created by jburney on 15/03/2016.
 */
public class VideoListFragment extends BrowseFragment implements IVideoListView {

    @Inject VideoListPresenter videoListPresenter;

    private ArrayObjectAdapter videoListAdapter;

    public static VideoListFragment newInstance() {
        return new VideoListFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

        videoListAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        videoListPresenter.attachView(this);

        setAdapter(videoListAdapter);
        setHeadersState(this.HEADERS_DISABLED);
        getVideoList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        videoListPresenter.detachView();
    }

    public void getVideoList() {
        videoListPresenter.getVideoList();
    }

    public void showVideoList(ArrayList<VideoEntity> videoEntities) {

        final ArrayObjectAdapter videoListAdapter = new ArrayObjectAdapter(new CardPresenter());

        videoListAdapter.addAll(0, videoEntities);

        ListRow videoListRow = new ListRow(new HeaderItem(0, ""), videoListAdapter);
        this.videoListAdapter.add(videoListRow);

        setListViewClickHandlers();
    }

    /**
     * Set the click handlers for the items on display
     */
    private void setListViewClickHandlers() {

        OnItemViewClickedListener clickedListener  = new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                VideoEntity clickedVideoEntity = (VideoEntity) item;
                Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
                intent.putExtra("VideoUrl", clickedVideoEntity.url);
                startActivity(intent);
            }

        };

        setOnItemViewClickedListener(clickedListener);
    }


}
