package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.Att_w_adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Attendance extends AppCompatActivity {

    List<Att_WholeYearPoJo.AttData> data=new ArrayList<>();
    ListView listView;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        listView=(ListView)findViewById(R.id.listview);
        StudSharedPref s=new StudSharedPref(Attendance.this);
        stud_data=s.getSharedData();
        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);

        getAtt();
        String[] months={"June", "July", "August", "September", "October",
                "November", "December","January" , "February" , "March" , "April", "May"};

       /* for(int i=0;i<months.length;i++){
            Att_WholeYearPoJo.AttData obj=new Att_WholeYearPoJo.AttData(months[i],"25","5","5");
            data.add(obj);
        }*/


        /*Att_w_adapter adapter=new Att_w_adapter(Attendance.this,data);
        listView.setAdapter(adapter);*/



    }

    private void getAtt(){
        Call<Att_WholeYearPoJo> call=apiInterface.get_attendance(stud_data.getClassds(),stud_data.getDivision(),stud_data.getURL(),stud_data.getRoll_number());
                call.enqueue(new Callback<Att_WholeYearPoJo>() {
                    @Override
                    public void onResponse(Call<Att_WholeYearPoJo> call, Response<Att_WholeYearPoJo> response) {
                        if(String.valueOf(response.code()).equals("200")){
                            if(response.body().getSuccess().equals("true")){
                                //Toast.makeText(Attendance.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Att_w_adapter adapter=new Att_w_adapter(Attendance.this,response.body().getData());
                                listView.setAdapter(adapter);
                            }else{
                                Toast.makeText(Attendance.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Attendance.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Att_WholeYearPoJo> call, Throwable t) {
                        Toast.makeText(Attendance.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
