package co.in.dreamguys.littlekids.RealmModel;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by user5 on 13-09-2017.
 */

public class LanguageInfo extends RealmObject implements Serializable {

    private String lang_id;
    private String lang;

    @DefaultValue(0)
    private String last_updated_time;

    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLast_updated_time() {
        return last_updated_time;
    }

    public void setLast_updated_time(String last_updated_time) {
        this.last_updated_time = last_updated_time;
    }

    private @interface DefaultValue {
        int value();
    }
}
