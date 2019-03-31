package mahmoudvic.org.phomunity.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import mahmoudvic.org.phomunity.LabReservationOrderActivity;
import mahmoudvic.org.phomunity.LabsOptionsPOJO;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.Util;

public class LabRentAdapter extends RecyclerView.Adapter<LabRentAdapter.LabViewHolder> {

    private static final String TAG = "LabRentAdapter";
    private Context mContext;
    private List<LabsOptionsPOJO> options;

    public LabRentAdapter(Context mContext, List<LabsOptionsPOJO> options) {
        this.mContext = mContext;
        this.options = options;

    }

    @NonNull
    @Override
    public LabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rent_item, parent, false);
        return new LabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabViewHolder holder, final int position) {

        final LabsOptionsPOJO labOption = options.get(position);
        final int id = labOption.getId();
        final String imageUrl = labOption.getImages().get(0).getImageFilename();
        final String title = labOption.getTitle();
        final String price = labOption.getPrice() + Util.STUDIO_RENT_PRICE_SUFFIX;
        final String about = labOption.getAbout();
        final String contactNumber = labOption.getContactNumbers().get(0);

        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(ImageUtil.getImage(imageUrl))
                .into(holder.itemImage);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on an image: " + imageUrl);
                Toast.makeText(mContext, imageUrl, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemRentTitleTxt.setText(title);
        holder.itemRentPriceTxt.setText(price);
        holder.itemDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(mContext);
                Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Regular.ttf");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.activity_studio_details);
                Util.setDialogSize(mContext, dialog);
                ImageView closeBtn = dialog.findViewById(R.id.close_btn);
                TextView aboutTxt = (TextView) dialog.findViewById(R.id.itemText);
                ImageView itemImage = (ImageView) dialog.findViewById(R.id.itemImage);
                TextView itemRentTitleTxt = (TextView) dialog.findViewById(R.id.itemRentTitleTxt);
                TextView itemRentPriceTxt = (TextView) dialog.findViewById(R.id.itemRentPriceTxt);
                Button bookNowBtn = (Button) dialog.findViewById(R.id.book_now_btn);
                Button callBtn = (Button) dialog.findViewById(R.id.call_btn);
                aboutTxt.setTypeface(quickSand);
                itemRentTitleTxt.setTypeface(quickSand);
                itemRentPriceTxt.setTypeface(quickSand);
                bookNowBtn.setTypeface(quickSand);
                callBtn.setTypeface(quickSand);
                LinearLayout bookNowLayout = (LinearLayout) dialog.findViewById(R.id.book_layout);
                LinearLayout callLayout = (LinearLayout) dialog.findViewById(R.id.call_layout);

                aboutTxt.setText(about);
                itemRentTitleTxt.setText(title);
                itemRentPriceTxt.setText(price);
                Glide.with(mContext)
                        .asBitmap()
                        .load(ImageUtil.getImage(imageUrl))
                        .into(itemImage);
                dialog.show();


                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                bookNowBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleBookNow(id);

                    }
                });
                bookNowLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleBookNow(id);

                    }
                });
                callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleCall(contactNumber);
                    }
                });
                callLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleCall(contactNumber);
                    }
                });
            }
        });
    }

    private void handleBookNow(int id) {
        Intent intent = new Intent(mContext, LabReservationOrderActivity.class);
        intent.putExtra("id", id);
        mContext.startActivity(intent);
    }

    private void handleCall(String contactNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactNumber, null));
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class LabViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemRentTitleTxt;
        TextView itemRentPriceTxt;
        Button itemDetailsBtn;

        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public LabViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemRentTitleTxt = itemView.findViewById(R.id.itemRentTitleTxt);
            itemRentPriceTxt = itemView.findViewById(R.id.itemRentPriceTxt);
            itemDetailsBtn = itemView.findViewById(R.id.itemDetailsBtn);

            itemRentTitleTxt.setTypeface(quickSand);
            itemRentPriceTxt.setTypeface(quickSand);
            itemDetailsBtn.setTypeface(quickSand);

        }
    }
}
