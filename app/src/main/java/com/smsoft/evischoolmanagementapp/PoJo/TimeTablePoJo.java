package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

/*
Created By Amit Baddi On 2020-01-22
*/
public class TimeTablePoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<PeriodData> getPeriods() {
        return Periods;
    }

    public void setPeriods(List<PeriodData> periods) {
        Periods = periods;
    }

    @SerializedName("Periods")
    @Expose
    private List<PeriodData> Periods;

    public static class PeriodData{
        @SerializedName("Time")
        @Expose
        private String Time;

        @SerializedName("Subject")
        @Expose
        private String Subject;

        public String getAlter() {
            return Alter;
        }

        public void setAlter(String alter) {
            Alter = alter;
        }

        @SerializedName("Teacher")
        @Expose
        private String Teacher;

        @SerializedName("Alter")
        @Expose
        private String Alter;

        public PeriodData(String time, String subject, String teacher,String alter) {
            Alter=alter;
            Time = time;
            Subject = subject;
            Teacher = teacher;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String subject) {
            Subject = subject;
        }

        public String getTeacher() {
            return Teacher;
        }

        public void setTeacher(String teacher) {
            Teacher = teacher;
        }
    }
}
