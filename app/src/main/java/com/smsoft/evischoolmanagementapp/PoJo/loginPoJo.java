package com.smsoft.evischoolmanagementapp.PoJo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
Created By Amit Baddi On 2020-01-24
*/
public class loginPoJo {
    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Message")
    @Expose
    private String Message;

//    @SerializedName("Data")
//    @Expose
//    private List<Stud_Data> Data ;

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

    /*public List<Stud_Data> getData() {
        return Data;
    }

    public void setData(List<Stud_Data> data) {
        Data = data;
    }*/

    public static class Stud_Data{

    }

}
