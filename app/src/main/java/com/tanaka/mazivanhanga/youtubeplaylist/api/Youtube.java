package com.tanaka.mazivanhanga.youtubeplaylist.api;


import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelSearchResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistItemListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistResponse;

import java.io.IOException;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.BASEURL;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.LETMEINNNNNNN;

public class Youtube {
    private YoutubeService youtubeService;
    private Retrofit retrofit;
    private OkHttpClient client;
    private static Youtube youtube;

    public static Youtube getInstance() {
        if (youtube == null) {

            youtube = new Youtube();
        }
        return youtube;
    }

    private Youtube() {
        setUpRetrofit();
    }

    public Youtube( Retrofit retrofit){

        this.youtubeService = retrofit.create(YoutubeService.class);
    }

    private void setUpRetrofit() {
        client = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("key", LETMEINNNNNNN).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        youtubeService = retrofit.create(YoutubeService.class);
    }
    public YoutubeService getYoutubeService(){
        return youtubeService;
    }

    /**
     * Search for a youtube channel
     *
     * @param channelName The youtube channel you want to search for
     * @param channelSearchResponseSingleObserver observer
     */
    public void searchForChannel(String channelName, SingleObserver<ChannelSearchResponse> channelSearchResponseSingleObserver) {
        youtubeService.channelSearchResponse(channelName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(channelSearchResponseSingleObserver);
    }

    /**
     * Search for playlists on a particular channel using ChannelID
     *
     * @param channelId The youtube channelId
     * @param playlistConsumer  observer to handle data received
     */
    public void searchForPlayList(String channelId, SingleObserver<PlaylistResponse> playlistConsumer) {
        youtubeService.playlistResponse(channelId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(playlistConsumer);
    }

    /**
     * Get the videos in a given playlist
     * @param playlistId the playlist id
     * @param playlistItemListResponseConsumer observer
     */
    public void searchForPlaylistItem(String playlistId, SingleObserver<PlaylistItemListResponse> playlistItemListResponseConsumer) {
        youtubeService.playlistItemResponse(playlistId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(playlistItemListResponseConsumer);
    }

}
