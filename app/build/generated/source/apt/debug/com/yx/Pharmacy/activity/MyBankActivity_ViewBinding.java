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

public class MyBankActivity_ViewBinding implements Unbinder {
  private MyBankActivity target;

  private View view2131296795;

  private View view2131296329;

  @UiThread
  public MyBankActivity_ViewBinding(MyBankActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyBankActivity_ViewBinding(final MyBankActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_bank = Utils.findRequiredViewAsType(source, R.id.tv_bank, "field 'tv_bank'", TextView.class);
    target.tv_card_num = Utils.findRequiredViewAsType(source, R.id.tv_card_num, "field 'tv_card_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cardView, "method 'click'");
    view2131296329 = view;
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
    MyBankActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_bank = null;
    target.tv_card_num = null;

    view2131296795.setOnClickListener(null);
    view2131296795 = null;
    view2131296329.setOnClickListener(null);
    view2131296329 = null;
  }
}
