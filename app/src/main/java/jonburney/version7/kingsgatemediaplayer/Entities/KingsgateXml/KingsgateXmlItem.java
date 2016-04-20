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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Attribute(name="summary_image_path_base", required=false)
    private String ThumbnailBasePath;

    @Attribute(name="summary_image_file_name", required=false)
    private String ThumbnailFilename;

    @Attribute(name="recording_dt", required=false)
    public String CreatedDate;

    public String getThumbnailUrl() {
        if (ThumbnailBasePath == null || ThumbnailBasePath == "" || ThumbnailFilename == null || ThumbnailFilename == "") {
            return null;
        }

        return ThumbnailBasePath + ThumbnailFilename;
    }

    public Date getCreatedDate() {

        if (CreatedDate == null) {
            return null;
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        try {
            return dateFormatter.parse(CreatedDate);
        } catch (ParseException e) {
            Log.e("XML Parser", "Failed to process date", e);
        }

        return null;
    }

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
