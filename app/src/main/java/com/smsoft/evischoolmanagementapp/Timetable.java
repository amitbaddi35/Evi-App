package com.smsoft.evischoolmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.TimeTablePoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.simplePoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.TimetableAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Timetable extends AppCompatActivity {
    List<TimeTablePoJo.PeriodData> list=new ArrayList<>();
    Calendar calendar;
    CalendarView calendarView;
    String selectedDate;
    ApiInterface apiInterface;
    ListView listView;
    loginPoJo.Stud_Data stud_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        calendarView = findViewById(R.id.calendarView);


        listView=(ListView)findViewById(R.id.listview);


        calendar = Calendar.getInstance();


        StudSharedPref s=new StudSharedPref(this);
        stud_data=s.getSharedData();

        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);


        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2012);


        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);

        Date today = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        selectedDate = dateFormat.format(today);
        getTimetable(selectedDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {


                selectedDate=i +"-"+(i1 + 1)+"-" +i2;

                //String msg =  i +"-"+(i1 + 1)+"-" +i2;
                getTimetable(selectedDate);
            }
        });





    }

    private void getTimetable(String Date){
        retrofit2.Call<TimeTablePoJo> call=apiInterface.getTimetable(Date,stud_data.getClassds(),stud_data.getDivision(),stud_data.getURL());
        call.enqueue(new Callback<TimeTablePoJo>() {
            @Override
            public void onResponse(retrofit2.Call<TimeTablePoJo> call, Response<TimeTablePoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){

                        TimetableAdapter adapter=new TimetableAdapter(Timetable.this,response.body().getData());
                        listView.setAdapter(adapter);
                    }else{
                        Toast.makeText(Timetable.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Timetable.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TimeTablePoJo> call, Throwable t) {
                Toast.makeText(Timetable.this, R.string.error_message, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
