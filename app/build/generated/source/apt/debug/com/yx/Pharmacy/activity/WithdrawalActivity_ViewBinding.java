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

public class WithdrawalActivity_ViewBinding implements Unbinder {
  private WithdrawalActivity target;

  private View view2131296795;

  private View view2131296986;

  private View view2131297145;

  @UiThread
  public WithdrawalActivity_ViewBinding(WithdrawalActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithdrawalActivity_ViewBinding(final WithdrawalActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_bank = Utils.findRequiredViewAsType(source, R.id.tv_bank, "field 'tv_bank'", TextView.class);
    target.tv_where = Utils.findRequiredViewAsType(source, R.id.tv_where, "field 'tv_where'", TextView.class);
    target.tv_card_num = Utils.findRequiredViewAsType(source, R.id.tv_card_num, "field 'tv_card_num'", TextView.class);
    target.edit_withdrawal = Utils.findRequiredViewAsType(source, R.id.edit_withdrawal, "field 'edit_withdrawal'", EditText.class);
    target.tv_my_money = Utils.findRequiredViewAsType(source, R.id.tv_my_money, "field 'tv_my_money'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_commit, "method 'click'");
    view2131296986 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_take_all, "method 'click'");
    view2131297145 = view;
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
    WithdrawalActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_bank = null;
    target.tv_where = null;
    target.tv_card_num = null;
    target.edit_withdrawal = null;
    target.tv_my_money = null;

    view2131296795.setOnClickListener(null);
    view2131296795 = null;
    view2131296986.setOnClickListener(null);
    view2131296986 = null;
    view2131297145.setOnClickListener(null);
    view2131297145 = null;
  }
}
