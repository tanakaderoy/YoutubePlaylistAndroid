package com.tanaka.mazivanhanga.youtubeplaylist.controllers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ChannelSearchResponse channel;
    ArrayList<ChannelListItem> channelListItems;
    private RecyclerView recyclerView;
    private ChannelListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView promptTextView;
    ProgDialog progDialog;
    @NonNull
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews() {
        channelListItems = new ArrayList<>();
        recyclerView = findViewById(R.id.channelListRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new ChannelListAdapter(channelListItems);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        progDialog = new ProgDialog(this);
        promptTextView = findViewById(R.id.searchPromptTTextView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
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
                performSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                promptTextView.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.clear();
                promptTextView.setVisibility(View.VISIBLE);
                return true;
            }
        });
        return true;
    }

    private void performSearch(String query) {
        if (query.equals("") || query.isEmpty()) {
            showAlert();
            return;
        }
        Youtube.getInstance().searchForChannel(query, channelSearchResponseSingleObserver);
    }

    private void showAlert() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Warning!");
        builder1.setMessage("Please Enter A Search Param");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "OK!",
                (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    SingleObserver<ChannelSearchResponse> channelSearchResponseSingleObserver = new SingleObserver<ChannelSearchResponse>() {
        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onSuccess(ChannelSearchResponse channelSearchResponse) {
            progDialog.hide();

            channel = channelSearchResponse;
            System.out.println(channelListItems);
            new Handler(Looper.getMainLooper()).post(() -> {
                channelListItems = (ArrayList<ChannelListItem>) channel.getItems().stream().map((item -> new ChannelListItem(item.getSnippet().getThumbnails().getHigh().getUrl(), item.getSnippet().getTitle(), item.getSnippet().getChannelId(), 200))).collect(Collectors.toList());
                System.out.println(channelListItems);
                adapter.replace(channelListItems);
                Log.i("NETWORK", "<-- Success: ");
            });

        }

        @Override
        public void onError(Throwable e) {
            progDialog.hide();
            e.printStackTrace();
        }
    };
}
