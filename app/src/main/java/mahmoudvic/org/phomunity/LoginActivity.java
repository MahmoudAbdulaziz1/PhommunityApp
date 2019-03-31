package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.pojo.LoginRsponse;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Toolbar loginToolbar;
    private Button signUpBtn;
    private Button signInBtn;
    private EditText email;
    private EditText password;
    private String emailTxt;
    private String passwordTxt;
    private TextView joinOurTeamTxt;
    private TextView aboutUsTxt;
    private TextView forgetPassword;
    //private CheckBox rememberMeCheckBox;
    private SharedPreferences rememberMePreference;

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Bold.ttf", true);
        loginToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(loginToolbar);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        joinOurTeamTxt = (TextView) findViewById(R.id.join_our_team);
        aboutUsTxt = (TextView) findViewById(R.id.about_us);
        forgetPassword = (TextView) findViewById(R.id.forget_password_txt);
        email = (EditText) findViewById(R.id.login_email_edit_text);
        password = (EditText) findViewById(R.id.login_password_edit_text);
        signUpBtn = (Button) findViewById(R.id.sign_up);
        signInBtn = (Button) findViewById(R.id.sign_in);
        //rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me);
        rememberMePreference = getSharedPreferences("remember", MODE_PRIVATE);
        //remember me shared preference
        boolean isRememberChecked = rememberMePreference.getBoolean("checked", false);
        if (isRememberChecked) {
            //rememberMeCheckBox.setChecked(true);
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
//            SharedPreferences.Editor loginData = rememberMePreference.edit();
//            String emailBack   = rememberMePreference.getString("email", "");
//            String passwordBac = rememberMePreference.getString("password", "");
//            //loginData.commit();
//            if (emailBack.trim().equals("")||passwordBac.trim().equals("")){
//
//            }else {
//                signIn(email.getText().toString().trim(), password.getText().toString().trim());
//            }

        }
///////////////////////////////////////////////////////////////////////////////
        //toolbar texts
        joinOurTeamTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NotLoginJoinOurTeamActivity.class);
                startActivity(intent);
            }
        });

        aboutUsTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NotLoginAboutUsActivity.class);
                startActivity(intent);
            }
        });


        //////////////////////////////////////////////////////////////////////////////////////
        //sign up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //////////////////////////////////////////////////////////////////////////////
        //login button

        password.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    //Toast.makeText(LoginActivity.this, email.getText()+"", Toast.LENGTH_SHORT).show();
                    emailTxt = email.getText().toString().trim();
                    passwordTxt = password.getText().toString().trim();
                    if (validate(email)) {
                        if (validate(password)) {
                            signIn(emailTxt, passwordTxt);
                        } else {
                            password.setError("enter your password");
                        }
                    } else {
                        email.setError("not correct mail");
                    }
                    return true;
                }
                return false;
            }
        });


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailTxt = email.getText().toString().trim();
                passwordTxt = password.getText().toString().trim();
                if (validate(email)) {
                    if (validate(password)) {
                        signIn(emailTxt, passwordTxt);
                    } else {
                        password.setError("enter your password");
                    }
                } else {
                    email.setError("not correct mail");
                }


            }
        });

        ////////////////////////////////////////////////////////////////////////////
        //forget password text
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "forget Password", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
            }
        });

        //////////////////////////////////////////////////////////////////////////////////
        //remember me check box
//        rememberMeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    SharedPreferences.Editor editor = rememberMePreference.edit();
//                    editor.putBoolean("checked", true);
//                    editor.commit();
//                }else {
//                    SharedPreferences.Editor editor = rememberMePreference.edit();
//                    editor.putBoolean("checked", false);
//                    editor.commit();
//                }
//            }
//        });


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


    public void signIn(String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        // enqueue is used for callback response and error
//email.getText().toString().trim()    password.getText().toString().trim()


        (Api.getClient().login(email, password))
                .enqueue(new Callback<LoginRsponse>() {
                    @Override
                    public void onResponse(Call<LoginRsponse> call, Response<LoginRsponse> response) {

                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                LoginRsponse res = response.body();
                                if (res.getStatus() == 200) {
                                    Log.d("correct", res.getUser().getId() + " ");

                                    SharedPreferences.Editor editor = rememberMePreference.edit();
                                    editor.putBoolean("checked", true);
                                    //editor.putInt("id", res.getUser().getId());
                                    editor.commit();
                                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                                    intent.putExtra("id", res.getUser().getId());
                                    Log.d("id", res.getUser().getId() + "");
                                    intent.putExtra("first", res.getUser().getFirstName());
                                    intent.putExtra("second", res.getUser().getLastName());
                                    intent.putExtra("email", res.getUser().getEmail());
                                    intent.putExtra("password", res.getUser().getPassword());
                                    intent.putExtra("mobile", res.getUser().getMobile());
                                    intent.putExtra("level", res.getUser().getLevel());
                                    intent.putExtra("camera", res.getUser().getCameraBrand());
                                    intent.putExtra("model", res.getUser().getCameraModel());
                                    intent.putExtra("interested", res.getUser().getInterestedIn());
                                    //intent.putExtra("", res.getUser().);
                                    // Toast.makeText(getBaseContext(), res.getUser().getId() +"", Toast.LENGTH_LONG).show();

                                    SharedPreferences.Editor loginData = rememberMePreference.edit();
                                    loginData.putInt("id", res.getUser().getId());
                                    loginData.putString("first", res.getUser().getFirstName());
                                    loginData.putString("second", res.getUser().getLastName());
                                    loginData.putString("email", res.getUser().getEmail());
                                    loginData.putString("password", res.getUser().getPassword());
                                    loginData.putString("mobile", res.getUser().getMobile());
                                    loginData.putString("level", res.getUser().getLevel());
                                    loginData.putString("camera", res.getUser().getCameraBrand());
                                    loginData.putString("model", res.getUser().getCameraModel());
                                    loginData.putString("interested", res.getUser().getInterestedIn());

                                    //loginData.putInt("count", res.getPoints().get(0).getCount());

                                    loginData.commit();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else if (res.getStatus() == 405) {
                                    Toast.makeText(getApplicationContext(), "Email or Passwod Incorrect", Toast.LENGTH_LONG).show();
                                } else {
                                    Log.d("what is this", "ooooooops");
                                }
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(LoginActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<LoginRsponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();
                        Log.d("LoginActivity", "login api faillure");
                        progressDialog.dismiss();

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < 14; i++) {
            getSharedPreferences("shared", Context.MODE_PRIVATE).
                    edit().putBoolean("check" + i, false).commit();
        }
    }
}
