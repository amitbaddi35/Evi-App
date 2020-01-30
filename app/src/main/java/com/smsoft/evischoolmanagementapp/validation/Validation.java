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

}
