// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131296578;

  private View view2131296611;

  private View view2131296571;

  private View view2131296605;

  private View view2131296580;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.ll_main_container = Utils.findRequiredViewAsType(source, R.id.ll_main_container, "field 'll_main_container'", LinearLayout.class);
    target.iv_message = Utils.findRequiredViewAsType(source, R.id.iv_message, "field 'iv_message'", ImageView.class);
    target.tv_message = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tv_message'", TextView.class);
    target.iv_type = Utils.findRequiredViewAsType(source, R.id.iv_type, "field 'iv_type'", ImageView.class);
    target.tv_type = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tv_type'", TextView.class);
    target.iv_home_page = Utils.findRequiredViewAsType(source, R.id.iv_home_page, "field 'iv_home_page'", ImageView.class);
    target.tv_home_page = Utils.findRequiredViewAsType(source, R.id.tv_home_page, "field 'tv_home_page'", TextView.class);
    target.iv_shopping_car = Utils.findRequiredViewAsType(source, R.id.iv_shopping_car, "field 'iv_shopping_car'", ImageView.class);
    target.tv_shopping_car = Utils.findRequiredViewAsType(source, R.id.tv_shopping_car, "field 'tv_shopping_car'", TextView.class);
    target.iv_my = Utils.findRequiredViewAsType(source, R.id.iv_my, "field 'iv_my'", ImageView.class);
    target.tv_my = Utils.findRequiredViewAsType(source, R.id.tv_my, "field 'tv_my'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_message, "method 'onClick'");
    view2131296578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_type, "method 'onClick'");
    view2131296611 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_home_page, "method 'onClick'");
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_shopping_car, "method 'onClick'");
    view2131296605 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my, "method 'onClick'");
    view2131296580 = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ll_main_container = null;
    target.iv_message = null;
    target.tv_message = null;
    target.iv_type = null;
    target.tv_type = null;
    target.iv_home_page = null;
    target.tv_home_page = null;
    target.iv_shopping_car = null;
    target.tv_shopping_car = null;
    target.iv_my = null;
    target.tv_my = null;

    view2131296578.setOnClickListener(null);
    view2131296578 = null;
    view2131296611.setOnClickListener(null);
    view2131296611 = null;
    view2131296571.setOnClickListener(null);
    view2131296571 = null;
    view2131296605.setOnClickListener(null);
    view2131296605 = null;
    view2131296580.setOnClickListener(null);
    view2131296580 = null;
  }
}
