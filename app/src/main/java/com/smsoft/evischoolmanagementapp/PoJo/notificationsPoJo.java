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
        return data;
    }

    public void setData(List<notifications> data) {
        this.data = data;
    }

    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("data")
    @Expose
    private List<notifications> data;

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
