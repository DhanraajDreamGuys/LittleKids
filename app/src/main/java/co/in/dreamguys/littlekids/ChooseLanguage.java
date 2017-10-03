package co.in.dreamguys.littlekids;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.widget.ListView;

import co.in.dreamguys.littlekids.Adapter.LanguageAdapter;
import co.in.dreamguys.littlekids.RealmModel.LanguageInfo;
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
