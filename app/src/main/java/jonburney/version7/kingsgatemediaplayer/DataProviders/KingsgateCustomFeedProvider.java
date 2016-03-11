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
package jonburney.version7.kingsgatemediaplayer.DataProviders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.UrlNotSetException;
import jonburney.version7.kingsgatemediaplayer.Services.Http.HttpRequest;
import jonburney.version7.kingsgatemediaplayer.Services.Http.HttpResponse;
import jonburney.version7.kingsgatemediaplayer.Services.Http.IHttpClient;

/**
 * Created by jburney on 04/03/2016.
 */
public class KingsgateCustomFeedProvider implements IVideoListDataProvider {

    private IHttpClient httpClient;

    @Inject
    public KingsgateCustomFeedProvider(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public ArrayList<VideoEntity> FetchVideoList(String rssFeedUrl) {

        HttpRequest request = new HttpRequest();
        try {
            request.setUrl("http://kingsgateuk.com/Media/MediaXML.xml?fid=3882");
        } catch (MalformedURLException e) {
            return new ArrayList<>();
        }
        request.setMethod("GET");

        HttpResponse response;
        try {
            response = this.httpClient.execute(request);
        } catch (UrlNotSetException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }

        return fetchEntitiesFromXml(response.getStream());

    }

    /**
     * Fetch the video entities from the remote XML
     * @param xmlFeedStream The input XML stream
     * @return ArrayList<VideoEntity> An array list of video entities
     */
    private ArrayList<VideoEntity> fetchEntitiesFromXml(InputStream xmlFeedStream)  {
        ArrayList<VideoEntity> videoList = new ArrayList<>();
        XPath xpath = XPathFactory.newInstance().newXPath();

        InputSource iSource = new InputSource(xmlFeedStream);
        NodeList nodes = null;

        try {
            nodes = (NodeList)xpath.evaluate("/media/group/group/group/group/item", iSource, XPathConstants.NODESET);

            for (int i=0; i < 10; i++) {
                VideoEntity video = new VideoEntity();

                Node currentNode = nodes.item(i);

                video.title = this.getVideoTitle(currentNode);

                video.description  = this.getVideoDescription(currentNode);
                video.duration     = this.getVideoDuration(currentNode);
                video.thumbnailUrl = this.getVideoThumbnailUrl(currentNode);
                video.url          = this.getVideoUrl(currentNode);
                video.thumbnailUrl = this.getVideoThumbnailUrl(currentNode);

                if (video.isValid()) {
                    videoList.add(video);
                }
            }

        } catch (XPathExpressionException e) {

            Log.e("XML", "Exception: " + e.getMessage() + "\n" + e.getStackTrace());
            return videoList;
        }

        Log.i("XML", "Found: " + nodes.getLength()  + "items");

        return videoList;
    }

    private String getVideoTitle(Node xmlNode){
        try {
            return xmlNode.getAttributes().getNamedItem("title").getNodeValue();
        } catch (Exception e) {
            Log.e("XML", "Error (getVideoTitle): " + e.getMessage());
        }

        return "";
    }

    private String getVideoDescription(Node xmlNode) {
        try {
            return xmlNode.getAttributes().getNamedItem("description").getNodeValue();
        } catch (Exception e) {
            Log.e("XML", "Error (getVideoDescription): " + e.getMessage());
        }

        return "";
    }

    private String getVideoUrl(Node xmlNode) {
        try {
            NodeList childNodes = xmlNode.getChildNodes();

            for (int i=0; i < childNodes.getLength(); i++) {
                Node targetNode = childNodes.item(i);

               if (targetNode.getNodeName().equals("file")
                   && targetNode.hasAttributes()
                   && targetNode.getAttributes().getNamedItem("mime_type").getNodeValue().equals("video/mp4")
               ) {
                    String basePath = targetNode.getAttributes().getNamedItem("file_base_path").getNodeValue();
                    String filename = targetNode.getAttributes().getNamedItem("file_name").getNodeValue();

                    return basePath + filename;
                }
            }

        } catch (Exception e) {
            Log.e("XML", "Error fetching video URL: " + e.getMessage());
        }

        return "";
    }

    private String getVideoDuration(Node xmlNode) {
        try {
            return xmlNode.getAttributes().getNamedItem("length_text").getNodeValue();
        } catch (Exception e) {
            Log.e("XML", "Error (getVideoDuration): " + e.getMessage());
        }

        return "";
    }

    private String getVideoThumbnailUrl(Node xmlNode) {

        try {
            String basePath = xmlNode.getAttributes().getNamedItem("summary_image_path_base").getNodeValue();
            String fileName = xmlNode.getAttributes().getNamedItem("summary_image_file_name").getNodeValue();
            return basePath + fileName;

        } catch (Exception e) {
            Log.e("XML", "Error (getVideoThumbnail): " + e.getMessage());
        }

        return "";
    }






}
