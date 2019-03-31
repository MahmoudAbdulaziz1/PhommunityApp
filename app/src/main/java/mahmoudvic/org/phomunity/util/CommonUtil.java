package mahmoudvic.org.phomunity.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class CommonUtil {

    public void setFont(List<View> views, AssetManager asset) {
        if (!views.isEmpty()) {
            Typeface quickSand = Typeface.createFromAsset(asset, "font/Quicksand-Regular.ttf");
            for (View view : views) {
                if (view instanceof TextView)
                    ((TextView) view).setTypeface(quickSand);
                if (view instanceof EditText)
                    ((EditText) view).setTypeface(quickSand);
                if (view instanceof Button)
                    ((Button) view).setTypeface(quickSand);
                if (view instanceof CheckBox)
                    ((CheckBox) view).setTypeface(quickSand);
            }
        }
    }

    public static boolean isProbablyArabic(String s) {
        for (int i = 0; i < s.length(); ) {
            int c = s.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06E0)
                return true;
            i += Character.charCount(c);
        }
        return false;
    }
}
