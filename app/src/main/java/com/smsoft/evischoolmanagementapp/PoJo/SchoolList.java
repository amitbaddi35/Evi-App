package com.smsoft.evischoolmanagementapp.PoJo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchoolList {

    @SerializedName("Success")
    @Expose
    private String Success;

    @SerializedName("Data")
    @Expose
    private List<schoolData> Data;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<schoolData> getData() {
        return Data;
    }

    public void setData(List<schoolData> data) {
        Data = data;
    }

   public static class schoolData{
        @SerializedName("SchoolCode")
        @Expose
        private String SchoolCode;

        @SerializedName("SchoolName")
        @Expose
        private String SchoolName;

       public schoolData(String schoolCode, String schoolName) {
           SchoolCode = schoolCode;
           SchoolName = schoolName;
       }

       public String getSchoolCode() {
            return SchoolCode;
        }

        public void setSchoolCode(String schoolCode) {
            SchoolCode = schoolCode;
        }

        public String getSchoolName() {
            return SchoolName;
        }

        public void setSchoolName(String schoolName) {
            SchoolName = schoolName;
        }

        @Override
        public String toString() {
            return SchoolName;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof SchoolList.schoolData){
                schoolData s=(schoolData)obj;
                if(s.getSchoolCode().equals(SchoolCode) && s.getSchoolName().equals(SchoolName))
                    return true;
            }
            return false;
        }
    }








}
