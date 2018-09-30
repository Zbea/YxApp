// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AfterOrderDetailActivity_ViewBinding implements Unbinder {
  private AfterOrderDetailActivity target;

  private View view2131296611;

  private View view2131296577;

  private View view2131296791;

  private View view2131296525;

  @UiThread
  public AfterOrderDetailActivity_ViewBinding(AfterOrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AfterOrderDetailActivity_ViewBinding(final AfterOrderDetailActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_order_state = Utils.findRequiredViewAsType(source, R.id.tv_order_state, "field 'tv_order_state'", TextView.class);
    target.tv_order_state_desc = Utils.findRequiredViewAsType(source, R.id.tv_order_state_desc, "field 'tv_order_state_desc'", TextView.class);
    target.tv_backorderid = Utils.findRequiredViewAsType(source, R.id.tv_backorderid, "field 'tv_backorderid'", TextView.class);
    target.tv_applyfor_time = Utils.findRequiredViewAsType(source, R.id.tv_applyfor_time, "field 'tv_applyfor_time'", TextView.class);
    target.tv_refund_amount = Utils.findRequiredViewAsType(source, R.id.tv_refund_amount, "field 'tv_refund_amount'", TextView.class);
    target.tv_order_id = Utils.findRequiredViewAsType(source, R.id.tv_order_id, "field 'tv_order_id'", TextView.class);
    target.tv_order_num = Utils.findRequiredViewAsType(source, R.id.tv_order_num, "field 'tv_order_num'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.recyclerView_gift = Utils.findRequiredViewAsType(source, R.id.recyclerView_gift, "field 'recyclerView_gift'", RecyclerView.class);
    target.ll_order_list = Utils.findRequiredViewAsType(source, R.id.ll_order_list, "field 'll_order_list'", LinearLayout.class);
    target.view_gift_line = Utils.findRequiredView(source, R.id.view_gift_line, "field 'view_gift_line'");
    view = Utils.findRequiredView(source, R.id.ll_open, "field 'll_open' and method 'click'");
    target.ll_open = Utils.castView(view, R.id.ll_open, "field 'll_open'", LinearLayout.class);
    view2131296611 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_close, "field 'll_close' and method 'click'");
    target.ll_close = Utils.castView(view, R.id.ll_close, "field 'll_close'", LinearLayout.class);
    view2131296577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.rl_wuliu_info = Utils.findRequiredViewAsType(source, R.id.rl_wuliu_info, "field 'rl_wuliu_info'", RelativeLayout.class);
    target.tv_wuliu_info = Utils.findRequiredViewAsType(source, R.id.tv_wuliu_info, "field 'tv_wuliu_info'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296791 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_service, "method 'click'");
    view2131296525 = view;
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
    AfterOrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_order_state = null;
    target.tv_order_state_desc = null;
    target.tv_backorderid = null;
    target.tv_applyfor_time = null;
    target.tv_refund_amount = null;
    target.tv_order_id = null;
    target.tv_order_num = null;
    target.recyclerView = null;
    target.recyclerView_gift = null;
    target.ll_order_list = null;
    target.view_gift_line = null;
    target.ll_open = null;
    target.ll_close = null;
    target.rl_wuliu_info = null;
    target.tv_wuliu_info = null;

    view2131296611.setOnClickListener(null);
    view2131296611 = null;
    view2131296577.setOnClickListener(null);
    view2131296577 = null;
    view2131296791.setOnClickListener(null);
    view2131296791 = null;
    view2131296525.setOnClickListener(null);
    view2131296525 = null;
  }
}
