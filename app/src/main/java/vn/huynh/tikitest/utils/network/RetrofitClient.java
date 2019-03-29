package vn.huynh.tikitest.utils.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duong on 3/28/2019.
 */

public class RetrofitClient {
    private static final int CONNECT_TIME_OUT = 60;
    private static final int READ_TIME_OUT = 60;
    private static final int WRITE_TIME_OUT = 60;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            initRetrofit(baseUrl);
        } else if (!retrofit.baseUrl().toString().equalsIgnoreCase(baseUrl)) {
            initRetrofit(baseUrl);
        }
        return retrofit;
    }

    private static void initRetrofit(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIME_OUT, TIME_UNIT);
        builder.writeTimeout(WRITE_TIME_OUT, TIME_UNIT);
        builder.connectTimeout(CONNECT_TIME_OUT, TIME_UNIT);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
