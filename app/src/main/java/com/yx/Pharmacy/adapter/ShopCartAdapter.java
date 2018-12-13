package com.yx.Pharmacy.adapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created time  2018/8/2 0002
 * @author : mcx
 * 类描述 :
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartModel.ShopCartListBean,BaseViewHolder> {


    private OnClickShopCartListener mOnShopCartListener;
    private ListDeviderDecoration mDecoration;
    private List<ShopCartProductAdapter> adapters=new ArrayList<>();
    private  int pos;
    private boolean isBoolean;
    private boolean isDelete;
    public ShopCartAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartModel.ShopCartListBean item) {
        RecyclerView product = helper.getView(R.id.rc_product);
        ImageView    ivType  = helper.getView(R.id.iv_type);
        LinearLayout ll_type = helper.getView(R.id.ll_type);
        LinearLayout ll_open = helper.getView(R.id.ll_open);
        LinearLayout ll_close = helper.getView(R.id.ll_close);
        TextView tv_clear = helper.getView(R.id.tv_clear);
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
        } else {
            ll_type.setVisibility(View.GONE);
        }

        if (TextUtils.equals(item.activityname, "失效商品")&&TextUtils.equals(type, "0"))
        {
            ll_open.setVisibility(View.VISIBLE);
            product.setVisibility(View.GONE);
            ll_close.setVisibility(View.GONE);
            tv_clear.setVisibility(View.VISIBLE);
            ll_type.setVisibility(View.VISIBLE);
            ivType.setImageResource(R.drawable.icon_cart_disable_product);
            type="11111";
        }
        else
        {
            ll_open.setVisibility(View.GONE);
            product.setVisibility(View.VISIBLE);
            ll_close.setVisibility(View.GONE);
            tv_clear.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_type,item.activityname);
        product.setNestedScrollingEnabled(false);

        if (item.goods.size()==0)
        {
            ll_type.setVisibility(View.GONE);
        }

        if (adapters.size()>helper.getAdapterPosition()&&adapters.get(helper.getAdapterPosition())!=null) {
            L.i("LLLLLLLLLLLLLLLLLLLL");
            if (isBoolean)
            {
                for (int i = 0; i < item.goods.size(); i++) {
                    adapters.get(helper.getAdapterPosition()).notifyItemChanged(i,0);
                }
            }
            else
            {
                if (!isDelete)
                    adapters.get(helper.getAdapterPosition()).notifyItemChanged(pos,0);
                isDelete=false;
            }
//            adapters.get(helper.getAdapterPosition()).setNewData(item.goods);
        }else
          {
            ShopCartProductAdapter adapter = new ShopCartProductAdapter(R.layout.item_shop_cart_product,type);
              product.setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//            product.setLayoutManager(new WrapContentLinearLayoutManager(UiUtil.getContext()));
//            ((DefaultItemAnimator) product.getItemAnimator()).setSupportsChangeAnimations(false);
            product.setAdapter(adapter);
//              product.setHasFixedSize(false);
            adapter.setNewData(item.goods);
            adapters.add(adapter);
            L.i("dddddddddddddddddddddddd");
            if (mDecoration==null) {
                mDecoration = new ListDeviderDecoration(UiUtil.getContext());
                product.addItemDecoration(mDecoration);
            }else {
                product.removeItemDecoration(mDecoration);
                product.addItemDecoration(mDecoration);
            }

            adapter.setOnShopGoodListener(new ShopCartProductAdapter.OnShopGoodListener() {
                @Override
                public void onAumountChangeListener(View view, int amount, String goodsid, int position, boolean isEdit, int addnum) {
                    if (mOnShopCartListener!=null) {
                        isBoolean=false;
                        pos=position;
//                        adapters.get(helper.getAdapterPosition()).notifyItemChanged(position);
                        mOnShopCartListener.aumontChange(view,amount,goodsid,item.activityid,isEdit,helper.getAdapterPosition(),addnum);
                    }
                }
            });

            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.ll_checkall:
                            ShopCartModel.GoodsBean bean =item.goods.get(position);
                            if (TextUtils.equals(bean.quehuo, "false")&&TextUtils.equals(bean.isvalid, "false")) {
                                bean.isSelect = !bean.isSelect;
                                isBoolean=false;
                                pos=position;
//                                adapters.get(helper.getAdapterPosition()).notifyItemChanged(position);
                                if (mOnShopCartListener!=null) {
                                    mOnShopCartListener.modifySelect(helper.getAdapterPosition());
                                }
                            }
                            break;
                        case R.id.iv_product:
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

        ll_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setVisibility(View.VISIBLE);
                ll_close.setVisibility(View.VISIBLE);
                ll_open.setVisibility(View.GONE);
            }
        });

        ll_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setVisibility(View.GONE);
                ll_close.setVisibility(View.GONE);
                ll_open.setVisibility(View.VISIBLE);
            }
        });

        helper.setGone(R.id.tv_coupon,item.couponList!=null&&item.couponList.size()>0)
              .addOnClickListener(R.id.tv_coupon);

        helper.addOnClickListener(R.id.tv_clear);

        boolean isHaveCoupon = false;
        boolean isOkCoupon=false;
        if (item.couponList!=null&&item.couponList.size()>0) {
            for (ShopCartModel.CouponListBean couponListBean : item.couponList) {
                if (couponListBean.isSelectCoupon) {
                    isOkCoupon = true;
                    isHaveCoupon=true;
                    helper.setText(R.id.tv_coupon,couponListBean.couponcontent);
                    break;
                }
                else
                {
                    isOkCoupon = false;
                    isHaveCoupon=true;
                }
            }
        }
        else
        {
            isHaveCoupon = false;
        }

        if (!isHaveCoupon) {
            helper.setText(R.id.tv_coupon,"去领券");
        }
        else
        {
            if (!isOkCoupon)
            {
                helper.setText(R.id.tv_coupon,item.couponList.get(0).couponcontent);
            }
        }
    }

    public void setCheckAll(boolean checkAll) {
        isBoolean=true;
        List<ShopCartModel.ShopCartListBean> data = getData();
        int count=getData().size();
        if (count>0)
        {
            for (int i = 0; i < count; i++) {
                ShopCartModel.ShopCartListBean shopCartListBean = data.get(i);
                for (int j = 0; j <shopCartListBean.goods.size() ; j++) {
                    ShopCartModel.GoodsBean good=shopCartListBean.goods.get(j);
                    if (!TextUtils.equals(good.quehuo, "true")&&!TextUtils.equals(good.isvalid, "true")) {
                        good.isSelect = checkAll;
                    }
                }
                notifyItemChanged(i,0);
            }
        }
    }

    public void setRefresh()
    {
        adapters.clear();
    }

    public void setDelete(int cartPosition,int position)
    {
        pos=position;
        adapters.get(cartPosition).notifyItemRemoved(position);
        if (getData().get(cartPosition).goods.size()==0)
        {
            isDelete=true;
            adapters.remove(cartPosition);
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
