package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smsoft.evischoolmanagementapp.PoJo.ExamPoJo;
import com.smsoft.evischoolmanagementapp.R;

import java.util.List;

/*
Created By Amit Baddi On 2020-01-22
*/
public class ExamAdapter extends ArrayAdapter<ExamPoJo.Examdata> {
    private List<ExamPoJo.Examdata> mList;
    private Context mContext;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExamPoJo.Examdata obj=mList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exam_layout, parent, false);
        }

        TextView subname=(TextView)convertView.findViewById(R.id.subjectname);
        TextView marks=(TextView)convertView.findViewById(R.id.marks);

        subname.setText(obj.getSubject());
        marks.setText(obj.getMarks());
        return convertView;
    }

    public ExamAdapter(Context context, List<ExamPoJo.Examdata> list) {
        super(context, 0,list);
        this.mContext=context;
        this.mList=list;
    }

}
