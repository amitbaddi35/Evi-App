package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.smsoft.evischoolmanagementapp.PoJo.LibraryBooksPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.LibraryAdapter;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Library extends AppCompatActivity {
    TextView digi,add_digi;
    com.google.android.material.textfield.TextInputEditText search;
    ListView listView;
    LibraryAdapter adapter;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        StudSharedPref s=new StudSharedPref(Library.this);
        stud_data=s.getSharedData();


        digi=(TextView)findViewById(R.id.digi);
        add_digi=(TextView)findViewById(R.id.add_digi);

        if(stud_data.getUser_type().equals("TEACHER")){
            add_digi.setVisibility(View.VISIBLE);
        }

        add_digi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Library.this,DigiLibraryUpload.class);
                startActivity(intent);
            }
        });



        search=(com.google.android.material.textfield.TextInputEditText)findViewById(R.id.search);
        listView=(ListView)findViewById(R.id.listview);

        digi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Library.this,DigiLibrary.class);
                startActivity(intent);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count >= 3){
                    getBooks(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getBooks(String key){
        Call<LibraryBooksPoJo> call=apiInterface.Library(key,stud_data.getURL());
        call.enqueue(new Callback<LibraryBooksPoJo>() {
            @Override
            public void onResponse(Call<LibraryBooksPoJo> call, Response<LibraryBooksPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){

                        adapter=new LibraryAdapter(Library.this,response.body().getData());
                        listView.setAdapter(adapter);

                        //Toast.makeText(Library.this, R.string.error_message, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Library.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LibraryBooksPoJo> call, Throwable t) {
                Toast.makeText(Library.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
