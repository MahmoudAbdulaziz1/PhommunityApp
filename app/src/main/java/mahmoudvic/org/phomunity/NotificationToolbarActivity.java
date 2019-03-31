package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.NotificationAdapter;
import mahmoudvic.org.phomunity.pojo.NotificationsPOJO;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationToolbarActivity extends Activity {


    public static Activity fas;
    private RecyclerView recyclerView;
    private FrameLayout notifyIn;
    private TextView notifyCount;
    private ArrayList<String> newNotify = new ArrayList<String>(Arrays.asList("What is Question1?", "What is Question2?", "What is Question3?"));
    private ArrayList<String> earlierNotify = new ArrayList<String>(Arrays.asList("This is Answer1", "This is Answer2", "This is Answer3", "This is Answer4", "This is Answer5", "This is Answer6", "This is Answer7", "This is Answer8", "This is Answer9"));
    private List<String> titleList = new ArrayList<>();
    private List<String> messageList = new ArrayList<>();
    private List<String> linkList = new ArrayList<>();
    private int newNotifyBun = 0;
    private Button clickMenu;
    private Button clickNotify;
    private Button clickBack;
    private Button clickHome;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Window window = getWindow();
//        // Let touches go through to apps/activities underneath.
//        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        setContentView(R.layout.activity_notification_toolbar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.home_notify_layoutss);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        Log.d("xxx", getIntent().getIntExtra("tool", 0) + "");
        params.setMargins(0, getIntent().getIntExtra("tool", 0), 0, 0);
        layout.setLayoutParams(params);
        recyclerView = (RecyclerView) findViewById(R.id.notification_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        fas = this;

        clickMenu = (Button) findViewById(R.id.click_menu_n);
        clickMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("menu", false)) {
                    //menuLayout.setVisibility(View.GONE);

                    getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
                    finish();
                    Log.d("finish", "test");
                } else {
                    if (getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("notify", false)) {
                        //notiLayout.setVisibility(View.GONE);

                        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
                        finish();
                    }


                    getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", true).commit();
                    //menuLayout.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getBaseContext(), MenuToolbarACtivity.class);
                    int toolbarHeight = getIntent().getIntExtra("tool", 0);
                    intent.putExtra("tool", toolbarHeight);
                    startActivity(intent);
                    finish();
                }
            }
        });
        clickNotify = (Button) findViewById(R.id.click_notify_n);
        clickNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notifyIn.setVisibility(View.GONE);
                if (getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("notify", false)) {
                    //menuLayout.setVisibility(View.GONE);
                    getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
                    finish();
                    Log.d("finish", "test");
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
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

        clickBack = (Button) findViewById(R.id.click_back_n);
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        clickHome = (Button) findViewById(R.id.click_home_n);
        clickHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


        Api.getClient().getNotifications(getSharedPreferences("remember", Context.MODE_PRIVATE).getInt("id", 0)).enqueue(new Callback<List<NotificationsPOJO>>() {
            @Override
            public void onResponse(Call<List<NotificationsPOJO>> call, Response<List<NotificationsPOJO>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().size() == 0) {
                            Log.d("good", "empty one");
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            titleList.clear();
                            messageList.clear();
                            linkList.clear();
                            recyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < response.body().size(); i++) {
                                titleList.add(response.body().get(i).getTitle());
                                Log.d("tt", response.body().get(i).getTitle());
                                messageList.add(response.body().get(i).getMessage());
                                linkList.add(response.body().get(i).getLink());
                            }

                            recyclerView.setVisibility(View.VISIBLE);
                            NotificationAdapter adapter = new NotificationAdapter(getBaseContext(), titleList, messageList,
                                    linkList, newNotifyBun, NotificationToolbarActivity.this);
                            recyclerView.setAdapter(adapter);
                            Log.d("good", "added");

                            SharedPreferences pref = getBaseContext().getSharedPreferences("notify", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            Set<String> titles = new HashSet<>();
                            titles.addAll(titleList);
                            Set<String> links = new HashSet<>();
                            titles.addAll(linkList);
                            editor.putStringSet("title", titles);
                            editor.putStringSet("link", links);
                            editor.commit();


                            Log.d("size1", titleList.size() + "ss");
                            //notifyIn.setVisibility(View.VISIBLE);
                            //notifyCount.setText(val + "");


                        }
                    } else {
                        Log.d("bad", "BAAAD");
                        Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NotificationsPOJO>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
        super.onDestroy();
        getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
    }
}
