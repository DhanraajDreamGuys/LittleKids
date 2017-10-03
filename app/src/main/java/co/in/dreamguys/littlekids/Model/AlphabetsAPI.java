package co.in.dreamguys.littlekids.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user5 on 25-09-2017.
 */

public class AlphabetsAPI {

    @SerializedName("CategoryDetail")
    @Expose
    private List<CategoryDetail> categoryDetail = null;
    @SerializedName("last_updated_time")
    @Expose
    private String last_updated_time;

    public List<CategoryDetail> getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(List<CategoryDetail> categoryDetail) {
        this.categoryDetail = categoryDetail;
    }

    public String getLast_updated_time() {
        return last_updated_time;
    }

    public void setLast_updated_time(String last_updated_time) {
        this.last_updated_time = last_updated_time;
    }

    public class CategoryDetail {

        @SerializedName("category_title")
        @Expose
        private String category_title;
        @SerializedName("category_img_url")
        @Expose
        private String category_img_url;
        @SerializedName("subCategoryInfo")
        @Expose
        private List<SubCategoryInfo> subCategoryInfo = null;
        @SerializedName("main_sub_category_title")
        @Expose
        private String main_sub_category_title;
        @SerializedName("main_sub_category_img_url")
        @Expose
        private String main_sub_category_img_url;

        public String getCategory_title() {
            return category_title;
        }

        public void setCategory_title(String category_title) {
            this.category_title = category_title;
        }

        public String getCategory_img_url() {
            return category_img_url;
        }

        public void setCategory_img_url(String category_img_url) {
            this.category_img_url = category_img_url;
        }

        public List<SubCategoryInfo> getSubCategoryInfo() {
            return subCategoryInfo;
        }

        public void setSubCategoryInfo(List<SubCategoryInfo> subCategoryInfo) {
            this.subCategoryInfo = subCategoryInfo;
        }

        public String getMain_sub_category_title() {
            return main_sub_category_title;
        }

        public void setMain_sub_category_title(String main_sub_category_title) {
            this.main_sub_category_title = main_sub_category_title;
        }

        public String getMain_sub_category_img_url() {
            return main_sub_category_img_url;
        }

        public void setMain_sub_category_img_url(String main_sub_category_img_url) {
            this.main_sub_category_img_url = main_sub_category_img_url;
        }

    }

    public class SubCategoryInfo {

        @SerializedName("sub_category_name")
        @Expose
        private String sub_category_name;
        @SerializedName("sub_category_img_url")
        @Expose
        private String sub_category_img_url;
        @SerializedName("sub_category_img_audio")
        @Expose
        private String sub_category_img_audio;

        public String getSub_category_name() {
            return sub_category_name;
        }

        public void setSub_category_name(String sub_category_name) {
            this.sub_category_name = sub_category_name;
        }

        public String getSub_category_img_url() {
            return sub_category_img_url;
        }

        public void setSub_category_img_url(String sub_category_img_url) {
            this.sub_category_img_url = sub_category_img_url;
        }

        public String getSub_category_img_audio() {
            return sub_category_img_audio;
        }

        public void setSub_category_img_audio(String sub_category_img_audio) {
            this.sub_category_img_audio = sub_category_img_audio;
        }

    }

}
