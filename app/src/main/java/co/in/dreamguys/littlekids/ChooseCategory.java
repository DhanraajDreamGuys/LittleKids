package co.in.dreamguys.littlekids;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.widget.ListView;

import co.in.dreamguys.littlekids.Adapter.CategoryAdapter;
import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.Model.CategoryResponse;
import co.in.dreamguys.littlekids.Network.GitHubClient;
import co.in.dreamguys.littlekids.RealmModel.Category;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user5 on 13-09-2017.
 */

public class ChooseCategory extends LittleKidsActivity {

    String langid, last_updated_time = "";
    private Realm realm;
    private static final String TAG = ChooseCategory.class.getSimpleName();
    CategoryAdapter aCategoryAdapter;
    ListView categoryWidgets;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setupWindowAnimations();
        realm = Realm.getDefaultInstance();
        langid = getIntent().getStringExtra(Config.LANG_ID);

        loadUI();

        RealmResults<Category> mCategoryResponse = realm.where(Category.class).findAll();
        long max = realm.where(Category.class).count();
        Category value = realm.where(Category.class).equalTo("category_id", String.valueOf(max)).findFirst();

        if (!Utility.isNetworkAvailable(ChooseCategory.this)) {
            if (mCategoryResponse.size() > 0) {
                showCategoryListData(mCategoryResponse);
            }
        } else if (value != null) {
            getCategory(langid, value.getLast_updated_time());
        } else {
            getCategory(langid, "0");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);

       /* Explode mExplode = new Explode();
        mExplode.setDuration(1000);
        getWindow().setReenterTransition(mExplode);*/
    }

    private void loadUI() {
        categoryWidgets = (ListView) findViewById(R.id.CC_LV_category);
    }

    private void showCategoryListData(RealmResults<Category> mCategoryResponse) {
        aCategoryAdapter = new CategoryAdapter();
        aCategoryAdapter.setClassInstance(ChooseCategory.this);
        aCategoryAdapter.setData(mCategoryResponse);
        categoryWidgets.setAdapter(aCategoryAdapter);
    }


    private void getCategory(String langid, String last_update_time) {
        Subscription subscription = GitHubClient.getInstance()
                .getCategoryData(langid, last_update_time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                        RealmResults<Category> mCategoryResponse = realm.where(Category.class).findAll();
                        if (mCategoryResponse.size() == 0) {
                            Utility.showAlertDialog(ChooseCategory.this, getString(R.string.no_data_found), getString(R.string.alert_warning));
                        } else {
                            showCategoryListData(mCategoryResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onNext(CategoryResponse categoryResponse) {
                        if (categoryResponse.getResponse().getResponse_code().equalsIgnoreCase("1")) {
                            realm.beginTransaction();
                            for (int i = 0; i < categoryResponse.getCategories().size(); i++) {
                                Category updateCategory = realm.where(Category.class).equalTo("category_id", categoryResponse.getCategories().get(i).getCategory_id()).findFirst();
                                if (updateCategory != null) {
                                    updateCategory.setCategory_id(categoryResponse.getCategories().get(i).getCategory_id());
                                    updateCategory.setCategory_name(categoryResponse.getCategories().get(i).getCategory_name());
                                    updateCategory.setLast_updated_time(categoryResponse.getLast_updated_time());
                                } else {
                                    Category addCategories = realm.createObject(Category.class);
                                    addCategories.setCategory_id(categoryResponse.getCategories().get(i).getCategory_id());
                                    addCategories.setCategory_name(categoryResponse.getCategories().get(i).getCategory_name());
                                    addCategories.setLast_updated_time(categoryResponse.getLast_updated_time());
                                }
                            }
                            realm.commitTransaction();
                        } else {
                            Log.i(TAG, categoryResponse.getResponse().getResponse_message());
                        }
                    }

                });
    }
}
