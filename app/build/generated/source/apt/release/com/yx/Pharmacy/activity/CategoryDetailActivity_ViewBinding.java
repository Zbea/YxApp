// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoryDetailActivity_ViewBinding implements Unbinder {
  private CategoryDetailActivity target;

  private View view2131296774;

  private View view2131296629;

  private View view2131296601;

  private View view2131296626;

  private View view2131297081;

  private View view2131296557;

  private View view2131296510;

  private View view2131296805;

  private View view2131296804;

  @UiThread
  public CategoryDetailActivity_ViewBinding(CategoryDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CategoryDetailActivity_ViewBinding(final CategoryDetailActivity target, View source) {
    this.target = target;

    View view;
    target.tv_zonghe = Utils.findRequiredViewAsType(source, R.id.tv_zonghe, "field 'tv_zonghe'", TextView.class);
    target.iv_zonghe = Utils.findRequiredViewAsType(source, R.id.iv_zonghe, "field 'iv_zonghe'", ImageView.class);
    target.tv_price = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tv_price'", TextView.class);
    target.iv_price = Utils.findRequiredViewAsType(source, R.id.iv_price, "field 'iv_price'", ImageView.class);
    target.tv_xiaoliang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliang, "field 'tv_xiaoliang'", TextView.class);
    target.iv_xiaoliang = Utils.findRequiredViewAsType(source, R.id.iv_xiaoliang, "field 'iv_xiaoliang'", ImageView.class);
    target.iv_layout_mode = Utils.findRequiredViewAsType(source, R.id.iv_layout_mode, "field 'iv_layout_mode'", ImageView.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.ll_error = Utils.findRequiredViewAsType(source, R.id.ll_error, "field 'll_error'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296774 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_zonghe, "method 'click'");
    view2131296629 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_price, "method 'click'");
    view2131296601 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_xiaoliang, "method 'click'");
    view2131296626 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_reload, "method 'click'");
    view2131297081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_layout, "method 'click'");
    view2131296557 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shopping_car, "method 'click'");
    view2131296510 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_search, "method 'click'");
    view2131296805 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_scan, "method 'click'");
    view2131296804 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoryDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_zonghe = null;
    target.iv_zonghe = null;
    target.tv_price = null;
    target.iv_price = null;
    target.tv_xiaoliang = null;
    target.iv_xiaoliang = null;
    target.iv_layout_mode = null;
    target.tv_title = null;
    target.swipeRefreshLayout = null;
    target.ll_nodata = null;
    target.ll_error = null;

    view2131296774.setOnClickListener(null);
    view2131296774 = null;
    view2131296629.setOnClickListener(null);
    view2131296629 = null;
    view2131296601.setOnClickListener(null);
    view2131296601 = null;
    view2131296626.setOnClickListener(null);
    view2131296626 = null;
    view2131297081.setOnClickListener(null);
    view2131297081 = null;
    view2131296557.setOnClickListener(null);
    view2131296557 = null;
    view2131296510.setOnClickListener(null);
    view2131296510 = null;
    view2131296805.setOnClickListener(null);
    view2131296805 = null;
    view2131296804.setOnClickListener(null);
    view2131296804 = null;
  }
}
