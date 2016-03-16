package jonburney.version7.kingsgatemediaplayer.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jonburney.version7.kingsgatemediaplayer.DataProviders.IVideoListDataProvider;
import jonburney.version7.kingsgatemediaplayer.Fragments.VideoListFragment;

/**
 * Created by jburney on 15/03/2016.
 */
@Module
public class FragmentModule
{
    public FragmentModule() {

    }

    @Provides
    VideoListFragment providesVideoListFragment() {
        return new VideoListFragment();
    }
}
