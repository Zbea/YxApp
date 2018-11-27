package com.yx.Pharmacy.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.CaptureActivity;
import com.yx.Pharmacy.activity.SearchActivity;
import com.yx.Pharmacy.adapter.SortAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.dialog.ConfirmDialog;
import com.yx.Pharmacy.model.YaoType1;
import com.yx.Pharmacy.presenter.CategoryPresenter;
import com.yx.Pharmacy.view.CheckListener;
import com.yx.Pharmacy.view.ICategoryView;
import com.yx.Pharmacy.widget.ItemHeaderDecoration;
import com.yx.Pharmacy.zxing.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by KID on 2018/7/14.
 */

public class CategoryFragment extends BaseFragment implements ICategoryView, CheckListener {

    private CategoryPresenter mPresenter;
    private ArrayList<YaoType1>yaoType1s=new ArrayList<>();

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_sort)
    RecyclerView rvSort;

    private SortAdapter mSortAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SortDetailFragment mSortDetailFragment;

    private int targetPosition;//点击左边某一个具体的item的位置
    private boolean isMoved;



    @Override
    protected int getLayoutId() {
        return R.layout.frag_category;
    }
    private ImmersionBar mImmersionBar;
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }
    @Override
    protected void init() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        initView();
        mPresenter = new CategoryPresenter(this);
        initData();
    }

    public void initData() {
        mPresenter.getCategoryData((BaseActivity) mContext);
    }

    //初始化recyclerView
    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        rvSort.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        rvSort.addItemDecoration(decoration);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    //获取到数据
    @Override
    public void getCategoryResult(List<YaoType1> data) {
        if(data!=null)initData(data);
    }
    //初始化数据
    private void initData(List<YaoType1> data) {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        yaoType1s= (ArrayList<YaoType1>) data;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < yaoType1s.size(); i++) {
            list.add(yaoType1s.get(i).catname);
        }
        mSortAdapter = new SortAdapter(mContext, list, new SortAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mSortDetailFragment != null) {
                    isMoved = true;
                    targetPosition = position;
                    setChecked(position, true);
                }
            }
        });
        rvSort.setAdapter(mSortAdapter);
        createFragment();
    }
    private void createFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("right", yaoType1s);
        mSortDetailFragment.setArguments(bundle);
        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.lin_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }
    private void setChecked(int position, boolean isLeft) {
        Log.d("p-------->", String.valueOf(position));
        if (isLeft) {
            mSortAdapter.setCheckedPosition(position);
            //此处的位置需要根据每个分类的集合来进行计算
            int count = 0;
            for (int i = 0; i < position; i++) {
                count += yaoType1s.get(i).child.size();
            }
            count += position;
            mSortDetailFragment.setData(count);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(targetPosition));//凡是点击左边，将左边点击的位置作为当前的tag
        } else {
            if (isMoved) {
                isMoved = false;
            } else
                mSortAdapter.setCheckedPosition(position);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));//如果是滑动右边联动左边，则按照右边传过来的位置作为tag

        }
        moveToCenter(position);

    }
    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = rvSort.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - rvSort.getHeight() / 2);
            rvSort.smoothScrollBy(0, y);
        }

    }

    @Override
    public void check(int position, boolean isScroll) {
        setChecked(position, isScroll);
    }
    @OnClick({R.id.ll_search,R.id.rl_scan})
    public void click(View v){
        switch (v.getId()){
            case R.id.ll_search://搜索
                SearchActivity.startActivity(mContext,"");
                break;
            case R.id.rl_scan://扫描
                //打开二维码扫描界面
                if(CommonUtil.isCameraCanUse()){
                    CaptureActivity.startActivity(mContext);
                }else{

                    Intent intent = new Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + mContext.getPackageName()));
                    startActivity(intent);
//                    Toast.makeText(mContext,"请打开此应用的摄像头权限！",Toast.LENGTH_SHORT).show();
//                    ConfirmDialog confirmDialog=new ConfirmDialog(mContext);
//                    confirmDialog.setTitle("请打开此应用的摄像头权限").setContent("立即前往?").setOk("设置").setcancle("取消");
//                    confirmDialog.builder().show();
//                    confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
//                        @Override
//                        public void ok() {
//                            Intent intent = new Intent(
//                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                            intent.setData(Uri.parse("package:" + mContext.getPackageName()));
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void cancle() {
//
//                        }
//                    });
                }
                break;
        }
    }
}
