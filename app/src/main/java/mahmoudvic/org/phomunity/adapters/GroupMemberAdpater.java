package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mahmoudvic.org.phomunity.GroupMemberActivity;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.UserPOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;

public class GroupMemberAdpater extends RecyclerView.Adapter {

    private List<UserPOJO> userPOJOS;
    private Context mContext;


    public GroupMemberAdpater(Context mContext, List<UserPOJO> userPOJOS) {
        this.userPOJOS = userPOJOS;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_member_item, parent, false);
        return new GroupMemberAdpater.MyGroupMemberViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        final UserPOJO userPOJO = userPOJOS.get(position);

        ((MyGroupMemberViewHolder) holder).memberName.setText(userPOJO.getFirstname() + " " + userPOJO.getLastname());
        final Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Bold.ttf");
        ((MyGroupMemberViewHolder) holder).memberName.setTypeface(quickSand);
        String imageFileName = "";
        if (userPOJO.getShooterPhotoPOJO() != null) {
            imageFileName = userPOJO.getShooterPhotoPOJO().getImageFilename();
            if (!imageFileName.equals("")) {
                Glide.with(mContext)
                        .asBitmap()
                        .load(ImageUtil.BASE_IMAGE_URL+userPOJO.getShooterPhotoPOJO().getImageFilename())
                        .into(((MyGroupMemberViewHolder) holder).memberImage);
            }
        }



    }

    @Override
    public int getItemCount() {
        return userPOJOS.size();
    }


    public class MyGroupMemberViewHolder extends RecyclerView.ViewHolder {


        public TextView memberName;
        public ImageView memberImage;

        public MyGroupMemberViewHolder(View view) {
            super(view);
            memberName = view.findViewById(R.id.group_member_name);
            memberImage = view.findViewById(R.id.group_member_image);


        }


    }

}

