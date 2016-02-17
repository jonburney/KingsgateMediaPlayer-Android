package jonburney.version7.kingsgatemediaplayer.Modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import jonburney.version7.kingsgatemediaplayer.Attributes.PerActivity;

/**
 * Created by jburney on 16/02/2016.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides  @PerActivity Activity activity() {
        return this.activity;
    }
}
