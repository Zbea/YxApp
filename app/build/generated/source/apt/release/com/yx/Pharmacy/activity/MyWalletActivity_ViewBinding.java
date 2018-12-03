// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyWalletActivity_ViewBinding implements Unbinder {
  private MyWalletActivity target;

  private View view2131297105;

  private View view2131296783;

  private View view2131296562;

  private View view2131296555;

  private View view2131296624;

  private View view2131297096;

  @UiThread
  public MyWalletActivity_ViewBinding(MyWalletActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyWalletActivity_ViewBinding(final MyWalletActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
    target.tv_my_money = Utils.findRequiredViewAsType(source, R.id.tv_my_money, "field 'tv_my_money'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_select_ximing, "field 'tv_select_ximing' and method 'click'");
    target.tv_select_ximing = Utils.castView(view, R.id.tv_select_ximing, "field 'tv_select_ximing'", TextView.class);
    view2131297105 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.ll_error = Utils.findRequiredViewAsType(source, R.id.ll_error, "field 'll_error'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296783 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_charge_money, "method 'click'");
    view2131296562 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_bank_card, "method 'click'");
    view2131296555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_tixian, "method 'click'");
    view2131296624 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_reload, "method 'click'");
    view2131297096 = view;
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
    MyWalletActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_more = null;
    target.tv_my_money = null;
    target.tv_select_ximing = null;
    target.recyclerview = null;
    target.ll_nodata = null;
    target.ll_error = null;

    view2131297105.setOnClickListener(null);
    view2131297105 = null;
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296562.setOnClickListener(null);
    view2131296562 = null;
    view2131296555.setOnClickListener(null);
    view2131296555 = null;
    view2131296624.setOnClickListener(null);
    view2131296624 = null;
    view2131297096.setOnClickListener(null);
    view2131297096 = null;
  }
}
