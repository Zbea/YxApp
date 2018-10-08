// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.SwitchButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateOrderActivity_ViewBinding implements Unbinder {
  private CreateOrderActivity target;

  private View view2131296791;

  private View view2131296611;

  private View view2131296578;

  private View view2131296991;

  @UiThread
  public CreateOrderActivity_ViewBinding(CreateOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreateOrderActivity_ViewBinding(final CreateOrderActivity target, View source) {
    this.target = target;

    View view;
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onClick'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view2131296791 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mTvMore = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'mTvMore'", TextView.class);
    target.mIvMore = Utils.findRequiredViewAsType(source, R.id.iv_more, "field 'mIvMore'", ImageView.class);
    target.mRlMore = Utils.findRequiredViewAsType(source, R.id.rl_more, "field 'mRlMore'", RelativeLayout.class);
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'mTvName'", TextView.class);
    target.mTvMobile = Utils.findRequiredViewAsType(source, R.id.tv_mobile, "field 'mTvMobile'", TextView.class);
    target.mTvLocation = Utils.findRequiredViewAsType(source, R.id.tv_location, "field 'mTvLocation'", TextView.class);
    target.mTvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'mTvPrice'", TextView.class);
    target.mTvCouponPrice = Utils.findRequiredViewAsType(source, R.id.tv_coupon_price, "field 'mTvCouponPrice'", TextView.class);
    target.mTvYuePrice = Utils.findRequiredViewAsType(source, R.id.tv_yue_price, "field 'mTvYuePrice'", TextView.class);
    target.mSbButtonYue = Utils.findRequiredViewAsType(source, R.id.sb_button_yue, "field 'mSbButtonYue'", SwitchButton.class);
    target.mTvOrderPrice = Utils.findRequiredViewAsType(source, R.id.tv_order_price, "field 'mTvOrderPrice'", TextView.class);
    target.mTvCompanyName = Utils.findRequiredViewAsType(source, R.id.tv_company_name, "field 'mTvCompanyName'", TextView.class);
    target.mSbButtonCompany = Utils.findRequiredViewAsType(source, R.id.sb_button_company, "field 'mSbButtonCompany'", SwitchButton.class);
    target.mTvProductNum = Utils.findRequiredViewAsType(source, R.id.tv_product_num, "field 'mTvProductNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_open, "field 'mLlOpen' and method 'onClick'");
    target.mLlOpen = Utils.castView(view, R.id.ll_open, "field 'mLlOpen'", LinearLayout.class);
    view2131296611 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
    target.recyclerView_gift = Utils.findRequiredViewAsType(source, R.id.recyclerView_gift, "field 'recyclerView_gift'", RecyclerView.class);
    target.ll_order_list = Utils.findRequiredViewAsType(source, R.id.ll_order_list, "field 'll_order_list'", LinearLayout.class);
    target.view_gift_line = Utils.findRequiredView(source, R.id.view_gift_line, "field 'view_gift_line'");
    view = Utils.findRequiredView(source, R.id.ll_close, "field 'mLlClose' and method 'onClick'");
    target.mLlClose = Utils.castView(view, R.id.ll_close, "field 'mLlClose'", LinearLayout.class);
    view2131296578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvPayPrice = Utils.findRequiredViewAsType(source, R.id.tv_pay_price, "field 'mTvPayPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_create_order, "field 'mTvCreateOrder' and method 'onClick'");
    target.mTvCreateOrder = Utils.castView(view, R.id.tv_create_order, "field 'mTvCreateOrder'", TextView.class);
    view2131296991 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mEdtNote = Utils.findRequiredViewAsType(source, R.id.edt_note, "field 'mEdtNote'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvBack = null;
    target.mRlBack = null;
    target.mTvTitle = null;
    target.mTvMore = null;
    target.mIvMore = null;
    target.mRlMore = null;
    target.mTvName = null;
    target.mTvMobile = null;
    target.mTvLocation = null;
    target.mTvPrice = null;
    target.mTvCouponPrice = null;
    target.mTvYuePrice = null;
    target.mSbButtonYue = null;
    target.mTvOrderPrice = null;
    target.mTvCompanyName = null;
    target.mSbButtonCompany = null;
    target.mTvProductNum = null;
    target.mLlOpen = null;
    target.mRecyclerView = null;
    target.recyclerView_gift = null;
    target.ll_order_list = null;
    target.view_gift_line = null;
    target.mLlClose = null;
    target.mTvPayPrice = null;
    target.mTvCreateOrder = null;
    target.mEdtNote = null;

    view2131296791.setOnClickListener(null);
    view2131296791 = null;
    view2131296611.setOnClickListener(null);
    view2131296611 = null;
    view2131296578.setOnClickListener(null);
    view2131296578 = null;
    view2131296991.setOnClickListener(null);
    view2131296991 = null;
  }
}
