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
import com.tanaka.mazivanhanga.youtubeplaylist.controllers.ChannelDetailActivity;
import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelListItem;

import java.util.ArrayList;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.CHANNEL_ID;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.CHANNEL_IMAGE;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.CHANNEL_NAME;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.UPLOAD_COUNT;

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ViewHolder> {

    private ArrayList<ChannelListItem> channelListItems;

    public ChannelListAdapter(ArrayList<ChannelListItem> channelListItems) {
        this.channelListItems = channelListItems;
    }

    public void addAll(ArrayList<ChannelListItem> newItems) {
        channelListItems.addAll(newItems);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View homeListItem = layoutInflater.inflate(R.layout.channel_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(homeListItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChannelListItem channelListItem = channelListItems.get(position);
        holder.channelNameTextView.setText(channelListItem.getChannelName());
//        Picasso.get().load("https://image.tmdb.org/t/p/"+ImageSize.w780+tvShow.getPosterPath()).into(holder.posterImageView);
//        Picasso.get().load(channelListItem.getChannelImageURL()).into(holder.channelImageView);
        Glide.with(holder.itemView)
                .load(channelListItem.getChannelImageURL())

                .override(100, 100).thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(holder.channelImageView);
        holder.channelId = channelListItem.getChannelId();
        holder.channelImageUrl = channelListItem.getChannelImageURL();
        holder.uploadCount = channelListItem.getUploadCount();
    }

    @Override
    public int getItemCount() {
        return channelListItems.size();
    }

    public void replace(ArrayList<ChannelListItem> channelListItems) {
        this.channelListItems = channelListItems;
        notifyDataSetChanged();
    }

    public void clear() {
        channelListItems.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView channelImageView;
        TextView channelNameTextView;
        String channelId;
        int uploadCount;
        String channelImageUrl;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            channelImageView = itemView.findViewById(R.id.channelListChannelImageView);
            channelNameTextView = itemView.findViewById(R.id.channelListChannelNameTextView);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), ChannelDetailActivity.class);
                intent.putExtra(CHANNEL_ID, channelId);
                intent.putExtra(CHANNEL_NAME, channelNameTextView.getText());
                intent.putExtra(CHANNEL_IMAGE, channelImageUrl);
                intent.putExtra(UPLOAD_COUNT, uploadCount);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
