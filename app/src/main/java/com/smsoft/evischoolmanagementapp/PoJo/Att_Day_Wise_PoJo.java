package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-02-10
*/
public class Att_Day_Wise_PoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Data")
    @Expose
    private List<Att_Day_Wise_PoJo.AttData> Data;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<AttData> getData() {
        return Data;
    }

    public void setData(List<AttData> data) {
        Data = data;
    }

    public static class AttData{
        @SerializedName("Day")
        @Expose
        private String Day;

        @SerializedName("Subject")
        @Expose
        private String Subject;

        @SerializedName("Status")
        @Expose
        private String Status;

        @SerializedName("In")
        @Expose
        private String In;

        @SerializedName("Out")
        @Expose
        private String Out;

        public String getDay() {
            return Day;
        }

        public void setDay(String day) {
            Day = day;
        }

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String subject) {
            Subject = subject;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getIn() {
            return In;
        }

        public void setIn(String in) {
            In = in;
        }

        public String getOut() {
            return Out;
        }

        public void setOut(String out) {
            Out = out;
        }
    }
}
