// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePasswordActivity_ViewBinding implements Unbinder {
  private ChangePasswordActivity target;

  private View view2131297005;

  private View view2131296790;

  private View view2131296970;

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePasswordActivity_ViewBinding(final ChangePasswordActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.editOldPassword = Utils.findRequiredViewAsType(source, R.id.edit_old_password, "field 'editOldPassword'", EditText.class);
    target.editNewPassword = Utils.findRequiredViewAsType(source, R.id.edit_new_password, "field 'editNewPassword'", EditText.class);
    target.editPhoneNum = Utils.findRequiredViewAsType(source, R.id.edit_phone_num, "field 'editPhoneNum'", EditText.class);
    target.editCode = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'editCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tvGetCode' and method 'click'");
    target.tvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'tvGetCode'", TextView.class);
    view2131297005 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296790 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_commit, "method 'click'");
    view2131296970 = view;
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
    ChangePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.editOldPassword = null;
    target.editNewPassword = null;
    target.editPhoneNum = null;
    target.editCode = null;
    target.tvGetCode = null;

    view2131297005.setOnClickListener(null);
    view2131297005 = null;
    view2131296790.setOnClickListener(null);
    view2131296790 = null;
    view2131296970.setOnClickListener(null);
    view2131296970 = null;
  }
}
