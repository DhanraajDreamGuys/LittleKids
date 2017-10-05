package co.in.dreamguys.littlekids;

import android.os.Bundle;
import android.support.annotation.Nullable;

import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.RealmModel.CategoryItems;
import co.in.dreamguys.littlekids.RealmModel.SectionLists;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Prasad on 10/5/2017.
 */

public class LearningAct extends LittleKidsActivity {
    CategoryItems fetchCategoryItems;
    String section_id;
    private Realm realm;
    private static final String TAG = LearningAct.class.getSimpleName();
    RealmList<SectionLists> sectionLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        realm = Realm.getDefaultInstance();

        section_id = getIntent().getStringExtra(Config.SECTION_ID);

        fetchCategoryItems = realm.where(CategoryItems.class).equalTo(Config.DB_SECTION_ID, section_id).findFirst();

        if (fetchCategoryItems != null)
            if (fetchCategoryItems.getSectionList().size() > 0) {
                sectionLists = fetchCategoryItems.getSectionList();

            } else {
                Utility.PassAlertPAct(LearningAct.this, getString(R.string.no_data_found));
            }


    }


}
