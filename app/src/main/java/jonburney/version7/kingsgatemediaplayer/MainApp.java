package jonburney.version7.kingsgatemediaplayer;

import android.app.Application;

import jonburney.version7.kingsgatemediaplayer.Components.DaggerIApplicationComponent;
import jonburney.version7.kingsgatemediaplayer.Components.IApplicationComponent;
import jonburney.version7.kingsgatemediaplayer.Modules.ApplicationModule;

/**
 * Created by jburney on 16/02/2016.
 */
public class MainApp extends Application {

    private IApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationComponent = DaggerIApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }



    public IApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
