package com.antonioleiva.mvpexample.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85732 on 2018/4/18.
 */

public class MyAdapter extends BaseAdapter {
    List<String> datas=new ArrayList<>();
    Context mContext;
    public MyAdapter(Context context){
        this.mContext=context;
    }
    public void setDatas(List<String> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas==null?null:datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler hodler=null;
        if(view==null){
            hodler=new ViewHodler();
            view= LayoutInflater.from(mContext).inflate(R.layout.item1,null);
            hodler.mTextView=(TextView)view;
            view.setTag(hodler);
        }else {
            hodler=(ViewHodler)view.getTag();
        }
        hodler.mTextView.setText(datas.get(i));
        return view;
    }
    private static class ViewHodler{
        TextView mTextView;
    }
}
