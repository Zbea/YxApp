// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.widget.flowtag.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchActivity_ViewBinding implements Unbinder {
  private SearchActivity target;

  private View view2131296514;

  private View view2131296638;

  private View view2131296608;

  private View view2131296635;

  private View view2131296561;

  private View view2131296963;

  private View view2131296954;

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
    target.ll_search_result = Utils.findRequiredViewAsType(source, R.id.ll_search_result, "field 'll_search_result'", LinearLayout.class);
    target.tv_zonghe = Utils.findRequiredViewAsType(source, R.id.tv_zonghe, "field 'tv_zonghe'", TextView.class);
    target.iv_zonghe = Utils.findRequiredViewAsType(source, R.id.iv_zonghe, "field 'iv_zonghe'", ImageView.class);
    target.tv_price = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tv_price'", TextView.class);
    target.iv_price = Utils.findRequiredViewAsType(source, R.id.iv_price, "field 'iv_price'", ImageView.class);
    target.tv_xiaoliang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliang, "field 'tv_xiaoliang'", TextView.class);
    target.iv_xiaoliang = Utils.findRequiredViewAsType(source, R.id.iv_xiaoliang, "field 'iv_xiaoliang'", ImageView.class);
    target.iv_layout_mode = Utils.findRequiredViewAsType(source, R.id.iv_layout_mode, "field 'iv_layout_mode'", ImageView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.rl_amin_window = Utils.findRequiredViewAsType(source, R.id.rl_amin_window, "field 'rl_amin_window'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_shopping_car, "field 'iv_shopping_car' and method 'click'");
    target.iv_shopping_car = Utils.castView(view, R.id.iv_shopping_car, "field 'iv_shopping_car'", ImageView.class);
    view2131296514 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.tv_num = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tv_num'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_zonghe, "method 'click'");
    view2131296638 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_price, "method 'click'");
    view2131296608 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_xiaoliang, "method 'click'");
    view2131296635 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_layout, "method 'click'");
    view2131296561 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_clear_history, "method 'click'");
    view2131296963 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cancel, "method 'click'");
    view2131296954 = view;
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
    target.ll_search_result = null;
    target.tv_zonghe = null;
    target.iv_zonghe = null;
    target.tv_price = null;
    target.iv_price = null;
    target.tv_xiaoliang = null;
    target.iv_xiaoliang = null;
    target.iv_layout_mode = null;
    target.swipeRefreshLayout = null;
    target.recyclerview = null;
    target.ll_nodata = null;
    target.rl_amin_window = null;
    target.iv_shopping_car = null;
    target.tv_num = null;

    view2131296514.setOnClickListener(null);
    view2131296514 = null;
    view2131296638.setOnClickListener(null);
    view2131296638 = null;
    view2131296608.setOnClickListener(null);
    view2131296608 = null;
    view2131296635.setOnClickListener(null);
    view2131296635 = null;
    view2131296561.setOnClickListener(null);
    view2131296561 = null;
    view2131296963.setOnClickListener(null);
    view2131296963 = null;
    view2131296954.setOnClickListener(null);
    view2131296954 = null;
  }
}
