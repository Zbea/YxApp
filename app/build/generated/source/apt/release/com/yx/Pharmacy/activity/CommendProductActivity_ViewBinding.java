// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import com.yx.Pharmacy.widget.LoadingLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommendProductActivity_ViewBinding implements Unbinder {
  private CommendProductActivity target;

<<<<<<< Updated upstream
  private View view2131296777;

  private View view2131296632;

  private View view2131296604;

  private View view2131296629;
=======
  private View view2131296784;

  private View view2131296639;

  private View view2131296609;

  private View view2131296636;
>>>>>>> Stashed changes

  @UiThread
  public CommendProductActivity_ViewBinding(CommendProductActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CommendProductActivity_ViewBinding(final CommendProductActivity target, View source) {
    this.target = target;

    View view;
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onClick'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mTvZonghe = Utils.findRequiredViewAsType(source, R.id.tv_zonghe, "field 'mTvZonghe'", TextView.class);
    target.mIvZonghe = Utils.findRequiredViewAsType(source, R.id.iv_zonghe, "field 'mIvZonghe'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_zonghe, "field 'mLlZonghe' and method 'onClick'");
    target.mLlZonghe = Utils.castView(view, R.id.ll_zonghe, "field 'mLlZonghe'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296632 = view;
=======
    view2131296639 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'mTvPrice'", TextView.class);
    target.mIvPrice = Utils.findRequiredViewAsType(source, R.id.iv_price, "field 'mIvPrice'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_price, "field 'mLlPrice' and method 'onClick'");
    target.mLlPrice = Utils.castView(view, R.id.ll_price, "field 'mLlPrice'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296604 = view;
=======
    view2131296609 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvXiaoliang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliang, "field 'mTvXiaoliang'", TextView.class);
    target.mIvXiaoliang = Utils.findRequiredViewAsType(source, R.id.iv_xiaoliang, "field 'mIvXiaoliang'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_xiaoliang, "field 'mLlXiaoliang' and method 'onClick'");
    target.mLlXiaoliang = Utils.castView(view, R.id.ll_xiaoliang, "field 'mLlXiaoliang'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296629 = view;
=======
    view2131296636 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mRvProduct = Utils.findRequiredViewAsType(source, R.id.rv_product, "field 'mRvProduct'", RecyclerView.class);
    target.mLoadinglayout = Utils.findRequiredViewAsType(source, R.id.loadinglayout, "field 'mLoadinglayout'", LoadingLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CommendProductActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvBack = null;
    target.mRlBack = null;
    target.mTvTitle = null;
    target.mTvZonghe = null;
    target.mIvZonghe = null;
    target.mLlZonghe = null;
    target.mTvPrice = null;
    target.mIvPrice = null;
    target.mLlPrice = null;
    target.mTvXiaoliang = null;
    target.mIvXiaoliang = null;
    target.mLlXiaoliang = null;
    target.mRvProduct = null;
    target.mLoadinglayout = null;

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296632.setOnClickListener(null);
    view2131296632 = null;
    view2131296604.setOnClickListener(null);
    view2131296604 = null;
    view2131296629.setOnClickListener(null);
    view2131296629 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296639.setOnClickListener(null);
    view2131296639 = null;
    view2131296609.setOnClickListener(null);
    view2131296609 = null;
    view2131296636.setOnClickListener(null);
    view2131296636 = null;
>>>>>>> Stashed changes
  }
}
