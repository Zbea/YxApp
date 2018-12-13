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

  private View view2131297155;

  private View view2131296832;

  private View view2131296826;

  private View view2131296581;

  private View view2131296827;

  private View view2131296814;

  private View view2131296606;

  private View view2131296605;

  private View view2131296607;

  private View view2131296602;

  private View view2131296604;

  private View view2131296617;

  private View view2131296603;

  private View view2131296640;

  private View view2131296642;

  private View view2131296641;

  private View view2131296644;

  private View view2131296574;

  private View view2131296560;

  private View view2131296585;

  private View view2131296555;

  private View view2131296556;

  @UiThread
  public MyFragment_ViewBinding(final MyFragment target, View source) {
    this.target = target;

    View view;
    target.tv_collect_num = Utils.findRequiredViewAsType(source, R.id.tv_collect_num, "field 'tv_collect_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_user_name, "field 'tv_user_name' and method 'onclick'");
    target.tv_user_name = Utils.castView(view, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
    view2131297155 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tv_my_integral = Utils.findRequiredViewAsType(source, R.id.tv_my_integral, "field 'tv_my_integral'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_user_head, "field 'rl_user_head' and method 'onclick'");
    target.rl_user_head = Utils.castView(view, R.id.rl_user_head, "field 'rl_user_head'", RoundImageView.class);
    view2131296832 = view;
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
    view2131296826 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_feedback, "method 'onclick'");
    view2131296581 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_signin, "method 'onclick'");
    view2131296827 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_order, "method 'onclick'");
    view2131296814 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_store, "method 'onclick'");
    view2131296606 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_integral, "method 'onclick'");
    view2131296605 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_wallet, "method 'onclick'");
    view2131296607 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_collect, "method 'onclick'");
    view2131296602 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_erweima, "method 'onclick'");
    view2131296604 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_qiyezizhi, "method 'onclick'");
    view2131296617 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_coupons, "method 'onclick'");
    view2131296603 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_pay, "method 'onclick'");
    view2131296640 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_send, "method 'onclick'");
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_receive, "method 'onclick'");
    view2131296641 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_youjiang_task, "method 'onclick'");
    view2131296644 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_completed, "method 'onclick'");
    view2131296574 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_after_sales, "method 'onclick'");
    view2131296560 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_have_need, "method 'onclick'");
    view2131296585 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about, "method 'onclick'");
    view2131296555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_accout_security, "method 'onclick'");
    view2131296556 = view;
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

    view2131297155.setOnClickListener(null);
    view2131297155 = null;
    view2131296832.setOnClickListener(null);
    view2131296832 = null;
    view2131296826.setOnClickListener(null);
    view2131296826 = null;
    view2131296581.setOnClickListener(null);
    view2131296581 = null;
    view2131296827.setOnClickListener(null);
    view2131296827 = null;
    view2131296814.setOnClickListener(null);
    view2131296814 = null;
    view2131296606.setOnClickListener(null);
    view2131296606 = null;
    view2131296605.setOnClickListener(null);
    view2131296605 = null;
    view2131296607.setOnClickListener(null);
    view2131296607 = null;
    view2131296602.setOnClickListener(null);
    view2131296602 = null;
    view2131296604.setOnClickListener(null);
    view2131296604 = null;
    view2131296617.setOnClickListener(null);
    view2131296617 = null;
    view2131296603.setOnClickListener(null);
    view2131296603 = null;
    view2131296640.setOnClickListener(null);
    view2131296640 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131296641.setOnClickListener(null);
    view2131296641 = null;
    view2131296644.setOnClickListener(null);
    view2131296644 = null;
    view2131296574.setOnClickListener(null);
    view2131296574 = null;
    view2131296560.setOnClickListener(null);
    view2131296560 = null;
    view2131296585.setOnClickListener(null);
    view2131296585 = null;
    view2131296555.setOnClickListener(null);
    view2131296555 = null;
    view2131296556.setOnClickListener(null);
    view2131296556 = null;
  }
}
