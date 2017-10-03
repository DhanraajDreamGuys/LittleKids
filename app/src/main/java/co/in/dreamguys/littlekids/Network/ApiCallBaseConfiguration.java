package co.in.dreamguys.littlekids.Network;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import co.in.dreamguys.littlekids.Helper.Config;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by tech on 8/13/2015.
 */
public class ApiCallBaseConfiguration {
    private static ApiCallBaseConfiguration ourInstance;
    RestAdapter ApiBuilder;

    /*BASE URL place it here  -  live url*/

    public static ApiCallBaseConfiguration getInstance() {
        if (ourInstance == null) {
            synchronized (ApiCallBaseConfiguration.class) {
                if (ourInstance == null)
                    ourInstance = new ApiCallBaseConfiguration();
            }
        }
        ourInstance.config();
        return ourInstance;
    }

    private ApiCallBaseConfiguration() {
    }

    private void config() {

        OkHttpClient mOkHttp = new OkHttpClient();
        mOkHttp.setReadTimeout(150, TimeUnit.SECONDS);
        mOkHttp.setConnectTimeout(150, TimeUnit.SECONDS);
        ApiBuilder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(mOkHttp))
                .setEndpoint(Config.Base_URL)
                .build();
    }

    public RestAdapter getApiBuilder() {
        return ApiBuilder;
    }

    public void setApiBuilder(RestAdapter apiBuilder) {
        ApiBuilder = apiBuilder;
    }
}
