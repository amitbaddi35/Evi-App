package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.feedbackListPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.simplePoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback extends AppCompatActivity {
    Spinner list;
    Button submit;
    com.google.android.material.textfield.TextInputEditText message;
    ApiInterface apiInterface;
    List<feedbackListPoJo.feedbackData> data=new ArrayList<feedbackListPoJo.feedbackData>();

    String selected="";
    loginPoJo.Stud_Data stud_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        submit=(Button)findViewById(R.id.submit);

        list=(Spinner)findViewById(R.id.spinner);
        message=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.message);
        StudSharedPref s=new StudSharedPref(this);
        stud_data=s.getSharedData();

        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);
        feedbacklist();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selected.equals("") && !message.getText().toString().equals("")){
                    sendData(selected);
                }else{
                    Toast.makeText(Feedback.this, "Error occours Check Once", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(Feedback.this, selected+"//"+message.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void feedbacklist(){
        Call<feedbackListPoJo> call=apiInterface.getList(stud_data.getURL());
        call.enqueue(new Callback<feedbackListPoJo>() {
            @Override
            public void onResponse(Call<feedbackListPoJo> call, Response<feedbackListPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        ArrayAdapter adapter = new ArrayAdapter(Feedback.this,android.R.layout.simple_spinner_item,response.body().getData());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        list.setAdapter(adapter);
                        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                selected=parent.getItemAtPosition(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }else{
                        Toast.makeText(Feedback.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Feedback.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<feedbackListPoJo> call, Throwable t) {
                Toast.makeText(Feedback.this, R.string.error_message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void sendData(String Selected){
        Call<simplePoJo> call=apiInterface.feedbackInsert(Selected,message.getText().toString(),
                stud_data.getClassds(),stud_data.getDivision(),stud_data.getName()
                ,stud_data.getCommunication_number(),stud_data.getSex(),stud_data.getURL());
        call.enqueue(new Callback<simplePoJo>() {
            @Override
            public void onResponse(Call<simplePoJo> call, Response<simplePoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        selected="";
                        message.setText("");
                        Toast.makeText(Feedback.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Feedback.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Feedback.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<simplePoJo> call, Throwable t) {
                Toast.makeText(Feedback.this, R.string.error_message, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
