// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import android.support.v4.widget.SwipeRefreshLayout;
>>>>>>> Stashed changes
=======
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
>>>>>>> feature_1.0
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.LoadingLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductCartActivity_ViewBinding implements Unbinder {
  private ProductCartActivity target;

  private View view2131297042;

  private View view2131296572;

  private View view2131296959;

  private View view2131296791;

  @UiThread
  public ProductCartActivity_ViewBinding(ProductCartActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductCartActivity_ViewBinding(final ProductCartActivity target, View source) {
    this.target = target;

    View view;
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.mTvNotice = Utils.findRequiredViewAsType(source, R.id.tv_notice, "field 'mTvNotice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_more, "field 'mTvMore' and method 'onViewClicked'");
    target.mTvMore = Utils.castView(view, R.id.tv_more, "field 'mTvMore'", TextView.class);
    view2131297042 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_checkall, "field 'mLlCheckall' and method 'onViewClicked'");
    target.mLlCheckall = Utils.castView(view, R.id.ll_checkall, "field 'mLlCheckall'", LinearLayout.class);
    view2131296572 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mCbCheckall = Utils.findRequiredViewAsType(source, R.id.cb_checkall, "field 'mCbCheckall'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.tv_buy, "field 'mTvBuy' and method 'onViewClicked'");
    target.mTvBuy = Utils.castView(view, R.id.tv_buy, "field 'mTvBuy'", TextView.class);
    view2131296959 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvPayPrice = Utils.findRequiredViewAsType(source, R.id.tv_pay_price, "field 'mTvPayPrice'", TextView.class);
    target.mRvShopCart = Utils.findRequiredViewAsType(source, R.id.rv_shop_cart, "field 'mRvShopCart'", RecyclerView.class);
    target.mLoadingLayout = Utils.findRequiredViewAsType(source, R.id.loadinglayout, "field 'mLoadingLayout'", LoadingLayout.class);
    target.nestingScrollview = Utils.findRequiredViewAsType(source, R.id.nestedScrollView, "field 'nestingScrollview'", NestedScrollView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onViewClicked'");
    view2131296791 = view;
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
    ProductCartActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvTitle = null;
    target.swipeRefreshLayout = null;
    target.mTvNotice = null;
    target.mTvMore = null;
    target.mLlCheckall = null;
    target.mCbCheckall = null;
    target.mTvBuy = null;
    target.mTvPayPrice = null;
    target.mRvShopCart = null;
    target.mLoadingLayout = null;
    target.nestingScrollview = null;

    view2131297042.setOnClickListener(null);
    view2131297042 = null;
    view2131296572.setOnClickListener(null);
    view2131296572 = null;
    view2131296959.setOnClickListener(null);
    view2131296959 = null;
    view2131296791.setOnClickListener(null);
    view2131296791 = null;
  }
}
