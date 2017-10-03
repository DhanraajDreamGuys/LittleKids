package co.in.dreamguys.littlekids.RealmModel;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Prasad on 10/3/2017.
 */

public class Categories extends RealmObject {
    @PrimaryKey
    long id;
    RealmList <Category> category;
    String lastupdatetime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
