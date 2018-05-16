package com.antonioleiva.mvpexample.app.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.DecorationApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85732 on 2018/5/5.
 */

public class MyDRecyclerAdapter extends RecyclerView.Adapter<MyDRecyclerAdapter.ViewHolder> {
    List<DecorationApplication> mDList;

    public MyDRecyclerAdapter() {
        this.mDList = new ArrayList<>();
    }

    public MyDRecyclerAdapter(List<DecorationApplication> mDList) {
        this.mDList = mDList;
    }

    public void add(List<DecorationApplication> list) {
        mDList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_d, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DecorationApplication decorationApplication = mDList.get(position);
        holder.d_itemPhone.setText(decorationApplication.getDecorationApplicationPhone());
        holder.d_itemPlace.setText(decorationApplication.getDecorationApplicationPlace());
        switch (decorationApplication.getDecorationApplicationStatus()) {
            case 1:
                holder.d_itemStatus.setText("未审核");
                break;
            case 2:
                holder.d_itemStatus.setText("已审核");
                break;
            case 3:
                holder.d_itemStatus.setText("已拒绝");
                break;
            default:
                holder.d_itemStatus.setText("未审核");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView d_itemPlace;
        TextView d_itemPhone;
        TextView d_itemStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            d_itemPlace = (TextView) itemView.findViewById(R.id.d_itemPlace);
            d_itemPhone = (TextView) itemView.findViewById(R.id.d_itemPhone);
            d_itemStatus = (TextView) itemView.findViewById(R.id.d_itemStatus);
        }
    }
}
