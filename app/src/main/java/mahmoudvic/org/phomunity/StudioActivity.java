package mahmoudvic.org.phomunity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.ImageSliderAdapter;
import mahmoudvic.org.phomunity.adapters.StudioRentAdapter;
import mahmoudvic.org.phomunity.pojo.ImagePOJO;
import mahmoudvic.org.phomunity.pojo.StudioOptionsPOJO;
import mahmoudvic.org.phomunity.pojo.StudiosPOJO;
import mahmoudvic.org.phomunity.pojo.VideoStudioPOJO;
import mahmoudvic.org.phomunity.util.VideoUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudioActivity extends AppCompatActivity {

    private static final int RECOVERY_REQUEST = 1;
    //    private String videoURL = null;
    public static final int YOUTUBE_VIDEO_ID_LENGTH = 11;
//    private YouTubePlayerView youTubeView;

    private List<StudioOptionsPOJO> options = new ArrayList<>();
    private List<ImagePOJO> slides = new ArrayList<>();
    private List<VideoStudioPOJO> video = new ArrayList<>();
    private LinearLayout studioLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        studioLayout = (LinearLayout) findViewById(R.id.studio_layout);
//        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
//        ToolbarUtil.setFragment(StudioActivity.this, toolbarLayoutFragment);

        loadStudios();
    }

    public String getVideoId(String videoURL) {
        String videoId = VideoUtil.extractVideoIdFromUrl(videoURL).substring(0, YOUTUBE_VIDEO_ID_LENGTH);
        return videoId;

    }

//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
//        if (!wasRestored) {
//            player.cueVideo(getVideoId(videoURL));
//        }
//    }

//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
//        if (errorReason.isUserRecoverableError()) {
//            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
//        } else {
//            String error = String.format(getString(R.string.player_error), errorReason.toString());
//            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == RECOVERY_REQUEST) {
//            // Retry initialization if user performed a recovery action
//            getYouTubePlayerProvider().initialize(PlayerConfig.YOUTUBE_API_KEY, this);
//        }
//    }
//
//    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
//        return youTubeView;
//    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

//    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {
//
//        @Override
//        public void onPlaying() {
//            // Called when playback starts, either due to user action or call to play().
//            showMessage("Video is Playing");
//        }
//
//        @Override
//        public void onPaused() {
//            // Called when playback is paused, either due to user action or call to pause().
//            showMessage("Video has Paused");
//        }
//
//        @Override
//        public void onStopped() {
//            // Called when playback stops for a reason other than being paused.
//            showMessage("Video has Stopped");
//        }
//
//        @Override
//        public void onBuffering(boolean b) {
//            // Called when buffering starts or ends.
//        }
//
//        @Override
//        public void onSeekTo(int i) {
//            // Called when a jump in playback position occurs, either
//            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
//        }
//    }

//    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
//
//        @Override
//        public void onLoading() {
//            // Called when the player is loading a video
//            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
//        }
//
//        @Override
//        public void onLoaded(String s) {
//            // Called when a video is done loading.
//            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
//        }
//
//        @Override
//        public void onAdStarted() {
//            // Called when playback of an advertisement starts.
//        }
//
//        @Override
//        public void onVideoStarted() {
//            // Called when playback of the video starts.
//        }
//
//        @Override
//        public void onVideoEnded() {
//            // Called when the video reaches its end.
//        }
//
//        @Override
//        public void onError(YouTubePlayer.ErrorReason errorReason) {
//            // Called when an error occurs.
//        }
//
//    }

//    public String getVideoURL() {
//        return this.videoURL;
//    }
//
//    public void setVideoURL(String videoURL) {
//        this.videoURL = videoURL;
//    }

    private void loadStudios() {
        Api.getClient().getStudios().enqueue(new Callback<StudiosPOJO>() {
            @Override
            public void onResponse(Call<StudiosPOJO> call, Response<StudiosPOJO> response) {
                if (response != null && response.isSuccessful()) {
                    if (response.body() != null) {
                        StudiosPOJO studiosPOJO = response.body();
                        options = studiosPOJO.getOptions();
                        slides = studiosPOJO.getSlides();
                        video = studiosPOJO.getVideo();
                        loadSlider();
                        loadStudioDetails();
                        loadYoutubeFragment();
                    } else {
                        Toast.makeText(StudioActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(StudioActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StudiosPOJO> call, Throwable t) {
                Toast.makeText(StudioActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();
            }
        });
    }

    //    private void loadVideo(){
//        // youtube player
//        videoURL = video.get(0).getValue();
//        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
//        youTubeView.initialize(PlayerConfig.YOUTUBE_API_KEY, this);
//
//    }
    private void loadSlider() {
        RecyclerView recyclerView = findViewById(R.id.image_slider);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StudioActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if (slides != null) {
            ImageSliderAdapter adapter = new ImageSliderAdapter(StudioActivity.this, slides);
            recyclerView.setAdapter(adapter);
        } else {
            // show message to the user
        }
    }

    private void loadStudioDetails() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.studio_rent_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        if (options != null) {
            StudioRentAdapter adapter = new StudioRentAdapter(StudioActivity.this, options);
            recyclerView.setAdapter(adapter);
        } else {
            // show message to the user
        }

    }

    private void loadYoutubeFragment() {
        Bundle bundle = new Bundle();
        String videoURL = video.get(0).getValue();

        bundle.putString(YoutubeFragment.VIDEO_ID_KEY, getVideoId(videoURL));
// set Fragmentclass Arguments

        YoutubeFragment youtubeFragment = new YoutubeFragment();
        youtubeFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_layout, youtubeFragment).commit();
    }
}
