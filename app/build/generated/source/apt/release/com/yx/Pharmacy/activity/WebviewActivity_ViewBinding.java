// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebviewActivity_ViewBinding implements Unbinder {
  private WebviewActivity target;

  @UiThread
  public WebviewActivity_ViewBinding(WebviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebviewActivity_ViewBinding(WebviewActivity target, View source) {
    this.target = target;

    target.wvView = Utils.findRequiredViewAsType(source, R.id.wv_view, "field 'wvView'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WebviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.wvView = null;
  }
}
