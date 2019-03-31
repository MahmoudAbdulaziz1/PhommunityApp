package mahmoudvic.org.phomunity.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.TipPOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;

public class TipAdapter extends RecyclerView.Adapter {

    private List<TipPOJO> tipPOJOS;
    private Context mContext;


    public TipAdapter(Context mContext, List<TipPOJO> tipPOJOS) {
        this.tipPOJOS = tipPOJOS;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == 1) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.first_index_image, parent, false);
            return new TipAdapter.MyImageHolder(itemView);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tip_item, parent, false);
            return new TipAdapter.MyTipsViewHolder(itemView);

        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof TipAdapter.MyTipsViewHolder) {
            final TipPOJO tipPOJO = tipPOJOS.get(position);

            ((MyTipsViewHolder) holder).tipTitle.setText(tipPOJO.getTitle());
            ((MyTipsViewHolder) holder).tipDetails.setText(tipPOJO.getDescription());

            String imageFileName = "";
            if (tipPOJO.getImagePOJOList().get(0) != null) {
                imageFileName = tipPOJO.getImagePOJOList().get(0).getImageFilename();
                if (!imageFileName.equals("")) {
                    Glide.with(mContext)
                            .asBitmap()
                            .load(ImageUtil.BASE_IMAGE_URL + tipPOJO.getImagePOJOList().get(0).getImageFilename())
                            .into(((MyTipsViewHolder) holder).tipImage);
                }
            }


            ((MyTipsViewHolder) holder).moreTips.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(mContext, tipPOJO.getTitle(), tipPOJO.getDescription(), tipPOJO.getImagePOJOList().get(0).getImageFilename());
                }
            });

        } else {
            ((MyImageHolder) holder).fisrtIndexImage.setImageResource(R.drawable.tips_header);
        }


    }

    @Override
    public int getItemCount() {
        return tipPOJOS.size();
    }


    public class MyTipsViewHolder extends RecyclerView.ViewHolder {


        public ImageView tipImage;
        public TextView tipTitle;
        public TextView tipDetails;
        public TextView moreTips;


        public MyTipsViewHolder(View view) {
            super(view);
            tipImage = (ImageView) view.findViewById(R.id.tip_image);
            tipTitle = (TextView) view.findViewById(R.id.tip_title);
            tipDetails = (TextView) view.findViewById(R.id.tip_detail);
            moreTips = (TextView) view.findViewById(R.id.more_tips);
            Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Bold.ttf");
            moreTips.setTypeface(quickSand);
            tipDetails.setTypeface(quickSand);
            tipTitle.setTypeface(quickSand);
        }


    }

    public class MyImageHolder extends RecyclerView.ViewHolder {
        public ImageView fisrtIndexImage;

        public MyImageHolder(View view) {
            super(view);
            fisrtIndexImage = (ImageView) view.findViewById(R.id.first_index);
        }
    }

    public void showDialog(Context mContext, String title, String description, String imageUrl) {
        final Dialog dialog = new Dialog(mContext);
        // Include dialog.xml file
        Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Bold.ttf");

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.tip_dialog_item);

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.90);
        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);

        // Set dialog title
        ImageView closeId = (ImageView) dialog.findViewById(R.id.tip_closeId);
        // set values for custom dialog components - text, image and button
        final TextView tipTitle = dialog.findViewById(R.id.tip_dialog_title);
        final TextView tipDetail = dialog.findViewById(R.id.tip_dialog_detail);
        final ImageView tipImage = dialog.findViewById(R.id.tip_dialog_image);
        tipDetail.setTypeface(quickSand);
        tipTitle.setTypeface(quickSand);
        dialog.show();

        tipTitle.setText(title);
        tipDetail.setText(description);
        Glide.with(mContext)
                .asBitmap()
                .load(ImageUtil.BASE_IMAGE_URL + imageUrl)
                .into(tipImage);

        // if decline button is clicked, close the custom dialog
        closeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}