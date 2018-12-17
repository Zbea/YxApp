// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyShopAddActivity_ViewBinding implements Unbinder {
  private MyShopAddActivity target;

<<<<<<< Updated upstream
  private View view2131296777;

  private View view2131296806;

  private View view2131296614;

  private View view2131296549;

  private View view2131296464;

  private View view2131296556;
=======
  private View view2131296784;

  private View view2131296816;

  private View view2131296619;

  private View view2131296552;

  private View view2131296464;

  private View view2131296559;
>>>>>>> Stashed changes

  private View view2131296498;

<<<<<<< Updated upstream
  private View view2131296602;

  private View view2131296603;

  private View view2131296919;
=======
  private View view2131296607;

  private View view2131296608;

  private View view2131296930;
>>>>>>> Stashed changes

  private View view2131296475;

<<<<<<< Updated upstream
  private View view2131296574;
=======
  private View view2131296577;
>>>>>>> Stashed changes

  private View view2131296493;

<<<<<<< Updated upstream
  private View view2131296586;

  private View view2131296579;

  private View view2131296554;

  private View view2131296580;
=======
  private View view2131296591;

  private View view2131296583;

  private View view2131296557;

  private View view2131296584;
>>>>>>> Stashed changes

  @UiThread
  public MyShopAddActivity_ViewBinding(MyShopAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyShopAddActivity_ViewBinding(final MyShopAddActivity target, View source) {
    this.target = target;

    View view;
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onViewClicked'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
<<<<<<< Updated upstream
    view2131296777 = view;
=======
    view2131296784 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_scan, "field 'mRlScan' and method 'onViewClicked'");
    target.mRlScan = Utils.castView(view, R.id.rl_scan, "field 'mRlScan'", RelativeLayout.class);
<<<<<<< Updated upstream
    view2131296806 = view;
=======
    view2131296816 = view;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    view2131296614 = view;
=======
    view2131296619 = view;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    view2131296549 = view;
=======
    view2131296552 = view;
>>>>>>> Stashed changes
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
    view2131296464 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_business, "field 'mLlBusiness' and method 'onViewClicked'");
    target.mLlBusiness = Utils.castView(view, R.id.ll_business, "field 'mLlBusiness'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296556 = view;
=======
    view2131296559 = view;
>>>>>>> Stashed changes
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
    view2131296498 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_organ, "field 'mLlOrgan' and method 'onViewClicked'");
    target.mLlOrgan = Utils.castView(view, R.id.ll_organ, "field 'mLlOrgan'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296602 = view;
=======
    view2131296607 = view;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    view2131296603 = view;
=======
    view2131296608 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mEditBuyerMobile = Utils.findRequiredViewAsType(source, R.id.edit_buyer_mobile, "field 'mEditBuyerMobile'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_add_shop, "field 'mTvAddShop' and method 'onViewClicked'");
    target.mTvAddShop = Utils.castView(view, R.id.tv_add_shop, "field 'mTvAddShop'", TextView.class);
<<<<<<< Updated upstream
    view2131296919 = view;
=======
    view2131296930 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvGspState = Utils.findRequiredViewAsType(source, R.id.tv_gsp_state, "field 'mTvGspState'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_gsp_state, "field 'mIvGspState' and method 'onViewClicked'");
    target.mIvGspState = Utils.castView(view, R.id.iv_gsp_state, "field 'mIvGspState'", ImageView.class);
    view2131296475 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_gsp, "field 'mLlGsp' and method 'onViewClicked'");
    target.mLlGsp = Utils.castView(view, R.id.ll_gsp, "field 'mLlGsp'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296574 = view;
=======
    view2131296577 = view;
>>>>>>> Stashed changes
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
    view2131296493 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mentou, "field 'mLlMentou' and method 'onViewClicked'");
    target.mLlMentou = Utils.castView(view, R.id.ll_mentou, "field 'mLlMentou'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296586 = view;
=======
    view2131296591 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rbGeneral = Utils.findRequiredViewAsType(source, R.id.rb_general, "field 'rbGeneral'", RadioButton.class);
    target.rbSpecial = Utils.findRequiredViewAsType(source, R.id.rb_special, "field 'rbSpecial'", RadioButton.class);
    target.rgInvoice = Utils.findRequiredViewAsType(source, R.id.rg_invoice, "field 'rgInvoice'", RadioGroup.class);
    target.ivInvoiceState = Utils.findRequiredViewAsType(source, R.id.iv_invoice_state, "field 'ivInvoiceState'", ImageView.class);
    target.llInvoiceContent = Utils.findRequiredViewAsType(source, R.id.ll_invoice_content, "field 'llInvoiceContent'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_invoice_info, "field 'llInvoiceInfo' and method 'onViewClicked'");
    target.llInvoiceInfo = Utils.castView(view, R.id.ll_invoice_info, "field 'llInvoiceInfo'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296579 = view;
=======
    view2131296583 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivBiaoState = Utils.findRequiredViewAsType(source, R.id.iv_biao_state, "field 'ivBiaoState'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_biao, "field 'llBiao' and method 'onViewClicked'");
    target.llBiao = Utils.castView(view, R.id.ll_biao, "field 'llBiao'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296554 = view;
=======
    view2131296557 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llInvoice = Utils.findRequiredViewAsType(source, R.id.ll_invoice, "field 'llInvoice'", LinearLayout.class);
    target.cbCheckall = Utils.findRequiredViewAsType(source, R.id.cb_checkall, "field 'cbCheckall'", CheckBox.class);
    target.llCheckall = Utils.findRequiredViewAsType(source, R.id.ll_checkall, "field 'llCheckall'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_invoice_select, "field 'llInvoiceSelect' and method 'onViewClicked'");
    target.llInvoiceSelect = Utils.castView(view, R.id.ll_invoice_select, "field 'llInvoiceSelect'", LinearLayout.class);
<<<<<<< Updated upstream
    view2131296580 = view;
=======
    view2131296584 = view;
>>>>>>> Stashed changes
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvInvoiceSelect = Utils.findRequiredViewAsType(source, R.id.tv_invoice_select, "field 'tvInvoiceSelect'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyShopAddActivity target = this.target;
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
    target.rbGeneral = null;
    target.rbSpecial = null;
    target.rgInvoice = null;
    target.ivInvoiceState = null;
    target.llInvoiceContent = null;
    target.llInvoiceInfo = null;
    target.ivBiaoState = null;
    target.llBiao = null;
    target.llInvoice = null;
    target.cbCheckall = null;
    target.llCheckall = null;
    target.llInvoiceSelect = null;
    target.tvInvoiceSelect = null;

<<<<<<< Updated upstream
    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296806.setOnClickListener(null);
    view2131296806 = null;
    view2131296614.setOnClickListener(null);
    view2131296614 = null;
    view2131296549.setOnClickListener(null);
    view2131296549 = null;
    view2131296464.setOnClickListener(null);
    view2131296464 = null;
    view2131296556.setOnClickListener(null);
    view2131296556 = null;
    view2131296498.setOnClickListener(null);
    view2131296498 = null;
    view2131296602.setOnClickListener(null);
    view2131296602 = null;
    view2131296603.setOnClickListener(null);
    view2131296603 = null;
    view2131296919.setOnClickListener(null);
    view2131296919 = null;
    view2131296475.setOnClickListener(null);
    view2131296475 = null;
    view2131296574.setOnClickListener(null);
    view2131296574 = null;
    view2131296493.setOnClickListener(null);
    view2131296493 = null;
    view2131296586.setOnClickListener(null);
    view2131296586 = null;
    view2131296579.setOnClickListener(null);
    view2131296579 = null;
    view2131296554.setOnClickListener(null);
    view2131296554 = null;
    view2131296580.setOnClickListener(null);
    view2131296580 = null;
=======
    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296816.setOnClickListener(null);
    view2131296816 = null;
    view2131296619.setOnClickListener(null);
    view2131296619 = null;
    view2131296552.setOnClickListener(null);
    view2131296552 = null;
    view2131296464.setOnClickListener(null);
    view2131296464 = null;
    view2131296559.setOnClickListener(null);
    view2131296559 = null;
    view2131296500.setOnClickListener(null);
    view2131296500 = null;
    view2131296607.setOnClickListener(null);
    view2131296607 = null;
    view2131296608.setOnClickListener(null);
    view2131296608 = null;
    view2131296930.setOnClickListener(null);
    view2131296930 = null;
    view2131296476.setOnClickListener(null);
    view2131296476 = null;
    view2131296577.setOnClickListener(null);
    view2131296577 = null;
    view2131296495.setOnClickListener(null);
    view2131296495 = null;
    view2131296591.setOnClickListener(null);
    view2131296591 = null;
    view2131296583.setOnClickListener(null);
    view2131296583 = null;
    view2131296557.setOnClickListener(null);
    view2131296557 = null;
    view2131296584.setOnClickListener(null);
    view2131296584 = null;
>>>>>>> Stashed changes
  }
}
