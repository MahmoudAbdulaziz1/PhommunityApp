package mahmoudvic.org.phomunity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.pojo.GetProfilePOJO;
import mahmoudvic.org.phomunity.services.Listener;
import mahmoudvic.org.phomunity.services.MyService;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    //Button btn;
    private Toolbar homeToolbar;
    private ImageView menu;
    private ImageView notification;
    private LinearLayout frameLayout;
    private RelativeLayout relativeLayout;
    private boolean layoutVisible = false;

    //    private TextView userNameBtn;
//    private TextView feedbackBtn;
//    private TextView joinOurTeamBtn;
//    private TextView aboutUsBtn;
//    private TextView contactUsBtn;
//    private TextView signOutBtn;
//    private TextView homeFirstName;
    private TextView welcome;
    private TextView user_name;
    //private int testNotify = 0;
    private Button schoolBtn;
    private Button sellBtn;
    private Button rentBtn;
    private Button cpcLabBtn;
    private Button cpcStudioBtn;
    private Button helpBtn;
    private Button assistantBtn;
    private SharedPreferences userDataPreference;
    private ToolbarNotificationCoding notificationItem;
    private ToolbarMenuCoding menuItem;
    private ToolbarCoding toolbarItem;

    public static void setBadge(Context context, int count) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    public static String getLauncherClassName(Context context) {

        PackageManager pm = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            String pkgName = resolveInfo.activityInfo.applicationInfo.packageName;
            if (pkgName.equalsIgnoreCase(context.getPackageName())) {
                String className = resolveInfo.activityInfo.name;
                return className;
            }
        }
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Typeface quickSand = Typeface.createFromAsset(getBaseContext().getAssets(), "font/Quicksand-Bold.ttf");
        setBadge(this, 0);
        if (!isMyServiceRunning(MyService.class)) {
            startService(new Intent(this, MyService.class));
        }
        user_name = (TextView) findViewById(R.id.user_name);
        user_name.setTypeface(quickSand);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        HomeToolbarFragment toolbarLayoutFragment = new HomeToolbarFragment();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.about_toolbar_container, toolbarLayoutFragment);
        t.commit();

        user_name.setVisibility(View.VISIBLE);

        schoolBtn = (Button) findViewById(R.id.school);
        rentBtn = (Button) findViewById(R.id.rent);
        sellBtn = (Button) findViewById(R.id.sell);
        cpcLabBtn = (Button) findViewById(R.id.cbc_lab);
        cpcStudioBtn = (Button) findViewById(R.id.cbc_studio);
        helpBtn = (Button) findViewById(R.id.help);
        assistantBtn = (Button) findViewById(R.id.assistant_second_shooter);

        //user_name.setText(getSharedPreferences("remember", MODE_PRIVATE).getString("first", "") + " " + getSharedPreferences("second", MODE_PRIVATE).getString("", ""));
        userDataPreference = getSharedPreferences("remember", MODE_PRIVATE);
        Api.getClient().getProfile(getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0)).
                enqueue(new Callback<List<GetProfilePOJO>>() {
                    @Override
                    public void onResponse(Call<List<GetProfilePOJO>> call, Response<List<GetProfilePOJO>> response) {


                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                //userNameBtn.setText(response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName());
                                String name = response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName();
                                SharedPreferences.Editor editor = userDataPreference.edit();
                                editor.putString("name", name);
                                editor.commit();
                                user_name.setText(response.body().get(0).getFirstName());
                                user_name.setTypeface(quickSand);
                                //menuItem = new ToolbarMenuCoding(HomeActivity.this, HomeActivity.this, name);

                                //toolbarItem.onMenuClick();
                                //toolbarItem.onNotificationClick();

                            } else {
                                Toast.makeText(HomeActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(HomeActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetProfilePOJO>> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();

                    }
                });


        schoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(MainSchoolActivity.class);
//                Toast.makeText(getBaseContext(), "School", Toast.LENGTH_LONG).show();
            }
        });

        rentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(IrentMainActivity.class);
//                Intent moveToRent = new Intent(getBaseContext(), IrentMainActivity.class);
//                startActivity(moveToRent);
            }
        });
        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(IsellMainActivity.class);
            }
        });
        cpcLabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(LabActivity.class);
            }
        });
        cpcStudioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(StudioActivity.class);
            }
        });
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(HelpActivity.class);

            }
        });
        assistantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(MainSecondShooterActivity.class);
            }
        });

//        userNameBtn = (TextView) findViewById(R.id.user_name);
//        userNameBtn.setText(userDataPreference.getString("first", "notFound"));

    }

    private void loadActivity(Class activityClass) {
        Intent moveToActivity = new Intent(getBaseContext(), activityClass);
        startActivity(moveToActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // welcome.setVisibility(View.VISIBLE);
        user_name.setVisibility(View.VISIBLE);
        final Typeface quickSand = Typeface.createFromAsset(getBaseContext().getAssets(), "font/Quicksand-Bold.ttf");
        //user_name = (TextView) findViewById(R.id.user_name);
        user_name.setTypeface(quickSand, Typeface.BOLD);
    }

    @Override
    public void onDestroy() {
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
        super.onDestroy();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

}
