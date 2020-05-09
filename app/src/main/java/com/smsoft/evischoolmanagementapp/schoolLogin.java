package com.smsoft.evischoolmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.validation.Validation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class schoolLogin extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ProgressDialog pd;
    List<String> SchoolNames;
    List<String> SchoolURLs;
    Button submit;
    String url="";
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;


    com.google.android.material.textfield.TextInputEditText username,password;
    AutoCompleteTextView schoolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_login);

        pd=new ProgressDialog(schoolLogin.this);
        pd.setTitle(R.string.progrees_title);
        pd.setMessage("Loading Please wait...");
        pd.setCancelable(false);


        StudSharedPref s=new StudSharedPref(schoolLogin.this);
        stud_data=s.getGlobalData();

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
                    if(val.text_field_validation(username) & val.text_field_validation(password)
                            & val.isElementPresentInList(schoolList,schoolList.getText().toString(),SchoolNames)){
                        pd.show();
                        signin();
                    }
            }
        });

         schoolList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 schoolList.clearFocus();
             }
         });
    }

    private void signin(){
        url= SchoolURLs.get(SchoolNames.indexOf(schoolList.getText().toString()));
        Call<loginPoJo> call=apiInterface.login(username.getText().toString(),password.getText().toString(),url,stud_data.getFcm());
        call.enqueue(new Callback<loginPoJo>() {
            @Override
            public void onResponse(Call<loginPoJo> call, Response<loginPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        StudSharedPref s=new StudSharedPref(schoolLogin.this);
                        loginPoJo.Stud_Data ss=response.body().getData().get(0);
                        ss.setURL(response.body().getDomain());
                        ss.setSchoolName(response.body().getSchoolName());
                        ss.setFcm(s.getGlobalData().getFcm());
                        ss.setSchoolCode(response.body().getSchoolCode());
                        String classTopic=response.body().getSchoolCode()+"-"+response.body().getData().get(0).getClassds()+"-"+response.body().getData().get(0).getDivision();
                        //Subscribe To School Topic and Class/Div Topic

                        FirebaseMessaging.getInstance().subscribeToTopic(response.body().getSchoolCode());
                        FirebaseMessaging.getInstance().subscribeToTopic(classTopic);
                        s.setSharedData(ss);
                        pd.dismiss();
                        Intent intent=new Intent(schoolLogin.this,SchoolDashBoard.class);
                        startActivity(intent);
                        finish();
                    }else {
                        pd.dismiss();
                        Toast.makeText(schoolLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else if(!String.valueOf(response.code()).equals("200")){
                    pd.dismiss();
                    Toast.makeText(schoolLogin.this, R.string.error_message+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginPoJo> call, Throwable t) {
                pd.dismiss();
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

                           submit.setVisibility(View.VISIBLE);
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
                Toast.makeText(schoolLogin.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }





}
