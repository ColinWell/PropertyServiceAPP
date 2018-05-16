package com.antonioleiva.mvpexample.app.Decoration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Repair.RepairActivity;
import com.antonioleiva.mvpexample.app.Service.IDecorationService;
import com.antonioleiva.mvpexample.app.Service.ServiceGenerator;
import com.antonioleiva.mvpexample.app.main.MainActivity;
import com.antonioleiva.mvpexample.app.bean.DecorationApplication;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 85732 on 2018/4/11.
 */

public class DecorationActivity extends Activity implements View.OnClickListener{
    private TextView tvShowDialog;
    private TextView tvShowDialog1;
    private Calendar cal;
    private int year,month,day;
    public DecorationApplication decorationApplication;
    public List<DecorationApplication> list;
    private EditText decorationPhone;
    private EditText decorationName;
    private EditText decorationPlace;
    IDecorationService client= ServiceGenerator.createService(IDecorationService.class);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration);
        decorationApplication=new DecorationApplication();
        decorationName=(EditText)findViewById(R.id.decorationName);
        decorationApplication.setLogname(decorationName.getText().toString());
        decorationPhone=(EditText)findViewById(R.id.decorationPhone);
        decorationApplication.setDecorationApplicationPhone(decorationPhone.getText().toString());
        decorationApplication.setDecorationApplicationStatus(1);
        decorationApplication.setId(1);
        decorationPlace=(EditText)findViewById(R.id.decorationPlace);
        decorationApplication.setDecorationApplicationPlace(decorationPlace.getText().toString());
        //设置装修起止时间1
        getDate();
        tvShowDialog=(TextView) findViewById(R.id.tvShowDialog);
        tvShowDialog.setOnClickListener(this);
        tvShowDialog1=(TextView)findViewById(R.id.tvShowDialog1);
        tvShowDialog1.setOnClickListener(this);
        String string1=tvShowDialog.getContext().toString();
        String string2=tvShowDialog1.getContext().toString();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        Date date2=null;
        try {
           date1=format1.parse(string1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date2=format1.parse(string2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        decorationApplication.setDecorationApplicationStartTime(date1);
        decorationApplication.setDecorationApplicationEndTime(date2);
        decorationApplication.setId(1);
         list=new ArrayList<>();
        list.add(decorationApplication);
        Button decorationSubmit=(Button)findViewById(R.id.decorationSubmit);
        decorationSubmit.setOnClickListener(this);

    }
    public void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(DecorationActivity.this)
                .setTitle("提醒")
                .setMessage("提交成功")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(DecorationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(dialog);
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextColor(Color.BLUE);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    //获取当前日期
    private void getDate() {
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }
    @SuppressLint("CheckResult")
    public void addDecoratioon(String Id, String LogName, String DecorationApplication_Phone, String DecorationApplication_StartTime, String DecorationApplication_EndTime, String DecorationApplication_Place, String DecorationApplication_Status){
        client.addDecoration(Id,LogName, DecorationApplication_Phone,  DecorationApplication_StartTime, DecorationApplication_EndTime,DecorationApplication_Place,DecorationApplication_Status)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if(integer>0){
                            /*
                            AlertDialog.Builder dialog2=new  AlertDialog.Builder(DecorationActivity.this);
                            dialog2.setTitle("提醒")
                                    .setMessage("申请成功")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent=new Intent(DecorationActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                                    */
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvShowDialog:
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        tvShowDialog.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(DecorationActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;
            case R.id.tvShowDialog1:
                DatePickerDialog.OnDateSetListener listener1=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        tvShowDialog1.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog1=new DatePickerDialog(DecorationActivity.this, 0,listener1,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog1.show();
                break;
            case R.id.decorationSubmit:

                showDialog();
                //Id,LogName, DecorationApplication_Phone,  DecorationApplication_StartTime, DecorationApplication_EndTime,DecorationApplication_Place,DecorationApplication_Status
                String id=String.valueOf(decorationApplication.getId());
                String status=String.valueOf(decorationApplication.getDecorationApplicationStatus());
                addDecoratioon(id,decorationName.getText().toString(),decorationPhone.getText().toString(),tvShowDialog.getText().toString(),tvShowDialog1.getText().toString(),decorationPlace.getText().toString(),status);
                break;
            default:
                break;

        }

}

}
