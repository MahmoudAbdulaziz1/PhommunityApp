package mahmoudvic.org.phomunity.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mahmoudvic.org.phomunity.PlayerConfig;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.VideoPOJO;

public class VideosAdapter extends RecyclerView.Adapter {

    //these ids are the unique id for each video
    final String youTubeUrlRegEx = "^(https?)?(://)?(www.)?(m.)?((youtube.com)|(youtu.be))/";
    final String[] videoIdRegex = {"\\?vi?=([^&]*)", "watch\\?.*v=([^&]*)", "(?:embed|vi?)/([^/?]*)", "^([A-Za-z0-9\\-]*)"};

    //    String[] VideoID = {"P3mAtvs5Elc", "nCgQDjiotG0", "P3mAtvs5Elc"};
    Context ctx;
    List<VideoPOJO> videoPOJOS;
    public VideosAdapter(){}
    public VideosAdapter(Context context, List<VideoPOJO> videoPOJOS) {
        this.ctx = context;
        this.videoPOJOS = videoPOJOS;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == 1) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.first_index_image, parent, false);
            return new VideosAdapter.MyImageHolder(itemView);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.video_item, parent, false);
            return new VideosAdapter.VideoInfoHolder(itemView);

        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof VideosAdapter.VideoInfoHolder) {
            final VideoPOJO videoPOJO = videoPOJOS.get(position);
            ((VideoInfoHolder) holder).videoTitle.setText(videoPOJO.getDescription());
            System.out.println(videoPOJO);
            final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                @Override
                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                }

                @Override
                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                    youTubeThumbnailView.setVisibility(View.VISIBLE);
                    ((VideoInfoHolder) holder).linearLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
                }
            };

            ((VideoInfoHolder) holder).youTubeThumbnailView.initialize(PlayerConfig.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo(extractVideoIdFromUrl(videoPOJOS.get(position).getUrl()));
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    Toast.makeText(ctx, "Failed to Initialize Video ", Toast.LENGTH_SHORT).show();

                }
            });
        }else {
            ((VideosAdapter.MyImageHolder) holder).fisrtIndexImage.setImageResource(R.drawable.video_header);
        }
    }

    @Override
    public int getItemCount() {
        return videoPOJOS.size();
    }

    public class MyImageHolder extends RecyclerView.ViewHolder {
        public ImageView fisrtIndexImage;
        public MyImageHolder(View view) {
            super(view);
            fisrtIndexImage = (ImageView) view.findViewById(R.id.first_index);
        }
    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout linearLayoutOverYouTubeThumbnailView;
        public YouTubeThumbnailView youTubeThumbnailView;
        public ImageView playButton;
        public TextView videoTitle;

        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public VideoInfoHolder(View itemView) {
            super(itemView);
            playButton = (ImageView) itemView.findViewById(R.id.btnYoutube_player);
            playButton.setOnClickListener(this);
            linearLayoutOverYouTubeThumbnailView = (LinearLayout) itemView.findViewById(R.id.linearLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
            videoTitle = (TextView) itemView.findViewById(R.id.video_title);

            videoTitle.setTypeface(quickSand);

        }

        @Override
        public void onClick(View v) {
            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx, PlayerConfig.YOUTUBE_API_KEY, extractVideoIdFromUrl(videoPOJOS.get(getLayoutPosition()).getUrl()));
            ctx.startActivity(intent);
        }
    }


    public String extractVideoIdFromUrl(String url) {
        String youTubeLinkWithoutProtocolAndDomain = youTubeLinkWithoutProtocolAndDomain(url);

        for (String regex : videoIdRegex) {
            Pattern compiledPattern = Pattern.compile(regex);
            Matcher matcher = compiledPattern.matcher(youTubeLinkWithoutProtocolAndDomain);

            if (matcher.find()) {
                return matcher.group(1);
            }
        }

        return null;
    }

    private String youTubeLinkWithoutProtocolAndDomain(String url) {
        Pattern compiledPattern = Pattern.compile(youTubeUrlRegEx);

        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            return url.replace(matcher.group(), "");
        }
        return url;
    }



}