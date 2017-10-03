package co.in.dreamguys.littlekids.Network;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.Model.CategoryResponse;
import co.in.dreamguys.littlekids.Model.LanguageResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by chris on 6/1/16.
 */
public class GitHubClient {

//    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private static GitHubClient instance;
    private GitHubService gitHubService;

    private GitHubClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.Base_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    public static GitHubClient getInstance() {
        if (instance == null) {
            instance = new GitHubClient();
        }
        return instance;
    }

    public Observable<LanguageResponse> getLangaugeData(@NonNull String last_updated_time) {
        return gitHubService.getLangauge(last_updated_time);
    }

    public Observable<CategoryResponse> getCategoryData(@NonNull String lang_id, @NonNull String last_updated_time) {
        return gitHubService.getCategory(lang_id, last_updated_time);
    }

}
