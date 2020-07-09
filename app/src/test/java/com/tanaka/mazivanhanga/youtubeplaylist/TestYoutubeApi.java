package com.tanaka.mazivanhanga.youtubeplaylist;

import com.google.gson.Gson;
import com.tanaka.mazivanhanga.youtubeplaylist.api.Youtube;
import com.tanaka.mazivanhanga.youtubeplaylist.models.ChannelSearchResponse;
import com.tanaka.mazivanhanga.youtubeplaylist.models.PlaylistResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.SingleObserver;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.BASEURL;
import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.LETMEINNNNNNN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class TestYoutubeApi {
    private MockWebServer server;
    private Youtube youtube;
    private Retrofit retrofit;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();

        retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        youtube = new Youtube(retrofit);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void read_simple_file_success() {
        MockResponseFileReader reader = new MockResponseFileReader("test.json");
        assertEquals("success", reader.getContent());
    }

    @Test
    public void test_channel_search_response_success() throws IOException {
        server.enqueue(new MockResponse().setBody(new MockResponseFileReader("channel_search_success.json").getContent()));

        youtube.getYoutubeService().getChannelSearchResponse("wisecrack").test().awaitDone(3, TimeUnit.SECONDS).assertComplete()
                .assertValue(value -> value.getItems().get(0).getSnippet().getChannelTitle().equals("Wisecrack")).assertNoErrors();
    }

    @Test
    public void test_playlist_response_success() {
        MockResponseFileReader playlistResponseReader = new MockResponseFileReader("playlist_response_success.json");
        server.enqueue(new MockResponse().setBody(playlistResponseReader.getContent()));
        Gson gson = new Gson();
        PlaylistResponse playlistResponse = gson.fromJson(playlistResponseReader.getContent(), PlaylistResponse.class);
        assertNotNull(playlistResponse);
        youtube.getYoutubeService().playlistResponse("UC6-ymYjG0SU0jUWnWh9ZzEQ").test().awaitDone(3, TimeUnit.SECONDS).assertComplete()
                .assertValue(value -> value.equals(playlistResponse))
                .assertValue(value -> value.getEtag().equals(playlistResponse.getEtag())).assertNoErrors();

    }
}