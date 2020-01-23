package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

public class Att_w_adapter extends ArrayAdapter<Att_WholeYearPoJo.AttData> {
    Context mContext;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Att_WholeYearPoJo.AttData obj=mList.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.attendance_layout, parent, false);
        }
        TextView Month=(TextView)convertView.findViewById(R.id.month);
        TextView Present=(TextView)convertView.findViewById(R.id.present);
        TextView Absent=(TextView)convertView.findViewById(R.id.absent);
        TextView Holidays=(TextView)convertView.findViewById(R.id.holiday);

        ImageView submit=(ImageView) convertView.findViewById(R.id.submit);

        Month.setText(obj.getMonth());
        Present.setText(obj.getPresent());
        Absent.setText(obj.getAbsent());
        Holidays.setText(obj.getHoliday());

        return convertView;
    }

    List<Att_WholeYearPoJo.AttData> mList=new ArrayList<>();
    public Att_w_adapter(Context context, List<Att_WholeYearPoJo.AttData> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;

    }


}
