package jonburney.version7.kingsgatemediaplayer.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jonburney.version7.kingsgatemediaplayer.Fragments.VideoListFragment;
import jonburney.version7.kingsgatemediaplayer.Presenters.VideoListPresenter;

/**
 * Created by jburney on 15/03/2016.
 */
@Module
public class PresenterModule {

    public PresenterModule() {

    }

    @Provides
    VideoListPresenter providesVideoListPresenter() {
        return new VideoListPresenter();
    }

}
