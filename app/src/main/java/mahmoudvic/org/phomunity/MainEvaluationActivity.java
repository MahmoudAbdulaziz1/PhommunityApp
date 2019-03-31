package mahmoudvic.org.phomunity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;

public class MainEvaluationActivity extends AppCompatActivity {


    private CardView photography;
    private CardView videography;
    private CardView videoEditing;
    private CardView photoEditing;
    private CardView lighting;

    private TextView photographyTextView;
    private TextView videographyTextView;
    private TextView videoEditingTextView;
    private TextView photoEditingTextView;
    private TextView lightingTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_evaluation);
//        Calligrapher calligrapher = new Calligrapher(this);
//        calligrapher.setFont(this, "font/Quicksand-Bold.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(MainEvaluationActivity.this, toolbarLayoutFragment);
        photography = (CardView) findViewById(R.id.photography_cardview);
        videography = (CardView) findViewById(R.id.videography_cardView);
        videoEditing = (CardView) findViewById(R.id.video_editing_cardview);
        photoEditing = (CardView) findViewById(R.id.photo_editing_cardview);
        lighting = (CardView) findViewById(R.id.lighting_cardview);

        photographyTextView = (TextView) findViewById(R.id.photography_textView);
        videographyTextView = (TextView) findViewById(R.id.videography_textview);
        videoEditingTextView = (TextView) findViewById(R.id.video_editing_textview);
        photoEditingTextView = (TextView) findViewById(R.id.photo_editing_textview);
        lightingTextView = (TextView) findViewById(R.id.lighting_textview);

        Typeface quickSand = Typeface.createFromAsset(MainEvaluationActivity.this.getAssets(), "font/Quicksand-Bold.ttf");
        photographyTextView.setTypeface(quickSand);
        videographyTextView.setTypeface(quickSand);
        videoEditingTextView.setTypeface(quickSand);
        photoEditingTextView.setTypeface(quickSand);
        lightingTextView.setTypeface(quickSand);

        photography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EvaluationPhotographyActivity.class);
                startActivity(intent);
            }
        });

        videography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EvaluationvideographyActivity.class);
                startActivity(intent);
            }
        });
        videoEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EvaluationVideoEditingActivity.class);
                startActivity(intent);
            }
        });

        photoEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EvaluationPhotoEditingActivity.class);
                startActivity(intent);
            }
        });

        lighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EvaluationLightingActivity.class);
                startActivity(intent);
            }
        });


    }
}
