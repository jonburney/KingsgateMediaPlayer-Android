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
package jonburney.version7.kingsgatemediaplayer.DataProviders;

import java.util.ArrayList;
import javax.inject.Inject;
import jonburney.version7.kingsgatemediaplayer.Entities.KingsgateXml.KingsgateXmlList;
import jonburney.version7.kingsgatemediaplayer.Entities.VideoEntity;
import jonburney.version7.kingsgatemediaplayer.Services.Http.IHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jburney on 04/03/2016.
 */
public class KingsgateCustomFeedProvider implements IVideoListDataProvider {

    private IHttpClient httpClient;
    private String defaultFeedUrl = "http://kingsgateuk.com/Media/MediaXML.xml?fid=3882";

    private IKingsgateAdvancedMediaEndpoint kingsgateXmlService;

    @Inject
    public KingsgateCustomFeedProvider(IHttpClient httpClient) {
        this.httpClient = httpClient;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kingsgateuk.com")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        kingsgateXmlService = retrofit.create(IKingsgateAdvancedMediaEndpoint.class);
    }

    @Override
    public Observable<ArrayList<VideoEntity>> FetchVideoList() {
        return FetchVideoList(defaultFeedUrl);
    }

    @Override
    public Observable<ArrayList<VideoEntity>> FetchVideoList(String rssFeedUrl) {

        Observable<KingsgateXmlList> response =
            kingsgateXmlService.getApiResponse()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        return response.map(new Func1<KingsgateXmlList, ArrayList<VideoEntity>>() {
            @Override
            public ArrayList<VideoEntity> call(KingsgateXmlList kingsgateXmlList) {
                return kingsgateXmlList.convertToVideoEntities();
            }
        }).first();
    }
}
