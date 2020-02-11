package com.smsoft.evischoolmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.EventsDatesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.EventsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

public class Events extends AppCompatActivity {
    Calendar calendar;

    MCalendarView calendarView;
    String selectedDate;
    DateData dd;
    LinearLayout main;
    TextView date_dis,message;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        pd=new ProgressDialog(Events.this);
        pd.setTitle(R.string.progrees_title);
        pd.setMessage("Loading Please wait..");
        pd.show();




        StudSharedPref s=new StudSharedPref(Events.this);
        stud_data=s.getSharedData();
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        calendarView = (MCalendarView) findViewById(R.id.calendarView);
        date_dis=(TextView)findViewById(R.id.date);
        message=(TextView)findViewById(R.id.message);
        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);

        calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2012);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);
        calendarView =new MCalendarView(Events.this);



        Date today = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
          selectedDate = dateFormat.format(today);

        date_dis.setText("Date : "+selectedDate);


        selectedDate = dateFormat.format(today);



        //selectedWeekDay = (String) DateFormat.format("EEEE", today);


        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.DAY_OF_YEAR, 1);

        calendarView.setMarkedStyle(MarkStyle.BACKGROUND);
        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                calendarView.unMarkDate(dd);
                calendarView.markDate(date);
                Calendar calSelected = Calendar.getInstance();
                String d=date.getYear()+"-"+date.getMonth()+"-"+date.getDay();
                selectedDate = d;
                date_dis.setText(selectedDate);
                pd.show();
                getEvents(selectedDate);
                dd=date;
            }
        });
        getEvents(selectedDate);
        getDates();

    }

    private void getEvents(String date){

        Call<EventsPoJo> call=apiInterface.get_events(date,stud_data.getURL());
        call.enqueue(new Callback<EventsPoJo>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<EventsPoJo> call, Response<EventsPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                         String msg="";
                        for(int i=0;i<response.body().getData().size();i++){
                            msg=msg+"\n"+response.body().getData().get(i).getMessage();
                            message.setText(msg);
                        }
                        if(response.body().getData().size() == 0){
                            message.setText("");
                        }
                        pd.dismiss();
                    }else{
                        pd.dismiss();
                        Toast.makeText(Events.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    pd.dismiss();
                    Toast.makeText(Events.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventsPoJo> call, Throwable t) {
                pd.dismiss();
            }
        });
    }

    private void getDates(){
        Call<EventsDatesPoJo> call=apiInterface.EventsDates(stud_data.getURL());
        call.enqueue(new Callback<EventsDatesPoJo>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<EventsDatesPoJo> call, Response<EventsDatesPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){

                   for(int i=0;i<response.body().getData().size();i++){
                       Log.d("Trace",response.body().getData().get(i).getDate());
                      String[] a= response.body().getData().get(i).getDate().split("-");
                       DateData d=new DateData(Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                            calendarView.markDate(d);
//
                        }
                   pd.dismiss();
                    }else{
                        pd.dismiss();
                        Toast.makeText(Events.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    pd.dismiss();
                    Toast.makeText(Events.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventsDatesPoJo> call, Throwable t) {
                pd.dismiss();
            }
        });


    }
}
