package tmnt.example.onedaily.api;

import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tmnt on 2017/4/12.
 */
public class Api {

    private static final int DEFAULT_TIMEOUT = 10;

    private static Api ourInstance=new Api();


    private String baseUrl;

    private static final String TAG = "Api";

    public static Api getInstance() {
        return ourInstance;

    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private Api() {
    }

    private OkHttpClient getOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }

    private Retrofit createRetrofit(String baseUrl) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkhttp())
                .build();
        return retrofit;
    }

    public <T> T getCall(String baseUrl,Class<T> clazz) {
        Retrofit retrofit = createRetrofit(baseUrl);
        return retrofit.create(clazz);
    }

}
