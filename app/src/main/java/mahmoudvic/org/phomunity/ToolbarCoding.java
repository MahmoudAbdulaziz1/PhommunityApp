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

public class ToolbarCoding {

    private Toolbar homeToolbar;
    private ImageView menu;
    private ImageView notification;
    private Context context;
    private Activity activity;
    private LinearLayout mainLayout;
    private ToolbarNotificationCoding notificationItem;
    private ToolbarMenuCoding menuItem;
    private RelativeLayout r;

    public ToolbarCoding(final Context context, final Activity activity, String name) {
        this.context = context;
        this.activity = activity;
        mainLayout = (LinearLayout) activity.findViewById(R.id.home_toolbar_response);
        homeToolbar = (Toolbar) mainLayout.findViewById(R.id.home_toolbar);
        menu = (ImageView) homeToolbar.findViewById(R.id.home_toolbar_menu);
        notification = (ImageView) homeToolbar.findViewById(R.id.home_toolbar_notification);
        notificationItem = new ToolbarNotificationCoding(context, activity);
        menuItem = new ToolbarMenuCoding(context, activity, name);
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
