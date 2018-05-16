package com.antonioleiva.mvpexample.app.personalInfo;

import android.content.Intent;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.UserService;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.util.MyDateUtil;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BirthdayActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        View contentView = getLayoutInflater().inflate(R.layout.activity_birthday,null);
        progressBar = (ProgressBar) findViewById(R.id.progress_done);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_birthday);
        toolbar.setTitle("");

        //toolbar.addView(title);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToParent();
            }
        });


        final TextView birthdayTv = (TextView)findViewById(R.id.birthdayTv);
        birthdayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String defaultDate = MyApplication.getInstance().getBirthday();
                int year,month,day;
                if(defaultDate == null){
                    year = 2000;
                    month = 1;
                    day = 1;
                }
                else{
                    String[] temp = defaultDate.split("-");
                    year = Integer.valueOf(temp[0]);
                    month = Integer.valueOf(temp[1])-1;
                    day = Integer.valueOf(temp[2]);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(BirthdayActivity.this, R.style.MyDatePickerDialogTheme, new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        birthdayTv.setText(year+"-"+(++month)+"-"+day);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        TextView toolbarDone = (TextView)findViewById(R.id.toolbar_done);
        toolbarDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String birthday = birthdayTv.getText().toString();
                Retrofit retrofit = MyRetrofit.getInstance();
                UserService userService = retrofit.create(UserService.class);
                Call<ResponseBody> call = userService.setBirthday(MyApplication.getInstance().getUserId(),birthday);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try{
                            String result = response.body().string();
                            if(result.equals("true")){
                                MyApplication.getInstance().setBirthday(birthday);
                                Toast.makeText(BirthdayActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                                backToParent();
                                progressBar.setVisibility(View.GONE);
                            }
                            else{
                                Toast.makeText(BirthdayActivity.this,"修改失败,请检查网络环境",Toast.LENGTH_SHORT).show();
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
