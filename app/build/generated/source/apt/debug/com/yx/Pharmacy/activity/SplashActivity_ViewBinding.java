// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashActivity_ViewBinding implements Unbinder {
  private SplashActivity target;

  private View view2131296397;

  @UiThread
  public SplashActivity_ViewBinding(SplashActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashActivity_ViewBinding(final SplashActivity target, View source) {
    this.target = target;

    View view;
    target.iv_splash = Utils.findRequiredViewAsType(source, R.id.iv_splash, "field 'iv_splash'", ImageView.class);
    target.vp_guide = Utils.findRequiredViewAsType(source, R.id.vp_guide, "field 'vp_guide'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.enter_home_page_bottom_layout, "field 'enter_home_page_bottom_layout' and method 'click'");
    target.enter_home_page_bottom_layout = Utils.castView(view, R.id.enter_home_page_bottom_layout, "field 'enter_home_page_bottom_layout'", LinearLayout.class);
    view2131296397 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.tv_downcount = Utils.findRequiredViewAsType(source, R.id.tv_downcount, "field 'tv_downcount'", TextView.class);
    target.rl_downcount = Utils.findRequiredViewAsType(source, R.id.rl_downcount, "field 'rl_downcount'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_splash = null;
    target.vp_guide = null;
    target.enter_home_page_bottom_layout = null;
    target.tv_downcount = null;
    target.rl_downcount = null;

    view2131296397.setOnClickListener(null);
    view2131296397 = null;
  }
}
