package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class feesPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<Feesdata> getFeesData() {
        return Data;
    }

    public void setFeesData(List<Feesdata> feesData) {
        Data = feesData;
    }

    @SerializedName("Data")
    @Expose
    private List<Feesdata> Data;

    public static class Feesdata{

        @SerializedName("HeadId")
        @Expose
        private String HeadId;

        public String getHeadId() {
            return HeadId;
        }

        public void setHeadId(String headId) {
            HeadId = headId;
        }

        @SerializedName("HeadName")
        @Expose
        private String HeadName;

        @SerializedName("TotalAmount")
        @Expose
        private String TotalAmount;

        public String getHeadName() {
            return HeadName;
        }

        public void setHeadName(String headName) {
            HeadName = headName;
        }

        public String getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            TotalAmount = totalAmount;
        }

        public String getPaidAmount() {
            return PaidAmount;
        }

        public void setPaidAmount(String paidAmount) {
            PaidAmount = paidAmount;
        }

        public String getBalanceAmount() {
            return BalanceAmount;
        }

        public void setBalanceAmount(String balanceAmount) {
            BalanceAmount = balanceAmount;
        }

        public String getRecDate() {
            return RecDate;
        }

        public void setRecDate(String recDate) {
            RecDate = recDate;
        }

        public String getAmountPaid() {
            return AmountPaid;
        }

        public void setAmountPaid(String amountPaid) {
            AmountPaid = amountPaid;
        }

        @SerializedName("PaidAmount")
        @Expose
        private String PaidAmount;

        public Feesdata(String headName, String totalAmount, String paidAmount, String balanceAmount, String recDate, String amountPaid) {
            HeadName = headName;
            TotalAmount = totalAmount;
            PaidAmount = paidAmount;
            BalanceAmount = balanceAmount;
            RecDate = recDate;
            AmountPaid = amountPaid;
        }

        @SerializedName("BalanceAmount")
        @Expose
        private String BalanceAmount;

        @SerializedName("RecDate")
        @Expose
        private String RecDate;

        @SerializedName("AmountPaid")
        @Expose
        private String AmountPaid;




    }



}
