package mahmoudvic.org.phomunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.ImageSliderAdapter;
import mahmoudvic.org.phomunity.adapters.LabRentAdapter;
import mahmoudvic.org.phomunity.pojo.ImagePOJO;
import mahmoudvic.org.phomunity.pojo.LabsPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabActivity extends AppCompatActivity {
    private List<LabsOptionsPOJO> options = new ArrayList<>();
    private List<ImagePOJO> slides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(LabActivity.this, toolbarLayoutFragment);

        loadLabs();

    }

    private void loadLabs() {
        (Api.getClient().getLabs()).enqueue(new Callback<LabsPOJO>() {
            @Override
            public void onResponse(Call<LabsPOJO> call, Response<LabsPOJO> response) {
                if (response != null && response.isSuccessful()) {
                    if (response.body() != null) {
                        options = response.body().getOptions();
                        slides = response.body().getSlides();

                        loadSlider();
                        loadLabDetails();
                    } else {
                        Toast.makeText(LabActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(LabActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LabsPOJO> call, Throwable t) {
                Toast.makeText(LabActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadSlider() {
        RecyclerView recyclerView = findViewById(R.id.image_slider);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LabActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if (slides != null) {
            ImageSliderAdapter adapter = new ImageSliderAdapter(LabActivity.this, slides);
            recyclerView.setAdapter(adapter);
        } else {
            // show message to the user
        }


    }

    private void loadLabDetails() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.lab_rent_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        if (options != null) {
            LabRentAdapter adapter = new LabRentAdapter(LabActivity.this, options);
            recyclerView.setAdapter(adapter);
        } else {
            // show message to the user
        }


    }
}
