// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
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

  private View view2131297055;

  private View view2131296572;

  private View view2131296972;

  private View view2131296529;

  private View view2131296465;

  private View view2131296794;

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
    view2131297055 = view;
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
    view2131296972 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvPayPrice = Utils.findRequiredViewAsType(source, R.id.tv_pay_price, "field 'mTvPayPrice'", TextView.class);
    target.mRvShopCart = Utils.findRequiredViewAsType(source, R.id.rv_shop_cart, "field 'mRvShopCart'", RecyclerView.class);
    target.mLoadingLayout = Utils.findRequiredViewAsType(source, R.id.loadinglayout, "field 'mLoadingLayout'", LoadingLayout.class);
    target.llIndicate = Utils.findRequiredViewAsType(source, R.id.ll_indicate, "field 'llIndicate'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_top, "field 'ivTop' and method 'onViewClicked'");
    target.ivTop = Utils.castView(view, R.id.iv_top, "field 'ivTop'", ImageView.class);
    view2131296529 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_bottom, "field 'ivBottom' and method 'onViewClicked'");
    target.ivBottom = Utils.castView(view, R.id.iv_bottom, "field 'ivBottom'", ImageView.class);
    view2131296465 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.nestingScrollview = Utils.findRequiredViewAsType(source, R.id.nestedScrollView, "field 'nestingScrollview'", NestedScrollView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onViewClicked'");
    view2131296794 = view;
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
    target.llIndicate = null;
    target.ivTop = null;
    target.ivBottom = null;
    target.nestingScrollview = null;

    view2131297055.setOnClickListener(null);
    view2131297055 = null;
    view2131296572.setOnClickListener(null);
    view2131296572 = null;
    view2131296972.setOnClickListener(null);
    view2131296972 = null;
    view2131296529.setOnClickListener(null);
    view2131296529 = null;
    view2131296465.setOnClickListener(null);
    view2131296465 = null;
    view2131296794.setOnClickListener(null);
    view2131296794 = null;
  }
}
