package jonburney.version7.kingsgatemediaplayer.DataProviders;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jburney on 16/02/2016.
 */
public interface IVideoListDataProvider {
    public ArrayList<String> FetchVideoList(String rssFeedUrl);
}
