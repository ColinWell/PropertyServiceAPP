package com.antonioleiva.mvpexample.app.personalInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.context.MyApplication;


public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView roomTv = (TextView)findViewById(R.id.room_tv);
        TextView nickNameTv = (TextView)findViewById(R.id.nickname_tv);
        TextView birthdayTv = (TextView)findViewById(R.id.birthday_tv);
        TextView signalTv = (TextView)findViewById(R.id.signal_tv);

        MyApplication application = MyApplication.getInstance();
        roomTv.setText(application.getRoomName());
        nickNameTv.setText(application.getNickName()==null?"尚未设置昵称":application.getNickName());
        birthdayTv.setText(application.getBirthday()==null?"暂无生日信息":application.getBirthday());
        signalTv.setText(application.getSignal()==null?"暂无个人签名":application.getSignal());

        nickNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInfoActivity.this,NicknameActivity.class);
                startActivityForResult(intent,3);
            }
        });
        birthdayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInfoActivity.this,BirthdayActivity.class);
                startActivityForResult(intent,3);
            }
        });
        signalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInfoActivity.this,SignalActivity.class);
                startActivityForResult(intent,3);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",3);
        setResult(id);
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        //switch (item.getItemId()) {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",3);
        setResult(id);
        return super.onOptionsItemSelected(item);
    }
}
