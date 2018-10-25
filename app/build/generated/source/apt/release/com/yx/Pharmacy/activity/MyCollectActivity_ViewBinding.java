// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.LoadingLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyCollectActivity_ViewBinding implements Unbinder {
  private MyCollectActivity target;

  private View view2131296774;

  @UiThread
  public MyCollectActivity_ViewBinding(MyCollectActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyCollectActivity_ViewBinding(final MyCollectActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.rl_more = Utils.findRequiredViewAsType(source, R.id.rl_more, "field 'rl_more'", RelativeLayout.class);
    target.iv_more = Utils.findRequiredViewAsType(source, R.id.iv_more, "field 'iv_more'", ImageView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    target.mLoadingLayout = Utils.findRequiredViewAsType(source, R.id.loadinglayout, "field 'mLoadingLayout'", LoadingLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296774 = view;
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
    MyCollectActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.rl_more = null;
    target.iv_more = null;
    target.swipeRefreshLayout = null;
    target.recyclerview = null;
    target.mLoadingLayout = null;

    view2131296774.setOnClickListener(null);
    view2131296774 = null;
  }
}
