package co.in.dreamguys.littlekids;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.Helper.SessionHandler;
import co.in.dreamguys.littlekids.Model.LanguageResponse;
import co.in.dreamguys.littlekids.Network.LanguageAPI;
import co.in.dreamguys.littlekids.RealmModel.LanguageInfo;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user5 on 12-09-2017.
 */

public class SplashScreen extends AppCompatActivity {

    Handler mHandler;
    private Realm realm;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        realm = Realm.getDefaultInstance();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                languageResponse();
                Intent FilterActivity = new Intent(SplashScreen.this, LanguageActivity.class);
                startActivity(FilterActivity);
                finish();
            }
        }, 5000);
    }

    public void languageResponse() {
        if (Utility.isNetworkAvailable(SplashScreen.this)) {
            LanguageAPI.getInstance().Callresponse(Config.Lang_Last_Updated_time, new Callback<LanguageResponse>() {
                @Override
                public void success(final LanguageResponse languageResponse, Response response) {
                    if (languageResponse.getResponse().getResponse_code().equalsIgnoreCase("1")) {
                        SessionHandler.getInstance().save(SplashScreen.this, Config.Lang_Last_Updated_time, languageResponse.getResponse().getLast_updated());
                        LanguageInfo languageinfo;
                        realm.beginTransaction();
                        RealmResults<LanguageInfo> languageInfos = realm.where(LanguageInfo.class).findAll();
                        languageInfos.deleteAllFromRealm();
                        for (int i = 0; i < languageResponse.getLanguageInfo().size(); i++) {
                            languageinfo = realm.createObject(LanguageInfo.class);
                            languageinfo.setLang_id(languageResponse.getLanguageInfo().get(i).getLang_id());
                            languageinfo.setLang(languageResponse.getLanguageInfo().get(i).getLang());
                            Log.d("TAG", languageinfo.getLang_id() + languageinfo.getLang());
                        }
                        realm.commitTransaction();
                        Toast.makeText(SplashScreen.this, languageResponse.getResponse().getResponse_message(), Toast.LENGTH_SHORT).show();
                    } else if (languageResponse.getResponse().getResponse_code().equalsIgnoreCase("2")) {
                        Toast.makeText(SplashScreen.this, languageResponse.getResponse().getResponse_message(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SplashScreen.this, languageResponse.getResponse().getResponse_message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } else {
            Toast.makeText(this, "Plaese enable internet connection and try again...", Toast.LENGTH_SHORT).show();
        }
    }

}
