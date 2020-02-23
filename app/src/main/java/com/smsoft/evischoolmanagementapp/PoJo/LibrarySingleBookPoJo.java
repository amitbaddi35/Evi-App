package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-02-15
*/public class LibrarySingleBookPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Data")
    @Expose
    private List<LibrarySingleBookPoJo.books> Data;

    public String getSuceess() {
        return Success;
    }

    public void setSuceess(String suceess) {
        Success = suceess;
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

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String author) {
            Author = author;
        }

        public String getAccessNumber() {
            return AccessNumber;
        }

        public void setAccessNumber(String accessNumber) {
            AccessNumber = accessNumber;
        }

        public String getSection() {
            return Section;
        }

        public void setSection(String section) {
            Section = section;
        }

        public String getPublisher() {
            return Publisher;
        }

        public void setPublisher(String publisher) {
            Publisher = publisher;
        }

        public String getPDF() {
            return PDF;
        }

        public void setPDF(String PDF) {
            this.PDF = PDF;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) {
            Department = department;
        }
    }

}
