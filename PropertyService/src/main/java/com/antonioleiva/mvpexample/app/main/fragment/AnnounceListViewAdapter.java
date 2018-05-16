package com.antonioleiva.mvpexample.app.main.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.AnnouncementItem;

import java.util.List;

/**
 * Created by Colin on 2018/4/10.
 */

public class AnnounceListViewAdapter extends BaseAdapter {
    public List<AnnouncementItem> list;
    private Context context = null;
    private LayoutInflater inflater = null;

    public AnnounceListViewAdapter(List<AnnouncementItem> list, Context context){
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
            convertView = inflater.inflate(R.layout.annoucement_list_item, null);//实例化一个对象
            viewHolder = new ViewHolder();
            viewHolder.im_type = (ImageView) convertView.findViewById(R.id.menu_img);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.menu_title);//获取该布局内的文本视图
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.menu_date);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag(); // 获取，通过ViewHolder找到相应的控件
        }
        AnnouncementItem menu = (AnnouncementItem) getItem(position); // 获取当前项的实例
        if(menu.getType() == 1){
            viewHolder.im_type.setImageResource(R.drawable.ic_error_black_24dp);
        }
        else{
            viewHolder.im_type.setImageResource(R.drawable.ic_mail_black_24dp);
        }
        viewHolder.tv_title.setText(menu.getTitle());//为文本视图设置文本内容
        viewHolder.tv_date.setText(menu.getPubDate());
        return convertView;
    }
    class ViewHolder {
        ImageView im_type;
        TextView tv_title;
        TextView tv_date;
    }
}
