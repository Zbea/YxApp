// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AccountSecurityActivity_ViewBinding implements Unbinder {
  private AccountSecurityActivity target;

  private View view2131296801;

  private View view2131296828;

  private View view2131296825;

  @UiThread
  public AccountSecurityActivity_ViewBinding(AccountSecurityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccountSecurityActivity_ViewBinding(final AccountSecurityActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_phone_num = Utils.findRequiredViewAsType(source, R.id.tv_phone_num, "field 'tv_phone_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_phone, "method 'click'");
    view2131296828 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_password, "method 'click'");
    view2131296825 = view;
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
    AccountSecurityActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_phone_num = null;

    view2131296801.setOnClickListener(null);
    view2131296801 = null;
    view2131296828.setOnClickListener(null);
    view2131296828 = null;
    view2131296825.setOnClickListener(null);
    view2131296825 = null;
  }
}
