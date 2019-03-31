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

import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.CommentPOJO;
import mahmoudvic.org.phomunity.util.DateUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.PostUtil;

public class PostDetailAdapter extends RecyclerView.Adapter {


    private List<CommentPOJO> commentPOJOS;
    private Context mContext;


    public PostDetailAdapter(Context mContext, List<CommentPOJO> commentPOJOS) {
        this.mContext = mContext;
        this.commentPOJOS = commentPOJOS;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new PostDetailAdapter.MyCommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        final CommentPOJO commentPOJO = commentPOJOS.get(position);

        String imageFileName = "";
        if (commentPOJO.getUserPOJO() != null) {

            if (commentPOJO.getUserPOJO().getShooterPhotoPOJO() != null) {
                imageFileName = commentPOJO.getUserPOJO().getShooterPhotoPOJO().getImageFilename();
                if (!imageFileName.equals("")) {
                    Glide.with(mContext)
                            .asBitmap()
                            .load(ImageUtil.BASE_IMAGE_URL + commentPOJO.getUserPOJO().getShooterPhotoPOJO().getImageFilename())
                            .into(((MyCommentViewHolder) holder).userCommentImage);
                }
            }

        }
        PostUtil postUtil = new PostUtil();
        ((MyCommentViewHolder) holder).userCommentName.setText(commentPOJO.getUserPOJO().getFirstname() + " " + commentPOJO.getUserPOJO().getLastname());
        ((MyCommentViewHolder) holder).postCommetDate.setText(DateUtil.getPostDate(DateUtil.convertToEntityAttribute(commentPOJO.getCreatedAt())));
        ((MyCommentViewHolder) holder).postCommentText.setText(commentPOJO.getBody().toString());

        final Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Bold.ttf");
        ((PostDetailAdapter.MyCommentViewHolder) holder).userCommentName.setTypeface(quickSand);
        ((PostDetailAdapter.MyCommentViewHolder) holder).postCommentText.setTypeface(quickSand);

    }

    @Override
    public int getItemCount() {
        return commentPOJOS.size();
    }


    public class MyCommentViewHolder extends RecyclerView.ViewHolder {

        public ImageView userCommentImage;
        public TextView userCommentName;
        public TextView postCommetDate;
        public TextView postCommentText;



        public MyCommentViewHolder(View view) {
            super(view);
            userCommentImage = (ImageView) view.findViewById(R.id.user_comment_image);
            userCommentName = (TextView) view.findViewById(R.id.user_comment_name);
            postCommetDate = (TextView) view.findViewById(R.id.post_comment_date);
            postCommentText = (TextView) view.findViewById(R.id.post_comment_text);



        }


    }


}