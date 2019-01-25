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

  private View view2131297172;

  private View view2131296838;

  private View view2131296832;

  private View view2131296586;

  private View view2131296833;

  private View view2131296820;

  private View view2131296612;

  private View view2131296611;

  private View view2131296613;

  private View view2131296608;

  private View view2131296610;

  private View view2131296623;

  private View view2131296609;

  private View view2131296646;

  private View view2131296648;

  private View view2131296647;

  private View view2131296650;

  private View view2131296579;

  private View view2131296565;

  private View view2131296590;

  private View view2131296560;

  private View view2131296561;

  @UiThread
  public MyFragment_ViewBinding(final MyFragment target, View source) {
    this.target = target;

    View view;
    target.tv_collect_num = Utils.findRequiredViewAsType(source, R.id.tv_collect_num, "field 'tv_collect_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_user_name, "field 'tv_user_name' and method 'onclick'");
    target.tv_user_name = Utils.castView(view, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
    view2131297172 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tv_my_integral = Utils.findRequiredViewAsType(source, R.id.tv_my_integral, "field 'tv_my_integral'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_user_head, "field 'rl_user_head' and method 'onclick'");
    target.rl_user_head = Utils.castView(view, R.id.rl_user_head, "field 'rl_user_head'", RoundImageView.class);
    view2131296838 = view;
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
    target.tvMyMoney = Utils.findRequiredViewAsType(source, R.id.tv_my_money, "field 'tvMyMoney'", TextView.class);
    target.tvMyCoupon = Utils.findRequiredViewAsType(source, R.id.tv_my_coupon, "field 'tvMyCoupon'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_setting, "method 'onclick'");
    view2131296832 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_feedback, "method 'onclick'");
    view2131296586 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_signin, "method 'onclick'");
    view2131296833 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_order, "method 'onclick'");
    view2131296820 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_store, "method 'onclick'");
    view2131296612 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_integral, "method 'onclick'");
    view2131296611 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_wallet, "method 'onclick'");
    view2131296613 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_collect, "method 'onclick'");
    view2131296608 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_erweima, "method 'onclick'");
    view2131296610 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_qiyezizhi, "method 'onclick'");
    view2131296623 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_coupons, "method 'onclick'");
    view2131296609 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_pay, "method 'onclick'");
    view2131296646 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_send, "method 'onclick'");
    view2131296648 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_receive, "method 'onclick'");
    view2131296647 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_youjiang_task, "method 'onclick'");
    view2131296650 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_completed, "method 'onclick'");
    view2131296579 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_after_sales, "method 'onclick'");
    view2131296565 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_have_need, "method 'onclick'");
    view2131296590 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about, "method 'onclick'");
    view2131296560 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_accout_security, "method 'onclick'");
    view2131296561 = view;
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
    target.tvMyMoney = null;
    target.tvMyCoupon = null;

    view2131297172.setOnClickListener(null);
    view2131297172 = null;
    view2131296838.setOnClickListener(null);
    view2131296838 = null;
    view2131296832.setOnClickListener(null);
    view2131296832 = null;
    view2131296586.setOnClickListener(null);
    view2131296586 = null;
    view2131296833.setOnClickListener(null);
    view2131296833 = null;
    view2131296820.setOnClickListener(null);
    view2131296820 = null;
    view2131296612.setOnClickListener(null);
    view2131296612 = null;
    view2131296611.setOnClickListener(null);
    view2131296611 = null;
    view2131296613.setOnClickListener(null);
    view2131296613 = null;
    view2131296608.setOnClickListener(null);
    view2131296608 = null;
    view2131296610.setOnClickListener(null);
    view2131296610 = null;
    view2131296623.setOnClickListener(null);
    view2131296623 = null;
    view2131296609.setOnClickListener(null);
    view2131296609 = null;
    view2131296646.setOnClickListener(null);
    view2131296646 = null;
    view2131296648.setOnClickListener(null);
    view2131296648 = null;
    view2131296647.setOnClickListener(null);
    view2131296647 = null;
    view2131296650.setOnClickListener(null);
    view2131296650 = null;
    view2131296579.setOnClickListener(null);
    view2131296579 = null;
    view2131296565.setOnClickListener(null);
    view2131296565 = null;
    view2131296590.setOnClickListener(null);
    view2131296590 = null;
    view2131296560.setOnClickListener(null);
    view2131296560 = null;
    view2131296561.setOnClickListener(null);
    view2131296561 = null;
  }
}
