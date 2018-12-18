// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductCategoryDetailActivity_ViewBinding implements Unbinder {
  private ProductCategoryDetailActivity target;

<<<<<<< HEAD
  private View view2131296784;

  private View view2131296639;

  private View view2131296609;

  private View view2131296636;

  private View view2131297099;

  private View view2131296562;

  private View view2131296514;

  private View view2131296817;

  private View view2131296816;

=======
  private View view2131296783;

  private View view2131296638;

  private View view2131296608;

  private View view2131296635;

  private View view2131297097;

  private View view2131296561;

  private View view2131296514;

  private View view2131296816;

  private View view2131296815;

>>>>>>> feature_1.0
  @UiThread
  public ProductCategoryDetailActivity_ViewBinding(ProductCategoryDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductCategoryDetailActivity_ViewBinding(final ProductCategoryDetailActivity target,
      View source) {
    this.target = target;

    View view;
    target.tv_zonghe = Utils.findRequiredViewAsType(source, R.id.tv_zonghe, "field 'tv_zonghe'", TextView.class);
    target.iv_zonghe = Utils.findRequiredViewAsType(source, R.id.iv_zonghe, "field 'iv_zonghe'", ImageView.class);
    target.tv_price = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tv_price'", TextView.class);
    target.iv_price = Utils.findRequiredViewAsType(source, R.id.iv_price, "field 'iv_price'", ImageView.class);
    target.tv_xiaoliang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliang, "field 'tv_xiaoliang'", TextView.class);
    target.iv_xiaoliang = Utils.findRequiredViewAsType(source, R.id.iv_xiaoliang, "field 'iv_xiaoliang'", ImageView.class);
    target.iv_layout_mode = Utils.findRequiredViewAsType(source, R.id.iv_layout_mode, "field 'iv_layout_mode'", ImageView.class);
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.ll_nodata = Utils.findRequiredViewAsType(source, R.id.ll_nodata, "field 'll_nodata'", LinearLayout.class);
    target.ll_error = Utils.findRequiredViewAsType(source, R.id.ll_error, "field 'll_error'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'click'");
<<<<<<< HEAD
    view2131296784 = view;
=======
    view2131296783 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_zonghe, "method 'click'");
<<<<<<< HEAD
    view2131296639 = view;
=======
    view2131296638 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_price, "method 'click'");
<<<<<<< HEAD
    view2131296609 = view;
=======
    view2131296608 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_xiaoliang, "method 'click'");
<<<<<<< HEAD
    view2131296636 = view;
=======
    view2131296635 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_reload, "method 'click'");
<<<<<<< HEAD
    view2131297099 = view;
=======
    view2131297097 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_layout, "method 'click'");
<<<<<<< HEAD
    view2131296562 = view;
=======
    view2131296561 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_shopping_car, "method 'click'");
    view2131296514 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_search, "method 'click'");
<<<<<<< HEAD
    view2131296817 = view;
=======
    view2131296816 = view;
>>>>>>> feature_1.0
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_scan, "method 'click'");
<<<<<<< HEAD
    view2131296816 = view;
=======
    view2131296815 = view;
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
    ProductCategoryDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_zonghe = null;
    target.iv_zonghe = null;
    target.tv_price = null;
    target.iv_price = null;
    target.tv_xiaoliang = null;
    target.iv_xiaoliang = null;
    target.iv_layout_mode = null;
    target.tv_title = null;
    target.swipeRefreshLayout = null;
    target.ll_nodata = null;
    target.ll_error = null;

<<<<<<< HEAD
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296639.setOnClickListener(null);
    view2131296639 = null;
    view2131296609.setOnClickListener(null);
    view2131296609 = null;
    view2131296636.setOnClickListener(null);
    view2131296636 = null;
    view2131297099.setOnClickListener(null);
    view2131297099 = null;
    view2131296562.setOnClickListener(null);
    view2131296562 = null;
    view2131296514.setOnClickListener(null);
    view2131296514 = null;
    view2131296817.setOnClickListener(null);
    view2131296817 = null;
    view2131296816.setOnClickListener(null);
    view2131296816 = null;
=======
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296638.setOnClickListener(null);
    view2131296638 = null;
    view2131296608.setOnClickListener(null);
    view2131296608 = null;
    view2131296635.setOnClickListener(null);
    view2131296635 = null;
    view2131297097.setOnClickListener(null);
    view2131297097 = null;
    view2131296561.setOnClickListener(null);
    view2131296561 = null;
    view2131296514.setOnClickListener(null);
    view2131296514 = null;
    view2131296816.setOnClickListener(null);
    view2131296816 = null;
    view2131296815.setOnClickListener(null);
    view2131296815 = null;
>>>>>>> feature_1.0
  }
}
