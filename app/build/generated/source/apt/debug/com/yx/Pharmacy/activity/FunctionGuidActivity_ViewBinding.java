// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FunctionGuidActivity_ViewBinding implements Unbinder {
  private FunctionGuidActivity target;

  @UiThread
  public FunctionGuidActivity_ViewBinding(FunctionGuidActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FunctionGuidActivity_ViewBinding(FunctionGuidActivity target, View source) {
    this.target = target;

    target.ivGuid = Utils.findRequiredViewAsType(source, R.id.iv_guid, "field 'ivGuid'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FunctionGuidActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivGuid = null;
  }
}
