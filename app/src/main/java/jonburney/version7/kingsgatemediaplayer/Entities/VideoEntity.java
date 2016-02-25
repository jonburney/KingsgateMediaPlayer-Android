package jonburney.version7.kingsgatemediaplayer.Entities;

import android.util.Log;

/**
 * Created by jburney on 21/02/2016.
 */
public class VideoEntity {
    public String title;
    public String url;
    public String description;

    @Override
    public String toString() {
        return this.title;
    }

    public Boolean isValid() {

        if (title == null) {
            Log.i("Video", "title is null");
        }

        if (url == null) {
            Log.i("Video", "url is null");
        }

        if (description == null) {
            Log.i("Video", "description is null");
        }

        if (title == null || url == null || description == null) {
            return false;
        }

        return true;
    }

}
