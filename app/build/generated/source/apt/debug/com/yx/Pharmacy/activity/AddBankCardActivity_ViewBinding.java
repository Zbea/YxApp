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

public class AddBankCardActivity_ViewBinding implements Unbinder {
  private AddBankCardActivity target;

  private View view2131297008;

  private View view2131296977;

  private View view2131296791;

  @UiThread
  public AddBankCardActivity_ViewBinding(AddBankCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddBankCardActivity_ViewBinding(final AddBankCardActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.edit_phone = Utils.findRequiredViewAsType(source, R.id.edit_phone, "field 'edit_phone'", EditText.class);
    target.edit_name = Utils.findRequiredViewAsType(source, R.id.edit_name, "field 'edit_name'", EditText.class);
    target.edit_card_code = Utils.findRequiredViewAsType(source, R.id.edit_card_code, "field 'edit_card_code'", EditText.class);
    target.tv_kaihuhang = Utils.findRequiredViewAsType(source, R.id.tv_kaihuhang, "field 'tv_kaihuhang'", TextView.class);
    target.edit_wangdian = Utils.findRequiredViewAsType(source, R.id.edit_wangdian, "field 'edit_wangdian'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tv_get_code' and method 'click'");
    target.tv_get_code = Utils.castView(view, R.id.tv_get_code, "field 'tv_get_code'", TextView.class);
    view2131297008 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.edit_code = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'edit_code'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_commit, "field 'tv_commit' and method 'click'");
    target.tv_commit = Utils.castView(view, R.id.tv_commit, "field 'tv_commit'", TextView.class);
    view2131296977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296791 = view;
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
    AddBankCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.edit_phone = null;
    target.edit_name = null;
    target.edit_card_code = null;
    target.tv_kaihuhang = null;
    target.edit_wangdian = null;
    target.tv_get_code = null;
    target.edit_code = null;
    target.tv_commit = null;

    view2131297008.setOnClickListener(null);
    view2131297008 = null;
    view2131296977.setOnClickListener(null);
    view2131296977 = null;
    view2131296791.setOnClickListener(null);
    view2131296791 = null;
  }
}
