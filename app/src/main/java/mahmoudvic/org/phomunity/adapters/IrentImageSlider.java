package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.util.ImageUtil;

public class IrentImageSlider extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<String> images;

    public IrentImageSlider(Context context, ArrayList images){
        this.context = context;
        this.images = images;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.irent_detailed_item_slider, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(images!=null){
            if (images.get(position).equals(null)|| images.get(position).equals("")){
                ((MyViewHolder)holder).cameraImage.setImageResource(R.drawable.camera_test);
            }else {
                Log.d("images", images.get(position)+"");
                Picasso.with(context).
                        load(ImageUtil.BASE_IMAGE_URL + images.get(position)).
                        into(((MyViewHolder) holder).cameraImage);
            }
        }

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ImageView cameraImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            cameraImage = (ImageView) itemView.findViewById(R.id.slider_image);
        }
    }
}
