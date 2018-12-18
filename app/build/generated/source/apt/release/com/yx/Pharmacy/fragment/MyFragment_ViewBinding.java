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

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131297131;
=======
  private View view2131297146;
>>>>>>> feature_1.0

  private View view2131296824;

  private View view2131296818;

<<<<<<< HEAD
  private View view2131296571;
=======
  private View view2131297148;

  private View view2131296825;
>>>>>>> Stashed changes
=======
  private View view2131296573;
>>>>>>> feature_1.0

  private View view2131296819;

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296797;
=======
  private View view2131296806;
>>>>>>> feature_1.0

  private View view2131296598;

<<<<<<< HEAD
  private View view2131296593;
=======
  private View view2131296574;

  private View view2131296820;

  private View view2131296807;
>>>>>>> Stashed changes
=======
  private View view2131296597;
>>>>>>> feature_1.0

  private View view2131296599;

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296590;
=======
  private View view2131296594;
>>>>>>> feature_1.0

  private View view2131296596;

<<<<<<< HEAD
  private View view2131296605;
=======
  private View view2131296598;

  private View view2131296600;
>>>>>>> Stashed changes
=======
  private View view2131296609;
>>>>>>> feature_1.0

  private View view2131296595;

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296626;

  private View view2131296628;
=======
  private View view2131296597;

  private View view2131296610;

  private View view2131296596;
>>>>>>> Stashed changes
=======
  private View view2131296632;

  private View view2131296634;
>>>>>>> feature_1.0

  private View view2131296633;

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296630;
=======
  private View view2131296636;
>>>>>>> feature_1.0

  private View view2131296566;

  private View view2131296552;

  private View view2131296577;

<<<<<<< HEAD
  private View view2131296545;
=======
  private View view2131296635;

  private View view2131296634;

  private View view2131296637;

  private View view2131296567;

  private View view2131296553;

  private View view2131296578;
>>>>>>> Stashed changes
=======
  private View view2131296547;
>>>>>>> feature_1.0

  private View view2131296548;

  private View view2131296549;

  @UiThread
  public MyFragment_ViewBinding(final MyFragment target, View source) {
    this.target = target;

    View view;
    target.tv_collect_num = Utils.findRequiredViewAsType(source, R.id.tv_collect_num, "field 'tv_collect_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_user_name, "field 'tv_user_name' and method 'onclick'");
    target.tv_user_name = Utils.castView(view, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131297131 = view;
=======
    view2131297148 = view;
>>>>>>> Stashed changes
=======
    view2131297146 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tv_my_integral = Utils.findRequiredViewAsType(source, R.id.tv_my_integral, "field 'tv_my_integral'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_user_head, "field 'rl_user_head' and method 'onclick'");
    target.rl_user_head = Utils.castView(view, R.id.rl_user_head, "field 'rl_user_head'", RoundImageView.class);
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296814 = view;
=======
    view2131296825 = view;
>>>>>>> Stashed changes
=======
    view2131296824 = view;
>>>>>>> feature_1.0
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
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296809 = view;
=======
    view2131296819 = view;
>>>>>>> Stashed changes
=======
    view2131296818 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_feedback, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296571 = view;
=======
    view2131296574 = view;
>>>>>>> Stashed changes
=======
    view2131296573 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_signin, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296810 = view;
=======
    view2131296820 = view;
>>>>>>> Stashed changes
=======
    view2131296819 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_order, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296797 = view;
=======
    view2131296807 = view;
>>>>>>> Stashed changes
=======
    view2131296806 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_store, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296594 = view;
=======
    view2131296599 = view;
>>>>>>> Stashed changes
=======
    view2131296598 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_integral, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296593 = view;
=======
    view2131296598 = view;
>>>>>>> Stashed changes
=======
    view2131296597 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_wallet, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296595 = view;
=======
    view2131296600 = view;
>>>>>>> Stashed changes
=======
    view2131296599 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_collect, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296590 = view;
=======
    view2131296595 = view;
>>>>>>> Stashed changes
=======
    view2131296594 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_erweima, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296592 = view;
=======
    view2131296597 = view;
>>>>>>> Stashed changes
=======
    view2131296596 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_qiyezizhi, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296605 = view;
=======
    view2131296610 = view;
>>>>>>> Stashed changes
=======
    view2131296609 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_coupons, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296591 = view;
=======
    view2131296596 = view;
>>>>>>> Stashed changes
=======
    view2131296595 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_pay, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296626 = view;
=======
    view2131296633 = view;
>>>>>>> Stashed changes
=======
    view2131296632 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_send, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296628 = view;
=======
    view2131296635 = view;
>>>>>>> Stashed changes
=======
    view2131296634 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_receive, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296627 = view;
=======
    view2131296634 = view;
>>>>>>> Stashed changes
=======
    view2131296633 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_youjiang_task, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296630 = view;
=======
    view2131296637 = view;
>>>>>>> Stashed changes
=======
    view2131296636 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_completed, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296564 = view;
=======
    view2131296567 = view;
>>>>>>> Stashed changes
=======
    view2131296566 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_after_sales, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296550 = view;
=======
    view2131296553 = view;
>>>>>>> Stashed changes
=======
    view2131296552 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_have_need, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296575 = view;
=======
    view2131296578 = view;
>>>>>>> Stashed changes
=======
    view2131296577 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296545 = view;
=======
    view2131296548 = view;
>>>>>>> Stashed changes
=======
    view2131296547 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_accout_security, "method 'onclick'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296546 = view;
=======
    view2131296549 = view;
>>>>>>> Stashed changes
=======
    view2131296548 = view;
>>>>>>> feature_1.0
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

<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131297131.setOnClickListener(null);
    view2131297131 = null;
    view2131296814.setOnClickListener(null);
    view2131296814 = null;
    view2131296809.setOnClickListener(null);
    view2131296809 = null;
    view2131296571.setOnClickListener(null);
    view2131296571 = null;
    view2131296810.setOnClickListener(null);
    view2131296810 = null;
    view2131296797.setOnClickListener(null);
    view2131296797 = null;
=======
    view2131297146.setOnClickListener(null);
    view2131297146 = null;
    view2131296824.setOnClickListener(null);
    view2131296824 = null;
    view2131296818.setOnClickListener(null);
    view2131296818 = null;
    view2131296573.setOnClickListener(null);
    view2131296573 = null;
    view2131296819.setOnClickListener(null);
    view2131296819 = null;
    view2131296806.setOnClickListener(null);
    view2131296806 = null;
    view2131296598.setOnClickListener(null);
    view2131296598 = null;
    view2131296597.setOnClickListener(null);
    view2131296597 = null;
    view2131296599.setOnClickListener(null);
    view2131296599 = null;
>>>>>>> feature_1.0
    view2131296594.setOnClickListener(null);
    view2131296594 = null;
    view2131296596.setOnClickListener(null);
    view2131296596 = null;
    view2131296609.setOnClickListener(null);
    view2131296609 = null;
    view2131296595.setOnClickListener(null);
    view2131296595 = null;
<<<<<<< HEAD
    view2131296590.setOnClickListener(null);
    view2131296590 = null;
    view2131296592.setOnClickListener(null);
    view2131296592 = null;
    view2131296605.setOnClickListener(null);
    view2131296605 = null;
    view2131296591.setOnClickListener(null);
    view2131296591 = null;
    view2131296626.setOnClickListener(null);
    view2131296626 = null;
    view2131296628.setOnClickListener(null);
    view2131296628 = null;
    view2131296627.setOnClickListener(null);
    view2131296627 = null;
    view2131296630.setOnClickListener(null);
    view2131296630 = null;
    view2131296564.setOnClickListener(null);
    view2131296564 = null;
    view2131296550.setOnClickListener(null);
    view2131296550 = null;
    view2131296575.setOnClickListener(null);
    view2131296575 = null;
    view2131296545.setOnClickListener(null);
    view2131296545 = null;
    view2131296546.setOnClickListener(null);
    view2131296546 = null;
=======
    view2131297148.setOnClickListener(null);
    view2131297148 = null;
    view2131296825.setOnClickListener(null);
    view2131296825 = null;
    view2131296819.setOnClickListener(null);
    view2131296819 = null;
    view2131296574.setOnClickListener(null);
    view2131296574 = null;
    view2131296820.setOnClickListener(null);
    view2131296820 = null;
    view2131296807.setOnClickListener(null);
    view2131296807 = null;
    view2131296599.setOnClickListener(null);
    view2131296599 = null;
    view2131296598.setOnClickListener(null);
    view2131296598 = null;
    view2131296600.setOnClickListener(null);
    view2131296600 = null;
    view2131296595.setOnClickListener(null);
    view2131296595 = null;
    view2131296597.setOnClickListener(null);
    view2131296597 = null;
    view2131296610.setOnClickListener(null);
    view2131296610 = null;
    view2131296596.setOnClickListener(null);
    view2131296596 = null;
    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296635.setOnClickListener(null);
    view2131296635 = null;
    view2131296634.setOnClickListener(null);
    view2131296634 = null;
    view2131296637.setOnClickListener(null);
    view2131296637 = null;
    view2131296567.setOnClickListener(null);
    view2131296567 = null;
    view2131296553.setOnClickListener(null);
    view2131296553 = null;
    view2131296578.setOnClickListener(null);
    view2131296578 = null;
    view2131296548.setOnClickListener(null);
    view2131296548 = null;
    view2131296549.setOnClickListener(null);
    view2131296549 = null;
>>>>>>> Stashed changes
=======
    view2131296632.setOnClickListener(null);
    view2131296632 = null;
    view2131296634.setOnClickListener(null);
    view2131296634 = null;
    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296636.setOnClickListener(null);
    view2131296636 = null;
    view2131296566.setOnClickListener(null);
    view2131296566 = null;
    view2131296552.setOnClickListener(null);
    view2131296552 = null;
    view2131296577.setOnClickListener(null);
    view2131296577 = null;
    view2131296547.setOnClickListener(null);
    view2131296547 = null;
    view2131296548.setOnClickListener(null);
    view2131296548 = null;
>>>>>>> feature_1.0
  }
}
