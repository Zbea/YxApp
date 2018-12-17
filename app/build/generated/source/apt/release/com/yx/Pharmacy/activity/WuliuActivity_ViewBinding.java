// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WuliuActivity_ViewBinding implements Unbinder {
  private WuliuActivity target;

<<<<<<< Updated upstream
  private View view2131296777;
=======
  private View view2131296784;
>>>>>>> Stashed changes

  @UiThread
  public WuliuActivity_ViewBinding(WuliuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WuliuActivity_ViewBinding(final WuliuActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.recyclerView_wuliu = Utils.findRequiredViewAsType(source, R.id.recyclerView_wuliu, "field 'recyclerView_wuliu'", RecyclerView.class);
    target.tv_wuliu_right1 = Utils.findRequiredViewAsType(source, R.id.tv_wuliu_right1, "field 'tv_wuliu_right1'", TextView.class);
    target.tv_wuliu_right2 = Utils.findRequiredViewAsType(source, R.id.tv_wuliu_right2, "field 'tv_wuliu_right2'", TextView.class);
    target.tv_wuliu_right3 = Utils.findRequiredViewAsType(source, R.id.tv_wuliu_right3, "field 'tv_wuliu_right3'", TextView.class);
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
  }

  @Override
  @CallSuper
  public void unbind() {
    WuliuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.recyclerView_wuliu = null;
    target.tv_wuliu_right1 = null;
    target.tv_wuliu_right2 = null;
    target.tv_wuliu_right3 = null;

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
>>>>>>> Stashed changes
  }
}
