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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.SecondShooterAdapter;
import mahmoudvic.org.phomunity.drop_down.ActionItem;
import mahmoudvic.org.phomunity.drop_down.QuickAction;
import mahmoudvic.org.phomunity.pojo.AssistantSecondShooterPOJO;
import mahmoudvic.org.phomunity.pojo.ShootersPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LookingSecondShooterActivity extends AppCompatActivity {

    private SecondShooterAdapter secondShooterAdapter;
    private RecyclerView recyclerView;
    private TextView sortTextView;
    private QuickAction secondshooterSortQuickAction;
    private List<AssistantSecondShooterPOJO> allShooterList = new ArrayList<>();
    private List<AssistantSecondShooterPOJO> topShooterList = new ArrayList<>();
    private ImageView allShooter;
    private ImageView topShooter;
    private boolean isTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_shooter);
        allShooter = findViewById(R.id.all_shooters);
        topShooter = findViewById(R.id.top_shooter);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        String[] sorts = new String[]{getResources().getString(R.string.sort_by), "Beginner", "Intermediate", "Professional"};

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sortTextView = findViewById(R.id.sort_seconshooter_text_view);
        final List<Integer> brandList = new ArrayList<>();

        secondshooterSortQuickAction = new QuickAction(getBaseContext(), QuickAction.VERTICAL);
        for (int i = 0; i < sorts.length; i++) {
            ActionItem item1 = new ActionItem(i + 1, sorts[i]);
            secondshooterSortQuickAction.addActionItem(item1);
        }


        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(LookingSecondShooterActivity.this, toolbarLayoutFragment);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        initData(false);


        secondshooterSortQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                ActionItem actionItem = secondshooterSortQuickAction.getActionItem(pos);
                sortTextView.setText(actionItem.getTitle());

                if (pos == 0) {

                    if (isTop) {
                        initData(true);
                    } else {
                        initData(false);
                    }
                } else {

                    if (isTop) {
                        sortShooters(actionItem.getTitle(),true);
                    } else {
                        sortShooters(actionItem.getTitle(),false);
                    }

                }
            }
        });
        sortTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondshooterSortQuickAction.show(v);
                secondshooterSortQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
            }
        });

        allShooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondShooterAdapter = new SecondShooterAdapter(LookingSecondShooterActivity.this, allShooterList);
                recyclerView.setAdapter(secondShooterAdapter);
                isTop = false;
            }
        });
        topShooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondShooterAdapter = new SecondShooterAdapter(LookingSecondShooterActivity.this, topShooterList);
                recyclerView.setAdapter(secondShooterAdapter);
                isTop = true;
            }
        });

    }


    private void initData(final boolean isTop) {
        final ProgressDialog progressDialog = new ProgressDialog(LookingSecondShooterActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        try {
            (Api.getClient().getSecondShooter()).enqueue(new Callback<ShootersPOJO>() {

                @Override
                public void onResponse(Call<ShootersPOJO> call, Response<ShootersPOJO> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            allShooterList.clear();
                            topShooterList.clear();
                            ShootersPOJO shootersPOJO = response.body();
                            allShooterList = shootersPOJO.getAssistantSecondShooterPOJOS();
                            topShooterList = shootersPOJO.getTopSecondShooterPOJOS();
                            if (isTop) {
                                secondShooterAdapter = new SecondShooterAdapter(LookingSecondShooterActivity.this, topShooterList);
                            } else {
                                secondShooterAdapter = new SecondShooterAdapter(LookingSecondShooterActivity.this, allShooterList);
                            }
                            recyclerView.setAdapter(secondShooterAdapter);
                        } else {
                            Toast.makeText(LookingSecondShooterActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(LookingSecondShooterActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ShootersPOJO> call, Throwable t) {
                    Toast.makeText(LookingSecondShooterActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void sortShooters(String level,final  boolean isTop) {
        final ProgressDialog progressDialog = new ProgressDialog(LookingSecondShooterActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        try {
            (Api.getClient().getSecondShooter(level)).enqueue(new Callback<ShootersPOJO>() {

                @Override
                public void onResponse(Call<ShootersPOJO> call, Response<ShootersPOJO> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            allShooterList.clear();
                            topShooterList.clear();
                            ShootersPOJO shootersPOJO = response.body();
                            allShooterList = shootersPOJO.getAssistantSecondShooterPOJOS();
                            topShooterList = shootersPOJO.getTopSecondShooterPOJOS();
                            if (isTop) {
                                secondShooterAdapter = new SecondShooterAdapter(LookingSecondShooterActivity.this, topShooterList);
                            } else {
                                secondShooterAdapter = new SecondShooterAdapter(LookingSecondShooterActivity.this, allShooterList);
                            }
                            recyclerView.setAdapter(secondShooterAdapter);
                        } else {
                            Toast.makeText(LookingSecondShooterActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(LookingSecondShooterActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ShootersPOJO> call, Throwable t) {
                    Toast.makeText(LookingSecondShooterActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
