// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.LoadingLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyShopActivity_ViewBinding implements Unbinder {
  private MyShopActivity target;

  private View view2131296801;

  private View view2131296832;

  private View view2131296564;

  @UiThread
  public MyShopActivity_ViewBinding(MyShopActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyShopActivity_ViewBinding(final MyShopActivity target, View source) {
    this.target = target;

    View view;
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onClick'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view2131296801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_scan, "field 'mRlScan' and method 'onClick'");
    target.mRlScan = Utils.castView(view, R.id.rl_scan, "field 'mRlScan'", RelativeLayout.class);
    view2131296832 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRvShop = Utils.findRequiredViewAsType(source, R.id.rv_shop, "field 'mRvShop'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_add_shop, "field 'mLlAddShop' and method 'onClick'");
    target.mLlAddShop = Utils.castView(view, R.id.ll_add_shop, "field 'mLlAddShop'", LinearLayout.class);
    view2131296564 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mLoadingLayout = Utils.findRequiredViewAsType(source, R.id.loadinglayout, "field 'mLoadingLayout'", LoadingLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyShopActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvBack = null;
    target.mRlBack = null;
    target.mTvTitle = null;
    target.mRlScan = null;
    target.mRvShop = null;
    target.mLlAddShop = null;
    target.mLoadingLayout = null;

    view2131296801.setOnClickListener(null);
    view2131296801 = null;
    view2131296832.setOnClickListener(null);
    view2131296832 = null;
    view2131296564.setOnClickListener(null);
    view2131296564 = null;
  }
}
