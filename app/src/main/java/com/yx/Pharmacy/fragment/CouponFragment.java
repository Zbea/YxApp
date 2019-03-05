package com.yx.Pharmacy.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.CommendMsActivity;
import com.yx.Pharmacy.activity.CommendProductActivity;
import com.yx.Pharmacy.activity.MyCouponActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.activity.ProductItemActivity;
import com.yx.Pharmacy.adapter.CouponListAdapter;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.model.CouponModel;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by KID on 2018/7/14.
 * 已经使用
 */

public class CouponFragment extends BaseFragment  {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String json;
    private List<CouponModel> models=new ArrayList<>();
    private CouponListAdapter mAdapter;
    private int type;

    public static CouponFragment newInstance(int type,List<CouponModel> models) {
        Bundle bundle = new Bundle();
        Gson gson=new Gson();
        String json=gson.toJson(models);
        bundle.putString("models", json);
        bundle.putInt("type", type);
        CouponFragment fragment = new CouponFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frag_coupon;
    }

    @Override
    protected void init() {
        json=getArguments().getString("models");
        type=getArguments().getInt("type");
        Gson gson=new Gson();
        models = gson.fromJson(json, new TypeToken<List<CouponModel>>(){}.getType());
        Log.e("kid","model.size==="+models.size());

        final LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new CouponListAdapter(mContext,type,models);
        recyclerview.setAdapter(mAdapter);
        int itemDecoration = DensityUtils.dp2px(mContext, 10);
        recyclerview.addItemDecoration(new SpacesItemDecoration(0, itemDecoration));

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CouponModel couponModel = mAdapter.getData().get(position);
                if(couponModel.getCoupontype()==1) {
                    ProductDetailActivity.startActivity(mContext, couponModel.getPid());
                }
                if(couponModel.getCoupontype()==2) {
                    String type = couponModel.leveltype+"";
                    if (TextUtils.equals(String.valueOf(type), "1")) {
                        // 秒杀
                        CommendMsActivity.startActivity(mContext,""+couponModel.levelid);
                    } else  {
                        ProductItemActivity.startActivity(mContext,2,""+couponModel.levelid,couponModel.activityname);
                    }
                }
                if(couponModel.getCoupontype()==3)
                {
                    EventBus.getDefault().post("gotohome");
                    ((MyCouponActivity) mContext).finish();
                }
            }
        });
    }


}
