// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IntegralDetailActivity_ViewBinding implements Unbinder {
  private IntegralDetailActivity target;

<<<<<<< HEAD
  private View view2131296778;

  private View view2131297087;
=======
  private View view2131296791;

  private View view2131297106;
>>>>>>> feature_1.0

  @UiThread
  public IntegralDetailActivity_ViewBinding(IntegralDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public IntegralDetailActivity_ViewBinding(final IntegralDetailActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.ll_error = Utils.findRequiredViewAsType(source, R.id.ll_error, "field 'll_error'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
<<<<<<< HEAD
    view2131296778 = view;
=======
    view2131296791 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_reload, "method 'click'");
<<<<<<< HEAD
    view2131297087 = view;
=======
    view2131297106 = view;
>>>>>>> feature_1.0
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
    IntegralDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.recyclerview = null;
    target.swipeRefreshLayout = null;
    target.ll_nodata = null;
    target.ll_error = null;

<<<<<<< HEAD
    view2131296778.setOnClickListener(null);
    view2131296778 = null;
    view2131297087.setOnClickListener(null);
    view2131297087 = null;
=======
    view2131296791.setOnClickListener(null);
    view2131296791 = null;
    view2131297106.setOnClickListener(null);
    view2131297106 = null;
>>>>>>> feature_1.0
  }
}
