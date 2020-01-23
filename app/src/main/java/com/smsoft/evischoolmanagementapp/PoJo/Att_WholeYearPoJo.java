package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Att_WholeYearPoJo {
    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<notificationsPoJo.notifications> getData() {
        return data;
    }

    public void setData(List<notificationsPoJo.notifications> data) {
        this.data = data;
    }

    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("data")
    @Expose
    private List<notificationsPoJo.notifications> data;

    public static class AttData{

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        @SerializedName("month")
        @Expose
        private String month;


        @SerializedName("present")
        @Expose
        private String present;

        @SerializedName("absent")
        @Expose
        private String absent;

        @SerializedName("holiday")
        @Expose
        private String holiday;

        public AttData(String month,String present, String absent, String holiday) {
            this.month=month;
            this.present = present;
            this.absent = absent;
            this.holiday = holiday;
        }

        public String getPresent() {
            return present;
        }

        public void setPresent(String present) {
            this.present = present;
        }

        public String getAbsent() {
            return absent;
        }

        public void setAbsent(String absent) {
            this.absent = absent;
        }

        public String getHoliday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }
    }
}
