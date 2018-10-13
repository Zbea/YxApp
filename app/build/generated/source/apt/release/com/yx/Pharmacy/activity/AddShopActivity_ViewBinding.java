// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddShopActivity_ViewBinding implements Unbinder {
  private AddShopActivity target;

  private View view2131296764;

  private View view2131296794;

  private View view2131296604;

  private View view2131296545;

  private View view2131296461;

  private View view2131296551;

  private View view2131296494;

  private View view2131296592;

  private View view2131296593;

  private View view2131296907;

  private View view2131296472;

  private View view2131296569;

  private View view2131296489;

  private View view2131296577;

  @UiThread
  public AddShopActivity_ViewBinding(AddShopActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddShopActivity_ViewBinding(final AddShopActivity target, View source) {
    this.target = target;

    View view;
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onViewClicked'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view2131296764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_scan, "field 'mRlScan' and method 'onViewClicked'");
    target.mRlScan = Utils.castView(view, R.id.rl_scan, "field 'mRlScan'", RelativeLayout.class);
    view2131296794 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvTitle1 = Utils.findRequiredViewAsType(source, R.id.tv_title1, "field 'mTvTitle1'", TextView.class);
    target.mTvShopType = Utils.findRequiredViewAsType(source, R.id.tv_shop_type, "field 'mTvShopType'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_shop_type, "field 'mLlShopType' and method 'onViewClicked'");
    target.mLlShopType = Utils.castView(view, R.id.ll_shop_type, "field 'mLlShopType'", LinearLayout.class);
    view2131296604 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvTitle2 = Utils.findRequiredViewAsType(source, R.id.tv_title2, "field 'mTvTitle2'", TextView.class);
    target.mEditShopName = Utils.findRequiredViewAsType(source, R.id.edit_shop_name, "field 'mEditShopName'", EditText.class);
    target.mEditShopUser = Utils.findRequiredViewAsType(source, R.id.edit_shop_user, "field 'mEditShopUser'", EditText.class);
    target.mEditShopUserPhone = Utils.findRequiredViewAsType(source, R.id.edit_shop_user_phone, "field 'mEditShopUserPhone'", EditText.class);
    target.mTvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'mTvAddress'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_address, "field 'mLlAddress' and method 'onViewClicked'");
    target.mLlAddress = Utils.castView(view, R.id.ll_address, "field 'mLlAddress'", LinearLayout.class);
    view2131296545 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mEditShopAddress = Utils.findRequiredViewAsType(source, R.id.edit_shop_address, "field 'mEditShopAddress'", EditText.class);
    target.mTvBusinessState = Utils.findRequiredViewAsType(source, R.id.tv_business_state, "field 'mTvBusinessState'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_business_state, "field 'mIvBusinessState' and method 'onViewClicked'");
    target.mIvBusinessState = Utils.castView(view, R.id.iv_business_state, "field 'mIvBusinessState'", ImageView.class);
    view2131296461 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_business, "field 'mLlBusiness' and method 'onViewClicked'");
    target.mLlBusiness = Utils.castView(view, R.id.ll_business, "field 'mLlBusiness'", LinearLayout.class);
    view2131296551 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mEditBusiness = Utils.findRequiredViewAsType(source, R.id.edit_business, "field 'mEditBusiness'", EditText.class);
    target.mTvOrgan = Utils.findRequiredViewAsType(source, R.id.tv_organ, "field 'mTvOrgan'", TextView.class);
    target.mTvOrganState = Utils.findRequiredViewAsType(source, R.id.tv_organ_state, "field 'mTvOrganState'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_organ_state, "field 'mIvOrganState' and method 'onViewClicked'");
    target.mIvOrganState = Utils.castView(view, R.id.iv_organ_state, "field 'mIvOrganState'", ImageView.class);
    view2131296494 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_organ, "field 'mLlOrgan' and method 'onViewClicked'");
    target.mLlOrgan = Utils.castView(view, R.id.ll_organ, "field 'mLlOrgan'", LinearLayout.class);
    view2131296592 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mEditOrgan = Utils.findRequiredViewAsType(source, R.id.edit_organ, "field 'mEditOrgan'", EditText.class);
    target.mTvOrganTime = Utils.findRequiredViewAsType(source, R.id.tv_organ_time, "field 'mTvOrganTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_organ_time, "field 'mLlOrganTime' and method 'onViewClicked'");
    target.mLlOrganTime = Utils.castView(view, R.id.ll_organ_time, "field 'mLlOrganTime'", LinearLayout.class);
    view2131296593 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mEditBuyerMobile = Utils.findRequiredViewAsType(source, R.id.edit_buyer_mobile, "field 'mEditBuyerMobile'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_add_shop, "field 'mTvAddShop' and method 'onViewClicked'");
    target.mTvAddShop = Utils.castView(view, R.id.tv_add_shop, "field 'mTvAddShop'", TextView.class);
    view2131296907 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvGspState = Utils.findRequiredViewAsType(source, R.id.tv_gsp_state, "field 'mTvGspState'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_gsp_state, "field 'mIvGspState' and method 'onViewClicked'");
    target.mIvGspState = Utils.castView(view, R.id.iv_gsp_state, "field 'mIvGspState'", ImageView.class);
    view2131296472 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_gsp, "field 'mLlGsp' and method 'onViewClicked'");
    target.mLlGsp = Utils.castView(view, R.id.ll_gsp, "field 'mLlGsp'", LinearLayout.class);
    view2131296569 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvMentouState = Utils.findRequiredViewAsType(source, R.id.tv_mentou_state, "field 'mTvMentouState'", TextView.class);
    target.tv_gsp_x = Utils.findRequiredViewAsType(source, R.id.tv_gsp_x, "field 'tv_gsp_x'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_mentou_state, "field 'mIvMentouState' and method 'onViewClicked'");
    target.mIvMentouState = Utils.castView(view, R.id.iv_mentou_state, "field 'mIvMentouState'", ImageView.class);
    view2131296489 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mentou, "field 'mLlMentou' and method 'onViewClicked'");
    target.mLlMentou = Utils.castView(view, R.id.ll_mentou, "field 'mLlMentou'", LinearLayout.class);
    view2131296577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddShopActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvBack = null;
    target.mRlBack = null;
    target.mTvTitle = null;
    target.mRlScan = null;
    target.mTvTitle1 = null;
    target.mTvShopType = null;
    target.mLlShopType = null;
    target.mTvTitle2 = null;
    target.mEditShopName = null;
    target.mEditShopUser = null;
    target.mEditShopUserPhone = null;
    target.mTvAddress = null;
    target.mLlAddress = null;
    target.mEditShopAddress = null;
    target.mTvBusinessState = null;
    target.mIvBusinessState = null;
    target.mLlBusiness = null;
    target.mEditBusiness = null;
    target.mTvOrgan = null;
    target.mTvOrganState = null;
    target.mIvOrganState = null;
    target.mLlOrgan = null;
    target.mEditOrgan = null;
    target.mTvOrganTime = null;
    target.mLlOrganTime = null;
    target.mEditBuyerMobile = null;
    target.mTvAddShop = null;
    target.mTvGspState = null;
    target.mIvGspState = null;
    target.mLlGsp = null;
    target.mTvMentouState = null;
    target.tv_gsp_x = null;
    target.mIvMentouState = null;
    target.mLlMentou = null;

    view2131296764.setOnClickListener(null);
    view2131296764 = null;
    view2131296794.setOnClickListener(null);
    view2131296794 = null;
    view2131296604.setOnClickListener(null);
    view2131296604 = null;
    view2131296545.setOnClickListener(null);
    view2131296545 = null;
    view2131296461.setOnClickListener(null);
    view2131296461 = null;
    view2131296551.setOnClickListener(null);
    view2131296551 = null;
    view2131296494.setOnClickListener(null);
    view2131296494 = null;
    view2131296592.setOnClickListener(null);
    view2131296592 = null;
    view2131296593.setOnClickListener(null);
    view2131296593 = null;
    view2131296907.setOnClickListener(null);
    view2131296907 = null;
    view2131296472.setOnClickListener(null);
    view2131296472 = null;
    view2131296569.setOnClickListener(null);
    view2131296569 = null;
    view2131296489.setOnClickListener(null);
    view2131296489 = null;
    view2131296577.setOnClickListener(null);
    view2131296577 = null;
  }
}
