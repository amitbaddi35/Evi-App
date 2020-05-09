package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.smsoft.evischoolmanagementapp.AttendanceDayWise;
import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Att_w_adapter extends ArrayAdapter<Att_WholeYearPoJo.AttData> {
    Context mContext;
    List<Att_WholeYearPoJo.AttData> mList=new ArrayList<>();
    public Att_w_adapter(Context context, List<Att_WholeYearPoJo.AttData> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Att_WholeYearPoJo.AttData obj=mList.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.attendance_layout, parent, false);
        }
        CardView card=(CardView)convertView.findViewById(R.id.card_view);
        TextView Month=(TextView)convertView.findViewById(R.id.month);
        TextView Present=(TextView)convertView.findViewById(R.id.present);
        TextView Absent=(TextView)convertView.findViewById(R.id.absent);
        TextView Holidays=(TextView)convertView.findViewById(R.id.holiday);

        ImageView submit=(ImageView) convertView.findViewById(R.id.submit);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, AttendanceDayWise.class);
                intent.putExtra("Month",obj.getMonthNumber());
                mContext.startActivity(intent);
            }
        });

        Month.setText(obj.getMonth());
        Present.setText(obj.getPresent());
        Absent.setText(obj.getAbsent());
        Holidays.setText(obj.getHoliday());

        return convertView;
    }
}
