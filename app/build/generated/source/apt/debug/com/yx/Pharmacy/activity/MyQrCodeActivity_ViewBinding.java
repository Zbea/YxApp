// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyQrCodeActivity_ViewBinding implements Unbinder {
  private MyQrCodeActivity target;

  private View view2131296790;

  private View view2131297110;

  @UiThread
  public MyQrCodeActivity_ViewBinding(MyQrCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyQrCodeActivity_ViewBinding(final MyQrCodeActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.iv_qrcode = Utils.findRequiredViewAsType(source, R.id.iv_qrcode, "field 'iv_qrcode'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onClick'");
    view2131296790 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_save, "method 'onClick'");
    view2131297110 = view;
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
    MyQrCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.iv_qrcode = null;

    view2131296790.setOnClickListener(null);
    view2131296790 = null;
    view2131297110.setOnClickListener(null);
    view2131297110 = null;
  }
}
