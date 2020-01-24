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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class schoolLogin extends AppCompatActivity {
    ArrayAdapter<SchoolList.schoolData> adapter;
    ArrayList<SchoolList.schoolData> list;
    Button submit;
    ApiInterface apiInterface;

    com.google.android.material.textfield.TextInputEditText username,password;
    AutoCompleteTextView schoolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_login);

        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        username=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.username);
        password=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.password);
        schoolList=(AutoCompleteTextView)findViewById(R.id.schoolName);

        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
                /*Intent intent=new Intent(schoolLogin.this,SchoolDashBoard.class);
                startActivity(intent);*/
            }
        });





        list=new ArrayList<SchoolList.schoolData>();
        SchoolList.schoolData s=new SchoolList.schoolData("DEMO","Demo Institite");

         list.add(s);
        adapter=new ArrayAdapter<SchoolList.schoolData>(schoolLogin.this,android.R.layout.simple_list_item_1,list);
        schoolList.setAdapter(adapter);
    }

    private void signin(){
        Log.d("trace",username.getText().toString());
        Call<loginPoJo> call=apiInterface.login(username.getText().toString(),password.getText().toString(),"http://demo.smsoft.in");
        call.enqueue(new Callback<loginPoJo>() {
            @Override
            public void onResponse(Call<loginPoJo> call, Response<loginPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        Toast.makeText(schoolLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(schoolLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else if(!String.valueOf(response.code()).equals("200")){
                    Toast.makeText(schoolLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<loginPoJo> call, Throwable t) {
                Toast.makeText(schoolLogin.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
