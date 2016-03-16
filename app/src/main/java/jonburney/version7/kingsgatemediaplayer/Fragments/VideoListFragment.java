package jonburney.version7.kingsgatemediaplayer.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;

import javax.inject.Inject;

import jonburney.version7.kingsgatemediaplayer.Activities.BaseActivity;
import jonburney.version7.kingsgatemediaplayer.Presenters.VideoListPresenter;
import jonburney.version7.kingsgatemediaplayer.Views.IVideoListView;

/**
 * Created by jburney on 15/03/2016.
 */
public class VideoListFragment extends ListFragment implements IVideoListView {

    @Inject VideoListPresenter videoListPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

        videoListPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        videoListPresenter.detachView();
    }

    public void getVideoList() {
        videoListPresenter.getVideoList();
    }
}
