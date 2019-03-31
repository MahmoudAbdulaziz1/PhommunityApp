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
import mahmoudvic.org.phomunity.adapters.TipAdapter;
import mahmoudvic.org.phomunity.pojo.TipPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipsActivity extends AppCompatActivity {

    private RecyclerView tipRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(TipsActivity.this, toolbarLayoutFragment);


        tipRecyclerView =(RecyclerView)findViewById(R.id.tips_recycler_view);
        tipRecyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tipRecyclerView.setLayoutManager(linearLayoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(TipsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        try {
            (Api.getClient().getTips()).enqueue(new Callback<List<TipPOJO>>() {

                @Override
                public void onResponse(Call<List<TipPOJO>> call, Response<List<TipPOJO>> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {

                            List<TipPOJO> tipPOJOS  = response.body();
                            tipPOJOS.add(0, new TipPOJO());
                            TipAdapter tipAdapter = new TipAdapter(TipsActivity.this, tipPOJOS);
                            tipRecyclerView.setAdapter(tipAdapter);


                        } else {
                            Toast.makeText(TipsActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(TipsActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<TipPOJO>> call, Throwable t) {
                    Toast.makeText(TipsActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
