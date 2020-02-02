package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.notificationsPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.feesAdapter;
import com.smsoft.evischoolmanagementapp.adapter.notificationAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notifications extends AppCompatActivity {

    ArrayList<notificationsPoJo.notifications> data;
    ListView listView;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        StudSharedPref s=new StudSharedPref(Notifications.this);
        stud_data=s.getSharedData();

        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);

        listView=(ListView)findViewById(R.id.listview);
        data=new ArrayList<>();
        getNoti();

     }

    private void getNoti(){
        Call<notificationsPoJo> call=apiInterface.get_notification(stud_data.getClassds(),stud_data.getDivision(),stud_data.getURL());
        call.enqueue(new Callback<notificationsPoJo>() {
            @Override
            public void onResponse(Call<notificationsPoJo> call, Response<notificationsPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
//                        Toast.makeText(Notifications.this, String.valueOf(response.body().getData().size()), Toast.LENGTH_SHORT).show();

                        notificationAdapter adapter=new notificationAdapter(Notifications.this,response.body().getData());
                        listView.setAdapter(adapter);
                    }else
                    {
                        Toast.makeText(Notifications.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //Toast.makeText(Notifications.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<notificationsPoJo> call, Throwable t) {
                Toast.makeText(Notifications.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }



}
