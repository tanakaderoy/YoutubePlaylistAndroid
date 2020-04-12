package com.tanaka.mazivanhanga.youtubeplaylist.controllers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanaka.mazivanhanga.youtubeplaylist.R;
import com.tanaka.mazivanhanga.youtubeplaylist.api.Youtube;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistItemListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.VideoListItem;
import com.tanaka.mazivanhanga.youtubeplaylist.utils.ProgDialog;
import com.tanaka.mazivanhanga.youtubeplaylist.views.VideoListAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.PLAYLIST_ID;

public class VideoListActivity extends AppCompatActivity {
    ProgDialog progressDialog;
    RecyclerView recyclerView;
    ArrayList<VideoListItem> videoListItems;
    VideoListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String playlistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        progressDialog = new ProgDialog(this);

        recyclerView = findViewById(R.id.videoListRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        videoListItems = new ArrayList<>();
        adapter = new VideoListAdapter(videoListItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        playlistId = getIntent().getStringExtra(PLAYLIST_ID);
        loadData();

    }

    private void loadData() {
        progressDialog.show();
        Youtube.getInstance().searchForPlaylistItem(playlistId, new Callback<PlaylistItemListResponse>() {
            @Override
            public void onResponse(Call<PlaylistItemListResponse> call, Response<PlaylistItemListResponse> response) {
                progressDialog.hide();
                ArrayList<VideoListItem> listItems = (ArrayList<VideoListItem>) response.body().getItems().stream().map((item -> {
                    String playlistId = item.getSnippet().getPlaylistId();
                    String videoId = item.getContentDetails().getVideoId();
                    String thumbnailUrl = item.getSnippet().getThumbnails().getHigh().getUrl();
                    String description = item.getSnippet().getDescription();
                    String videoTitle = item.getSnippet().getTitle();
                    return new VideoListItem(videoTitle, description, thumbnailUrl, videoId, playlistId);
                })).collect(Collectors.toList());
                new Handler(Looper.getMainLooper()).post(() -> adapter.add(listItems));


            }

            @Override
            public void onFailure(Call<PlaylistItemListResponse> call, Throwable t) {

            }
        });
    }
}
