package com.tanaka.mazivanhanga.youtubeplaylist.models;

public class VideoListItem {
    private String videoTitle, videoDescription, videoThumbnailUrl, videoId, playlistId;

    public VideoListItem(String videoTitle, String videoDescription, String videoThumbnailUrl, String videoId, String playlistId) {
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.videoThumbnailUrl = videoThumbnailUrl;
        this.videoId = videoId;
        this.playlistId = playlistId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public String getVideoThumbnailUrl() {
        return videoThumbnailUrl;
    }

    public String getVideoUrl() {
        return "https://www.youtube.com/watch?v=" + videoId + "&list=" + playlistId;
    }
}
