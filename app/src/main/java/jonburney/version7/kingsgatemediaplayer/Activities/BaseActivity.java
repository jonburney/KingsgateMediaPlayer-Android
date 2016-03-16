package jonburney.version7.kingsgatemediaplayer.Activities;

import android.app.Activity;
import android.os.Bundle;

import jonburney.version7.kingsgatemediaplayer.Components.DaggerIActivityComponent;
import jonburney.version7.kingsgatemediaplayer.Components.IActivityComponent;
import jonburney.version7.kingsgatemediaplayer.MainApp;
import jonburney.version7.kingsgatemediaplayer.Modules.ActivityModule;

/**
 * Created by jburney on 16/03/2016.
 */
public class BaseActivity extends Activity {
    private IActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public IActivityComponent activityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerIActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .iApplicationComponent(MainApp.get(this).getApplicationComponent())
                    .build();

        }

        return activityComponent;
    }
}
