package com.antonioleiva.mvpexample.app.Main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.antonioleiva.mvpexample.app.Bean.ListMenuItem;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Utils.OnMenuListItemClick;

import java.util.ArrayList;
import java.util.List;


public class ExtendFragment extends Fragment {
    private List<ListMenuItem> extendMenu = new ArrayList<>();
    private OnMenuListItemClick onMenuListItemClick;
    private View contentView; // 缓存view，避免因切换重复加载

    public ExtendFragment() {
        super();
    }

    // 使用静态工厂创建实例
    public static ExtendFragment newInstance(){
        ExtendFragment extendFragment = new ExtendFragment();
        return extendFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(contentView == null) {
            initMenu();
            contentView = inflater.inflate(R.layout.fragment_extend, null);
            final ListView listView = (ListView) contentView.findViewById(R.id.payment_menu);
            ListViewAdapter adapter = new ListViewAdapter(extendMenu,getContext());
            listView.setAdapter(adapter);
            // Inflate the layout for this fragment
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int row,
                                        long arg3) {
                    //4、如果接口成员变量不为空null，则调用接口变量的方法。
                    if(onMenuListItemClick!=null){
                        onMenuListItemClick.onClick(listView,row);
                    }
                    System.out.println("点击了第"+row+"行");
                }
            });
        }else{
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if(parent != null){
                parent.removeView(contentView);
            }
        }

        return contentView;
    }


    public void initMenu(){
        extendMenu.add(new ListMenuItem("在线报修","发现故障，在线及时完成报修"));
        extendMenu.add(new ListMenuItem("装修申请","提交装修申请，无纸申请更轻松"));
        extendMenu.add(new ListMenuItem("投诉建议","对小区建设和服务有什么建议，欢迎提出"));
        extendMenu.add(new ListMenuItem("个人资料修改","完善个人信息，以便提供更好的服务"));
    }

    public OnMenuListItemClick getOnMenuListItemClick() {
        return onMenuListItemClick;
    }

    public void setOnMenuListItemClick(OnMenuListItemClick onMenuListItemClick) {
        this.onMenuListItemClick = onMenuListItemClick;
    }
}
