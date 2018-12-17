// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserInfoActivity_ViewBinding implements Unbinder {
  private UserInfoActivity target;

<<<<<<< Updated upstream
  private View view2131296777;

  private View view2131296814;

  private View view2131296778;
=======
  private View view2131296784;

  private View view2131296825;

  private View view2131296785;
>>>>>>> Stashed changes

  @UiThread
  public UserInfoActivity_ViewBinding(UserInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserInfoActivity_ViewBinding(final UserInfoActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.iv_user_head = Utils.findRequiredViewAsType(source, R.id.iv_user_head, "field 'iv_user_head'", ImageView.class);
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
    view = Utils.findRequiredView(source, R.id.rl_user_head, "method 'click'");
<<<<<<< Updated upstream
    view2131296814 = view;
=======
    view2131296825 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_birthday, "method 'click'");
<<<<<<< Updated upstream
    view2131296778 = view;
=======
    view2131296785 = view;
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
    UserInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.iv_user_head = null;

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296814.setOnClickListener(null);
    view2131296814 = null;
    view2131296778.setOnClickListener(null);
    view2131296778 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296825.setOnClickListener(null);
    view2131296825 = null;
    view2131296785.setOnClickListener(null);
    view2131296785 = null;
>>>>>>> Stashed changes
  }
}
