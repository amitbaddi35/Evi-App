package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.iid.FirebaseInstanceId;
import com.smsoft.evischoolmanagementapp.PoJo.AdsPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    CardView login_card;
    loginPoJo.Stud_Data stud_data;
    TextView schoolName,valid;
    SliderLayout sliderLayout;
    ApiInterface apiInterface;
    ProgressDialog pd;

    @SuppressLint({"ResourceAsColor", "WrongThread"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        pd=new ProgressDialog(Dashboard.this);
        pd.setCancelable(false);
        pd.setTitle(R.string.progrees_title);
        pd.setMessage("Please wait...");
        pd.show();

        login_card=(CardView)findViewById(R.id.login_card);
        schoolName=(TextView)findViewById(R.id.schoolName);
        valid=(TextView)findViewById(R.id.validText);
        sliderLayout = (SliderLayout)findViewById(R.id.slider);


        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        fetchAds();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(5000);
        sliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) Dashboard.this);
        deleteDirectoryTree(this.getCacheDir());




        StudSharedPref s=new StudSharedPref(Dashboard.this);
        stud_data=s.getSharedData();

        if(stud_data.getSchoolName().equals("")){
            login_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Dashboard.this,schoolLogin.class);
                    startActivity(intent);
                }
            });
        }else{
            schoolName.setText(stud_data.getSchoolName());
            schoolName.setTextColor(R.color.green);
            valid.setText("Valid User");
            valid.setTextColor(R.color.green);
            login_card.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Dashboard.this,SchoolDashBoard.class);
                    startActivity(intent);
                }
            });
        }
    }



    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void fetchAds(){
        Call<AdsPoJo> call=apiInterface.getAds();
        call.enqueue(new Callback<AdsPoJo>() {
            @Override
            public void onResponse(Call<AdsPoJo> call, Response<AdsPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        for(int i=0;i<response.body().getData().size();i++)
                        {
                            TextSliderView textSliderView = new TextSliderView(Dashboard.this);
                            textSliderView
                                    .description(response.body().getData().get(i).getDiscription())
                                    .image(response.body().getData().get(i).getAdvtURL())
                                    .setScaleType(BaseSliderView.ScaleType.Fit)
                                    .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) Dashboard.this);

                            textSliderView.bundle(new Bundle());

                            textSliderView.getBundle()
                                    .putString("extra",response.body().getData().get(i).getDiscription());
                            sliderLayout.addSlider(textSliderView);
                        }
                        pd.dismiss();
                    }else {
                        pd.dismiss();
                        Toast.makeText(Dashboard.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    pd.dismiss();
                    Toast.makeText(Dashboard.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdsPoJo> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(Dashboard.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void deleteDirectoryTree(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteDirectoryTree(child);
            }
        }

        fileOrDirectory.delete();
    }
}
