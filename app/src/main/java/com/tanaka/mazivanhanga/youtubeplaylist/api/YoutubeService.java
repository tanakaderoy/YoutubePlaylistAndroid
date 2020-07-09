package com.tanaka.mazivanhanga.youtubeplaylist.api;

import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelSearchResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistItemListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    @GET("channels?part=id%2Csnippet%2CcontentDetails")
    Single<ChannelListResponse> channelListResponse(@Query("forUsername") String channelName);

    @GET("search?part=snippet&maxResults=10&type=channel")
    Single<ChannelSearchResponse>channelSearchResponse(@Query("q") String q);

    @GET("playlists?part=snippet%2CcontentDetails&maxResults=50")
    Single<PlaylistResponse> playlistResponse(@Query("channelId") String channelId);

    @GET("playlistItems?part=snippet%2CcontentDetails&maxResults=50")
    Single<PlaylistItemListResponse> playlistItemResponse(@Query("playlistId") String playlistId);

    @GET("search?part=snippet&maxResults=10&type=channel")
    Single<ChannelSearchResponse> getChannelSearchResponse(@Query("q") String q);

}
