package jonburney.version7.kingsgatemediaplayer.Services.Http;

import java.io.IOException;
import java.net.ProtocolException;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jburney on 21/02/2016.
 */
public interface IHttpClient {

    HttpResponse execute(HttpRequest request) throws IOException;
}
