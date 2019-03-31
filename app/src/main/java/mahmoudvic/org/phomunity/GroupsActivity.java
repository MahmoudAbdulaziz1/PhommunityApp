package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.GroupsAdapter;
import mahmoudvic.org.phomunity.pojo.GroupPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupsActivity extends AppCompatActivity {


    private RecyclerView groupRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(GroupsActivity.this, toolbarLayoutFragment);


        groupRecyclerView = (RecyclerView) findViewById(R.id.groups_recycler_view);
        groupRecyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        groupRecyclerView.setLayoutManager(linearLayoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(GroupsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();


//        SharedPreferences rememberMePreference = getSharedPreferences("remember", MODE_PRIVATE);
//        SharedPreferences.Editor loginData = rememberMePreference.edit();
//        loginData.putInt("id", 104);
//        loginData.commit();

        try {
            (Api.getClient().getGroups(getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0))).enqueue(new Callback<List<GroupPOJO>>() {

                @Override
                public void onResponse(Call<List<GroupPOJO>> call, Response<List<GroupPOJO>> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {

                            List<GroupPOJO> groupPOJOS = response.body();

                            groupPOJOS.add(0, new GroupPOJO());
                            GroupsAdapter groupsAdapter = new GroupsAdapter(GroupsActivity.this, groupPOJOS);
                            groupRecyclerView.setAdapter(groupsAdapter);


                        } else {
                            Toast.makeText(GroupsActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(GroupsActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<GroupPOJO>> call, Throwable t) {
                    Toast.makeText(GroupsActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
