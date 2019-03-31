package mahmoudvic.org.phomunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;

import mahmoudvic.org.phomunity.adapters.IrentImageSlider;
import me.anwarshahriar.calligrapher.Calligrapher;

public class IsellDetailedItemActivity extends YouTubeBaseActivity {

    public static final int YOUTUBE_VIDEO_ID_LENGTH = 11;
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private String videoURL = "https://www.youtube.com/watch?v=fhWaJi1Hsfo";
    private ArrayList<Integer> imageList = new ArrayList<>(Arrays.asList(R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test,
            R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test));

    private Button call;
    private Button request;
    private String phone1;
    private String branches = "";
    private TextView cameraNameTxt;
    private TextView cameraBrandTxt;
    private TextView priceTxt;
    private TextView branchTxt;
    private TextView descTxt;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_isell_detailed_item);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        cameraNameTxt = (TextView) findViewById(R.id.isell_detailed_camera_name);
        cameraBrandTxt = (TextView) findViewById(R.id.isell_detailed_camera_brand);
        priceTxt = (TextView) findViewById(R.id.isell_detailed_camera_price);
        branchTxt = (TextView) findViewById(R.id.isell_detailed_camera_branch);
        descTxt = (TextView) findViewById(R.id.isell_detailed_desc);
        Intent intent = getIntent();
        Bundle phones = intent.getBundleExtra("phone");
        Bundle branch = intent.getBundleExtra("branch");
        Bundle get = intent.getBundleExtra("s");
        int phonseSize = phones.getInt("size");
        Log.d("size", phonseSize+"");
        if (phonseSize > 0) {
            phone1 = phones.getString("phone0");
            Log.d("psssssssss", phones.getString("phone0")+" ");

        }

        int branchSize = branch.getInt("size");
        if (branchSize > 0) {
            for (int i = 0; i < branchSize; i++) {
                branches+= branch.getString("branch" + i) + " ";
            }

        }
        Bundle images = intent.getBundleExtra("image");
        int imageSize = images.getInt("size");
        ArrayList<String> img = new ArrayList<>();
        if (imageSize > 0) {
            for (int i = 0; i < imageSize; i++) {
                img.add(images.getString("image" + i) );
            }

        }
        final int id = get.getInt("id");
        String desc = get.getString("desc");
        int cat_id = get.getInt("cat_id");
        int brand_id = get.getInt("brand_id");
        int verified = get.getInt("verified");
        int available = get.getInt("available");
        String cat_name = get.getString("cat_name");
        String brand_name = get.getString("brand_name");
        final String video = get.getString("video");
        String name = get.getString("name");
        double price = get.getDouble("price");
        cameraNameTxt.setText(name);
        cameraBrandTxt.setText(brand_name);
        priceTxt.setText(price+" LE (24H) "+ price/2+" LE (12H)");
        branchTxt.setText(branches);
        descTxt.setText(desc);

        ImageView close = (ImageView) findViewById(R.id.isell_closeId);
        youTubeView = (YouTubePlayerView) findViewById(R.id.isell_youtube_view);
        if (video.trim().equals("") || video.trim().equals(null)){
            youTubeView.setVisibility(View.GONE);
        }else {
            youTubeView.setVisibility(View.VISIBLE);
            youTubeView.initialize(PlayerConfig.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {

                        youTubePlayer.cueVideo(getVideoId(video)); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    if (youTubeInitializationResult.isUserRecoverableError()) {
                        youTubeInitializationResult.getErrorDialog(IsellDetailedItemActivity.this, RECOVERY_REQUEST).show();
                    } else {
                        String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
                        Toast.makeText(IsellDetailedItemActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.dismiss();
                finish();
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.isell_image_slider);
        LinearLayoutManager layoutManager = new LinearLayoutManager(IsellDetailedItemActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        IrentImageSlider slider = new IrentImageSlider(IsellDetailedItemActivity.this, img);
        recyclerView.setAdapter(slider);

        call = (Button) findViewById(R.id.isell_call_btn);
        request = (Button) findViewById(R.id.isell_request_btns);
        request.setText("Request");
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone1));
                startActivity(intent);
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(IsellDetailedItemActivity.this, SellCameraActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }

    public String getVideoId(String videoURL) {
        //String result = videoURL.substring(videoURL.lastIndexOf("?v=") + 1).trim();
        String videoId = videoURL.substring(videoURL.length() - YOUTUBE_VIDEO_ID_LENGTH);
        return videoId;

    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    private void showMessage(String message) {
        Toast.makeText(IsellDetailedItemActivity.this, message, Toast.LENGTH_LONG).show();
    }


    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().
            showMessage("Playing");
        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().
            showMessage("Paused");
        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
            showMessage("Stopped");
        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }


    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }

        @Override
        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
        }

        @Override
        public void onVideoEnded() {
            // Called when the video reaches its end.
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }

    }


}
