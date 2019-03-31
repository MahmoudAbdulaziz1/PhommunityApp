package mahmoudvic.org.phomunity.util;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import mahmoudvic.org.phomunity.R;

public class ToolbarUtil {

    public static void setFragment(Activity activity, Fragment fragment) {
        FragmentTransaction t = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
        t.replace(R.id.about_toolbar_container, fragment);
        t.commit();
    }

}
