package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-02-15
*/public class LibrarySingleBookPoJo {
    @SerializedName("Suceess")
    @Expose
    private String Suceess;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Data")
    @Expose
    private List<LibrarySingleBookPoJo.books> Data;

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

    public List<LibrarySingleBookPoJo.books> getData() {
        return Data;
    }

    public void setData(List<LibrarySingleBookPoJo.books> data) {
        Data = data;
    }

    public static class books{
        @SerializedName("Title")
        @Expose
        private String Title;

        @SerializedName("Author")
        @Expose
        private String Author;

        @SerializedName("AccessNumber")
        @Expose
        private String AccessNumber;

        @SerializedName("Section")
        @Expose
        private String Section;

        @SerializedName("Publisher")
        @Expose
        private String Publisher;

        @SerializedName("PDF")
        @Expose
        private String PDF;

        @SerializedName("Type")
        @Expose
        private String Type;

        @SerializedName("Department")
        @Expose
        private String Department;

    }

}
