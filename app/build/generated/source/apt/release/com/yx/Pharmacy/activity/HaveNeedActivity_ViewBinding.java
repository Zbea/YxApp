// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HaveNeedActivity_ViewBinding implements Unbinder {
  private HaveNeedActivity target;

  private View view2131296774;

  private View view2131296952;

  private View view2131296806;

  @UiThread
  public HaveNeedActivity_ViewBinding(HaveNeedActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HaveNeedActivity_ViewBinding(final HaveNeedActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.edit_product_name = Utils.findRequiredViewAsType(source, R.id.edit_product_name, "field 'edit_product_name'", EditText.class);
    target.edit_need_num = Utils.findRequiredViewAsType(source, R.id.edit_need_num, "field 'edit_need_num'", EditText.class);
    target.edit_product_type = Utils.findRequiredViewAsType(source, R.id.edit_product_type, "field 'edit_product_type'", TextView.class);
    target.edit_need_note = Utils.findRequiredViewAsType(source, R.id.edit_need_note, "field 'edit_need_note'", EditText.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296774 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_commit, "method 'click'");
    view2131296952 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_select_product_type, "method 'click'");
    view2131296806 = view;
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
    HaveNeedActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.edit_product_name = null;
    target.edit_need_num = null;
    target.edit_product_type = null;
    target.edit_need_note = null;

    view2131296774.setOnClickListener(null);
    view2131296774 = null;
    view2131296952.setOnClickListener(null);
    view2131296952 = null;
    view2131296806.setOnClickListener(null);
    view2131296806 = null;
  }
}
