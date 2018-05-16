package com.antonioleiva.mvpexample.app.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.AnnouncementItem;
import com.antonioleiva.mvpexample.app.bean.OrderItem;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Colin on 2018/4/16.
 */

public class OrderAdapter extends BaseAdapter {
    private List<OrderItem> list;
    private Context context = null;
    private LayoutInflater inflater = null;

    public OrderAdapter(List<OrderItem> list,Context context){
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.order_item, null);//实例化一个对象
            viewHolder = new ViewHolder();
            viewHolder.tv_amount = (TextView) convertView.findViewById(R.id.menu_amount);
            viewHolder.tv_month = (TextView) convertView.findViewById(R.id.menu_month);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.menu_date);
            viewHolder.tv_type = (TextView) convertView.findViewById(R.id.menu_type);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag(); // 获取，通过ViewHolder找到相应的控件
        }
        OrderItem order = (OrderItem) getItem(position); // 获取当前项的实例
        viewHolder.tv_amount.setText(order.getAmount());
        viewHolder.tv_type.setText(order.getType());
        viewHolder.tv_month.setText(order.getMonths());//为文本视图设置文本内容
        viewHolder.tv_date.setText(order.getDate());
        return convertView;
    }
    class ViewHolder {
        TextView tv_month;
        TextView tv_amount;
        TextView tv_type;
        TextView tv_date;
    }
}
