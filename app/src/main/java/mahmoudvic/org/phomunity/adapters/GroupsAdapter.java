package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mahmoudvic.org.phomunity.PostActivity;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.GroupPOJO;

public class GroupsAdapter extends RecyclerView.Adapter {

    private List<GroupPOJO> groupPOJOS;
    private Context mContext;


    public GroupsAdapter(Context mContext, List<GroupPOJO> groupPOJOS) {
        this.groupPOJOS = groupPOJOS;
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
            return new GroupsAdapter.MyImageHolder(itemView);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.group_item, parent, false);
            return new GroupsAdapter.MyViewHolder(itemView);

        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (groupPOJOS.size() == 1) {
            Toast.makeText(mContext, "there's no groups for you", Toast.LENGTH_SHORT).show();
        }

        if (holder instanceof GroupsAdapter.MyViewHolder) {
            final GroupPOJO groupPOJO = groupPOJOS.get(position);

            ((GroupsAdapter.MyViewHolder) holder).groupName.setText(groupPOJO.getName());
            final Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Bold.ttf");
            ((GroupsAdapter.MyViewHolder) holder).groupName.setTypeface(quickSand);
            ((MyViewHolder) holder).groupLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, PostActivity.class);
                    intent.putExtra("groupId", groupPOJO.getId());
                    mContext.startActivity(intent);
                }
            });

        } else {
            ((GroupsAdapter.MyImageHolder) holder).fisrtIndexImage.setImageResource(R.drawable.group_header);
        }
    }

    @Override
    public int getItemCount() {
        return groupPOJOS.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView groupName;
        public LinearLayout groupLinearLayout;

        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public MyViewHolder(View view) {
            super(view);
            groupName = (TextView) view.findViewById(R.id.group_name);
            groupLinearLayout = view.findViewById(R.id.group_linear_layout);
            groupName.setTypeface(quickSand);


        }


    }

    public class MyImageHolder extends RecyclerView.ViewHolder {
        public ImageView fisrtIndexImage;

        public MyImageHolder(View view) {
            super(view);
            fisrtIndexImage = (ImageView) view.findViewById(R.id.first_index);
        }
    }


}
