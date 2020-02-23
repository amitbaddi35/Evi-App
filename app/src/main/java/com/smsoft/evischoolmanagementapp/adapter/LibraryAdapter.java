package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.AttendanceDayWise;
import com.smsoft.evischoolmanagementapp.BookView;
import com.smsoft.evischoolmanagementapp.PoJo.Att_WholeYearPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.LibraryBooksPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.LibrarySingleBookPoJo;
import com.smsoft.evischoolmanagementapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
Created By Amit Baddi On 2020-02-15
*/
public class LibraryAdapter extends ArrayAdapter<LibraryBooksPoJo.books> {
    Context mContext;
    List<LibraryBooksPoJo.books> mList=new ArrayList<>();

    public LibraryAdapter(Context context, List<LibraryBooksPoJo.books> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final LibraryBooksPoJo.books obj=mList.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.library_layout, parent, false);
        }
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView status=(TextView)convertView.findViewById(R.id.status);
        LinearLayout mLayout=(LinearLayout)convertView.findViewById(R.id.main);

        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, BookView.class);
                intent.putExtra("bookId",obj.getId());
                mContext.startActivity(intent);
            }
        });

        name.setText(obj.getName());
        status.setText(obj.getStatus());

        if(obj.getStatus().equals("0")){
           status.setText("Avilable");
        }else{
            status.setText("Issued");
        }

        return convertView;
    }




}
