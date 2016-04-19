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

    @Path(value="group")
    @ElementList(inline=true, entry="group", empty = false)
    public List<KingsgateXmlGroup> group;

    public ArrayList<VideoEntity> converyToVideoEntities() {
        ArrayList<VideoEntity> currentSeries = new ArrayList<VideoEntity>();
        ArrayList<VideoEntity> pastSeries = new ArrayList<VideoEntity>();

        for (KingsgateXmlGroup currentGroup: this.group) {

            if (currentGroup.GroupId.equals(256240)) {
                currentSeries = parseVideoGroup(currentGroup.group);
            } else if (currentGroup.GroupId.equals(171224)) {
                pastSeries = parseVideoGroup(currentGroup.group);
            }
        }

        currentSeries.addAll(pastSeries);

        return currentSeries;
    }

    private ArrayList<VideoEntity> parseVideoGroup(List<KingsgateXmlGroup> inputGroup) {

        ArrayList<VideoEntity> videosFound = new ArrayList<VideoEntity>();

        if (inputGroup != null) {

            for (KingsgateXmlGroup currentgroup : inputGroup) {

                if (currentgroup.group != null) {
                    videosFound.addAll(parseVideoGroup(currentgroup.group));
                }

                if (currentgroup.item != null) {
                    for (KingsgateXmlItem item : currentgroup.item) {

                        VideoEntity videoEntity = new VideoEntity();

                        videoEntity.title = item.title;
                        videoEntity.description = item.Description;
                        videoEntity.url = item.getVideoUrl();
                        videoEntity.thumbnailUrl = item.getThumbnailUrl();

                        if (videoEntity.isValid()) {
                            videosFound.add(videoEntity);
                        }
                    }
                }
            }
        }



        return videosFound;

    }
}

