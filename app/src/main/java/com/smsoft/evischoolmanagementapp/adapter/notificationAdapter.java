        package com.smsoft.evischoolmanagementapp.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.notificationsPoJo;
import com.smsoft.evischoolmanagementapp.R;
import com.squareup.picasso.Picasso;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        notificationsPoJo.notifications obj=mList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_layout, parent, false);
        }
        TextView date=(TextView)convertView.findViewById(R.id.date);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView description=(TextView)convertView.findViewById(R.id.disc);
        ImageView img=(ImageView)convertView.findViewById(R.id.image);




        // 0  PDF or Any Other (Link)   1 Text  2 Image
        if(obj.getStatus().equals("0")){

           //description.setVisibility(View.GONE);
            date.setText(obj.getDate());
            description.setText(Html.fromHtml(obj.getDiscription(), Html.FROM_HTML_MODE_COMPACT));
            title.setText(obj.getTitle());
            /*img.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(obj.getDiscription()).into(img);*/
        }else if(obj.getStatus().equals("1")){
            description.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
            date.setText(obj.getDate());
            title.setText(obj.getTitle());
            description.setText(obj.getDiscription());
        }else if(obj.getStatus().equals("2")){
            description.setText(Html.fromHtml(obj.getDiscription(), Html.FROM_HTML_MODE_COMPACT));
            date.setText(obj.getDate());
            description.setText(Html.fromHtml(obj.getDiscription(), Html.FROM_HTML_MODE_COMPACT));
            title.setText(obj.getTitle());
            img.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(obj.getDiscription()).into(img);
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        if(mList.size()==0){
            return 1;
        }
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
