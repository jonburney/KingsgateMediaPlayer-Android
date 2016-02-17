package jonburney.version7.kingsgatemediaplayer.Modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jonburney.version7.kingsgatemediaplayer.MainApp;

/**
 * Created by jburney on 16/02/2016.
 */
@Module
public class ApplicationModule {

    private final MainApp application;

    public ApplicationModule(MainApp application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }


}
