package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.TagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class DrugTagAdapter<T>  extends BaseAdapter {

    private final Context mContext;
    private final List<TagModel>models;

    public DrugTagAdapter(Context context) {
        this.mContext = context;
        models=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public TagModel getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_drug_tag, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        textView.setBackgroundResource(R.drawable.selector_round_rec_gray_stroke_bg);

        if(!TextUtils.isEmpty(models.get(position).getName())){
            textView.setText(models.get(position).getName());
        }else {
            textView.setText("无数据");
        }


        return view;
    }

    public void onlyAddAll(List<TagModel> datas) {
        models.addAll(datas);
        notifyDataSetChanged();
    }


}
