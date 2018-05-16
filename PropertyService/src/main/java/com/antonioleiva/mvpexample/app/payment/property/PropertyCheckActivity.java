package com.antonioleiva.mvpexample.app.payment.property;

import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.util.MyDateUtil;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PropertyCheckActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String dateBegin;
    private String dateFrom;
    private TextView dateTo;
    private TextView total;
    private Spinner spinner;
    private List data_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_check);

        mTextMessage = (TextView) findViewById(R.id.message);
        TextView pay_detail = (TextView)findViewById(R.id.pay_detail);
        dateTo = (TextView)findViewById(R.id.date_to);
        total = (TextView)findViewById(R.id.total);
        MyApplication application = MyApplication.getInstance();
        pay_detail.append("1、您所居住的单元每月物业费的收费标准为："+application.getPropertyFee()+"元/平米/月" +"\n");
        pay_detail.append("2、物业费缴纳起始日期为");
        pay_detail.append("2018-04-01"+"\n");
        dateFrom = new String("2018-04-01");
        if(dateFrom != null){
            pay_detail.append("3、您上次缴纳物业费至"+dateFrom);
        }


        spinner = (Spinner) findViewById(R.id.month_select);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("1个月");
        data_list.add("2个月");
        data_list.add("3个月");
        data_list.add("半年");
        data_list.add("一年");
        //适配器
        ArrayAdapter arr_adapter= new ArrayAdapter<String>(this, R.layout.my_simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int day = 31;
                if(MyApplication.getInstance().getPropertyFee() == null){
                    Toast.makeText(getApplicationContext(),"暂无用户信息",Toast.LENGTH_SHORT).show();
                    backOfActivity();
                }
                TextView tv = (TextView)view;
                tv.setTextColor(getResources().getColor(R.color.black));
                switch (position){
                    case 0:
                        try {
                            if(dateFrom == null){
                                dateFrom = dateBegin;
                            }
                            dateTo.setText(MyDateUtil.longToString( (MyDateUtil.stringToLong(dateFrom, "yyyy-MM-dd")/(1000*60*60*24)+day*1)*(1000*60*60*24),"yyyy-MM-dd"));
                        }catch (ParseException e){}
                        total.setText(String.valueOf(MyApplication.getInstance().getPropertyFee()*day));
                        break;
                    case 1:
                        try {
                            dateTo.setText(MyDateUtil.longToString( (MyDateUtil.stringToLong(dateFrom, "yyyy-MM-dd")/(1000*60*60*24)+day*2)*(1000*60*60*24),"yyyy-MM-dd"));
                        }catch (ParseException e){}
                        total.setText(String.valueOf(MyApplication.getInstance().getPropertyFee()*day*2));
                        break;
                    case 2:
                        try {
                            dateTo.setText(MyDateUtil.longToString( (MyDateUtil.stringToLong(dateFrom, "yyyy-MM-dd")/(1000*60*60*24)+day*3)*(1000*60*60*24),"yyyy-MM-dd"));
                        }catch (ParseException e){}
                        total.setText(String.valueOf(MyApplication.getInstance().getPropertyFee()*day*3));
                        break;
                    case 3:
                        try {
                            dateTo.setText(MyDateUtil.longToString( (MyDateUtil.stringToLong(dateFrom, "yyyy-MM-dd")/(1000*60*60*24)+day*6)*(1000*60*60*24),"yyyy-MM-dd"));
                        }catch (ParseException e){}
                        total.setText(String.valueOf(MyApplication.getInstance().getPropertyFee()*day*6));
                        break;
                    case 4:
                        try {
                            dateTo.setText(MyDateUtil.longToString( (MyDateUtil.stringToLong(dateFrom, "yyyy-MM-dd")/(1000*60*60*24)+day*12)*(1000*60*60*24),"yyyy-MM-dd"));
                        }catch (ParseException e){}
                        total.setText(String.valueOf(MyApplication.getInstance().getPropertyFee()*day*12));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button checkBtn = (Button)findViewById(R.id.check_btn);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(100);
                progressBar.setProgress(20);
                Toast.makeText(PropertyCheckActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
                backOfActivity();
            }
        });
    }
    private void backOfActivity(){
        Intent intent = new Intent(PropertyCheckActivity.this,PropertyActivity.class);
        startActivity(intent);
        finish();
    }

}

