package ru.naviwork.compass.auth.data.source.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.naviwork.compass.Const;
import ru.naviwork.compass.auth.data.User;
import ru.naviwork.compass.auth.util.AuthUtils;

import static androidx.core.util.Preconditions.checkNotNull;

public class CompassRestClient {

    private final static HttpUrl httpUrl = checkNotNull(HttpUrl.parse(Const.BASE_URL + Const.REST_URL));

    private static volatile CompassRestClient instance;

    private CompassApi apiService;

    private CompassRestClient() {

        OkHttpClient.Builder
                httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " + AuthUtils.getToken())
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        httpClient.followRedirects(false);

        OkHttpClient client = httpClient.build();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(CompassApi.class);
    }

    /**
     * Get default instance of {@code CompassRestClient}.
     *
     * @return an instance of CompassRestClient
     */
    public static CompassRestClient getInstance() {
        CompassRestClient localInstance = instance;
        if (localInstance == null) {
            synchronized (CompassRestClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CompassRestClient();
                }
            }
        }
        return localInstance;
    }

    public void getCurrentUserAsync(Callback<User> callback) {
        apiService.getCurrentUser().enqueue(callback);
    }

    public User getCurrentUserSync() throws IOException {
        return apiService.getCurrentUser().execute().body();
    }
}