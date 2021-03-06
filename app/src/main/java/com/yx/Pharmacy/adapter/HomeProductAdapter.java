package com.yx.Pharmacy.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;

/**
 * Created time  2018/7/16 0016
 * @author : mcx
 * 类描述 :
 */

public class HomeProductAdapter
        extends BaseQuickAdapter<HomeDataModel,BaseViewHolder>
{
    private onProdutcClick mOnProdutcClick;
    private GridDividerItemDecoration mTjDecoration;
    private ListDeviderDecoration mMzDecoration;
    private ListDeviderDecoration mKxDecoration;

    public HomeProductAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataModel item) {
        ImageView    title   = helper.getView(R.id.iv_title);
        RecyclerView product = helper.getView(R.id.rv_product);

        helper.addOnClickListener(R.id.iv_title);

        GlideUtil.loadImg(UiUtil.getContext(),item.thumb,title);
        String type = item.type;

        HomeProductListAdapter listAdapter = null;
        // 商品列表 1：秒杀 2：特价 3：满减 9：控销
        if(TextUtils.equals(type,"1")){
            // 秒杀
//            LinearLayoutManager manager = new LinearLayoutManager(mContext);
//            product.setLayoutManager(manager);
//            listAdapter = new HomeProductListAdapter(R.layout.item_home_product_detail, item.goodlists, type);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            product.setLayoutManager(manager);
            listAdapter = new HomeProductListAdapter(R.layout.item_qianggou, item.goodlists, type);
        }else if(TextUtils.equals(type,"2")){
            // 特价
            GridLayoutManager manager = new GridLayoutManager(mContext,2);
            product.setLayoutManager(manager);
            listAdapter = new HomeProductListAdapter(R.layout.item_home_product_special, item.goodlists, type);

            if (mTjDecoration==null) {
                mTjDecoration = new GridDividerItemDecoration(UiUtil.getContext());
                product.addItemDecoration(mTjDecoration);
            }else {
                product.removeItemDecoration(mTjDecoration);
                product.addItemDecoration(mTjDecoration);
            }
        }else if(TextUtils.equals(type,"3")){
            // 满减
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            product.setLayoutManager(manager);
            listAdapter = new HomeProductListAdapter(R.layout.item_home_product_minus, item.goodlists, type);

            if (mMzDecoration==null) {
                mMzDecoration = new ListDeviderDecoration(UiUtil.getContext());
                product.addItemDecoration(mMzDecoration);
            }else {
                product.removeItemDecoration(mMzDecoration);
                product.addItemDecoration(mMzDecoration);
            }
        }else if(TextUtils.equals(type,"9")){
            // 控销
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            product.setLayoutManager(manager);
            listAdapter = new HomeProductListAdapter(R.layout.item_home_product_minus, item.goodlists, type);

            if (mKxDecoration==null) {
                mKxDecoration = new ListDeviderDecoration(UiUtil.getContext());
                product.addItemDecoration(mKxDecoration);
            }else {
                product.removeItemDecoration(mKxDecoration);
                product.addItemDecoration(mKxDecoration);
            }
        }else {
            GridLayoutManager manager = new GridLayoutManager(mContext,2);
            product.setLayoutManager(manager);
            listAdapter = new HomeProductListAdapter(R.layout.item_home_product_special, item.goodlists, type);

            if (mTjDecoration==null) {
                mTjDecoration = new GridDividerItemDecoration(UiUtil.getContext());
                product.addItemDecoration(mTjDecoration);
            }else {
                product.removeItemDecoration(mTjDecoration);
                product.addItemDecoration(mTjDecoration);
            }
        }
//        product.addItemDecoration(new SpacesItemDecoration(1, 1));
        product.setAdapter(listAdapter);
        product.setNestedScrollingEnabled(false);
        HomeProductListAdapter finalListAdapter = listAdapter;
        listAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mOnProdutcClick!=null&&finalListAdapter!=null) {
                    mOnProdutcClick.onClick(finalListAdapter.getData().get(position));
                }
            }
        });

        listAdapter.setOnCountEndListener(new HomeProductListAdapter.onCountEndClick() {
            @Override
            public void onEnd() {
                if (mOnProdutcClick!=null) {
                    mOnProdutcClick.onEnd();
                }
            }
        });
    }

    public  interface onProdutcClick{
        void onClick(HomeDataModel.GoodlistsBean o);

        void onEnd();
    }

    public void setOnProdutcClick(onProdutcClick click){
        this.mOnProdutcClick = click;
    }
}
