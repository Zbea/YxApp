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

public class ChangeBankCardActivity_ViewBinding implements Unbinder {
  private ChangeBankCardActivity target;

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
  public ChangeBankCardActivity_ViewBinding(ChangeBankCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangeBankCardActivity_ViewBinding(final ChangeBankCardActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.edit_name = Utils.findRequiredViewAsType(source, R.id.edit_name, "field 'edit_name'", EditText.class);
    target.edit_card_code = Utils.findRequiredViewAsType(source, R.id.edit_card_code, "field 'edit_card_code'", EditText.class);
    target.tv_kaihuhang = Utils.findRequiredViewAsType(source, R.id.tv_kaihuhang, "field 'tv_kaihuhang'", TextView.class);
    target.edit_wangdian = Utils.findRequiredViewAsType(source, R.id.edit_wangdian, "field 'edit_wangdian'", EditText.class);
    target.edit_phone = Utils.findRequiredViewAsType(source, R.id.edit_phone, "field 'edit_phone'", EditText.class);
    target.edit_code = Utils.findRequiredViewAsType(source, R.id.edit_code, "field 'edit_code'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tv_get_code' and method 'click'");
    target.tv_get_code = Utils.castView(view, R.id.tv_get_code, "field 'tv_get_code'", TextView.class);
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
    ChangeBankCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.edit_name = null;
    target.edit_card_code = null;
    target.tv_kaihuhang = null;
    target.edit_wangdian = null;
    target.edit_phone = null;
    target.edit_code = null;
    target.tv_get_code = null;

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
