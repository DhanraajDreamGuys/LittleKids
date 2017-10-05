package co.in.dreamguys.littlekids.RealmModel;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Prasad on 10/5/2017.
 */

public class SectionLists extends RealmObject implements Serializable{
    private String section_title;
    private String section_name;
    private String section_img_url;
    private String section_audio_url;
    private boolean isView;


    public String getSection_title() {
        return section_title;
    }

    public void setSection_title(String section_title) {
        this.section_title = section_title;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSection_img_url() {
        return section_img_url;
    }

    public void setSection_img_url(String section_img_url) {
        this.section_img_url = section_img_url;
    }

    public String getSection_audio_url() {
        return section_audio_url;
    }

    public void setSection_audio_url(String section_audio_url) {
        this.section_audio_url = section_audio_url;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }
}
