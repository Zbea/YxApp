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

  private View view2131296462;

  private View view2131296972;

  private View view2131296993;

  private View view2131296956;

  private View view2131297063;

  private View view2131296970;

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
    view2131296462 = view;
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
    view2131296972 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mEditCode = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'mEditCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_login, "field 'mTvLogin' and method 'onClick'");
    target.mTvLogin = Utils.castView(view, R.id.tv_login, "field 'mTvLogin'", TextView.class);
    view2131296993 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cut, "field 'mTvCut' and method 'onClick'");
    target.mTvCut = Utils.castView(view, R.id.tv_cut, "field 'mTvCut'", TextView.class);
    view2131296956 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_register, "field 'mTvRegister' and method 'onClick'");
    target.mTvRegister = Utils.castView(view, R.id.tv_register, "field 'mTvRegister'", TextView.class);
    view2131297063 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_find, "field 'mTvFind' and method 'onClick'");
    target.mTvFind = Utils.castView(view, R.id.tv_find, "field 'mTvFind'", TextView.class);
    view2131296970 = view;
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

    view2131296462.setOnClickListener(null);
    view2131296462 = null;
    view2131296972.setOnClickListener(null);
    view2131296972 = null;
    view2131296993.setOnClickListener(null);
    view2131296993 = null;
    view2131296956.setOnClickListener(null);
    view2131296956 = null;
    view2131297063.setOnClickListener(null);
    view2131297063 = null;
    view2131296970.setOnClickListener(null);
    view2131296970 = null;
  }
}
