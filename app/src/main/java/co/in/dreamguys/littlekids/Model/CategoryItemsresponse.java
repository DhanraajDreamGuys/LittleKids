package co.in.dreamguys.littlekids.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryItemsresponse {

    @SerializedName("Primary")
    @Expose
    private List<Primary> primary = null;
    @SerializedName("Response")
    @Expose
    private Response response;
    @SerializedName("last_updated_time")
    @Expose
    private String last_updated_time;

    public List<Primary> getPrimary() {
        return primary;
    }

    public void setPrimary(List<Primary> primary) {
        this.primary = primary;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getLast_updated_time() {
        return last_updated_time;
    }

    public void setLast_updated_time(String last_updated_time) {
        this.last_updated_time = last_updated_time;
    }

    public class Primary {

        @SerializedName("lang_id")
        @Expose
        private String lang_id;
        @SerializedName("primary_id")
        @Expose
        private String primary_id;
        @SerializedName("primary_name")
        @Expose
        private String primary_name;
        @SerializedName("Sections")
        @Expose
        private List<Section> sections = null;

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

        public String getPrimary_name() {
            return primary_name;
        }

        public void setPrimary_name(String primary_name) {
            this.primary_name = primary_name;
        }

        public List<Section> getSections() {
            return sections;
        }

        public void setSections(List<Section> sections) {
            this.sections = sections;
        }

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

    public class Section {

        @SerializedName("section_title")
        @Expose
        private String section_title;
        @SerializedName("section_name")
        @Expose
        private String section_name;
        @SerializedName("section_img_url")
        @Expose
        private String section_img_url;
        @SerializedName("section_audio_url")
        @Expose
        private String section_audio_url;
        @SerializedName("SubSections")
        @Expose
        private List<SubSection> subSections = null;

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

        public List<SubSection> getSubSections() {
            return subSections;
        }

        public void setSubSections(List<SubSection> subSections) {
            this.subSections = subSections;
        }

    }


    public class SubSection {

        @SerializedName("sub_section_name")
        @Expose
        private String sub_section_name;
        @SerializedName("sub_section_img_url")
        @Expose
        private String sub_section_img_url;
        @SerializedName("sub_section_img_audio")
        @Expose
        private String sub_section_img_audio;

        public String getSub_section_name() {
            return sub_section_name;
        }

        public void setSub_section_name(String sub_section_name) {
            this.sub_section_name = sub_section_name;
        }

        public String getSub_section_img_url() {
            return sub_section_img_url;
        }

        public void setSub_section_img_url(String sub_section_img_url) {
            this.sub_section_img_url = sub_section_img_url;
        }

        public String getSub_section_img_audio() {
            return sub_section_img_audio;
        }

        public void setSub_section_img_audio(String sub_section_img_audio) {
            this.sub_section_img_audio = sub_section_img_audio;
        }

    }

}