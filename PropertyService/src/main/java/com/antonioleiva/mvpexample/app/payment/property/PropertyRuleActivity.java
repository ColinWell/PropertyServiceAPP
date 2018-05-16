package com.antonioleiva.mvpexample.app.payment.property;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.Service.PaymentService;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class PropertyRuleActivity extends AppCompatActivity {

    private Double propertyFee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_rule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView ruleTv = (TextView)findViewById(R.id.ruleTv);
        if(propertyFee==null){
            getPropertyFee();
        }
        StringBuffer content = new StringBuffer("");
        content.append("  尊敬的业主:" +
                "您好!您所居住的单元每月物业费的收费标准为："+propertyFee+"元/平米/月" +
                "请悉知，如有疑问，请电联。");
        ruleTv.setText(content.toString());
    }

    public void getPropertyFee(){
        MyApplication application = MyApplication.getInstance();
        propertyFee = application.getPropertyFee();
    }
}
