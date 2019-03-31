package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


import mahmoudvic.org.phomunity.PostDetailActivity;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.GroupPOJO;
import mahmoudvic.org.phomunity.pojo.LikePOJO;
import mahmoudvic.org.phomunity.pojo.PostPOJO;
import mahmoudvic.org.phomunity.util.DateUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.PostUtil;

import static android.content.Context.MODE_PRIVATE;

public class PostAdapter extends RecyclerView.Adapter {

    private GroupPOJO groupPOJO;
    private List<PostPOJO> postPOJOS;
    private Context mContext;

    public PostAdapter(Context mContext, GroupPOJO groupPOJO, List<PostPOJO> postPOJOS) {
        this.groupPOJO = groupPOJO;
//        this.postPOJOS=new ArrayList<>();
        this.postPOJOS = postPOJOS;
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

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new PostAdapter.MyPostViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof PostAdapter.MyPostViewHolder) {
            final PostPOJO postPOJO = postPOJOS.get(position);
            final PostUtil postUtil = new PostUtil();
            String userImageFileName = "";
            if (postPOJO.getUserPOJO() != null) {
                if (postPOJO.getUserPOJO().getShooterPhotoPOJO() != null) {
                    userImageFileName = postPOJO.getUserPOJO().getShooterPhotoPOJO().getImageFilename();
                    if (!userImageFileName.equals("")) {
                        Glide.with(mContext)
                                .asBitmap()
                                .load(ImageUtil.BASE_IMAGE_URL + userImageFileName)
                                .into(((MyPostViewHolder) holder).userImage);
                    }
                }
            }

            String postImageFileName = "";
            if (postPOJO.getImagePOJOS() != null) {
                if (postPOJO.getImagePOJOS().size() > 0) {
                    if (postPOJO.getImagePOJOS().get(0) != null) {
                        postImageFileName = postPOJO.getImagePOJOS().get(0).getImageFilename();
                        if (!postImageFileName.equals("")) {
                            Glide.with(mContext)
                                    .asBitmap()
                                    .load(ImageUtil.BASE_IMAGE_URL + postImageFileName)
                                    .into(((MyPostViewHolder) holder).postImage);
                        } else {
                            hideImageView(((MyPostViewHolder) holder).postImage);
                        }

                    } else {
                        hideImageView(((MyPostViewHolder) holder).postImage);
                    }
                } else {
                    hideImageView(((MyPostViewHolder) holder).postImage);
                }
            } else {
                hideImageView(((MyPostViewHolder) holder).postImage);
            }
            if (postPOJO.getUserPOJO() != null) {
                ((MyPostViewHolder) holder).userName.setText(postPOJO.getUserPOJO().getFirstname() + " " + postPOJO.getUserPOJO().getLastname());

            }
            final Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Bold.ttf");
            ((MyPostViewHolder) holder).userName.setTypeface(quickSand);
            ((MyPostViewHolder) holder).postDate.setText(DateUtil.getPostDate(DateUtil.convertToEntityAttribute(postPOJO.getCreatedAt())));
            ((MyPostViewHolder) holder).postText.setText(postPOJO.getBody());
            ((MyPostViewHolder) holder).likeImage.setTypeface(quickSand);
            ((MyPostViewHolder) holder).commentImage.setTypeface(quickSand);
            ((MyPostViewHolder) holder).postText.setTypeface(quickSand);
            List<LikePOJO> likePOJOS = postPOJO.getLikePOJOS();
            if (likePOJOS != null) {
                ((MyPostViewHolder) holder).likesCount.setText(String.valueOf(likePOJOS.size()));
                int userId = mContext.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
                boolean isLike = false;
                for (LikePOJO likePOJO : likePOJOS) {
                    if (likePOJO.getUserId() == userId) {
                        isLike = true;
                        break;
                    }
                }
                if (isLike) {
                    ((MyPostViewHolder) holder).likeImage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_icon, 0, 0, 0);
                } else {
                    ((MyPostViewHolder) holder).likeImage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unlike, 0, 0, 0);
                }
            } else {
                ((MyPostViewHolder) holder).likeImage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unlike, 0, 0, 0);
                ((MyPostViewHolder) holder).likesCount.setText("0");
            }
            if (postPOJO.getCommentPOJOS() != null) {
                ((MyPostViewHolder) holder).commentsCount.setText(String.valueOf(postPOJO.getCommentPOJOS().size()));
            } else {
                ((MyPostViewHolder) holder).commentsCount.setText("0");
            }
            ((MyPostViewHolder) holder).commentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PostDetailActivity.class);
                    intent.putExtra("groupId", groupPOJO.getId());
                    intent.putExtra("postId", postPOJO.getId());
                    mContext.startActivity(intent);
                }
            });
            ((MyPostViewHolder) holder).likeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postUtil.storeLike(mContext, postPOJO.getId(), mContext.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0), ((MyPostViewHolder) holder).likesCount, ((MyPostViewHolder) holder).likeImage);

                }
            });
            ((MyPostViewHolder) holder).commentImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PostDetailActivity.class);
                    intent.putExtra("groupId", groupPOJO.getId());
                    intent.putExtra("postId", postPOJO.getId());
                    mContext.startActivity(intent);
                }
            });


        }
    }

    public void hideImageView(ImageView imageView) {
        imageView.getLayoutParams().height = 0;
        imageView.setVisibility(View.GONE);
    }


    @Override
    public int getItemCount() {
        return groupPOJO.getPostPOJOS().size();
    }


    public class MyPostViewHolder extends RecyclerView.ViewHolder {

        public ImageView userImage;
        public TextView userName;
        public TextView postDate;
        public TextView postText;
        public ImageView postImage;
        public TextView likesCount;
        public TextView commentsCount;
        public TextView likeImage;
        public TextView commentImage;
        public LinearLayout commentLayout;

        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public MyPostViewHolder(View view) {
            super(view);
            userImage = (ImageView) view.findViewById(R.id.user_image);
            userName = (TextView) view.findViewById(R.id.user_name);
            postDate = (TextView) view.findViewById(R.id.post_date);
            postText = (TextView) view.findViewById(R.id.post_text);
            postImage = (ImageView) view.findViewById(R.id.post_image);
            likesCount = (TextView) view.findViewById(R.id.like_count);
            commentsCount = (TextView) view.findViewById(R.id.comment_count);
            likeImage = (TextView) view.findViewById(R.id.like_image);
            commentImage = (TextView) view.findViewById(R.id.comment_image);
            commentLayout = view.findViewById(R.id.comment_layout);

            userName.setTypeface(quickSand);
            postDate.setTypeface(quickSand);
            postText.setTypeface(quickSand);
            likesCount.setTypeface(quickSand);
            commentsCount.setTypeface(quickSand);
            likeImage.setTypeface(quickSand);
            commentImage.setTypeface(quickSand);


        }


    }


}
