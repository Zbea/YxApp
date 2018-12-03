package com.yx.Pharmacy.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ProductCategoryGridAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ConfirmDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.CategoryDetailPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SelectStoreUtil;
import com.yx.Pharmacy.view.ICategoryDetailView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yx.Pharmacy.activity.LoginActivity.START_LOGIN_RESULT;

public class ProductCategoryDetailActivity extends BaseActivity implements ProductCategoryGridAdapter.AddListener, ICategoryDetailView {

    private static final int TYPE_zonghe=3;
    private static final int TYPE_jiage=1;
    private static final int TYPE_xiaoliang=2;
    private int curType=3;//当前选中类型
    private boolean isUp=false;//true升序  false降序
    private int page=1;//当前页码
    private static final int mode_linear=0;
    private static final int mode_grid=1;
    private int curLayout=0;//默认mode_linear


    @BindView(R.id.tv_zonghe)
    TextView  tv_zonghe;
    @BindView(R.id.iv_zonghe)
    ImageView iv_zonghe;
    @BindView(R.id.tv_price)
    TextView  tv_price;
    @BindView(R.id.iv_price)
    ImageView iv_price;
    @BindView(R.id.tv_xiaoliang)
    TextView  tv_xiaoliang;
    @BindView(R.id.iv_xiaoliang)
    ImageView iv_xiaoliang;
    @BindView(R.id.iv_layout_mode)
    ImageView iv_layout_mode;


    private CategoryDetailPresenter mPresenter;

    ProductCategoryGridAdapter mAdapter;
    private RecyclerView recyclerview_category;
    private List<DrugModel>drugModels=new ArrayList<>();

    private RelativeLayout rl_amin_window;
    private ImageView iv_shopping_car;
    private TextView tv_num;
    private int catid;
    private String catname;

    @BindView(R.id.tv_title)
    TextView tv_title;

    public static final String CAT_ID="catid";
    public static final String CAT_NAME="catname";

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;
    private SelectStoreUtil mSelectStoreUtil;


    public static void startActivity(Context context,int catid,String catname) {
        Intent intent = new Intent(context, ProductCategoryDetailActivity.class);
        intent.putExtra(CAT_ID,catid);
        intent.putExtra(CAT_NAME,catname);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_category_detail;
    }

    @Override
    protected void init() {
        CartCountManage.newInstance().setCartCountManageListener(new CartCountManage.CartCountManageListener() {
            @Override
            public void onRefresh(int max) {
                getShopCarNum(""+max);
            }
        });
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mPresenter = new CategoryDetailPresenter(this);
        initView();
        mPresenter.getProductList(this,page,catid,curType,isUp,true);
        mPresenter.getShopcarNum(this);
    }


    private void initView() {
        catid=getIntent().getIntExtra(CAT_ID,-1);
        catname=getIntent().getStringExtra(CAT_NAME);
        if(!TextUtils.isEmpty(catname))tv_title.setText(catname);
        Log.e("kid","catid==="+catid);
        rl_amin_window= (RelativeLayout) findViewById(R.id.rl_amin_window);
        iv_shopping_car= (ImageView) findViewById(R.id.iv_shopping_car);
        tv_num= (TextView) findViewById(R.id.tv_num);
        recyclerview_category= (RecyclerView) findViewById(R.id.recyclerview_category);

        if(curLayout==mode_grid){
            setGridLayout();
        }else {
            setLinearLayout();
        }

        int itemDecoration = DensityUtils.dp2px(this, 1);
        recyclerview_category.addItemDecoration(new SpacesItemDecoration(itemDecoration, itemDecoration));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 刷新
                page=1;
                mPresenter.getProductList(ProductCategoryDetailActivity.this,page,catid,curType,isUp,true);
            }
        });
        addListener();


    }

    private void addListener() {
        mAdapter.setListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailActivity.startActivity(ProductCategoryDetailActivity.this,String.valueOf(mAdapter.getData().get(position).getItemid()));
            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //TODO 加载下一页
                if(mAdapter.getData().size()>=Constants.PAGESIZE){
                    initNestPage();
                }else {
                    mAdapter.loadMoreEnd();
                }

            }
        },recyclerview_category);
    }
    //因为gridLayout一个item的高度是302，linearLayout一个item的高度是160，差不多是两倍，故不计算高度去ScrollBy，效果也差不多
    private void setGridLayout() {
        curLayout=mode_grid;
        iv_layout_mode.setImageResource(R.drawable.twck);
        final GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerview_category.setLayoutManager(layoutManager);
        mAdapter=new ProductCategoryGridAdapter(this, R.layout.item_category_detail,drugModels,1);
        recyclerview_category.setAdapter(mAdapter);
        addListener();
    }
    private void setLinearLayout() {
        curLayout=mode_linear;
        iv_layout_mode.setImageResource(R.drawable.dtmock);
        recyclerview_category.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new ProductCategoryGridAdapter(this, R.layout.item_category_linear,drugModels,0);
        recyclerview_category.setAdapter(mAdapter);
        addListener();
    }


    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    //添加购物车动画实现
    @Override
    public void addCart(DrugModel item, ImageView imgview) {
        if (TextUtils.isEmpty(NetUtil.getToken())) {
            LoginActivity.startActivity(ProductCategoryDetailActivity.this,1);
            return;
        }else if(TextUtils.isEmpty(NetUtil.getStoreid())){
            if (mSelectStoreUtil!=null) {
                mSelectStoreUtil.loadMyShop(ProductCategoryDetailActivity.this,true);
                return;
            }else {
                mSelectStoreUtil = new SelectStoreUtil(ProductCategoryDetailActivity.this, () -> {
                    page = 1;
                    mPresenter.getProductList(this,page,catid,curType,isUp,true);
                });
            }
            return;
        }
        if (item.getType()==1||item.getType()==2)
        {
            showComfirmDialog(item,imgview);
        }
        else
        {
            mPresenter.addCartProduct(this,item.getItemid()+"",item,imgview);
        }

    }

    private void showComfirmDialog(DrugModel item, ImageView imgview) {
        ConfirmDialog confirmDialog = new ConfirmDialog(this);
        confirmDialog.setTitle("温馨提示").setContent("当前商品为限时抢购商品").setcancle("原价购买").setOk(TextUtils.equals(item.getType()+"", "1")?"秒杀购买":"特价购买").builder().show();
        ;
        confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
            @Override
            public void ok() {//特价购买
                confirmDialog.cancle();
                mPresenter.miaoshaBuy(ProductCategoryDetailActivity.this, item.getItemid()+"", "1",item,imgview);
            }

            @Override
            public void cancle() {//原价购买(加入购物车)
                confirmDialog.cancle();
                mPresenter.addCartProduct(ProductCategoryDetailActivity.this,item.getItemid()+"",item,imgview);
            }
        });
    }


    @Override
    public void productArrived(int itemid) {
        mPresenter.productArrive(this,itemid+"");
    }
    @Override
    public void getProductListResult(List<DrugModel> data) {//刷新，获取新数据
        mAdapter.setNewData(data);
        if (data.size()< Constants.PAGESIZE){
            mAdapter.loadMoreEnd();
        }
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }
    private void showNoData() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.VISIBLE);
    }

    private void showErrorPage() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.VISIBLE);
    }
    private void showNornaml(){
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
    @Override
    public void addProductListResult(List<DrugModel> data) {//添加数据
        mAdapter.addData(data);
        if (data.size()<Constants.PAGESIZE){
            mAdapter.loadMoreEnd();
        }else {
            mAdapter.loadMoreComplete();
        }
        page++;
    }

    @Override
    public void showAddResult(AddShopCartModel data, DrugModel item, ImageView imgview) {
        startAddAnim(item,imgview,data.count);
    }

    @Override
    public void getShopCarNum(String carnum) {//获取购物车数量
        CartCountManage.newInstance().setCount(Integer.parseInt(carnum));
        tv_num.setVisibility(Integer.valueOf(carnum)==0?View.GONE:View.VISIBLE);
        tv_num.setText(Integer.valueOf(carnum)>99?"99+":carnum+"");
    }
    @Override
    public void noProductListData() {//无数据
        showNoData();
    }
    @Override
    public void onErrorPage() {
        showErrorPage();
    }

    //开始加入购物车动画
    private void startAddAnim(DrugModel item, ImageView imgview, final String count) {
        int parent[] = new int[2];
        rl_amin_window.getLocationInWindow(parent);
        int startLoc[] = new int[2];
        imgview.getLocationInWindow(startLoc);
        int endLoc[] = new int[2];
        iv_shopping_car.getLocationInWindow(endLoc);
        final ImageView goods = new ImageView(getApplicationContext());
//        goods.setImageDrawable(imgview.getDrawable());
        goods.setImageResource(R.drawable.ydd);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(80, 80);
        rl_amin_window.addView(goods, params);
        float startX = startLoc[0];
        float startY = startLoc[1] - parent[1];
        float toX = endLoc[0] + iv_shopping_car.getWidth() / 3;
//        float toY = endLoc[1] ;
//        float toY = endLoc[1] - iv_shopping_car.getHeight()-goods.getHeight()-DensityUtils.dp2px(this,30);
        float toY = endLoc[1] - parent[1];
        Path path = new Path();
        path.moveTo(startX, startY);
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        mPathMeasure = new PathMeasure(path, false);
        //属性动画实现
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(800);
        // 匀速插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
        valueAnimator.start();

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                tv_num.setVisibility(View.VISIBLE);
                tv_num.setText(Integer.valueOf(count)>99?"99+":count+"");
                rl_amin_window.removeView(goods);
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @OnClick({R.id.rl_back, R.id.ll_zonghe, R.id.ll_price, R.id.ll_xiaoliang,R.id.tv_reload,
            R.id.ll_change_layout,R.id.iv_shopping_car,R.id.rl_search,R.id.rl_scan})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_scan:
                CaptureActivity.startActivity(this);
                break;
            case R.id.rl_search:
                SearchActivity.startActivity(this,"");
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_zonghe://综合
                if(curType!=TYPE_zonghe){//切换类型
                    curType=TYPE_zonghe;
                    isUp=false;
                    tv_zonghe.setTextColor(getResources().getColor(R.color.color_main));
                    iv_zonghe.setImageResource(R.drawable.zi_down);
                    tv_price.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_price.setImageResource(R.drawable.gray_down);
                    tv_xiaoliang.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_xiaoliang.setImageResource(R.drawable.gray_down);
                }else {//非切换类型,要判断当前是升序还是降序
                    if(isUp){
                        isUp=false;
                        tv_zonghe.setTextColor(getResources().getColor(R.color.color_main));
                        iv_zonghe.setImageResource(R.drawable.zi_down);
                    }else {
                        isUp=true;
                        tv_zonghe.setTextColor(getResources().getColor(R.color.color_main));
                        iv_zonghe.setImageResource(R.drawable.zi_up);
                    }
                }
                page=1;
                mPresenter.getProductList(this,page,catid,curType,isUp,true);
                break;
            case R.id.ll_price://价格
                if(curType!=TYPE_jiage){//切换类型
                    curType=TYPE_jiage;
                    isUp=false;
                    tv_zonghe.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_zonghe.setImageResource(R.drawable.gray_down);
                    tv_price.setTextColor(getResources().getColor(R.color.color_main));
                    iv_price.setImageResource(R.drawable.zi_down);
                    tv_xiaoliang.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_xiaoliang.setImageResource(R.drawable.gray_down);
                }else {//非切换类型,要判断当前是升序还是降序
                    if(isUp){
                        isUp=false;
                        tv_price.setTextColor(getResources().getColor(R.color.color_main));
                        iv_price.setImageResource(R.drawable.zi_down);
                    }else {
                        isUp=true;
                        tv_price.setTextColor(getResources().getColor(R.color.color_main));
                        iv_price.setImageResource(R.drawable.zi_up);
                    }
                }
                page=1;
                mPresenter.getProductList(this,page,catid,curType,isUp,true);
                break;
            case R.id.ll_xiaoliang://销量
                if(curType!=TYPE_xiaoliang){//切换类型
                    curType=TYPE_xiaoliang;
                    isUp=false;
                    tv_zonghe.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_zonghe.setImageResource(R.drawable.gray_down);
                    tv_price.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_price.setImageResource(R.drawable.gray_down);
                    tv_xiaoliang.setTextColor(getResources().getColor(R.color.color_main));
                    iv_xiaoliang.setImageResource(R.drawable.zi_down);//
                }else {//非切换类型,要判断当前是升序还是降序
                    if(isUp){
                        isUp=false;
                        tv_xiaoliang.setTextColor(getResources().getColor(R.color.color_main));
                        iv_xiaoliang.setImageResource(R.drawable.zi_down);
                    }else {
                        isUp=true;
                        tv_xiaoliang.setTextColor(getResources().getColor(R.color.color_main));
                        iv_xiaoliang.setImageResource(R.drawable.zi_up);
                    }
                }
                page=1;
                mPresenter.getProductList(this,page,catid,curType,isUp,true);
                break;
            case R.id.ll_change_layout://切换布局
                if(mAdapter!=null)drugModels=mAdapter.getData();
                if(curLayout!=mode_grid){
                    setGridLayout();
                }else {
                    setLinearLayout();
                }
                break;
            case R.id.iv_shopping_car://购物车
                ProductCartActivity.startActivity(this);
                break;
            case R.id.tv_reload://重新加载
                List<DrugModel>models=new ArrayList<>();
                mAdapter.setNewData(models);
                showNornaml();
                page=1;
                mPresenter.getProductList(ProductCategoryDetailActivity.this,page,catid,curType,isUp,true);
                break;
        }
    }
    //加载下一页
    private void initNestPage() {
        mPresenter.getProductList(ProductCategoryDetailActivity.this,page+1,catid,curType,isUp,false);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode==START_LOGIN_RESULT) {
            mSelectStoreUtil = new SelectStoreUtil(this, () -> {
                page = 1;
                mPresenter.getProductList(this,page,catid,curType,isUp,true);
            });
        }
    }

}
