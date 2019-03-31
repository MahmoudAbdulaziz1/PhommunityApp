package mahmoudvic.org.phomunity.pojo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

public class Margin implements LeadingMarginSpan.LeadingMarginSpan2 {
    private int margin;
    private int lines;

    public Margin(int lines, int margin) {
        this.margin = margin;
        this.lines = lines;
    }

    @Override
    public void drawLeadingMargin(Canvas arg0, Paint arg1, int arg2,
                                  int arg3, int arg4, int arg5, int arg6, CharSequence arg7,
                                  int arg8, int arg9, boolean arg10, Layout arg11) {
// TODO Auto-generated method stub
    }

    @Override
    public int getLeadingMargin(boolean arg0) {
// TODO Auto-generated method stub
        if (arg0) {
            /*
             * This indentation is applied to the number of rows returned
             * getLeadingMarginLineCount ()
             */
            return margin;
        } else {
// Offset for all other Layout layout ) { }
            /*
             * Returns * the number of rows which should be applied * indent
             * returned by getLeadingMargin (true) Note:* Indent only
             * applies to N lines of the first paragraph.
             */
            return 0;
        }
    }

    @Override
    public int getLeadingMarginLineCount() {
        // TODO Auto-generated method stub
        return lines;
    }

}
