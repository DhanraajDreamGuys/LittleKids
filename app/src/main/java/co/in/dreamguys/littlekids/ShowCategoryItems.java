package co.in.dreamguys.littlekids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.GridView;

import co.in.dreamguys.littlekids.Adapter.CategoryItemsAdapter;
import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.Model.CategoryItemsresponse;
import co.in.dreamguys.littlekids.Network.GitHubClient;
import co.in.dreamguys.littlekids.RealmModel.CategoryItems;
import co.in.dreamguys.littlekids.RealmModel.SectionLists;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Prasad on 10/3/2017.
 */

public class ShowCategoryItems extends LittleKidsActivity {

    private Realm realm;
    private static final String TAG = ShowCategoryItems.class.getSimpleName();
    GridView gridShowCategoryItem;
    String langId = "", catID = "";
    RealmResults<CategoryItems> fetchCategoryItems;
    CategoryItemsAdapter aCategoryItemsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category_items);
        realm = Realm.getDefaultInstance();

        catID = getIntent().getStringExtra(Config.CAT_ID);

        loadUI();

        fetchCategoryItems = realm.where(CategoryItems.class).equalTo(Config.DB_CAT_ID, catID).findAll();


        if (fetchCategoryItems.size() > 0 && !Utility.isNetworkAvailable(ShowCategoryItems.this)) {
            showCategoryListData(fetchCategoryItems);
        } else if (Utility.isNetworkAvailable(ShowCategoryItems.this)) {
            getCategoryItems(catID, fetchCategoryItems.get(0).getLast_updated_time());
        } else {
            Utility.PassAlertPAct(ShowCategoryItems.this, getString(R.string.no_data_found));
        }


    }


    private void showCategoryListData(RealmResults<CategoryItems> sectionList) {
        aCategoryItemsAdapter = new CategoryItemsAdapter();
        aCategoryItemsAdapter.setClassInstance(ShowCategoryItems.this);
        aCategoryItemsAdapter.setData(sectionList);
        gridShowCategoryItem.setAdapter(aCategoryItemsAdapter);

    }

    private void loadUI() {
        gridShowCategoryItem = (GridView) findViewById(R.id.lv_show_category_items);
    }

    private void getCategoryItems(final String catID, final String last_update_time) {
        Subscription subscription = GitHubClient.getInstance()
                .getCategoryItemsData(catID, last_update_time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryItemsresponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                        fetchCategoryItems = realm.where(CategoryItems.class).equalTo(Config.DB_CAT_ID, catID).findAll();
                        if (fetchCategoryItems.size() > 0) {
                            showCategoryListData(fetchCategoryItems);
                        } else {
                            Utility.PassAlertPAct(ShowCategoryItems.this, getString(R.string.no_data_found));
                        }
                        /* if (fetchCategoryItems != null)
                            Log.i(TAG, "lang_id: " +
                                    fetchCategoryItems.getLang_id() + "last_update_time: " +
                                    fetchCategoryItems.getLast_updated_time() + "category_size: " +
                                    fetchCategoryItems.getSectionList().size() + "section_title: " +
                                    fetchCategoryItems.getSection_title() + "PrimaryID: " +
                                    fetchCategoryItems.getPrimary_id());*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onNext(CategoryItemsresponse categoryItemsresponse) {
                        getCategoryItemFromServer(categoryItemsresponse);
                    }
                });
    }

    private void getCategoryItemFromServer(CategoryItemsresponse categoryItemsresponse) {

        if (categoryItemsresponse.getResponse().getResponse_code().equalsIgnoreCase("0")) {
            Log.i(TAG, categoryItemsresponse.getResponse().getResponse_message());
        } else if (categoryItemsresponse.getResponse().getResponse_code().equalsIgnoreCase("1")) {

            final CategoryItems updateCatItems = realm.where(CategoryItems.class).equalTo(Config.LAST_UPDATED_TIME, categoryItemsresponse.getLast_updated_time()).findFirst();
            if (updateCatItems != null) {
                udpdateShowCategoryItems(updateCatItems, categoryItemsresponse);
            } else {
                insertShowCategoryItems(categoryItemsresponse);
            }


        } else if (categoryItemsresponse.getResponse().getResponse_code().equalsIgnoreCase("2")) {
            Log.i(TAG, categoryItemsresponse.getResponse().getResponse_message());
        }

    }

    private void udpdateShowCategoryItems(final CategoryItems updateCatItems, final CategoryItemsresponse categoryItemsresponse) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (CategoryItemsresponse.Primary primary : categoryItemsresponse.getPrimary()) {

                    // Update primary data here...
                    updateCatItems.setLang_id(primary.getLang_id());
                    updateCatItems.setPrimary_id(primary.getPrimary_id());
                    updateCatItems.setLast_updated_time(categoryItemsresponse.getLast_updated_time());


                    //Update sub sections data here...
                    for (int i = 0; i < primary.getSections().size(); i++) {
                        CategoryItemsresponse.Section section = primary.getSections().get(i);
                        updateCatItems.setSection_title(section.getSection_title());
                        updateCatItems.setSection_title(section.getSection_title());
                        updateCatItems.getSectionList().get(i).setSection_name(section.getSection_name());
                        updateCatItems.getSectionList().get(i).setSection_audio_url(section.getSection_audio_url());
                        updateCatItems.getSectionList().get(i).setSection_img_url(section.getSection_img_url());

                        updateCatItems.getSectionList().add(updateCatItems.getSectionList().get(i));
                        for (int subsection = 0; subsection < section.getSubSections().size(); subsection++) {
                            CategoryItemsresponse.SubSection subSection = section.getSubSections().get(subsection);
                            updateCatItems.setSection_title(section.getSection_title());
                            updateCatItems.getSectionList().get(subsection).setSection_name(subSection.getSub_section_name());
                            updateCatItems.getSectionList().get(subsection).setSection_audio_url(subSection.getSub_section_img_audio());
                            updateCatItems.getSectionList().get(subsection).setSection_img_url(subSection.getSub_section_img_url());

                            updateCatItems.getSectionList().add(updateCatItems.getSectionList().get(subsection));
                        }
                    }
                }

            }
        });
    }

    private void insertShowCategoryItems(final CategoryItemsresponse categoryItemsresponse) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (CategoryItemsresponse.Primary primary : categoryItemsresponse.getPrimary()) {

                    // add primary data here...
                    CategoryItems addCategoriesItems = realm.createObject(CategoryItems.class);
                    addCategoriesItems.setLang_id(primary.getLang_id());
                    addCategoriesItems.setPrimary_id(primary.getPrimary_id());
                    addCategoriesItems.setLast_updated_time(categoryItemsresponse.getLast_updated_time());


                    //add sub sections data here...
                    for (CategoryItemsresponse.Section section : primary.getSections()) {
                        addCategoriesItems.setSection_title(section.getSection_title());

                        SectionLists addSubCategoriesItems = realm.createObject(SectionLists.class);
                        addSubCategoriesItems.setSection_title(section.getSection_title());
                        addSubCategoriesItems.setSection_name(section.getSection_name());
                        addSubCategoriesItems.setSection_audio_url(section.getSection_audio_url());
                        addSubCategoriesItems.setSection_img_url(section.getSection_img_url());

                        addCategoriesItems.getSectionList().add(addSubCategoriesItems);

                        for (CategoryItemsresponse.SubSection subSection : section.getSubSections()) {
                            addSubCategoriesItems.setSection_title(section.getSection_title());
                            addSubCategoriesItems.setSection_name(subSection.getSub_section_name());
                            addSubCategoriesItems.setSection_audio_url(subSection.getSub_section_img_audio());
                            addSubCategoriesItems.setSection_img_url(subSection.getSub_section_img_url());

                            addCategoriesItems.getSectionList().add(addSubCategoriesItems);
                        }


                    }


                }

            }
        });


    }

}
