package com.smsoft.evischoolmanagementapp;

import com.smsoft.evischoolmanagementapp.PoJo.AdsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.AttTeacherPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.Att_Day_Wise_PoJo;
import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.EventsDatesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.EventsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.ExamPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.ExamsListPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.FeesReciptsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.FiltersPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.LibraryBooksPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.LibrarySingleBookPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.StudentsListAttPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.TimeTablePoJo;
import com.smsoft.evischoolmanagementapp.PoJo.feedbackListPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.getStudentsMarksPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.notificationsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.simplePoJo;

import org.json.JSONArray;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/*
Created By Amit Baddi On 2020-01-24
*/public interface ApiInterface {
    @POST("loginAuth.php")
    Call<loginPoJo> login(@Query("Username") String Username,
                          @Query("Password") String Password,
                          @Query("URL") String URL,
                          @Query("fcm") String fcm);

    @POST("SchoolList.php")
    Call<SchoolList> schoollist();

    @POST("Version.php")
    Call<simplePoJo> version();



    @POST("EventsDates.php")
    Call<EventsDatesPoJo> EventsDates(@Query("URL") String URL);

    @POST("AdvtUrls.php")
    Call<AdsPoJo> getAds();

    @POST("FeesDetails.php")
    Call<feesPoJo> FeesDetails(@Query("Register_Number") String Register_Number,
                               @Query("URL") String URL);

    @POST("Library.php")
    Call<LibraryBooksPoJo> Library(@Query("Key") String Key,
                                   @Query("URL") String URL);

    @POST("LibraryBook.php")
    Call<LibrarySingleBookPoJo> getBook(@Query("Key") String Key, @Query("URL") String URL);

    @POST("FeesRecipts.php")
    Call<FeesReciptsPoJo> getFeesRecipt(@Query("Register_Number") String Register_Number,
                                        @Query("URL") String URL,
                                        @Query("HeadId") String HeadId);



    @POST("attendance.php")
    Call<Att_WholeYearPoJo> get_attendance(@Query("Class") String Class,
                                           @Query("Division") String Division,
                                           @Query("URL") String URL,
                                           @Query("Roll") String Roll);

    @POST("AttendanceDayWise.php")
    Call<Att_Day_Wise_PoJo> get_attendance_daywise(@Query("Class") String Class,
                                                   @Query("Division") String Division,
                                                   @Query("Month") String Month,
                                                   @Query("URL") String URL,
                                                   @Query("Roll") String Roll);



    @POST("notification.php")
    Call<notificationsPoJo> get_notification(@Query("Class") String Class,
                                           @Query("Division") String Division,
                                           @Query("URL") String URL
                                          );
    @POST("changePassword.php")
    Call<simplePoJo> changePassword(@Query("SID") String SID,
                                    @Query("Password") String Password,
                                    @Query("URL") String URL);

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

    @POST("ExamList.php")
    Call<ExamsListPoJo> getExamList(@Query("Class") String Class,
                                       @Query("Division") String Division,
                                       @Query("URL") String URL,
                                       @Query("Roll") String Roll);

    @POST("ExamMarks.php")
    Call<ExamPoJo> getExamResults(@Query("Class") String Class,
                                        @Query("Division") String Division,
                                        @Query("URL") String URL,
                                        @Query("Roll") String Roll,
                                        @Query("ExamName") String ExamName,
                                        @Query("ExamType") String ExamType,
                                        @Query("Term") String Term,
                                        @Query("S_id") String S_id
                                  );

    @POST("Att_Teacher1.php")
    Call<AttTeacherPoJo> getAtt1(@Query("SID") String SID,
                                 @Query("URL") String URL);

    @POST("digiDir.php")
    Call<AttTeacherPoJo> getDir(@Query("URL") String URL);

    @POST("Att_Teacher2.php")
    Call<simplePoJo> getAtt2(@Query("URL") String URL,
                             @Query("Arr") JSONArray Arr,
                             @Query("SMS") String SMS,
                             @Query("Subject") String Subject);

    @POST("StudentsAtt.php")
    Call<StudentsListAttPoJo> getStudents(@Query("CLS") String CLS,
                                          @Query("Div") String Div,
                                          @Query("URL") String URL);

    @POST("StudentsFrMarks.php")
    Call<getStudentsMarksPoJo> getStudentsformarks(@Query("CLS") String CLS,
                                                   @Query("Div") String Div,
                                                   @Query("Subject") String Subject,
                                                   @Query("Field") String Field,
                                                   @Query("Term") String Term,
                                                   @Query("URL") String URL);

    @POST("UpdateMarks.php")
    Call<simplePoJo> insertMarks(@Query("URL") String URL,
                                 @Query("Arr") JSONArray Arr,
                                 @Query("Subject") String Subject,
                                 @Query("Field") String Field,
                                 @Query("Term") String Term);


    @POST("GetFilters.php")
    Call<FiltersPoJo> getFilter(@Query("SID") String SID,
                                @Query("URL") String URL);

    @Multipart
    @POST("SendNotification.php")
    Call<simplePoJo> sendNotification(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                                 @Part("Message") RequestBody Message,
                                                 @Part("Cls") RequestBody Cls,
                                                 @Part("Div") RequestBody Div,
                                                 @Part("URL") RequestBody URL,
                                                 @Part("Code") RequestBody Code);

    @Multipart
    @POST("uploadToDigi.php")
    Call<simplePoJo> uploadDigi(
                                    @Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                    @Part("Dir") RequestBody Dir,
                                    @Part("URL") RequestBody URL,
                                    @Part("Code") RequestBody Code);

    @POST("SendNotificationText.php")
    Call<simplePoJo> sendNotificationText(@Query("Title") String Title,
                                          @Query("Message") String Message,
                                          @Query("Cls") String Cls,
                                          @Query("Div") String Div,
                                          @Query("URL") String URL,
                                          @Query("Code") String Code);


}
