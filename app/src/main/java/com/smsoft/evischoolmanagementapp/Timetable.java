package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.PoJo.TimeTablePoJo;
import com.smsoft.evischoolmanagementapp.adapter.TimetableAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Timetable extends AppCompatActivity {
    List<TimeTablePoJo.PeriodData> list=new ArrayList<>();
    Calendar calendar;
    CalendarView calendarView;
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        listView=(ListView)findViewById(R.id.listview);


        calendar = Calendar.getInstance();



        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2012);


        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);


        Date today = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        selectedDate = dateFormat.format(today);
        String[] data={"true","true","true","false","true"};
        for(int i=0;i<data.length;i++){
             TimeTablePoJo.PeriodData o=new TimeTablePoJo.PeriodData("10:15-11:15","Subject 1","Teacher 1",data[i]);
            list.add(o);
        }
        TimetableAdapter adapter=new TimetableAdapter(Timetable.this,list);
        listView.setAdapter(adapter);
    }
}
