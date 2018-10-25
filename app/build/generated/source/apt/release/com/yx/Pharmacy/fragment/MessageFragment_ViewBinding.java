// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageFragment_ViewBinding implements Unbinder {
  private MessageFragment target;

  @UiThread
  public MessageFragment_ViewBinding(MessageFragment target, View source) {
    this.target = target;

    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRecyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'mRecyclerview'", RecyclerView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.ll_error = Utils.findRequiredViewAsType(source, R.id.ll_error, "field 'll_error'", LinearLayout.class);
    target.tv_reload = Utils.findRequiredViewAsType(source, R.id.tv_reload, "field 'tv_reload'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvTitle = null;
    target.mRecyclerview = null;
    target.swipeRefreshLayout = null;
    target.ll_nodata = null;
    target.ll_error = null;
    target.tv_reload = null;
  }
}
