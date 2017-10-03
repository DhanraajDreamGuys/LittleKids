package co.in.dreamguys.littlekids.RealmModel;

import io.realm.RealmObject;

public class Category extends RealmObject {


    private String category_id;
    private String category_name;

    @DefaultValue(0)
    private String last_updated_time;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getLast_updated_time() {
        return last_updated_time;
    }

    public void setLast_updated_time(String last_updated_time) {
        this.last_updated_time = last_updated_time;
    }

    public @interface DefaultValue {
        int value();
    }
}