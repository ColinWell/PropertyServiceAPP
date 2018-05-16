package com.antonioleiva.mvpexample.app.payment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.PaymentService;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PaymentStateActivity extends AppCompatActivity {

    private TextView propertyState;
    private TextView parkingState;
    private TextView utilitiesState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_state);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        propertyState = (TextView) findViewById(R.id.propertyState);
        parkingState =(TextView) findViewById(R.id.parkingState);
        utilitiesState = (TextView) findViewById(R.id.utilitiesState);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runChildThread();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        runChildThread();

    }
    public void runChildThread(){
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String result = data.getString("result");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if(jsonObject.getInt("propertyState")==1){
                        propertyState.setText("已缴纳");
                    }
                    else{
                        propertyState.setText("未缴纳");
                    }
                    if(jsonObject.getInt("parkingState")==1){
                        parkingState.setText("已缴纳");
                    }
                    else{
                        parkingState.setText("未缴纳");
                    }
                    if(jsonObject.getInt("utilitiesState")==1){
                        utilitiesState.setText("已缴纳");
                    }
                    else{
                        utilitiesState.setText("未缴纳");
                    }
                }catch (JSONException e){
                    System.out.println(e.getMessage());
                }

            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                Bundle data = new Bundle();

                Retrofit retrofit = MyRetrofit.getInstance();
                PaymentService paymentService = retrofit.create(PaymentService.class);
                Call<ResponseBody> call = paymentService.getOrderList(MyApplication.getInstance().getUserId());
                try {
                    Response<ResponseBody> response = call.execute();
                    String result = response.body().string();
                    data.putString("result",result);
                    msg.setData(data);
                    handler.sendMessage(msg);
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",1);
        setResult(id);
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        //switch (item.getItemId()) {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",1);
        setResult(id);
        return super.onOptionsItemSelected(item);

    }
}
