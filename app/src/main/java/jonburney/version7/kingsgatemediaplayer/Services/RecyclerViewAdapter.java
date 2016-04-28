package jonburney.version7.kingsgatemediaplayer.Services;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.R;

/**
 * Created by jburney on 26/04/2016.
 */
public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<VideoEntity> dataSet;

    public RecyclerViewAdapter(ArrayList<VideoEntity> dataSet) {
        this.dataSet = dataSet;
    }

    public void addAll(ArrayList<VideoEntity> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        VideoEntity currentEntity = dataSet.get(position);

        LinearLayout cardView = holder.cardView;

        ImageView cardImage = (ImageView)cardView.findViewById(R.id.video_card_image);
        TextView cardTitle = (TextView)cardView.findViewById(R.id.video_card_title);
        TextView cardDescription = (TextView)cardView.findViewById(R.id.video_card_description);

        cardTitle.setText(currentEntity.title);
        cardDescription.setText(currentEntity.description);

        DisplayMetrics metrics = cardView.getResources().getDisplayMetrics();
        int cardWidth = (int)(metrics.widthPixels - ((metrics.widthPixels * 0.07) * 2));
        int cardHeight = (int)(cardWidth * 0.5625);

        Glide.with(cardView.getContext())
                .load(currentEntity.thumbnailUrl)
                .centerCrop()
                .crossFade()
                .override(cardWidth, cardHeight)
                .into(cardImage);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout cardView;
        public ViewHolder(LinearLayout view) {
            super(view);
            cardView = view;
        }
    }

}
