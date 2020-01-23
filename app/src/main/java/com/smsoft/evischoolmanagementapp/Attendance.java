package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.adapter.Att_w_adapter;

import java.util.ArrayList;
import java.util.List;

public class Attendance extends AppCompatActivity {

    List<Att_WholeYearPoJo.AttData> data=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        listView=(ListView)findViewById(R.id.listview);

        String[] months={"June", "July", "August", "September", "October",
                "November", "December","January" , "February" , "March" , "April", "May"};

        for(int i=0;i<months.length;i++){
            Att_WholeYearPoJo.AttData obj=new Att_WholeYearPoJo.AttData(months[i],"25","5","5");
            data.add(obj);
        }
        Att_w_adapter adapter=new Att_w_adapter(Attendance.this,data);
        listView.setAdapter(adapter);



    }
}
