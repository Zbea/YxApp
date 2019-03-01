// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoryFragment_ViewBinding implements Unbinder {
  private CategoryFragment target;

  private View view2131296631;

  private View view2131296832;

  @UiThread
  public CategoryFragment_ViewBinding(final CategoryFragment target, View source) {
    this.target = target;

    View view;
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.rvSort = Utils.findRequiredViewAsType(source, R.id.rv_sort, "field 'rvSort'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_search, "method 'click'");
    view2131296631 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_scan, "method 'click'");
    view2131296832 = view;
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
    CategoryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeRefreshLayout = null;
    target.rvSort = null;

    view2131296631.setOnClickListener(null);
    view2131296631 = null;
    view2131296832.setOnClickListener(null);
    view2131296832 = null;
  }
}
