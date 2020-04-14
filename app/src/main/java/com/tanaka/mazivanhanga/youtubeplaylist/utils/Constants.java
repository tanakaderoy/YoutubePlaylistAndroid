package com.tanaka.mazivanhanga.youtubeplaylist.utils;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.APIKEY.API_KEY;

public class Constants {
    public static final String LETMEINNNNNNN = API_KEY;
    public static final String BASEURL = "https://www.googleapis.com/youtube/v3/";
    public static final String PLAYLIST_ID = "playlistId";
    public static final String PLAYLIST_NAME = "playlistName";
    static final String LOADING = "Loading...";
    public static final String CHANNEL_ID = "channelId";
    public static final String CHANNEL_NAME = "channelName";
    public static final String CHANNEL_IMAGE = "channelImage";
    public static final String UPLOAD_COUNT = "uploadCount";
    public static final String SCROLL_TO = "com.tanaka.mazivanhanga.youtubeplaylist.scrollToIntent";

    /**
     * Takes in an base64encoded string and outputs the decoded string value
     *
     * @param coded string
     * @return decoded string
     */
    private static String base64Decode(String coded) {
        byte[] valueDecoded;
        valueDecoded = Base64.decode(coded.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        return new String(valueDecoded);
    }
}
