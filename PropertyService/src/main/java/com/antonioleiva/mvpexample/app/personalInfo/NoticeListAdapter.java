package com.antonioleiva.mvpexample.app.personalInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.NoticeItem;

import java.util.List;

/**
 * Created by Colin on 2018/4/18.
 */

public class NoticeListAdapter extends BaseAdapter {
    private List<NoticeItem> list;
    private Context context = null;
    private LayoutInflater inflater = null;

    public NoticeListAdapter(List<NoticeItem> list, Context context) {
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
            convertView = inflater.inflate(R.layout.notice_item, null);//实例化一个对象
            viewHolder = new ViewHolder();
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.menu_date);//获取该布局内的文本视图
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.menu_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag(); // 获取，通过ViewHolder找到相应的控件
        }
        NoticeItem menu = (NoticeItem) getItem(position); // 获取当前项的实例

        viewHolder.tv_date.setText(menu.getDate());//为文本视图设置文本内容
        viewHolder.tv_content.setText(menu.getContent());
        return convertView;
    }
    class ViewHolder {
        TextView tv_date;
        TextView tv_content;
    }
}
