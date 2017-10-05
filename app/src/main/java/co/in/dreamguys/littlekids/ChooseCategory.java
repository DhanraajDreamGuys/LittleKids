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
import co.in.dreamguys.littlekids.RealmModel.Categories;
import co.in.dreamguys.littlekids.RealmModel.Category;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.Realm;
import io.realm.RealmList;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user5 on 13-09-2017.
 */

public class ChooseCategory extends LittleKidsActivity {

    String langid;
    private Realm realm;
    private static final String TAG = ChooseCategory.class.getSimpleName();
    CategoryAdapter aCategoryAdapter;
    ListView categoryWidgets;
    Categories fetchCatdata;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setupWindowAnimations();
        realm = Realm.getDefaultInstance();
        langid = getIntent().getStringExtra(Config.LANG_ID);

        loadUI();

        fetchCatdata = realm.where(Categories.class).equalTo("id", langid).findFirst();

        if (fetchCatdata != null) {
            if (fetchCatdata.getCategory().size() > 0 && !Utility.isNetworkAvailable(ChooseCategory.this)) {
                showCategoryListData(fetchCatdata.getCategory());
            } else if (Utility.isNetworkAvailable(ChooseCategory.this)) {
                getCategory(langid, fetchCatdata.getLastupdatetime());
            } else {
                Utility.PassAlertPAct(ChooseCategory.this, getString(R.string.no_data_found));
            }
        } else if (Utility.isNetworkAvailable(ChooseCategory.this)) {
            getCategory(langid, Config.Lang_Last_Updated_time);
        } else {
            Utility.PassAlertPAct(ChooseCategory.this, getString(R.string.no_data_found));
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

    }

    private void loadUI() {
        categoryWidgets = (ListView) findViewById(R.id.CC_LV_category);
    }

    private void showCategoryListData(RealmList<Category> mCategoryResponse) {
        aCategoryAdapter = new CategoryAdapter();
        aCategoryAdapter.setClassInstance(ChooseCategory.this);
        aCategoryAdapter.setData(mCategoryResponse);
        categoryWidgets.setAdapter(aCategoryAdapter);
    }


    private void getCategory(final String langid, String last_update_time) {
        Subscription subscription = GitHubClient.getInstance()
                .getCategoryData(langid, last_update_time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                        fetchCatdata = realm.where(Categories.class).equalTo(Config.ID, langid).findFirst();
                        if (fetchCatdata != null) {
                            if (fetchCatdata.getCategory().size() > 0) {
                                showCategoryListData(fetchCatdata.getCategory());
                            } else {
                                Utility.PassAlertPAct(ChooseCategory.this, getString(R.string.no_data_found));
                            }
                        } else {
                            Utility.PassAlertPAct(ChooseCategory.this, getString(R.string.no_data_found));
                        }
                        // Log.i(TAG, "lang_id: " +
                        // fetchCatdata.getId() + "last_update_time: " +
                        // fetchCatdata.getLastupdatetime() + "category_size: " +
                        // fetchCatdata.getCategory().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onNext(CategoryResponse categoryResponse) {
//                        oldTricks(categoryResponse);
                        newTricks(categoryResponse);
                    }

                });
    }

    private void newTricks(final CategoryResponse categoryResponse) {
        if (categoryResponse.getResponse().getResponse_code().equalsIgnoreCase("0")) {
            Log.i(TAG, categoryResponse.getResponse().getResponse_message());
        } else if (categoryResponse.getResponse().getResponse_code().equalsIgnoreCase("2")) {
            Log.i(TAG, categoryResponse.getResponse().getResponse_message());
        } else if (categoryResponse.getResponse().getResponse_code().equalsIgnoreCase("1")) {
            final Categories updateCat = realm.where(Categories.class).equalTo(Config.LAST_UPDATED_TIME, categoryResponse.getLast_updated_time()).findFirst();
            if (updateCat != null) {
                updateCatgorydata(updateCat, categoryResponse);
            } else {
                createCategorydata(categoryResponse);
            }
        }
    }

    private void updateCatgorydata(final Categories updateCat, final CategoryResponse categoryResponse) {
        // Update category in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (categoryResponse.getResponse().getResponse_code().equalsIgnoreCase("1")) {
                    updateCat.setId(categoryResponse.getLang());
                    updateCat.setLastupdatetime(categoryResponse.getLast_updated_time().trim());
                    for (int i = 0; i < categoryResponse.getCategories().size(); i++) {
                        CategoryResponse.Category category = categoryResponse.getCategories().get(i);
                        updateCat.getCategory().get(i).setCategory_id(category.getCategory_id());
                        updateCat.getCategory().get(i).setCategory_name(category.getCategory_name());
                        updateCat.getCategory().add(updateCat.getCategory().get(i));
                    }
                } else {
                    Log.i(TAG, categoryResponse.getResponse().getResponse_message());
                }
            }
        });

    }

    private void createCategorydata(final CategoryResponse categoryResponse) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // Insert New categories items here...
                if (categoryResponse.getResponse().getResponse_code().equalsIgnoreCase("1")) {
                    Categories addCategories = realm.createObject(Categories.class);
                    addCategories.setId(categoryResponse.getLang());
                    addCategories.setLastupdatetime(categoryResponse.getLast_updated_time().trim());
                    for (CategoryResponse.Category category : categoryResponse.getCategories()) {
                        Category category1 = realm.createObject(Category.class);
                        category1.setCategory_id(category.getCategory_id());
                        category1.setCategory_name(category.getCategory_name());
                        addCategories.getCategory().add(category1);
                    }
                } else {
                    Log.i(TAG, categoryResponse.getResponse().getResponse_message());
                }
            }
        });

    }

    private void oldTricks(CategoryResponse categoryResponse) {
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
}
