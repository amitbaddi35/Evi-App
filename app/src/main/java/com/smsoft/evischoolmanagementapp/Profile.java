package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.simplePoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.validation.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    loginPoJo.Stud_Data stud_data;
    Validation val;
    ImageView submit,logout;
    StudSharedPref s;
    ApiInterface apiInterface;
    com.google.android.material.textfield.TextInputEditText name,clas,div,roll,sex,mobile,dob,caste,category,religion,father,mother,username,password,password2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
         s=new StudSharedPref(Profile.this);
        stud_data=s.getSharedData();
        val=new Validation();
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        name=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.name);
        clas=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.cls);
        div=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.div);
        roll=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.roll);
        sex=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.sex);
        mobile=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.mobile);
        submit=(ImageView)findViewById(R.id.submit);
        logout=(ImageView)findViewById(R.id.logout);


        caste=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.caste);
        category=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.category);
        religion=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.religion);
        father=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.father);
        mother=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.mother);

        username=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.username);
        password=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.password1);
        password2=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.password2);

        name.setText(stud_data.getName());
        clas.setText(stud_data.getClassds());
        div.setText(stud_data.getDivision());
        roll.setText(stud_data.getRoll_number());
        sex.setText(stud_data.getSex());
        mobile.setText(stud_data.getCommunication_number());

        caste.setText(stud_data.getCaste());
        category.setText(stud_data.getCategory());
        religion.setText(stud_data.getReligion());
        father.setText(stud_data.getFather_Name());
        mother.setText(stud_data.getMother_Name());
        username.setText(stud_data.getUsername());
        password.setText(stud_data.getPassword());
        password2.setText(stud_data.getPassword());

        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                submit.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val.IsPasswordMatch(password,password2) & val.isPasswordChanged(password,stud_data.getPassword())){
                        changePassword();
                 }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
      }

      private void changePassword(){
          Call<simplePoJo> call=apiInterface.changePassword(stud_data.getS_id(),
                                                            password.getText().toString(),
                                                            stud_data.getURL());
          call.enqueue(new Callback<simplePoJo>() {
              @Override
              public void onResponse(Call<simplePoJo> call, Response<simplePoJo> response) {
                  if(String.valueOf(response.code()).equals("200")){
                      if(response.body().getSuccess().equals("true")){
                         stud_data.setPassword(password.getText().toString());
                         s.setSharedData(stud_data);
                         logout();
                      }else{
                          Toast.makeText(Profile.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                      }
                  }else{
                      Toast.makeText(Profile.this,String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                  }

              }
              @Override
              public void onFailure(Call<simplePoJo> call, Throwable t) {
                  Toast.makeText(Profile.this, t.toString(), Toast.LENGTH_SHORT).show();
              }
          });
      }


      private void logout(){
            stud_data.setSchoolName("");
            s.setSharedData(stud_data);
            Intent intent=new Intent(this,schoolLogin.class);
            startActivity(intent);
            finish();
      }
}
