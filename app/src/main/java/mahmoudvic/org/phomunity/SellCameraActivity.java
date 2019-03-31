package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import mahmoudvic.org.phomunity.APIClass.Api;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellCameraActivity extends Activity {
    private ImageView close;
    private EditText name;
    private EditText email;
    private EditText mobile;
    private EditText notes;
    private Button submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sell_camera);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        close = (ImageView) findViewById(R.id.isell_sell_closeId);
        name = (EditText) findViewById(R.id.isell_name_edit_text);
        email = (EditText) findViewById(R.id.isell_email_edit_text);
        mobile = (EditText) findViewById(R.id.isell_mobile_edit_text);
        notes = (EditText) findViewById(R.id.isell_notes);
        submit = (Button) findViewById(R.id.isell_reverse_submit);


        name.setText(getSharedPreferences("remember", MODE_PRIVATE).getString("first","")+" "+
                getSharedPreferences("remember", MODE_PRIVATE).getString("last",""));
        email.setText(getSharedPreferences("remember", MODE_PRIVATE).getString("email",""));
        mobile.setText(getSharedPreferences("remember", MODE_PRIVATE).getString("mobile",""));

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(getBaseContext(), dateStr, Toast.LENGTH_LONG).show();
                if (validate(name)) {
                    if (validate(email)) {
                        if (validate(mobile)) {


                            String nameStr = name.getText().toString().trim();
                            String emailStr = email.getText().toString().trim();
                            String mobileStr = mobile.getText().toString().trim();
                            String notesStr = notes.getText().toString().trim();

                            Intent intent = getIntent();
                            addRentCamera(intent.getIntExtra("id", 0), nameStr, mobileStr, emailStr, notesStr);
                            finish();


                        } else {
                            mobile.setError("enter correct mobile");
                        }
                    } else {
                        email.setError("enter valid mail");
                    }
                } else {
                    name.setError("Enter your name");
                }


            }
        });
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

    private void addRentCamera(int product_id, String nameStr, String mobileStr, String emailStr, String notesStr) {
        Api.getClient().sellCamera(product_id, nameStr, mobileStr, emailStr, notesStr).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null&& response!=null) {
                    Log.d("true", response.body().intValue() + "");

                    Toast.makeText(getBaseContext(), "your request has been Sent Successfully", Toast.LENGTH_LONG).show();
//                    ConfirmMessageUtil.setMessage((LinearLayout) findViewById(R.id.sell_msg),
//                            "your request has been Sent Successfully", getBaseContext());
                    finish();


                } else {
                    Log.d("false", "is empty");
                    Toast.makeText(getApplicationContext(), "Check Your Internet Connection", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(SellCameraActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
