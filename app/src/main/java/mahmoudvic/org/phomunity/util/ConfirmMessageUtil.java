package mahmoudvic.org.phomunity.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import mahmoudvic.org.phomunity.R;

public class ConfirmMessageUtil {


    public static void setMessage(LinearLayout layouts, String message, Context context){
        Snackbar snackbar = Snackbar
                .make(layouts, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(context.getResources().getColor(R.color.colorPrimary));
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setPadding(0,0,0,0);
        (snackbar.getView()).getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        snackbar.show();
    }

}
