package com.antonioleiva.mvpexample.app.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.ListMenuItem;
import com.antonioleiva.mvpexample.app.main.MainActivity;
import com.antonioleiva.mvpexample.app.payment.PaymentHistoryActivity;
import com.antonioleiva.mvpexample.app.payment.PaymentStateActivity;
import com.antonioleiva.mvpexample.app.payment.parking.ParkingActivity;
import com.antonioleiva.mvpexample.app.payment.property.PropertyActivity;
import com.antonioleiva.mvpexample.app.payment.utilities.UtilitiesActivity;
import com.antonioleiva.mvpexample.app.util.OnMenuListItemClick;

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
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
                    Intent intent = null;
                    switch (row) {
                        case 0:
                            intent = new Intent(getActivity(), PropertyActivity.class);
                            intent.putExtra("id", 1);
                            startActivityForResult(intent, 1);
                            //getActivity().finish();
                            break;
                        case 1:
                            intent = new Intent(getActivity(), ParkingActivity.class);
                            intent.putExtra("id", 1);
                            startActivityForResult(intent, 1);
                            break;
                        case 2:
                            intent = new Intent(getActivity(), UtilitiesActivity.class);
                            intent.putExtra("id", 1);
                            startActivityForResult(intent, 1);
                            break;
                        case 3:
                            intent = new Intent(getActivity(), PaymentHistoryActivity.class);
                            intent.putExtra("id", 1);
                            startActivityForResult(intent, 1);
                            break;
                        case 4:
                            intent = new Intent(getActivity(), PaymentStateActivity.class);
                            intent.putExtra("id",1);
                            startActivityForResult(intent,1);
                            break;
                    }
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
        paymentMenu.add(new ListMenuItem("历史缴费查询","直观展示历史缴费情况"));
        paymentMenu.add(new ListMenuItem("当月缴费情况","直观展示当月缴费情况"));
    }

    public OnMenuListItemClick getOnMenuListItemClick() {
        return onMenuListItemClick;
    }

    public void setOnMenuListItemClick(OnMenuListItemClick onMenuListItemClick) {
        this.onMenuListItemClick = onMenuListItemClick;
    }

}
