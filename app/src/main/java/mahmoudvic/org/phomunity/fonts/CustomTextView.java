package mahmoudvic.org.phomunity.fonts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import mahmoudvic.org.phomunity.R;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    private int typefaceType;
    private TypeFactory mFontFactory;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context) {
        super(context);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {


        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomTextView,
                0, 0);
        try {
            typefaceType = array.getInteger(R.styleable.CustomTextView_font_name, 0);
        } finally {
            array.recycle();
        }
        if (!isInEditMode()) {
            setTypeface(getTypeFace(typefaceType));
        }

    }

    public Typeface getTypeFace(int type) {
        if (mFontFactory == null)
            mFontFactory = new TypeFactory(getContext());

        switch (type) {
            case Constants.A_BOLD:
                return mFontFactory.ambleBold;

            case Constants.A_LIGHT:
                return mFontFactory.ambleLight;


            default:
                return mFontFactory.ambleBold;
        }
    }

    public interface Constants {
        int A_BOLD = 1, A_LIGHT = 2;

    }
}