package jonburney.version7.kingsgatemediaplayer.Services;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

    private int orientation;
    private ArrayList<VideoEntity> dataSet;

    // @Todo - Change this to take a presneter and move the formatting logic
    public RecyclerViewAdapter(ArrayList<VideoEntity> dataSet, int orientation) {
        this.dataSet = dataSet;
        this.orientation = orientation;
    }

    public void addAll(ArrayList<VideoEntity> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout view = (RelativeLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        float cardWidthPercentage = 0.8f;

        if (orientation == LinearLayoutManager.HORIZONTAL) {
            cardWidthPercentage = 0.5f;
        }

        VideoEntity currentEntity = dataSet.get(position);

        RelativeLayout cardView = holder.cardView;

        ImageView cardImage = (ImageView)cardView.findViewById(R.id.video_card_image);
        TextView cardTitle = (TextView)cardView.findViewById(R.id.video_card_title);
        TextView cardDescription = (TextView)cardView.findViewById(R.id.video_card_description);

        cardTitle.setText(currentEntity.title);
        cardDescription.setText(currentEntity.description);

        DisplayMetrics metrics = cardView.getResources().getDisplayMetrics();
        int imageWidth = (int)(metrics.widthPixels * cardWidthPercentage);
        int imageHeight = (int)(imageWidth * 0.5625);

        int cardHeight = imageHeight + getTextViewHeight(cardTitle) + (getTextViewHeight(cardDescription) * 2);

        int screenWidth = getScreenWidth(cardView.getContext());
        int screenHeight = getScreenHeight(cardView.getContext());

        Log.i("RecyclerView", "Screen Width = " + screenWidth);
        Log.i("RecyclerView", "Screen Height = " + screenHeight);

        int horizontalPadding = (screenWidth - imageWidth) / 2;
        int verticalPadding = (getScreenHeight(cardView.getContext()) - cardHeight) / 2;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, cardHeight);

        if (orientation == LinearLayoutManager.HORIZONTAL) {
            int minPadding = (int)(imageWidth * 0.05);
            layoutParams.setMargins(minPadding, verticalPadding, minPadding, verticalPadding);
        } else {
            layoutParams.setMargins(horizontalPadding, 0, horizontalPadding, 0);
        }

        cardView.setLayoutParams(layoutParams);

        Glide.with(cardView.getContext())
                .load(currentEntity.thumbnailUrl)
                .centerCrop()
                .crossFade()
                .override(imageWidth, imageHeight)
                .into(cardImage);
    }

    private int getTextViewHeight(TextView textView) {
        return getTextViewDimension(textView, "height");
    }

    private int getTextViewWidth(TextView textView) {
        return getTextViewDimension(textView, "width");
    }

    private int getScreenWidth(Context context) {
        return getScreenDimension(context, "width");
    }

    private int getScreenHeight(Context context) {
        return getScreenDimension(context, "height");
    }

    private int getScreenDimension(Context context, String dimension) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        if (dimension == "width") {
            return displayMetrics.widthPixels;
        }

        return displayMetrics.heightPixels;
    }

    private int getTextViewDimension(TextView textView, String dimension) {
        int screenWidth = getScreenWidth(textView.getContext());
        int measuredSpecWidth = View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.AT_MOST);
        int measuredSpecHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(measuredSpecWidth, measuredSpecHeight);

        if (dimension == "height") {
            return textView.getMeasuredHeight();
        }

        return textView.getMeasuredWidth();

    }



    @Override
    public int getItemCount() {
        return (dataSet != null) ? dataSet.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout cardView;
        public ViewHolder(RelativeLayout view) {
            super(view);
            cardView = view;
        }
    }

}
