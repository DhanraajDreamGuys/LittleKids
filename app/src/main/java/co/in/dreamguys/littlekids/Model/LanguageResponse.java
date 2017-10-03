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
    @SerializedName("last_updated_time")
    @Expose
    private String last_updated_time;
    @SerializedName("LanguageInfo")
    @Expose
    private List<LanguageInfo> languageInfo = null;

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

        // 0  - No Data, 1 - New Data, 2 - No Updates - Response code.

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
}
