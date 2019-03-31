package mahmoudvic.org.phomunity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.HelpAdapter;
import mahmoudvic.org.phomunity.pojo.GetHelpPOJO;
import mahmoudvic.org.phomunity.util.ConfirmMessageUtil;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpActivity extends AppCompatActivity {

    private EditText helpMessage;
    private Button sendMessage;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeLayout;

    private ArrayList<String> questions = new ArrayList<String>(Arrays.asList("What is Question1?", "What is Question2?", "What is Question3?", "What is Question3?", "What is Question4?",
            "What is Question5?", "What is Question5?", "What is Question7?", "What is Question8?"));
    private ArrayList<String> answers = new ArrayList<String>(Arrays.asList("This is Answer1", "This is Answer2", "This is Answer3", "This is Answer4", "This is Answer5", "This is Answer6", "This is Answer7", "This is Answer8", "This is Answer9"));

    private ArrayList<String> questionMessages = new ArrayList<String>();
    private ArrayList<Integer> AnswerMessages = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(HelpActivity.this, toolbarLayoutFragment);
        helpMessage = (EditText) findViewById(R.id.help_send_message);
        sendMessage = (Button) findViewById(R.id.send_help_message_btn);

        //send help message


        swipeLayout = findViewById(R.id.swipe_refresh_help);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(false);
                int ids = getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
                Log.d("Refresh","Refreshhhhhhhh ids:"+ids);
                getHelpMessages(ids);
            }
        });

        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user_id = getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
                String msg = helpMessage.getText().toString().trim();
                if (msg.equals("") || msg.equals(null)) {
                    helpMessage.setError("Enter Your message");
                } else {
                    sendHelpMessage(user_id, msg);
                }
            }
        });


        helpMessage.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    int user_id = getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);
                    String msg = helpMessage.getText().toString().trim();
                    if (msg.equals("") || msg.equals(null)) {
                        helpMessage.setError("Enter Your message");
                    } else {
                        sendHelpMessage(user_id, msg);
                    }
                    return true;
                }
                return false;
            }
        });


        int ids = getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0);

        getHelpMessages(ids);

    }


    public void sendHelpMessage(final int user_id, String body) {


        (Api.getClient().sendHelpMessage(user_id, body))
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {

                        if (response.isSuccessful()) {
                            int res = response.body();
                            if (res == 1) {
                                Log.d("correct", "sent");
                                helpMessage.setText("");
                                getHelpMessages(user_id);
                                ConfirmMessageUtil.setMessage((LinearLayout) findViewById(R.id.help_activity), "Message Sent Successfully", getBaseContext());
                            } else {
                                Log.d("what is this", "ooooooops");
                            }
                        } else {
                            Log.d("error", "not success " + response);
                            //Toast.makeText(getApplicationContext(), " fiald " + response.body().toString(), Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(HelpActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void getHelpMessages(int ids){
        (Api.getClient().getHeloMessages(ids))
                .enqueue(new Callback<List<GetHelpPOJO>>() {
                    @Override
                    public void onResponse(Call<List<GetHelpPOJO>> call, Response<List<GetHelpPOJO>> response) {

                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                List<GetHelpPOJO> res = response.body();
                                if (response.body() != null) {
                                    Log.d("res", res.size() + "");
                                    AnswerMessages.clear();
                                    questionMessages.clear();
                                    for (int i = 0; i < res.size(); i++) {
                                        AnswerMessages.add(res.get(i).getAdmin_user_id());
                                        questionMessages.add(res.get(i).getBody());
                                    }
                                    recyclerView = (RecyclerView) findViewById(R.id.help_recycle);
                                    //set a LinearLayoutManager with default orientation
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
//                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                                        linearLayoutManager.getOrientation());
//                                //dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getBaseContext(),));
//                                recyclerView.addItemDecoration(dividerItemDecoration);
                                    HelpAdapter adapter = new HelpAdapter(getBaseContext(), questionMessages, AnswerMessages);
                                    recyclerView.setAdapter(adapter);
                                } else {
                                    Toast.makeText(HelpActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                                }
                            } else {

                                Toast.makeText(HelpActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetHelpPOJO>> call, Throwable t) {
                        Toast.makeText(HelpActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();

                    }
                });
    }

}
