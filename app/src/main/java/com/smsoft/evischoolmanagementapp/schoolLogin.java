package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;

import java.util.ArrayList;

public class schoolLogin extends AppCompatActivity {
    ArrayAdapter<SchoolList.schoolData> adapter;
    ArrayList<SchoolList.schoolData> list;
    Button submit;

    com.google.android.material.textfield.TextInputEditText username,password;
    AutoCompleteTextView schoolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_login);

        username=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.username);
        password=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.password);
        schoolList=(AutoCompleteTextView)findViewById(R.id.schoolName);

        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(schoolLogin.this,SchoolDashBoard.class);
                startActivity(intent);
            }
        });



        list=new ArrayList<SchoolList.schoolData>();
        SchoolList.schoolData s=new SchoolList.schoolData("DEMO","Demo Institite");

         list.add(s);
        adapter=new ArrayAdapter<SchoolList.schoolData>(schoolLogin.this,android.R.layout.simple_list_item_1,list);
        schoolList.setAdapter(adapter);
    }
}
