package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SchoolDashBoard extends AppCompatActivity {
    private LinearLayout fees,notifications,attendance,timetable,exam,digi,feedback,events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_dash_board);
        fees=(LinearLayout)findViewById(R.id.fees);
        notifications=(LinearLayout)findViewById(R.id.notification);
        attendance=(LinearLayout)findViewById(R.id.attendance);
        timetable=(LinearLayout)findViewById(R.id.timetable);
        exam=(LinearLayout)findViewById(R.id.exam);
        digi=(LinearLayout)findViewById(R.id.digi);
        feedback=(LinearLayout)findViewById(R.id.feedback);
        events=(LinearLayout)findViewById(R.id.events);
        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,FeesDetails.class);
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Notifications.class);
                startActivity(intent);
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Events.class);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Attendance.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Feedback.class);
                startActivity(intent);
            }
        });

        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Exam.class);
                startActivity(intent);
            }
        });

        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Timetable.class);
                startActivity(intent);
            }
        });
    }
}
