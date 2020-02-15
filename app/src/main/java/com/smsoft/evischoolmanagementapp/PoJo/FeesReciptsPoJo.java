package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-02-10
*/
public class FeesReciptsPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Data")
    @Expose
    private List<FeesReciptsPoJo.ReciptsDetails> Data;

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

    public List<ReciptsDetails> getData() {
        return Data;
    }

    public void setData(List<ReciptsDetails> data) {
        Data = data;
    }

    public static class ReciptsDetails{
        @SerializedName("ReciptName")
        @Expose
        private String ReciptName;

        @SerializedName("Date")
        @Expose
        private String Date;

        @SerializedName("Amount")
        @Expose
        private String Amount;

        public String getReciptName() {
            return ReciptName;
        }

        public void setReciptName(String reciptName) {
            ReciptName = reciptName;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            Amount = amount;
        }
    }
}
