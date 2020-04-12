package com.tanaka.mazivanhanga.youtubeplaylist.controllers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanaka.mazivanhanga.youtubeplaylist.R;
import com.tanaka.mazivanhanga.youtubeplaylist.api.Youtube;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistItemListResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.VideoListItem;
import com.tanaka.mazivanhanga.youtubeplaylist.utils.ProgDialog;
import com.tanaka.mazivanhanga.youtubeplaylist.views.VideoListAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.PLAYLIST_ID;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.PLAYLIST_NAME;

public class VideoListActivity extends AppCompatActivity {
    ProgDialog progressDialog;
    RecyclerView recyclerView;
    ArrayList<VideoListItem> videoListItems;
    VideoListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String playlistId, playlistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        setUpIntents();
        setUpViews();
        loadData();

    }

    private void setUpViews() {
        progressDialog = new ProgDialog(this);

        recyclerView = findViewById(R.id.videoListRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        videoListItems = new ArrayList<>();
        adapter = new VideoListAdapter(videoListItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getSupportActionBar().setTitle(playlistName);
    }

    private void setUpIntents() {
        playlistId = getIntent().getStringExtra(PLAYLIST_ID);
        playlistName = getIntent().getStringExtra(PLAYLIST_NAME);
    }

    private void loadData() {
        progressDialog.show();
        Youtube.getInstance().searchForPlaylistItem(playlistId, new Callback<PlaylistItemListResponse>() {
            @Override
            public void onResponse(@NotNull Call<PlaylistItemListResponse> call, @NotNull Response<PlaylistItemListResponse> response) {
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
                Log.i("NETWORK", "<-- Success: " + call.request().toString());


            }

            @Override
            public void onFailure(@NotNull Call<PlaylistItemListResponse> call, @NotNull Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }
}
