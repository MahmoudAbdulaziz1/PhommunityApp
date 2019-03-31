package mahmoudvic.org.phomunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;

public class ContactUsActivity extends AppCompatActivity {

    private ImageView fbook;
    private ImageView youtube;
    private ImageView insta;
    private ImageView twitter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Bold.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(ContactUsActivity.this, toolbarLayoutFragment);

        fbook = (ImageView) findViewById(R.id.facebook_btn);
        youtube = (ImageView) findViewById(R.id.youtube_btn);
        twitter = (ImageView) findViewById(R.id.twitter_btn);
        insta = (ImageView) findViewById(R.id.insta_btn);
        fbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/Cphotographyclub/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                if (likeIng.resolveActivity(getPackageManager())!= null){
                    Intent intent = Intent.createChooser(likeIng, "choose your app");
                    startActivity(intent);
                }
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/user/CairoPhotographyClub");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                if (likeIng.resolveActivity(getPackageManager())!= null){
                    Intent intent = Intent.createChooser(likeIng, "choose your app");
                    startActivity(intent);
                }
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/explore/locations/250759419/cairo-photography-club/?hl=en");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                if (likeIng.resolveActivity(getPackageManager())!= null){
                    Intent intent = Intent.createChooser(likeIng, "choose your app");
                    startActivity(intent);
                }
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://twitter.com/cpc_club");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                if (likeIng.resolveActivity(getPackageManager())!= null){
                    Intent intent = Intent.createChooser(likeIng, "choose your app");
                    startActivity(intent);
                }
            }
        });
    }
}
