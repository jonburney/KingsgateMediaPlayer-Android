package jonburney.version7.kingsgatemediaplayer.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jonburney.version7.kingsgatemediaplayer.Services.Http.HttpClient;
import jonburney.version7.kingsgatemediaplayer.Services.Http.IHttpClient;

/**
 * Created by jburney on 21/02/2016.
 */
@Module
public class HttpModule {

    public HttpModule() {

    }

    @Provides
    @Singleton
    IHttpClient providesIHttpClient() {
        return new HttpClient();
    }

}
