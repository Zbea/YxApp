package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.model.YaoType1;
import com.yx.Pharmacy.model.YaoType2;
import com.yx.Pharmacy.presenter.HaveNeedPresenter;
import com.yx.Pharmacy.view.ICategoryView;
import com.yx.Pharmacy.view.IHaveNeedView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.LinkagePicker;

/**
 * 我有需求
 */
public class HaveNeedActivity  extends BaseActivity implements ICategoryView, IHaveNeedView {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.edit_product_name)
    EditText edit_product_name;
    @BindView(R.id.edit_need_num)
    EditText edit_need_num;
    @BindView(R.id.edit_product_type)
    TextView edit_product_type;
    @BindView(R.id.edit_need_note)
    EditText edit_need_note;

    private int catid;//YaoType2的catid

    private HaveNeedPresenter mPresenter;
    private List<YaoType1> yaoType1List=new ArrayList<>();

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, HaveNeedActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_have_need;
    }

    @Override
    protected void init() {
        initView();
        mPresenter=new HaveNeedPresenter(this);
        mPresenter.getCategoryData(this);
    }

    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("我有需求");
    }
    @OnClick({R.id.rl_back,R.id.tv_commit,R.id.rl_select_product_type})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_commit://提交需求
                commitNeed();
                break;
            case R.id.rl_select_product_type:
                //TODO 弹框选择产品类型，传入列表数据yaoType1List，
                // 点击确认按钮后，把YaoType2的catid给成员变量赋值，catname给editText设置
                if (yaoType1List!=null) {
                    onLinkagePicker();
                }
                break;

        }
    }
    //提交需求
    private void commitNeed() {
        String productName=edit_product_name.getText().toString().trim();
        String num=edit_need_num.getText().toString().trim();
        String note=edit_need_note.getText().toString().trim();//选填
        if(TextUtils.isEmpty(productName)){
            getShortToastByString("请输入商品名称");
            return;
        }
        if(TextUtils.isEmpty(num)){
            getShortToastByString("请输入需求数量");
            return;
        }
        //选择产品类型后，给EditText setText(YaoType2的catname)并赋值catid
        if(TextUtils.isEmpty(edit_product_type.getText().toString().trim())){
            getShortToastByString("请选择产品类型");
            return;
        }
        //此处的catid在选择产品类型的时候赋值，
        mPresenter.commitProductNeed(this,productName,num,catid,note);
    }


    @Override
    public void getCategoryResult(List<YaoType1> data) {//获取弹框里联动分类的数据
        yaoType1List=data;
    }

    @Override
    public void commitSuccess() {//提交需求成功
        finish();
    }


    public void onLinkagePicker() {
        //联动选择器的更多用法，可参见AddressPicker和CarNumberPicker
        LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

            @Override
            public boolean isOnlyTwo() {
                return true;
            }

            @NonNull
            @Override
            public List<String> provideFirstData() {
                ArrayList<String> firstList = new ArrayList<>();
                if (yaoType1List!=null) {
                    for (YaoType1 yaoType1 : yaoType1List) {
                        firstList.add(yaoType1.catname);
                    }
                }
                return firstList;
            }

            @NonNull
            @Override
            public List<String> provideSecondData(int firstIndex) {
                ArrayList<String> secondList = new ArrayList<>();
                List<YaoType2>    child      = yaoType1List.get(firstIndex).child;
                for (YaoType2 yaoType2 : child) {
                    secondList.add(yaoType2.catname);
                }
                return secondList;
            }

            @Nullable
            @Override
            public List<String> provideThirdData(int firstIndex, int secondIndex) {
                return null;
            }

        };
        LinkagePicker picker = new LinkagePicker(this, provider);
        picker.setCycleDisable(true);
        picker.setUseWeight(true);
        picker.setContentPadding(10, 0);
        picker.setOnStringPickListener(new LinkagePicker.OnStringPickListener() {
            @Override
            public void onPicked(String first, String second, String third) {
                edit_product_type.setText(first+" "+second);

                for (YaoType1 yaoType1 : yaoType1List) {
                    for (YaoType2 yaoType2 : yaoType1.child) {
                        if (TextUtils.equals(yaoType2.catname, second)) {
                            catid = yaoType2.catid;
                            return;
                        }
                    }
                }
            }
        });
        picker.show();
    }
}
