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

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296777;
=======
  private View view2131296783;
>>>>>>> feature_1.0

  private View view2131296815;

<<<<<<< HEAD
  private View view2131296547;
=======
  private View view2131296784;

  private View view2131296816;

  private View view2131296550;
>>>>>>> Stashed changes
=======
  private View view2131296549;
>>>>>>> feature_1.0

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
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
=======
    view2131296783 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_scan, "field 'mRlScan' and method 'onClick'");
    target.mRlScan = Utils.castView(view, R.id.rl_scan, "field 'mRlScan'", RelativeLayout.class);
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296806 = view;
=======
    view2131296816 = view;
>>>>>>> Stashed changes
=======
    view2131296815 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRvShop = Utils.findRequiredViewAsType(source, R.id.rv_shop, "field 'mRvShop'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_add_shop, "field 'mLlAddShop' and method 'onClick'");
    target.mLlAddShop = Utils.castView(view, R.id.ll_add_shop, "field 'mLlAddShop'", LinearLayout.class);
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296547 = view;
=======
    view2131296550 = view;
>>>>>>> Stashed changes
=======
    view2131296549 = view;
>>>>>>> feature_1.0
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

<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296806.setOnClickListener(null);
    view2131296806 = null;
    view2131296547.setOnClickListener(null);
    view2131296547 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296816.setOnClickListener(null);
    view2131296816 = null;
    view2131296550.setOnClickListener(null);
    view2131296550 = null;
>>>>>>> Stashed changes
=======
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296815.setOnClickListener(null);
    view2131296815 = null;
    view2131296549.setOnClickListener(null);
    view2131296549 = null;
>>>>>>> feature_1.0
  }
}
