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

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import java.util.ArrayList;
import javax.inject.Inject;
import jonburney.version7.kingsgatemediaplayer.DataProviders.IVideoListDataProvider;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.Views.IVideoListView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jburney on 15/03/2016.
 */
public class VideoListPresenter extends Presenter<IVideoListView> {

    private Subscription videoDataSubscription;
    private IVideoListDataProvider dataProvider;

    @Inject
    public VideoListPresenter(IVideoListDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }


    @Override
    public void detachView() {
        super.detachView();
        if (videoDataSubscription != null) {
            videoDataSubscription.unsubscribe();
        }
    }

    public void getVideoList() {

        checkViewAttached();

        videoDataSubscription = dataProvider.FetchVideoList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArrayList<VideoEntity>>() {

                    ProgressDialog progressDialog;
                    AlertDialog alertDialog;

                    @Override
                    public void onStart() {
                        progressDialog = new ProgressDialog(getMvpView().getContext());
                        progressDialog.setTitle("Fetching Video List");
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();

                        alertDialog = new AlertDialog.Builder(getMvpView().getContext()).create();
                        alertDialog.setTitle("Oops");
                        alertDialog.setMessage("There was a problem fetching the list of videos from the website.");


                        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                getMvpView().getVideoList();
                            }
                        });

                        alertDialog.show();

                        Log.e("VideoListPresenter", "Error: ", e);
                    }

                    @Override
                    public void onNext(ArrayList<VideoEntity> videoEntities) {
                        progressDialog.dismiss();
                        getMvpView().showVideoList(videoEntities);
                    }
                });
    }
}
