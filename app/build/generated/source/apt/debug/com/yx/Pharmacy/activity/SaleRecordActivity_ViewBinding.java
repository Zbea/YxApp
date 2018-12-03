// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
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
import java.lang.IllegalStateException;
import java.lang.Override;

public class SaleRecordActivity_ViewBinding implements Unbinder {
  private SaleRecordActivity target;

  private View view2131296811;

  private View view2131296790;

  private View view2131296485;

  private View view2131296473;

  @UiThread
  public SaleRecordActivity_ViewBinding(SaleRecordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SaleRecordActivity_ViewBinding(final SaleRecordActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_more, "field 'rl_more' and method 'onClick'");
    target.rl_more = Utils.castView(view, R.id.rl_more, "field 'rl_more'", RelativeLayout.class);
    view2131296811 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.iv_more = Utils.findRequiredViewAsType(source, R.id.iv_more, "field 'iv_more'", ImageView.class);
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.mTvNum = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'mTvNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onClick'");
    view2131296790 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_home, "method 'onClick'");
    view2131296485 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_cart, "method 'onClick'");
    view2131296473 = view;
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
    SaleRecordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.rl_more = null;
    target.iv_more = null;
    target.recyclerview = null;
    target.swipeRefreshLayout = null;
    target.ll_nodata = null;
    target.mTvNum = null;

    view2131296811.setOnClickListener(null);
    view2131296811 = null;
    view2131296790.setOnClickListener(null);
    view2131296790 = null;
    view2131296485.setOnClickListener(null);
    view2131296485 = null;
    view2131296473.setOnClickListener(null);
    view2131296473 = null;
  }
}
