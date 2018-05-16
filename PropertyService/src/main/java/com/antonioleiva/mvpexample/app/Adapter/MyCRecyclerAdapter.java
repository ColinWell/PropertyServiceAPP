package com.antonioleiva.mvpexample.app.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.bean.Complaint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85732 on 2018/5/6.
 */

public class MyCRecyclerAdapter extends RecyclerView.Adapter<MyCRecyclerAdapter.ViewHolder>{
    List<Complaint> mCList;
    public MyCRecyclerAdapter(List<Complaint> mCList){
        this.mCList=mCList;
    }
    public MyCRecyclerAdapter(){
        this.mCList=new ArrayList<>();
    }
    public void  add(List<Complaint> list){
        mCList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_c, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Complaint complaint=mCList.get(position);
        holder.c_itemContent.setText(complaint.getComplaints_Content());
        switch (complaint.getComplaints_Status()){
            case 0:
                holder.c_itemStatus.setText("未审核");
                holder.c_itemReply.setText(" ");
                break;
            case 1:
                holder.c_itemStatus.setText("已审核");
                holder.c_itemReply.setText(complaint.getComplaints_Reply());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mCList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView c_itemContent;
        TextView c_itemStatus;
        TextView c_itemReply;
        public ViewHolder(View itemView) {
            super(itemView);
            c_itemContent=(TextView)itemView.findViewById(R.id.c_itemContent);
            c_itemReply=(TextView)itemView.findViewById(R.id.c_itemReply);
            c_itemStatus=(TextView)itemView.findViewById(R.id.c_itemStatus);

        }
    }
}
