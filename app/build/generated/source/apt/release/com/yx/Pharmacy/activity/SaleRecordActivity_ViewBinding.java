// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SaleRecordActivity_ViewBinding implements Unbinder {
  private SaleRecordActivity target;

<<<<<<< HEAD
<<<<<<< Updated upstream
  private View view2131296796;

  private View view2131296777;
=======
  private View view2131296806;

  private View view2131296784;
>>>>>>> Stashed changes
=======
  private View view2131296805;

  private View view2131296783;
>>>>>>> feature_1.0

  private View view2131296478;

  private View view2131296466;

  @UiThread
  public SaleRecordActivity_ViewBinding(SaleRecordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SaleRecordActivity_ViewBinding(final SaleRecordActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_more, "field 'rl_more' and method 'onClick'");
    target.rl_more = Utils.castView(view, R.id.rl_more, "field 'rl_more'", RelativeLayout.class);
<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296796 = view;
=======
    view2131296806 = view;
>>>>>>> Stashed changes
=======
    view2131296805 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.iv_more = Utils.findRequiredViewAsType(source, R.id.iv_more, "field 'iv_more'", ImageView.class);
    target.recyclerview = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.mTvNum = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'mTvNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onClick'");
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
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_home, "method 'onClick'");
    view2131296478 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_cart, "method 'onClick'");
    view2131296466 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SaleRecordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.rl_more = null;
    target.iv_more = null;
    target.recyclerview = null;
    target.swipeRefreshLayout = null;
    target.ll_nodata = null;
    target.mTvNum = null;

<<<<<<< HEAD
<<<<<<< Updated upstream
    view2131296796.setOnClickListener(null);
    view2131296796 = null;
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296476.setOnClickListener(null);
    view2131296476 = null;
=======
    view2131296806.setOnClickListener(null);
    view2131296806 = null;
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296478.setOnClickListener(null);
    view2131296478 = null;
>>>>>>> Stashed changes
=======
    view2131296805.setOnClickListener(null);
    view2131296805 = null;
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296478.setOnClickListener(null);
    view2131296478 = null;
>>>>>>> feature_1.0
    view2131296466.setOnClickListener(null);
    view2131296466 = null;
  }
}
