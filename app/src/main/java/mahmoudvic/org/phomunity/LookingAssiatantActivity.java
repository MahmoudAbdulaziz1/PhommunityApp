package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.AssistantAdapter;
import mahmoudvic.org.phomunity.pojo.AssiatantsPOJO;
import mahmoudvic.org.phomunity.pojo.AssistantSecondShooterPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LookingAssiatantActivity extends AppCompatActivity {

    private AssistantAdapter secondShooterAdapter;
    private RecyclerView recyclerView;
    private List<AssistantSecondShooterPOJO> allShooterList = new ArrayList<>();
    private List<AssistantSecondShooterPOJO> topShooterList = new ArrayList<>();
    private ImageView allAssiatant;
    private ImageView topAssiatant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_assiatant);
        allAssiatant = findViewById(R.id.all_assistant);
        topAssiatant = findViewById(R.id.top_assistant);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        String[] sorts = new String[]{getResources().getString(R.string.sort_by), "Beginner", "Intermediate", "Professional"};

        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(LookingAssiatantActivity.this, toolbarLayoutFragment);
        recyclerView = (RecyclerView) findViewById(R.id.assiatant_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        initData(false);

        allAssiatant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(false);
            }
        });
        topAssiatant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(true);
            }
        });
    }


    private void initData(final boolean isTop) {
        final ProgressDialog progressDialog = new ProgressDialog(LookingAssiatantActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        try {
            (Api.getClient().getAssistants()).enqueue(new Callback<AssiatantsPOJO>() {
                @Override
                public void onResponse(Call<AssiatantsPOJO> call, Response<AssiatantsPOJO> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            AssiatantsPOJO assiatantsPOJO = response.body();
                            allShooterList = response.body().getAssistantSecondShooterPOJOS();
                            topShooterList = response.body().getTopAssistantSecondShooterPOJOS();
                            if (isTop) {
                                secondShooterAdapter = new AssistantAdapter(LookingAssiatantActivity.this, topShooterList);
                                recyclerView.setAdapter(secondShooterAdapter);
                            } else {
                                secondShooterAdapter = new AssistantAdapter(LookingAssiatantActivity.this, allShooterList);
                                recyclerView.setAdapter(secondShooterAdapter);
                            }
                        } else {
                            Toast.makeText(LookingAssiatantActivity.this, "response fail ", Toast.LENGTH_LONG).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), " failed " + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<AssiatantsPOJO> call, Throwable t) {
                    Toast.makeText(LookingAssiatantActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}