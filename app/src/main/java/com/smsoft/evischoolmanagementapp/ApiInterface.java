package com.smsoft.evischoolmanagementapp;

import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
Created By Amit Baddi On 2020-01-24
*/public interface ApiInterface {
    @POST("loginAuth.php")
    Call<loginPoJo> login(@Query("Username") String Username,
                          @Query("Password") String Password,
                          @Query("URL") String URL);

    @POST("test.php")
    Call<SchoolList> schoollist();



}
