package org.learning.lexitron;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://lexitron.herokuapp.com";
// private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit mRetrofit;

    private NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public UsersApiService getUsersJSONApi() {
        return mRetrofit.create(UsersApiService.class);
    }

    public SuffixApiService getSufJSONApi() {
        return mRetrofit.create(SuffixApiService.class);
    }

    public TextsApiService getTextsJSONApi() {
        return mRetrofit.create(TextsApiService.class);
    }
}
