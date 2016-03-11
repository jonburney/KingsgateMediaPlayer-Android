package jonburney.version7.kingsgatemediaplayer.Services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.inject.Inject;

import jonburney.version7.kingsgatemediaplayer.DataProviders.IVideoListDataProvider;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.UrlNotSetException;
import jonburney.version7.kingsgatemediaplayer.R;
import jonburney.version7.kingsgatemediaplayer.Services.Http.HttpRequest;
import jonburney.version7.kingsgatemediaplayer.Services.Http.HttpResponse;
import jonburney.version7.kingsgatemediaplayer.Services.Http.IHttpClient;

/**
 * Created by jburney on 11/03/2016.
 */
public class VideoThumbnailUpdater extends AsyncTask<String, Integer, Bitmap> {

    IHttpClient httpClient;
    Activity homeActivity;

    @Inject
    public VideoThumbnailUpdater(Activity targetActivity, IHttpClient httpClient) {
        this.homeActivity = targetActivity;
        this.httpClient = httpClient;

    }

    @Override
    protected Bitmap doInBackground(String... thumbnailUrl) {

        Bitmap imageBitmap = null;

        HttpRequest request = new HttpRequest();
        try {
            request.setUrl(thumbnailUrl[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        request.setMethod("GET");
        HttpResponse response = null;

        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UrlNotSetException e) {
            e.printStackTrace();
        }

        imageBitmap = BitmapFactory.decodeStream(response.getStream());


        return imageBitmap;

    }

    protected void onPostExecute(Bitmap result) {

        ImageView targetImage = (ImageView)homeActivity.findViewById(R.id.videoPreviewThumbnail);
        targetImage.setImageBitmap(result);
    }
}
