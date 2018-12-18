// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayActivity_ViewBinding implements Unbinder {
  private PayActivity target;

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296777;
=======
  private View view2131296813;
>>>>>>> feature_1.0

  private View view2131296783;

  private View view2131297134;

  private View view2131296825;

<<<<<<< HEAD
  private View view2131296804;
=======
  private View view2131296814;

  private View view2131296784;

  private View view2131297136;

  private View view2131296826;

  private View view2131296781;
>>>>>>> Stashed changes
=======
  private View view2131296780;
>>>>>>> feature_1.0

  @UiThread
  public PayActivity_ViewBinding(PayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PayActivity_ViewBinding(final PayActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.iv_select_wechat_pay = Utils.findRequiredViewAsType(source, R.id.iv_select_wechat_pay, "field 'iv_select_wechat_pay'", ImageView.class);
    target.iv_select_alipay = Utils.findRequiredViewAsType(source, R.id.iv_select_alipay, "field 'iv_select_alipay'", ImageView.class);
    target.iv_select_public_pay = Utils.findRequiredViewAsType(source, R.id.iv_select_public_pay, "field 'iv_select_public_pay'", ImageView.class);
<<<<<<< HEAD
<<<<<<< Updated upstream
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296777 = view;
=======
    view = Utils.findRequiredView(source, R.id.rl_public_pay, "field 'rlPublicPay' and method 'click'");
    target.rlPublicPay = Utils.castView(view, R.id.rl_public_pay, "field 'rlPublicPay'", RelativeLayout.class);
    view2131296814 = view;
>>>>>>> Stashed changes
=======
    view = Utils.findRequiredView(source, R.id.rl_public_pay, "field 'rlPublicPay' and method 'click'");
    target.rlPublicPay = Utils.castView(view, R.id.rl_public_pay, "field 'rlPublicPay'", RelativeLayout.class);
    view2131296813 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
<<<<<<< HEAD
<<<<<<< Updated upstream
    view = Utils.findRequiredView(source, R.id.tv_to_pay, "method 'click'");
    view2131297119 = view;
=======
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296784 = view;
>>>>>>> Stashed changes
=======
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296783 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
<<<<<<< HEAD
<<<<<<< Updated upstream
    view = Utils.findRequiredView(source, R.id.rl_wechat_pay, "method 'click'");
    view2131296815 = view;
=======
    view = Utils.findRequiredView(source, R.id.tv_to_pay, "method 'click'");
    view2131297136 = view;
>>>>>>> Stashed changes
=======
    view = Utils.findRequiredView(source, R.id.tv_to_pay, "method 'click'");
    view2131297134 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
<<<<<<< HEAD
<<<<<<< Updated upstream
    view = Utils.findRequiredView(source, R.id.rl_alipay, "method 'click'");
    view2131296774 = view;
=======
    view = Utils.findRequiredView(source, R.id.rl_wechat_pay, "method 'click'");
    view2131296826 = view;
>>>>>>> Stashed changes
=======
    view = Utils.findRequiredView(source, R.id.rl_wechat_pay, "method 'click'");
    view2131296825 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
<<<<<<< HEAD
<<<<<<< Updated upstream
    view = Utils.findRequiredView(source, R.id.rl_public_pay, "method 'click'");
    view2131296804 = view;
=======
    view = Utils.findRequiredView(source, R.id.rl_alipay, "method 'click'");
    view2131296781 = view;
>>>>>>> Stashed changes
=======
    view = Utils.findRequiredView(source, R.id.rl_alipay, "method 'click'");
    view2131296780 = view;
>>>>>>> feature_1.0
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
    PayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.iv_select_wechat_pay = null;
    target.iv_select_alipay = null;
    target.iv_select_public_pay = null;
    target.rlPublicPay = null;

<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131297119.setOnClickListener(null);
    view2131297119 = null;
    view2131296815.setOnClickListener(null);
    view2131296815 = null;
    view2131296774.setOnClickListener(null);
    view2131296774 = null;
    view2131296804.setOnClickListener(null);
    view2131296804 = null;
=======
    view2131296814.setOnClickListener(null);
    view2131296814 = null;
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131297136.setOnClickListener(null);
    view2131297136 = null;
    view2131296826.setOnClickListener(null);
    view2131296826 = null;
    view2131296781.setOnClickListener(null);
    view2131296781 = null;
>>>>>>> Stashed changes
=======
    view2131296813.setOnClickListener(null);
    view2131296813 = null;
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131297134.setOnClickListener(null);
    view2131297134 = null;
    view2131296825.setOnClickListener(null);
    view2131296825 = null;
    view2131296780.setOnClickListener(null);
    view2131296780 = null;
>>>>>>> feature_1.0
  }
}
