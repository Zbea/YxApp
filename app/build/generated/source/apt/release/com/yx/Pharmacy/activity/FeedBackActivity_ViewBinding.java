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

public class FeedBackActivity_ViewBinding implements Unbinder {
  private FeedBackActivity target;

  private View view2131296774;

  @UiThread
  public FeedBackActivity_ViewBinding(FeedBackActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FeedBackActivity_ViewBinding(final FeedBackActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
    view2131296774 = view;
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
    FeedBackActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_more = null;

    view2131296774.setOnClickListener(null);
    view2131296774 = null;
  }
}
