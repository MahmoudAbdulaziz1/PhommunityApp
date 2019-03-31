package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.pojo.GetProfilePOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {


    private ImageView profileImage;
    private TextView profileId;
    private TextView profilePixel;
    private TextView profileUserName;
    private TextView profileEmail;
    private TextView profileMobile;
    private TextView profileLevel;
    private TextView profileCamera;
    private TextView profileInterest;
    private Button updateProfileInformation;

    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Bold.ttf", true);

        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(UserProfileActivity.this, toolbarLayoutFragment);

        profileImage = (ImageView) findViewById(R.id.profile_image_textView);
        profileId = (TextView) findViewById(R.id.profile_id_num);
        calligrapher.setFont(profileId, "font/Quicksand-Bold.ttf");
        profilePixel = (TextView) findViewById(R.id.profile_pixel);
        calligrapher.setFont(profilePixel, "font/Quicksand-Bold.ttf");
        updateProfileInformation = (Button) findViewById(R.id.update_profile_information);
        profileInterest = (TextView) findViewById(R.id.profile_interest);
        profileCamera = (TextView) findViewById(R.id.profile_camera);
        profileLevel = (TextView) findViewById(R.id.profile_level);
        profileMobile = (TextView) findViewById(R.id.profile_mobile);
        profileEmail = (TextView) findViewById(R.id.profile_email);
        profileUserName = (TextView) findViewById(R.id.profile_user_name);
        updateProfileInformation = (Button) findViewById(R.id.update_profile_information);
        final ProgressDialog progressDialog = new ProgressDialog(UserProfileActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog


        int id = getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
        (Api.getClient().getProfile(id))
                .enqueue(new Callback<List<GetProfilePOJO>>() {
                    @Override
                    public void onResponse(Call<List<GetProfilePOJO>> call, Response<List<GetProfilePOJO>> response) {

                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                SharedPreferences preferences = getSharedPreferences("pro_data", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                List<GetProfilePOJO> res = response.body();
                                if (res.get(0).getId() > 0) {
                                    try {

                                        Log.d("did it", res.get(0).getPhoto().getImageFilename());
                                        //profileImage.setImageBitmap(decodeBase64(res.get(0).getPhoto()));
                                        editor.putString("image", res.get(0).getPhoto().getImageFilename());
                                        Picasso.with(getBaseContext()).
                                                load(ImageUtil.BASE_IMAGE_URL + res.get(0).getPhoto().getImageFilename()).
                                                into(profileImage);

                                    } catch (NullPointerException e) {

                                        //Log.d("did it", res.get(0).getPhoto().getImageFilename());
                                        editor.putString("image", "");
                                        //profileImage.setImageBitmap(decodeBase64(res.get(0).getPhoto()));
//                                    Picasso.with(getBaseContext()).
//                                            load(ImageUtil.BASE_IMAGE_URL+res.get(0).getPhoto().getImageFilename()).
//                                            into(profileImage);
                                        profileImage.setImageResource(R.drawable.logo);

                                    }

                                    profileId.setText(res.get(0).getId() + "");
                                    try {
                                        profilePixel.setText(res.get(0).getPoints().get(0).getCount() + "");
                                        editor.putInt("pixel", res.get(0).getPoints().get(0).getCount());

                                    } catch (NullPointerException e) {
                                        profilePixel.setText("0");
                                        editor.putInt("pixel", 0);
                                    } catch (IndexOutOfBoundsException e) {
                                        profilePixel.setText("0");
                                        editor.putInt("pixel", 0);
                                    }
                                    profileInterest.setText(res.get(0).getInterestedIn());
                                    editor.putString("interest", res.get(0).getInterestedIn());
                                    profileCamera.setText(res.get(0).getCameraBrand());
                                    editor.putString("brand", res.get(0).getCameraBrand());
                                    profileLevel.setText(res.get(0).getLevel());
                                    editor.putString("level", res.get(0).getLevel());
                                    profileMobile.setText(res.get(0).getMobile());
                                    editor.putString("mobile", res.get(0).getMobile());
                                    profileEmail.setText(res.get(0).getEmail());
                                    editor.putString("email", res.get(0).getEmail());
                                    profileUserName.setText(res.get(0).getFirstName() + " " + res.get(0).getLastName());
                                    editor.putString("first", res.get(0).getFirstName());
                                    editor.putString("last", res.get(0).getLastName());
                                    Log.d("val", res.get(0).getLastName() + " " + res.get(0).getCameraBrand() + " " + res.get(0).getInterestedIn());
                                    editor.putString("model", res.get(0).getCameraModel());
                                    editor.commit();
                                } else {
                                    Log.d("what is this", "ooooooops");
                                }
                                progressDialog.dismiss();

                            } else {
                                Toast.makeText(UserProfileActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(UserProfileActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetProfilePOJO>> call, Throwable t) {
                        Toast.makeText(UserProfileActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    }
                });

        updateProfileInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), UpdateProfileActivity.class);
                startActivity(intent);
            }
        });
    }


}
