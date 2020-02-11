package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.AttendanceDayWise;
import com.smsoft.evischoolmanagementapp.FeesRecipts;
import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

public class feesAdapter extends ArrayAdapter<feesPoJo.Feesdata> {

    List<feesPoJo.Feesdata> mList;
    Context mContext;

    public feesAdapter(Context context, List<feesPoJo.Feesdata> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final feesPoJo.Feesdata obj= mList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fees_layout, parent, false);
        }

        TextView headName=(TextView)convertView.findViewById(R.id.headname);
        TextView t_amt=(TextView)convertView.findViewById(R.id.total_amount);
        TextView p_amt=(TextView)convertView.findViewById(R.id.paid_amount);
        TextView b_amt=(TextView)convertView.findViewById(R.id.balance_amount);

        final LinearLayout layout=(LinearLayout)convertView.findViewById(R.id.add_layout);

        ImageView img=(ImageView)convertView.findViewById(R.id.dropdown);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!obj.getPaidAmount().equals("0")){
                    Intent intent=new Intent(mContext, FeesRecipts.class);
                    intent.putExtra("HeadId",obj.getHeadId());
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext, "Fees Not Paid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                layout.setVisibility(View.GONE);
                return true;
            }
        });


        TextView rept_date=(TextView)convertView.findViewById(R.id.recpt_date);
        TextView rept_amt=(TextView)convertView.findViewById(R.id.recpt_amount);


       // headName.setText(obj.getHeadName());
        headName.setText(obj.getHeadName());
        t_amt.setText(obj.getTotalAmount());
        p_amt.setText(obj.getPaidAmount());
        b_amt.setText(obj.getBalanceAmount());
        rept_date.setText(obj.getRecDate());
        rept_amt.setText(obj.getAmountPaid());



        return convertView;
    }
}
