package mahmoudvic.org.phomunity.util;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Util {
    public static final String STUDIO_RENT_PRICE_SUFFIX = " Per/Hour";

    public static void  setDialogSize(Context mContext, Dialog dialog){
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.90);
        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);
    }


}
