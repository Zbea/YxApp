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

  private View view2131297132;

  private View view2131296815;

  private View view2131296809;

  private View view2131296571;

  private View view2131296810;

  private View view2131296797;

  private View view2131296594;

  private View view2131296593;

  private View view2131296595;

  private View view2131296590;

  private View view2131296592;

  private View view2131296605;

  private View view2131296591;

  private View view2131296626;

  private View view2131296628;

  private View view2131296627;

  private View view2131296630;

  private View view2131296564;

  private View view2131296550;

  private View view2131296575;

  private View view2131296545;

  private View view2131296546;

  @UiThread
  public MyFragment_ViewBinding(final MyFragment target, View source) {
    this.target = target;

    View view;
    target.tv_collect_num = Utils.findRequiredViewAsType(source, R.id.tv_collect_num, "field 'tv_collect_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_user_name, "field 'tv_user_name' and method 'onclick'");
    target.tv_user_name = Utils.castView(view, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
    view2131297132 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tv_my_integral = Utils.findRequiredViewAsType(source, R.id.tv_my_integral, "field 'tv_my_integral'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_user_head, "field 'rl_user_head' and method 'onclick'");
    target.rl_user_head = Utils.castView(view, R.id.rl_user_head, "field 'rl_user_head'", RoundImageView.class);
    view2131296815 = view;
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
    view2131296809 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_feedback, "method 'onclick'");
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_signin, "method 'onclick'");
    view2131296810 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_order, "method 'onclick'");
    view2131296797 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_store, "method 'onclick'");
    view2131296594 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_integral, "method 'onclick'");
    view2131296593 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_wallet, "method 'onclick'");
    view2131296595 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_collect, "method 'onclick'");
    view2131296590 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_erweima, "method 'onclick'");
    view2131296592 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_qiyezizhi, "method 'onclick'");
    view2131296605 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_coupons, "method 'onclick'");
    view2131296591 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_pay, "method 'onclick'");
    view2131296626 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_send, "method 'onclick'");
    view2131296628 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_waitto_receive, "method 'onclick'");
    view2131296627 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_youjiang_task, "method 'onclick'");
    view2131296630 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_completed, "method 'onclick'");
    view2131296564 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_after_sales, "method 'onclick'");
    view2131296550 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_have_need, "method 'onclick'");
    view2131296575 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about, "method 'onclick'");
    view2131296545 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_accout_security, "method 'onclick'");
    view2131296546 = view;
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

    view2131297132.setOnClickListener(null);
    view2131297132 = null;
    view2131296815.setOnClickListener(null);
    view2131296815 = null;
    view2131296809.setOnClickListener(null);
    view2131296809 = null;
    view2131296571.setOnClickListener(null);
    view2131296571 = null;
    view2131296810.setOnClickListener(null);
    view2131296810 = null;
    view2131296797.setOnClickListener(null);
    view2131296797 = null;
    view2131296594.setOnClickListener(null);
    view2131296594 = null;
    view2131296593.setOnClickListener(null);
    view2131296593 = null;
    view2131296595.setOnClickListener(null);
    view2131296595 = null;
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
  }
}
