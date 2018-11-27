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

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131297022;

  private View view2131296988;

  private View view2131297083;

  private View view2131296777;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_more, "field 'mTvMore' and method 'onClick'");
    target.mTvMore = Utils.castView(view, R.id.tv_more, "field 'mTvMore'", TextView.class);
    view2131297022 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mEditPhoneNum = Utils.findRequiredViewAsType(source, R.id.edit_phone_num, "field 'mEditPhoneNum'", EditText.class);
    target.mEditPassword = Utils.findRequiredViewAsType(source, R.id.edit_password, "field 'mEditPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'mTvGetCode' and method 'onClick'");
    target.mTvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'mTvGetCode'", TextView.class);
    view2131296988 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mEditCode = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'mEditCode'", EditText.class);
    target.mEditTuijianma = Utils.findRequiredViewAsType(source, R.id.edit_tuijianma, "field 'mEditTuijianma'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_register, "field 'mTvRegister' and method 'onClick'");
    target.mTvRegister = Utils.castView(view, R.id.tv_register, "field 'mTvRegister'", TextView.class);
    view2131297083 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onClick'");
    view2131296777 = view;
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
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvTitle = null;
    target.mTvMore = null;
    target.mEditPhoneNum = null;
    target.mEditPassword = null;
    target.mTvGetCode = null;
    target.mEditCode = null;
    target.mEditTuijianma = null;
    target.mTvRegister = null;

    view2131297022.setOnClickListener(null);
    view2131297022 = null;
    view2131296988.setOnClickListener(null);
    view2131296988 = null;
    view2131297083.setOnClickListener(null);
    view2131297083 = null;
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
  }
}
