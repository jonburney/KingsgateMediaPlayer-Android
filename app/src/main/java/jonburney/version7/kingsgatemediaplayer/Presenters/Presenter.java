package jonburney.version7.kingsgatemediaplayer.Presenters;

import jonburney.version7.kingsgatemediaplayer.Views.IView;

/**
 * Created by jburney on 14/03/2016.
 */
public class Presenter<T extends IView> implements IPresenter<T> {

    private T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
