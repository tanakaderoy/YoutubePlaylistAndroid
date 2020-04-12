package com.tanaka.mazivanhanga.youtubeplaylist.views;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tanaka.mazivanhanga.youtubeplaylist.R;
import com.tanaka.mazivanhanga.youtubeplaylist.controllers.VideoListActivity;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistListItem;

import java.util.ArrayList;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.PLAYLIST_ID;

public class PlaylistListAdapter extends RecyclerView.Adapter<PlaylistListAdapter.ViewHolder> {
    ArrayList<PlaylistListItem> playlistListItems;

    public PlaylistListAdapter(ArrayList<PlaylistListItem> playlistListItems) {
        this.playlistListItems = playlistListItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View playListListItem = layoutInflater.inflate(R.layout.playlist_list_item, parent, false);
        PlaylistListAdapter.ViewHolder viewHolder = new PlaylistListAdapter.ViewHolder(playListListItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistListItem currentItem = playlistListItems.get(position);
        holder.playlistTitleTextView.setText(currentItem.getPlaylistName());
        Glide.with(holder.itemView)
                .load(currentItem.getPlaylistThumbnailURL())
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(holder.playlistThumbImageView);
        String videoCountText = "Videos: " + currentItem.getPlaylistVideoCount();
        holder.playlistId = currentItem.getPlaylistId();
        holder.playlistCountTextView.setText(videoCountText);
    }

    @Override
    public int getItemCount() {
        return playlistListItems.size();
    }

    public void add(ArrayList<PlaylistListItem> listItems) {
        this.playlistListItems = listItems;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView playlistThumbImageView;
        public TextView playlistTitleTextView;
        public TextView playlistCountTextView;
        public String playlistId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistCountTextView = itemView.findViewById(R.id.playlistListCountTextView);
            playlistThumbImageView = itemView.findViewById(R.id.playlistListPlaylistImageView);
            playlistTitleTextView = itemView.findViewById(R.id.playlistListPlaylistNameTextView);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), VideoListActivity.class);
                intent.putExtra(PLAYLIST_ID, playlistId);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
