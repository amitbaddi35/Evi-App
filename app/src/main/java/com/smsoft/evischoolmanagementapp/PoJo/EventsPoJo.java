package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-01-27
*/
public class EventsPoJo {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Data")
    @Expose
    private List<Event_msg> data = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Event_msg> getData() {
        return data;
    }

    public void setData(List<Event_msg> data) {
        this.data = data;
    }

    public class Event_msg {

        @SerializedName("Message")
        @Expose
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
