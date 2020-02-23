package com.smsoft.evischoolmanagementapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;


import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.simplePoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    loginPoJo.Stud_Data stud_data;
    ApiInterface apiInterface;
    String currentVersion = null,latestVersion=null;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        new Handler().postDelayed(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
            VersionChecker versionChecker = new VersionChecker();
                try {
                    latestVersion = versionChecker.execute().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                try {
                    currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                if(currentVersion.equals(latestVersion)){
                    Intent HomeIntent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(HomeIntent);
                    StudSharedPref s = new StudSharedPref(MainActivity.this);
                    finish();
                }else{
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Update Avilable..")
                            .setCancelable(false)
                            .setMessage("New Features are avilable Please update the app...")
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Uri uri = Uri.parse("market://details?id=" + MainActivity.this.getPackageName());
                                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                        MainActivity.this.startActivity(goToMarket);
                                    } catch (ActivityNotFoundException e) {
                                        MainActivity.this.startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("http://play.google.com/store/apps/details?id="
                                                        +  MainActivity.this.getPackageName())));
                                    }
                                }
                            })
                            .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(0);
                                }
                            }).show();
                }


            }
        },SPLASH_TIME_OUT);

    }

}
