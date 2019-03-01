// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.flowtag.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchActivity_ViewBinding implements Unbinder {
  private SearchActivity target;

  private View view2131296467;

  private View view2131296990;

  private View view2131296982;

  @UiThread
  public SearchActivity_ViewBinding(SearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchActivity_ViewBinding(final SearchActivity target, View source) {
    this.target = target;

    View view;
    target.flowlayout_history = Utils.findRequiredViewAsType(source, R.id.flowlayout_history, "field 'flowlayout_history'", FlowTagLayout.class);
    target.flowlayout_hot = Utils.findRequiredViewAsType(source, R.id.flowlayout_hot, "field 'flowlayout_hot'", FlowTagLayout.class);
    target.edit_search = Utils.findRequiredViewAsType(source, R.id.edit_search, "field 'edit_search'", EditText.class);
    target.ll_tuijian = Utils.findRequiredViewAsType(source, R.id.ll_tuijian, "field 'll_tuijian'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'click'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296467 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_clear_history, "method 'click'");
    view2131296990 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cancel, "method 'click'");
    view2131296982 = view;
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
    SearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.flowlayout_history = null;
    target.flowlayout_hot = null;
    target.edit_search = null;
    target.ll_tuijian = null;
    target.ivBack = null;

    view2131296467.setOnClickListener(null);
    view2131296467 = null;
    view2131296990.setOnClickListener(null);
    view2131296990 = null;
    view2131296982.setOnClickListener(null);
    view2131296982 = null;
  }
}
