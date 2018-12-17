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

<<<<<<< Updated upstream
  private View view2131297021;
=======
  private View view2131297035;
>>>>>>> Stashed changes

  private View view2131296490;

<<<<<<< Updated upstream
  private View view2131296777;
=======
  private View view2131296784;
>>>>>>> Stashed changes

  @UiThread
  public CaptureActivity_ViewBinding(CaptureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CaptureActivity_ViewBinding(final CaptureActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_more, "method 'onClick'");
<<<<<<< Updated upstream
    view2131297021 = view;
=======
    view2131297035 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_light, "method 'onClick'");
    view2131296490 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onClick'");
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
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


<<<<<<< Updated upstream
    view2131297021.setOnClickListener(null);
    view2131297021 = null;
    view2131296490.setOnClickListener(null);
    view2131296490 = null;
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
=======
    view2131297035.setOnClickListener(null);
    view2131297035 = null;
    view2131296492.setOnClickListener(null);
    view2131296492 = null;
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
>>>>>>> Stashed changes
  }
}
