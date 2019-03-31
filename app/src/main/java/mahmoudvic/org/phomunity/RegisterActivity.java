package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.RegisterInterestPopupAdapter;
import mahmoudvic.org.phomunity.drop_down.ActionItem;
import mahmoudvic.org.phomunity.drop_down.QuickAction;
import mahmoudvic.org.phomunity.util.ImageUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private final int PICK_IMAGE_REQUEST = 171;
    private Uri filePath;
    private String image = null;
    private Toolbar registerToolbar;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText passwordConfirmation;
    private EditText mobile;
    private TextView level;
    private EditText cameraBrand;
    private EditText model;
    private TextView interestedIn;
    private EditText portrait;
    private Button uploadImage;
    private ImageView star;
    private TextView noFile;
    private Button signUp;
    private String firstNameTxt;
    private String lastNameTxt;
    private String emailTxt;
    private String passwordTxt;
    private String passwordConfirmationTxt;
    private String mobileTxt;
    private String levelTxt;
    private String cameraBrandTxt;
    private String modelTxt;
    private String interestedInTxt;
    private String portraitTxt;
    private byte[] uploadImageTxt;
    private Button getSelectedBtn;
    private QuickAction mQuickAction;

    private ArrayList<String> interestsList = new ArrayList<>(Arrays.asList("Portrait",
            "Marco",
            "Abstract",
            "Products",
            "Sports",
            "Press",
            "Landscape",
            "Architectural",
            "Fashion",
            "Fine Art",
            "Wedding",
            "Montage",
            "Video",
            "Newborn",
            "Other"));


    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)‌​;
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);

        registerToolbar = (Toolbar) findViewById(R.id.regiter_toolbar);
        setSupportActionBar(registerToolbar);

        registerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        firstName = (EditText) findViewById(R.id.register_first_edit_text);
        lastName = (EditText) findViewById(R.id.register_last_edit_text);
        email = (EditText) findViewById(R.id.register_email_edit_text);
        password = (EditText) findViewById(R.id.register_password_edit_text);
        passwordConfirmation = (EditText) findViewById(R.id.register_confirmation_edit_text);
        mobile = (EditText) findViewById(R.id.register_mobile_edit_text);
        level = (TextView) findViewById(R.id.register_level_edit_text);
        cameraBrand = (EditText) findViewById(R.id.register_camera_edit_text);
        model = (EditText) findViewById(R.id.register_model_edit_text);
        interestedIn = (TextView) findViewById(R.id.register_interest_edit_text);
        uploadImage = (Button) findViewById(R.id.register_upload_edit_text);
        star = (ImageView) findViewById(R.id.register_star);
        noFile = (TextView) findViewById(R.id.register_no_file_text);
        signUp = (Button) findViewById(R.id.register_sign_up);
        mQuickAction = new QuickAction(getBaseContext(), QuickAction.VERTICAL);
        final ArrayList<String> levelsList = new ArrayList<>(Arrays.asList("Beginner", "Intermediate", "Professional"));
        final ArrayList<String> levels = new ArrayList<>();
        //level.setEnabled(false);


        //ActionItemR b_item0 = new ActionItemR(0, "Brand");
        //mQuickAction.addActionItem(b_item0);
        for (int i = 0; i < levelsList.size(); i++) {
            //levels.add(levelsList.get(i).getId());
            ActionItem item1 = new ActionItem(i, levelsList.get(i));
            mQuickAction.addActionItem(item1);
        }

        mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                ActionItem actionItem = mQuickAction.getActionItem(pos);
                level.setText(actionItem.getTitle());
                //Toast.makeText(getApplicationContext(), levelsList.get(actionId) + " selected", Toast.LENGTH_SHORT).show();
            }
        });//new QuickAction.OnActionItemClickListener() {
//            @Override
//            public void onItemClick(QuickAction source, int pos, int actionId) {
//                ActionItem2 actionItem = mQuickAction.getActionItem(pos);
//                level.setText(actionItem.getTitle());
//                Toast.makeText(getApplicationContext(), levelsList.get(actionId) + " selected", Toast.LENGTH_SHORT).show();
//
//            }
//        });


        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                } catch (Exception e) {
//                    // TODO: handle exception
//                }
//                dismissKeyboard(RegisterActivity.this);
                closeKeyboard();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                mQuickAction.show(v);
                mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
//                final Dialog dialog = new Dialog(RegisterActivity.this);
//                // Include dialog.xml file
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.register_popup_level);
//                RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.register_level_popup);
//                LinearLayoutManager manager = new LinearLayoutManager(RegisterActivity.this);
//                recyclerView.setLayoutManager(manager);
//                RegisterLevelPopUp adapter = new RegisterLevelPopUp(RegisterActivity.this, levelsList, dialog, level);
//                recyclerView.setAdapter(adapter);
//                dialog.show();
            }
        });


        interestedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface quickSand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.ttf");

                final Dialog dialog = new Dialog(RegisterActivity.this);
                // Include dialog.xml file
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.register_interest_popup);
                RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.register_interest_popup);
                LinearLayoutManager manager = new LinearLayoutManager(RegisterActivity.this);
                recyclerView.setLayoutManager(manager);
                getSelectedBtn = (Button) dialog.findViewById(R.id.get_selected_btn);
                getSelectedBtn.setTypeface(quickSand);
                RegisterInterestPopupAdapter adapter = new RegisterInterestPopupAdapter(RegisterActivity.this, interestsList, dialog,
                        interestedIn, getSelectedBtn);
                recyclerView.setAdapter(adapter);
                dialog.show();


            }
        });


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("1", "1");
                chooseImageStr();
                //Log.d("1", "2");
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameTxt = firstName.getText().toString();
                lastNameTxt = lastName.getText().toString();
                emailTxt = email.getText().toString();
                passwordTxt = password.getText().toString();
                passwordConfirmationTxt = passwordConfirmation.getText().toString();
                mobileTxt = mobile.getText().toString();
                levelTxt = level.getText().toString();
                cameraBrandTxt = cameraBrand.getText().toString();
                modelTxt = model.getText().toString();

                interestedInTxt = interestedIn.getText().toString();

                if (isValidEmail(emailTxt)) {
                    if (validate(firstName)) {
                        if (validate(lastName)) {
                            if (validate(password)) {
                                if (validate(passwordConfirmation)) {
                                    if (passwordTxt.equals(passwordConfirmationTxt)) {
                                        if (validate(mobile)) {
                                            if (validate(cameraBrand)) {
                                                if (validate(model)) {


                                                    signUp();


                                                } else {
                                                    model.setError("complete field");
                                                    model.requestFocus();
                                                }
                                            } else {
                                                cameraBrand.setError("complete field");
                                                cameraBrand.requestFocus();
                                            }
                                        } else {
                                            mobile.setError("enter correct mobile number");
                                            mobile.requestFocus();
                                        }
                                    } else {
                                        password.setError("not the same");
                                        passwordConfirmation.setError("not the same");
                                        passwordConfirmation.requestFocus();
                                        password.requestFocus();
                                    }
                                } else {
                                    passwordConfirmation.setError("Reenter your password");
                                    passwordConfirmation.requestFocus();
                                }
                            } else {
                                password.setError("complete field");
                                passwordConfirmation.requestFocus();
                            }
                        } else {
                            lastName.setError("complete field");
                            lastName.requestFocus();
                        }
                    } else {
                        firstName.setError("complete field");
                        firstName.requestFocus();
                    }
                } else {
                    email.setError("not correct");
                    email.requestFocus();
                }


            }
        });


    }


    public void dismissKeyboard(AppCompatActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus()) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
        } else {
            Log.d("loggs", "test test test");
        }
        try {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
        } catch (Exception e) {
            Log.d("loggs", "test test test2");
        }
    }


    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void chooseImageStr() {
        Log.d("choose image", "done");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        Log.d("choose image", "done2");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();

            Bitmap bitmap = null;
            Bitmap scaled = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), filePath);
                bitmap = new ImageUtil().getResizedBitmap(bitmap, 80, 80);
            } catch (IOException e) {
                e.printStackTrace();
            }


            image = encodeTobase64(bitmap);
            //star.setVisibility(View.GONE);
            //noFile.setVisibility(View.GONE);
            noFile.setText("Uploaded");

        }
    }


    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private void signUp() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        // enqueue is used for callback response and error
        Log.d("test register", image);
        if (image == null) {
            uploadImage.setError("Must select image");
            progressDialog.dismiss();
        } else {

            (Api.getClient().registration(firstNameTxt, lastNameTxt, emailTxt, passwordTxt, mobileTxt
                    , levelTxt, cameraBrandTxt, modelTxt, interestedInTxt, image)).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response != null && response.isSuccessful()) {
                        if (response.body() != null) {

                            if (response.body().toString().trim().equals("user saved with image")) {
                                Log.d("ok", response.toString());
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                for (int i = 0; i < interestsList.size(); i++) {
                                    getSharedPreferences("shared", Context.MODE_PRIVATE).
                                            edit().putBoolean("check" + i, false).commit();
                                }
                                startActivity(intent);
                                //finish();
                            } else if (response.body().toString().trim().equals("this email already exist")) {
                                Toast.makeText(getApplicationContext(), "This email already exist", Toast.LENGTH_LONG).show();
                                //email.setError("this email already exist");
                                //email.requestFocus();
                            }


                        } else {
                            Toast.makeText(RegisterActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                        }
                    } else {

                        Toast.makeText(RegisterActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < interestsList.size(); i++) {
            getSharedPreferences("shared", Context.MODE_PRIVATE).
                    edit().putBoolean("check" + i, false).commit();
        }
    }
}
