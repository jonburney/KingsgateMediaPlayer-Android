package jonburney.version7.kingsgatemediaplayer.Presenters;

import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.R;

/**
 * Created by jburney on 12/04/2016.
 */
public class CardPresenter extends Presenter {

    private static final int CARD_WIDTH = 640;
    private static final int CARD_HEIGHT = 360;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {

        ImageCardView cardView = new ImageCardView(new ContextThemeWrapper(parent.getContext(), R.style.Widget_Leanback_ImageCardViewStyle)) {
            @Override
            public void setSelected(boolean selected) {
                super.setSelected(selected);
            }
        };

        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

        VideoEntity videoEntity = (VideoEntity)item;

        ImageCardView cardView = (ImageCardView)viewHolder.view;
        cardView.setTitleText(videoEntity.title);
        cardView.setContentText(videoEntity.description);
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);

        Glide.with(cardView.getContext())
                .load(videoEntity.thumbnailUrl)
                .override(CARD_WIDTH, CARD_HEIGHT)
                .fitCenter()
                .into(cardView.getMainImageView());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}