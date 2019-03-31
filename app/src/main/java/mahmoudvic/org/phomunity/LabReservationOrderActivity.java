package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class LabReservationOrderActivity extends Activity {
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
        setContentView(R.layout.activity_lab_reservation_order);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        close = (ImageView) findViewById(R.id.lab_reservation_close);
        name = (EditText) findViewById(R.id.lab_name_edit_text);
        email = (EditText) findViewById(R.id.lab_email_edit_text);
        mobile = (EditText) findViewById(R.id.lab_mobile_edit_text);
        notes = (EditText) findViewById(R.id.lab_notes);
        submit = (Button) findViewById(R.id.lab_reservation_submit);

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
                            String clientName = name.getText().toString().trim();
                            String clientPhoneNumber = mobile.getText().toString().trim();
                            String clientEmail = email.getText().toString().trim();
                            String clientNotes = notes.getText().toString().trim();
                            Intent intent = getIntent();
                            addRentLab(intent.getIntExtra("id", 0), clientName, clientPhoneNumber, clientEmail, clientNotes);
                            finish();
                        } else {
                            mobile.setError("Enter Valid Mobile");
                        }
                    } else {
                        email.setError("Enter Valid Email");
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

    private void addRentLab(int labOptionId, String clientName, String clientPhoneNumber,
                            String clientEmailAddress, String notes) {
        Api.getClient().rentLab(labOptionId, clientName, clientPhoneNumber, clientEmailAddress, notes).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response != null && response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(getBaseContext(), R.string.request_success, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(LabReservationOrderActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(LabReservationOrderActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(LabReservationOrderActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();

            }
        });

    }

}