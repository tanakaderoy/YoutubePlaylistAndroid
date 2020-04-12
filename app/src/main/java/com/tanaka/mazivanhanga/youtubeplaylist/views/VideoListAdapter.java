package com.tanaka.mazivanhanga.youtubeplaylist.views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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
import com.tanaka.mazivanhanga.youtubeplaylist.models.VideoListItem;

import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private ArrayList<VideoListItem> videoListItems;

    public VideoListAdapter(ArrayList<VideoListItem> videoListItems) {
        this.videoListItems = videoListItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View videoListItem = layoutInflater.inflate(R.layout.video_list_item, parent, false);
        return new ViewHolder(videoListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoListItem currentItem = videoListItems.get(position);
        holder.videoDescriptionTextView.setText(currentItem.getVideoDescription());
        holder.videoTitleTextView.setText(currentItem.getVideoTitle());
        holder.videoUrl = currentItem.getVideoUrl();
        Glide.with(holder.itemView)
                .load(currentItem.getVideoThumbnailUrl())

                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return videoListItems.size();
    }

    public void add(ArrayList<VideoListItem> listItems) {
        videoListItems = listItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private String videoUrl;
        private TextView videoTitleTextView, videoDescriptionTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.videoListImageView);
            videoTitleTextView = itemView.findViewById(R.id.videoListNameTextView);
            videoDescriptionTextView = itemView.findViewById(R.id.videoListDescriptionTextView);
            itemView.setOnClickListener(v -> {
                //"vnd.youtube:"
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(videoUrl));
                try {
                    itemView.getContext().startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    itemView.getContext().startActivity(webIntent);
                }
            });
        }
    }
}
