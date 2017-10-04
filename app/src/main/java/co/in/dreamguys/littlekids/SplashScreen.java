package co.in.dreamguys.littlekids;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;

import co.in.dreamguys.littlekids.Model.LanguageResponse;
import co.in.dreamguys.littlekids.Network.GitHubClient;
import co.in.dreamguys.littlekids.RealmModel.LanguageInfo;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user5 on 12-09-2017.
 */

public class SplashScreen extends AppCompatActivity {

    Handler mHandler;
    private Realm realm;
    private static final String TAG = SplashScreen.class.getSimpleName();

    private enum ResponseCode {
        EMPTY("0"), NEWDATA("1"), NOUPDATES("2");

        String value;

        ResponseCode(String value) {
            this.value = value;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        setupWindowAnimations();
        realm = Realm.getDefaultInstance();
        mHandler = new Handler();


        long max = realm.where(LanguageInfo.class).count();
        final LanguageInfo languages = realm.where(LanguageInfo.class).equalTo("lang_id", String.valueOf(max)).findFirst();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Utility.isNetworkAvailable(SplashScreen.this)) {
                    final RealmResults<LanguageInfo> mLangArray = realm.where(LanguageInfo.class).findAll();
                    if (mLangArray.size() == 0) {
                        Utility.showAlertDialog(SplashScreen.this, getString(R.string.no_internet_connection));
                    } else {
                        showLanguageActivity();
                    }
                } else if (languages != null) {
                    getLanguage(languages.getLast_updated_time());
                } else {
                    getLanguage("0");
                }

            }
        }, 5000);
    }

    private void getLanguage(String last_update_time) {
        Subscription subscription = GitHubClient.getInstance()
                .getLangaugeData(last_update_time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LanguageResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                        RealmResults<LanguageInfo> languageInfos = realm.where(LanguageInfo.class).findAll();
                        if (languageInfos.size() == 0) {
                            Utility.showAlertDialog(SplashScreen.this, getString(R.string.no_data_found));
                        } else {
                            showLanguageActivity();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onNext(LanguageResponse languageResponse) {
                        if (languageResponse.getResponse().getResponse_code().equalsIgnoreCase("1")) {
                            LanguageInfo languageinfo;
                            realm.beginTransaction();
                            RealmResults<LanguageInfo> languageInfos = realm.where(LanguageInfo.class).findAll();
                            languageInfos.deleteAllFromRealm();
                            for (int i = 0; i < languageResponse.getLanguageInfo().size(); i++) {
                                languageinfo = realm.createObject(LanguageInfo.class);
                                languageinfo.setLang_id(languageResponse.getLanguageInfo().get(i).getLang_id());
                                languageinfo.setLang(languageResponse.getLanguageInfo().get(i).getLang());
                                languageinfo.setLast_updated_time(languageResponse.getLast_updated_time());
                            }
                            realm.commitTransaction();
                        } else {
                            Log.i(TAG, languageResponse.getResponse().getResponse_message());
                        }
                    }
                });
    }

    private void showLanguageActivity() {
        Intent callLangAct = new Intent(SplashScreen.this, ChooseLanguage.class);
        startActivity(callLangAct);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(1000);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

}
