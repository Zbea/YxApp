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

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296777;
=======
  private View view2131296784;
>>>>>>> Stashed changes
=======
  private View view2131296783;
>>>>>>> feature_1.0

  private View view2131296513;

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
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
=======
    view2131296783 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_service, "method 'click'");
    view2131296513 = view;
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

<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296511.setOnClickListener(null);
    view2131296511 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296513.setOnClickListener(null);
    view2131296513 = null;
>>>>>>> Stashed changes
=======
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296513.setOnClickListener(null);
    view2131296513 = null;
>>>>>>> feature_1.0
  }
}
