package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.ExamPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.ExamsListPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.ExamAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Exam extends AppCompatActivity {
    ListView listView;
    Spinner spinner,terms;

    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;

    List<String> name=null;
    List<String> type=null;
    List<String> term=null;

    String[] data={"Term 1","Term 2"};
    String t="",ExamName="",ExamType="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);



        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        StudSharedPref s=new StudSharedPref(Exam.this);
        stud_data=s.getSharedData();

        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);

        listView=(ListView)findViewById(R.id.listview);
        spinner=(Spinner)findViewById(R.id.spinner);
        terms=(Spinner)findViewById(R.id.spinner2);


        term=new ArrayList<>();
        name=new ArrayList<>();
        type=new ArrayList<>();



        for(int i=0;i<data.length;i++){
            term.add(data[i]);
        }

        getList();
}

    private void getList(){
        Call<ExamsListPoJo> call=apiInterface.getExamList(stud_data.getClassds(),stud_data.getDivision(),stud_data.getURL(),stud_data.getRoll_number());
        call.enqueue(new Callback<ExamsListPoJo>() {
            @Override
            public void onResponse(Call<ExamsListPoJo> call, Response<ExamsListPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        for(int i = 0;i<response.body().getData().size();i++){
                                      name.add(response.body().getData().get(i).getExam());
                                       type.add(response.body().getData().get(i).getType());
                        }

                        ArrayAdapter mAdapter=new ArrayAdapter(Exam.this,android.R.layout.simple_spinner_item,name);
                        spinner.setAdapter(mAdapter);
                        int initialSelectedPosition=spinner.getSelectedItemPosition();
                        spinner.setSelection(initialSelectedPosition, false);



                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                ExamName= (String) parent.getItemAtPosition(position);
                                ExamType=type.get(name.indexOf(parent.getItemAtPosition(position)));
                                if(type.get(name.indexOf(parent.getItemAtPosition(position))).equals("Normal")){
                                    terms.setVisibility(View.GONE);
                                    getMarks(ExamName,ExamType,t);
                                }else{
                                    ArrayAdapter mAdapter=new ArrayAdapter(Exam.this,android.R.layout.simple_spinner_item,term);
                                    terms.setAdapter(mAdapter);
                                    int initialSelectedPosition=terms.getSelectedItemPosition();
                                    terms.setSelection(initialSelectedPosition, false);
                                    terms.setVisibility(View.VISIBLE);

                                    terms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            if(parent.getItemAtPosition(position).equals("Term 1")){
                                                t="1";
                                                getMarks(ExamName,ExamType,t);
                                            }else{
                                                t="2";
                                                getMarks(ExamName,ExamType,t);
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });

                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }else
                    {
                        Toast.makeText(Exam.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<ExamsListPoJo> call, Throwable t) {

            }
        });
    }

    private void getMarks(String mExamName,String mExamType,String mt){

        Call<ExamPoJo> call=apiInterface.getExamResults(stud_data.getClassds(),stud_data.getDivision(),stud_data.getURL(),stud_data.getRoll_number(),
                mExamName,mExamType,mt,stud_data.getS_id());
        call.enqueue(new Callback<ExamPoJo>() {
            @Override
            public void onResponse(Call<ExamPoJo> call, Response<ExamPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){

                        Log.d("trace",String.valueOf(response.body().getExamData().size()));
                      ExamAdapter adapter=new ExamAdapter(Exam.this,response.body().getExamData());
                        listView.setAdapter(adapter);


                     }else
                    {
                        Toast.makeText(Exam.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<ExamPoJo> call, Throwable t) {

            }
        });
    }



}
