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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by jburney on 06/02/2016.
 */
public class EndisRssProvider {

    /**
     * Update the video list from the remote source
     *
     * @param rssFeedUrl the URL of the remote source
     *
     * @return an ArrayList of entry titles as strings
     */
    public ArrayList<String> UpdateVideoList(String rssFeedUrl) {

        try {
            // @// TODO: 11/02/2016 Wrap this in a HttpClient abstraction for better testing
            URL url = new URL(rssFeedUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream stream = conn.getInputStream();

            return fetchTitlesFromRss(stream);

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
