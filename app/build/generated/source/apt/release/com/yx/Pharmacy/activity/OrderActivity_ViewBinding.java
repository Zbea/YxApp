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

public class OrderActivity_ViewBinding implements Unbinder {
  private OrderActivity target;

<<<<<<< Updated upstream
  private View view2131297021;

  private View view2131296777;
=======
  private View view2131297035;

  private View view2131296784;
>>>>>>> Stashed changes

  @UiThread
  public OrderActivity_ViewBinding(OrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderActivity_ViewBinding(final OrderActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_more, "field 'tv_more' and method 'click'");
    target.tv_more = Utils.castView(view, R.id.tv_more, "field 'tv_more'", TextView.class);
<<<<<<< Updated upstream
    view2131297021 = view;
=======
    view2131297035 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.vp_order = Utils.findRequiredViewAsType(source, R.id.vp_order, "field 'vp_order'", ViewPager.class);
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
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_more = null;
    target.vp_order = null;
    target.topic_viewpager_title = null;

<<<<<<< Updated upstream
    view2131297021.setOnClickListener(null);
    view2131297021 = null;
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
=======
    view2131297035.setOnClickListener(null);
    view2131297035 = null;
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
>>>>>>> Stashed changes
  }
}
