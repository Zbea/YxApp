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

<<<<<<< Updated upstream
  private View view2131296777;

  private View view2131296954;

  private View view2131296808;
=======
  private View view2131296784;

  private View view2131296966;

  private View view2131296818;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_commit, "method 'click'");
<<<<<<< Updated upstream
    view2131296954 = view;
=======
    view2131296966 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_select_product_type, "method 'click'");
<<<<<<< Updated upstream
    view2131296808 = view;
=======
    view2131296818 = view;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296954.setOnClickListener(null);
    view2131296954 = null;
    view2131296808.setOnClickListener(null);
    view2131296808 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296966.setOnClickListener(null);
    view2131296966 = null;
    view2131296818.setOnClickListener(null);
    view2131296818 = null;
>>>>>>> Stashed changes
  }
}
