package com.antonioleiva.mvpexample.app.payment.property;

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


public class PropertyActivity extends AppCompatActivity {
    private List<ListMenuItem> propertyMenu = new ArrayList<>();

    private ListView listView;

    private OnMenuListItemClick onMenuListItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (listView == null) {
            initMenu();
            listView = (ListView)findViewById(R.id.property_menu);
            ListViewAdapter adapter = new ListViewAdapter(propertyMenu, this);
            listView.setAdapter(adapter);
            // Inflate the layout for this fragment
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int row,
                                        long arg3) {
                    Intent intent;
                    switch(row){
                        case 0:
                            intent = new Intent(PropertyActivity.this,PropertyCheckActivity.class);
                            intent.putExtra("id",1);
                            startActivityForResult(intent,1);
                            break;
                        case 1:
                            intent = new Intent(PropertyActivity.this, PropertyRuleActivity.class);
                            intent.putExtra("id",1);
                            startActivityForResult(intent,1);
                            break;
                    }
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
        propertyMenu.add(new ListMenuItem("缴费","在线完成物业费用的缴纳"));
        propertyMenu.add(new ListMenuItem("物业费细则","物业费相关明细、查询、缴纳"));

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
