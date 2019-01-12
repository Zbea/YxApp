package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.SupplierListModel;
import com.yx.Pharmacy.util.SPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KID on 2018/8/27.
 */
public class SupplierAdapter extends BaseAdapter {

    private List<SupplierListModel> models=new ArrayList<>();
    private Context mContext;
    public SupplierAdapter(Context context, List<SupplierListModel> list){
        mContext=context;
        models=list;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public SupplierListModel getItem(int position) {
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
            convertView=View.inflate(mContext, R.layout.item_supplier_list, null);
            vh=new ViewHolder();
            vh.tvSupplier=(TextView) convertView.findViewById(R.id.tv_supplier);
            vh.ivSelect=(ImageView) convertView.findViewById(R.id.iv_select);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        //当前item要加载的图片路径
        final SupplierListModel item =models.get(position);

        vh.tvSupplier.setText(item.company);

        if (SPUtil.getString(mContext,Constants.KEY_STORE_ID).equals(item.storeid))
        {
            vh.ivSelect.setVisibility(View.VISIBLE);
        }
        else
        {
            vh.ivSelect.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewHolder{
        TextView tvSupplier;
        ImageView ivSelect;
    }
}
