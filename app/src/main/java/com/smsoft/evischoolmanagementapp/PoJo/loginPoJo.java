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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @SerializedName("domain")
    @Expose
    private String domain;

    @SerializedName("user_type")
    @Expose
    private String user_type;

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getSchoolcode() {
        return schoolcode;
    }

    public void setSchoolcode(String schoolcode) {
        this.schoolcode = schoolcode;
    }

    @SerializedName("schoolName")
    @Expose
    private String schoolName;

    @SerializedName("schoolcode")
    @Expose
    private String schoolcode;


    public String getSchoolCode() {
        return schoolcode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolcode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

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

        @SerializedName("Caste")
        @Expose
        private String Caste;

        @SerializedName("Category")
        @Expose
        private String Category;

        @SerializedName("Religion")
        @Expose
        private String Religion;

        @SerializedName("Father_Name")
        @Expose
        private String Father_Name;

        @SerializedName("Mother_Name")
        @Expose
        private String Mother_Name;

        @SerializedName("Username")
        @Expose
        private String Username;

        @SerializedName("Password")
        @Expose
        private String Password;

        @SerializedName("SchoolImage")
        @Expose
        private String SchoolImage;

        public String getSchoolImage() {
            return SchoolImage;
        }

        public void setSchoolImage(String schoolImage) {
            SchoolImage = schoolImage;
        }

        public String getUsername() {
            return Username;
        }

        public void setUsername(String username) {
            Username = username;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getCaste() {
            return Caste;
        }

        public void setCaste(String caste) {
            Caste = caste;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String category) {
            Category = category;
        }

        public String getReligion() {
            return Religion;
        }

        public void setReligion(String religion) {
            Religion = religion;
        }

        public String getFather_Name() {
            return Father_Name;
        }

        public void setFather_Name(String father_Name) {
            Father_Name = father_Name;
        }

        public String getMother_Name() {
            return Mother_Name;
        }

        public void setMother_Name(String mother_Name) {
            Mother_Name = mother_Name;
        }

        @SerializedName("Name")
        @Expose
        private String Name;

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }


        public String getClasss() {
            return Class;
        }

        @SerializedName("URL")
        @Expose
        private String URL;

        public String getFcm() {
            return fcm;
        }

        public void setFcm(String fcm) {
            this.fcm = fcm;
        }

        @SerializedName("fcm")
        @Expose
        private String fcm;

        @SerializedName("schoolName")
        @Expose
        private String schoolName;

        public String getSchoolCode() {
            return mschoolCode;
        }

        public void setSchoolCode(String schoolCode) {
            this.mschoolCode = schoolCode;
        }

        @SerializedName("mschoolCode")
        @Expose
        private String mschoolCode;

        @SerializedName("Register_number")
        @Expose
        private String Register_number;

        public String getRegister_number() {
            return Register_number;
        }

        public void setRegister_number(String register_number) {
            Register_number = register_number;
        }

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

        @SerializedName("user_type")
        @Expose
        private String user_type;

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

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
