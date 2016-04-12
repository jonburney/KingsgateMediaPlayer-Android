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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import javax.inject.Inject;
import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.HttpRequestException;
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
        } catch (HttpRequestException e) {
            e.printStackTrace();
        }
        try {
            request.setMethod("GET");
        } catch (HttpRequestException e) {
            e.printStackTrace();
        }
        HttpResponse response = null;

        try {
            response = httpClient.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageBitmap = BitmapFactory.decodeStream(response.getStream());

        return imageBitmap;
    }

    protected void onPostExecute(Bitmap result) {

    }
}
