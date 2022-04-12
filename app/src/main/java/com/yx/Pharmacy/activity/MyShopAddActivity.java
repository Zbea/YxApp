package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiyukf.unicorn.widget.ViewPagerFixed;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.BigPicAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.dialog.PhotoDialog;
import com.yx.Pharmacy.dialog.ReasonDialog;
import com.yx.Pharmacy.model.AddressModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.StoreDetailModel;
import com.yx.Pharmacy.model.StoreTypeModel;
import com.yx.Pharmacy.model.UploadModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.MyShopPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.TimeUtils;
import com.yx.Pharmacy.view.IMyShopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.SinglePicker;
import cn.qqtheme.framework.util.ConvertUtils;


/*
 *  @项目名：  android
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   MyShopAddActivity
 *  @创建者:   CC
 *  @创建时间:  2018/7/23 22:03
 *  @描述：    TODO
 */

public class MyShopAddActivity
        extends BaseActivity
        implements IMyShopView, PhotoDialog.DialogClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rl_scan)
    RelativeLayout mRlScan;
    @BindView(R.id.tv_title1)
    TextView mTvTitle1;
    @BindView(R.id.tv_shop_type)
    TextView mTvShopType;
    @BindView(R.id.ll_shop_type)
    LinearLayout mLlShopType;
    @BindView(R.id.tv_title2)
    TextView mTvTitle2;
    @BindView(R.id.edit_shop_name)
    EditText mEditShopName;
    @BindView(R.id.edit_shop_user)
    EditText mEditShopUser;
    @BindView(R.id.edit_shop_user_phone)
    EditText mEditShopUserPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.edit_shop_address)
    EditText mEditShopAddress;
    @BindView(R.id.tv_business_state)
    TextView mTvBusinessState;
    @BindView(R.id.iv_business_state)
    ImageView mIvBusinessState;
    @BindView(R.id.ll_business)
    LinearLayout mLlBusiness;
    @BindView(R.id.edit_business)
    EditText mEditBusiness;
    @BindView(R.id.tv_organ)
    TextView mTvOrgan;
    @BindView(R.id.tv_organ_state)
    TextView mTvOrganState;
    @BindView(R.id.iv_organ_state)
    ImageView mIvOrganState;
    @BindView(R.id.ll_organ)
    LinearLayout mLlOrgan;
    @BindView(R.id.edit_organ)
    EditText mEditOrgan;
    @BindView(R.id.tv_organ_time)
    TextView mTvOrganTime;
    @BindView(R.id.ll_organ_time)
    LinearLayout mLlOrganTime;
    @BindView(R.id.edit_buyer_mobile)
    EditText mEditBuyerMobile;
    @BindView(R.id.tv_add_shop)
    TextView mTvAddShop;
    @BindView(R.id.tv_gsp_state)
    TextView mTvGspState;
    @BindView(R.id.iv_gsp_state)
    ImageView mIvGspState;
    @BindView(R.id.ll_gsp)
    LinearLayout mLlGsp;
    @BindView(R.id.tv_mentou_state)
    TextView mTvMentouState;
    @BindView(R.id.tv_gsp_x)
    TextView tv_gsp_x;
    @BindView(R.id.iv_mentou_state)
    ImageView mIvMentouState;
    @BindView(R.id.ll_mentou)
    LinearLayout mLlMentou;
    @BindView(R.id.rb_general)
    RadioButton rbGeneral;
    @BindView(R.id.rb_special)
    RadioButton rbSpecial;
    @BindView(R.id.rg_invoice)
    RadioGroup rgInvoice;
    @BindView(R.id.iv_invoice_state)
    ImageView ivInvoiceState;
    @BindView(R.id.ll_invoice_content)
    LinearLayout llInvoiceContent;
    @BindView(R.id.ll_invoice_info)
    LinearLayout llInvoiceInfo;
    @BindView(R.id.iv_biao_state)
    ImageView ivBiaoState;
    @BindView(R.id.ll_biao)
    LinearLayout llBiao;
    @BindView(R.id.ll_invoice)
    LinearLayout llInvoice;
    @BindView(R.id.cb_checkall)
    CheckBox cbCheckall;
    @BindView(R.id.ll_checkall)
    LinearLayout llCheckall;
    @BindView(R.id.ll_invoice_select)
    LinearLayout llInvoiceSelect;
    @BindView(R.id.tv_invoice_select)
    TextView tvInvoiceSelect;
    private MyShopPresenter mPresenter;
    private List<AddressModel> mAddressModel;
    private List<StoreTypeModel> mStoreType;
    private OptionPicker mTypePicker;
    private int mSelectStoreType;// 门店类型
    private AddressPicker mAddressPicker;
    private DatePicker mDataPicker;
    private List<LocalMedia> selectList;
    private String mSelectStoreItem;
    private int mPhotoType; // 0-营业执照  1-许可证  2-GSP 3-门头照
    private String mBusinessPath;
    private String mOrganPath;
    private String mGspPath;
    private String mMentouPath;
    private String mAddressId;
    private String mBusinessUrl;
    private String mOrganUrl;
    private String mGspUrl;
    private String mMentouUrl = "";

    private String mGeneralUrl;
    private String mSpecialUrl;
    private String mOrganTime;
    private String itemid;
    private Dialog mPhotoDialog;
    private ViewPagerFixed mViewpager;
    private TextView mTvPosition;
    private BigPicAdapter mBigPicAdapter;
    private int type = 1;
    ReasonDialog invoiceDialog = null;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyShopAddActivity.class);
        activity.startActivity(intent);
    }


    public static void startActivity(Activity activity, String itemid) {
        Intent intent = new Intent(activity, MyShopAddActivity.class);
        intent.putExtra("itemid", itemid);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_shop;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mTvTitle.setText("添加门店");

        itemid = getIntent().getStringExtra("itemid");

        rgInvoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_general) {
                    type = 1;
                    llInvoice.setVisibility(View.GONE);
                }
                if (checkedId == R.id.rb_special) {
                    type = 2;
                    llInvoice.setVisibility(View.VISIBLE);
                }
            }
        });

        mPresenter = new MyShopPresenter(this);
        initData();
    }

    private void initData() {
        mPresenter.getAddressList(this);
        mPresenter.getStoreType(this);

        if (!TextUtils.isEmpty(itemid)) {
            mPresenter.getStoreDetail(this, itemid);
            llInvoiceContent.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.rl_back,
            R.id.rl_scan,
            R.id.ll_shop_type,
            R.id.ll_address,
            R.id.ll_business,
            R.id.ll_organ,
            R.id.ll_organ_time,
            R.id.ll_gsp,
            R.id.ll_mentou,
            R.id.iv_business_state,
            R.id.iv_gsp_state,
            R.id.iv_mentou_state,
            R.id.iv_organ_state,
            R.id.ll_invoice_info,
            R.id.ll_biao,
            R.id.tv_add_shop,
            R.id.ll_invoice_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_scan:
                break;
            case R.id.ll_shop_type:
                showStoreTypeDialog();
                break;
            case R.id.ll_address:
                showAddressDialog();
                break;
            case R.id.ll_business:
                mPhotoType = 0;
                showPhotoDialog();
                break;
            case R.id.ll_organ:
                mPhotoType = 1;
                showPhotoDialog();
                break;
            case R.id.ll_gsp:
                mPhotoType = 2;
                showPhotoDialog();
                break;
            case R.id.ll_mentou:
                mPhotoType = 3;
                showPhotoDialog();
                break;
            case R.id.ll_organ_time:
                showDataDialog();
                break;
            case R.id.iv_business_state:
                if (!TextUtils.isEmpty(mBusinessUrl)) {
                    showBigPhotoDialog(mBusinessUrl);
                } else {
                    mPhotoType = 0;
                    showPhotoDialog();
                }
                break;
            case R.id.iv_organ_state:
                if (!TextUtils.isEmpty(mOrganUrl)) {
                    showBigPhotoDialog(mOrganUrl);
                } else {
                    mPhotoType = 1;
                    showPhotoDialog();
                }
                break;
            case R.id.iv_gsp_state:
                if (!TextUtils.isEmpty(mGspUrl)) {
                    showBigPhotoDialog(mGspUrl);
                } else {
                    mPhotoType = 2;
                    showPhotoDialog();
                }
                break;
            case R.id.iv_mentou_state:
                if (!TextUtils.isEmpty(mMentouUrl)) {
                    showBigPhotoDialog(mMentouUrl);
                } else {
                    mPhotoType = 3;
                    showPhotoDialog();
                }
                break;
            case R.id.ll_invoice_info:
                if (!TextUtils.isEmpty(mGeneralUrl)) {
                    showBigPhotoDialog(mGeneralUrl);
                } else {
                    mPhotoType = 4;
                    showPhotoDialog();
                }
                break;
            case R.id.ll_biao:
                if (!TextUtils.isEmpty(mSpecialUrl)) {
                    showBigPhotoDialog(mSpecialUrl);
                } else {
                    mPhotoType = 5;
                    showPhotoDialog();
                }
                break;
            case R.id.tv_add_shop:
                commit();
                break;
            case R.id.ll_invoice_select:

                showInvoice();

                break;
        }
    }

    /**
     * 发票类型选择
     */
    private void showInvoice() {

        if (invoiceDialog == null) {
            List<String> invoices = new ArrayList<>();
            invoices.add("普通发票");
            invoices.add("增值税专用发票");

            invoiceDialog = new ReasonDialog(mContext, invoices).builder();
            invoiceDialog.setDialogClickListener(new ReasonDialog.DialogClickListener() {
                @Override
                public void reason(String reason) {
                    tvInvoiceSelect.setText(reason);
                    if (reason.equals("普通发票")) {
                        type = 1;
                        llInvoice.setVisibility(View.GONE);
                    } else {
                        type = 2;
                        llInvoice.setVisibility(View.VISIBLE);
                    }
                }
            });
            invoiceDialog.show();
        } else {
            invoiceDialog.show();
        }

    }

    private void commit() {
        if (TextUtils.isEmpty(mSelectStoreItem)) {
            getShortToastByString("选择门店类型");
            return;
        }
        String name = mEditShopName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            getShortToastByString("输入门店名称");
            return;
        }
        String shopUser = mEditShopUser.getText().toString().trim();
        if (TextUtils.isEmpty(shopUser)) {
            getShortToastByString("输入主要负责人姓名");
            return;
        }
        String shopUserPhone = mEditShopUserPhone.getText().toString().trim();
        if (TextUtils.isEmpty(shopUserPhone)) {
            getShortToastByString("输入联系电话");
            return;
        }

        if (TextUtils.isEmpty(mAddressId)) {
            getShortToastByString("选择所在省市区");
            return;
        }
        String shopAddress = mEditShopAddress.getText().toString().trim();
        if (TextUtils.isEmpty(shopAddress)) {
            getShortToastByString("输入门店地址");
            return;
        }

        if (TextUtils.isEmpty(mBusinessUrl)) {
            getShortToastByString("上传营业执照");
            return;
        }
//        String business = mEditBusiness.getText().toString().trim();
//        if (TextUtils.isEmpty(business)) {
//            getShortToastByString("输入营业执照编号");
//            return;
//        }

        if (TextUtils.isEmpty(mOrganUrl)) {
            getShortToastByString("上传许可证照片");
            return;
        }
        String organ = mEditOrgan.getText().toString().trim();
        if (TextUtils.isEmpty(organ)) {
            getShortToastByString("输入许可证编号");
            return;
        }
//        if (TextUtils.isEmpty(mOrganTime)) {
//            getShortToastByString("选择许可证有效期");
//            return;
//        }

//        if (TextUtils.isEmpty(mMentouUrl)) {
//            getShortToastByString("上传门头照");
//            return;
//        }

//        if (mPhotoType==2) {
//            getShortToastByString("上传门头照");
//            return;
//        }
        if (TextUtils.equals(mSelectStoreItem, "药店") && TextUtils.isEmpty(mGspUrl)) {
            getShortToastByString("请上传GSP证照片");
            return;
        }

        String mobile = mEditBuyerMobile.getText().toString().trim();

        if (type == 2) {
            if (TextUtils.isEmpty(mGeneralUrl)) {
                getShortToastByString("请上传开票资料");
                return;
            }
            if (TextUtils.isEmpty(mSpecialUrl)) {
                getShortToastByString("请上传纳税人资格认证表");
                return;
            }
        }

        //添加门店
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("storecatid", String.valueOf(mSelectStoreType));//门店类型id
        urlMap.put("storename", name);//门店名称
//        urlMap.put("storenumber", business);//门店营业执照编号-
        urlMap.put("truename", shopUser);//负责人姓名
        urlMap.put("mobile", shopUserPhone);//负责人电话
        urlMap.put("areaid", mAddressId);//地址id
        urlMap.put("address", shopAddress);//门店地址
//        urlMap.put("overtime", mOrganTime);//许可证有效期
        urlMap.put("storelicense", organ);//许可证有效期
        urlMap.put("saleyw", NetUtil.isStringNull(mobile));//业务员电话
        urlMap.put("thumb", mBusinessUrl);//营业执照图片url
        urlMap.put("thumb1", mOrganUrl);//许可证图片url
        urlMap.put("thumb2", NetUtil.isStringNull(mGspUrl));//GSP证url
//        urlMap.put("thumb3", mMentouUrl);//门店形象图片url
        urlMap.put("invoicetype", type + "");//发票类型
        urlMap.put("einvoice", "" + (cbCheckall.isChecked() ? 1 : 0));//是否电子发票
        if (type == 2) {
            urlMap.put("invoiceimg1", mGeneralUrl);//普通发票图片url
            urlMap.put("invoiceimg2", mSpecialUrl);//认证表图片url
        }
        if (!TextUtils.isEmpty(itemid)) {
            urlMap.put("itemid", itemid);//门店id（特别注意：修改门店信息时需要上传 添加门店无此参数）
        }

        L.i(urlMap.toString());

        mPresenter.addStore(this, urlMap);
    }

    private void showPhotoDialog() {
        PhotoDialog photoDialog = new PhotoDialog(this);
        photoDialog.setDialogClickListener(this);
        photoDialog.builder().show();
    }

    @Override
    public void showShopData(List<MyShopModel> data) {

    }

    @Override
    public void showStoreType(List<StoreTypeModel> data) {
        this.mStoreType = data;
    }

    @Override
    public void showAddressList(List<AddressModel> data) {
        this.mAddressModel = data;
    }

    @Override
    public void showUploadResult(UploadModel data) {
        if (mPhotoType == 0) {
            mBusinessUrl = data.filepath;
            mTvBusinessState.setText("已选择");
        } else if (mPhotoType == 1) {
            mOrganUrl = data.filepath;
            mTvOrganState.setText("已选择");
        } else if (mPhotoType == 2) {
            mGspUrl = data.filepath;
            mTvGspState.setText("已选择");
        } else if (mPhotoType == 3) {
            mMentouUrl = data.filepath;
            mTvMentouState.setText("已选择");
        } else if (mPhotoType == 4) {
            mGeneralUrl = data.filepath;
        } else if (mPhotoType == 5) {
            mSpecialUrl = data.filepath;
        }
    }

    @Override
    public void showStoreDetail(StoreDetailModel data) {
        mEditShopUser.setText(data.truename);
        mEditBuyerMobile.setText(data.saleyw);
        mEditOrgan.setText(data.storelicense);
        mEditBusiness.setText(data.storelicense);
        mEditShopAddress.setText(data.address);
        mEditShopName.setText(data.storename);
        mEditShopUserPhone.setText(data.mobile);
        mTvAddress.setText(data.areaAddress);
        mTvShopType.setText(data.storecatName);
        mTvOrganTime.setText(data.overtime);

        mBusinessUrl = data.thumb;
        mOrganUrl = data.thumb1;
        mGspUrl = data.thumb2;
        mMentouUrl = data.thumb3;
        mSelectStoreType = DensityUtils.parseInt(data.storecatid);
        mSelectStoreItem = data.storecatName;
        mOrganTime = data.overtime;
        mAddressId = data.areaid;

        if (TextUtils.equals(mSelectStoreItem, "药店")) {
            tv_gsp_x.setVisibility(View.VISIBLE);
        } else {
            tv_gsp_x.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(mBusinessUrl)) {
            mIvBusinessState.setImageResource(R.drawable.icon_add_look);
            mTvBusinessState.setText("已选择");
        }
        if (!TextUtils.isEmpty(mOrganUrl)) {
            mIvOrganState.setImageResource(R.drawable.icon_add_look);
            mTvOrganState.setText("已选择");
        }
        if (!TextUtils.isEmpty(mGspUrl)) {
            mIvGspState.setImageResource(R.drawable.icon_add_look);
            mTvGspState.setText("已选择");
        }
        if (!TextUtils.isEmpty(mMentouUrl)) {
            mIvMentouState.setImageResource(R.drawable.icon_add_look);
            mTvMentouState.setText("已选择");
        }


        if (TextUtils.equals(data.status, "0")) {
            mTvAddShop.setEnabled(false);
            mTvAddShop.setBackgroundColor(Color.parseColor("#cccccc"));
            mTvAddShop.setText("审核中");
        } else if (TextUtils.equals(data.status, "1")) {
            mTvAddShop.setEnabled(false);
            mTvAddShop.setBackgroundColor(Color.parseColor("#cccccc"));
            mTvAddShop.setText("门店已关闭");
        } else if (TextUtils.equals(data.status, "8")) {
            mTvAddShop.setEnabled(true);
            mTvAddShop.setText("重新提交");
        } else if (TextUtils.equals(data.status, "9")) {
            mTvAddShop.setEnabled(false);
            mTvAddShop.setBackgroundColor(Color.parseColor("#cccccc"));
            mTvAddShop.setText("已通过");
        }
    }

    @Override
    public void showAdvanceData(HomeAdvanceModel data) {

    }

    private void showAddressDialog() {
        if (mAddressModel != null && mAddressModel.size() > 0) {
            showAddress();
        } else {
            mPresenter.getAddressList(this);
        }
    }


    /**
     * 省市区弹窗
     */
    private void showAddress() {
        if (mAddressPicker == null) {
            ArrayList<Province> data = new ArrayList<>();
            for (AddressModel model : mAddressModel) {
                Province province = new Province();
                province.setAreaId(model.areaid);
                province.setAreaName(model.areaname);
                ArrayList<City> citys = new ArrayList<>();
                for (AddressModel.ChildBean bean : model.child) {
                    City city = new City();
                    city.setAreaId(bean.areaid);
                    city.setAreaName(bean.areaname);
                    ArrayList<County> counties = new ArrayList<>();
                    for (AddressModel.CountyBean countyBean : bean.child) {
                        County county = new County();
                        county.setAreaId(countyBean.areaid);
                        county.setAreaName(countyBean.areaname);
                        counties.add(county);
                    }
                    city.setCounties(counties);
                    citys.add(city);
                }
                province.setCities(citys);
                data.add(province);
            }
            mAddressPicker = new AddressPicker(this, data);
            mAddressPicker.setShadowVisible(true);
            mAddressPicker.setTextColor(getResources().getColor(R.color.color_main));
            mAddressPicker.setTextSize(18);
            mAddressPicker.setDividerVisible(false);
            mAddressPicker.setShadowColor(Color.WHITE);
            mAddressPicker.setContentPadding(0, 10);
            mAddressPicker.setTopPadding(20);
            mAddressPicker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(Province province, City city, County county) {
                    if (county!=null)
                    {
                        mAddressId = county.getAreaId();
                        mTvAddress.setText(province.getAreaName() + " " + city.getAreaName() + " " + county.getAreaName());
                    }
                    else
                    {
                        if (city!=null)
                        {
                            mAddressId = city.getAreaId();
                            mTvAddress.setText(province.getAreaName() + " " + city.getAreaName());
                        }
                    }

                }
            });
            mAddressPicker.show();
        } else {
            mAddressPicker.show();
        }

    }


    private void showStoreTypeDialog() {
        if (mStoreType != null && mStoreType.size() > 0) {
            showTypeDialog();
        } else {
            mPresenter.getStoreType(this);
        }
    }

    /**
     * 门店类型弹窗
     */
    private void showTypeDialog() {
        if (mTypePicker == null) {
            ArrayList<String> typeList = new ArrayList<>();
            for (StoreTypeModel model : mStoreType) {
                typeList.add(model.catname);
            }
            mTypePicker = new OptionPicker(this, typeList);
            mTypePicker.setCanceledOnTouchOutside(true);
            mTypePicker.setCycleDisable(true);
            mTypePicker.setTextSize(18);
            mTypePicker.setShadowVisible(false);
            mTypePicker.setTextColor(getResources().getColor(R.color.color_main));
            mTypePicker.setDividerVisible(false);
            mTypePicker.setContentPadding(0, 10);
            mTypePicker.setTopPadding(20);
            mTypePicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    mSelectStoreType = mStoreType.get(index).catid;
                    mSelectStoreItem = item;
                    mTvShopType.setText(item);

                    if (TextUtils.equals(item, "药店") || TextUtils.equals(item, "商业")) {
                        mTvOrgan.setText("药品经营许可");
                        mTvOrganState.setHint("上传药品经营许可证");
                    } else {
                        mTvOrgan.setText("医疗机构许可");
                        mTvOrganState.setHint("上传医疗机构许可证");
                    }
                    if (TextUtils.equals(item, "药店")) {
                        tv_gsp_x.setVisibility(View.VISIBLE);
                    } else {
                        tv_gsp_x.setVisibility(View.GONE);
                    }
                }
            });
            mTypePicker.show();
        } else {
            mTypePicker.show();
        }

    }

    /**
     * 选择时间弹窗
     */
    private void showDataDialog() {
        if (mDataPicker == null) {

            long l = System.currentTimeMillis();
            long ofter = l + 311040000000L;
            mDataPicker = new DatePicker(this);
            mDataPicker.setCanceledOnTouchOutside(true);
            mDataPicker.setUseWeight(true);
            mDataPicker.setDividerVisible(false);
            mDataPicker.setTopPadding(ConvertUtils.toPx(this, 10));
            mDataPicker.setRangeEnd(DensityUtils.parseInt(TimeUtils.getDateYear(ofter)), DensityUtils.parseInt(TimeUtils.getDateMonth(ofter)), DensityUtils.parseInt(TimeUtils.getDateDay(ofter)));
            mDataPicker.setRangeStart(DensityUtils.parseInt(TimeUtils.getDateYear(l)), DensityUtils.parseInt(TimeUtils.getDateMonth(l)), DensityUtils.parseInt(TimeUtils.getDateDay(l)));
            mDataPicker.setResetWhileWheel(false);
            mDataPicker.setContentPadding(0, 10);
            mDataPicker.setTopPadding(20);
            mDataPicker.setLabel(null, null, null);
            mDataPicker.setTextColor(getResources().getColor(R.color.color_main));
            mDataPicker.setTextSize(18);
            mDataPicker.setContentPadding(0, 10);
            mDataPicker.setTopPadding(20);

            mDataPicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                @Override
                public void onDatePicked(String year, String month, String day) {
                    mOrganTime = year + "-" + month + "-" + day;
                    mTvOrganTime.setText(mOrganTime);
                }
            });
            mDataPicker.show();
        } else {
            mDataPicker.show();
        }

    }

    @Override
    public void takePhoto() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void pickPhoto() {
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
            //                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
            .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(0)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(false)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/data/data/com.yx.Pharmacy/shop")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .selectionMedia(null)// 是否传入已选图片 List<LocalMedia> list
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {  //图片选择返回
            // 图片选择结果回调
            selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList != null && selectList.size() > 0) {
                String path = selectList.get(0).getPath();
                if (mPhotoType == 0) {
                    mBusinessPath = path;
                    mIvBusinessState.setImageResource(R.drawable.icon_add_look);
                } else if (mPhotoType == 1) {
                    mOrganPath = path;
                    mIvOrganState.setImageResource(R.drawable.icon_add_look);
                } else if (mPhotoType == 2) {
                    mGspPath = path;
                    mIvGspState.setImageResource(R.drawable.icon_add_look);
                } else if (mPhotoType == 3) {
                    mMentouPath = path;
                    mIvMentouState.setImageResource(R.drawable.icon_add_look);
                } else if (mPhotoType == 4) {
                    mMentouPath = path;
                    ivInvoiceState.setImageResource(R.drawable.icon_add_look);
                } else if (mPhotoType == 5) {
                    mMentouPath = path;
                    ivBiaoState.setImageResource(R.drawable.icon_add_look);
                }

                mPresenter.uploadFile(this, path);
            }
        }
    }

    /**
     * 查看图片弹窗
     */
    private void showBigPhotoDialog(String fileName) {
        ArrayList<String> files = new ArrayList<>();
        if (TextUtils.isEmpty(fileName)) {
            return;
        } else {
            files.add(fileName);
        }
        if (mPhotoDialog == null) {
            mPhotoDialog = new Dialog(this, R.style.Dialog_Fullscreen);
            View view = getLayoutInflater().inflate(R.layout.activity_big_pic, null);
            mViewpager = view.findViewById(R.id.viewpager);
            mBigPicAdapter = new BigPicAdapter(files);
            mBigPicAdapter.setOnClick(new BigPicAdapter.OnClickFinishListener() {
                @Override
                public void onClick() {
                    mPhotoDialog.dismiss();
                }
            });

            mViewpager.setAdapter(mBigPicAdapter);
            mPhotoDialog.setContentView(view);
            mPhotoDialog.show();
        } else {
            mBigPicAdapter.setData(files);
            mPhotoDialog.show();
        }
    }


}
