package mahmoudvic.org.phomunity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mahmoudvic.org.phomunity.APIClass.Api;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetActivity extends AppCompatActivity {

    private EditText email;
    private Button send;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.forget_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        email = (EditText) findViewById(R.id.forget_edit_text);
        send = (Button) findViewById(R.id.send_forget);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "TEST", Toast.LENGTH_LONG).show();
                if (validate(email)) {
                    Api.getClient().forgetPassword(email.getText().toString()).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    String res = response.body();
                                    if (res.equals("email has been sent")) {
                                        Toast.makeText(getApplicationContext(), "Email has been sent", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else if (res.equals("email not found")) {
                                        email.setError("Email not found");
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Check your validation", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(ForgetActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    email.setError("Enter Your Email");
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

}
