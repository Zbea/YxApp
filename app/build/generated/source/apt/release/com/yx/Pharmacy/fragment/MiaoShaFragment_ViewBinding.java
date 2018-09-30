// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.LoadingLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MiaoShaFragment_ViewBinding implements Unbinder {
  private MiaoShaFragment target;

  @UiThread
  public MiaoShaFragment_ViewBinding(MiaoShaFragment target, View source) {
    this.target = target;

    target.mRecyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'mRecyclerview'", RecyclerView.class);
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    target.mLoadinglayout = Utils.findRequiredViewAsType(source, R.id.loadinglayout, "field 'mLoadinglayout'", LoadingLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MiaoShaFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerview = null;
    target.mSwipeRefreshLayout = null;
    target.mLoadinglayout = null;
  }
}
