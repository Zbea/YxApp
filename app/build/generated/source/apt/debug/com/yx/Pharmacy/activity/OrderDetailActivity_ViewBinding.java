// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
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

public class OrderDetailActivity_ViewBinding implements Unbinder {
  private OrderDetailActivity target;

  private View view2131296992;

  private View view2131296810;

  private View view2131296801;

  private View view2131296612;

  private View view2131296573;

  private View view2131296791;

  private View view2131297106;

  @UiThread
  public OrderDetailActivity_ViewBinding(OrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderDetailActivity_ViewBinding(final OrderDetailActivity target, View source) {
    this.target = target;

    View view;
    target.ll_error = Utils.findRequiredViewAsType(source, R.id.ll_error, "field 'll_error'", LinearLayout.class);
    target.nestedScrollView = Utils.findRequiredViewAsType(source, R.id.nestedScrollView, "field 'nestedScrollView'", NestedScrollView.class);
    view = Utils.findRequiredView(source, R.id.tv_detail_todo, "field 'tv_detail_todo' and method 'click'");
    target.tv_detail_todo = Utils.castView(view, R.id.tv_detail_todo, "field 'tv_detail_todo'", TextView.class);
    view2131296992 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_invoice, "field 'rlInvoice' and method 'click'");
    target.rlInvoice = Utils.castView(view, R.id.rl_invoice, "field 'rlInvoice'", RelativeLayout.class);
    view2131296810 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_coupon, "field 'rlCoupon' and method 'click'");
    target.rlCoupon = Utils.castView(view, R.id.rl_coupon, "field 'rlCoupon'", RelativeLayout.class);
    view2131296801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_order_state = Utils.findRequiredViewAsType(source, R.id.tv_order_state, "field 'tv_order_state'", TextView.class);
    target.tv_order_state_desc = Utils.findRequiredViewAsType(source, R.id.tv_order_state_desc, "field 'tv_order_state_desc'", TextView.class);
    target.tv_user_info = Utils.findRequiredViewAsType(source, R.id.tv_user_info, "field 'tv_user_info'", TextView.class);
    target.tv_user_address = Utils.findRequiredViewAsType(source, R.id.tv_user_address, "field 'tv_user_address'", TextView.class);
    target.ll_note = Utils.findRequiredViewAsType(source, R.id.ll_note, "field 'll_note'", LinearLayout.class);
    target.tv_note = Utils.findRequiredViewAsType(source, R.id.tv_note, "field 'tv_note'", TextView.class);
    target.tv_price = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tv_price'", TextView.class);
    target.tv_discount = Utils.findRequiredViewAsType(source, R.id.tv_discount, "field 'tv_discount'", TextView.class);
    target.tv_dikou = Utils.findRequiredViewAsType(source, R.id.tv_dikou, "field 'tv_dikou'", TextView.class);
    target.tv_amount = Utils.findRequiredViewAsType(source, R.id.tv_amount, "field 'tv_amount'", TextView.class);
    target.tv_storename = Utils.findRequiredViewAsType(source, R.id.tv_storename, "field 'tv_storename'", TextView.class);
    target.tv_order_time = Utils.findRequiredViewAsType(source, R.id.tv_order_time, "field 'tv_order_time'", TextView.class);
    target.tv_price_payed = Utils.findRequiredViewAsType(source, R.id.tv_price_payed, "field 'tv_price_payed'", TextView.class);
    target.rl_pay_time = Utils.findRequiredViewAsType(source, R.id.rl_pay_time, "field 'rl_pay_time'", RelativeLayout.class);
    target.tv_pay_time = Utils.findRequiredViewAsType(source, R.id.tv_pay_time, "field 'tv_pay_time'", TextView.class);
    target.tv_order_id = Utils.findRequiredViewAsType(source, R.id.tv_order_id, "field 'tv_order_id'", TextView.class);
    target.tv_product_num = Utils.findRequiredViewAsType(source, R.id.tv_product_num, "field 'tv_product_num'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.recyclerView_gift = Utils.findRequiredViewAsType(source, R.id.recyclerView_gift, "field 'recyclerView_gift'", RecyclerView.class);
    target.ll_order_list = Utils.findRequiredViewAsType(source, R.id.ll_order_list, "field 'll_order_list'", LinearLayout.class);
    target.view_gift_line = Utils.findRequiredView(source, R.id.view_gift_line, "field 'view_gift_line'");
    view = Utils.findRequiredView(source, R.id.ll_open, "field 'll_open' and method 'click'");
    target.ll_open = Utils.castView(view, R.id.ll_open, "field 'll_open'", LinearLayout.class);
    view2131296612 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_close, "field 'll_close' and method 'click'");
    target.ll_close = Utils.castView(view, R.id.ll_close, "field 'll_close'", LinearLayout.class);
    view2131296573 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.rl_paytype = Utils.findRequiredViewAsType(source, R.id.rl_paytype, "field 'rl_paytype'", RelativeLayout.class);
    target.tv_paytype = Utils.findRequiredViewAsType(source, R.id.tv_paytype, "field 'tv_paytype'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296791 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_reload, "method 'click'");
    view2131297106 = view;
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
    OrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ll_error = null;
    target.nestedScrollView = null;
    target.tv_detail_todo = null;
    target.rlInvoice = null;
    target.rlCoupon = null;
    target.tv_title = null;
    target.tv_order_state = null;
    target.tv_order_state_desc = null;
    target.tv_user_info = null;
    target.tv_user_address = null;
    target.ll_note = null;
    target.tv_note = null;
    target.tv_price = null;
    target.tv_discount = null;
    target.tv_dikou = null;
    target.tv_amount = null;
    target.tv_storename = null;
    target.tv_order_time = null;
    target.tv_price_payed = null;
    target.rl_pay_time = null;
    target.tv_pay_time = null;
    target.tv_order_id = null;
    target.tv_product_num = null;
    target.recyclerView = null;
    target.recyclerView_gift = null;
    target.ll_order_list = null;
    target.view_gift_line = null;
    target.ll_open = null;
    target.ll_close = null;
    target.rl_paytype = null;
    target.tv_paytype = null;

    view2131296992.setOnClickListener(null);
    view2131296992 = null;
    view2131296810.setOnClickListener(null);
    view2131296810 = null;
    view2131296801.setOnClickListener(null);
    view2131296801 = null;
    view2131296612.setOnClickListener(null);
    view2131296612 = null;
    view2131296573.setOnClickListener(null);
    view2131296573 = null;
    view2131296791.setOnClickListener(null);
    view2131296791 = null;
    view2131297106.setOnClickListener(null);
    view2131297106 = null;
  }
}
