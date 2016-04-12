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
package jonburney.version7.kingsgatemediaplayer.Entities.KingsgateXml;

import android.util.Log;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import java.util.ArrayList;
import java.util.List;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;

/**
 * Created by jburney on 05/04/2016.
 */
@Root(name="media", strict=false)
public class KingsgateXmlList {

    @Path(value="group/group")
    @ElementList(inline=true, entry="group", empty = false)
    public List<KingsgateXmlGroup> group;

    public ArrayList<VideoEntity> converyToVideoEntities() {
        ArrayList<VideoEntity> videoList = new ArrayList<VideoEntity>();

        for (KingsgateXmlGroup currentGroup: this.group) {

            if (currentGroup.group != null) {

                for (KingsgateXmlGroup subGroup: currentGroup.group) {

                    for (KingsgateXmlItem item : subGroup.item) {

                        VideoEntity videoEntity = new VideoEntity();

                        videoEntity.title = item.title;
                        videoEntity.description = item.Description;
                        videoEntity.url = item.getVideoUrl();

                        if (videoEntity.isValid()) {
                            videoList.add(videoEntity);
                        } else {
                            Log.i("VideoEntity", "Constructed video entity is not valid. Title = " + videoEntity.title);
                        }
                    }
                }
            }
        }

        return videoList;
    }
}

