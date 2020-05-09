package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class notificationsPoJo {
    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<notifications> getData() {
        return Data;
    }

    public void setData(List<notifications> data) {
        this.Data = data;
    }

    @SerializedName("Success")
    @Expose
    private String Success;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Data")
    @Expose
    private List<notifications> Data;

    public static class notifications{
        @SerializedName("Date")
        @Expose
        private String Date;

        @SerializedName("Title")
        @Expose
        private String Title;

        @SerializedName("Discription")
        @Expose
        private String Discription;

        @SerializedName("Status")
        @Expose
        private String Status;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public notifications(String date, String title, String discription) {
            Date = date;
            Title = title;
            Discription = discription;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDiscription() {
            return Discription;
        }

        public void setDiscription(String discription) {
            Discription = discription;
        }
    }


}
