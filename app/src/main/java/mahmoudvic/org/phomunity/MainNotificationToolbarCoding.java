package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import mahmoudvic.org.phomunity.adapters.NotificationAdapter;

public class MainNotificationToolbarCoding {

    private ArrayList<String> newNotify = new ArrayList<String>(Arrays.asList("What is Question1?", "What is Question2?", "What is Question3?"));
    private ArrayList<String> earlierNotify = new ArrayList<String>(Arrays.asList("This is Answer1", "This is Answer2", "This is Answer3", "This is Answer4", "This is Answer5", "This is Answer6", "This is Answer7", "This is Answer8", "This is Answer9"));

    private LinearLayout mainLayout;
    private Context context;
    private Activity activity;
    private boolean visibility = false;
    private RecyclerView recyclerView;
    public MainNotificationToolbarCoding(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        mainLayout = (LinearLayout) activity.findViewById(R.id.main_notification_response);

        Thread thread = new Thread(new Runnable()
        {
            int lastMinute;
            int currentMinute;
            @Override
            public void run()
            {
                lastMinute = currentMinute;
                while (true)
                {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    currentMinute = calendar.get(Calendar.MINUTE);
                    if (currentMinute != lastMinute){
                        lastMinute = currentMinute;
                        Log.v("LOG", "your code here");
                    }
                }
            }
        });
        thread.run();


        recyclerView = (RecyclerView) mainLayout.findViewById(R.id.notification_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
//        NotificationAdapter adapter = new NotificationAdapter(context, newNotify, earlierNotify);
//        recyclerView.setAdapter(adapter);
    }

    public void visibleLayout() {
//        mainLayout = (LinearLayout) activity.findViewById(R.id.home_notify_layout);
        mainLayout.setVisibility(View.VISIBLE);
    }

    public void goneLayout() {
        //mainLayout = (LinearLayout) activity.findViewById(R.id.home_notify_layout);
        mainLayout.setVisibility(View.GONE);
    }

    public boolean getVisibility(){
        mainLayout = (LinearLayout) activity.findViewById(R.id.main_notification_response);
        Log.d("test", "visibility is ");
        Toast.makeText(context, "visibility is ", Toast.LENGTH_LONG).show();
        Log.d("test", "visibility is 2");
        //int v = mainLayout.getVisibility();
        if (mainLayout.getVisibility() == View.VISIBLE){
            Log.d("test", "visibility is 3");
            visibility = true;

        }else {
            Log.d("test", "visibility is 4");

            visibility = false;
        }
        Log.d("test", "visibility is 5");

        return visibility;
    }

}
