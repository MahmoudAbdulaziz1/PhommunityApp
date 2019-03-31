package mahmoudvic.org.phomunity.fonts;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFactory {
    private String A_BOLD = "font/Quicksand-Bold.ttf";
    private String A_LIGHT = "font/Quicksand-Regular.ttf";

    Typeface ambleBold;
    Typeface ambleLight;

    public TypeFactory(Context context) {
        ambleBold = Typeface.createFromAsset(context.getAssets(), A_BOLD);
        ambleLight = Typeface.createFromAsset(context.getAssets(), A_LIGHT);
    }

}
