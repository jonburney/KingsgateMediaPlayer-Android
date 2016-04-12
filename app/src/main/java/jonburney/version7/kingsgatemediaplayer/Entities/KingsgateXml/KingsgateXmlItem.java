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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="item", strict=false)
public class KingsgateXmlItem
{
    @ElementList(required=false, inline=true)
    public List<KingsgateXmlFile> file;

    @ElementList(required=false, inline=true)
    public List<KingsgateXmlLink> link;

    @Attribute(name="title")
    public String title;

    @Attribute(name="description", required=false)
    public String Description;

    public String getVideoUrl() {

        if (file == null || file.size() == 0) {
            return null;
        }

        for (KingsgateXmlFile currentFile : file) {
            if (currentFile.MimeType.equals("video/mp4")) {
                return currentFile.FileUrl();
            }
        }

        return null;
    }

}
