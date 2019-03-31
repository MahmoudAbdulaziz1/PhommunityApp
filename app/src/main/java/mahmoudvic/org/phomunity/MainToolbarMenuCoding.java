package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainToolbarMenuCoding {

    private Context context;
    private Activity activity;
    private LinearLayout mainLayout;
    private TextView userNameBtn;
    private TextView feedbackBtn;
    private TextView joinOurTeamBtn;
    private TextView aboutUsBtn;
    private TextView contactUsBtn;
    private TextView signOutBtn;
    private boolean visibility = false;
    private SharedPreferences userDataPreference;

    public MainToolbarMenuCoding(Context context, Activity activity, String name) {
        this.context = context;
        this.activity = activity;
        mainLayout = (LinearLayout) activity.findViewById(R.id.main_menu_response);
        userNameBtn = (TextView) mainLayout.findViewById(R.id.home_menu_name);
        feedbackBtn = (TextView) mainLayout.findViewById(R.id.home_menu_feedback);
        joinOurTeamBtn = (TextView) mainLayout.findViewById(R.id.home_menu_join_team);
        aboutUsBtn = (TextView) mainLayout.findViewById(R.id.home_menu_about_us);
        contactUsBtn = (TextView) mainLayout.findViewById(R.id.home_menu_contact_us);
        signOutBtn = (TextView) mainLayout.findViewById(R.id.home_menu_sign_out);

        userDataPreference = context.getSharedPreferences("remember", Context.MODE_PRIVATE);

        userNameBtn.setText(name);
        textViewListener();

    }

    public void visibleLayout() {
//        mainLayout = (LinearLayout) activity.findViewById(R.id.home_notify_layout);
        mainLayout.setVisibility(View.VISIBLE);
    }

    public void goneLayout() {

        //mainLayout = (LinearLayout) activity.findViewById(R.id.home_notify_layout);
        mainLayout.setVisibility(View.GONE);
    }

    public boolean getVisibility() {
        Log.d("test", "visibility is ");
        if (mainLayout.getVisibility() == View.VISIBLE) {
            Log.d("test", "visibility is 3");
            visibility = true;

        } else {
            Log.d("test", "visibility is 4");

            visibility = false;
        }
        Log.d("test", "visibility is 5");

        return visibility;
    }


    public void textViewListener(){

        userNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneLayout();
                Intent moveToProfile = new Intent(context, UserProfileActivity.class);
                context.startActivity(moveToProfile);
            }
        });

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainLayout.setVisibility(View.GONE);
                goneLayout();
                Intent moveToFeedback = new Intent(context, FeedbackActivity.class);
                context.startActivity(moveToFeedback);
            }
        });

        joinOurTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goneLayout();
                Intent moveToJoinToOurTeam = new Intent(context, JoinOurTeamActivity.class);
                context.startActivity(moveToJoinToOurTeam);
            }
        });

        aboutUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneLayout();
                Intent moveToAboutUs = new Intent(context, AboutUsActivity.class);
                context.startActivity(moveToAboutUs);
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goneLayout();
                Toast.makeText(context, "out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = context.getSharedPreferences("remember", Context.MODE_PRIVATE).edit();
                editor.putBoolean("checked", false);
                editor.commit();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                activity.finish();
            }
        });

        contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goneLayout();
                Intent moveToJoinToOurTeam = new Intent(context, ContactUsActivity.class);
                context.startActivity(moveToJoinToOurTeam);
            }
        });

    }
}
