package com.yx.Pharmacy.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ProductItemAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.AddCartItemDialog;
import com.yx.Pharmacy.dialog.ConfirmDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.ProductItemPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SelectStoreUtil;
import com.yx.Pharmacy.view.IProductItemView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yx.Pharmacy.activity.LoginActivity.START_LOGIN_RESULT;

/**
 * Author: Zbea
 * Date: 2019/1/5 16:01
 * Description: 通用产品列表
 */
public class ProductItemActivity extends BaseActivity implements ProductItemAdapter.AddListener, IProductItemView
{

    private static final int TYPE_zonghe=3;
    private static final int TYPE_jiage=1;
    private static final int TYPE_xiaoliang=2;
    private int curType=3;//当前选中类型
    private boolean isUp=false;//true升序  false降序
    private int page=1;//当前页码

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

    private ProductItemPresenter mPresenter;

    private ProductItemAdapter mAdapter;
    private RecyclerView recyclerview_category;
    private List<DrugModel>drugMdels=new ArrayList<>();

    private RelativeLayout rl_amin_window;
    private ImageView iv_shopping_car;
    private TextView tv_num;
    private String itemId;
    private String itemName;
    private int type;

    @BindView(R.id.tv_title)
    TextView tv_title;

    public static final String ITEM_TYPE="itemType";
    public static final String ITEM_ID="itemId";
    public static final String ITEM_NAME="itemName";

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;
    private SelectStoreUtil mSelectStoreUtil;
    private int cartCount=1;
    private DrugModel itemModel;


    public static void startActivity(Context context,int type,String catid,String catname) {
        Intent intent = new Intent(context, ProductItemActivity.class);
        intent.putExtra(ITEM_ID,catid);
        intent.putExtra(ITEM_TYPE,type);
        intent.putExtra(ITEM_NAME,catname);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_item;
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
        mPresenter = new ProductItemPresenter(this);
        initView();
        mPresenter.getProductList(this,page,type, itemId,curType,isUp,true);
        mPresenter.getShopcarNum(this);
    }


    private void initView() {
        itemId =getIntent().getStringExtra(ITEM_ID);
        itemName =getIntent().getStringExtra(ITEM_NAME);
        type=getIntent().getIntExtra(ITEM_TYPE,0);
        if(!TextUtils.isEmpty(itemName))tv_title.setText(itemName);
        rl_amin_window= findViewById(R.id.rl_amin_window);
        iv_shopping_car= findViewById(R.id.iv_shopping_car);
        tv_num= findViewById(R.id.tv_num);
        recyclerview_category=  findViewById(R.id.recyclerview_category);


        int itemDecoration = DensityUtils.dp2px(this, 1);
        recyclerview_category.addItemDecoration(new SpacesItemDecoration(itemDecoration, itemDecoration));
        recyclerview_category.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new ProductItemAdapter(this, R.layout.item_product);
        recyclerview_category.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                mPresenter.getProductList(ProductItemActivity.this,page,type,itemId,curType,isUp,true);
            }
        });
        addListener();


    }

    private void addListener() {

        mAdapter.setListener(this);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailActivity.startActivity(ProductItemActivity.this,String.valueOf(mAdapter.getData().get(position).getItemid()));
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if(mAdapter.getData().size()>=Constants.PAGESIZE){
                    initNestPage();
                }else {
                    mAdapter.loadMoreEnd();
                }

            }
        },recyclerview_category);
    }


    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    //添加购物车动画实现
    @Override
    public void addCart(DrugModel item, ImageView imgview) {
        if (TextUtils.isEmpty(NetUtil.getToken())) {
            LoginActivity.startActivity(ProductItemActivity.this,1);
            return;
        }else if(TextUtils.isEmpty(NetUtil.getStoreid())){
            if (mSelectStoreUtil!=null) {
                mSelectStoreUtil.loadMyShop(ProductItemActivity.this,true);
                return;
            }else {
                mSelectStoreUtil = new SelectStoreUtil(ProductItemActivity.this, (MyShopModel myShopModel) -> {
                    page = 1;
                    mPresenter.getProductList(this,page,type, itemId,curType,isUp,true);
                });
            }
            return;
        }
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

    private void showAddDialog(int type,DrugModel item) {
        if (item == null) return;
        AddCartItemDialog addCartItemDialog =new AddCartItemDialog(mContext,item,type);
        addCartItemDialog.setDialogClickListener(new AddCartItemDialog.DialogClickListener() {
            @Override
            public void ok(int count) {
                L.i("count:"+count);
                cartCount=count;
                setRefreshMax();
                mPresenter.getShopcarNum(ProductItemActivity.this);
            }
        });
        addCartItemDialog.builder().show();

    }

    private void showComfirmDialog() {
        ConfirmDialog confirmDialog = new ConfirmDialog(mContext);
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

    @OnClick({R.id.rl_back, R.id.ll_zonghe, R.id.ll_price, R.id.ll_xiaoliang,R.id.tv_reload,R.id.iv_shopping_car})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_zonghe://综合
                if(curType!=TYPE_zonghe) {//切换类型
                    curType = TYPE_zonghe;
                    isUp = false;
                    tv_zonghe.setTextColor(getResources().getColor(R.color.color_main));
                    tv_price.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_price.setImageResource(R.drawable.gray_down);
                    tv_xiaoliang.setTextColor(getResources().getColor(R.color.color_606060));
                    iv_xiaoliang.setImageResource(R.drawable.gray_down);
                }
                page=1;
                mPresenter.getProductList(this,page,type, itemId,curType,isUp,true);
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
                mPresenter.getProductList(this,page,type, itemId,curType,isUp,true);
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
                mPresenter.getProductList(this,page,type, itemId,curType,isUp,true);
                break;
            case R.id.iv_shopping_car://购物车
                ProductCartActivity.startActivity(this);
                break;
            case R.id.tv_reload://重新加载
                List<DrugModel>models=new ArrayList<>();
                mAdapter.setNewData(models);
                showNornaml();
                page=1;
                mPresenter.getProductList(ProductItemActivity.this,page,type, itemId,curType,isUp,true);
                break;
        }
    }
    //加载下一页
    private void initNestPage() {
        mPresenter.getProductList(ProductItemActivity.this,page+1,type, itemId,curType,isUp,false);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode==START_LOGIN_RESULT) {
            mSelectStoreUtil = new SelectStoreUtil(this, (MyShopModel myShopModel) -> {
                page = 1;
                mPresenter.getProductList(this,page,type, itemId,curType,isUp,true);
            });
        }
    }

}
