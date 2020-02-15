package com.smsoft.evischoolmanagementapp.validation;

import android.view.View;

/*
Created By Amit Baddi On 2020-01-25
*/
public class Validation {


    public boolean text_field_validation(com.google.android.material.textfield.TextInputEditText v){
             if(v.getText().toString().equals("")){
                 v.setError("Field Cannot Be Blank");
                 return false;
             }
             return true;
    }

    public boolean IsPasswordMatch(com.google.android.material.textfield.TextInputEditText p1,com.google.android.material.textfield.TextInputEditText p2){
        if(!p1.getText().toString().equals(p2.getText().toString())){
            p2.setError("Password Mismatch");
            return false;
        }
        return true;
    }

    public boolean isPasswordChanged(com.google.android.material.textfield.TextInputEditText v,String oldPassword){
        if(v.getText().toString().equals(oldPassword)){
            v.setError("Existing Password and New Password are Same");
            return false;
        }
        return true;
    }



}
