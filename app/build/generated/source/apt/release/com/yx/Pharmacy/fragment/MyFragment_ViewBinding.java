// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.RoundImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyFragment_ViewBinding implements Unbinder {
  private MyFragment target;

  private View view2131297128;

  private View view2131296812;

  private View view2131296807;

  private View view2131296569;

  private View view2131296808;

  private View view2131296795;

  private View view2131296592;

  private View view2131296591;

  private View view2131296593;

  private View view2131296588;

  private View view2131296590;

  private View view2131296602;

  private View view2131296589;

  private View view2131296623;

  private View view2131296625;

  private View view2131296624;

  private View view2131296627;

  private View view2131296562;

  private View view2131296548;

  private View view2131296573;

  private View view2131296543;

  private View view2131296544;

  @UiThread
  public MyFragment_ViewBinding(final MyFragment target, View source) {
    this.target = target;

    View view;
    target.tv_collect_num = Utils.findRequiredViewAsType(source, R.id.tv_collect_num, "field 'tv_collect_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_user_name, "field 'tv_user_name' and method 'onclick'");
    target.tv_user_name = Utils.castView(view, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
    view2131297128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tv_my_integral = Utils.findRequiredViewAsType(source, R.id.tv_my_integral, "field 'tv_my_integral'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_user_head, "field 'rl_user_head' and method 'onclick'");
    target.rl_user_head = Utils.castView(view, R.id.rl_user_head, "field 'rl_user_head'", RoundImageView.class);
    view2131296812 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tvPayNum = Utils.findRequiredViewAsType(source, R.id.tv_pay_num, "field 'tvPayNum'", TextView.class);
    target.tvSendNum = Utils.findRequiredViewAsType(source, R.id.tv_send_num, "field 'tvSendNum'", TextView.class);
    target.tvRecieveNum = Utils.findRequiredViewAsType(source, R.id.tv_recieve_num, "field 'tvRecieveNum'", TextView.class);
    target.tvDoneNum = Utils.findRequiredViewAsType(source, R.id.tv_done_num, "field 'tvDoneNum'", TextView.class);
    target.tvAfterNum = Utils.findRequiredViewAsType(source, R.id.tv_after_num, "field 'tvAfterNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_setting, "method 'onclick'");
    view2131296807 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_feedback, "method 'onclick'");
    view2131296569 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_signin, "method 'onclick'");
    view2131296808 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_order, "method 'onclick'");
    view2131296795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_store, "method 'onclick'");
    view2131296592 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_integral, "method 'onclick'");
    view2131296591 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_wallet, "method 'onclick'");
    view2131296593 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_collect, "method 'onclick'");
    view2131296588 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_erweima, "method 'onclick'");
    view2131296590 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_qiyezizhi, "method 'onclick'");
    view2131296602 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_coupons, "method 'onclick'");
    view2131296589 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_pay, "method 'onclick'");
    view2131296623 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_send, "method 'onclick'");
    view2131296625 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_receive, "method 'onclick'");
    view2131296624 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_youjiang_task, "method 'onclick'");
    view2131296627 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_completed, "method 'onclick'");
    view2131296562 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_after_sales, "method 'onclick'");
    view2131296548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_have_need, "method 'onclick'");
    view2131296573 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about, "method 'onclick'");
    view2131296543 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_accout_security, "method 'onclick'");
    view2131296544 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_collect_num = null;
    target.tv_user_name = null;
    target.tv_my_integral = null;
    target.rl_user_head = null;
    target.tvPayNum = null;
    target.tvSendNum = null;
    target.tvRecieveNum = null;
    target.tvDoneNum = null;
    target.tvAfterNum = null;

    view2131297128.setOnClickListener(null);
    view2131297128 = null;
    view2131296812.setOnClickListener(null);
    view2131296812 = null;
    view2131296807.setOnClickListener(null);
    view2131296807 = null;
    view2131296569.setOnClickListener(null);
    view2131296569 = null;
    view2131296808.setOnClickListener(null);
    view2131296808 = null;
    view2131296795.setOnClickListener(null);
    view2131296795 = null;
    view2131296592.setOnClickListener(null);
    view2131296592 = null;
    view2131296591.setOnClickListener(null);
    view2131296591 = null;
    view2131296593.setOnClickListener(null);
    view2131296593 = null;
    view2131296588.setOnClickListener(null);
    view2131296588 = null;
    view2131296590.setOnClickListener(null);
    view2131296590 = null;
    view2131296602.setOnClickListener(null);
    view2131296602 = null;
    view2131296589.setOnClickListener(null);
    view2131296589 = null;
    view2131296623.setOnClickListener(null);
    view2131296623 = null;
    view2131296625.setOnClickListener(null);
    view2131296625 = null;
    view2131296624.setOnClickListener(null);
    view2131296624 = null;
    view2131296627.setOnClickListener(null);
    view2131296627 = null;
    view2131296562.setOnClickListener(null);
    view2131296562 = null;
    view2131296548.setOnClickListener(null);
    view2131296548 = null;
    view2131296573.setOnClickListener(null);
    view2131296573 = null;
    view2131296543.setOnClickListener(null);
    view2131296543 = null;
    view2131296544.setOnClickListener(null);
    view2131296544 = null;
  }
}
