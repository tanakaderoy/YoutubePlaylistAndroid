package com.tanaka.mazivanhanga.youtubeplaylist.views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tanaka.mazivanhanga.youtubeplaylist.R;
import com.tanaka.mazivanhanga.youtubeplaylist.models.VideoListItem;

import java.util.ArrayList;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.SCROLL_TO;

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
        holder.position = position;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(currentItem.getVideoDescription());
        holder.videoDescriptionTextView.setText(spannableStringBuilder);
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
        public int position;
        private ImageView thumbnail;
        private String videoUrl;
        private TextView videoTitleTextView, videoDescriptionTextView, showMore;
        private boolean isShrunk = true;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.videoListImageView);
            videoTitleTextView = itemView.findViewById(R.id.videoListNameTextView);
            videoDescriptionTextView = itemView.findViewById(R.id.videoListDescriptionTextView);
            showMore = itemView.findViewById(R.id.showMore);
            showMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDescriptionText(itemView);
                }
            });
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

        private void toggleDescriptionText(View itemView) {
            if (isShrunk) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    videoDescriptionTextView.setMaxLines(Integer.MAX_VALUE);
                    showMore.setText("Show Less");
                    isShrunk = false;
                });

            } else {
                new Handler(Looper.getMainLooper()).post(() -> {
                    showMore.setText("Show More");
                    videoDescriptionTextView.setMaxLines(4);
                    isShrunk = true;
                });
                new Handler(Looper.getMainLooper()).post(() -> {
                    Intent intent = new Intent();
                    intent.setAction(SCROLL_TO);
                    intent.putExtra("position", position);
                    LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(intent);
                });
            }
        }
    }
}
