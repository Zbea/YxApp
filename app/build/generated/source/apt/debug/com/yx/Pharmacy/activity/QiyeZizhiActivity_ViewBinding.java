// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QiyeZizhiActivity_ViewBinding implements Unbinder {
  private QiyeZizhiActivity target;

  private View view2131296795;

  @UiThread
  public QiyeZizhiActivity_ViewBinding(QiyeZizhiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QiyeZizhiActivity_ViewBinding(final QiyeZizhiActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onViewClicked'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view2131296795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRvZizhi = Utils.findRequiredViewAsType(source, R.id.rv_zizhi, "field 'mRvZizhi'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QiyeZizhiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRlBack = null;
    target.mTvTitle = null;
    target.mRvZizhi = null;

    view2131296795.setOnClickListener(null);
    view2131296795 = null;
  }
}
