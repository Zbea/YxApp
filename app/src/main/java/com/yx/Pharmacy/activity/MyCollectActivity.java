package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.MyCollectAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.AddCartIDialog;
import com.yx.Pharmacy.dialog.ConfirmDialog;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.presenter.MyCollectPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IMyCollectView;
import com.yx.Pharmacy.widget.LoadingLayout;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的收藏（接口无分页）
 */
public  class MyCollectActivity extends BaseActivity implements IMyCollectView, SwipeRefreshLayout.OnRefreshListener, LoadingLayout.OnReloadListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    @BindView(R.id.iv_more)
    ImageView iv_more;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.loadinglayout)
    LoadingLayout mLoadingLayout;
    private MyCollectAdapter mAdapter;
    private List<DrugModel>drugModels=new ArrayList<>();

    private int cartCount;
    private DrugModel itemModel;
    private int type;

    private MyCollectPresenter mPresenter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyCollectActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        initView();
        initData();

    }
    private void initView() {
        tv_title.setText("我的收藏");
        rl_more.setVisibility(View.GONE);
        iv_more.setImageResource(R.drawable.dblsyicon);
        mPresenter = new MyCollectPresenter(this);

        int itemDecoration = DensityUtils.dp2px(this, 1);
        recyclerview.addItemDecoration(new SpacesItemDecoration(itemDecoration, itemDecoration));
        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new MyCollectAdapter(this, R.layout.item_my_collect);
        recyclerview.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        mLoadingLayout.setOnReloadListener(this);
        mAdapter.setListener(new MyCollectAdapter.AddListener() {
            @Override
            public void addCart(DrugModel item, ImageView imageView) {
                if (item!=null)
                {
                    itemModel=item;
                    cartCount=DensityUtils.parseInt(item.minimum);

                    if (item.getType()==1||item.getType()==2) { //特价商品特殊处理
                        showComfirmDialog();
                    } else {
                        if (!item.productLimit) {
                            showAddDialog(0,item);
                        }
                        else
                        {
                            getShortToastByString("商品已达限购");
                        }
                    }
                }
            }

            @Override
            public void cancel(int position, DrugModel model) {
                mPresenter.cancleCollect(MyCollectActivity.this,model.getItemid(),position);
            }

            @Override
            public void click(DrugModel model) {
                ProductDetailActivity.startActivity(mContext,model.getItemid()+"");
            }
        });



    }
    private void initData() {
        mPresenter.getCollectList(this);
    }

    private void setEmptyView()
    {
        mLoadingLayout.setStatus(LoadingLayout.Empty);
        mLoadingLayout.setEmptyImage(R.drawable.zwtjmdwk);
        mLoadingLayout.setEmptyText("暂无收藏商品");
        mLoadingLayout.setEmptyReloadBtnVisible(false);
    }

    private void showAddDialog(int type,DrugModel item) {
        if (item == null) return;
        AddCartIDialog addCartIDialog=new AddCartIDialog(this,item,type);
        addCartIDialog.setDialogClickListener(new AddCartIDialog.DialogClickListener() {
            @Override
            public void ok(int count) {
                L.i("count:"+count);
                cartCount=count;
                setRefreshMax();
            }
        });
        addCartIDialog.builder().show();

    }

    /**
     * 添加成功后刷新商品数量变化
     */
    private void setRefreshMax()
    {
        if (type==1)
        {
            itemModel.flashmax =(DensityUtils.parseDouble(itemModel.flashmax)  - cartCount)+"";
            if ((DensityUtils.parseDouble(itemModel.flashmax)<=1))
            {
                itemModel.flashLimit=true;
            }
        }
        else
        {
            itemModel.max = itemModel.max - cartCount;
            if ((itemModel.max<=1))
            {
                itemModel.productLimit=true;
            }
        }
        if (itemModel.getType()==1||itemModel.getType()==2)
        {
            if (itemModel.max <= 0&&DensityUtils.parseDouble(itemModel.flashmax)<=1) {
                itemModel.setQuehuo(true);
            }
        }
        else
        {
            if (itemModel.max <= 0) {
                itemModel.setQuehuo(true);
            }
        }
        List<DrugModel> lists=mAdapter.getData();
        for (int i = 0; i <lists.size(); i++) {
            DrugModel drugModel=lists.get(i);
            if (drugModel.getItemid()==itemModel.getItemid())
            {
                mAdapter.getData().get(i).setQuehuo(itemModel.isQuehuo());
                mAdapter.getData().get(i).max=itemModel.max;
                mAdapter.getData().get(i).flashmax=itemModel.flashmax;
                mAdapter.getData().get(i).flashLimit=itemModel.flashLimit;
                mAdapter.getData().get(i).productLimit=itemModel.productLimit;
                mAdapter.notifyItemChanged(i);
                return;
            }
        }
    }

    private void showComfirmDialog() {
        ConfirmDialog confirmDialog = new ConfirmDialog(this);
        confirmDialog.setTitle("温馨提示").setContent("当前商品为限时抢购商品").setcancle("原价购买").setOk(TextUtils.equals(""+itemModel.getType(), "1")?"秒杀购买":"特价购买").builder().show();
        ;
        confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
            @Override
            public void ok() {//第一次特价购买，不需要传是否覆盖
                confirmDialog.cancle();
                if (!itemModel.flashLimit) {
                    type=1;
                    showAddDialog(1,itemModel);
                } else {
                    getShortToastByString("购买已达上限");
                }
            }

            @Override
            public void cancle() {//原价购买(加入购物车)
                confirmDialog.cancle();
                if (!itemModel.productLimit) {
                    type=0;
                    showAddDialog(0,itemModel);
                } else {
                    getShortToastByString("购买已达上限");
                }
            }
        });
    }

    @Override
    public void getCollectList(List<DrugModel> data) {
        if(data!=null&&data.size()>0){
            mLoadingLayout.setStatus(LoadingLayout.Success);
            mAdapter.setNewData(data);
        }else {
            setEmptyView();
            swipeRefreshLayout.setEnabled(false);
        }
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void cancelSuccess(int position) {
        mAdapter.remove(position);
        if (mAdapter.getData().size()==0)
        {
            setEmptyView();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getCollectList(this);
    }

    @Override
    public void onReload(View v) {
        mPresenter.getCollectList(this);
        swipeRefreshLayout.setEnabled(true);
    }
    @OnClick({R.id.rl_back})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }
}
