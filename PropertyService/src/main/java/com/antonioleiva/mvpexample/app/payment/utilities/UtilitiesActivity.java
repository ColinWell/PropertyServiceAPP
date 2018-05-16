package com.antonioleiva.mvpexample.app.payment.utilities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.ListMenuItem;
import com.antonioleiva.mvpexample.app.main.fragment.ListViewAdapter;
import com.antonioleiva.mvpexample.app.util.OnMenuListItemClick;

import java.util.ArrayList;
import java.util.List;


public class UtilitiesActivity extends AppCompatActivity {

    private List<ListMenuItem> utilitiesMenu = new ArrayList<>();

    private ListView listView;

    private OnMenuListItemClick onMenuListItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (listView == null) {
            initMenu();
            listView = (ListView)findViewById(R.id.utilities_menu);
            ListViewAdapter adapter = new ListViewAdapter(utilitiesMenu, this);
            listView.setAdapter(adapter);
            // Inflate the layout for this fragment
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int row,
                                        long arg3) {
                    //4、如果接口成员变量不为空null，则调用接口变量的方法。
                    if (onMenuListItemClick != null) {
                        onMenuListItemClick.onClick(listView, row);
                    }
                }
            });
        } else {

        }
    }

    public void initMenu(){
        utilitiesMenu.add(new ListMenuItem("缴费","在线完成公共水电费用的缴纳"));
        utilitiesMenu.add(new ListMenuItem("公共水电费细则","公共水电费相关明细、查询、缴纳"));
    }

    public OnMenuListItemClick getOnMenuListItemClick() {
        return onMenuListItemClick;
    }

    public void setOnMenuListItemClick(OnMenuListItemClick onMenuListItemClick) {
        this.onMenuListItemClick = onMenuListItemClick;
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        setResult(id);
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        //switch (item.getItemId()) {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        setResult(id);
        return super.onOptionsItemSelected(item);

    }

}
