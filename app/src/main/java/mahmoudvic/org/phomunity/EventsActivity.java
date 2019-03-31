package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.EventsAdapter;
import mahmoudvic.org.phomunity.pojo.EventPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {

    private EventsAdapter eventsAdapter;
    private RecyclerView eventRecyclerView;
    List<EventPOJO> eventPOJOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);

        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(EventsActivity.this, toolbarLayoutFragment);


        eventRecyclerView = (RecyclerView) findViewById(R.id.events_recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        eventRecyclerView.setLayoutManager(mLayoutManager);
        eventRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initData();


    }


    private void initData() {
        final ProgressDialog progressDialog = new ProgressDialog(EventsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        try {
            (Api.getClient().getEvents()).enqueue(new Callback<List<EventPOJO>>() {

                @Override
                public void onResponse(Call<List<EventPOJO>> call, Response<List<EventPOJO>> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {
//                        assistantSecondShooterPOJOsList.add(assistantSecondShooterPOJO2);
//                            for (AssistantSecondShooterPOJO assistantSecondShooterPOJO :response.body().getAssistantSecondShooterPOJOS()){
//                                System.out.println(assistantSecondShooterPOJO);
//                            }
                            eventPOJOS = response.body();
                            eventPOJOS.add(0, new EventPOJO());
                            eventsAdapter = new EventsAdapter(EventsActivity.this, eventPOJOS);
                            eventRecyclerView.setAdapter(eventsAdapter);


                        } else {
                            Toast.makeText(EventsActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(EventsActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<EventPOJO>> call, Throwable t) {
                    Toast.makeText(EventsActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
