package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-02-08
*/
public class EventsDatesPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Data")
    @Expose
    private List<Dates> Data;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<Dates> getData() {
        return Data;
    }

    public void setData(List<Dates> data) {
        Data = data;
    }

    public static class Dates{
        @SerializedName("Date")
        @Expose
        private String Date;

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }
    }
}
