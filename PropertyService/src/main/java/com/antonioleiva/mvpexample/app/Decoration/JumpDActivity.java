package com.antonioleiva.mvpexample.app.Decoration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.ListMenuItem;
import com.antonioleiva.mvpexample.app.main.fragment.ListViewAdapter;
import com.antonioleiva.mvpexample.app.util.OnMenuListItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85732 on 2018/4/28.
 */

public class JumpDActivity extends Activity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private List<ListMenuItem> mDatas;
    private Button db;
    private Button dhistoryB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmenu);
        db=(Button)findViewById(R.id.db);
        db.setOnClickListener(this);
        dhistoryB=(Button)findViewById(R.id.dhistoryB);
        dhistoryB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.db:
                Intent intent=new Intent(JumpDActivity.this,DecorationActivity.class);
                startActivity(intent);
                break;
            case R.id.dhistoryB:
                Intent intent1=new Intent(JumpDActivity.this,DecorationHistoryActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
//        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_jumpd);
//        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutmanager);
//        //ListViewAdapter adapter=new ListViewAdapter(mDatas);
//    }
//    private void inintMenu(){
//        mDatas.add(new ListMenuItem("装修记录","历史记录查看"));
//        mDatas.add(new ListMenuItem("装修申请","提交装修申请，无纸申请更轻松"));
//    }
}
