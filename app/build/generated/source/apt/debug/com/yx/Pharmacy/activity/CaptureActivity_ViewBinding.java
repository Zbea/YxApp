// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CaptureActivity_ViewBinding implements Unbinder {
  private CaptureActivity target;

  private View view2131297004;

  private View view2131296486;

  private View view2131296764;

  @UiThread
  public CaptureActivity_ViewBinding(CaptureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CaptureActivity_ViewBinding(final CaptureActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_more, "method 'onClick'");
    view2131297004 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_light, "method 'onClick'");
    view2131296486 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onClick'");
    view2131296764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131297004.setOnClickListener(null);
    view2131297004 = null;
    view2131296486.setOnClickListener(null);
    view2131296486 = null;
    view2131296764.setOnClickListener(null);
    view2131296764 = null;
  }
}
