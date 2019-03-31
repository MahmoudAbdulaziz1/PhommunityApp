package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MenuToolbarACtivity extends Activity {

    public static Activity fa;
    private TextView userNameBtn;
    private TextView feedbackBtn;
    private TextView joinOurTeamBtn;
    private TextView aboutUsBtn;
    private TextView contactUsBtn;
    private TextView signOutBtn;
    private LinearLayout menuLayout;
    private LinearLayout notiLayout;
    private RecyclerView recyclerView;
    private FrameLayout notifyIn;
    private TextView notifyCount;
    private Button clickMenu;
    private Button clickNotify;
    private Button clickBack;
    private Button clickHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Window window = getWindow();
//
//        // Let touches go through to apps/activities underneath.
//        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        setContentView(R.layout.activity_menu_toolbar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.home_menu_layoutss);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        Log.d("xxx", getIntent().getIntExtra("tool", 0) + "");
        params.setMargins(0, getIntent().getIntExtra("tool", 0), 0, 0);
        layout.setLayoutParams(params);
        userNameBtn = (TextView) findViewById(R.id.home_menu_name);
        feedbackBtn = (TextView) findViewById(R.id.home_menu_feedback);
        joinOurTeamBtn = (TextView) findViewById(R.id.home_menu_join_team);
        aboutUsBtn = (TextView) findViewById(R.id.home_menu_about_us);
        contactUsBtn = (TextView) findViewById(R.id.home_menu_contact_us);
        signOutBtn = (TextView) findViewById(R.id.home_menu_sign_out);
        final Typeface quickSand = Typeface.createFromAsset(getBaseContext().getAssets(), "font/Quicksand-Bold.ttf");
        userNameBtn.setTypeface(quickSand, Typeface.BOLD);
        feedbackBtn.setTypeface(quickSand, Typeface.BOLD);
        joinOurTeamBtn.setTypeface(quickSand, Typeface.BOLD);
        aboutUsBtn.setTypeface(quickSand, Typeface.BOLD);
        contactUsBtn.setTypeface(quickSand, Typeface.BOLD);
        signOutBtn.setTypeface(quickSand, Typeface.BOLD);
        userNameBtn.setText(getSharedPreferences("remember", Context.MODE_PRIVATE).getString("name", ""));
        textViewListener();
        fa = this;
        clickMenu = (Button) findViewById(R.id.click_menu);
        clickMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("menu", false)) {
                    //menuLayout.setVisibility(View.GONE);
                    getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
                    finish();
                    Log.d("finish", "test1");
                } else {
                    if (getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("notify", false)) {
                        //notiLayout.setVisibility(View.GONE);
                        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
                        finish();
                        Log.d("finish notify", "test notify in menu");
                    }


                    getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", true).commit();
                    //menuLayout.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getBaseContext(), MenuToolbarACtivity.class);
                    int toolbarHeight = getIntent().getIntExtra("tool", 0);
                    intent.putExtra("tool", toolbarHeight);
                    startActivity(intent);
                }
            }
        });
        clickNotify = (Button) findViewById(R.id.click_notify);
        clickNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("notify", false)) {
                    getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
                    finish();
                    Log.d("finish", "test2");
                } else {
                    if (getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("menu", false)) {
                        //notiLayout.setVisibility(View.GONE);
                        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
                        finish();
                    }


                    getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", true).commit();
                    //menuLayout.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getBaseContext(), NotificationToolbarActivity.class);
                    int toolbarHeight = getIntent().getIntExtra("tool", 0);
                    intent.putExtra("tool", toolbarHeight);
                    startActivity(intent);
                }
            }
        });

        clickBack = (Button) findViewById(R.id.click_back);
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        clickHome = (Button) findViewById(R.id.click_home);
        clickHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void textViewListener() {

        userNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menuLayout.setVisibility(View.GONE);
                //menuVisibility = false;
                getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();

                Intent moveToProfile = new Intent(getBaseContext(), UserProfileActivity.class);
                startActivity(moveToProfile);
                finish();
            }
        });

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainLayout.setVisibility(View.GONE);
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
                getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
                Intent moveToFeedback = new Intent(getBaseContext(), FeedbackActivity.class);
                startActivity(moveToFeedback);
            }
        });

        joinOurTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
                getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
                Intent moveToJoinToOurTeam = new Intent(getBaseContext(), JoinOurTeamActivity.class);
                startActivity(moveToJoinToOurTeam);
                finish();
            }
        });

        aboutUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
                Intent moveToAboutUs = new Intent(getBaseContext(), AboutUsActivity.class);
                startActivity(moveToAboutUs);
                finish();
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
                getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
//                Toast.makeText(getBaseContext(), "out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences("remember", Context.MODE_PRIVATE).edit();
                editor.putBoolean("checked", false);
                editor.commit();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //menuLayout.setVisibility(View.GONE);
                //menuVisibility = false;
                Intent moveToJoinToOurTeam = new Intent(getBaseContext(), ContactUsActivity.class);
                startActivity(moveToJoinToOurTeam);
            }
        });

    }


    @Override
    protected void onDestroy() {
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        super.onDestroy();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();

    }
}
