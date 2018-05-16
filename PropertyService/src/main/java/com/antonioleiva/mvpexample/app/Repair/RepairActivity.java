package com.antonioleiva.mvpexample.app.Repair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.Adapter.MyAdapter;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.IRepairService;
import com.antonioleiva.mvpexample.app.Service.ServiceGenerator;
import com.antonioleiva.mvpexample.app.main.MainActivity;
import com.antonioleiva.mvpexample.app.bean.RepairApplication;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 85732 on 2018/4/11.
 */

public class RepairActivity extends Activity implements View.OnClickListener{
    public RepairApplication repairApplication;
    private EditText repairName;
    private  EditText repairPhone;
    private EditText repairLName;
    private EditText repairPlace;
    IRepairService client= ServiceGenerator.createService(IRepairService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        repairApplication=new RepairApplication();
        Spinner spinner=(Spinner) findViewById(R.id.spinnerRepair);
        final List<String> datas=new ArrayList<>();
        datas.add(0,"公用设备");
        datas.add(1,"家用设备");
        MyAdapter adapter=new MyAdapter(this);
        spinner.setAdapter(adapter);
        adapter.setDatas(datas);
        //选择监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RepairActivity.this, "点击了" + datas.get(i), Toast.LENGTH_SHORT).show();
                repairApplication.setRepairApplication_Type(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        repairApplication.setId(1);
        repairApplication.setProcessor_Id(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        repairApplication.setRepairApplication_Time(date);
         repairName=(EditText)findViewById(R.id.repairName);

         repairPhone=(EditText)findViewById(R.id.repairPhone);

       repairLName=(EditText)findViewById(R.id.repairLName);
        repairApplication.setLogname(repairLName.getText().toString());
        repairApplication.setRepairApplication_Name(repairName.getText().toString());
        repairApplication.setRepairApplication_Phone(repairPhone.getText().toString());
        repairPlace=(EditText)findViewById(R.id.repairPlace);
        repairApplication.setRepairApplication_Place(repairPlace.getText().toString());
        Button repairSubmit=(Button)findViewById(R.id.repairSubmit);
        repairSubmit.setOnClickListener(this);

    }

    public void addRepair(String LogName,String RepairApplication_Phone,String RepairApplication_Name,String RepairApplication_Place,String RepairApplication_Type,String id,String Processor_Id){
        client.addRepair(LogName,RepairApplication_Phone,RepairApplication_Name,RepairApplication_Place,RepairApplication_Type,id,Processor_Id)
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
        AlertDialog dialog=new AlertDialog.Builder(RepairActivity.this)
                .setTitle("提醒")
                .setMessage("提交成功")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(RepairActivity.this, MainActivity.class);
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
            case R.id.repairSubmit:
                showDialog();
                System.out.println(repairLName.getText().toString());

                String p_id=String.valueOf(repairApplication.getProcessor_Id());
                String sid=String.valueOf(repairApplication.getId());
                String stype=String.valueOf(repairApplication.getRepairApplication_Type());
                System.out.println(sid);
                System.out.println(repairApplication.getRepairApplication_Time());
                System.out.println(stype);
                addRepair(repairLName.getText().toString(),repairPhone.getText().toString(),repairName.getText().toString(),repairPlace.getText().toString(),stype,sid,p_id);
                showDialog();
                       }
    }
}
