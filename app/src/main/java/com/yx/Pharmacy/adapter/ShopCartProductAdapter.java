package com.yx.Pharmacy.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.AmountView;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;
import com.yx.Pharmacy.widget.MarqueeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created time  2018/8/2 0002
 *
 * @author : mcx
 * 类描述 :
 */

public class ShopCartProductAdapter
        extends BaseQuickAdapter<ShopCartModel.GoodsBean, BaseViewHolder> {

    private String mType;
    private boolean mCheckAll;
    private OnShopGoodListener mOnShopGoodListener;

    public ShopCartProductAdapter(int layoutResId, String type) {
        super(layoutResId);
        this.mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartModel.GoodsBean item) {
        ImageView product = helper.getView(R.id.iv_product);
        ImageView iv_state = helper.getView(R.id.iv_state);
        TextView title = helper.getView(R.id.tv_title);
        TextView time = helper.getView(R.id.tv_time);
        MarqueeView marqueeView = helper.getView(R.id.marqueeView);
        CheckBox cbSelect = helper.getView(R.id.cb_select);
        LinearLayout ll_item = helper.getView(R.id.ll_item);
        LinearLayout ll_gift = helper.getView(R.id.ll_gift);
        AmountView amountView = helper.getView(R.id.amount_view);
        GlideUtil.loadImg(UiUtil.getContext(), item.thumb, product);


//        L.i("刷新产品");
        if (TextUtils.isEmpty(item.validtime))
        {
            time.setVisibility(View.GONE);
        }
        else
        {
            time.setText("有效期：" +DateUtil.formatyyyyMMdd(DensityUtils.parseLong(item.validtime)*1000) );
        }
        List<String> info = item.info;
        if (info != null && info.size() > 0) {
            marqueeView.setVisibility(View.VISIBLE);
            List<CharSequence> list = new ArrayList<>();
            for (String s : info) {
                if (!TextUtils.isEmpty(s)) {
                    SpannableString ss = new SpannableString(s);
                    ss.setSpan(new ForegroundColorSpan(Color.parseColor("#ff8f00")), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    list.add(ss);
                }
            }
            marqueeView.startWithList(list);
        } else {
            marqueeView.setVisibility(View.GONE);
        }
        cbSelect.setChecked(item.isSelect);
        if (item.isSelect) {
            ll_item.setBackgroundColor(Color.parseColor("#fafafa"));
        } else {
            ll_item.setBackgroundColor(Color.WHITE);
        }
        mType=item.goodsType;
//        if (TextUtils.equals(mType, "11111")) {
//            cbSelect.setVisibility(View.GONE);
//        }
//        else
//        {
//            cbSelect.setVisibility(View.VISIBLE);
//        }


        if (TextUtils.equals(item.isvalid, "true")) {
            iv_state.setVisibility(View.VISIBLE);
            iv_state.setImageResource(R.drawable.icon_shopcar_lose);
            amountView.setEnable(false);
            helper.addOnClickListener(R.id.right);
        } else if (TextUtils.equals(item.quehuo, "true")) {
            iv_state.setVisibility(View.VISIBLE);
            iv_state.setImageResource(R.drawable.icon_shopcar_lack);
            amountView.setEnable(false);

            helper.addOnClickListener(R.id.right);
        } else {
            iv_state.setVisibility(View.GONE);
            amountView.setEnable(true);

            helper.addOnClickListener(R.id.content).addOnClickListener(R.id.right).addOnClickListener(R.id.ll_checkall).addOnClickListener(R.id.iv_product);
        }

        // 赠送产品


        if (item.giftList != null) {
            List<Integer> limits=new ArrayList<>();
            List<ShopCartModel.GoodsBean.GiftListBean> listBeans=new ArrayList<>();
            int carcount = DensityUtils.parseInt(item.cartcount);
            for (ShopCartModel.GoodsBean.GiftListBean giftListBean : item.giftList) {
                int limit = DensityUtils.parseInt(giftListBean.limit);
                int give = DensityUtils.parseInt(giftListBean.give);
                if (carcount >= limit&&giftListBean.giftInfo!=null) {
                    int div = carcount / limit;
                    int count = div * give;
                    if (count> 0) {
                        limits.add(limit);
                        listBeans.add(giftListBean);
                    }
                }
            }

            if (limits.size()>0)
            {
                int limit=Collections.max(limits);
                for (ShopCartModel.GoodsBean.GiftListBean giftListBean : listBeans) {
                    int limit3 = DensityUtils.parseInt(giftListBean.limit);
                    int give = DensityUtils.parseInt(giftListBean.give);
                    if (limit== limit3) {
                        int div = carcount / limit;
                        int count = div * give;
                        ll_gift.setVisibility(item.isSelect ? View.VISIBLE : View.GONE);
                        ImageView gift_product = helper.getView(R.id.iv_gift_product);
                        GlideUtil.loadImg(UiUtil.getContext(), giftListBean.giftInfo.goodsthumb, gift_product);
                        helper.setText(R.id.tv_gift_title, giftListBean.giftInfo.goodsname)
                                .setText(R.id.tv_gift_gg, giftListBean.giftInfo.goodsgg)
                                .setText(R.id.tv_gift_price, "0.01");
                        helper.setText(R.id.tv_gift_number, "x" + count);
                    }
                }
            }
            else
            {
                ll_gift.setVisibility(View.GONE);
            }
        }
        else
        {
            ll_gift.setVisibility(View.GONE);
        }


        int addnum = DensityUtils.parseInt(item.addmum);
        int minnum = DensityUtils.parseInt(item.minimum);
        helper.setText(R.id.tv_scqy, item.scqy)
                .setText(R.id.tv_price, item.usefulprice)
                .setText(R.id.tv_limit_count, "每" + addnum + "件起购")
                .setText(R.id.tv_gg, item.gg)
                .setVisible(R.id.tv_limit_count, addnum != 1);

        amountView.setMinNum(minnum);
        amountView.setAddNum(addnum);
//        amountView.setIsChanage(false);
        if (TextUtils.isEmpty(item.max)) {
            amountView.setGoods_storage(Integer.MAX_VALUE);
        } else {
            int i = DensityUtils.parseInt(item.max);
            if (i <= 0) {
                if (DensityUtils.parseInt(item.cartcount) < minnum) {
                    amountView.setMinNum(DensityUtils.parseInt(item.cartcount));
                }
                amountView.setGoods_storage(DensityUtils.parseInt(item.cartcount));
            } else {
                amountView.setGoods_storage(i);
            }
        }

        amountView.setAmount((int) DensityUtils.parseDouble(item.cartcount));

        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount, boolean isEdit) {
                ShopCartModel.GoodsBean goodsBean = getData().get(helper.getAdapterPosition());
                // 修改购物车商品数
                if (DensityUtils.parseInt(getData().get(helper.getAdapterPosition()).cartcount) != amount) {
                    if (amount > DensityUtils.parseInt(getData().get(helper.getLayoutPosition()).max)) {
                        getData().get(helper.getAdapterPosition()).cartcount = getData().get(helper.getAdapterPosition()).max;
//                        item.cartcount = item.max;
                    } else {
                        getData().get(helper.getAdapterPosition()).cartcount = String.valueOf(amount);
                        getData().get(helper.getAdapterPosition()).isSelect=true;
//                        item.cartcount = String.valueOf(amount);
                        if (mOnShopGoodListener != null) {
                            mOnShopGoodListener.onAumountChangeListener(view, amount, goodsBean.goodsid,helper.getAdapterPosition() ,isEdit, DensityUtils.parseInt(goodsBean.addmum));
                        }
                    }


                    if (item.giftList != null) {
                        List<Integer> limits=new ArrayList<>();
                        List<ShopCartModel.GoodsBean.GiftListBean> listBeans=new ArrayList<>();
                        int carcount = DensityUtils.parseInt(item.cartcount);
                        for (ShopCartModel.GoodsBean.GiftListBean giftListBean : item.giftList) {
                            int limit = DensityUtils.parseInt(giftListBean.limit);
                            int give = DensityUtils.parseInt(giftListBean.give);
                            if (carcount >= limit&&giftListBean.giftInfo!=null) {
                                int div = carcount / limit;
                                int count = div * give;
                                if (count> 0) {
                                    limits.add(limit);
                                    listBeans.add(giftListBean);
                                }
                            }
                        }

                        if (limits.size()>0)
                        {
                            int limit=Collections.max(limits);
                            for (ShopCartModel.GoodsBean.GiftListBean giftListBean : listBeans) {
                                int limit3 = DensityUtils.parseInt(giftListBean.limit);
                                int give = DensityUtils.parseInt(giftListBean.give);
                                if (limit== limit3) {
                                    int div = carcount / limit;
                                    int count = div * give;
                                    ll_gift.setVisibility(item.isSelect ? View.VISIBLE : View.GONE);
                                    ImageView gift_product = helper.getView(R.id.iv_gift_product);
                                    GlideUtil.loadImg(UiUtil.getContext(), giftListBean.giftInfo.goodsthumb, gift_product);
                                    helper.setText(R.id.tv_gift_title, giftListBean.giftInfo.goodsname)
                                            .setText(R.id.tv_gift_gg, giftListBean.giftInfo.goodsgg)
                                            .setText(R.id.tv_gift_price, "0.01");
                                    helper.setText(R.id.tv_gift_number, "x" + count);
                                }
                            }
                        }
                        else
                        {
                            ll_gift.setVisibility(View.GONE);
                        }
                    }
                    else
                    {
                        ll_gift.setVisibility(View.GONE);
                    }

                }
            }
        });

        if (TextUtils.equals(mType, "1")) {
            // 秒杀
            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs);
            CenterAlignImageSpan imgSpan = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }
        else if(TextUtils.equals(mType,"2")){
            // 特价
            Bitmap          b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
            CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }
        else if (TextUtils.equals(mType, "3")) {
            // 满赠
            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_mz);
            CenterAlignImageSpan imgSpan = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        } else if (TextUtils.equals(mType, "9")) {
            // 控销
            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_kx);
            CenterAlignImageSpan imgSpan = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        } else {
            title.setText(item.title);
        }

        helper.setGone(R.id.ll_single_coupon, TextUtils.equals(mType, "4"))
                .addOnClickListener(R.id.ll_single_coupon);

        if (TextUtils.equals(mType, "4")) {
            if (item.couponList != null && item.couponList.size() > 0) {
                boolean isSelectCoupon = false;
                for (ShopCartModel.CouponListBean couponListBean : item.couponList) {
                    if (couponListBean.isSelectCoupon) {
                        isSelectCoupon = true;
                        helper.setText(R.id.tv_single_coupon, couponListBean.couponcontent);
                    }
                }
                if (!isSelectCoupon) {
                    helper.setText(R.id.tv_single_coupon, "去领券");
                }
            } else {
                helper.setText(R.id.tv_single_coupon, "暂无可用优惠券");
            }
        }

    }


    public interface OnShopGoodListener {
        void onAumountChangeListener(View view, int amount, String goodsid,int position, boolean isEdit, int addnum);
    }

    public void setOnShopGoodListener(OnShopGoodListener listener) {
        this.mOnShopGoodListener = listener;
    }

}
