package com.smsoft.evischoolmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.EventsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events extends AppCompatActivity {
    Calendar calendar;
    CalendarView calendarView;
    String selectedDate;
    LinearLayout main;
    TextView date,message;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        StudSharedPref s=new StudSharedPref(Events.this);
        stud_data=s.getSharedData();
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        calendarView = findViewById(R.id.calendarView);

        date=(TextView)findViewById(R.id.date);
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




        Date today = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
          selectedDate = dateFormat.format(today);

        date.setText("Date : "+selectedDate);



        getEvents(selectedDate);







        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {


                selectedDate=i +"-"+(i1 + 1)+"-" +i2;
                date.setText("Date : "+selectedDate);
                //String msg =  i +"-"+(i1 + 1)+"-" +i2;
                getEvents(selectedDate);


            }
        });
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
                    }else{
                        Toast.makeText(Events.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Events.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventsPoJo> call, Throwable t) {

            }
        });
    }
}
