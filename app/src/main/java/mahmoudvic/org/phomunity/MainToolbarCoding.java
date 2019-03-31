package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainToolbarCoding {


    private Toolbar homeToolbar;
    private ImageView menu;
    private ImageView notification;
    private Context context;
    private Activity activity;
    private LinearLayout mainLayout;
    private MainNotificationToolbarCoding notificationItem;
    private MainToolbarMenuCoding menuItem;
    private RelativeLayout r;

    public MainToolbarCoding(final Context context, final Activity activity, String name) {
        this.context = context;
        this.activity = activity;
        mainLayout = (LinearLayout) activity.findViewById(R.id.main_app_toolbar_response);
        homeToolbar = (Toolbar) mainLayout.findViewById(R.id.main_app_toolbar);
        menu = (ImageView) homeToolbar.findViewById(R.id.main_app_toolbar_menu);
        notification = (ImageView) homeToolbar.findViewById(R.id.main_app_toolbar_notification);
        notificationItem = new MainNotificationToolbarCoding(context, activity);
        menuItem = new MainToolbarMenuCoding(context, activity, name);
        onNotificationClick();
        onMenuClick();
        ((AppCompatActivity) activity).setSupportActionBar(homeToolbar);

        // Find logo
        View view = homeToolbar.getChildAt(0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform actions
                Intent intent = new Intent(context, HomeActivity.class);
                activity.startActivity(intent);
            }
        });


    }

    public void onNotificationClick() {
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notificationItem.getVisibility()) {
                    notificationItem.goneLayout();

                    //welcome.setVisibility(View.VISIBLE);
                    //user_name.setVisibility(View.VISIBLE);
                } else {
                    notificationItem.visibleLayout();
                    menuItem.goneLayout();
                    //welcome.setVisibility(View.GONE);
                    //user_name.setVisibility(View.GONE);
                }
            }
        });

    }

    public void onMenuClick() {
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuItem.getVisibility()) {
//                    frameLayout.setVisibility(View.GONE);
////                    relativeLayout.setVerticalGravity(View.VISIBLE);

//                    layoutVisible = false;


                    menuItem.goneLayout();
                    //welcome.setVisibility(View.VISIBLE);
                    //user_name.setVisibility(View.VISIBLE);
                } else {
                    menuItem.visibleLayout();
                    notificationItem.goneLayout();
                    //frameLayout.setVisibility(View.VISIBLE);
                    //welcome.setVisibility(View.GONE);
                    //user_name.setVisibility(View.GONE);
                    //relativeLayout.setVerticalGravity(View.GONE);
                    //layoutVisible = true;
                }
            }
        });
    }

}
