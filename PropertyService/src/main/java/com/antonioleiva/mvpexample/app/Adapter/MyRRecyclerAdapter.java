package com.antonioleiva.mvpexample.app.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.RepairApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85732 on 2018/4/28.
 */
public class MyRRecyclerAdapter extends RecyclerView.Adapter<MyRRecyclerAdapter.ViewHolder>{
    List<RepairApplication> mRepairList;
    public MyRRecyclerAdapter(List<RepairApplication> mRepairList){
        this.mRepairList=mRepairList;
    }
    public MyRRecyclerAdapter(){
        this.mRepairList=new ArrayList<>();
    }
    public void add(List<RepairApplication> list) {
        mRepairList.addAll(list);
        notifyDataSetChanged();
    }
    //加载item 的布局  创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_r,parent,false);
        return new ViewHolder(view);
    }
    //对RecyclerView子项数据进行赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RepairApplication repairApplication=mRepairList.get(position);
        holder.r_itemName.setText(repairApplication.getRepairApplication_Name());
        switch (repairApplication.getRepairApplication_Status()){
            case 0:
                holder.r_itemStatus.setText("未审核");
                break;
            case 1:
                holder.r_itemStatus.setText("已审核");
                break;
            case 2:
                holder.r_itemStatus.setText("暂缓");
                break;
            default:
                holder.r_itemStatus.setText("未审核");
                break;
        }
        switch (repairApplication.getRepairApplication_Type()){
            case 0:
                holder.r_itemType.setText("公用设备");
                break;
            case 1:
                holder.r_itemType.setText("家用设备");
                break;
            default:
                holder.r_itemType.setText("家用设备");
                break;
        }
        holder.r_itemPlace.setText(repairApplication.getRepairApplication_Place());
    }
    @Override
    public int getItemCount(){
        return mRepairList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView r_itemName;
        TextView r_itemPlace;
        TextView r_itemStatus;
        TextView r_itemType;
        public ViewHolder(View view) {
            super(view);
            r_itemName = (TextView) view.findViewById(R.id.r_itemName);
            r_itemPlace=(TextView) view.findViewById(R.id.r_itemPlace);
            r_itemStatus=(TextView) view.findViewById(R.id.r_itemStatus);
            r_itemType=(TextView)view.findViewById(R.id.r_itemType);
        }
    }



}
