package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.PoJo.Att_Day_Wise_PoJo;
import com.smsoft.evischoolmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

/*
Created By Amit Baddi On 2020-02-10
*/
public class Attendance_Day_Wise_Adapter extends ArrayAdapter<Att_Day_Wise_PoJo.AttData> {

    Context mContext;
    List<Att_Day_Wise_PoJo.AttData> mList=new ArrayList<>();

    public Attendance_Day_Wise_Adapter(Context context, List<Att_Day_Wise_PoJo.AttData> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Att_Day_Wise_PoJo.AttData obj=mList.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.daywise_att_layout, parent, false);
        }
        TextView Date=(TextView)convertView.findViewById(R.id.Date);
        TextView Sub=(TextView)convertView.findViewById(R.id.Sub);
        TextView Status=(TextView)convertView.findViewById(R.id.Status);
        TextView In=(TextView)convertView.findViewById(R.id.In);
        TextView Out=(TextView)convertView.findViewById(R.id.Out);

        Date.setText(obj.getDay());
        Sub.setText(obj.getSubject());
        Status.setText(obj.getStatus().toString().substring(0,2).toUpperCase());
        if(obj.getIn().equals("00:00:00")){
            In.setText("");
        }else{
            In.setText(obj.getIn());
        }
        if(obj.getOut().equals("00:00:00")){
            Out.setText("");
        }else{
            Out.setText(obj.getOut());
        }
            return convertView;
    }
}
