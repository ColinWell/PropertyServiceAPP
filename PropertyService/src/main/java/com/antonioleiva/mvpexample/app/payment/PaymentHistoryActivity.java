package com.antonioleiva.mvpexample.app.payment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.AnnounceService;
import com.antonioleiva.mvpexample.app.Service.PaymentService;
import com.antonioleiva.mvpexample.app.bean.AnnouncementItem;
import com.antonioleiva.mvpexample.app.bean.OrderItem;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.main.fragment.AnnounceListViewAdapter;
import com.antonioleiva.mvpexample.app.util.MyDateUtil;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentHistoryActivity extends AppCompatActivity {

    private List<OrderItem> orderList;
    private ListView historyLv;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        historyLv = (ListView) findViewById(R.id.payment_history);
        orderList = new ArrayList<OrderItem>();

        runChildThread();

        adapter = new OrderAdapter(orderList,this);
        historyLv.setAdapter(adapter);
    }

    private void setOrderList(String result) {
            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = (JSONArray)object.get("data");
                orderList.clear();
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    OrderItem item = new OrderItem();
                    item.setDate(MyDateUtil.longToString(jsonObject.getLong("date"),"yyyy-MM-dd hh:mm:ss"));
                    int months = jsonObject.getInt("months");
                    switch (months){
                        case 1:
                        case 2:
                        case 3:
                            item.setMonths(months+"个月");
                            break;
                        case 6:
                            item.setMonths("半年");
                            break;
                        case 12:
                            item.setMonths("一年");
                            break;
                        default:
                            System.out.println("error:"+months);
                            break;
                    }
                    int type = jsonObject.getInt("pay_type");
                    switch (type){
                        case 1:
                            item.setType("物业费");
                            break;
                        case 2:
                            item.setType("公共水电费");
                            break;
                        case 3:
                            item.setType("停车费");
                            break;
                    }
                    item.setAmount("￥"+jsonObject.getDouble("amount"));
                    orderList.add(item);
                }
                if(adapter != null){
                    adapter.notifyDataSetChanged();
                }
            }catch (JSONException e){
                System.out.println(e.getMessage());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }

    public void runChildThread(){
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String result = data.getString("result");
                setOrderList(result);
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
