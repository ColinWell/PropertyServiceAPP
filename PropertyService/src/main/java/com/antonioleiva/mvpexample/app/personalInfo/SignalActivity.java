package com.antonioleiva.mvpexample.app.personalInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.UserService;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignalActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal);
        View contentView = getLayoutInflater().inflate(R.layout.activity_signal,null);
        progressBar = (ProgressBar) findViewById(R.id.progress_done);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_signal);
        toolbar.setTitle("");

        //toolbar.addView(title);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToParent();
            }
        });

        final EditText signalEt = (EditText) findViewById(R.id.signal_Et);
        signalEt.setText(MyApplication.getInstance().getSignal());


        //清除EditText输入的图标
        final ImageView clearIv = (ImageView) findViewById(R.id.iv_clear);
        clearIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalEt.setText(null);
            }
        });

        /*
         * 设置文本输入变化监听
         */
        signalEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    clearIv.setVisibility(View.VISIBLE);
                }else{
                    clearIv.setVisibility(View.GONE);
                }
            }
        });

        TextView toolbarDone = (TextView)findViewById(R.id.toolbar_done);

        toolbarDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String signal = signalEt.getText().toString();
                Retrofit retrofit = MyRetrofit.getInstance();
                UserService userService = retrofit.create(UserService.class);
                Call<ResponseBody> call = userService.setSignal(MyApplication.getInstance().getUserId(),signal);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try{
                            String result = response.body().string();
                            if(result.equals("true")){
                                MyApplication.getInstance().setSignal(signal);
                                Toast.makeText(SignalActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                                backToParent();
                            }
                            else{
                                Toast.makeText(SignalActivity.this,"修改失败,请检查网络环境",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }catch (IOException e){

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                backToParent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",3);
        setResult(id);
        super.onBackPressed();
    }

    private void backToParent(){
        Intent intent = getIntent();
        setResult(3);
        super.onBackPressed();
    }
}
