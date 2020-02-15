package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-02-15
*/
public class LibraryBooksPoJo {
    @SerializedName("Suceess")
    @Expose
    private String Suceess;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Data")
    @Expose
    private List<books> Data;

    public String getSuceess() {
        return Suceess;
    }

    public void setSuceess(String suceess) {
        Suceess = suceess;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<books> getData() {
        return Data;
    }

    public void setData(List<books> data) {
        Data = data;
    }

    public static class books{
        @SerializedName("Id")
        @Expose
        private String Id;

        @SerializedName("Name")
        @Expose
        private String Name;

        @SerializedName("Status")
        @Expose
        private String Status;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }
    }

}
