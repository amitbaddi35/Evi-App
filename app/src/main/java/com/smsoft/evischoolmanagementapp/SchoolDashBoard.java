package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.LibraryAdapter;

public class SchoolDashBoard extends AppCompatActivity {
    private LinearLayout fees,notifications,attendance,timetable,exam,digi,feedback,events,stud_layout,teacher_layout,notifications_t,attendance_t,marks_t,digi_t;
    private TextView user,user_t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_dash_board);

        fees=(LinearLayout)findViewById(R.id.fees);
        stud_layout=(LinearLayout)findViewById(R.id.stud_layout);
        teacher_layout=(LinearLayout)findViewById(R.id.teacher_layout);

        StudSharedPref s=new StudSharedPref(SchoolDashBoard.this);
        final loginPoJo.Stud_Data stud_data=s.getSharedData();

        if(stud_data.getUser_type().equals("TEACHER")){
            stud_layout.setVisibility(View.GONE);
        }else if(stud_data.getUser_type().equals("STUDENT")){
            teacher_layout.setVisibility(View.GONE);
        }

        notifications=(LinearLayout)findViewById(R.id.notification);
        attendance=(LinearLayout)findViewById(R.id.attendance);
        timetable=(LinearLayout)findViewById(R.id.timetable);
        exam=(LinearLayout)findViewById(R.id.exam);
        digi=(LinearLayout)findViewById(R.id.digi);
        feedback=(LinearLayout)findViewById(R.id.feedback);
        events=(LinearLayout)findViewById(R.id.events);
        user=(TextView)findViewById(R.id.user);
        user_t=(TextView)findViewById(R.id.user_t);

        notifications_t=(LinearLayout)findViewById(R.id.notification_t);
        attendance_t=(LinearLayout)findViewById(R.id.attendance_t);
        marks_t=(LinearLayout)findViewById(R.id.marks_t);
        digi_t=(LinearLayout)findViewById(R.id.digilibrary_t);

        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);


        user.setText(stud_data.getName());
        user_t.setText(stud_data.getName());

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Profile.class);
                startActivity(intent);
            }
        });

        user_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Profile.class);
                startActivity(intent);
            }
        });

        notifications_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        marks_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        attendance_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        digi_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stud_data.getRegister_number().equals("")){
                    Toast.makeText(SchoolDashBoard.this, "Contact School to Update Register Number to Access Fees Details", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent=new Intent(SchoolDashBoard.this,FeesDetails.class);
                    startActivity(intent);
                }

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

        digi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this, Library.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SchoolDashBoard.this,Dashboard.class);
        startActivity(intent);
    }
}
