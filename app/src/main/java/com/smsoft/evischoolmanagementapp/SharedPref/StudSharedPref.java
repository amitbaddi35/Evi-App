package com.smsoft.evischoolmanagementapp.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;

import static android.content.Context.MODE_PRIVATE;

/*
Created By Amit Baddi On 2020-01-26
*/
public class StudSharedPref {
    private static StudSharedPref instance;
    private Context mContext;

    public StudSharedPref(Context context)
    {
        this.mContext=context;
    }

    /*@Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }*/

    public void setSharedData(loginPoJo.Stud_Data stud_data){
        SharedPreferences sharedPreferences = null;
        sharedPreferences = mContext.getSharedPreferences("Stud_Data", MODE_PRIVATE);
        Gson gson=new Gson();
        String toSharedPrefString=gson.toJson(stud_data);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Stud_Data",toSharedPrefString);
        editor.commit();
    }

    public loginPoJo.Stud_Data getSharedData() {
        loginPoJo.Stud_Data stud_data;
        stud_data=new loginPoJo.Stud_Data();
        SharedPreferences sharedPreferences;
        sharedPreferences = mContext.getSharedPreferences("Stud_Data", MODE_PRIVATE);

        Gson gson = new Gson();
        String fromSharedPref = sharedPreferences.getString("Stud_Data", "Empty");
        if (!fromSharedPref.equals("Empty")) {

            stud_data = gson.fromJson(fromSharedPref, loginPoJo.Stud_Data.class);
        }else {
            //stud_data.setUatoken("");
        }
        return stud_data;
    }


}
