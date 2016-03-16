package jonburney.version7.kingsgatemediaplayer.Presenters;

import jonburney.version7.kingsgatemediaplayer.Views.IView;

/**
 * Created by jburney on 14/03/2016.
 */
public interface IPresenter<T extends IView> {

    void attachView(T view);
    void detachView();
}
