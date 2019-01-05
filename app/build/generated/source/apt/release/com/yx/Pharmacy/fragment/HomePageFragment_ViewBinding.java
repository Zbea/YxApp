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

  private View view2131296456;

  private View view2131296626;

  private View view2131296625;

  private View view2131297000;

  private View view2131296525;

  private View view2131296510;

  private View view2131296619;

  private View view2131296517;

  private View view2131296515;

  @UiThread
  public HomePageFragment_ViewBinding(final HomePageFragment target, View source) {
    this.target = target;

    View view;
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.mLlToolbar = Utils.findRequiredViewAsType(source, R.id.ll_toolbar, "field 'mLlToolbar'", LinearLayout.class);
    target.mMZBanner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'mMZBanner'", MZBannerView.class);
    target.iv_banner_bg = Utils.findRequiredViewAsType(source, R.id.iv_banner_bg, "field 'iv_banner_bg'", ImageView.class);
    target.mRvWebview = Utils.findRequiredViewAsType(source, R.id.rv_webview, "field 'mRvWebview'", RecyclerView.class);
    target.mRvGood = Utils.findRequiredViewAsType(source, R.id.rv_good, "field 'mRvGood'", RecyclerView.class);
    target.mRvProduct = Utils.findRequiredViewAsType(source, R.id.rv_product, "field 'mRvProduct'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.iv_advence, "field 'mIvAdvence' and method 'onViewClicked'");
    target.mIvAdvence = Utils.castView(view, R.id.iv_advence, "field 'mIvAdvence'", ImageView.class);
    view2131296456 = view;
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
    view2131296626 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_store, "field 'llStore' and method 'onViewClicked'");
    target.llStore = Utils.castView(view, R.id.ll_store, "field 'llStore'", LinearLayout.class);
    view2131296625 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_factory_address, "field 'tvFactoryAddress' and method 'onViewClicked'");
    target.tvFactoryAddress = Utils.castView(view, R.id.tv_factory_address, "field 'tvFactoryAddress'", TextView.class);
    view2131297000 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.marqueeView = Utils.findRequiredViewAsType(source, R.id.marqueeView, "field 'marqueeView'", MarqueeView.class);
    view = Utils.findRequiredView(source, R.id.iv_top, "field 'ivTop' and method 'onViewClicked'");
    target.ivTop = Utils.castView(view, R.id.iv_top, "field 'ivTop'", ImageView.class);
    view2131296525 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_qrcode, "method 'onViewClicked'");
    view2131296510 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_search, "method 'onViewClicked'");
    view2131296619 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_sign, "method 'onViewClicked'");
    view2131296517 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_service, "method 'onViewClicked'");
    view2131296515 = view;
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
    target.ivTop = null;

    view2131296456.setOnClickListener(null);
    view2131296456 = null;
    view2131296626.setOnClickListener(null);
    view2131296626 = null;
    view2131296625.setOnClickListener(null);
    view2131296625 = null;
    view2131297000.setOnClickListener(null);
    view2131297000 = null;
    view2131296525.setOnClickListener(null);
    view2131296525 = null;
    view2131296510.setOnClickListener(null);
    view2131296510 = null;
    view2131296619.setOnClickListener(null);
    view2131296619 = null;
    view2131296517.setOnClickListener(null);
    view2131296517 = null;
    view2131296515.setOnClickListener(null);
    view2131296515 = null;
  }
}
