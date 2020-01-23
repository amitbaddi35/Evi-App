package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.adapter.feesAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeesDetails extends AppCompatActivity {
    private ListView listView;
    ArrayList<feesPoJo.Feesdata> data=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_details);
        data=new ArrayList<>();
        listView=(ListView)findViewById(R.id.listview);

        for(int i=1;i<=5;i++){
            feesPoJo.Feesdata obj=new feesPoJo.Feesdata("Head-"+i,String.valueOf(2000*i),String.valueOf(1000*i),String.valueOf(500*i),"Sch"+i,String.valueOf(1000*i));
            data.add(obj);
        }
        feesAdapter adapter=new feesAdapter(FeesDetails.this,data);
        listView.setAdapter(adapter);






    }
}
