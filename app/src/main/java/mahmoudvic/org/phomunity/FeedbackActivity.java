package mahmoudvic.org.phomunity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.util.ConfirmMessageUtil;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    private EditText note;
    private Button send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        note = (EditText) findViewById(R.id.editText);
        send = (Button) findViewById(R.id.send_feedback_btn);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(note)) {
                    int user_id = getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
                    sendFeedback(user_id, note.getText().toString());
                }
            }
        });

        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(FeedbackActivity.this, toolbarLayoutFragment);

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

    public void sendFeedback(int user_id, String body) {


        (Api.getClient().sendFeedback(user_id, body))
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {

                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                int res = response.body();
                                if (res == 1) {
                                    Log.d("correct", "sent");
                                    note.setText("");
                                    ConfirmMessageUtil.setMessage((LinearLayout) findViewById(R.id.feed_activity), "Message Sent Successfully", getBaseContext());

                                } else {
                                    Log.d("what is this", "ooooooops");
                                }

                            } else {
                                Toast.makeText(FeedbackActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(FeedbackActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(FeedbackActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();

                    }
                });
    }
}
