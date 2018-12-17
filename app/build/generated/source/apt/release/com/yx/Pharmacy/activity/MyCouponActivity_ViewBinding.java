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

public class MyCouponActivity_ViewBinding implements Unbinder {
  private MyCouponActivity target;

  private View view2131296777;

<<<<<<< Updated upstream
  private View view2131296796;
=======
  private View view2131296784;

  private View view2131296806;
>>>>>>> Stashed changes

  @UiThread
  public MyCouponActivity_ViewBinding(MyCouponActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyCouponActivity_ViewBinding(final MyCouponActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
    target.vp_coupon = Utils.findRequiredViewAsType(source, R.id.vp_coupon, "field 'vp_coupon'", ViewPager.class);
    target.topic_viewpager_title = Utils.findRequiredViewAsType(source, R.id.topic_viewpager_title, "field 'topic_viewpager_title'", PagerSlidingTabStrip.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_more, "method 'click'");
<<<<<<< Updated upstream
    view2131296796 = view;
=======
    view2131296806 = view;
>>>>>>> Stashed changes
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
    MyCouponActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_more = null;
    target.vp_coupon = null;
    target.topic_viewpager_title = null;

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296796.setOnClickListener(null);
    view2131296796 = null;
=======
    view2131296475.setOnClickListener(null);
    view2131296475 = null;
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296806.setOnClickListener(null);
    view2131296806 = null;
>>>>>>> Stashed changes
  }
}
