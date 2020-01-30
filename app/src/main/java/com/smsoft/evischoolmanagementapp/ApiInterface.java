package com.smsoft.evischoolmanagementapp;

import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.EventsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.TimeTablePoJo;
import com.smsoft.evischoolmanagementapp.PoJo.feedbackListPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.notificationsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.simplePoJo;

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

    @POST("FeesDetails.php")
    Call<feesPoJo> FeesDetails(@Query("Register_Number") String Register_Number,
                               @Query("URL") String URL);

    @POST("attendance.php")
    Call<Att_WholeYearPoJo> get_attendance(@Query("Class") String Class,
                                           @Query("Division") String Division,
                                           @Query("URL") String URL,
                                           @Query("Roll") String Roll);
    @POST("notification.php")
    Call<notificationsPoJo> get_notification(@Query("Class") String Class,
                                           @Query("Division") String Division,
                                           @Query("URL") String URL
                                          );
    @POST("Events.php")
    Call<EventsPoJo> get_events(@Query("Date") String Date,
                                @Query("URL") String URL);

    @POST("FeedbackSuggestionList.php")
    Call<feedbackListPoJo> getList(@Query("URL") String URL);

    @POST("FeedbackInsert.php")
    Call<simplePoJo> feedbackInsert(@Query("FeedbackList") String FeedbackList,
                                    @Query("Message") String Message,
                                    @Query("Class") String Class,
                                    @Query("Div") String Div,
                                    @Query("StudName") String StudName,
                                    @Query("Mobile") String Mobile,
                                    @Query("Sex") String Sex,
                                    @Query("URL") String URL);

    @POST("Timetable.php")
    Call<TimeTablePoJo> getTimetable(@Query("Date") String Date,
                                   @Query("Class") String Class,
                                   @Query("Division") String Division,
                                   @Query("URL") String URL);
}
