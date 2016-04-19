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
package jonburney.version7.kingsgatemediaplayer.Entities;

import android.util.Log;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jburney on 21/02/2016.
 */
public class VideoEntity implements Comparable<VideoEntity> {
    public String title;
    public String url;
    public String description;
    public String thumbnailUrl;
    public String duration;
    public Date createdDate;

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

    @Override
    public int compareTo(VideoEntity comparisonItem) {
        if (createdDate == null || comparisonItem.createdDate == null) {
            return 0;
        }
        return comparisonItem.createdDate.compareTo(this.createdDate);
    }

}
