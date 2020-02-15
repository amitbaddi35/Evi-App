package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.Att_Day_Wise_PoJo;
import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.Att_w_adapter;
import com.smsoft.evischoolmanagementapp.adapter.Attendance_Day_Wise_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceDayWise extends AppCompatActivity {
    List<Att_WholeYearPoJo.AttData> data = new ArrayList<>();
    ListView listView;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_day_wise);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        listView = (ListView) findViewById(R.id.listview);
        StudSharedPref s = new StudSharedPref(AttendanceDayWise.this);
        stud_data = s.getSharedData();
        TextView schoolname = (TextView) findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Log.d("Trace",extras.getString("Month"));
            getAtt(extras.getString("Month"));
        }
    }

    private void getAtt(String month) {
        Call<Att_Day_Wise_PoJo> call = apiInterface.get_attendance_daywise(stud_data.getClassds(),stud_data.getDivision(),month, stud_data.getURL(), stud_data.getRoll_number());
        call.enqueue(new Callback<Att_Day_Wise_PoJo>() {
            @Override
            public void onResponse(Call<Att_Day_Wise_PoJo> call, Response<Att_Day_Wise_PoJo> response) {
                if (String.valueOf(response.code()).equals("200")) {
                    if (response.body().getSuccess().equals("true")) {
                        //Toast.makeText(Attendance.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Attendance_Day_Wise_Adapter adapter = new Attendance_Day_Wise_Adapter(AttendanceDayWise.this, response.body().getData());
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(AttendanceDayWise.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AttendanceDayWise.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Att_Day_Wise_PoJo> call, Throwable t) {
                Toast.makeText(AttendanceDayWise.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
