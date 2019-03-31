package mahmoudvic.org.phomunity.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.IrentDetailedItemActivity;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.ReservationOrderActivity;
import mahmoudvic.org.phomunity.pojo.SellImagesPOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;
import me.anwarshahriar.calligrapher.Calligrapher;

public class IrentAdapterr extends RecyclerView.Adapter {


    private Context context;
    //private ArrayList<Integer> cameraImageList;
    private int image;
    private ArrayList<Integer> id;
    private ArrayList<String> name;
    private ArrayList<String> desc;
    private ArrayList<Double> price;
    private ArrayList<List<String>> phone;
    private ArrayList<String> sign;
    private ArrayList<List<String>> branchs;
    private ArrayList<Integer> cat_id;
    private ArrayList<Integer> brand_id;
    private ArrayList<Integer> verified;
    private ArrayList<Integer> available;
    private ArrayList<String> cat_name;
    private ArrayList<String> brand_name;
    private ArrayList<String> video_url;
    private ArrayList<List<SellImagesPOJO>> images;
    private int flag;
    private Bundle branchBundle;
    private Activity activity;


    public IrentAdapterr(Context context, ArrayList id, ArrayList name, ArrayList desc, ArrayList price, ArrayList phone, ArrayList sign,
                         ArrayList branchs, ArrayList cat_id, ArrayList brand_id, ArrayList verified, ArrayList available,
                         ArrayList cat_name, ArrayList brand_name, ArrayList video_url, ArrayList images, int cameraImageList, int flag, Activity activity) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.phone = phone;
        this.sign = sign;
        this.branchs = branchs;
        this.cat_id = cat_id;
        this.brand_id = brand_id;
        this.verified = verified;
        this.available = available;
        this.cat_name = cat_name;
        this.brand_name = brand_name;
        this.video_url = video_url;
        this.image = cameraImageList;
        this.images = images;
        this.flag = flag;
        branchBundle = new Bundle();
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_irent_item, parent, false);
        Calligrapher calligrapher = new Calligrapher(context);
        calligrapher.setFont(activity, "font/Quicksand-Regular.ttf", true);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Calligrapher calligrapher = new Calligrapher(context);
        calligrapher.setFont(activity, "font/Quicksand-Regular.ttf", true);
//        if (flag == 1) {
//            ((MyViewHolder) holder).rentRequest.setText("Request");
//        } else {
            ((MyViewHolder) holder).rentRequest.setText("Request");
     //   }
        if (sign.get(position).trim().equals("") || sign.get(position).trim().equals(null)) {
            ((MyViewHolder) holder).signLayout.setVisibility(View.INVISIBLE);
        } else {
            String str = sign.get(position);
            String result = str.substring(str.lastIndexOf(',') + 1).trim();
            result  = result.substring(0, 1).toUpperCase() + result.substring(1);
            ((MyViewHolder) holder).sign.setText(result);
        }

        Log.d("size", images.size() + "");
        if (images != null) {
            try {
                if (!(images.get(position).get(0).getImage_filename().equals("") || images.get(position).get(0).getImage_filename().equals(null))) {
                    Log.d("picasso", ImageUtil.BASE_IMAGE_URL + images.get(position).get(0).getImage_filename() + "");
                    Picasso.with(context).
                            load(ImageUtil.BASE_IMAGE_URL + images.get(position).get(0).getImage_filename()).
                            into(((MyViewHolder) holder).cameraImage);
                } else {
                    ((MyViewHolder) holder).cameraImage.setImageResource(image);
                    Log.d("not", "not");
                }
            }catch (Exception e){

            }
        } else {
            ((MyViewHolder) holder).cameraImage.setImageResource(image);
            Log.d("not2", "not2");
        }
        ((MyViewHolder) holder).cameraNAme.setText(name.get(position));
        ((MyViewHolder) holder).rentPer24.setText(price.get(position) + "");
        ((MyViewHolder) holder).rentPer12.setText(price.get(position) / 2 + "");
        ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "this is all item", Toast.LENGTH_LONG).show();
                Bundle imageBundle = new Bundle();
                if (images != null) {
                    if (images.get(position).size() > 0)
                        imageBundle.putInt("size", images.get(position).size());
                    for (int i = 0; i < images.get(position).size(); i++) {

                        imageBundle.putString("image" + i, images.get(position).get(i).getImage_filename());

                        //bundle.putParcelableArrayList("branch", branchs.get(position));
                    }
                } else {
                    imageBundle.putInt("size", 0);
                }
                Bundle bundle = new Bundle();
                bundle.putInt("id", id.get(position));
                bundle.putString("desc", desc.get(position));
                Bundle phoneBundle = new Bundle();
                phoneBundle.putInt("size", phone.get(position).size());
                for (int i = 0; i < phone.get(position).size(); i++) {
                    phoneBundle.putString("phone" + i, phone.get(position).get(i));
                    Log.d("phonr tt", phone.get(position).get(i));
                    //bundle.putParcelableArrayList("branch", branchs.get(position));
                }


                branchBundle.putInt("size", branchs.get(position).size());
                for (int i = 0; i < branchs.get(position).size(); i++) {
                    branchBundle.putString("branch" + i, branchs.get(position).get(i));
                    //bundle.putParcelableArrayList("branch", branchs.get(position));
                }
                bundle.putInt("cat_id", cat_id.get(position));
                bundle.putInt("brand_id", brand_id.get(position));
                bundle.putInt("verified", verified.get(position));
                bundle.putInt("available", available.get(position));
                bundle.putString("cat_name", cat_name.get(position));
                bundle.putString("brand_name", brand_name.get(position));
                bundle.putString("video", video_url.get(position));
                bundle.putString("name", name.get(position));
                bundle.putDouble("price", price.get(position));
                Intent intent = new Intent(context, IrentDetailedItemActivity.class);
                intent.putExtra("phone", phoneBundle);
                intent.putExtra("branch", branchBundle);
                intent.putExtra("image", imageBundle);
                intent.putExtra("s", bundle);
                context.startActivity(intent);

            }
        });
        ((MyViewHolder) holder).rentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle branchBundle = new Bundle();
//                //branchBundle.putInt("size", branchs.get(position).size());
//                for (int i = 0; i < branchs.get(position).size(); i++) {
//                    branchBundle.putString("branch" + i, branchs.get(position).get(i));
//                    //bundle.putParcelableArrayList("branch", branchs.get(position));
//                }
                //Toast.makeText(context, "this is Rent Request button", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ReservationOrderActivity.class);
                intent.putExtra("id", id.get(position));
                intent.putExtra("branch", branchBundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        LinearLayout signLayout;
        TextView sign;
        ImageView cameraImage;
        TextView cameraNAme;
        TextView rentPer24;
        TextView rentPer12;
        Button rentRequest;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");


        public MyViewHolder(View itemView) {
            super(itemView);
            Calligrapher calligrapher = new Calligrapher(context);
            calligrapher.setFont(activity, "font/Quicksand-Regular.ttf", true);
            // get the reference of item view's
            signLayout = (LinearLayout) itemView.findViewById(R.id.sign_layout);
            sign = (TextView) itemView.findViewById(R.id.sign);
            cameraImage = (ImageView) itemView.findViewById(R.id.camera_image);
            cameraNAme = (TextView) itemView.findViewById(R.id.camera_name);
            rentPer24 = (TextView) itemView.findViewById(R.id.rent_per_24);
            rentPer12 = (TextView) itemView.findViewById(R.id.rent_per_12);
            rentRequest = (Button) itemView.findViewById(R.id.rent_request_button);

            sign.setTypeface(quickSand);
            cameraNAme.setTypeface(quickSand);
            rentPer24.setTypeface(quickSand);
            rentPer12.setTypeface(quickSand);
            rentRequest.setTypeface(quickSand);
        }
    }


}
