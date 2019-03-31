package mahmoudvic.org.phomunity.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageUtil {

    public  static  final String BASE_IMAGE_URL="http://phommunity.com/uploads/images/";

    public static  String getImage(String imageOriginalFileName){
        return ImageUtil.BASE_IMAGE_URL.concat(imageOriginalFileName);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        return Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
    }
}
