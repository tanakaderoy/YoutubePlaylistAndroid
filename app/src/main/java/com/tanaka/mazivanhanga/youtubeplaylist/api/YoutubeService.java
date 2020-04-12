package com.tanaka.mazivanhanga.youtubeplaylist.api;

import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelSearchResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistItemListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    @GET("channels?part=id%2Csnippet%2CcontentDetails")
    Call<ChannelListResponse> channelListResponse(@Query("forUsername") String channelName);

    @GET("search?part=snippet&maxResults=10&type=channel")
    Call<ChannelSearchResponse> channelSearchResponse(@Query("q") String q);

    @GET("playlists?part=snippet%2CcontentDetails&maxResults=20")
    Call<PlaylistResponse> playlistResponse(@Query("channelId") String channelId);

    @GET("playlistItems?part=snippet%2CcontentDetails")
    Call<PlaylistItemListResponse> playlistItemResponse(@Query("playlistId") String playlistId);

}
