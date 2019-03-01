// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AskForAfterSaleActivity_ViewBinding implements Unbinder {
  private AskForAfterSaleActivity target;

  private View view2131296801;

  private View view2131296568;

  private View view2131296992;

  private View view2131296626;

  @UiThread
  public AskForAfterSaleActivity_ViewBinding(AskForAfterSaleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AskForAfterSaleActivity_ViewBinding(final AskForAfterSaleActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
    target.tv_num = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tv_num'", TextView.class);
    target.iv_all_select = Utils.findRequiredViewAsType(source, R.id.iv_all_select, "field 'iv_all_select'", ImageView.class);
    target.tv_back_price = Utils.findRequiredViewAsType(source, R.id.tv_back_price, "field 'tv_back_price'", TextView.class);
    target.edittext = Utils.findRequiredViewAsType(source, R.id.edittext, "field 'edittext'", EditText.class);
    target.tv_reason = Utils.findRequiredViewAsType(source, R.id.tv_reason, "field 'tv_reason'", TextView.class);
    target.rgSaleAfterType = Utils.findRequiredViewAsType(source, R.id.rg_sale_after_type, "field 'rgSaleAfterType'", RadioGroup.class);
    target.tvCanWriteNum = Utils.findRequiredViewAsType(source, R.id.tv_can_write_num, "field 'tvCanWriteNum'", TextView.class);
    target.etLogisticsName = Utils.findRequiredViewAsType(source, R.id.et_logistics_name, "field 'etLogisticsName'", EditText.class);
    target.etLogisticsNumber = Utils.findRequiredViewAsType(source, R.id.et_logistics_number, "field 'etLogisticsNumber'", EditText.class);
    target.tvDiscount = Utils.findRequiredViewAsType(source, R.id.tv_discount, "field 'tvDiscount'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_all_select, "method 'click'");
    view2131296568 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_commit, "method 'click'");
    view2131296992 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_reason, "method 'click'");
    view2131296626 = view;
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
    AskForAfterSaleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_more = null;
    target.tv_num = null;
    target.iv_all_select = null;
    target.tv_back_price = null;
    target.edittext = null;
    target.tv_reason = null;
    target.rgSaleAfterType = null;
    target.tvCanWriteNum = null;
    target.etLogisticsName = null;
    target.etLogisticsNumber = null;
    target.tvDiscount = null;
    target.recyclerView = null;

    view2131296801.setOnClickListener(null);
    view2131296801 = null;
    view2131296568.setOnClickListener(null);
    view2131296568 = null;
    view2131296992.setOnClickListener(null);
    view2131296992 = null;
    view2131296626.setOnClickListener(null);
    view2131296626 = null;
  }
}
