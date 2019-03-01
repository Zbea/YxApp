// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131296474;

  private View view2131297028;

  private View view2131297051;

  private View view2131297008;

  private View view2131297125;

  private View view2131297025;

  private View view2131297053;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_cancel, "field 'mIvCancel' and method 'onClick'");
    target.mIvCancel = Utils.castView(view, R.id.iv_cancel, "field 'mIvCancel'", ImageView.class);
    view2131296474 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvLoginType = Utils.findRequiredViewAsType(source, R.id.tv_login_type, "field 'mTvLoginType'", TextView.class);
    target.mTvLoginHintType = Utils.findRequiredViewAsType(source, R.id.tv_login_hint_type, "field 'mTvLoginHintType'", TextView.class);
    target.mEditPhoneNum = Utils.findRequiredViewAsType(source, R.id.edit_phone_num, "field 'mEditPhoneNum'", EditText.class);
    target.mIvCodeType = Utils.findRequiredViewAsType(source, R.id.iv_code_type, "field 'mIvCodeType'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'mTvGetCode' and method 'onClick'");
    target.mTvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'mTvGetCode'", TextView.class);
    view2131297028 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mEditCode = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'mEditCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_login, "field 'mTvLogin' and method 'onClick'");
    target.mTvLogin = Utils.castView(view, R.id.tv_login, "field 'mTvLogin'", TextView.class);
    view2131297051 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cut, "field 'mTvCut' and method 'onClick'");
    target.mTvCut = Utils.castView(view, R.id.tv_cut, "field 'mTvCut'", TextView.class);
    view2131297008 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_register, "field 'mTvRegister' and method 'onClick'");
    target.mTvRegister = Utils.castView(view, R.id.tv_register, "field 'mTvRegister'", TextView.class);
    view2131297125 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_find, "field 'mTvFind' and method 'onClick'");
    target.mTvFind = Utils.castView(view, R.id.tv_find, "field 'mTvFind'", TextView.class);
    view2131297025 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_login_member, "field 'mTvLoginMember' and method 'onClick'");
    target.mTvLoginMember = Utils.castView(view, R.id.tv_login_member, "field 'mTvLoginMember'", TextView.class);
    view2131297053 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvCancel = null;
    target.mTvLoginType = null;
    target.mTvLoginHintType = null;
    target.mEditPhoneNum = null;
    target.mIvCodeType = null;
    target.mTvGetCode = null;
    target.mEditCode = null;
    target.mTvLogin = null;
    target.mTvCut = null;
    target.mTvRegister = null;
    target.mTvFind = null;
    target.mTvLoginMember = null;

    view2131296474.setOnClickListener(null);
    view2131296474 = null;
    view2131297028.setOnClickListener(null);
    view2131297028 = null;
    view2131297051.setOnClickListener(null);
    view2131297051 = null;
    view2131297008.setOnClickListener(null);
    view2131297008 = null;
    view2131297125.setOnClickListener(null);
    view2131297125 = null;
    view2131297025.setOnClickListener(null);
    view2131297025 = null;
    view2131297053.setOnClickListener(null);
    view2131297053 = null;
  }
}
