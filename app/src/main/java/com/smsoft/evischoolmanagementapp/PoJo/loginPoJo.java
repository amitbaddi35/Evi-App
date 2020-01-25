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

    @SerializedName("Data")
    @Expose
    private List<Stud_Data> Data ;

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

    public List<Stud_Data> getData() {
        return Data;
    }

    public void setData(List<Stud_Data> data) {
        Data = data;
    }

    public static class Stud_Data{
        @SerializedName("S_id")
        @Expose
        private String S_id;

        @SerializedName("Name")
        @Expose
        private String Name;

        @SerializedName("Communication_number")
        @Expose
        private String Communication_number;

        @SerializedName("Class")
        @Expose
        private String Class;

        @SerializedName("Division")
        @Expose
        private String Division;

        @SerializedName("Roll_number")
        @Expose
        private String Roll_number;

        @SerializedName("Sex")
        @Expose
        private String Sex;

        @SerializedName("Date_of_birth")
        @Expose
        private String Date_of_birth;

        public String getS_id() {
            return S_id;
        }

        public void setS_id(String s_id) {
            S_id = s_id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getCommunication_number() {
            return Communication_number;
        }

        public void setCommunication_number(String communication_number) {
            Communication_number = communication_number;
        }


        public String getClassds() {
            return Class;
        }

        public void setClass(String aClass) {
            Class = aClass;
        }

        public String getDivision() {
            return Division;
        }

        public void setDivision(String division) {
            Division = division;
        }

        public String getRoll_number() {
            return Roll_number;
        }

        public void setRoll_number(String roll_number) {
            Roll_number = roll_number;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String sex) {
            Sex = sex;
        }

        public String getDate_of_birth() {
            return Date_of_birth;
        }

        public void setDate_of_birth(String date_of_birth) {
            Date_of_birth = date_of_birth;
        }
    }

}
