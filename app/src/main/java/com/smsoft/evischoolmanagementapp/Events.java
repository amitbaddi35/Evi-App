package com.smsoft.evischoolmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Events extends AppCompatActivity {
    Calendar calendar;
    CalendarView calendarView;
    String selectedDate;

    TextView date,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        date=(TextView)findViewById(R.id.date);
        message=(TextView)findViewById(R.id.message);

        calendar = Calendar.getInstance();



        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2012);


        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);


        Date today = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
          selectedDate = dateFormat.format(today);

        date.setText("Date : "+selectedDate);




        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {


                selectedDate=i +"-"+(i1 + 1)+"-" +i2;
                date.setText("Date : "+selectedDate);
                //String msg =  i +"-"+(i1 + 1)+"-" +i2;
                 message.setText("In the first case, we animate to another date with animation. In the last case, the custom range shows only the July Month. The indicators are disabled.\n" +
                         "\n" +
                         "This brings an end to this tutorial. You can download the project from the link below:");


            }
        });
    }
}
