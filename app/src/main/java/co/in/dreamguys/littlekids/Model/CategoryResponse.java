package co.in.dreamguys.littlekids.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmModel;

public class CategoryResponse  {

    @SerializedName("Response")
    @Expose
    private Response response;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("lang_id")
    @Expose
    private String lang_id;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("last_updated_time")
    @Expose
    private String last_updated_time;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getLast_updated_time() {
        return last_updated_time;
    }

    public void setLast_updated_time(String last_updated_time) {
        this.last_updated_time = last_updated_time;
    }

    public class Response {

        @SerializedName("response_code")
        @Expose
        private String response_code;
        @SerializedName("response_message")
        @Expose
        private String response_message;

        public String getResponse_code() {
            return response_code;
        }

        public void setResponse_code(String response_code) {
            this.response_code = response_code;
        }

        public String getResponse_message() {
            return response_message;
        }

        public void setResponse_message(String response_message) {
            this.response_message = response_message;
        }

    }

    public class Category {

        @SerializedName("category_id")
        @Expose
        private String category_id;
        @SerializedName("category_name")
        @Expose
        private String category_name;

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

    }
}