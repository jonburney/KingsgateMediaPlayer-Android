package jonburney.version7.kingsgatemediaplayer.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

import javax.inject.Inject;

import jonburney.version7.kingsgatemediaplayer.Activities.BaseActivity;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.Presenters.VideoListPresenter;
import jonburney.version7.kingsgatemediaplayer.R;
import jonburney.version7.kingsgatemediaplayer.Services.RecyclerViewAdapter;
import jonburney.version7.kingsgatemediaplayer.Views.IVideoListView;

/**
 * Created by jburney on 25/04/2016.
 */
public class VideoListPhoneFragment extends Fragment implements IVideoListView {

    @Inject
    VideoListPresenter videoListPresenter;

    private RecyclerViewAdapter videoListAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_list_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.video_list_recycler_view);

        return rootView;
    }

    private int getOrientation() {
        Display display = ((WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            return LinearLayoutManager.HORIZONTAL;
        }

        return LinearLayoutManager.VERTICAL;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

        videoListPresenter.attachView(this);
        videoListAdapter = new RecyclerViewAdapter(null);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), getOrientation() , false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(videoListAdapter);

        getVideoList();
    }

    @Override
    public void getVideoList() {
        videoListPresenter.getVideoList();
    }

    public void showVideoList(ArrayList<VideoEntity> videoEntities) {

        videoListAdapter.addAll(videoEntities);
        videoListAdapter.notifyDataSetChanged();

    }

    public static VideoListPhoneFragment newInstance() {
        return new VideoListPhoneFragment();
    }
}
