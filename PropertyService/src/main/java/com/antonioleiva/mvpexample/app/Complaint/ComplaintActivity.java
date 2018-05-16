package com.antonioleiva.mvpexample.app.Complaint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.IComplaint;
import com.antonioleiva.mvpexample.app.Service.ServiceGenerator;
import com.antonioleiva.mvpexample.app.main.MainActivity;
import com.antonioleiva.mvpexample.app.bean.Complaint;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 85732 on 2018/4/16.
 */

public class ComplaintActivity extends Activity implements View.OnClickListener {
    public Complaint complaint;
    private EditText complaintName;
    private EditText complaintPhone;
    private EditText complaintContent;
    IComplaint client= ServiceGenerator.createService(IComplaint.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        complaint=new Complaint();
        complaint.setId(1);
        complaint.setProcessor_Id(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        complaint.setComplaints_Time(date);
        complaintName=(EditText)findViewById(R.id.complaintName);
        complaint.setComplaint_Name(complaintName.getText().toString());
        complaintPhone=(EditText)findViewById(R.id.complaintPhone);
        complaint.setComplaint_Phone(complaintPhone.getText().toString());
        complaintContent=(EditText)findViewById(R.id.complaintContent);
        complaint.setComplaints_Content(complaintContent.getText().toString());
        Button complaintSubmit=(Button)findViewById(R.id.complaintSubmit);
        complaintSubmit.setOnClickListener(this);
    }
    public void addComplaint(String Id,String Processor_Id,String Complaint_Name,String Complaint_Phone,String Complaint_Content){
        client.addComplaint(Id,Processor_Id,Complaint_Name,Complaint_Phone,Complaint_Content)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("success");

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });}

                public void showDialog(){
                    AlertDialog dialog=new  AlertDialog.Builder(ComplaintActivity.this)
                            .setTitle("提醒")
                            .setMessage("提交成功")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent=new Intent(ComplaintActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .show();
                    try {
                        Field mAlert =AlertDialog.class.getDeclaredField("mAlert");
                        mAlert.setAccessible(true);
                        Object mAlertController=mAlert.get(dialog);
                        Field mMessage=mAlertController.getClass().getDeclaredField("mMessageView");
                        mMessage.setAccessible(true);
                        TextView mMessageView=(TextView)mMessage.get(mAlertController);
                        mMessageView.setTextColor(Color.BLUE);

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.complaintSubmit:
                showDialog();
                //Id,Processor_Id,Complaint_Name,Complaint_Phone,Complaint_Content
                String Id=String.valueOf(complaint.getId());
                String pid=String.valueOf(complaint.getProcessor_Id());
                addComplaint(Id,pid,complaintName.getText().toString(),complaintPhone.getText().toString(),complaintContent.getText().toString());
        }
    }
}
