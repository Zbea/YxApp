// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import org.apache.cordova.engine.SystemWebView;

public class HHActivity_ViewBinding implements Unbinder {
  private HHActivity target;

  private View view2131296778;

  private View view2131296764;

  @UiThread
  public HHActivity_ViewBinding(HHActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HHActivity_ViewBinding(final HHActivity target, View source) {
    this.target = target;

    View view;
    target.mWebview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'mWebview'", SystemWebView.class);
    target.loadlayout = Utils.findRequiredViewAsType(source, R.id.loadlayout, "field 'loadlayout'", LoadingLayout.class);
    target.include_title = Utils.findRequiredViewAsType(source, R.id.include_title, "field 'include_title'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_h5_back, "field 'rl_h5_back' and method 'onViewClicked'");
    target.rl_h5_back = Utils.castView(view, R.id.rl_h5_back, "field 'rl_h5_back'", RelativeLayout.class);
    view2131296778 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_h5_title = Utils.findRequiredViewAsType(source, R.id.tv_h5_title, "field 'tv_h5_title'", TextView.class);
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onViewClicked'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view2131296764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvMore = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'mTvMore'", TextView.class);
    target.mIvMore = Utils.findRequiredViewAsType(source, R.id.iv_more, "field 'mIvMore'", ImageView.class);
    target.mRlMore = Utils.findRequiredViewAsType(source, R.id.rl_more, "field 'mRlMore'", RelativeLayout.class);
    target.rl_title = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rl_title'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HHActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebview = null;
    target.loadlayout = null;
    target.include_title = null;
    target.rl_h5_back = null;
    target.tv_title = null;
    target.tv_h5_title = null;
    target.mIvBack = null;
    target.mRlBack = null;
    target.mTvMore = null;
    target.mIvMore = null;
    target.mRlMore = null;
    target.rl_title = null;

    view2131296778.setOnClickListener(null);
    view2131296778 = null;
    view2131296764.setOnClickListener(null);
    view2131296764 = null;
  }
}
