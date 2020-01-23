package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.notificationsPoJo;
import com.smsoft.evischoolmanagementapp.adapter.notificationAdapter;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {

    ArrayList<notificationsPoJo.notifications> data;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        listView=(ListView)findViewById(R.id.listview);
        data=new ArrayList<>();

        String d="In a RGB color space, hex #000000 (also known as Black) is composed of 0% red, 0% green and 0% blue. Whereas in a CMYK color space, it is composed of 0% cyan, 0% magenta, 0% yellow and 100% black. It has a hue angle of 0 degrees, a saturation of 0% and a lightness of 0%. #000000 color hex could be obtained by blending #000000 with #000000. #000000 (or #000) is a websafe color.";


        for(int i=1;i<=5;i++){
            notificationsPoJo.notifications obj=new notificationsPoJo.notifications(String.valueOf("2020-11-"+i),"Title 1",d);
            data.add(obj);
        }
        notificationAdapter adapter=new notificationAdapter(Notifications.this,data);
        listView.setAdapter(adapter);






    }
}
