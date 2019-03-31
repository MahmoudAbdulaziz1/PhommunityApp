package mahmoudvic.org.phomunity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;

public class MainSchoolActivity extends AppCompatActivity {


//    private TextView schoolCourse;
    private TextView schoolEvents;
    private TextView schoolGroups;
    private TextView schoolVideos;
    private TextView schoolTips;
    private TextView schoolEvaluation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_school);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(MainSchoolActivity.this, toolbarLayoutFragment);
        TextView  schoolCourse = (TextView) findViewById(R.id.school_course_txt);
        schoolEvents = (TextView) findViewById(R.id.school_events_txt);
        schoolGroups = (TextView) findViewById(R.id.school_groups_txt);
        schoolVideos = (TextView) findViewById(R.id.school_videos_txt);
        schoolTips = (TextView) findViewById(R.id.school_tips_txt);
        schoolEvaluation = (TextView) findViewById(R.id.school_evaluation_txt);



        schoolCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CourseActivity.class);
                startActivity(intent);
            }
        });

        schoolEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EventsActivity.class);
                startActivity(intent);
            }
        });
        schoolGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GroupsActivity.class);
                startActivity(intent);
            }
        });

        schoolVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), VideosActivity.class);
                startActivity(intent);
            }
        });

        schoolTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TipsActivity.class);
                startActivity(intent);
            }
        });

        schoolEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainEvaluationActivity.class);
                startActivity(intent);
            }
        });

        Typeface quickSand = Typeface.createFromAsset(MainSchoolActivity.this.getAssets(), "font/Quicksand-Bold.ttf");
        schoolCourse.setTypeface(quickSand);
        schoolEvents.setTypeface(quickSand);
        schoolGroups.setTypeface(quickSand);
        schoolVideos.setTypeface(quickSand);
        schoolTips.setTypeface(quickSand);
        schoolEvaluation.setTypeface(quickSand);

    }


    @Override
    public void onDestroy() {
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
        super.onDestroy();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
    }
}
