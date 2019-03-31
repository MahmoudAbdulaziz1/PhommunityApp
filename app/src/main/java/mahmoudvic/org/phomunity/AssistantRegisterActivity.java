package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssistantRegisterActivity extends AppCompatActivity {

    private EditText areaEditText;
    private EditText aboutEditText;
    private Button secondshooterRegister;
    private ImageView assiatantHeaderImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondshooter_assistant_register);

        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(AssistantRegisterActivity.this, toolbarLayoutFragment);

        areaEditText = findViewById(R.id.secondShooterArea);
        aboutEditText = findViewById(R.id.aboutSecondShooter);
        secondshooterRegister = findViewById(R.id.secondshooter_register_submit);
        assiatantHeaderImage=findViewById(R.id.second_shooter_header);
        assiatantHeaderImage.setImageResource(R.drawable.register_assiatant_header);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Bold.ttf", true);
        secondshooterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String areaText = areaEditText.getText().toString();
                String aboutText = aboutEditText.getText().toString();

                if (!TextUtils.isEmpty(aboutText)) {
                    if (!TextUtils.isEmpty(areaText)) {
                        final ProgressDialog progressDialog = new ProgressDialog(AssistantRegisterActivity.this);
                        progressDialog.setCancelable(false); // set cancelable to false
                        progressDialog.setMessage("Please Wait"); // set message
                        progressDialog.show(); // show progress dialog

                        (Api.getClient().joinAssistant(areaText, aboutText, getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0))).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {

                                if (response.isSuccessful()) {
                                    //signUpResponsesData = response.body();
                                    if (response.body() != null) {
                                        if (response.body().intValue() == 1) {
                                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                            Log.d("ok", response.toString());
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Check your connection", Toast.LENGTH_LONG).show();
                                        }


                                    } else {
                                        Toast.makeText(AssistantRegisterActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                                        Log.d("response fail", "Boom");
                                    }
                                } else {
                                    Toast.makeText(AssistantRegisterActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                                    Log.d("fail", response.toString());

                                }
                                progressDialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                Toast.makeText(AssistantRegisterActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        });
                    } else {
                        areaEditText.setError("enter area");
                    }
                } else {
                    aboutEditText.setError("enter about");

                }
            }
        });
    }
}
