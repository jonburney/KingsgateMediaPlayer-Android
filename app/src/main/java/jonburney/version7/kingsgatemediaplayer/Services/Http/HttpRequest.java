package jonburney.version7.kingsgatemediaplayer.Services.Http;

import org.apache.http.protocol.HTTP;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jburney on 21/02/2016.
 */
public class HttpRequest {

    private URL url;
    private String method;

    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public URL getUrl() {
        return this.url;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

}
