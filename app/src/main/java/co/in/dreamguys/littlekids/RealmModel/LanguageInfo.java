package co.in.dreamguys.littlekids.RealmModel;

import io.realm.RealmObject;

/**
 * Created by user5 on 13-09-2017.
 */

public class LanguageInfo extends RealmObject {

    private String lang_id;
    private String lang;

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
}
