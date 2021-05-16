package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.FiltersPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.LibraryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolDashBoard extends AppCompatActivity {
    private LinearLayout fees,notifications,attendance,timetable,exam,digi,feedback,feedback_t,events,events_t,stud_layout,teacher_layout,notifications_t,attendance_t,marks_t,digi_t,self_attendance_t,timetable_t;
    private TextView user,user_t;
    ViewGroup viewGroup;
    FilterIniClass filtersini;




    loginPoJo.Stud_Data stud_data;
    ApiInterface apiInterface;
    Button lms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_dash_board);

        fees=(LinearLayout)findViewById(R.id.fees);
        lms=(Button)findViewById(R.id.lms);
        stud_layout=(LinearLayout)findViewById(R.id.stud_layout);
        teacher_layout=(LinearLayout)findViewById(R.id.teacher_layout);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        lms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,DigiLibrary.class);
                intent.putExtra("url","https://evilms.in/public/");
                startActivity(intent);
            }
        });

        StudSharedPref s=new StudSharedPref(SchoolDashBoard.this);
         stud_data=s.getSharedData();

        if(stud_data.getUser_type().equals("TEACHER")){
            stud_layout.setVisibility(View.GONE);
            viewGroup = findViewById(android.R.id.content);
            //then we will inflate the custom alert dialog xml that we created


        }else if(stud_data.getUser_type().equals("STUDENT")){
            teacher_layout.setVisibility(View.GONE);
            stud_layout.setVisibility(View.VISIBLE);
        }


        self_attendance_t =(LinearLayout)findViewById(R.id.self_attendance_t);
        timetable_t=(LinearLayout)findViewById(R.id.timetable_t);
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
        feedback_t=(LinearLayout)findViewById(R.id.feedback_t);
        events_t=(LinearLayout)findViewById(R.id.events_t);

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

        self_attendance_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Attendance.class);
                startActivity(intent);
            }
        });
        timetable_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Timetable.class);
                startActivity(intent);
            }
        });

        notifications_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,TeacherSendNotification.class);
                startActivity(intent);
            }
        });

        events_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,Events.class);
                startActivity(intent);
            }
        });



        marks_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtersini=new FilterIniClass(SchoolDashBoard.this,viewGroup,stud_data.getS_id(),stud_data.getURL());
            }
        });

        attendance_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this,TeacherAtt.class);
                startActivity(intent);
            }
        });

        digi_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchoolDashBoard.this, Library.class);
                startActivity(intent);
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

        feedback_t.setOnClickListener(new View.OnClickListener() {
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
