package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class feedbackListPoJo {
    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<feedbackData> getData() {
        return Data;
    }

    public void setData(List<feedbackData> data) {
        Data = data;
    }

    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Data")
    @Expose
    private List<feedbackListPoJo.feedbackData> Data;

    public static class feedbackData{
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("Text")
        @Expose
        private String Text;



        public feedbackData(String id, String text) {
            this.id = id;
            Text = text;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return Text;
        }

        public void setText(String text) {
            Text = text;
        }
        @Override
        public String toString() {
            return Text;
        }
    }

}
