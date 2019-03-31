package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.AssistantSecondShooterPOJO;
import mahmoudvic.org.phomunity.pojo.OrderPOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.ShooterUtil;

import static android.content.Context.MODE_PRIVATE;

public class SecondShooterAdapter extends RecyclerView.Adapter {

    private List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOsList;
    private Context mContext;


    public SecondShooterAdapter(Context mContext, List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOsList) {
        this.assistantSecondShooterPOJOsList = assistantSecondShooterPOJOsList;
        this.mContext = mContext;
        if (this.assistantSecondShooterPOJOsList.isEmpty()) {
            Toast.makeText(mContext, "there's no data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_looking_second_shooter, parent, false);
        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof MyViewHolder) {

            AssistantSecondShooterPOJO assistantSecondShooterPOJO = assistantSecondShooterPOJOsList.get(position);
            ((MyViewHolder) holder).nameTextView.setText(assistantSecondShooterPOJO.getUserPOJO().getFirstname() + " " + assistantSecondShooterPOJO.getUserPOJO().getLastname());
            ((MyViewHolder) holder).gradeTextView.setText(assistantSecondShooterPOJO.getArea());
            ((MyViewHolder) holder).rateBar.setRating(assistantSecondShooterPOJO.getRating());
            List<OrderPOJO> orderPOJOS = assistantSecondShooterPOJO.getOrderPOJOS();

            int orderId = 0;
            String status = "";
            final int userId = mContext.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);

            if (!orderPOJOS.isEmpty()) {
                for (OrderPOJO orderPOJO : orderPOJOS) {
                    if (orderPOJO.getFrontEndUser() == userId) {
                        status = orderPOJO.getStatus();
                        orderId = orderPOJO.getId();
                        ((MyViewHolder) holder).requestSecondShooterTextview.setText(status);
                        ((MyViewHolder) holder).requestLinearLayoutId.setClickable(false);
                        break;
                    }
                }
            }

            String imageFileName = "";
            if (assistantSecondShooterPOJO.getUserPOJO().getShooterPhotoPOJO() != null) {
                imageFileName = assistantSecondShooterPOJO.getUserPOJO().getShooterPhotoPOJO().getImageFilename();
                if (!imageFileName.equals("")) {
                    Glide.with(mContext)
                            .asBitmap()
                            .load(ImageUtil.BASE_IMAGE_URL + imageFileName)
                            .into(((MyViewHolder) holder).photoImageView);
                }
            }

            final int finalOrderId = orderId;
            if (assistantSecondShooterPOJO.getFrontEndUserId() == userId) {
                ((MyViewHolder) holder).requestLinearLayoutId.setClickable(false);
            } else {
                ((SecondShooterAdapter.MyViewHolder) holder).requestLinearLayoutId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShooterUtil shooterUtil = new ShooterUtil();
                        String status = ((MyViewHolder) holder).requestSecondShooterTextview.getText().toString();
                        if (status.equals(mContext.getResources().getString(R.string.request))) {
                            shooterUtil.handleDefaultRequest(holder, mContext, false);
                        } else {
                            shooterUtil.handleOtherRequest(status, finalOrderId, mContext, userId);
                        }

                    }
                });
            }
            ((MyViewHolder) holder).aboutSecondShooterTextview.setText(assistantSecondShooterPOJO.getAbout());
            ((MyViewHolder) holder).reviewsSecondShooterTextview.setText(assistantSecondShooterPOJO.getArea());

            ((MyViewHolder) holder).aboutSecondShooterTextview.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).reviewsSecondShooterTextview.setVisibility(View.INVISIBLE);
            ((MyViewHolder) holder).aboutSecondShooterChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MyViewHolder) holder).reviewsSecondShooterChange.setBackgroundResource(R.drawable.tab_bg);
                    ((MyViewHolder) holder).aboutSecondShooterChange.setBackgroundResource(R.drawable.tab_bg_selected);
                    ((MyViewHolder) holder).aboutSecondShooterTextview.setVisibility(View.VISIBLE);
                    ((MyViewHolder) holder).reviewsSecondShooterTextview.setVisibility(View.INVISIBLE);
                }
            });

            ((MyViewHolder) holder).reviewsSecondShooterChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MyViewHolder) holder).reviewsSecondShooterChange.setBackgroundResource(R.drawable.tab_bg_selected);
                    ((MyViewHolder) holder).aboutSecondShooterChange.setBackgroundResource(R.drawable.tab_bg);
                    ((MyViewHolder) holder).aboutSecondShooterTextview.setVisibility(View.INVISIBLE);
                    ((MyViewHolder) holder).reviewsSecondShooterTextview.setVisibility(View.VISIBLE);
                }
            });
            ((MyViewHolder) holder).SecondShooter_gallery.setAdapter(new ImageGalleryAdapter(mContext, assistantSecondShooterPOJO.getSamplesSecondShooterPOJOS()));
        }
    }

    @Override
    public int getItemCount() {
        return assistantSecondShooterPOJOsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageView photoImageView;
        public TextView nameTextView;
        public TextView gradeTextView;
        public RatingBar rateBar;
        //        public RecyclerView simpleWorkSecondShooterRecyclerView;
        public LinearLayout requestLinearLayoutId;
        public Button aboutSecondShooterChange;
        public Button reviewsSecondShooterChange;
        public TextView aboutSecondShooterTextview;
        public TextView reviewsSecondShooterTextview;
        public TextView requestSecondShooterTextview;
        public Gallery SecondShooter_gallery;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Bold.ttf");


        public MyViewHolder(View view) {
            super(view);
            photoImageView = (ImageView) view.findViewById(R.id.secondShooterImage);
            nameTextView = (TextView) view.findViewById(R.id.secondShooterName);
            gradeTextView = (TextView) view.findViewById(R.id.secondShooterProfession);
            rateBar = (RatingBar) view.findViewById(R.id.secondShooterRatingBar);
//            simpleWorkSecondShooterRecyclerView = (RecyclerView) view.findViewById(R.id.simpleWorkSecondShooterRecyclerView);
            requestLinearLayoutId = (LinearLayout) view.findViewById(R.id.requestSecondShooterLinearLayoutId);
            aboutSecondShooterTextview = (TextView) view.findViewById(R.id.about_textview);
            reviewsSecondShooterTextview = (TextView) view.findViewById(R.id.reviews_textview);
            aboutSecondShooterChange = view.findViewById(R.id.aboutChange);
            reviewsSecondShooterChange = view.findViewById(R.id.reviewsChange);
            SecondShooter_gallery = view.findViewById(R.id.secondShooter_gallery);
            requestSecondShooterTextview = view.findViewById(R.id.register_secondshooter_textview);


            nameTextView.setTypeface(quickSand);
            gradeTextView.setTypeface(quickSand);
            aboutSecondShooterChange.setTypeface(quickSand);
            reviewsSecondShooterChange.setTypeface(quickSand);
            aboutSecondShooterTextview.setTypeface(quickSand);
            reviewsSecondShooterTextview.setTypeface(quickSand);
            requestSecondShooterTextview.setTypeface(quickSand);
        }
    }


}