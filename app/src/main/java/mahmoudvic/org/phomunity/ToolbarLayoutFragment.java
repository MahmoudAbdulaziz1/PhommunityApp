package mahmoudvic.org.phomunity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.NotificationAdapter;
import mahmoudvic.org.phomunity.pojo.NotificationsPOJO;
import mahmoudvic.org.phomunity.services.Listener;
import mahmoudvic.org.phomunity.services.MyService;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToolbarLayoutFragment extends Fragment implements Listener {


    //private MainToolbarCoding toolbarItem;

    SharedPreferences prf;
    int c = 0;
    List<String> titleList2 = new ArrayList<>();
    List<String> messageList2 = new ArrayList<>();
    List<String> linkList2 = new ArrayList<>();
    private Toolbar homeToolbar;
    private ImageView menu;
    private ImageView notification;
    private Context context;
    private RelativeLayout r;
    //    private TextView userNameBtn;
//    private TextView feedbackBtn;
//    private TextView feedbackBtn;
//    private TextView joinOurTeamBtn;
//    private TextView aboutUsBtn;
//    private TextView contactUsBtn;
//    private TextView signOutBtn;
    private boolean menuVisibility = false;
    private boolean notiVisibility = false;
    private SharedPreferences userDataPreference;
    private LinearLayout menuLayout;
    private LinearLayout notiLayout;
    private RecyclerView recyclerView;
    private FrameLayout notifyIn;
    private TextView notifyCount;
    private ArrayList<String> newNotify = new ArrayList<String>(Arrays.asList("What is Question1?", "What is Question2?", "What is Question3?"));
    private ArrayList<String> earlierNotify = new ArrayList<String>(Arrays.asList("This is Answer1", "This is Answer2", "This is Answer3", "This is Answer4", "This is Answer5", "This is Answer6", "This is Answer7", "This is Answer8", "This is Answer9"));
    private List<String> titleList = new ArrayList<>();
    private List<String> messageList = new ArrayList<>();
    private List<String> linkList = new ArrayList<>();
    private int newNotifyBun = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_app_toolbar, container, false);
        Calligrapher calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(), "font/Quicksand-Regular.ttf", true);
        getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();

        new MyService(ToolbarLayoutFragment.this);

        prf = getActivity().getSharedPreferences("state", Context.MODE_PRIVATE);
        homeToolbar = (Toolbar) view.findViewById(R.id.main_app_toolbar);
        menu = (ImageView) homeToolbar.findViewById(R.id.main_app_toolbar_menu);
        notification = (ImageView) homeToolbar.findViewById(R.id.main_app_toolbar_notification);
        notifyIn = (FrameLayout) homeToolbar.findViewById(R.id.main_app_notify_coming);
        notifyCount = (TextView) homeToolbar.findViewById(R.id.main_app_notify_count);
        menuLayout = (LinearLayout) view.findViewById(R.id.home_menu_layout);
        notiLayout = (LinearLayout) view.findViewById(R.id.home_notify_layout);
        c = getActivity().getSharedPreferences("counter", Context.MODE_PRIVATE).getInt("c", 0);
        recyclerView = (RecyclerView) view.findViewById(R.id.notification_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

// This schedule a runnable task every 2 minutes
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                //doSomethingUseful();
                Api.getClient().getNotificationCount(getActivity().
                        getSharedPreferences("remember", Context.MODE_PRIVATE).getInt("id", 0)).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {


                                if (response.body().intValue() == 0) {
                                    if (c > 0) {
                                        notifyIn.setVisibility(View.VISIBLE);
                                        notifyCount.setText(c + "");
                                        Log.d("test_log", c + "");

                                        Api.getClient().getNotifications(getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE).getInt("id", 0)).enqueue(new Callback<List<NotificationsPOJO>>() {
                                            @Override
                                            public void onResponse(Call<List<NotificationsPOJO>> call, Response<List<NotificationsPOJO>> response) {
                                                if (response.isSuccessful()) {
                                                    if (response.body() != null) {
                                                        if (c == 0) {
                                                            Log.d("good", "empty one");
                                                            recyclerView.setVisibility(View.GONE);
                                                        } else {
                                                            titleList2.clear();
                                                            messageList2.clear();
                                                            linkList2.clear();
                                                            recyclerView.setVisibility(View.VISIBLE);
                                                            for (int i = 0; i < response.body().size(); i++) {
                                                                titleList2.add(response.body().get(i).getTitle());
                                                                Log.d("tt", response.body().get(i).getTitle());
                                                                messageList2.add(response.body().get(i).getMessage());
                                                                linkList2.add(response.body().get(i).getLink());
                                                            }

                                                            recyclerView.setVisibility(View.VISIBLE);
                                                            NotificationAdapter adapter = new NotificationAdapter(getActivity(), titleList2, messageList2,
                                                                    linkList2, c, getActivity());
                                                            recyclerView.setAdapter(adapter);
                                                            Log.d("good", "added " + c);

                                                            SharedPreferences pref = getActivity().getSharedPreferences("notify", Context.MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = pref.edit();
                                                            Set<String> titles = new HashSet<>();
                                                            titles.addAll(titleList2);
                                                            Set<String> links = new HashSet<>();
                                                            titles.addAll(linkList2);
                                                            editor.putStringSet("title", titles);
                                                            editor.putStringSet("link", links);
                                                            editor.commit();


                                                            Log.d("size1", titleList2.size() + "ss");
                                                            //notifyIn.setVisibility(View.VISIBLE);
                                                            //notifyCount.setText(val + "");


                                                        }
                                                    } else {
                                                        Log.d("bad", "BAAAD");
                                                        Toast.makeText(getActivity(), "Response Failure", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(getActivity(), "Empty Response", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<List<NotificationsPOJO>> call, Throwable t) {
                                                Toast.makeText(getActivity(), "Check your Internet Connection", Toast.LENGTH_LONG).show();
                                            }
                                        });


                                    } else {
                                        notifyIn.setVisibility(View.GONE);
                                    }
                                    Log.d("notifications", "No notifications");


                                } else {
                                    newNotifyBun = response.body().intValue();
                                    Log.d("notifications", "it work");
                                    notifyIn.setVisibility(View.VISIBLE);
                                    notifyCount.setText((newNotifyBun + c) + "");

                                    Api.getClient().getNotifications(getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE).getInt("id", 0))
                                            .enqueue(new Callback<List<NotificationsPOJO>>() {
                                                @Override
                                                public void onResponse(Call<List<NotificationsPOJO>> call, Response<List<NotificationsPOJO>> response) {

                                                    if (response.isSuccessful()) {
                                                        if (response.body() != null) {
                                                            for (int i = 0; i < getActivity().getSharedPreferences("test", Context.MODE_PRIVATE).getInt("b", 0); i++) {

                                                                Intent intent = new Intent(getActivity(), HomeActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
                                                                PendingIntent pIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), intent, 0);
                                                                final Notification n = new Notification.Builder(getActivity())
                                                                        .setContentTitle(response.body().get(response.body().size() - (i + 1)).getTitle())
                                                                        .setContentText(response.body().get(response.body().size() - (i + 1)).getMessage())
                                                                        .setStyle(new Notification.BigTextStyle().bigText(response.body().get(response.body().size() - (i + 1)).getMessage()))
                                                                        .setSmallIcon(R.drawable.logo)
                                                                        .setContentIntent(pIntent)
                                                                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                                                        .setAutoCancel(true)
                                                                        .build();


                                                                final NotificationManager notificationManager =
                                                                        (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                                                                notificationManager.notify(i, n);
                                                            }
                                                        } else {
                                                            Toast.makeText(getActivity(), "Response Failure", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(getActivity(), "Empty Response", Toast.LENGTH_SHORT).show();
                                                    }


                                                }

                                                @Override
                                                public void onFailure(Call<List<NotificationsPOJO>> call, Throwable t) {
                                                    Toast.makeText(getActivity(), "Check your Internet Connection", Toast.LENGTH_LONG).show();

                                                }
                                            });

                                }
                            } else {
                                Toast.makeText(getActivity(), "Response Failure", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Empty Response", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
//                        Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }, 0, 1, TimeUnit.MINUTES);

        userDataPreference = getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE);
        System.out.println("NAme-------------------------" + userDataPreference.getString("name", ""));
        //System.out.println("userNameBtn=========================" + userNameBtn);

        //   userNameBtn.setText(userDataPreference.getString("name", ""));


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("menu", false)) {
                    //menuLayout.setVisibility(View.GONE);
                    menuVisibility = false;
                    prf.edit().putBoolean("menu", false).commit();
                    getActivity().finish();
                    Log.d("finish", "test");
                } else {
                    if (getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("notify", false)) {
                        //notiLayout.setVisibility(View.GONE);
                        notiVisibility = false;
                        prf.edit().putBoolean("notify", false).commit();
                        getActivity().finish();
                    }

                    menuVisibility = true;
                    prf.edit().putBoolean("menu", true).commit();
                    //menuLayout.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getActivity(), MenuToolbarACtivity.class);
                    int toolbarHeight = homeToolbar.getHeight();
                    intent.putExtra("tool", toolbarHeight);
                    getActivity().startActivity(intent);
                }
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyIn.setVisibility(View.GONE);
                if (getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).getBoolean("notify", false)) {
                    //notiLayout.setVisibility(View.GONE);
                    notiVisibility = false;
                    prf.edit().putBoolean("notify", false).commit();
                    getActivity().finish();
                } else {
                    if (menuVisibility) {
                        //menuLayout.setVisibility(View.GONE);
                        menuVisibility = false;
                        prf.edit().putBoolean("menu", false).commit();
                        MenuToolbarACtivity.fa.finish();
                    }
                    //notiLayout.setVisibility(View.VISIBLE);
                    notiVisibility = true;
                    prf.edit().putBoolean("notify", true).commit();
                    Intent intent = new Intent(getActivity(), NotificationToolbarActivity.class);
                    int toolbarHeight = homeToolbar.getHeight();
                    intent.putExtra("tool", toolbarHeight);
                    getActivity().startActivity(intent);

                    if (c == 0) {
                        Api.getClient().getNotifications(userDataPreference.getInt("id", 0)).enqueue(new Callback<List<NotificationsPOJO>>() {
                            @Override
                            public void onResponse(Call<List<NotificationsPOJO>> call, Response<List<NotificationsPOJO>> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        if (c == 0) {
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
                                            NotificationAdapter adapter = new NotificationAdapter(getActivity(), titleList, messageList,
                                                    linkList, c, getActivity());
                                            recyclerView.setAdapter(adapter);
                                            Log.d("good", "addedssss    "+ c);

                                            SharedPreferences pref = getActivity().getSharedPreferences("notify", Context.MODE_PRIVATE);
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
                                        Toast.makeText(getActivity(), "Response Failure", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Empty Response", Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<List<NotificationsPOJO>> call, Throwable t) {
                                Toast.makeText(getActivity(), "Check your Internet Connection", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        getActivity().getSharedPreferences("counter", Context.MODE_PRIVATE).edit().putInt("c", 0).commit();
                    }

                    SharedPreferences data = getActivity().getSharedPreferences("notify", Context.MODE_PRIVATE);
//                    Set<String> title = data.getStringSet("title", null);
//                    Set<String> link = data.getStringSet("link", null);
//                    List<String> titleData = new ArrayList<>();
//                    List<String> listData = new ArrayList<>();
//                    titleData.addAll(title);
//                    listData.addAll(link);
                    // Log.d("size1", titleData.size() + "");

                }
                //Toast.makeText(getActivity(), "notifications", Toast.LENGTH_LONG).show();
            }
        });

        ((AppCompatActivity) getActivity()).setSupportActionBar(homeToolbar);

        View viewBack = homeToolbar.getChildAt(1);
        viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform actions
                getActivity().finish();
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });

        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        //textViewListener();

        return view;
    }


//    public void textViewListener() {
//
//        userNameBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
//                Intent moveToProfile = new Intent(getActivity(), UserProfileActivity.class);
//                getActivity().startActivity(moveToProfile);
//            }
//        });
//
//        feedbackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //mainLayout.setVisibility(View.GONE);
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
//                Intent moveToFeedback = new Intent(getActivity(), FeedbackActivity.class);
//                getActivity().startActivity(moveToFeedback);
//            }
//        });
//
//        joinOurTeamBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
//                Intent moveToJoinToOurTeam = new Intent(getActivity(), JoinOurTeamActivity.class);
//                getActivity().startActivity(moveToJoinToOurTeam);
//            }
//        });
//
//        aboutUsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
//                Intent moveToAboutUs = new Intent(getActivity(), AboutUsActivity.class);
//                getActivity().startActivity(moveToAboutUs);
//            }
//        });
//
//        signOutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
//                Toast.makeText(getActivity(), "out", Toast.LENGTH_SHORT).show();
//                SharedPreferences.Editor editor = getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE).edit();
//                editor.putBoolean("checked", false);
//                editor.commit();
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getActivity().startActivity(intent);
//                getActivity().finish();
//            }
//        });
//
//        contactUsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                menuLayout.setVisibility(View.GONE);
//                menuVisibility = false;
//                Intent moveToJoinToOurTeam = new Intent(getActivity(), ContactUsActivity.class);
//                getActivity().startActivity(moveToJoinToOurTeam);
//            }
//        });
//
//    }


    @Override
    public void onDestroy() {
        getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
        super.onDestroy();
        getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("menu", false).commit();
        getActivity().getSharedPreferences("state", Context.MODE_PRIVATE).edit().putBoolean("notify", false).commit();
    }

    @Override
    public void onResultReceived(String str) {
        notifyCount.setText(str);
    }
}
