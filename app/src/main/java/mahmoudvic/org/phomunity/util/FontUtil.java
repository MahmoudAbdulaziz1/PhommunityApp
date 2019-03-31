package mahmoudvic.org.phomunity.util;

import android.app.Activity;
import android.content.Context;

import me.anwarshahriar.calligrapher.Calligrapher;

public class FontUtil {

    // static variable single_instance of type Singleton
    private static FontUtil fontUtil = null;

    // variable of type String
    public String s;

    // private constructor restricted to this class itself
    private FontUtil(Context mContext) {
        Calligrapher calligrapher = new Calligrapher(mContext);
        calligrapher.setFont((Activity) mContext, "font/Quicksand-Regular.ttf", true);

    }

    // static method to create instance of Singleton class
    public static FontUtil setFont(Context mcontext) {
        if (fontUtil == null)
            fontUtil = new FontUtil(mcontext);

        return fontUtil;
    }
}
