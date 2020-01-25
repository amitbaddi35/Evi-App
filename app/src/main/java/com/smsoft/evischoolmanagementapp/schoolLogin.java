package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.validation.Validation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class schoolLogin extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    List<String> SchoolNames;
    List<String> SchoolURLs;
    Button submit;
    ApiInterface apiInterface;

    com.google.android.material.textfield.TextInputEditText username,password;
    AutoCompleteTextView schoolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_login);

        SchoolNames=new ArrayList<String>();
        SchoolURLs=new ArrayList<String>();
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        getSchools();
        username=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.username);
        password=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.password);
        schoolList=(AutoCompleteTextView)findViewById(R.id.schoolName);

        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation val=new Validation();
                    if(val.text_field_validation(username) & val.text_field_validation(password)){
                        signin();
                    }



            }
        });











    }

    private void signin(){
        Call<loginPoJo> call=apiInterface.login(username.getText().toString(),password.getText().toString(),"http://demo.smsoft.in");
        call.enqueue(new Callback<loginPoJo>() {
            @Override
            public void onResponse(Call<loginPoJo> call, Response<loginPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        Intent intent=new Intent(schoolLogin.this,SchoolDashBoard.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(schoolLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }else if(!String.valueOf(response.code()).equals("200")){
                    Toast.makeText(schoolLogin.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginPoJo> call, Throwable t) {
                Toast.makeText(schoolLogin.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSchools(){
        Call<SchoolList> call=apiInterface.schoollist();
        call.enqueue(new Callback<SchoolList>() {
            @Override
            public void onResponse(Call<SchoolList> call, Response<SchoolList> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        for(int i=0;i<response.body().getData().size();i++){
                            SchoolNames.add(response.body().getData().get(i).getSchoolName());
                            SchoolURLs.add(response.body().getData().get(i).getSchoolCode());
                            adapter=new ArrayAdapter<String>(schoolLogin.this,android.R.layout.simple_list_item_1,SchoolNames);
                            schoolList.setAdapter(adapter);



                        }
                    }else{
                        Toast.makeText(schoolLogin.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(schoolLogin.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<SchoolList> call, Throwable t) {

            }
        });

    }





}
