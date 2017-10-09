package co.in.dreamguys.littlekids;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.ListView;

import co.in.dreamguys.littlekids.Adapter.LanguageAdapter;
import co.in.dreamguys.littlekids.RealmModel.LanguageInfo;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by user5 on 11-09-2017.
 */

public class ChooseLanguage extends LittleKidsActivity {

    private ListView mLanguageList;
    RealmResults<LanguageInfo> languages;
    LanguageAdapter languageAdapter;
    MediaPlayer mp;
    private Handler handler, handler1;
    private ImageView inputSun1, inputCloud, inputCloud1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        setupWindowAnimations();
        initWidgets();
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            languages = realm.where(LanguageInfo.class).findAll();
            if (languages.size() > 0) {
                languageAdapter = new LanguageAdapter();
                languageAdapter.setClassInstance(ChooseLanguage.this);
                languageAdapter.setData(languages);
                mLanguageList.setAdapter(languageAdapter);
            }
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        /*Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);*/

        Explode mExplode = new Explode();
        mExplode.setDuration(1000);
        getWindow().setReenterTransition(mExplode);
    }


    public void initWidgets() {
        mLanguageList = (ListView) findViewById(R.id.AC_LV_category);
        inputSun1 = (ImageView) findViewById(R.id.AB_IV_sun1);
        inputCloud = (ImageView) findViewById(R.id.AB_IV_cloud);
        inputCloud1 = (ImageView) findViewById(R.id.AB_IV_cloud1);
        handler = new Handler();
        handler1 = new Handler();
        Utility.handler(ChooseLanguage.this, inputSun1, 13000, handler);
        Utility.MoveImage(ChooseLanguage.this, inputCloud, 5000, handler);
        Utility.MoveImage1(ChooseLanguage.this, inputSun1, 14000, handler1);
        Utility.MoveImage(ChooseLanguage.this, inputCloud1, 10000, handler);
    }

    public void getMediaPlayInstance(MediaPlayer mp) {
        this.mp = mp;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()) {
            mp.stop();
        }
    }


}
