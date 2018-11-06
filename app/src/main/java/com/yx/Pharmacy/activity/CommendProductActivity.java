package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.CommendProductAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.presenter.CommendProductPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.view.ICommendView;
import com.yx.Pharmacy.widget.LoadingLayout;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   CommendProductActivity
 *  @创建者:   CC
 *  @创建时间:  2018/7/29 16:31
 *  @描述：    TODO
 */

public class CommendProductActivity
        extends BaseActivity implements ICommendView
{

    private static final int TYPE_zonghe=3;
    private static final int TYPE_jiage=1;
    private static final int TYPE_xiaoliang=2;
    private int curType=3;//当前选中类型
    private boolean isUp=false;//true升序  false降序
    
    @BindView(R.id.iv_back)
    ImageView      mIvBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView       mTvTitle;
    @BindView(R.id.tv_zonghe)
    TextView       mTvZonghe;
    @BindView(R.id.iv_zonghe)
    ImageView      mIvZonghe;
    @BindView(R.id.ll_zonghe)
    LinearLayout   mLlZonghe;
    @BindView(R.id.tv_price)
    TextView       mTvPrice;
    @BindView(R.id.iv_price)
    ImageView      mIvPrice;
    @BindView(R.id.ll_price)
    LinearLayout   mLlPrice;
    @BindView(R.id.tv_xiaoliang)
    TextView       mTvXiaoliang;
    @BindView(R.id.iv_xiaoliang)
    ImageView      mIvXiaoliang;
    @BindView(R.id.ll_xiaoliang)
    LinearLayout   mLlXiaoliang;
    @BindView(R.id.rv_product)
    RecyclerView   mRvProduct;
    @BindView(R.id.loadinglayout)
    LoadingLayout  mLoadinglayout;
    private CommendProductAdapter mAdapter;
    private String mType;//专区类型
    private CommendProductPresenter mPresenter;

    public static final String TYPE  = "type";
    public static final String LEVELID  = "levelid";
    private String mLevelid;
    private int diff;//请求参数区别

    public static void startActivity(Activity activity,String type,String levelid) {
        Intent intent = new Intent(activity, CommendProductActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(LEVELID, levelid);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity,String type,String levelid,String activityname) {
        Intent intent = new Intent(activity, CommendProductActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(LEVELID, levelid);
        intent.putExtra("activityname", activityname);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity,String type,String levelid,String activityname,int diff) {
        Intent intent = new Intent(activity, CommendProductActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(LEVELID, levelid);
        intent.putExtra("activityname", activityname);
        intent.putExtra("diff", diff);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commend_product;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mType = getIntent().getStringExtra(TYPE);
        mLevelid = getIntent().getStringExtra(LEVELID);
        mTvTitle.setText(getIntent().getStringExtra("activityname"));
        diff=getIntent().getIntExtra("diff",0);
        mPresenter = new CommendProductPresenter(this);

        initRecycler();
        initData();
    }

    private void initRecycler() {
        // 商品列表
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvProduct.setLayoutManager(manager);
        mAdapter = new CommendProductAdapter(R.layout.item_home_product_minus, mType);
        mRvProduct.setAdapter(mAdapter);

        int itemDecoration = DensityUtils.dp2px(this, 1);
        mRvProduct.addItemDecoration(new SpacesItemDecoration(itemDecoration, itemDecoration));


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailActivity.startActivity(CommendProductActivity.this,String.valueOf(mAdapter.getData().get(position).getItemid()));
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //TODO 加载下一页
                initNestPage();
            }
        },mRvProduct);
    }

    private void initNestPage() {
        if (diff==1)
        {
            mPresenter.moreProduct(this,mLevelid,curType,isUp,diff);
        }
        else
        {
            mPresenter.moreProduct(this,mLevelid,curType,isUp);
        }
    }

    private void initData() {
        if (diff==1)
        {
            mPresenter.loadProduct(this,mLevelid,curType,isUp,diff);
        }
        else
        {
            mPresenter.loadProduct(this,mLevelid,curType,isUp);
        }
    }

    @OnClick({R.id.rl_back,
              R.id.ll_zonghe,
              R.id.ll_price,
              R.id.ll_xiaoliang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_zonghe://综合
                if(curType!=TYPE_zonghe){//切换类型
                    curType=TYPE_zonghe;
                    isUp=false;
                    xiaoLiangDown();
                    mTvZonghe.setTextColor(getResources().getColor(R.color.color_main));
                    mIvZonghe.setImageResource(R.drawable.pxxjtdj);
                    priceDown();
                }else {//非切换类型,要判断当前是升序还是降序
                    if(isUp){
                        isUp=false;
                        mTvZonghe.setTextColor(getResources().getColor(R.color.color_main));
                        mIvZonghe.setImageResource(R.drawable.zi_down);
                    }else {
                        isUp=true;
                        mTvZonghe.setTextColor(getResources().getColor(R.color.color_main));
                        mIvZonghe.setImageResource(R.drawable.pxxjtdj);
                    }
                }
                if (diff==1)
                {
                    mPresenter.loadProduct(this,mLevelid,curType,isUp,diff);
                }
                else
                {
                    mPresenter.loadProduct(this,mLevelid,curType,isUp);
                }
                break;
            case R.id.ll_price://价格
                if(curType!=TYPE_jiage){//切换类型
                    curType=TYPE_jiage;
                    isUp=false;
                    xiaoLiangDown();
                    zhongheDown();
                    mTvPrice.setTextColor(getResources().getColor(R.color.color_main));
                    mIvPrice.setImageResource(R.drawable.pxxjtdj);
                }else {//非切换类型,要判断当前是升序还是降序
                    if(isUp){
                        isUp=false;
                        mTvPrice.setTextColor(getResources().getColor(R.color.color_main));
                        mIvPrice.setImageResource(R.drawable.zi_down);
                    }else {
                        isUp=true;
                        mTvPrice.setTextColor(getResources().getColor(R.color.color_main));
                        mIvPrice.setImageResource(R.drawable.pxxjtdj);
                    }
                }
                if (diff==1)
                {
                    mPresenter.loadProduct(this,mLevelid,curType,isUp,diff);
                }
                else
                {
                    mPresenter.loadProduct(this,mLevelid,curType,isUp);
                }
                break;
            case R.id.ll_xiaoliang://销量
                if(curType!=TYPE_xiaoliang){//切换类型
                    curType=TYPE_xiaoliang;
                    isUp=false;
                    mTvXiaoliang.setTextColor(getResources().getColor(R.color.color_main));
                    mIvXiaoliang.setImageResource(R.drawable.pxxjtdj);
                    zhongheDown();
                    priceDown();
                }else {//非切换类型,要判断当前是升序还是降序
                    if(isUp){
                        isUp=false;
                        mTvXiaoliang.setTextColor(getResources().getColor(R.color.color_main));
                        mIvXiaoliang.setImageResource(R.drawable.zi_down);
                    }else {
                        isUp=true;
                        mTvXiaoliang.setTextColor(getResources().getColor(R.color.color_main));
                        mIvXiaoliang.setImageResource(R.drawable.pxxjtdj);
                    }
                }
                if (diff==1)
                {
                    mPresenter.loadProduct(this,mLevelid,curType,isUp,diff);
                }
                else
                {
                    mPresenter.loadProduct(this,mLevelid,curType,isUp);
                }
                break;
        }
    }

    @Override
    public void getProductListResult(List<DrugModel> data, String extention) {
        if (data!=null&&data.size()>0) {
            mLoadinglayout.setStatus(LoadingLayout.Success);
            mAdapter.setNewData(data);
            if (data.size() < Constants.PAGESIZE) {
                mAdapter.loadMoreEnd();
            }
        }else {
            mLoadinglayout.setStatus(LoadingLayout.Empty);
        }
    }

    @Override
    public void addProductListResult(List<DrugModel> data) {
        mAdapter.addData(data);
        if (data.size()<Constants.PAGESIZE){
            mAdapter.loadMoreEnd();
        }else {
            mAdapter.loadMoreComplete();
        }
    }


    private void zhongheDown() {
        mTvZonghe.setTextColor(getResources().getColor(R.color.color_606060));
        mIvZonghe.setImageResource(R.drawable.pxxjtmr);
    }

    private void priceDown() {
        mTvPrice.setTextColor(getResources().getColor(R.color.color_606060));
        mIvPrice.setImageResource(R.drawable.pxxjtmr);
    }

    private void xiaoLiangDown() {
        mTvXiaoliang.setTextColor(getResources().getColor(R.color.color_606060));
        mIvXiaoliang.setImageResource(R.drawable.pxxjtmr);
    }
}
