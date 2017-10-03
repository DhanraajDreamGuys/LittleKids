package co.in.dreamguys.littlekids.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by user5 on 12-09-2017.
 */

public class LanguageModel extends RealmObject {

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