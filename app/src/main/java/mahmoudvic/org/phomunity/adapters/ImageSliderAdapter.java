package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.ImagePOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;

public class ImageSliderAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ImageSliderAdapter";

    private List<ImagePOJO> imagePOJOS;
    private Context mContext;

    public ImageSliderAdapter(Context mContext, List<ImagePOJO> imagePOJOS) {
        Log.d(TAG, "imagePOJOS:" + imagePOJOS.size());
        this.mContext = mContext;
        this.imagePOJOS = imagePOJOS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_imageitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Log.d("images size", " " + imagePOJOS.size());
        if (imagePOJOS.size() == 1) {
            myViewHolder.parentRelativeLayout.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
        }
        Glide.with(mContext)
                .asBitmap()
                .load(ImageUtil.getImage(imagePOJOS.get(position).getImageFilename()))
                .into(myViewHolder.image);

        myViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on an image: " + imagePOJOS.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagePOJOS.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public RelativeLayout parentRelativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_view);
            parentRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.parent_relativeLayout);
        }
    }
}
