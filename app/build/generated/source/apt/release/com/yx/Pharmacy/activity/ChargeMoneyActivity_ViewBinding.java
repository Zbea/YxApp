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

public class ChargeMoneyActivity_ViewBinding implements Unbinder {
  private ChargeMoneyActivity target;

<<<<<<< Updated upstream
  private View view2131296777;

  private View view2131297118;

  private View view2131296815;

  private View view2131296774;
=======
  private View view2131296784;

  private View view2131297135;

  private View view2131296826;

  private View view2131296781;
>>>>>>> Stashed changes

  @UiThread
  public ChargeMoneyActivity_ViewBinding(ChargeMoneyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChargeMoneyActivity_ViewBinding(final ChargeMoneyActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.iv_select_wechat_pay = Utils.findRequiredViewAsType(source, R.id.iv_select_wechat_pay, "field 'iv_select_wechat_pay'", ImageView.class);
    target.iv_select_alipay = Utils.findRequiredViewAsType(source, R.id.iv_select_alipay, "field 'iv_select_alipay'", ImageView.class);
    target.edit_search = Utils.findRequiredViewAsType(source, R.id.edit_search, "field 'edit_search'", EditText.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_to_chongzhi, "method 'click'");
<<<<<<< Updated upstream
    view2131297118 = view;
=======
    view2131297135 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_wechat_pay, "method 'click'");
<<<<<<< Updated upstream
    view2131296815 = view;
=======
    view2131296826 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_alipay, "method 'click'");
<<<<<<< Updated upstream
    view2131296774 = view;
=======
    view2131296781 = view;
>>>>>>> Stashed changes
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
    ChargeMoneyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.iv_select_wechat_pay = null;
    target.iv_select_alipay = null;
    target.edit_search = null;

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131297118.setOnClickListener(null);
    view2131297118 = null;
    view2131296815.setOnClickListener(null);
    view2131296815 = null;
    view2131296774.setOnClickListener(null);
    view2131296774 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131297135.setOnClickListener(null);
    view2131297135 = null;
    view2131296826.setOnClickListener(null);
    view2131296826 = null;
    view2131296781.setOnClickListener(null);
    view2131296781 = null;
>>>>>>> Stashed changes
  }
}
