package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yx.Pharmacy.R;

import java.util.List;

/**
 * Created by KID on 2018/7/16.
 */

public class SortAdapter  extends RecyclerView.Adapter<SortAdapter.MyViewHolder>{

    private Context context;
    private List<String> list;
    private int checkedPosition=0;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    public SortAdapter(Context context, List<String> list, OnItemClickListener onItemClickListener) {
        this.context=context;
        this.list=list;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sort_list, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String model=list.get(position);

        holder.tvName.setText(model);
        if (position == checkedPosition) {
            holder.mView.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tvName.setTextColor(Color.parseColor("#e72e55"));
        } else {
            holder.mView.setBackgroundColor(Color.parseColor("#f6f6f6"));
            holder.tvName.setTextColor(Color.parseColor("#333333"));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null)onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        View mView;

        public MyViewHolder(View view) {
            super(view);
            this.mView = itemView;
            tvName = (TextView) view.findViewById(R.id.tv_sort);
        }
    }


}
