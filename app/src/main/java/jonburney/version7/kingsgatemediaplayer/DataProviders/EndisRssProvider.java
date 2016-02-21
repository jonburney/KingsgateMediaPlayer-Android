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

import android.util.Log;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import dagger.Module;
import dagger.Provides;
import jonburney.version7.kingsgatemediaplayer.Services.Http.HttpRequest;
import jonburney.version7.kingsgatemediaplayer.Services.Http.HttpResponse;
import jonburney.version7.kingsgatemediaplayer.Services.Http.IHttpClient;

/**
 * Created by jburney on 06/02/2016.
 */
@Module
public class EndisRssProvider implements IVideoListDataProvider {

    private IHttpClient httpClient;

    @Inject
    public EndisRssProvider(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Update the video list from the remote source
     *
     * @param rssFeedUrl the URL of the remote source
     *
     * @return an ArrayList of entry titles as strings
     */
    @Provides public ArrayList<String> FetchVideoList(String rssFeedUrl) {

        try {

            HttpRequest request = new HttpRequest();
            request.setUrl(rssFeedUrl);
            request.setMethod("GET");

            HttpResponse response = this.httpClient.execute(request);

            return fetchTitlesFromRss(response.getStream());

        } catch (Exception e) {
            Log.e("e", "e is not null, toString is " + e + " and message is " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Parse the RSS inside a given input stream and return the titles
     *
     * @param rssFeed The input stream containing an RSS feeda
     *
     * @return and ArratList of item titles
     *
     * @throws XmlPullParserException
     * @throws IOException
     * @throws XPathExpressionException
     */
    private ArrayList<String> fetchTitlesFromRss(InputStream rssFeed) throws XmlPullParserException, IOException, XPathExpressionException {

        ArrayList<String> result = new ArrayList<>();

        // @// TODO: 11/02/2016 Wrap the Xpath and XML logic inside a service to allow for better testing and injection
        XPath xpath = XPathFactory.newInstance().newXPath();
        InputSource iSource = new InputSource();
        iSource.setByteStream(rssFeed);

        NodeList nodes = (NodeList)xpath.evaluate("/rss/channel/item", iSource, XPathConstants.NODESET);

        for (int i=0; i < nodes.getLength(); i++) {
            NodeList childNodes = nodes.item(i).getChildNodes();

            for (int j=0; j < childNodes.getLength(); j++) {
                String nodeName = childNodes.item(j).getNodeName();


                if (nodeName.equals("title")) {
                    String value = childNodes.item(j).getTextContent();
                    if (value != null) {
                        Log.i("XML", "Fond title: " + value);
                        result.add(value);
                    }
                }
            }

        }

        return result;
    }
}

