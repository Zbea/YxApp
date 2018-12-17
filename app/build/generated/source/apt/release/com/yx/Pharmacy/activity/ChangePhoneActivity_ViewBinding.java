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

public class ChangePhoneActivity_ViewBinding implements Unbinder {
  private ChangePhoneActivity target;

<<<<<<< Updated upstream
  private View view2131296987;

  private View view2131296777;

  private View view2131296954;
=======
  private View view2131297002;

  private View view2131296784;

  private View view2131296966;
>>>>>>> Stashed changes

  @UiThread
  public ChangePhoneActivity_ViewBinding(ChangePhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePhoneActivity_ViewBinding(final ChangePhoneActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.editOldPhoneNum = Utils.findRequiredViewAsType(source, R.id.edit_old_phone_num, "field 'editOldPhoneNum'", EditText.class);
    target.editPassword = Utils.findRequiredViewAsType(source, R.id.edit_password, "field 'editPassword'", EditText.class);
    target.editNewPhoneNum = Utils.findRequiredViewAsType(source, R.id.edit_new_phone_num, "field 'editNewPhoneNum'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tvGetCode' and method 'click'");
    target.tvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'tvGetCode'", TextView.class);
<<<<<<< Updated upstream
    view2131296987 = view;
=======
    view2131297002 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.editCode = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'editCode'", EditText.class);
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
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.editOldPhoneNum = null;
    target.editPassword = null;
    target.editNewPhoneNum = null;
    target.tvGetCode = null;
    target.editCode = null;

<<<<<<< Updated upstream
    view2131296987.setOnClickListener(null);
    view2131296987 = null;
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296954.setOnClickListener(null);
    view2131296954 = null;
=======
    view2131297002.setOnClickListener(null);
    view2131297002 = null;
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296966.setOnClickListener(null);
    view2131296966 = null;
>>>>>>> Stashed changes
  }
}
