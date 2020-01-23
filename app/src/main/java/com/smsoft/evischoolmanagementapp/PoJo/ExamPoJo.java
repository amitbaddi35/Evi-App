package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-01-22
*/
public class ExamPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    public ExamPoJo(String success, List<Examdata> examData) {
        Success = success;
        ExamData = examData;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<Examdata> getExamData() {
        return ExamData;
    }

    public void setExamData(List<Examdata> examData) {
        ExamData = examData;
    }

    @SerializedName("ExamData")
    @Expose
    private List<Examdata> ExamData;

    public static class Examdata{
        @SerializedName("Subject")
        @Expose
        private String Subject;

        public Examdata(String subject, String marks) {
            Subject = subject;
            Marks = marks;
        }

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String subject) {
            Subject = subject;
        }

        public String getMarks() {
            return Marks;
        }

        public void setMarks(String marks) {
            Marks = marks;
        }

        @SerializedName("Marks")
        @Expose
        private String Marks;
    }
}
