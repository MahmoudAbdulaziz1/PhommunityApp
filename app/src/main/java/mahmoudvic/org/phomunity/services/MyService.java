package mahmoudvic.org.phomunity.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.HomeActivity;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.NotificationsPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {




    public static Listener listener_obj;
    public MyService (Listener listener)
    {
        listener_obj=listener;
    }


    public MyService(){

    }


    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();
    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int Your_X_SECS = 150;

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
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);

        startTimer();

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");


    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        stoptimertask();
        super.onDestroy();


    }

    private void notifyMethod() {
        Api.getClient().getNotificationCount(
                getSharedPreferences("remember", Context.MODE_PRIVATE).getInt("id", 0)).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        if (response.body().intValue() == 0) {
                            Log.d("notifications", "No notifications");
                            //notifyIn.setVisibility(View.GONE);
                        } else {

                            final int newNotifyBun = response.body().intValue();
                            if (newNotifyBun>0){
                                getSharedPreferences("tt", MODE_PRIVATE).edit().putInt("t",newNotifyBun).commit();
                            }
                            if (newNotifyBun>0){
                                try {
                                    listener_obj.onResultReceived((newNotifyBun) + "");
                                }catch (Exception e){

                                }
                            }else {
                                Toast.makeText(getBaseContext(), "trst", Toast.LENGTH_LONG).show();
                            }
                            if (newNotifyBun >= getSharedPreferences("counter", MODE_PRIVATE).getInt("c", 0)) {

                                getSharedPreferences("counter", MODE_PRIVATE).edit().putInt("c", newNotifyBun).commit();
                                setBadge(getBaseContext(), newNotifyBun);

                            } else {
                                setBadge(getBaseContext(), getSharedPreferences("counter", MODE_PRIVATE).getInt("c", newNotifyBun));

                            }

                            Api.getClient().getNotifications(getBaseContext().getSharedPreferences("remember", Context.MODE_PRIVATE).getInt("id", 0))
                                    .enqueue(new Callback<List<NotificationsPOJO>>() {
                                        @Override
                                        public void onResponse(Call<List<NotificationsPOJO>> call, Response<List<NotificationsPOJO>> response) {

                                            if (response.isSuccessful()) {
                                                if (response.body() != null) {
                                                    for (int i = 0; i < newNotifyBun; i++) {

                                                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
                                                        PendingIntent pIntent = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);
                                                        final Notification n = new Notification.Builder(getBaseContext())
                                                                .setContentTitle(response.body().get(response.body().size() - (i + 1)).getTitle())
                                                                .setContentText(response.body().get(response.body().size() - (i + 1)).getMessage())
                                                                .setSmallIcon(R.drawable.logo)
                                                                .setContentIntent(pIntent)
                                                                .setAutoCancel(true)
                                                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                                                .build();


                                                        final NotificationManager notificationManager =
                                                                (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

                                                        notificationManager.notify(i, n);
                                                    }
                                                } else {
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

                    } else {
                        Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, Your_X_SECS * 1000); //
        //timer.schedule(timerTask, 5000,1000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        //TODO CALL NOTIFICATION FUNC
                        notifyMethod();
                        //Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_LONG).show();

                    }
                });
            }
        };
    }

}


