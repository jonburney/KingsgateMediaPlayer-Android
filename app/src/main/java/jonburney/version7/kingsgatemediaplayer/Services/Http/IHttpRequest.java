package jonburney.version7.kingsgatemediaplayer.Services.Http;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jburney on 24/02/2016.
 */
public interface IHttpRequest {
    void setUrl(String url) throws MalformedURLException;

    URL getUrl();

    void setMethod(String method);

    String getMethod();
}
