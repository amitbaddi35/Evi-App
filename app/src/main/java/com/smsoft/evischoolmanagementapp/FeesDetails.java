package com.smsoft.evischoolmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smsoft.evischoolmanagementapp.PoJo.SchoolList;
import com.smsoft.evischoolmanagementapp.PoJo.feesPoJo;
import com.smsoft.evischoolmanagementapp.PoJo.loginPoJo;
import com.smsoft.evischoolmanagementapp.SharedPref.StudSharedPref;
import com.smsoft.evischoolmanagementapp.adapter.feesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeesDetails extends AppCompatActivity {
    private ListView listView;
    List<feesPoJo.Feesdata> data=null;
    Button pay;
    ApiInterface apiInterface;
    loginPoJo.Stud_Data stud_data;
    final int UPI_PAYMENT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_details);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        StudSharedPref s=new StudSharedPref(FeesDetails.this);
         stud_data=s.getSharedData();

        TextView schoolname=(TextView)findViewById(R.id.schoolName);
        TextView pay=(Button)findViewById(R.id.pay_btn);
        schoolname.setText(stud_data.getSchoolName());
        schoolname.setSelected(true);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(FeesDetails.this,DigiLibrary.class);
                intent.putExtra("url",stud_data.getURL()+"/app_web_payment.php?Mobile="+stud_data.getCommunication_number()+"&Code="+stud_data.getSchoolCode());
                startActivity(intent);
                finish();*/
                payUsingUpi("Amit","9739833786@ybl", "Fees Payment", "1");

            }
        });

        data=new ArrayList<feesPoJo.Feesdata>();
        listView=(ListView)findViewById(R.id.listview);
        getFees();

    }

    private void getFees(){
        Call<feesPoJo> call=apiInterface.FeesDetails(stud_data.getRegister_number(),stud_data.getURL());
        call.enqueue(new Callback<feesPoJo>() {
            @Override
            public void onResponse(Call<feesPoJo> call, Response<feesPoJo> response) {
                if(String.valueOf(response.code()).equals("200")){
                    if(response.body().getSuccess().equals("true")){
                        feesAdapter adapter=new feesAdapter(FeesDetails.this,response.body().getFeesData());
                        listView.setAdapter(adapter);
                    }else
                    {
                        Toast.makeText(FeesDetails.this, response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<feesPoJo> call, Throwable t) {

            }
        });
    }
    void payUsingUpi(  String name,String upiId, String note, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                //.appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                .appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Log.d("trace", String.valueOf(uri));
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(FeesDetails.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("trace", "response "+resultCode+"/Request Code"+requestCode);
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("trace", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("trace", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.d("trace", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(FeesDetails.this)) {
            String str = data.get(0);
            Log.d("trace",str);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(FeesDetails.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.e("UPIPaymentLog", "payment successfull: "+approvalRefNo);

            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(FeesDetails.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPIPaymentLog", "Cancelled by user: "+approvalRefNo);
            }
            else {
                Toast.makeText(FeesDetails.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPIPaymentLog", "failed payment: "+approvalRefNo);
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(FeesDetails.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
