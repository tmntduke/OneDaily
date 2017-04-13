package tmnt.example.onedaily.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tmnt on 2017/4/12.
 */
public class Api {

    private static final int DEFAULT_TIMEOUT = 10;

    private static Api ourInstance;

    private static Retrofit retrofit;

    public static Api getInstance(String baseUrl) {
        if (ourInstance == null) {
            ourInstance = new Api(baseUrl);
        }
        return ourInstance;

    }

    private Api(String baseUrl) {

        createRetrofit(baseUrl);
    }

    private OkHttpClient getOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }

    private void createRetrofit(String baseUrl) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkhttp())
                .build();

    }

    public <T> T getCall(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
