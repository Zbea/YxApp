package com.yx.Pharmacy.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.loader.GlideImageLoader;
import com.yx.Pharmacy.model.ProductDetailModel;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.widget.AmountView;


/**
 * Created by KID on 2018/7/13.
 */

public class AddCartDialog {

    private Context context;
    private ProductDetailModel data;
    private Dialog alertDialog;
    private int type=0;//0为原价购买

    public AddCartDialog(Context context,ProductDetailModel data,int type) {
        this.context = context;
        this.data=data;
        this.type=type;
    }
    public AddCartDialog builder() {
        alertDialog = new Dialog(context, R.style.mycustom_dialog);
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
        TextView tvPrice=win.findViewById(R.id.tv_price);
        tvPrice.setText("￥"+data.price);
        TextView tvInfo=win.findViewById(R.id.tv_info);
        AmountView amountView=win.findViewById(R.id.amount_view);
        amountView.setMinNum(DensityUtils.parseInt(data.minimum));
        amountView.setAddNum(DensityUtils.parseInt(data.addmum));
        if (TextUtils.equals(data.type,"1")&&type==1)
        {
            tvInfo.setText("（每人限购"+data.flashmax+"件）");
            amountView.setGoods_storage(data.flashmax);
        }
        else
        {
            tvInfo.setText("（每人限购"+data.max+"件）");
            amountView.setAmount((int)Double.parseDouble(data.minimum));
            amountView.setGoods_storage(data.max);

        }
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount, boolean isEdit) {
                                // 修改购物车商品数
                if (dialogClickListener != null) {
                    dialogClickListener.onAumountChangeListener(view, amount, isEdit);
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
                cancle();
                if(dialogClickListener!=null)dialogClickListener.ok();
            }
        });
        return this;
    }
    public void show(){
        alertDialog.show();
    }
    public void cancle(){
        alertDialog.dismiss();
    }
    public DialogClickListener dialogClickListener;
    public interface DialogClickListener{
        void ok();
        void onAumountChangeListener(View view, int amount, boolean isEdit);
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }

}
