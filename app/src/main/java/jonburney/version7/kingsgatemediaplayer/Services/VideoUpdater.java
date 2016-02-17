package jonburney.version7.kingsgatemediaplayer.Services;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import javax.inject.Inject;

import jonburney.version7.kingsgatemediaplayer.DataProviders.EndisRssProvider;
import jonburney.version7.kingsgatemediaplayer.DataProviders.IVideoListDataProvider;
import jonburney.version7.kingsgatemediaplayer.R;

/**
 * Created by jburney on 08/02/2016.
 */
public class VideoUpdater extends AsyncTask<String, Integer, ArrayList<String>> {

    private String VideoListUrl = "http://www.kingsgateuk.com/Media/rss.xml";

    private Activity HomeActivity;
    private IVideoListDataProvider videoListProvider;

    @Inject
    public VideoUpdater(Activity targetActivity, IVideoListDataProvider videoListProvider) {
        HomeActivity = targetActivity;
        this.videoListProvider = videoListProvider;
    }

    protected ArrayList<String> doInBackground(String... urls) {

        ArrayList<String> videoList = videoListProvider.FetchVideoList(VideoListUrl);

        return videoList;
    }

    protected void onPostExecute(ArrayList<String> results) {
        super.onPostExecute(results);

        ListView VideoListView = (ListView) HomeActivity.findViewById(R.id.videoList);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>)VideoListView.getAdapter();

        adapter.addAll(results);

        adapter.notifyDataSetChanged();

    }
}

