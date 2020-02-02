package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-01-22
*/
public class ExamsListPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Data")
    @Expose
    private List<Exams_List> Data;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }



    public List<Exams_List> getData() {
        return Data;
    }

    public void setData(List<Exams_List> data) {
        Data = data;
    }

    public static class Exams_List{
        @SerializedName("Exam")
        @Expose
        private String Exam;

        @SerializedName("Type")
        @Expose
        private String Type;

        /*@Override
        public String toString() {
            return Exam;
        }*/

        public String getExam() {
            return Exam;
        }

        public void setExam(String exam) {
            Exam = exam;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }
    }



    }



