package co.in.dreamguys.littlekids.RealmModel;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Prasad on 10/3/2017.
 */

public class Categories extends RealmObject {

    private String id;
    private RealmList<Category> category;
    private String lastupdatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<Category> getCategory() {
        return category;
    }

    public void setCategory(RealmList<Category> category) {
        this.category = category;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}
