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

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import javax.inject.Inject;
import jonburney.version7.kingsgatemediaplayer.Activities.BaseActivity;
import jonburney.version7.kingsgatemediaplayer.Activities.VideoPlayerActivity;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.Presenters.VideoListPresenter;
import jonburney.version7.kingsgatemediaplayer.R;
import jonburney.version7.kingsgatemediaplayer.Views.IVideoListView;

/**
 * Created by jburney on 15/03/2016.
 */
public class VideoListFragment extends ListFragment implements IVideoListView {

    @Inject VideoListPresenter videoListPresenter;

    protected View listViewFragment;
    protected ListView videoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.video_list_fragment, container, false);
        listViewFragment = view;
        videoList = (ListView)listViewFragment.findViewById(R.id.videoList);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

        ArrayList<VideoEntity> listItems = new ArrayList<VideoEntity>();
        ArrayAdapter<VideoEntity> adapter = new ArrayAdapter<VideoEntity>(getActivity(), R.layout.video_list_text, listItems);
        setListAdapter(adapter);

        videoListPresenter.attachView(this);

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
        ArrayAdapter<VideoEntity> adapter = (ArrayAdapter<VideoEntity>)getListAdapter();
        adapter.addAll(videoEntities);
        adapter.notifyDataSetChanged();
        setListViewClickHandlers();
    }

    private void setListViewClickHandlers() {

        videoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoEntity clickedVideoEntity = (VideoEntity) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
                intent.putExtra("VideoUrl", clickedVideoEntity.url);
                startActivity(intent);
            }
        });
    }


}
