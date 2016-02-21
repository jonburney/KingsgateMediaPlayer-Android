package jonburney.version7.kingsgatemediaplayer.Services.Http;

import java.io.InputStream;

/**
 * Created by jburney on 21/02/2016.
 */
public class HttpResponse {

    private InputStream responseStream;

    public HttpResponse(InputStream responseStream) {
        this.responseStream = responseStream;
    }

    public InputStream getStream() {
        return this.responseStream;
    }

}
