package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.PostAdapter;
import mahmoudvic.org.phomunity.pojo.GroupPOJO;
import mahmoudvic.org.phomunity.pojo.PostPOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.PostUtil;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    public TextView groupName;
    public EditText postFeild;
    public ImageView captureImage;
    public ImageView attachFiles;
    public Button createPost;
    public ImageView showGroupMember;
    public ImageView previwImage;
    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeLayout;

    private String image = null;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 171;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(PostActivity.this, toolbarLayoutFragment);


        Intent intent = this.getIntent();

        intent.setExtrasClassLoader(GroupPOJO.class.getClassLoader());
        final int groupId = intent.getIntExtra("groupId", 0);
        groupName = (TextView) findViewById(R.id.group_name);
        postFeild = (EditText) findViewById(R.id.post_feild);
        captureImage = (ImageView) findViewById(R.id.capture_image);
        attachFiles = (ImageView) findViewById(R.id.attach_file);
        createPost = (Button) findViewById(R.id.create_post);
        showGroupMember = findViewById(R.id.show_group_member);
        previwImage = findViewById(R.id.preview_image);
        recyclerView = (RecyclerView) findViewById(R.id.post_recycler_view);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);

        getAllPosts(groupId);

        swipeLayout = findViewById(R.id.swipe_refresh_layout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(false);
                getAllPosts(groupId);
            }
        });

        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

    }


    private void chooseImageStr() {
        Log.d("choose image", "done");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        Log.d("choose image", "done2");
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), filePath);

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");

        }
        if (bitmap != null) {
            bitmap = new ImageUtil().getResizedBitmap(bitmap, 300, 250);
            image = encodeTobase64(bitmap);
            if (image.equals("")) {
                Toast.makeText(PostActivity.this, "large file, please select other one", Toast.LENGTH_LONG).show();

            }
            previwImage.setVisibility(View.VISIBLE);
            previwImage.setImageBitmap(bitmap);
        }
    }


    public void getAllPosts(int groupId) {
        final ProgressDialog progressDialog = new ProgressDialog(PostActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        final PostAdapter[] postAdapter = {null};

        try {
            (Api.getClient().getPosts(groupId)).enqueue(new Callback<List<GroupPOJO>>() {

                @Override
                public void onResponse(Call<List<GroupPOJO>> call, Response<List<GroupPOJO>> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {

                            final GroupPOJO groupPOJO = response.body().get(0);
                            final List<PostPOJO> postPOJOS = groupPOJO.getPostPOJOS();
                            String name = groupPOJO.getName();
                            groupName.setText(name);
                            final Typeface quickSand = Typeface.createFromAsset(getBaseContext().getAssets(), "font/Quicksand-Bold.ttf");
                            groupName.setTypeface(quickSand);


                            createPost.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String post = postFeild.getText().toString();

                                    if ((!TextUtils.isEmpty(post)) || (image != null && !image.isEmpty())) {
                                        new PostUtil().storePost(PostActivity.this, groupPOJO.getId(), PostActivity.this.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0), post, image, postAdapter[0], postPOJOS);
//                                        PostPOJO postPOJO = new PostPOJO();
//                                        postPOJO.setBody("");
//                                        postPOJOS.add(postPOJO);
//                                        postAdapter[0].notifyDataSetChanged();
                                        previwImage.setVisibility(View.GONE);
                                        postFeild.setText("");
                                    } else {
                                        postFeild.setError("enter post or select image");
                                    }
                                }
                            });

                            attachFiles.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    chooseImageStr();
                                }
                            });

                            captureImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dispatchTakePictureIntent();
                                }
                            });

                            showGroupMember.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(PostActivity.this, GroupMemberActivity.class);
                                    intent.putExtra("groupId", groupPOJO.getId());
                                    startActivity(intent);

                                }
                            });


                            postAdapter[0] = new PostAdapter(PostActivity.this, groupPOJO, postPOJOS);
                            recyclerView.setAdapter(postAdapter[0]);


                        } else {
                            Toast.makeText(PostActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(PostActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<GroupPOJO>> call, Throwable t) {
                    Toast.makeText(PostActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    Log.d("response", t.getStackTrace().toString() + " " + call.toString());
                    progressDialog.dismiss();

                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

}
