package co.in.dreamguys.littlekids.RealmModel;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Prasad on 10/5/2017.
 */

public class CategoryItems extends RealmObject implements Serializable{

    private String lang_id;
    private String primary_id;
    private String section_title;
    private RealmList<SectionLists> sectionList;
    private String last_updated_time;


    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    public String getPrimary_id() {
        return primary_id;
    }

    public void setPrimary_id(String primary_id) {
        this.primary_id = primary_id;
    }

    public String getSection_title() {
        return section_title;
    }

    public void setSection_title(String section_title) {
        this.section_title = section_title;
    }

    public RealmList<SectionLists> getSectionList() {
        return sectionList;
    }

    public void setSectionList(RealmList<SectionLists> sectionList) {
        this.sectionList = sectionList;
    }

    public String getLast_updated_time() {
        return last_updated_time;
    }

    public void setLast_updated_time(String last_updated_time) {
        this.last_updated_time = last_updated_time;
    }
}
