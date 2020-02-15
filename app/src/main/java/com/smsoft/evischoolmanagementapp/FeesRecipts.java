package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.Att_Day_Wise_PoJo;
import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.FeesReciptsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.Attendance_Day_Wise_Adapter;
import com.smsoft.evischoolmanagementapp.adapter.FeesReciptAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeesRecipts extends AppCompatActivity {
    List<FeesReciptsPoJo.ReciptsDetails> data = new ArrayList<>();
    ListView listView;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_recipts);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        listView = (ListView) findViewById(R.id.listview);
        StudSharedPref s = new StudSharedPref(FeesRecipts.this);
        stud_data = s.getSharedData();
        TextView schoolname = (TextView) findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            //Log.d("trace",extras.getString("HeadId"));
           getAtt(extras.getString("HeadId"));
        }



    }

    private void getAtt(String headid) {
        Call<FeesReciptsPoJo> call = apiInterface.getFeesRecipt(stud_data.getRegister_number(), stud_data.getURL(),headid);
        call.enqueue(new Callback<FeesReciptsPoJo>() {
            @Override
            public void onResponse(Call<FeesReciptsPoJo> call, Response<FeesReciptsPoJo> response) {
                if (String.valueOf(response.code()).equals("200")) {
                    if (response.body().getSuccess().equals("true")) {
                        //Toast.makeText(Attendance.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        FeesReciptAdapter adapter = new FeesReciptAdapter(FeesRecipts.this, response.body().getData());
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(FeesRecipts.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FeesRecipts.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeesReciptsPoJo> call, Throwable t) {
                Toast.makeText(FeesRecipts.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
