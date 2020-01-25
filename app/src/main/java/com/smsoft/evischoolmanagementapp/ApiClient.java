package com.smsoft.evischoolmanagementapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Created By Amit Baddi On 2020-01-24
*/
public class ApiClient {
    public static final String BASE_URL="http://smsoft.in/api/";
    public static Retrofit retrofit = null;
    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit =new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
