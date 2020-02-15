package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.PoJo.Att_Day_Wise_PoJo;
import com.smsoft.evischoolmanagementapp.PoJo.FeesReciptsPoJo;
import com.smsoft.evischoolmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

/*
Created By Amit Baddi On 2020-02-10
*/public class FeesReciptAdapter  extends ArrayAdapter<FeesReciptsPoJo.ReciptsDetails> {

    Context mContext;
    List<FeesReciptsPoJo.ReciptsDetails> mList=new ArrayList<>();
    public FeesReciptAdapter(Context context, List<FeesReciptsPoJo.ReciptsDetails> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        FeesReciptsPoJo.ReciptsDetails obj=mList.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fees_rec_layout, parent, false);
        }
        TextView rec_num=(TextView)convertView.findViewById(R.id.rec_number);
        TextView date=(TextView)convertView.findViewById(R.id.Date);
        TextView amount=(TextView)convertView.findViewById(R.id.amount);

        rec_num.setText(obj.getReciptName());
        date.setText(obj.getDate());
        amount.setText(obj.getAmount());

        return convertView;
    }
}
