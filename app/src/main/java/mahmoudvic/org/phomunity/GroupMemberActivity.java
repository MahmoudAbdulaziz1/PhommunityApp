package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.GroupMemberAdpater;
import mahmoudvic.org.phomunity.pojo.GroupPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupMemberActivity extends AppCompatActivity {

    private RecyclerView groupMemberRecyclerView;
    private TextView groupMemberTitle;
    private TextView groupMemberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);

        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(GroupMemberActivity.this, toolbarLayoutFragment);



        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", 0);
        groupMemberRecyclerView = (RecyclerView) findViewById(R.id.group_member_recycler);
        groupMemberTitle=findViewById(R.id.group_member_title);
        groupMemberText=findViewById(R.id.group_member_txt);
        groupMemberRecyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        groupMemberRecyclerView.setLayoutManager(linearLayoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(GroupMemberActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        try {
            (Api.getClient().getPosts(groupId)).enqueue(new Callback<List<GroupPOJO>>() {

                @Override
                public void onResponse(Call<List<GroupPOJO>> call, Response<List<GroupPOJO>> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {

                            GroupPOJO groupPOJO = response.body().get(0);
                            groupMemberTitle.setText(groupPOJO.getName());
                            final Typeface quickSand = Typeface.createFromAsset(GroupMemberActivity.this.getAssets(), "font/Quicksand-Bold.ttf");
                            groupMemberTitle.setTypeface(quickSand);
                            groupMemberText.setTypeface(quickSand);
                            GroupMemberAdpater groupMemberAdpater = new GroupMemberAdpater(GroupMemberActivity.this, groupPOJO.getUserPOJOS());
                            groupMemberRecyclerView.setAdapter(groupMemberAdpater);


                        } else {
                            Toast.makeText(GroupMemberActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(GroupMemberActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<GroupPOJO>> call, Throwable t) {
                    Toast.makeText(GroupMemberActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
