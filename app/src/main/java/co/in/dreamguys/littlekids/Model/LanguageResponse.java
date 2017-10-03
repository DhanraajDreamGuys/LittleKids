package co.in.dreamguys.littlekids.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user5 on 12-09-2017.
 */

public class LanguageResponse {
    @SerializedName("Response")
    @Expose
    private Response response;
    @SerializedName("LanguageInfo")
    @Expose
    private List<LanguageInfo> languageInfo = null;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<LanguageInfo> getLanguageInfo() {
        return languageInfo;
    }

    public void setLanguageInfo(List<LanguageInfo> languageInfo) {
        this.languageInfo = languageInfo;
    }

    public class LanguageInfo {

        @SerializedName("lang_id")
        @Expose
        private String lang_id;
        @SerializedName("lang")
        @Expose
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

    public class Response {

        @SerializedName("response_code")
        @Expose
        private String response_code;
        @SerializedName("response_message")
        @Expose
        private String response_message;
        @SerializedName("last_updated")
        @Expose
        private String last_updated;

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

        public String getLast_updated() {
            return last_updated;
        }

        public void setLast_updated(String last_updated) {
            this.last_updated = last_updated;
        }

    }
}
