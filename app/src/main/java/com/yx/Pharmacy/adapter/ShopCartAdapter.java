package com.yx.Pharmacy.adapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.WrapContentLinearLayoutManager;

import java.util.List;

/**
 * Created time  2018/8/2 0002
 * @author : mcx
 * 类描述 :
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartModel.ShopCartListBean,BaseViewHolder> {


    private OnClickShopCartListener mOnShopCartListener;
    private ListDeviderDecoration mDecoration;

    public ShopCartAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartModel.ShopCartListBean item) {
        RecyclerView product = helper.getView(R.id.rc_product);
        ImageView    ivType  = helper.getView(R.id.iv_type);
        LinearLayout ll_type = helper.getView(R.id.ll_type);
        ll_type.setVisibility(View.VISIBLE);
        String type = item.type;
        if(TextUtils.equals(type, "1")){
            // 秒杀
            ivType.setImageResource(R.drawable.icon_shopcar_flash_buy);
        }else if(TextUtils.equals(type,"2")){
            // 特价
            ivType.setImageResource(R.drawable.icon_shopcar_discount);
        }else if(TextUtils.equals(type,"3")){
            // 满减
            ivType.setImageResource(R.drawable.icon_shopcar_full_give);
        }else if(TextUtils.equals(type,"9")){
            // 控销
            ivType.setImageResource(R.drawable.icon_shopcar_full_accuse);
        }else {
            ll_type.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_type,item.activityname);
        product.setNestedScrollingEnabled(false);

        LogUtils.e("start = "+item.type+"  ----  "+System.currentTimeMillis()+"     -----------**");
        if (product.getAdapter()!=null) {
            for (int i = 0; i < item.goods.size(); i++) {
                ((ShopCartProductAdapter)product.getAdapter()).setNewData(item.goods);
                ((ShopCartProductAdapter)product.getAdapter()).notifyItemChanged(i);
            }
        }else {
            ShopCartProductAdapter adapter = new ShopCartProductAdapter(R.layout.item_shop_cart_product,type);
            product.setLayoutManager(new WrapContentLinearLayoutManager(UiUtil.getContext()));
            ((DefaultItemAnimator) product.getItemAnimator()).setSupportsChangeAnimations(false);
            product.setAdapter(adapter);
            adapter.setNewData(item.goods);

            if (mDecoration==null) {
                mDecoration = new ListDeviderDecoration(UiUtil.getContext());
                product.addItemDecoration(mDecoration);
            }else {
                product.removeItemDecoration(mDecoration);
                product.addItemDecoration(mDecoration);
            }


            adapter.setOnShopGoodListener(new ShopCartProductAdapter.OnShopGoodListener() {
                @Override
                public void onAumountChangeListener(View view, int amount, String goodsid,boolean isEdit,int addnum) {
                    if (mOnShopCartListener!=null) {
                        mOnShopCartListener.aumontChange(view,amount,goodsid,item.activityid,isEdit,helper.getAdapterPosition(),addnum);
                    }
                }
            });

            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.ll_checkall:
                            ShopCartModel.GoodsBean bean = item.goods.get(position);
                            if (!TextUtils.equals(bean.quehuo, "true")) {
                                bean.isSelect = !bean.isSelect;
                                if (mOnShopCartListener!=null) {
                                    mOnShopCartListener.modifySelect(helper.getAdapterPosition());
                                }
                            }
                            break;
                        case R.id.content:
                            ShopCartModel.GoodsBean bean1 = item.goods.get(position);
                            if (mOnShopCartListener!=null) {
                                mOnShopCartListener.toDetail(adapter,bean1.goodsid);
                            }
                            break;
                        case R.id.right:
                            if (mOnShopCartListener!=null) {
                                mOnShopCartListener.removeShop(adapter,helper.getAdapterPosition(),position,item.activityid);
                            }
                            break;
                        case R.id.ll_single_coupon:
                            if (mOnShopCartListener!=null) {
                                mOnShopCartListener.singleCoupon(adapter,helper.getAdapterPosition(),position,item.activityid);
                            }
                            break;
                    }
                }
            });
        }

        helper.setGone(R.id.tv_coupon,item.couponList!=null&&item.couponList.size()>0)
              .addOnClickListener(R.id.tv_coupon);

        boolean isHaveCoupon = false;
        if (item.couponList!=null&&item.couponList.size()>0) {
            for (ShopCartModel.CouponListBean couponListBean : item.couponList) {
                if (couponListBean.isSelectCoupon) {
                    isHaveCoupon = true;
                    helper.setText(R.id.tv_coupon,couponListBean.couponcontent);
                }
            }
        }
        if (!isHaveCoupon) {
            helper.setText(R.id.tv_coupon,"去领券");
        }
        LogUtils.e("end = "+item.type+"  ----  "+System.currentTimeMillis()+"     -----------**");
    }

    public void setCheckAll(boolean checkAll) {
        List<ShopCartModel.ShopCartListBean> data = getData();
        for (int i = 0; i < data.size(); i++) {
            ShopCartModel.ShopCartListBean shopCartListBean = data.get(i);
            for (ShopCartModel.GoodsBean good : shopCartListBean.goods) {
                if (!TextUtils.equals(good.quehuo, "true")&&!TextUtils.equals(good.isvalid, "true")) {
                    good.isSelect = checkAll;
                }
            }
            notifyItemChanged(i);
        }
    }

    public  interface OnClickShopCartListener{

        void modifySelect(int position);// 修改了选项

        void removeShop(BaseQuickAdapter adapter, int cartposition, int position, String activityid);

        void aumontChange(View view,
                          int amount,
                          String goodsid,
                          String activityid,
                          boolean isEdit,
                          int adapterPosition, int addnum);

        void toDetail(BaseQuickAdapter adapter, String goodsid);

        void singleCoupon(BaseQuickAdapter adapter, int cartposition, int position, String activityid);
    }

    /**
     * 点击购物车的事件
    */
    public void setOnClickShopCartListener(OnClickShopCartListener listener){
        this.mOnShopCartListener = listener;
    }
}
