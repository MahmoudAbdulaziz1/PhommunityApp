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
import mahmoudvic.org.phomunity.adapters.VideosAdapter;
import mahmoudvic.org.phomunity.pojo.VideoPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosActivity extends AppCompatActivity {

private RecyclerView videoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(VideosActivity.this, toolbarLayoutFragment);



        videoRecyclerView =(RecyclerView)findViewById(R.id.list);
        videoRecyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoRecyclerView.setLayoutManager(linearLayoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(VideosActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        try {
            (Api.getClient().getVideos()).enqueue(new Callback<List<VideoPOJO>>() {

                @Override
                public void onResponse(Call<List<VideoPOJO>> call, Response<List<VideoPOJO>> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {

                            List<VideoPOJO> videoPOJOS  = response.body();
                            videoPOJOS.add(0, new VideoPOJO());
                            VideosAdapter videosAdapter = new VideosAdapter(VideosActivity.this, videoPOJOS);
                            videoRecyclerView.setAdapter(videosAdapter);


                        } else {
                            Toast.makeText(VideosActivity.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(VideosActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<VideoPOJO>> call, Throwable t) {
                    Toast.makeText(VideosActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
