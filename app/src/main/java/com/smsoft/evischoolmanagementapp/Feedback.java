package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.feedbackListPoJo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Feedback extends AppCompatActivity {
    Spinner list;
    Button submit;
    List<feedbackListPoJo.feedbackData> data=new ArrayList<feedbackListPoJo.feedbackData>();
    com.google.android.material.textfield.TextInputEditText message;
    String selected="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        submit=(Button)findViewById(R.id.submit);

        list=(Spinner)findViewById(R.id.spinner);
        message=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.message);

        String[] lists={"obj1","obj2","obj3","obj4","obj5"};

        for(int i=0;i<lists.length;i++){
            feedbackListPoJo.feedbackData obj=new feedbackListPoJo.feedbackData(String.valueOf(i),lists[i]);
            data.add(obj);
        }

        ArrayAdapter adapter = new ArrayAdapter(Feedback.this,android.R.layout.simple_spinner_item,data);
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Feedback.this, selected+"//"+message.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
