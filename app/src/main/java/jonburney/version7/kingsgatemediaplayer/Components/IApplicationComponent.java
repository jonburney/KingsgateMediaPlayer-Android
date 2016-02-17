package jonburney.version7.kingsgatemediaplayer.Components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import jonburney.version7.kingsgatemediaplayer.Activities.MainActivity;
import jonburney.version7.kingsgatemediaplayer.Modules.ApplicationModule;

/**
 * Created by jburney on 16/02/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface IApplicationComponent {
    void inject(MainActivity mainActivity);
    Context context();
}
