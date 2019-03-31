package mahmoudvic.org.phomunity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
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


public class HomeToolbarFragment extends Fragment implements Listener {

    List<String> titleList2 = new ArrayList<>();
    List<String> messageList2 = new ArrayList<>();
    List<String> linkList2 = new ArrayList<>();
    int c = 0;
    private Toolbar homeToolbar;
    private ImageView menu;
    private ImageView notification;
    private Context context;
    private RelativeLayout r;
    private TextView userNameBtn;
    private TextView feedbackBtn;
    private TextView joinOurTeamBtn;
    private TextView aboutUsBtn;
    private TextView contactUsBtn;
    private TextView signOutBtn;
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

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public void onResultReceived(String str) {
        notifyCount.setText(str);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_toolbar_layout, container, false);
        Calligrapher calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(), "font/Quicksand-Regular.ttf", true);

        new MyService(HomeToolbarFragment.this);
        homeToolbar = (Toolbar) view.findViewById(R.id.main_app_toolbar2);
        menu = (ImageView) homeToolbar.findViewById(R.id.main_app_toolbar_menu2);
        notification = (ImageView) homeToolbar.findViewById(R.id.main_app_toolbar_notification2);
        notifyIn = (FrameLayout) homeToolbar.findViewById(R.id.main_app_notify_coming2);
        notifyCount = (TextView) homeToolbar.findViewById(R.id.main_app_notify_count2);
        menuLayout = (LinearLayout) view.findViewById(R.id.home_menu_layout2);
        notiLayout = (LinearLayout) view.findViewById(R.id.home_notify_layout2);
        c = getActivity().getSharedPreferences("counter", Context.MODE_PRIVATE).getInt("c", 0);
        Drawable logo = getActivity().getResources().getDrawable(R.drawable.logo);
        homeToolbar.setLogo(logo);
        for (int i = 0; i < homeToolbar.getChildCount(); i++) {
            View child = homeToolbar.getChildAt(i);
            if (child != null)
                if (child.getClass() == AppCompatImageView.class) {
                    AppCompatImageView iv2 = (AppCompatImageView) child;
                    //if ( iv2.getDrawable() == logo ) {
                    iv2.setAdjustViewBounds(true);
                    iv2.getLayoutParams().height = iv2.getMeasuredHeight() - 20;
                    iv2.requestLayout();
                    //iv2.setAdjustViewBounds(true);
                    //iv2.set(5, 45, 5, 45);
                    //setMargins(iv2,5,40,5,40);
                    Log.d("test icon", iv2.getMeasuredHeight() + "  pleasssssssse");
                }
            Log.d("test icon",
                    "pleasssssssse2" + child.getClass());

        }
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
//                                getActivity().getSharedPreferences("test", Context.MODE_PRIVATE).edit().putInt("b", response.body().intValue()).commit();
//                                if (getActivity().getSharedPreferences("test", Context.MODE_PRIVATE).getInt("b", 0) == 0) {
                                if (response.body().intValue() == 0) {
                                    if (c > 0) {
                                        notifyIn.setVisibility(View.VISIBLE);
                                        notifyCount.setText(c + "");


                                        Api.getClient().getNotifications(getActivity().getSharedPreferences(
                                                "remember", Context.MODE_PRIVATE).getInt("id", 0)).enqueue(new Callback<List<NotificationsPOJO>>() {
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
                                                            Log.d("good", "added");

                                                            SharedPreferences pref = getActivity().
                                                                    getSharedPreferences("notify", Context.MODE_PRIVATE);
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
                                    //notifyIn.setVisibility(View.GONE);
                                } else {
                                    newNotifyBun = response.body().intValue();
                                    Log.d("notifications", "it work" + newNotifyBun);
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
                        try {
                            Toast.makeText(getActivity(), "Check your Internet Connection", Toast.LENGTH_LONG).show();
                        } catch (NullPointerException e) {
//                            Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        }, 0, 1, TimeUnit.MINUTES);

        userNameBtn = (TextView) view.findViewById(R.id.home_menu_name2);
        feedbackBtn = (TextView) view.findViewById(R.id.home_menu_feedback2);
        joinOurTeamBtn = (TextView) view.findViewById(R.id.home_menu_join_team2);
        aboutUsBtn = (TextView) view.findViewById(R.id.home_menu_about_us2);
        contactUsBtn = (TextView) view.findViewById(R.id.home_menu_contact_us2);
        signOutBtn = (TextView) view.findViewById(R.id.home_menu_sign_out2);
        final Typeface quickSand = Typeface.createFromAsset(getActivity().getAssets(), "font/Quicksand-Bold.ttf");
        userNameBtn.setTypeface(quickSand, Typeface.BOLD);
        feedbackBtn.setTypeface(quickSand, Typeface.BOLD);
        joinOurTeamBtn.setTypeface(quickSand, Typeface.BOLD);
        aboutUsBtn.setTypeface(quickSand, Typeface.BOLD);
        contactUsBtn.setTypeface(quickSand, Typeface.BOLD);
        signOutBtn.setTypeface(quickSand, Typeface.BOLD);
        userDataPreference = getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE);

        userNameBtn.setText(getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE).getString("name", ""));


        recyclerView = (RecyclerView) view.findViewById(R.id.home_notification_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuVisibility) {
                    menuLayout.setVisibility(View.GONE);
                    menuVisibility = false;
                } else {
                    if (notiVisibility) {
                        notiLayout.setVisibility(View.GONE);
                        notiVisibility = false;
                    }
                    menuLayout.setVisibility(View.VISIBLE);
                    menuVisibility = true;
                }
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyIn.setVisibility(View.GONE);
                if (notiVisibility) {
                    notiLayout.setVisibility(View.GONE);
                    notiVisibility = false;
                } else {
                    if (menuVisibility) {
                        menuLayout.setVisibility(View.GONE);
                        menuVisibility = false;
                    }
                    notiLayout.setVisibility(View.VISIBLE);
                    notiVisibility = true;
                    if (c == 0) {

                        Api.getClient().getNotifications(userDataPreference.getInt("id", 0)).enqueue(new Callback<List<NotificationsPOJO>>() {
                            @Override
                            public void onResponse(Call<List<NotificationsPOJO>> call, Response<List<NotificationsPOJO>> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        if (response.body().size() == 0) {
                                            Log.d("good", "empty one");
                                        } else {
                                            titleList.clear();
                                            messageList.clear();
                                            linkList.clear();
                                            for (int i = 0; i < response.body().size(); i++) {
                                                recyclerView.setVisibility(View.GONE);
                                                titleList.add(response.body().get(i).getTitle());
                                                messageList.add(response.body().get(i).getMessage());
                                                linkList.add(response.body().get(i).getLink());
                                            }
                                            recyclerView.setVisibility(View.VISIBLE);
                                            NotificationAdapter adapter = new NotificationAdapter(getActivity(), titleList, messageList,
                                                    linkList, getActivity().getSharedPreferences("test", Context.MODE_PRIVATE).getInt("b", 0), getActivity());
                                            recyclerView.setAdapter(adapter);
                                            getActivity().getSharedPreferences("test", Context.MODE_PRIVATE).edit().putInt("b", 0).commit();

                                            Log.d("good", response.body().get(0).getTitle());
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

                }
                //Toast.makeText(getActivity(), "notifications", Toast.LENGTH_LONG).show();
            }
        });

        ((AppCompatActivity) getActivity()).setSupportActionBar(homeToolbar);

//        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
//            Drawable drawable = getResources().getDrawable(R.drawable.logo);
//            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//            Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
////            newdrawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(newdrawable);
//        }


//        View viewBack = homeToolbar.getChildAt(0);
//        viewBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Perform actions
//                getActivity().finish();
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                getActivity().startActivity(intent);
//            }
//        });

        textViewListener();

        return view;
    }


    public void textViewListener() {


        userNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayout.setVisibility(View.GONE);
                menuVisibility = false;
                Intent moveToProfile = new Intent(getActivity(), UserProfileActivity.class);
                getActivity().startActivity(moveToProfile);
            }
        });

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainLayout.setVisibility(View.GONE);
                menuLayout.setVisibility(View.GONE);
                menuVisibility = false;
                Intent moveToFeedback = new Intent(getActivity(), FeedbackActivity.class);
                getActivity().startActivity(moveToFeedback);
            }
        });

        joinOurTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuLayout.setVisibility(View.GONE);
                menuVisibility = false;
                Intent moveToJoinToOurTeam = new Intent(getActivity(), JoinOurTeamActivity.class);
                getActivity().startActivity(moveToJoinToOurTeam);
            }
        });

        aboutUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayout.setVisibility(View.GONE);
                menuVisibility = false;
                Intent moveToAboutUs = new Intent(getActivity(), AboutUsActivity.class);
                getActivity().startActivity(moveToAboutUs);
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayout.setVisibility(View.GONE);
                menuVisibility = false;
//                Toast.makeText(getActivity(), "out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE).edit();
                editor.putBoolean("checked", false);
                editor.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuLayout.setVisibility(View.GONE);
                menuVisibility = false;
                Intent moveToJoinToOurTeam = new Intent(getActivity(), ContactUsActivity.class);
                getActivity().startActivity(moveToJoinToOurTeam);
            }
        });

    }


}
