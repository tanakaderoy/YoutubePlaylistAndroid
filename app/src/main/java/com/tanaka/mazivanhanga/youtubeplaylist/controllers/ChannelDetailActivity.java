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
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistListItem;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.utils.ProgDialog;
import com.tanaka.mazivanhanga.youtubeplaylist.views.PlaylistListAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.CHANNEL_ID;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.CHANNEL_IMAGE;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.CHANNEL_NAME;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.UPLOAD_COUNT;

public class ChannelDetailActivity extends AppCompatActivity {
    ProgDialog progressDialog;
    RecyclerView recyclerView;
    ArrayList<PlaylistListItem> playlistListItems;
    PlaylistListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String channelId, channelImage;
    int uploadCount;
    private String channelName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_detail);
        setUpIntents();
        setUpViews();
        loadData(channelId);

    }

    /**
     * Set up the intents
     */
    private void setUpIntents() {
        channelId = getIntent().getStringExtra(CHANNEL_ID);
        channelName = getIntent().getStringExtra(CHANNEL_NAME);
        channelImage = getIntent().getStringExtra(CHANNEL_IMAGE);
        uploadCount = getIntent().getIntExtra(UPLOAD_COUNT, 0);
    }

    /**
     * Set the views and such
     */
    private void setUpViews() {
        recyclerView = findViewById(R.id.playlistListRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        playlistListItems = new ArrayList<>();
        adapter = new PlaylistListAdapter(playlistListItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getSupportActionBar().setTitle(channelName);
        progressDialog = new ProgDialog(this);
    }

    /**
     * Load the detail for a channels playlists
     *
     * @param channelId channelId to search using
     */
    private void loadData(String channelId) {
        progressDialog.show();
        Youtube.getInstance().searchForPlayList(channelId, new Callback<PlaylistResponse>() {
            @Override
            public void onResponse(Call<PlaylistResponse> call, Response<PlaylistResponse> response) {
                progressDialog.hide();
                if (response.body() != null) {
                    ArrayList<PlaylistListItem> listItems = (ArrayList<PlaylistListItem>) response.body().getItems().stream().map((item -> {
                        String title = item.getSnippet().getTitle();
                        String url = item.getSnippet() == null || item.getSnippet().getThumbnails() == null || item.getSnippet().getThumbnails().getDefault() == null ? "" : item.getSnippet().getThumbnails().getHigh().getUrl();
                        Integer itemCount = item.getContentDetails().getItemCount();
                        String playlistId = item.getId();
                        return new PlaylistListItem(title, url, itemCount, playlistId);
                    })).collect(Collectors.toList());
                    String channelPlaylistId = channelId.replaceAll("UC", "UU");
                    listItems.add(0, new PlaylistListItem(channelName + " Uploads", channelImage, uploadCount, channelPlaylistId));
                    new Handler(Looper.getMainLooper()).post(() -> adapter.add(listItems));
                    Log.i("NETWORK", "Success: " + call);
                }
            }

            @Override
            public void onFailure(Call<PlaylistResponse> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }
}
