package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.feesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeesDetails extends AppCompatActivity {
    private ListView listView;
    List<feesPoJo.Feesdata> data=null;
    Button pay;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_details);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        StudSharedPref s=new StudSharedPref(FeesDetails.this);
         stud_data=s.getSharedData();

        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        TextView pay=(Button)findViewById(R.id.pay_btn);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FeesDetails.this,DigiLibrary.class);
                intent.putExtra("url",stud_data.getURL()+"/app_web_payment.php?Mobile="+stud_data.getCommunication_number()+"&Code="+stud_data.getSchoolCode());
                startActivity(intent);
                finish();
            }
        });

        data=new ArrayList<feesPoJo.Feesdata>();
        listView=(ListView)findViewById(R.id.listview);
        getFees();







    }

    private void getFees(){
        Call<feesPoJo> call=apiInterface.FeesDetails(stud_data.getRegister_number(),stud_data.getURL());
        call.enqueue(new Callback<feesPoJo>() {
            @Override
            public void onResponse(Call<feesPoJo> call, Response<feesPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        feesAdapter adapter=new feesAdapter(FeesDetails.this,response.body().getFeesData());
                        listView.setAdapter(adapter);
                    }else
                    {
                        Toast.makeText(FeesDetails.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<feesPoJo> call, Throwable t) {

            }
        });
    }
}
