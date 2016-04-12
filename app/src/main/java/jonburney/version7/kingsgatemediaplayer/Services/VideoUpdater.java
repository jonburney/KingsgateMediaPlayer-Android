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
package jonburney.version7.kingsgatemediaplayer.Services;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import javax.inject.Inject;
import jonburney.version7.kingsgatemediaplayer.DataProviders.IVideoListDataProvider;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.R;

/**
 * Created by jburney on 08/02/2016.
 */
public class VideoUpdater extends AsyncTask<String, Integer, ArrayList<VideoEntity>> {

    private String VideoListUrl = "http://www.kingsgateuk.com/Media/rss.xml";

    private Activity HomeActivity;
    private IVideoListDataProvider videoListProvider;

    @Inject
    public VideoUpdater(Activity targetActivity, IVideoListDataProvider videoListProvider) {
        HomeActivity = targetActivity;
        this.videoListProvider = videoListProvider;
    }

    protected ArrayList<VideoEntity> doInBackground(String... urls) {
        ArrayList<VideoEntity> videoList = null;

       // videoList = videoListProvider.FetchVideoList(VideoListUrl);
        return videoList;
    }

    protected void onPostExecute(ArrayList<VideoEntity> results) {
        super.onPostExecute(results);

        ListView VideoListView = (ListView) HomeActivity.findViewById(R.id.videoList);
        ArrayAdapter<VideoEntity> adapter = (ArrayAdapter<VideoEntity>)VideoListView.getAdapter();

        if (results.size() > 0) {
            adapter.addAll(results);
            adapter.notifyDataSetChanged();
        }

    }
}

