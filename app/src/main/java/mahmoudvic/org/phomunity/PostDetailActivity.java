package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.PostAdapter;
import mahmoudvic.org.phomunity.adapters.PostDetailAdapter;
import mahmoudvic.org.phomunity.pojo.CommentPOJO;
import mahmoudvic.org.phomunity.pojo.GroupPOJO;
import mahmoudvic.org.phomunity.pojo.LikePOJO;
import mahmoudvic.org.phomunity.pojo.PostPOJO;
import mahmoudvic.org.phomunity.util.DateUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.PostUtil;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailActivity extends AppCompatActivity {

//    public static final int userId = 62;


    private ImageView userDetailImage;
    private TextView userDetailName;
    private TextView postDetailDate;
    private TextView postDetailText;
    private TextView likeDetailText;
    private TextView commentDetailText;
    private TextView likeDetailImage;
    private TextView commentDetailImage;
    private RecyclerView commentRecyclerView;
    private EditText commentDetailFeild;
    private ImageView postDetailImage;
    private ImageView sendComment;
    private SwipeRefreshLayout swipeLayout;
    private TextView allComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
//        Calligrapher calligrapher = new Calligrapher(this);
//        calligrapher.setFont(this, "font/Quicksand-Bold.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(PostDetailActivity.this, toolbarLayoutFragment);

        userDetailImage = findViewById(R.id.user_detail_image);
        userDetailName = findViewById(R.id.user_detail_name);
        postDetailDate = findViewById(R.id.post_detail_date);
        postDetailText = findViewById(R.id.post_detail_text);
        likeDetailText = findViewById(R.id.like_detail_count);
        commentDetailText = findViewById(R.id.comment_detail_count);
        likeDetailImage = findViewById(R.id.like_detail_image);
        commentDetailImage = findViewById(R.id.comment_detail_image);
        postDetailImage = findViewById(R.id.post_detail_image);
        commentRecyclerView = findViewById(R.id.comments_recyclerView);
        commentDetailFeild = findViewById(R.id.comment_detail_feild);
        sendComment = findViewById(R.id.send_comment);
        allComments = findViewById(R.id.view_all_comments);


        Intent intent = this.getIntent();

        intent.setExtrasClassLoader(GroupPOJO.class.getClassLoader());
        final int groupId = intent.getIntExtra("groupId", 0);
        final int postId = intent.getIntExtra("postId", 0);

        commentRecyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentRecyclerView.setLayoutManager(linearLayoutManager);



        getAllcoments(groupId, postId);

        swipeLayout = findViewById(R.id.swipe_refresh_detail_layout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(false);
                getAllcoments(groupId, postId);
            }
        });

    }

    public void hideImageView(ImageView imageView) {
        imageView.getLayoutParams().height = 0;
        imageView.setVisibility(View.GONE);
    }

    private void getAllcoments(int groupId, final int postId) {
        final ProgressDialog progressDialog = new ProgressDialog(PostDetailActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        final PostPOJO[] loadedPostPOJO = {null};
        final PostUtil postUtil = new PostUtil();

        try {
            (Api.getClient().getPosts(groupId)).enqueue(new Callback<List<GroupPOJO>>() {
                PostDetailAdapter postAdapter;

                @Override
                public void onResponse(Call<List<GroupPOJO>> call, Response<List<GroupPOJO>> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {
                            List<CommentPOJO> commentPOJOS = null;
                            GroupPOJO groupPOJO = response.body().get(0);
                            List<PostPOJO> postPOJOS = groupPOJO.getPostPOJOS();
                            for (PostPOJO postPOJO : postPOJOS) {
                                if (postPOJO.getId() == postId) {
                                    loadedPostPOJO[0] = postPOJO;
                                    commentPOJOS = postPOJO.getCommentPOJOS();
                                    break;
                                }
                            }
                            String userImageFileName = "";
                            if (loadedPostPOJO[0].getUserPOJO() != null) {
                                if (loadedPostPOJO[0].getUserPOJO().getShooterPhotoPOJO() != null) {
                                    userImageFileName = loadedPostPOJO[0].getUserPOJO().getShooterPhotoPOJO().getImageFilename();

                                    if (!userImageFileName.equals("")) {
                                        Glide.with(PostDetailActivity.this)
                                                .asBitmap()
                                                .load(ImageUtil.BASE_IMAGE_URL + userImageFileName)
                                                .into(userDetailImage);
                                    }
                                }
                            }

                            String postImageFileName = "";
                            if (loadedPostPOJO[0].getImagePOJOS().size() > 0) {
                                if (loadedPostPOJO[0].getImagePOJOS().get(0) != null) {
                                    postImageFileName = loadedPostPOJO[0].getImagePOJOS().get(0).getImageFilename();
                                    if (!postImageFileName.equals("")) {
                                        Glide.with(PostDetailActivity.this)
                                                .asBitmap()
                                                .load(ImageUtil.BASE_IMAGE_URL + postImageFileName)
                                                .into(postDetailImage);
                                    } else {
                                        hideImageView(postDetailImage);
                                    }
                                } else {
                                    hideImageView(postDetailImage);
                                }

                            } else {
                                hideImageView(postDetailImage);
                            }
                            userDetailName.setText(loadedPostPOJO[0].getUserPOJO().getFirstname() + " " + loadedPostPOJO[0].getUserPOJO().getLastname());
                            final Typeface quickSand = Typeface.createFromAsset(PostDetailActivity.this.getAssets(), "font/Quicksand-Bold.ttf");
                            userDetailName.setTypeface(quickSand);

                            postDetailDate.setText(DateUtil.getPostDate(DateUtil.convertToEntityAttribute(loadedPostPOJO[0].getCreatedAt())));
                            postDetailText.setText(loadedPostPOJO[0].getBody().toString());
                            likeDetailImage.setText(R.string.like);
                            commentDetailImage.setText(R.string.comment);
                            allComments.setText(R.string.view_comments);

                            likeDetailImage.setTypeface(quickSand);
                            commentDetailImage.setTypeface(quickSand);
                            allComments.setTypeface(quickSand);

                            List<LikePOJO> likePOJOS = loadedPostPOJO[0].getLikePOJOS();
                            if (likePOJOS != null) {
                                likeDetailText.setText(String.valueOf(likePOJOS.size()));
                                int userId = PostDetailActivity.this.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
                                boolean isLike = false;
                                for (LikePOJO likePOJO : likePOJOS) {
                                    if (likePOJO.getUserId() == userId) {
                                        isLike = true;
                                        break;
                                    }
                                }
                                if (isLike) {
                                    likeDetailImage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_icon, 0, 0, 0);
                                } else {
                                    likeDetailImage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unlike, 0, 0, 0);
                                }
                            } else {
                                likeDetailImage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unlike, 0, 0, 0);
                                likeDetailText.setText("0");
                            }

                            if (loadedPostPOJO[0].getCommentPOJOS() != null) {
                                commentDetailText.setText(String.valueOf(loadedPostPOJO[0].getCommentPOJOS().size()));
                            } else {
                                commentDetailText.setText("0");

                            }
// shareDetailText.setText(String.valueOf(loadedPostPOJO.g().size()));
                            likeDetailImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    postUtil.storeLike(PostDetailActivity.this, loadedPostPOJO[0].getId(), getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0), likeDetailText, likeDetailImage);

                                }
                            });
                            final List<CommentPOJO> finalCommentPOJOS = commentPOJOS;
                            sendComment.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String comment = commentDetailFeild.getText().toString();
                                    if (TextUtils.isEmpty(comment)) {
                                        commentDetailFeild.setError("enter comment");
                                    } else {
                                        int userId = getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
                                        postUtil.storeComment(PostDetailActivity.this, loadedPostPOJO[0].getId(), userId, comment, finalCommentPOJOS, postAdapter);
                                        int count = Integer.parseInt(commentDetailText.getText().toString());
                                        count++;
                                        commentDetailText.setText(String.valueOf(count));
                                        commentDetailFeild.setText("");
                                        allComments.setText("All comments..");
                                    }
                                }
                            });

                            if (finalCommentPOJOS.isEmpty()) {
                                allComments.setText("there's no comments..");
                            } else {
                                allComments.setText("All comments..");
                            }
                            postAdapter = new PostDetailAdapter(PostDetailActivity.this, finalCommentPOJOS);
                            commentRecyclerView.setAdapter(postAdapter);


                        } else {
                            Toast.makeText(PostDetailActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(PostDetailActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<GroupPOJO>> call, Throwable t) {
                    Toast.makeText(PostDetailActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
