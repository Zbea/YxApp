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

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296777;
=======
  private View view2131296784;
>>>>>>> Stashed changes
=======
  private View view2131296783;
>>>>>>> feature_1.0

  private View view2131296322;

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
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
=======
    view2131296783 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cardView, "method 'click'");
    view2131296322 = view;
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

<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
>>>>>>> Stashed changes
=======
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
>>>>>>> feature_1.0
    view2131296322.setOnClickListener(null);
    view2131296322 = null;
  }
}
