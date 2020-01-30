package com.smsoft.evischoolmanagementapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.PoJo.TimeTablePoJo;
import com.smsoft.evischoolmanagementapp.R;
import com.smsoft.evischoolmanagementapp.Timetable;

import org.w3c.dom.Text;

import java.util.List;

/*
Created By Amit Baddi On 2020-01-22
*/
public class TimetableAdapter extends ArrayAdapter<TimeTablePoJo.PeriodData> {
    Context mContext;
    List<TimeTablePoJo.PeriodData> mList;
    public TimetableAdapter(Context context, List<TimeTablePoJo.PeriodData> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TimeTablePoJo.PeriodData obj=mList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.timetable_layout, parent, false);
        }
        TextView time=(TextView)convertView.findViewById(R.id.sub_time);
        TextView teacher=(TextView)convertView.findViewById(R.id.sub_teacher);
        TextView subject=(TextView)convertView.findViewById(R.id.sub_name);

        time.setText(obj.getTime());
        teacher.setText(obj.getTeacher());
        subject.setText(obj.getSubject());
        if(!obj.getAlter().equals("0")){
            teacher.setText(obj.getNewTeacher());
            subject.setText(obj.getNewSubject());
            time.setTextColor(R.color.colorPrimaryDark);
            teacher.setTextColor(R.color.colorPrimaryDark);
            subject.setTextColor(R.color.colorPrimaryDark);
        }
        return convertView;
    }
}
