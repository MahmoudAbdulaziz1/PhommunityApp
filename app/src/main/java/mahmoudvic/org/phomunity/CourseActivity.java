package mahmoudvic.org.phomunity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.CoursesAdapter;
import mahmoudvic.org.phomunity.pojo.CoursePOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private CoursesAdapter coursesAdapter;
    private RecyclerView courseRecyclerView;
    List<CoursePOJO> coursePOJOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
//        Calligrapher calligrapher = new Calligrapher(this);
//        calligrapher.setFont(this, "fonts/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(CourseActivity.this, toolbarLayoutFragment);


        courseRecyclerView = (RecyclerView) findViewById(R.id.course_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        courseRecyclerView.setLayoutManager(mLayoutManager);
        courseRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initData();


    }


    private void initData() {
        final ProgressDialog progressDialog = new ProgressDialog(CourseActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        try {
            Log.d("Before API", "Before APIIIIIIIII");
            (Api.getClient().getCourses()).enqueue(new Callback<List<CoursePOJO>>() {
                @Override
                public void onResponse(Call<List<CoursePOJO>> call, Response<List<CoursePOJO>> response) {

                    if (response != null && response.isSuccessful()) {
                        if (response.body() != null) {

                            coursePOJOS = response.body();
                            coursePOJOS.add(0, new CoursePOJO());
                            coursesAdapter = new CoursesAdapter(CourseActivity.this, coursePOJOS);
                            courseRecyclerView.setAdapter(coursesAdapter);
                        } else {
                            Toast.makeText(CourseActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                        }
                    } else {

                        Toast.makeText(CourseActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                    }


                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<CoursePOJO>> call, Throwable t) {
                    Toast.makeText(CourseActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
