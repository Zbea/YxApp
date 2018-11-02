// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cn.iwgang.countdownview.CountdownView;
import com.youth.banner.Banner;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.LoadingLayout;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.apache.cordova.engine.SystemWebView;

public class ProductDetailActivity_ViewBinding implements Unbinder {
  private ProductDetailActivity target;

  private View view2131296777;

  private View view2131296785;

  private View view2131296795;

  private View view2131296610;

  private View view2131296476;

  private View view2131296466;

  private View view2131296918;

  private View view2131297015;

  private View view2131296511;

  @UiThread
  public ProductDetailActivity_ViewBinding(ProductDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductDetailActivity_ViewBinding(final ProductDetailActivity target, View source) {
    this.target = target;

    View view;
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'mBanner'", Banner.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onClick'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view2131296777 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_collect, "field 'mRlCollect' and method 'onClick'");
    target.mRlCollect = Utils.castView(view, R.id.rl_collect, "field 'mRlCollect'", RelativeLayout.class);
    view2131296785 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvProductName = Utils.findRequiredViewAsType(source, R.id.tv_product_name, "field 'mTvProductName'", TextView.class);
    target.mTvProductCompany = Utils.findRequiredViewAsType(source, R.id.tv_product_company, "field 'mTvProductCompany'", TextView.class);
    target.mTvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'mTvPrice'", TextView.class);
    target.mTvOldprice = Utils.findRequiredViewAsType(source, R.id.tv_oldprice, "field 'mTvOldprice'", TextView.class);
    target.mTvUnit = Utils.findRequiredViewAsType(source, R.id.tv_unit, "field 'mTvUnit'", TextView.class);
    target.mTvHasSale = Utils.findRequiredViewAsType(source, R.id.tv_has_sale, "field 'mTvHasSale'", TextView.class);
    target.mIvJiantouGery = Utils.findRequiredViewAsType(source, R.id.iv_jiantou_gery, "field 'mIvJiantouGery'", ImageView.class);
    target.mLlManjian = Utils.findRequiredViewAsType(source, R.id.ll_manjian, "field 'mLlManjian'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_manjian, "field 'mRlManjian' and method 'onClick'");
    target.mRlManjian = Utils.castView(view, R.id.rl_manjian, "field 'mRlManjian'", RelativeLayout.class);
    view2131296795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ll_coupon = Utils.findRequiredViewAsType(source, R.id.ll_coupon, "field 'll_coupon'", LinearLayout.class);
    target.tv_coupon_info = Utils.findRequiredViewAsType(source, R.id.tv_coupon_info, "field 'tv_coupon_info'", TextView.class);
    target.mTvSaleRecord = Utils.findRequiredViewAsType(source, R.id.tv_sale_record, "field 'mTvSaleRecord'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_sale_record, "field 'mLlSaleRecord' and method 'onClick'");
    target.mLlSaleRecord = Utils.castView(view, R.id.ll_sale_record, "field 'mLlSaleRecord'", LinearLayout.class);
    view2131296610 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mLlMore = Utils.findRequiredViewAsType(source, R.id.ll_more, "field 'mLlMore'", LinearLayout.class);
    target.mRvCommendProduct = Utils.findRequiredViewAsType(source, R.id.rv_commend_product, "field 'mRvCommendProduct'", RecyclerView.class);
    target.mWebview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'mWebview'", SystemWebView.class);
    view = Utils.findRequiredView(source, R.id.iv_home, "field 'mIvHome' and method 'onClick'");
    target.mIvHome = Utils.castView(view, R.id.iv_home, "field 'mIvHome'", ImageView.class);
    view2131296476 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_cart, "field 'mIvCart' and method 'onClick'");
    target.mIvCart = Utils.castView(view, R.id.iv_cart, "field 'mIvCart'", ImageView.class);
    view2131296466 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_add_cart, "field 'mTvAddCart' and method 'onClick'");
    target.mTvAddCart = Utils.castView(view, R.id.tv_add_cart, "field 'mTvAddCart'", TextView.class);
    view2131296918 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvNum = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'mTvNum'", TextView.class);
    target.mTvPresale = Utils.findRequiredViewAsType(source, R.id.tv_presale, "field 'mTvPresale'", TextView.class);
    target.iv_is_collect = Utils.findRequiredViewAsType(source, R.id.iv_is_collect, "field 'iv_is_collect'", ImageView.class);
    target.mIvCouponIcon = Utils.findRequiredViewAsType(source, R.id.iv_coupon_icon, "field 'mIvCouponIcon'", ImageView.class);
    target.mTvTongyong = Utils.findRequiredViewAsType(source, R.id.tv_tongyong, "field 'mTvTongyong'", TextView.class);
    target.mTvPizhun = Utils.findRequiredViewAsType(source, R.id.tv_pizhun, "field 'mTvPizhun'", TextView.class);
    target.mTvGuige = Utils.findRequiredViewAsType(source, R.id.tv_guige, "field 'mTvGuige'", TextView.class);
    target.mTvChangjia = Utils.findRequiredViewAsType(source, R.id.tv_changjia, "field 'mTvChangjia'", TextView.class);
    target.mTvRiqi = Utils.findRequiredViewAsType(source, R.id.tv_riqi, "field 'mTvRiqi'", TextView.class);
    target.mTvYouxiao = Utils.findRequiredViewAsType(source, R.id.tv_youxiao, "field 'mTvYouxiao'", TextView.class);
    target.mIvProductType = Utils.findRequiredViewAsType(source, R.id.iv_product_type, "field 'mIvProductType'", ImageView.class);
    target.mTvTopPrice = Utils.findRequiredViewAsType(source, R.id.tv_top_price, "field 'mTvTopPrice'", TextView.class);
    target.mTvTopOldPrice = Utils.findRequiredViewAsType(source, R.id.tv_top_old_price, "field 'mTvTopOldPrice'", TextView.class);
    target.mTvTimeState = Utils.findRequiredViewAsType(source, R.id.tv_time_state, "field 'mTvTimeState'", TextView.class);
    target.mCvCountdownView = Utils.findRequiredViewAsType(source, R.id.cv_countdownView, "field 'mCvCountdownView'", CountdownView.class);
    target.mCvCountdownViewDay = Utils.findRequiredViewAsType(source, R.id.cv_countdownView_day, "field 'mCvCountdownViewDay'", CountdownView.class);
    view = Utils.findRequiredView(source, R.id.tv_look_gift, "field 'mTvLookGift' and method 'onClick'");
    target.mTvLookGift = Utils.castView(view, R.id.tv_look_gift, "field 'mTvLookGift'", TextView.class);
    view2131297015 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvTopUnit = Utils.findRequiredViewAsType(source, R.id.tv_top_unit, "field 'mTvTopUnit'", TextView.class);
    target.mTvLevelnote = Utils.findRequiredViewAsType(source, R.id.tv_levelnote, "field 'mTvLevelnote'", TextView.class);
    target.ll_levelnote = Utils.findRequiredViewAsType(source, R.id.ll_levelnote, "field 'll_levelnote'", LinearLayout.class);
    target.mRlInfo = Utils.findRequiredViewAsType(source, R.id.rl_info, "field 'mRlInfo'", RelativeLayout.class);
    target.mRlCountDown = Utils.findRequiredViewAsType(source, R.id.ll_countdown, "field 'mRlCountDown'", LinearLayout.class);
    target.ll_price = Utils.findRequiredViewAsType(source, R.id.ll_price, "field 'll_price'", LinearLayout.class);
    target.rl_gg = Utils.findRequiredViewAsType(source, R.id.rl_gg, "field 'rl_gg'", RelativeLayout.class);
    target.loadinglayout = Utils.findRequiredViewAsType(source, R.id.loadinglayout, "field 'loadinglayout'", LoadingLayout.class);
    target.tv_sales = Utils.findRequiredViewAsType(source, R.id.tv_sales, "field 'tv_sales'", TextView.class);
    target.tv_product_progress = Utils.findRequiredViewAsType(source, R.id.tv_product_progress, "field 'tv_product_progress'", TextView.class);
    target.progress_bar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progress_bar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.iv_service, "method 'onClick'");
    view2131296511 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBanner = null;
    target.mRlBack = null;
    target.mRlCollect = null;
    target.mTvProductName = null;
    target.mTvProductCompany = null;
    target.mTvPrice = null;
    target.mTvOldprice = null;
    target.mTvUnit = null;
    target.mTvHasSale = null;
    target.mIvJiantouGery = null;
    target.mLlManjian = null;
    target.mRlManjian = null;
    target.ll_coupon = null;
    target.tv_coupon_info = null;
    target.mTvSaleRecord = null;
    target.mLlSaleRecord = null;
    target.mLlMore = null;
    target.mRvCommendProduct = null;
    target.mWebview = null;
    target.mIvHome = null;
    target.mIvCart = null;
    target.mTvAddCart = null;
    target.mTvNum = null;
    target.mTvPresale = null;
    target.iv_is_collect = null;
    target.mIvCouponIcon = null;
    target.mTvTongyong = null;
    target.mTvPizhun = null;
    target.mTvGuige = null;
    target.mTvChangjia = null;
    target.mTvRiqi = null;
    target.mTvYouxiao = null;
    target.mIvProductType = null;
    target.mTvTopPrice = null;
    target.mTvTopOldPrice = null;
    target.mTvTimeState = null;
    target.mCvCountdownView = null;
    target.mCvCountdownViewDay = null;
    target.mTvLookGift = null;
    target.mTvTopUnit = null;
    target.mTvLevelnote = null;
    target.ll_levelnote = null;
    target.mRlInfo = null;
    target.mRlCountDown = null;
    target.ll_price = null;
    target.rl_gg = null;
    target.loadinglayout = null;
    target.tv_sales = null;
    target.tv_product_progress = null;
    target.progress_bar = null;

    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296785.setOnClickListener(null);
    view2131296785 = null;
    view2131296795.setOnClickListener(null);
    view2131296795 = null;
    view2131296610.setOnClickListener(null);
    view2131296610 = null;
    view2131296476.setOnClickListener(null);
    view2131296476 = null;
    view2131296466.setOnClickListener(null);
    view2131296466 = null;
    view2131296918.setOnClickListener(null);
    view2131296918 = null;
    view2131297015.setOnClickListener(null);
    view2131297015 = null;
    view2131296511.setOnClickListener(null);
    view2131296511 = null;
  }
}
