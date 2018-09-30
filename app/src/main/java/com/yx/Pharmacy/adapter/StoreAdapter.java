package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.MyShopModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KID on 2018/8/27.
 */
public class StoreAdapter extends BaseAdapter {

    private List<MyShopModel> models=new ArrayList<>();
    private Context context;
    public StoreAdapter(Context context, List<MyShopModel> list){
        this.context=context;
        models=list;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public MyShopModel getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            //这里面的item是一个自定义的View继承线性布局，继承什么布局不重要，
            // 重要的是将item的宽高设置成一样；感觉这个效果项目中很多地方都能用到
            convertView=View.inflate(context, R.layout.item_store, null);
            vh=new ViewHolder();
            vh.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            vh.tv_address=(TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();

        }
        //当前item要加载的图片路径
        final MyShopModel model =models.get(position);
        if(model!=null){
            vh.tv_name.setText(TextUtils.isEmpty(model.storename)?"":model.storename);
            vh.tv_address.setText(TextUtils.isEmpty(model.storeaddress)?"":model.storeaddress);
        }
        return convertView;
    }

    private class ViewHolder{
        TextView tv_name;
        TextView tv_address;
    }
}
