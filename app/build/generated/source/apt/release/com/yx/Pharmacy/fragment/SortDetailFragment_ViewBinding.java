// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SortDetailFragment_ViewBinding implements Unbinder {
  private SortDetailFragment target;

  @UiThread
  public SortDetailFragment_ViewBinding(SortDetailFragment target, View source) {
    this.target = target;

    target.mRv = Utils.findRequiredViewAsType(source, R.id.rv, "field 'mRv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SortDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRv = null;
  }
}
