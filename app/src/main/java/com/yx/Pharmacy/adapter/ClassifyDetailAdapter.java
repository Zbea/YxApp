package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.RightBean;
import com.yx.Pharmacy.util.GlideUtil;

import java.util.List;

/**
 * Created by KID on 2018/7/16.
 */

public class ClassifyDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<RightBean> list;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public ClassifyDetailAdapter(Context context, List<RightBean> list, OnItemClickListener onItemClickListener) {
        this.context=context;
        this.list=list;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            return new MyViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_title, parent, false));
        }else {
            return new MyViewHolder2(LayoutInflater.from(context).inflate(R.layout.item_classify_detail, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RightBean model=list.get(position);
        switch (getItemViewType(position)){
            case 0:
                MyViewHolder1 holder1 = (MyViewHolder1) holder;
                holder1.tvTitle.setText(model.getCatname());
                holder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null&&!model.isTitle()){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                });
                break;
            case 1:
                MyViewHolder2 holder2 = (MyViewHolder2) holder;
                holder2.tvCity.setText(model.getCatname());
                holder2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null&&!model.isTitle()){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                });
                GlideUtil.loadImg(context, model.getThumb(), holder2.avatar, R.drawable.fhd);
                break;
        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public MyViewHolder1(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;

        public MyViewHolder2(View view) {
            super(view);
            tvCity = (TextView) view.findViewById(R.id.tvCity);
            avatar = (ImageView) view.findViewById(R.id.ivAvatar);
        }
    }

}
