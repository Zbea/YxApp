// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyIntegralActivity_ViewBinding implements Unbinder {
  private MyIntegralActivity target;

  private View view2131297055;

  private View view2131296794;

  private View view2131296813;

  @UiThread
  public MyIntegralActivity_ViewBinding(MyIntegralActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyIntegralActivity_ViewBinding(final MyIntegralActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_more, "field 'tv_more' and method 'click'");
    target.tv_more = Utils.castView(view, R.id.tv_more, "field 'tv_more'", TextView.class);
    view2131297055 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.tv_intergral = Utils.findRequiredViewAsType(source, R.id.tv_intergral, "field 'tv_intergral'", TextView.class);
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296794 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_liwu, "method 'click'");
    view2131296813 = view;
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
    MyIntegralActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_more = null;
    target.tv_intergral = null;
    target.recyclerview = null;

    view2131297055.setOnClickListener(null);
    view2131297055 = null;
    view2131296794.setOnClickListener(null);
    view2131296794 = null;
    view2131296813.setOnClickListener(null);
    view2131296813 = null;
  }
}
