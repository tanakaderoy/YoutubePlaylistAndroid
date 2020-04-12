package com.tanaka.mazivanhanga.youtubeplaylist.controllers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanaka.mazivanhanga.youtubeplaylist.R;
import com.tanaka.mazivanhanga.youtubeplaylist.api.Youtube;
import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelListItem;
import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelSearchResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.utils.ProgDialog;
import com.tanaka.mazivanhanga.youtubeplaylist.views.ChannelListAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ChannelSearchResponse channel;
    ArrayList<ChannelListItem> channelListItems;
    private RecyclerView recyclerView;
    private ChannelListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgDialog progDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        channelListItems = new ArrayList<>();
        recyclerView = findViewById(R.id.channelListRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new ChannelListAdapter(channelListItems);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        progDialog = new ProgDialog(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu_items, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                progDialog.show();
                Youtube.getInstance().searchForChannel(query, channelListResponseCallback);


//                ArrayList<ChannelListItem> cListItems =( ArrayList<ChannelListItem> ) channel.getItems().stream().map((chan)->{
//                    return new ChannelListItem(chan.getSnippet().getThumbnails().getHigh().getUrl(), chan.getSnippet().getTitle());
//                }).collect(Collectors.toList());
//                adapter.addAll(cListItems);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }


    Callback<ChannelSearchResponse> channelListResponseCallback = new Callback<ChannelSearchResponse>() {
        @Override
        public void onResponse(Call<ChannelSearchResponse> call, Response<ChannelSearchResponse> response) {
            progDialog.hide();
            channel = response.body();

            if (channel != null) {
                if (channel.getPageInfo().getTotalResults() != 0) {
                    System.out.println("MAinActivity: " + channel.getItems().get(0).getSnippet().getThumbnails().getHigh().getUrl() + "\n" + channel.getItems().get(0).getSnippet().getTitle());

                    System.out.println(channelListItems);
                    new Handler(Looper.getMainLooper()).post(() -> {
                        channelListItems = (ArrayList<ChannelListItem>) channel.getItems().stream().map((item -> new ChannelListItem(item.getSnippet().getThumbnails().getHigh().getUrl(), item.getSnippet().getTitle(), item.getSnippet().getChannelId(), 200))).collect(Collectors.toList());
                        System.out.println(channelListItems);
                        adapter.replace(channelListItems);


                    });
                }
            }
            //Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<ChannelSearchResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
