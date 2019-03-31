package mahmoudvic.org.phomunity.APIClass;

import android.os.Environment;

import java.io.File;

import mahmoudvic.org.phomunity.interfaces.ApiInterface;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    public static ApiInterface getClient() {

        // change your base URL
        if (retrofit==null) {
//            int cacheSize = 10 * 1024 * 1024; // 10 MB
//            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
//            Cache cache = new Cache(file, cacheSize);
//
//
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .cache(cache)
//                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://phommunity.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Creating object for our interface
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api; // return the APIInterface object
    }
}
