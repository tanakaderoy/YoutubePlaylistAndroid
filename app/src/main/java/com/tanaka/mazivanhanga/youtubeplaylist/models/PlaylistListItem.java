package com.tanaka.mazivanhanga.youtubeplaylist.models;

public class PlaylistListItem {
    private String playlistName;
    private String playlistThumbnailURL, playlistId;
    private int playlistVideoCount;

    public PlaylistListItem(String playlistName, String playlistThumbnailURL, int playlistVideoCount, String playlistId) {
        this.playlistName = playlistName;
        this.playlistThumbnailURL = playlistThumbnailURL;
        this.playlistVideoCount = playlistVideoCount;
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getPlaylistThumbnailURL() {
        return playlistThumbnailURL;
    }

    public int getPlaylistVideoCount() {
        return playlistVideoCount;
    }

    public String getPlaylistId() {
        return playlistId;
    }
}


