// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.MarqueeView;
import com.zhouwei.mzbanner.MZBannerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomePageFragment_ViewBinding implements Unbinder {
  private HomePageFragment target;

  private View view2131296463;

  private View view2131296637;

  private View view2131296636;

  private View view2131297022;

  private View view2131296536;

  private View view2131296519;

  private View view2131296631;

  private View view2131296527;

  private View view2131296525;

  @UiThread
  public HomePageFragment_ViewBinding(final HomePageFragment target, View source) {
    this.target = target;

    View view;
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.mLlToolbar = Utils.findRequiredViewAsType(source, R.id.ll_toolbar, "field 'mLlToolbar'", LinearLayout.class);
    target.mMZBanner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'mMZBanner'", MZBannerView.class);
    target.iv_banner_bg = Utils.findRequiredViewAsType(source, R.id.iv_banner_bg, "field 'iv_banner_bg'", ImageView.class);
    target.mRvWebview = Utils.findRequiredViewAsType(source, R.id.rv_webview, "field 'mRvWebview'", RecyclerView.class);
    target.mRvAdvance = Utils.findRequiredViewAsType(source, R.id.rv_advence, "field 'mRvAdvance'", RecyclerView.class);
    target.mRvGood = Utils.findRequiredViewAsType(source, R.id.rv_good, "field 'mRvGood'", RecyclerView.class);
    target.mRvProduct = Utils.findRequiredViewAsType(source, R.id.rv_product, "field 'mRvProduct'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.iv_advence, "field 'mIvAdvence' and method 'onViewClicked'");
    target.mIvAdvence = Utils.castView(view, R.id.iv_advence, "field 'mIvAdvence'", ImageView.class);
    view2131296463 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mNsvHome = Utils.findRequiredViewAsType(source, R.id.nsv_home, "field 'mNsvHome'", NestedScrollView.class);
    target.tv_no_more = Utils.findRequiredViewAsType(source, R.id.tv_no_more, "field 'tv_no_more'", TextView.class);
    target.tvShop = Utils.findRequiredViewAsType(source, R.id.tv_shop, "field 'tvShop'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_store_logout, "field 'llStoreLogout' and method 'onViewClicked'");
    target.llStoreLogout = Utils.castView(view, R.id.ll_store_logout, "field 'llStoreLogout'", LinearLayout.class);
    view2131296637 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_store, "field 'llStore' and method 'onViewClicked'");
    target.llStore = Utils.castView(view, R.id.ll_store, "field 'llStore'", LinearLayout.class);
    view2131296636 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_factory_address, "field 'tvFactoryAddress' and method 'onViewClicked'");
    target.tvFactoryAddress = Utils.castView(view, R.id.tv_factory_address, "field 'tvFactoryAddress'", TextView.class);
    view2131297022 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.marqueeView = Utils.findRequiredViewAsType(source, R.id.marqueeView, "field 'marqueeView'", MarqueeView.class);
    target.llMessage = Utils.findRequiredViewAsType(source, R.id.ll_message, "field 'llMessage'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_top, "field 'ivTop' and method 'onViewClicked'");
    target.ivTop = Utils.castView(view, R.id.iv_top, "field 'ivTop'", ImageView.class);
    view2131296536 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_qrcode, "method 'onViewClicked'");
    view2131296519 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_search, "method 'onViewClicked'");
    view2131296631 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_sign, "method 'onViewClicked'");
    view2131296527 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_service, "method 'onViewClicked'");
    view2131296525 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomePageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeRefreshLayout = null;
    target.mLlToolbar = null;
    target.mMZBanner = null;
    target.iv_banner_bg = null;
    target.mRvWebview = null;
    target.mRvAdvance = null;
    target.mRvGood = null;
    target.mRvProduct = null;
    target.mIvAdvence = null;
    target.mNsvHome = null;
    target.tv_no_more = null;
    target.tvShop = null;
    target.llStoreLogout = null;
    target.llStore = null;
    target.tvFactoryAddress = null;
    target.marqueeView = null;
    target.llMessage = null;
    target.ivTop = null;

    view2131296463.setOnClickListener(null);
    view2131296463 = null;
    view2131296637.setOnClickListener(null);
    view2131296637 = null;
    view2131296636.setOnClickListener(null);
    view2131296636 = null;
    view2131297022.setOnClickListener(null);
    view2131297022 = null;
    view2131296536.setOnClickListener(null);
    view2131296536 = null;
    view2131296519.setOnClickListener(null);
    view2131296519 = null;
    view2131296631.setOnClickListener(null);
    view2131296631 = null;
    view2131296527.setOnClickListener(null);
    view2131296527 = null;
    view2131296525.setOnClickListener(null);
    view2131296525 = null;
  }
}
