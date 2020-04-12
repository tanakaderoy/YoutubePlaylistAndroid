package com.tanaka.mazivanhanga.youtubeplaylist.models;

public class ChannelListItem {
    private String channelImageURL;
    private String channelName;
    private String channelId;
    private int uploadCount;

    public ChannelListItem(String channelImageURL, String channelName, String channelId, int uploadCount) {
        this.channelImageURL = channelImageURL;
        this.channelName = channelName;
        this.channelId = channelId;
        this.uploadCount = uploadCount;
    }

    public String getChannelImageURL() {
        return channelImageURL;
    }

    public String getChannelName() {
        return channelName;
    }

    @Override
    public String toString() {
        return "ChannelListItem{" +
                "channelImageURL='" + channelImageURL + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }

    public String getChannelId() {
        return channelId;
    }

    public int getUploadCount() {
        return uploadCount;
    }
}
