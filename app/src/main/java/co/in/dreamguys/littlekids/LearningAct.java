package co.in.dreamguys.littlekids;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import co.in.dreamguys.littlekids.Adapter.AdapterLearningAct;
import co.in.dreamguys.littlekids.Custom.CarouselEffectTransformer;
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
    private ViewPager viewpagerTop;
    public static final int ADAPTER_TYPE_TOP = 1;
    Animation anim_Fadein;
    private Handler handler, handler1;
    private ImageView inputSun1, inputCloud;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        loadUI();
        realm = Realm.getDefaultInstance();
        section_id = getIntent().getStringExtra(Config.SECTION_ID);
        fetchCategoryItems = realm.where(CategoryItems.class).equalTo(Config.DB_SECTION_ID, section_id).findFirst();
        anim_Fadein = AnimationUtils.loadAnimation(LearningAct.this, R.anim.fade_in);


        if (fetchCategoryItems != null)
            if (fetchCategoryItems.getSectionList().size() > 0) {
                sectionLists = fetchCategoryItems.getSectionList();
                AdapterLearningAct adapter = new AdapterLearningAct(this, sectionLists, ADAPTER_TYPE_TOP);
                viewpagerTop.setAdapter(adapter);
                viewpagerTop.startAnimation(anim_Fadein);
            } else {
                Utility.PassAlertPAct(LearningAct.this, getString(R.string.no_data_found));
            }


    }

    private void loadUI() {
        viewpagerTop = (ViewPager) findViewById(R.id.viewpagerTop);
        viewpagerTop.setClipChildren(false);
        viewpagerTop.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.pager_margin));
        viewpagerTop.setOffscreenPageLimit(3);
        viewpagerTop.setPageTransformer(false, new CarouselEffectTransformer(this)); // Set transformer

      /*  inputSun1 = (ImageView) findViewById(R.id.AB_IV_sun1);
        inputCloud = (ImageView) findViewById(R.id.AB_IV_cloud);
        handler = new Handler();
        handler1 = new Handler();
        Utility.handler(LearningAct.this, inputSun1, 13000, handler);
        Utility.MoveImage(LearningAct.this, inputCloud, 5000, handler);
        Utility.MoveImage1(LearningAct.this, inputSun1, 14000, handler1);*/
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

}
