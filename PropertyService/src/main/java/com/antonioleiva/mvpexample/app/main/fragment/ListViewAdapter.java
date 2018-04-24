package com.antonioleiva.mvpexample.app.Main.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.Bean.ListMenuItem;
import com.antonioleiva.mvpexample.app.R;

import java.util.List;

/**
 * Created by Colin on 2018/3/13.
 */

public class ListViewAdapter extends BaseAdapter{

    private List<ListMenuItem> list = null;
    private Context context = null;
    private LayoutInflater inflater = null;

    public ListViewAdapter(List<ListMenuItem> list, Context context) {
        this.list = list;
        this.context = context;
        // 布局装载器对象
        inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.payment_menu, null);//实例化一个对象
            viewHolder = new ViewHolder();
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.id_tvTitle);//获取该布局内的文本视图
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.id_tvContent);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag(); // 获取，通过ViewHolder找到相应的控件
        }
        ListMenuItem menu = (ListMenuItem) getItem(position); // 获取当前项的Fruit实例
        viewHolder.tv_title.setText(menu.getTitle());//为文本视图设置文本内容
        viewHolder.tv_content.setText(menu.getContent());
        return convertView;
    }
    class ViewHolder {
        TextView tv_title;
        TextView tv_content;
    }

}
