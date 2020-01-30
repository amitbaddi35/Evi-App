package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.notificationsPoJo;
import com.smsoft.evischoolmanagementapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



public class notificationAdapter extends ArrayAdapter<notificationsPoJo.notifications> {
    List<notificationsPoJo.notifications> mList;
    Context mContext;
    public notificationAdapter(Context context, List<notificationsPoJo.notifications> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        notificationsPoJo.notifications obj=mList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_layout, parent, false);
        }
        TextView date=(TextView)convertView.findViewById(R.id.date);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView description=(TextView)convertView.findViewById(R.id.disc);

        date.setText(obj.getDate());
        title.setText(obj.getTitle());
        description.setText(obj.getDiscription());
        return convertView;
    }
}
