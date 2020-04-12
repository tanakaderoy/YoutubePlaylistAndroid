package com.tanaka.mazivanhanga.youtubeplaylist.api;


import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelSearchResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistItemListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistResponse;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.APIKEY;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.BASEURL;

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

    private void setUpRetrofit() {
        client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("key", APIKEY).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        youtubeService = retrofit.create(YoutubeService.class);
    }

    /**
     * Search for a youtube channel
     *
     * @param channelName The youtube channel you want to search for
     * @param callback    call back to handle data recieved from network call
     */
    public void searchForChannel(String channelName, Callback<ChannelSearchResponse> callback) {
        youtubeService.channelSearchResponse(channelName).enqueue(callback);
    }

    /**
     * Search for playlists on a particular channel using ChannelID
     *
     * @param channelId The youtube channelId
     * @param callback  callback to handle data received
     */
    public void searchForPlayList(String channelId, Callback<PlaylistResponse> callback) {
        youtubeService.playlistResponse(channelId).enqueue(callback);
    }

    /**
     * Get the videos in a given playlist
     * @param playlistId the playlist id
     * @param callback callback
     */
    public void searchForPlaylistItem(String playlistId, Callback<PlaylistItemListResponse> callback) {
        youtubeService.playlistItemResponse(playlistId).enqueue(callback);
    }

}
