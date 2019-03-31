package mahmoudvic.org.phomunity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.IsellAdapter;
import mahmoudvic.org.phomunity.drop_down.ActionItem;
import mahmoudvic.org.phomunity.drop_down.QuickAction;
import mahmoudvic.org.phomunity.pojo.BrandsPOJO;
import mahmoudvic.org.phomunity.pojo.CategoriesAndBrandsPOJO;
import mahmoudvic.org.phomunity.pojo.CategoriesPOJO;
import mahmoudvic.org.phomunity.pojo.SellImagesPOJO;
import mahmoudvic.org.phomunity.pojo.SellPOJO;
import mahmoudvic.org.phomunity.pojo.SellProductPOJO;
import mahmoudvic.org.phomunity.pojo.SellSignedProductsPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IsellMainActivity extends AppCompatActivity {

    private int image = R.drawable.camera_test;
    private ArrayList<String> signList = new ArrayList<>(Arrays.asList("New", "Offer", "Feature", "", "New", "Offer", "Feature", ""));
    private ArrayList<Integer> imageList = new ArrayList<>(Arrays.asList(R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test,
            R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test, R.drawable.camera_test));
    private ArrayList<String> cameraName = new ArrayList<>(Arrays.asList("Nikon", "Cannon", "Sony", "XXXX", "Nikon", "Cannon", "Sony", "XXXX"));
    private ArrayList<String> per24 = new ArrayList<>(Arrays.asList("200 LE (24)", "200 LE (24)", "200 LE (24)", "200 LE (24)",
            "200 LE (24)", "200 LE (24)", "200 LE (24)", "200 LE (24)"));
    private ArrayList<String> per12 = new ArrayList<>(Arrays.asList("100 LE (12)", "100 LE (12)", "100 LE (12)", "100 LE (12)",
            "100 LE (12)", "100 LE (12)", "12 LE (12)", "100 LE (12)"));
    private RecyclerView recyclerView;
    private TextView brandTxt;
    private TextView catTxt;
    private QuickAction mQuickAction;
    private QuickAction catQuickAction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isell_main);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(IsellMainActivity.this, toolbarLayoutFragment);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final List<Integer> catList = new ArrayList<>();
        final List<Integer> brandList = new ArrayList<>();
        brandList.add(0);
        catList.add(0);
        List<ActionItem> items = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.isell_recycle);
        recyclerView.addItemDecoration(new mahmoudvic.org.phomunity.adapters.DividerItemDecoration(2, 50, false));


        final ArrayList<Integer> id = new ArrayList<>();
        final ArrayList<String> name = new ArrayList<>();
        final ArrayList<String> desc = new ArrayList<>();
        final ArrayList<Double> price = new ArrayList<>();
        final ArrayList<List<String>> phone = new ArrayList<>();
        final ArrayList<String> sign = new ArrayList<>();
        final ArrayList<List<String>> branchs = new ArrayList<>();
        final ArrayList<Integer> cat_id = new ArrayList<>();
        final ArrayList<Integer> brand_id = new ArrayList<>();
        final ArrayList<Integer> verified = new ArrayList<>();
        final ArrayList<Integer> available = new ArrayList<>();
        final ArrayList<String> cat_name = new ArrayList<>();
        final ArrayList<String> brand_name = new ArrayList<>();
        final ArrayList<String> video_url = new ArrayList<>();
        final ArrayList<List<SellImagesPOJO>> images = new ArrayList<>();


        Api.getClient().getCtaegoriesAndBrands().enqueue(new Callback<CategoriesAndBrandsPOJO>() {
            @Override
            public void onResponse(Call<CategoriesAndBrandsPOJO> call, Response<CategoriesAndBrandsPOJO> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            final CategoriesAndBrandsPOJO res = response.body();
                            final List<CategoriesPOJO> cat = res.getCategories();
                            final List<BrandsPOJO> brand = res.getBrands();

                            id.clear();
                            name.clear();
                            desc.clear();
                            price.clear();
                            phone.clear();
                            sign.clear();
                            branchs.clear();
                            cat_id.clear();
                            brand_id.clear();
                            verified.clear();
                            available.clear();
                            cat_name.clear();
                            brand_name.clear();
                            video_url.clear();
                            images.clear();
                            mQuickAction = new QuickAction(getBaseContext(), QuickAction.VERTICAL);
                            catQuickAction = new QuickAction(getBaseContext(), QuickAction.VERTICAL);

                            ActionItem b_item0 = new ActionItem(0, "Brand");
                            mQuickAction.addActionItem(b_item0);
                            for (int i = 0; i < brand.size(); i++) {
                                brandList.add(brand.get(i).getId());
                                ActionItem item1 = new ActionItem(i + 1, brand.get(i).getName());
                                mQuickAction.addActionItem(item1);
                            }


                            mQuickAction
                                    .setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
                                        @Override
                                        public void onItemClick(QuickAction source, int pos, int actionId) {

                                            ActionItem actionItem = mQuickAction.getActionItem(pos);
                                            brandTxt.setText(actionItem.getTitle());

                                            Api.getClient().displayCameraSellByBrand(brandList.get(actionId)).enqueue(new Callback<SellPOJO>() {
                                                @Override
                                                public void onResponse(Call<SellPOJO> call, Response<SellPOJO> response) {
                                                    if (response.isSuccessful()) {
                                                        if (response.body() != null) {
                                                            try {
                                                                if (response.body().getSignedProducts() != null || response.body().getSignedProducts().size() > 0) {
                                                                    List<SellSignedProductsPOJO> pro = response.body().getSignedProducts();
                                                                    Log.d("brand str", pro.size() + " aaaaaa");
                                                                    id.clear();
                                                                    name.clear();
                                                                    desc.clear();
                                                                    price.clear();
                                                                    phone.clear();
                                                                    sign.clear();
                                                                    branchs.clear();
                                                                    cat_id.clear();
                                                                    brand_id.clear();
                                                                    verified.clear();
                                                                    available.clear();
                                                                    cat_name.clear();
                                                                    brand_name.clear();
                                                                    video_url.clear();
                                                                    images.clear();
                                                                    for (SellSignedProductsPOJO pojo : pro) {
                                                                        id.add(pojo.getId());
                                                                        name.add(pojo.getName());
                                                                        desc.add(pojo.getDescription());
                                                                        price.add(pojo.getPrice());
                                                                        phone.add(pojo.getPhone_numbers());
                                                                        sign.add(pojo.getSign());
                                                                        branchs.add(pojo.getBranches());
                                                                        cat_id.add(pojo.getCategory_id());
                                                                        brand_id.add(pojo.getBrand_id());
                                                                        verified.add(pojo.getVerified());
                                                                        available.add(pojo.getAvailable());
                                                                        cat_name.add(pojo.getCategory().getName());
                                                                        brand_name.add(pojo.getBrand().getName());
                                                                        video_url.add(pojo.getVideo_url());
                                                                        images.add(pojo.getImages());
                                                                    }
                                                                }


                                                                if (response.body().getProducts() != null || response.body().getProducts().size() > 0) {
                                                                    List<SellProductPOJO> unPro = response.body().getProducts();
                                                                    Log.d("brand str", unPro.size() + "");
                                                                    for (SellProductPOJO pojo : unPro) {
                                                                        id.add(pojo.getId());
                                                                        try {
                                                                            name.add(pojo.getName() + "");
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        try {
                                                                            desc.add(pojo.getDescription() + "");
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        try {
                                                                            price.add(pojo.getPrice());
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        try {
                                                                            phone.add(pojo.getPhone_numbers());
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        sign.add(pojo.getSign() + "");
                                                                        try {
                                                                            branchs.add(pojo.getBranches());
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        cat_id.add(pojo.getCategory_id());
                                                                        brand_id.add(pojo.getBrand_id());
                                                                        verified.add(pojo.getVerified());
                                                                        available.add(pojo.getAvailable());
//                                                        if (pojo.getCategory().getName().equals("") || pojo.getCategory().getName().equals(null)) {
//                                                            cat_name.add("");
//                                                        } else {
                                                                        try {
                                                                            cat_name.add(pojo.getCategory().getName() + "");
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        //}
                                                                        //cat_name.add(pojo.getCategory().getName());
                                                                        try {
                                                                            brand_name.add(pojo.getBrand().getName() + "");
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        try {
                                                                            video_url.add(pojo.getVideo_url());
                                                                        } catch (NullPointerException e) {

                                                                        }
                                                                        try {
                                                                            images.add(pojo.getImages());
                                                                        } catch (NullPointerException e) {

                                                                        }

                                                                    }
                                                                }

                                                                GridLayoutManager manager = new GridLayoutManager(IsellMainActivity.this, 2);
                                                                recyclerView.setLayoutManager(manager);
                                                                IsellAdapter adapter = new IsellAdapter(IsellMainActivity.this, id, name, desc, price, phone, sign
                                                                        , branchs, cat_id, brand_id, verified, available, cat_name, brand_name, video_url, images, image, 1, IsellMainActivity.this);
                                                                recyclerView.setAdapter(adapter);
                                                            } catch (Exception e) {
                                                            }
                                                        } else {
                                                            Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<SellPOJO> call, Throwable t) {
                                                    Toast.makeText(IsellMainActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    });


                            ActionItem item0 = new ActionItem(0, "Category");
                            catQuickAction.addActionItem(item0);
                            for (int j = 0; j < cat.size(); j++) {
                                catList.add(cat.get(j).getId());
                                ActionItem item1 = new ActionItem(j + 1, cat.get(j).getName());
                                catQuickAction.addActionItem(item1);
                            }

                            catQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
                                @Override
                                public void onItemClick(QuickAction source, int pos, int actionId) {
                                    ActionItem actionItem = catQuickAction.getActionItem(pos);
                                    catTxt.setText(actionItem.getTitle());
//                                Toast.makeText(getApplicationContext(), catList.get(actionId) + " selected", Toast.LENGTH_SHORT).show();
                                    Api.getClient().displayCameraSellByCategory(catList.get(actionId)).enqueue(new Callback<SellPOJO>() {
                                        @Override
                                        public void onResponse(Call<SellPOJO> call, Response<SellPOJO> response) {
                                            if (response.isSuccessful()) {
                                                if (response.body() != null) {
                                                    try {
                                                        if (response.body().getSignedProducts() != null || response.body().getSignedProducts().size() > 0) {
                                                            List<SellSignedProductsPOJO> pro = response.body().getSignedProducts();
                                                            Log.d("cat", pro.size() + "  asd");
                                                            id.clear();
                                                            name.clear();
                                                            desc.clear();
                                                            price.clear();
                                                            phone.clear();
                                                            sign.clear();
                                                            branchs.clear();
                                                            cat_id.clear();
                                                            brand_id.clear();
                                                            verified.clear();
                                                            available.clear();
                                                            cat_name.clear();
                                                            brand_name.clear();
                                                            video_url.clear();
                                                            images.clear();
                                                            for (SellSignedProductsPOJO pojo : pro) {
                                                                id.add(pojo.getId());
                                                                try {
                                                                    name.add(pojo.getName() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    desc.add(pojo.getDescription() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    price.add(pojo.getPrice());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    phone.add(pojo.getPhone_numbers());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                sign.add(pojo.getSign() + "");
                                                                try {
                                                                    branchs.add(pojo.getBranches());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                cat_id.add(pojo.getCategory_id());
                                                                brand_id.add(pojo.getBrand_id());
                                                                verified.add(pojo.getVerified());
                                                                available.add(pojo.getAvailable());
//                                                        if (pojo.getCategory().getName().equals("") || pojo.getCategory().getName().equals(null)) {
//                                                            cat_name.add("");
//                                                        } else {
                                                                try {
                                                                    cat_name.add(pojo.getCategory().getName() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                //}
                                                                //cat_name.add(pojo.getCategory().getName());
                                                                try {
                                                                    brand_name.add(pojo.getBrand().getName() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    video_url.add(pojo.getVideo_url());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    images.add(pojo.getImages());
                                                                } catch (NullPointerException e) {

                                                                }

                                                            }
                                                        }


                                                        if (response.body().getProducts() != null || response.body().getProducts().size() > 0) {
                                                            List<SellProductPOJO> unPro = response.body().getProducts();
                                                            for (SellProductPOJO pojo : unPro) {
                                                                id.add(pojo.getId());
                                                                try {
                                                                    name.add(pojo.getName() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    desc.add(pojo.getDescription() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    price.add(pojo.getPrice());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    phone.add(pojo.getPhone_numbers());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                sign.add(pojo.getSign() + "");
                                                                try {
                                                                    branchs.add(pojo.getBranches());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                cat_id.add(pojo.getCategory_id());
                                                                brand_id.add(pojo.getBrand_id());
                                                                verified.add(pojo.getVerified());
                                                                available.add(pojo.getAvailable());
//                                                        if (pojo.getCategory().getName().equals("") || pojo.getCategory().getName().equals(null)) {
//                                                            cat_name.add("");
//                                                        } else {
                                                                try {
                                                                    cat_name.add(pojo.getCategory().getName() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                //}
                                                                //cat_name.add(pojo.getCategory().getName());
                                                                try {
                                                                    brand_name.add(pojo.getBrand().getName() + "");
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    video_url.add(pojo.getVideo_url());
                                                                } catch (NullPointerException e) {

                                                                }
                                                                try {
                                                                    images.add(pojo.getImages());
                                                                } catch (NullPointerException e) {

                                                                }

                                                            }
                                                        }
                                                        //recyclerView = (RecyclerView) findViewById(R.id.isell_recycle);
                                                        GridLayoutManager manager = new GridLayoutManager(IsellMainActivity.this, 2);
                                                        recyclerView.setLayoutManager(manager);
                                                        //recyclerView.addItemDecoration(new mahmoudvic.org.phomunity.adapters.DividerItemDecoration(2, 50, false));
                                                        IsellAdapter adapter = new IsellAdapter(IsellMainActivity.this, id, name, desc, price, phone, sign
                                                                , branchs, cat_id, brand_id, verified, available, cat_name, brand_name, video_url, images, image, 1, IsellMainActivity.this);
                                                        recyclerView.setAdapter(adapter);
                                                    } catch (Exception e) {

                                                    }
                                                } else {
                                                    Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<SellPOJO> call, Throwable t) {
                                            Toast.makeText(IsellMainActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            });
                            Log.d("true", "good");
                        } catch (Exception e) {

                        }

                    } else {
                        Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Log.d("false", "bad");
                    Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoriesAndBrandsPOJO> call, Throwable t) {
                Toast.makeText(IsellMainActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

        brandTxt = (TextView) findViewById(R.id.isell_brand_edit_text);
        brandTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickAction.show(v);
                mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
            }
        });
        catTxt = (TextView) findViewById(R.id.isell_cat_edit_text);
        catTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catQuickAction.show(v);
            }
        });


        if (brandTxt.getText().toString().trim().equals("Brand") && catTxt.getText().toString().trim().equals("Category")) {
            Log.d("test", brandTxt.getText().toString());
            Api.getClient().displayCameraSell().enqueue(new Callback<SellPOJO>() {
                @Override
                public void onResponse(Call<SellPOJO> call, Response<SellPOJO> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            try {
                                //Log.d("response", response.body().getSignedProducts().get(0).getName());
                                if (response.body().getSignedProducts() != null || response.body().getSignedProducts().size() > 0) {
                                    List<SellSignedProductsPOJO> pro = response.body().getSignedProducts();

                                    for (SellSignedProductsPOJO pojo : pro) {
                                        id.add(pojo.getId());
                                        name.add(pojo.getName() + "");
                                        desc.add(pojo.getDescription() + "");
                                        price.add(pojo.getPrice());
                                        if (pojo.getPhone_numbers() != null) {
                                            phone.add(pojo.getPhone_numbers());
                                        }
                                        //Log.d("p", pojo.getPhone_numbers() + " ");
                                        sign.add(pojo.getSign() + "");
                                        if (pojo.getBranches() != null) {
                                            branchs.add(pojo.getBranches());
                                        }
                                        cat_id.add(pojo.getCategory_id());
                                        brand_id.add(pojo.getBrand_id());
                                        verified.add(pojo.getVerified());
                                        available.add(pojo.getAvailable());
                                        try {
                                            cat_name.add(pojo.getCategory().getName() + "");
                                        } catch (NullPointerException e) {
                                            cat_name.add("");
                                        }
                                        brand_name.add(pojo.getBrand().getName() + "");
                                        video_url.add(pojo.getVideo_url() + "");
                                        if (pojo.getImages() != null) {
                                            images.add(pojo.getImages());
                                        }
                                    }
                                }


                                if (response.body().getProducts() != null || response.body().getProducts().size() > 0) {
                                    List<SellProductPOJO> unPro = response.body().getProducts();
                                    for (SellProductPOJO pojo : unPro) {
                                        id.add(pojo.getId());
                                        name.add(pojo.getName() + "");
                                        desc.add(pojo.getDescription() + "");
                                        price.add(pojo.getPrice());
                                        if (pojo.getPhone_numbers() != null) {
                                            phone.add(pojo.getPhone_numbers());
                                        }
                                        //Log.d("p", pojo.getPhone_numbers() + " ");
                                        sign.add(pojo.getSign() + "");
                                        if (pojo.getBranches() != null) {
                                            branchs.add(pojo.getBranches());
                                        }
                                        cat_id.add(pojo.getCategory_id());
                                        brand_id.add(pojo.getBrand_id());
                                        verified.add(pojo.getVerified());
                                        available.add(pojo.getAvailable());
                                        try {
                                            cat_name.add(pojo.getCategory().getName());
                                        } catch (NullPointerException e) {
                                            cat_name.add("");
                                        }


                                        try {
                                            brand_name.add(pojo.getBrand().getName());
                                        } catch (NullPointerException e) {
                                            brand_name.add("");
                                        }
                                        video_url.add(pojo.getVideo_url() + "");
                                        if (pojo.getImages() != null) {
                                            images.add(pojo.getImages());
                                        }
                                    }
                                }

                                //recyclerView = (RecyclerView) findViewById(R.id.isell_recycle);
                                GridLayoutManager manager = new GridLayoutManager(IsellMainActivity.this, 2);
                                recyclerView.setLayoutManager(manager);
                                //recyclerView.addItemDecoration(new mahmoudvic.org.phomunity.adapters.DividerItemDecoration(2, 50, false));
                                Log.d("p", phone.get(0) + " " + phone.size());
                                IsellAdapter adapter = new IsellAdapter(IsellMainActivity.this, id, name, desc, price, phone, sign
                                        , branchs, cat_id, brand_id, verified, available, cat_name, brand_name, video_url, images, image, 1, IsellMainActivity.this);
                                recyclerView.setAdapter(adapter);
                            } catch (Exception e) {

                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SellPOJO> call, Throwable t) {
                    Toast.makeText(IsellMainActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                }
            });
        }


    }


}