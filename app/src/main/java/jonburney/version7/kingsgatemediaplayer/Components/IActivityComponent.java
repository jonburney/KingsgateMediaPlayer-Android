package jonburney.version7.kingsgatemediaplayer.Components;

import android.app.Activity;

import dagger.Component;
import jonburney.version7.kingsgatemediaplayer.Attributes.PerActivity;
import jonburney.version7.kingsgatemediaplayer.Modules.ActivityModule;

/**
 * Created by jburney on 16/02/2016.
 */
@PerActivity
@Component (dependencies = IApplicationComponent.class, modules = ActivityModule.class)
public interface IActivityComponent {
    Activity activity();
}
