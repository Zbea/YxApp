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

public class CouponFragment_ViewBinding implements Unbinder {
  private CouponFragment target;

  @UiThread
  public CouponFragment_ViewBinding(CouponFragment target, View source) {
    this.target = target;

    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CouponFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerview = null;
  }
}
