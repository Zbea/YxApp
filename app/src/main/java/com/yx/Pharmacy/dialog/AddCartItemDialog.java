package com.yx.Pharmacy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.loader.GlideImageLoader;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.widget.AmountView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by KID on 2018/7/13.
 */

public class AddCartItemDialog {

    private Context context;
    private DrugModel data;
    private Dialog alertDialog;
    private int type=0;//0为原价购买
    private int cartCount=1;

    public AddCartItemDialog(Context context, DrugModel data, int type) {
        this.context = context;
        this.data=data;
        this.type=type;
    }
    public AddCartItemDialog builder() {
        alertDialog = new Dialog(context, R.style.DialogStyle);
        alertDialog.show();
        Window win = alertDialog.getWindow();
        win.setWindowAnimations(R.style.mystyle);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        // 设置弹出框的宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 设置弹出框的位置
        win.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        win.setAttributes(lp);
        win.setContentView(R.layout.dialog_product_details_add);

        Banner mBanner=win.findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBanner.setViewPagerIsScroll(true);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(false);
        if (data.pic != null) {
            mBanner.setImages(data.pic);
            mBanner.start();
        }
        TextView tvTitle=win.findViewById(R.id.tv_title);
        tvTitle.setText(data.getTitle());
        TextView tvSpecification=win.findViewById(R.id.tv_specification);
        tvSpecification.setText(data.getGg());
        TextView tvFactory=win.findViewById(R.id.tv_factory);
        tvFactory.setText(data.getScqy());
        TextView tvPrice=win.findViewById(R.id.tv_price);
        TextView tvInfo=win.findViewById(R.id.tv_info);
        TextView tvRiqi=win.findViewById(R.id.tv_pihao);
        TextView tvYouxiao=win.findViewById(R.id.tv_youxiao);
        tvRiqi.setText(data.ph1);
        if (TextUtils.isEmpty(data.validtime))
        {
            tvYouxiao.setVisibility(View.GONE);
        }
        else
        {
            tvYouxiao.setText(DensityUtils.getDayMothDate(DensityUtils.parseLong(data.validtime)*1000));
        }
        AmountView amountView=win.findViewById(R.id.amount_view);
        amountView.setMinNum(DensityUtils.parseInt(data.minimum));
        amountView.setAddNum(DensityUtils.parseInt(data.addmum));
        amountView.setIsChanage(true);
        if (data.getType()==1||data.getType()==2)
        {
            if (type==1)
            {
                tvPrice.setText("￥"+data.getPrice());
                tvInfo.setText("（剩余库存"+String.valueOf(Double.parseDouble(data.flashmax)<=0?0:data.flashmax)+"）");
                amountView.setGoods_storage((int) Math.floor(DensityUtils.parseDouble(data.flashmax)));
            }
            else
            {
                tvPrice.setText("￥"+data.getOldprice());
                tvInfo.setText("（剩余库存"+String.valueOf(data.max<=0?0:data.max)+"）");
                amountView.setGoods_storage((int) Math.floor(data.max));
            }
        }
        else
        {
            tvPrice.setText("￥"+data.getPrice());
            tvInfo.setText("（剩余库存"+String.valueOf(data.max<=0?0:data.max)+"）");
            amountView.setGoods_storage((int) Math.floor(data.max));
        }
        amountView.setAmount((int) Math.floor(DensityUtils.parseDouble(data.minimum)));

        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount, boolean isEdit) {
                                // 修改购物车商品数
                cartCount = amount;
                double i = (double) amount / Double.parseDouble(data.addmum);
                if (i % 1 != 0) {
                    NetUtil.getShortToastByString("输入商品的数量必须是起购量的倍数");
                    cartCount = (int) (DensityUtils.parseInt(data.addmum) * (int) i);
                    ((AmountView) view).setAmount(cartCount);
                }
            }
        });

        win.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });
        win.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart();
            }
        });
        return this;
    }

    private void addCart()
    {
        if (cartCount <= 0) {
            ((BaseActivity) context).getShortToastByString("起购量必须大于0");
            return;
        }
        if (data != null && (data.getType()==1||data.getType()==2) && type == 1) { //特价商品特殊处理
           miaoshaBuy((BaseActivity) context, String.valueOf(data.getItemid()), cartCount);
        } else {
            addCartProduct((BaseActivity) context,data, cartCount);
        }
    }

    /**
     * 添加商品到购物车
     */
    public void addCartProduct(final BaseActivity activity, final DrugModel item,int count ) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",item.getItemid()+"");
        urlMap.put("count", count+"");
        HomeNet.getHomeApi().addShopcart(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData()!=null) {
                            compelete();
                        }else {
                            activity.getShortToastByString(response.getAlertmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
    /**
     * 秒杀专区商品秒杀价购买
     */
    public void miaoshaBuy(BaseActivity activity, String pid, int count) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("count", "" + count);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (TextUtils.equals(response.getCode(), "201")) {
                            showComfirmDialog2();
                        } else {
                            if (!TextUtils.isEmpty(response.getAlertmsg()))
                            {
                                activity.getShortToastByString(response.getAlertmsg());
                            }
                            else
                            {
                                compelete();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error=========" + e.toString());
                        super.onError(e);
                    }
                });
    }

    //询问是否覆盖
    private void showComfirmDialog2() {
        ConfirmDialog confirmDialog = new ConfirmDialog(context);
        confirmDialog.setTitle("温馨提示").setContent("购物车中已有秒杀商品，是否覆盖").setcancle("否").setOk("是").builder().show();
        confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
            @Override
            public void ok() {//覆盖
                confirmDialog.cancle();
                miaoshaBuy((BaseActivity) context, data.getItemid()+"", "1", String.valueOf(cartCount));
            }

            @Override
            public void cancle() {//不覆盖
                confirmDialog.cancle();
            }
        });
    }

    /**
     * 秒杀专区商品秒杀价购买
     * confirm：是否覆盖购物车内的的秒杀活动商品 0 不覆盖  1覆盖
     */
    public void miaoshaBuy(BaseActivity activity, String pid, String confirm,String count) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("confirm", confirm);
        urlMap.put("count", count);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (TextUtils.equals(response.getCode(), "200")) {
                            compelete();
                        }
                        else
                        {
                            if (!TextUtils.isEmpty(response.getAlertmsg()))
                                activity.getShortToastByString(response.getAlertmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error=========" + e.toString());
                        super.onError(e);
                    }
                });
    }

    /**
     * 添加成功
     */
    private void compelete() {
        ((BaseActivity) context).getShortToastByString("添加成功");
        cancle();
        if (dialogClickListener!=null)
            dialogClickListener.ok(cartCount);
    }


    public void show(){
        alertDialog.show();
    }
    public void cancle(){
        alertDialog.dismiss();
    }
    public DialogClickListener dialogClickListener;
    public interface DialogClickListener{
        void ok(int count);
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }

}
