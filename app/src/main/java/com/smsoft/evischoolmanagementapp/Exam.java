package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.smsoft.evischoolmanagementapp.PoJo.ExamPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.ExamsListPoJo;
import com.smsoft.evischoolmanagementapp.adapter.ExamAdapter;

import java.util.ArrayList;
import java.util.List;

public class Exam extends AppCompatActivity {
    ListView listView;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        listView=(ListView)findViewById(R.id.listview);
        spinner=(Spinner)findViewById(R.id.spinner);

        List<ExamPoJo.Examdata> list=new ArrayList<>();

        String[] data={"Sub 1","Sub 2","Sub 3","Sub 4","Sub 5"};


        for(int i=0;i<data.length;i++){
            ExamPoJo.Examdata o=new ExamPoJo.Examdata(data[i],String.valueOf(i));
            list.add(o);
        }


        ArrayAdapter mAdapter=new ArrayAdapter(Exam.this,android.R.layout.simple_spinner_item,data);
        spinner.setAdapter(mAdapter);


        ExamAdapter adapter=new ExamAdapter(Exam.this,list);
        listView.setAdapter(adapter);



    }
}
