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

  private View view2131296812;

  private View view2131296783;

  private View view2131297133;

  private View view2131296824;

  private View view2131296780;

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
    view = Utils.findRequiredView(source, R.id.rl_public_pay, "field 'rlPublicPay' and method 'click'");
    target.rlPublicPay = Utils.castView(view, R.id.rl_public_pay, "field 'rlPublicPay'", RelativeLayout.class);
    view2131296812 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296783 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_to_pay, "method 'click'");
    view2131297133 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_wechat_pay, "method 'click'");
    view2131296824 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_alipay, "method 'click'");
    view2131296780 = view;
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

    view2131296812.setOnClickListener(null);
    view2131296812 = null;
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131297133.setOnClickListener(null);
    view2131297133 = null;
    view2131296824.setOnClickListener(null);
    view2131296824 = null;
    view2131296780.setOnClickListener(null);
    view2131296780 = null;
  }
}
