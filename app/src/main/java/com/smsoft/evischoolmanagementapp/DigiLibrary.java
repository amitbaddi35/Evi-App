package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;

public class DigiLibrary extends AppCompatActivity {
    ProgressBar progressbar;
    private WebView webView;
    private String url="";
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_library);
        StudSharedPref s=new StudSharedPref(DigiLibrary.this);
        stud_data=s.getSharedData();
        webView=(WebView)findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.paybar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Log.d("trace",extras.getString("HeadId"));
           url= extras.getString("url");
           Log.d("trace","URL"+extras.getString("url"));
        }else{
            url=stud_data.getURL()+"/student_management_system/student/Contents/index.php";
        }




        // Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // Set Render Priority To High
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressbar.setVisibility(view.VISIBLE);
                //setTitle("Loading.....");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(url.contains("logout1.php")){
                    finish();
                }
                if (url.startsWith("upi://")) {

                    webView.stopLoading();
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        Intent chooser = Intent.createChooser(intent, "Pay with...");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            startActivity(chooser);
                        }
                    } catch (android.content.ActivityNotFoundException ex) {
                        String MakeShortText = "PhonePe have not been installed";
                    }
                }
                progressbar.setVisibility(view.GONE);
                //setTitle(view.getTitle());
            }

            @SuppressLint("RestrictedApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });



    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
            finish();
        }
    }
}
