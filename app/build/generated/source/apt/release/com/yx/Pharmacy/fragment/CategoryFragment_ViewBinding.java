// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

  private View view2131296608;

  private View view2131296804;

  @UiThread
  public CategoryFragment_ViewBinding(final CategoryFragment target, View source) {
    this.target = target;

    View view;
    target.rvSort = Utils.findRequiredViewAsType(source, R.id.rv_sort, "field 'rvSort'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_search, "method 'click'");
    view2131296608 = view;
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
    CategoryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvSort = null;

    view2131296608.setOnClickListener(null);
    view2131296608 = null;
    view2131296804.setOnClickListener(null);
    view2131296804 = null;
  }
}
