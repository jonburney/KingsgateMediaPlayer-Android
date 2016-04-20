/*
 * Kingsgate Media Player
 * Copyright (C) 2016 Jon Burney (jon@version7.co.uk)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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