// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OftenBuyActivity_ViewBinding implements Unbinder {
  private OftenBuyActivity target;

  @UiThread
  public OftenBuyActivity_ViewBinding(OftenBuyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OftenBuyActivity_ViewBinding(OftenBuyActivity target, View source) {
    this.target = target;

    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.rl_more = Utils.findRequiredViewAsType(source, R.id.rl_more, "field 'rl_more'", RelativeLayout.class);
    target.iv_more = Utils.findRequiredViewAsType(source, R.id.iv_more, "field 'iv_more'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OftenBuyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.rl_more = null;
    target.iv_more = null;
  }
}
