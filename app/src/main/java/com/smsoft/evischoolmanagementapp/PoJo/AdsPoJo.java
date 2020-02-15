package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-02-02
*/
public class AdsPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<Ads> getData() {
        return Data;
    }

    public void setData(List<Ads> data) {
        Data = data;
    }

    @SerializedName("Data")
    @Expose
    private List<Ads> Data;

    public static class Ads{
        @SerializedName("CompanyName")
        @Expose
        private String CompanyName;

        @SerializedName("AdvtURL")
        @Expose
        private String AdvtURL;

        @SerializedName("Discription")
        @Expose
        private String Discription;

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String companyName) {
            CompanyName = companyName;
        }

        public String getAdvtURL() {
            return AdvtURL;
        }

        public void setAdvtURL(String advtURL) {
            AdvtURL = advtURL;
        }

        public String getDiscription() {
            return Discription;
        }

        public void setDiscription(String discription) {
            Discription = discription;
        }
    }


}
