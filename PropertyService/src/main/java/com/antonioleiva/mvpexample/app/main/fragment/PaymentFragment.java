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


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    private List<ListMenuItem> paymentMenu = new ArrayList<>();
    private OnMenuListItemClick onMenuListItemClick;
    private View contentView; // 缓存view，避免因切换重复加载

    public PaymentFragment() {
        // Required empty public constructor
    }

    // 使用静态工厂创建实例
    public static PaymentFragment newInstance(){
        PaymentFragment  paymentFragment = new PaymentFragment();
        return paymentFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(contentView == null) {
            initMenu();
            contentView = inflater.inflate(R.layout.fragment_payment, null);
            final ListView listView = (ListView) contentView.findViewById(R.id.payment_menu);
            ListViewAdapter adapter = new ListViewAdapter(paymentMenu,getContext());
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
        paymentMenu.add(new ListMenuItem("物业费","物业费相关明细、查询、缴纳"));
        paymentMenu.add(new ListMenuItem("停车费","停车费相关明细、查询、缴纳"));
        paymentMenu.add(new ListMenuItem("物业公共水电费","物业公共水电费相关明细、查询、缴纳"));
    }

    public OnMenuListItemClick getOnMenuListItemClick() {
        return onMenuListItemClick;
    }

    public void setOnMenuListItemClick(OnMenuListItemClick onMenuListItemClick) {
        this.onMenuListItemClick = onMenuListItemClick;
    }

}
