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

    public ExamsListPoJo(List<String> exams) {
        Exams = exams;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<String> getExams() {
        return Exams;
    }

    public void setExams(List<String> exams) {
        Exams = exams;
    }

    public ExamsListPoJo(String success, List<String> exams) {
        Success = success;
        Exams = exams;
    }

    @SerializedName("Exams")
    @Expose
    private List<String> Exams;
}
