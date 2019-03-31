package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.ImagePOJO;
import mahmoudvic.org.phomunity.pojo.SamplesSecondShooterPOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;

public class ImageGalleryAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    List<SamplesSecondShooterPOJO> images;
    List<ImagePOJO> imagesPOJO;
    private boolean isImagePOJO = false;

    public ImageGalleryAdapter(Context context, List<SamplesSecondShooterPOJO> images) {
        super(context, R.layout.gallery_image, images);

        inflater = LayoutInflater.from(context);
        this.images = images;
        this.context = context;

    }

    public ImageGalleryAdapter(Context context, List<ImagePOJO> images, boolean isImagePOJO) {
        super(context, R.layout.gallery_image, images);

        inflater = LayoutInflater.from(context);
        this.imagesPOJO = images;
        this.context = context;
        this.isImagePOJO = isImagePOJO;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gallery_image, parent, false);
        }

        if(isImagePOJO){
            Glide
                    .with(context)
                    .load(ImageUtil.BASE_IMAGE_URL + imagesPOJO.get(position).getImageFilename())
                    .into((ImageView) convertView);
        }else {
            Glide
                    .with(context)
                    .load(ImageUtil.BASE_IMAGE_URL + images.get(position).getImageFileName())
                    .into((ImageView) convertView);
        }
        return convertView;

    }
}

