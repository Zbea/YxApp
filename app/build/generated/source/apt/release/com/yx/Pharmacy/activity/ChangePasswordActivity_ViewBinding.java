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

public class ChangePasswordActivity_ViewBinding implements Unbinder {
  private ChangePasswordActivity target;

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296987;
=======
  private View view2131297000;
>>>>>>> feature_1.0

  private View view2131296783;

<<<<<<< HEAD
  private View view2131296954;
=======
  private View view2131297002;

  private View view2131296784;

  private View view2131296966;
>>>>>>> Stashed changes
=======
  private View view2131296965;
>>>>>>> feature_1.0

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePasswordActivity_ViewBinding(final ChangePasswordActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.editOldPassword = Utils.findRequiredViewAsType(source, R.id.edit_old_password, "field 'editOldPassword'", EditText.class);
    target.editNewPassword = Utils.findRequiredViewAsType(source, R.id.edit_new_password, "field 'editNewPassword'", EditText.class);
    target.editPhoneNum = Utils.findRequiredViewAsType(source, R.id.edit_phone_num, "field 'editPhoneNum'", EditText.class);
    target.editCode = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'editCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tvGetCode' and method 'click'");
    target.tvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'tvGetCode'", TextView.class);
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296987 = view;
=======
    view2131297002 = view;
>>>>>>> Stashed changes
=======
    view2131297000 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
=======
    view2131296783 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_commit, "method 'click'");
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296954 = view;
=======
    view2131296966 = view;
>>>>>>> Stashed changes
=======
    view2131296965 = view;
>>>>>>> feature_1.0
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
    ChangePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.editOldPassword = null;
    target.editNewPassword = null;
    target.editPhoneNum = null;
    target.editCode = null;
    target.tvGetCode = null;

<<<<<<< HEAD
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
=======
    view2131297000.setOnClickListener(null);
    view2131297000 = null;
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296965.setOnClickListener(null);
    view2131296965 = null;
>>>>>>> feature_1.0
  }
}
