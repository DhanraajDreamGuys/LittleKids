package co.in.dreamguys.littlekids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import co.in.dreamguys.littlekids.Adapter.LanguageAdapter;
import co.in.dreamguys.littlekids.RealmModel.LanguageInfo;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by user5 on 11-09-2017.
 */

public class LanguageActivity extends AppCompatActivity {

    private ListView mLanguageList;
    private Animation AnimBlink;
    RealmResults<LanguageInfo> users;

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        realm = Realm.getDefaultInstance();
        users = realm.where(LanguageInfo.class).findAll();
        initWidgets();
    }

    public void initWidgets() {
        AnimBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        mLanguageList = (ListView) findViewById(R.id.AC_LV_category);
        LanguageAdapter languageAdapter = new LanguageAdapter(this, users);
        mLanguageList.setAdapter(languageAdapter);


    }
}
