package jonburney.version7.kingsgatemediaplayer.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

import jonburney.version7.kingsgatemediaplayer.Components.IApplicationComponent;
import jonburney.version7.kingsgatemediaplayer.DataProviders.IVideoListDataProvider;
import jonburney.version7.kingsgatemediaplayer.MainApp;
import jonburney.version7.kingsgatemediaplayer.R;
import jonburney.version7.kingsgatemediaplayer.Services.VideoUpdater;

/**
 * Created by jburney on 21/02/2016.
 */
public class VideoPlayerActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.video_player);

        String videoTitle = "";
        String videoDescription = "";
        String videoUrl = "";

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                videoTitle = extras.getString("VideoTitle");
                videoDescription = extras.getString("VideoDescription");
                videoUrl = extras.getString("VideoUrl");
            }
        }


        VideoView videoView = (VideoView)findViewById(R.id.video_player);

        final ProgressDialog progressDialog = new ProgressDialog(VideoPlayerActivity.this);
        progressDialog.setTitle("Kingsgate Media Player");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        MediaController mediaControls = new MediaController(VideoPlayerActivity.this);
        videoView.setMediaController(mediaControls);

        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                mp.seekTo(0);
                mp.start();
            }
        });

    }

}
