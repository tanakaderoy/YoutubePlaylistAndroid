package com.tanaka.mazivanhanga.youtubeplaylist.utils;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Constants {
    public static final String LETMEINNNNNNN = de64It("QUl6YVN5QmNOU1FDdFRjR1E1WFRVMGtSc0haYjhGWnNXYXFBUzFB");
    public static final String BASEURL = "https://www.googleapis.com/youtube/v3/";
    public static final String PLAYLIST_ID = "playlistId";
    public static final String PLAYLIST_NAME = "playlistName";
    public static final String LOADING = "Loading...";
    public static final String CHANNEL_ID = "channelId";
    public static final String CHANNEL_NAME = "channelName";
    public static final String CHANNEL_IMAGE = "channelImage";
    public static final String UPLOAD_COUNT = "uploadCount";

    /**
     * Takes in an base64encoded string and outputs the decoded string value
     *
     * @param coded string
     * @return decoded string
     */
    private static String de64It(String coded) {
        byte[] valueDecoded;
        valueDecoded = Base64.decode(coded.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        return new String(valueDecoded);
    }
}
