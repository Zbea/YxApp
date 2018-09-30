// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.PagerSlidingTabStrip;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AfterSaleActivity_ViewBinding implements Unbinder {
  private AfterSaleActivity target;

  private View view2131296791;

  private View view2131296526;

  @UiThread
  public AfterSaleActivity_ViewBinding(AfterSaleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AfterSaleActivity_ViewBinding(final AfterSaleActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.vp_order = Utils.findRequiredViewAsType(source, R.id.vp_order, "field 'vp_order'", ViewPager.class);
    target.topic_viewpager_title = Utils.findRequiredViewAsType(source, R.id.topic_viewpager_title, "field 'topic_viewpager_title'", PagerSlidingTabStrip.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296791 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_service, "method 'click'");
    view2131296526 = view;
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
    AfterSaleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.vp_order = null;
    target.topic_viewpager_title = null;

    view2131296791.setOnClickListener(null);
    view2131296791 = null;
    view2131296526.setOnClickListener(null);
    view2131296526 = null;
  }
}
