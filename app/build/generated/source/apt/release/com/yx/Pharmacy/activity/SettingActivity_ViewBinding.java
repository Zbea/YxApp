// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

<<<<<<< Updated upstream
  private View view2131296777;

  private View view2131296782;

  private View view2131297012;

  private View view2131296788;

  private View view2131296773;
=======
  private View view2131296784;

  private View view2131296789;

  private View view2131297027;

  private View view2131296824;

  private View view2131296797;

  private View view2131296780;
>>>>>>> Stashed changes

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
    target.tv_cache = Utils.findRequiredViewAsType(source, R.id.tv_cache, "field 'tv_cache'", TextView.class);
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
    view = Utils.findRequiredView(source, R.id.rl_clean_cache, "method 'click'");
<<<<<<< Updated upstream
    view2131296782 = view;
=======
    view2131296789 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_login_out, "method 'click'");
<<<<<<< Updated upstream
    view2131297012 = view;
=======
    view2131297027 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_update, "method 'click'");
    view2131296824 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_evaluation, "method 'click'");
<<<<<<< Updated upstream
    view2131296788 = view;
=======
    view2131296797 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_about, "method 'click'");
<<<<<<< Updated upstream
    view2131296773 = view;
=======
    view2131296780 = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_more = null;
    target.tv_cache = null;

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296782.setOnClickListener(null);
    view2131296782 = null;
    view2131297012.setOnClickListener(null);
    view2131297012 = null;
    view2131296788.setOnClickListener(null);
    view2131296788 = null;
    view2131296773.setOnClickListener(null);
    view2131296773 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296789.setOnClickListener(null);
    view2131296789 = null;
    view2131297027.setOnClickListener(null);
    view2131297027 = null;
    view2131296824.setOnClickListener(null);
    view2131296824 = null;
    view2131296797.setOnClickListener(null);
    view2131296797 = null;
    view2131296780.setOnClickListener(null);
    view2131296780 = null;
>>>>>>> Stashed changes
  }
}
