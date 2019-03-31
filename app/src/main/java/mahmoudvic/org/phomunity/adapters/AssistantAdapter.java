package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import mahmoudvic.org.phomunity.util.FontUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.ShooterUtil;

import static android.content.Context.MODE_PRIVATE;

public class AssistantAdapter extends RecyclerView.Adapter {

    private List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOsList;
    private Context mContext;


    public AssistantAdapter(Context mContext, List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOsList) {
        this.assistantSecondShooterPOJOsList = assistantSecondShooterPOJOsList;
        this.mContext = mContext;
        FontUtil.setFont(mContext);

        if (this.assistantSecondShooterPOJOsList.isEmpty()) {
            Toast.makeText(mContext, "there's no data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_assistant_item, parent, false);
        return new AssistantAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof AssistantAdapter.MyViewHolder) {
            final AssistantSecondShooterPOJO assistantSecondShooterPOJO = assistantSecondShooterPOJOsList.get(position);

            ((AssistantAdapter.MyViewHolder) holder).nameTextView.setText(assistantSecondShooterPOJO.getUserPOJO().getFirstname() + " " + assistantSecondShooterPOJO.getUserPOJO().getLastname());
            ((AssistantAdapter.MyViewHolder) holder).gradeTextView.setText(assistantSecondShooterPOJO.getArea());
            ((AssistantAdapter.MyViewHolder) holder).rateBar.setRating(assistantSecondShooterPOJO.getRating());
            List<OrderPOJO> orderPOJOS = assistantSecondShooterPOJO.getOrderPOJOS();
            int orderId = 0;
            String status = "";
            final int userId = mContext.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);

            if (!orderPOJOS.isEmpty()) {
                for (OrderPOJO orderPOJO : orderPOJOS) {
                    if (orderPOJO.getFrontEndUser() == userId) {
                        status = orderPOJO.getStatus();
                        orderId = orderPOJO.getId();
                        ((MyViewHolder) holder).requestAssistantTextview.setText(status);
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
                            .into(((AssistantAdapter.MyViewHolder) holder).photoImageView);
                }
            }
            final int finalOrderId = orderId;
            ((AssistantAdapter.MyViewHolder) holder).requestLinearLayoutId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShooterUtil shooterUtil = new ShooterUtil();
                    String status = ((MyViewHolder) holder).requestAssistantTextview.getText().toString();
                    if (status.equals(mContext.getResources().getString(R.string.request))) {
                        shooterUtil.handleDefaultRequest(holder, mContext, true);
                    } else {
                        shooterUtil.handleOtherRequest(status, finalOrderId, mContext, userId);
                    }

                }
            });


            ((MyViewHolder) holder).aboutAssistantTextview.setText(assistantSecondShooterPOJO.getAbout());
            ((MyViewHolder) holder).reviewsAssistantTextview.setText(assistantSecondShooterPOJO.getArea());

            ((MyViewHolder) holder).aboutAssistantTextview.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).reviewsAssistantTextview.setVisibility(View.INVISIBLE);
            ((MyViewHolder) holder).aboutAssistantChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MyViewHolder) holder).reviewsAssistantChange.setBackgroundResource(R.drawable.tab_bg);
                    ((MyViewHolder) holder).aboutAssistantChange.setBackgroundResource(R.drawable.tab_bg_selected);
                    ((MyViewHolder) holder).aboutAssistantTextview.setVisibility(View.VISIBLE);
                    ((MyViewHolder) holder).reviewsAssistantTextview.setVisibility(View.INVISIBLE);
                }
            });

            ((MyViewHolder) holder).reviewsAssistantChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MyViewHolder) holder).reviewsAssistantChange.setBackgroundResource(R.drawable.tab_bg_selected);
                    ((MyViewHolder) holder).aboutAssistantChange.setBackgroundResource(R.drawable.tab_bg);
                    ((MyViewHolder) holder).aboutAssistantTextview.setVisibility(View.INVISIBLE);
                    ((MyViewHolder) holder).reviewsAssistantTextview.setVisibility(View.VISIBLE);
                }
            });
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
        public LinearLayout requestLinearLayoutId;
        public Button aboutAssistantChange;
        public Button reviewsAssistantChange;
        public TextView aboutAssistantTextview;
        public TextView reviewsAssistantTextview;
        public TextView requestAssistantTextview;

        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Bold.ttf");

        public MyViewHolder(View view) {
            super(view);
            photoImageView = (ImageView) view.findViewById(R.id.secondShooterImage);
            nameTextView = (TextView) view.findViewById(R.id.secondShooterName);
            gradeTextView = (TextView) view.findViewById(R.id.secondShooterProfession);
            rateBar = (RatingBar) view.findViewById(R.id.secondShooterRatingBar);
            aboutAssistantTextview = (TextView) view.findViewById(R.id.about_assistant_textview);
            reviewsAssistantTextview = (TextView) view.findViewById(R.id.reviews_assistant_textview);
            aboutAssistantChange = view.findViewById(R.id.about_assistant_change);
            reviewsAssistantChange = view.findViewById(R.id.reviews_assistant_change);
            requestLinearLayoutId = (LinearLayout) view.findViewById(R.id.requesAssiatnttLinearLayoutId);
            requestAssistantTextview = view.findViewById(R.id.register_assistant_textview);

            nameTextView.setTypeface(quickSand);
            gradeTextView.setTypeface(quickSand);
            aboutAssistantTextview.setTypeface(quickSand);
            reviewsAssistantTextview.setTypeface(quickSand);
            requestAssistantTextview.setTypeface(quickSand);
            aboutAssistantChange.setTypeface(quickSand);
            reviewsAssistantChange.setTypeface(quickSand);
        }


    }


}
