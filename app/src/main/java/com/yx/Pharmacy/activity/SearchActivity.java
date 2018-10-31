package com.yx.Pharmacy.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.CategoryGridAdapter;
import com.yx.Pharmacy.adapter.DrugTagAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.manage.ProductMaxManage;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.TagModel;
import com.yx.Pharmacy.presenter.SearchPresenter;
import com.yx.Pharmacy.util.ComMethodsUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.view.ISearchView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;
import com.yx.Pharmacy.widget.flowtag.FlowTagLayout;
import com.yx.Pharmacy.widget.flowtag.OnTagSelectListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements OnTagSelectListener, ISearchView, CategoryGridAdapter.AddListener {

    @BindView(R.id.flowlayout_history)
    FlowTagLayout flowlayout_history;
    private DrugTagAdapter historyAdapter;
    private List<TagModel>historyModels=new ArrayList<>();
    private List<TagModel>hotModels=new ArrayList<>();

    @BindView(R.id.flowlayout_hot)
    FlowTagLayout flowlayout_hot;
    private DrugTagAdapter hotAdapter;

    private SearchPresenter mPresenter;

    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.ll_tuijian)
    LinearLayout ll_tuijian;
    @BindView(R.id.ll_search_result)
    LinearLayout ll_search_result;


    private static final int TYPE_zonghe=3;
    private static final int TYPE_jiage=1;
    private static final int TYPE_xiaoliang=2;
    private int curType=3;//当前选中类型
    private boolean isUp=false;//true升序  false降序
    private int page=1;//当前页码
    private int curLayout=0;
    private static final int mode_linear=0;
    private static final int mode_grid=1;


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
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    private CategoryGridAdapter mAdapter;
    private List<DrugModel>drugModels=new ArrayList<>();


    @BindView(R.id.rl_amin_window)
    RelativeLayout rl_amin_window;
    @BindView(R.id.iv_shopping_car)
    ImageView iv_shopping_car;
    @BindView(R.id.tv_num)
    TextView tv_num;

    private String keyword;
    private int type=0;

    public static void startActivity(Activity activity,String keyword) {
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("keyword",keyword);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity,String keyword,String s,int type) {
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("keyword",keyword);
        intent.putExtra("title",s);
        intent.putExtra("type",type);
        activity.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {

        CartCountManage.newInstance().setCartCountManageListener(new CartCountManage.CartCountManageListener() {
            @Override
            public void onRefresh(int max) {
                getShopCarNum(""+max);

            }
        });

        ImmersionBarUtil.setBarColor(R.color.color_main,this,false);
        keyword=getIntent().getStringExtra("keyword");
        type=getIntent().getIntExtra("type",0);
        mPresenter = new SearchPresenter(this);
        initView();
        initData();
    }
    private void setGridLayout() {
        curLayout=mode_grid;
        iv_layout_mode.setImageResource(R.drawable.twck);
        final GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new CategoryGridAdapter(this, R.layout.item_category_detail,drugModels,1);
        recyclerview.setAdapter(mAdapter);
        addListener();
    }
    private void setLinearLayout() {
        curLayout=mode_linear;
        iv_layout_mode.setImageResource(R.drawable.dtmock);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new CategoryGridAdapter(this, R.layout.item_category_linear,drugModels,0);
        recyclerview.setAdapter(mAdapter);
        addListener();
    }
    private void addListener() {
        mAdapter.setListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailActivity.startActivity(SearchActivity.this,String.valueOf(mAdapter.getData().get(position).getItemid()));
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
        },recyclerview);
    }

    private void initNestPage() {
        mPresenter.getSearchResult(SearchActivity.this,page+1,edit_search.getText().toString(),type,curType,isUp,false);
    }

    private void initView() {
        //历史
        historyAdapter=new DrugTagAdapter(this);
        flowlayout_history.setAdapter(historyAdapter);
        flowlayout_history.setOnTagSelectListener(this);
        //热门
        hotAdapter= new DrugTagAdapter(this);
        flowlayout_hot.setAdapter(hotAdapter);
        flowlayout_hot.setOnTagSelectListener(this);

        //监听搜索按钮
        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //TODO 点击搜索
                if (actionId== EditorInfo.IME_ACTION_SEARCH) {
                    if(!TextUtils.isEmpty(edit_search.getText().toString().trim())){
                        ComMethodsUtil.hideSoftKeyBoard(SearchActivity.this);
                        //TODO 搜索
                        ll_tuijian.setVisibility(View.GONE);
                        ll_search_result.setVisibility(View.VISIBLE);
                        page=1;
                        mPresenter.getSearchResult(SearchActivity.this,page,edit_search.getText().toString(),type,curType,isUp,true);
                        addSearchHistory(edit_search.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        if(curLayout==mode_grid){
            setGridLayout();
        }else {
            setLinearLayout();
        }
        int itemDecoration = DensityUtils.dp2px(this, 2);
        recyclerview.addItemDecoration(new SpacesItemDecoration(itemDecoration, itemDecoration));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 刷新
                page=1;
                mPresenter.getSearchResult(SearchActivity.this,page,edit_search.getText().toString(),type,3,false,true);
            }
        });
        if(!TextUtils.isEmpty(keyword)){
            //TODO 搜索
            ll_tuijian.setVisibility(View.GONE);
            ll_search_result.setVisibility(View.VISIBLE);
            page=1;
            edit_search.setText(keyword);
            mPresenter.getSearchResult(SearchActivity.this,page,keyword,type,curType,isUp,true);
        }
    }

    private void initData() {
        mPresenter.getHotSearchList(this);
        //初始化搜索历史
        historyModels=getSearchHistory();
    }

    @Override
    public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList, int position) {
        String name=null;
        switch (parent.getId()){
            case R.id.flowlayout_history:
                name=historyModels.get(position).getName();
                break;
            case R.id.flowlayout_hot:
                name=hotModels.get(position).getName();
                break;
        }
        edit_search.setText(name);
        ComMethodsUtil.hideSoftKeyBoard(SearchActivity.this);
        //TODO 搜索
        ll_tuijian.setVisibility(View.GONE);
        ll_search_result.setVisibility(View.VISIBLE);
        page=1;
        mPresenter.getSearchResult(SearchActivity.this,page,name,type,curType,isUp,true);
    }


    @Override
    public void getHotSearchList(List<TagModel> data) {//获取到热门数据
        historyAdapter.onlyAddAll(historyModels);
        hotModels=data;
        hotAdapter.onlyAddAll(hotModels);
    }

    @Override
    public void getSearchResultList(List<DrugModel> data) {//获取搜索结果
        ll_nodata.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        mAdapter.setNewData(data);
        if (data.size()< Constants.PAGESIZE){
            mAdapter.loadMoreEnd();
        }
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addSearchResultList(List<DrugModel> data) {
        ll_nodata.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        mAdapter.addData(data);
        if (data.size()<Constants.PAGESIZE){
            mAdapter.loadMoreEnd();
        }else {
            mAdapter.loadMoreComplete();
        }
        page++;
    }
    @Override
    public void noData() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddResult(AddShopCartModel data, DrugModel item, ImageView imgview) {
        startAddAnim(item,imgview,data.count);
    }

    @Override
    public void getShopCarNum(String carnum) {
        CartCountManage.newInstance().setCount(Integer.parseInt(carnum));
        tv_num.setVisibility(Integer.valueOf(carnum)==0?View.GONE:View.VISIBLE);
        tv_num.setText(Integer.valueOf(carnum)>99?"99+":carnum+"");
    }

    @Override
    public void noErrorData() {
        mAdapter.loadMoreEnd();
    }

    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    //添加购物车动画实现
    @Override
    public void addCart(DrugModel item, ImageView imageView) {//加入购物车
        mPresenter.addCartProduct(this,item.getItemid()+"",item,imageView);
    }

    @Override
    public void productArrived(int itemid) {//到货提醒
        mPresenter.productArrive(this,itemid+"");
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

    @OnClick({R.id.ll_zonghe, R.id.ll_price,
              R.id.ll_xiaoliang,R.id.ll_change_layout,R.id.iv_shopping_car,
              R.id.tv_clear_history,R.id.tv_cancel})
    public void click(View v){
        switch (v.getId()){
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
                mPresenter.getSearchResult(SearchActivity.this,page,edit_search.getText().toString(),type,curType,isUp,true);
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
                mPresenter.getSearchResult(SearchActivity.this,page,edit_search.getText().toString(),type,curType,isUp,true);
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
                mPresenter.getSearchResult(SearchActivity.this,page,edit_search.getText().toString(),type,curType,isUp,true);
                break;
            case R.id.ll_change_layout://切换布局
                if(mAdapter!=null)drugModels=mAdapter.getData();
                if(curLayout!=mode_grid){
                    setGridLayout();
                }else {
                    setLinearLayout();
                }
                break;
            case R.id.tv_clear_history://清除历史
                removeHistory();
                flowlayout_history.setVisibility(View.GONE);
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.iv_shopping_car:
                ProductCartActivity.startActivity(this);
                break;
        }
    }
    //获取搜索历史
    private List<TagModel> getSearchHistory(){
        List<TagModel> list=new ArrayList<TagModel>();
        String search_history = SPUtil.getString(this,Constants.SEARCH_HISTORY);
        if (TextUtils.isEmpty(search_history)) {
            return list;
        }
        Gson gson = new Gson();
        list = gson.fromJson(search_history, new TypeToken<List<TagModel>>() {}.getType());
        return list;
    }
    private void addSearchHistory(String content){
        List<TagModel>list=getSearchHistory();
        if(list.size()>1){
            Iterator<TagModel> iter=list.iterator();
            while (iter.hasNext()){
                if(iter.next().getName().equals(content)){
                    iter.remove();
                }
            }
        }
        TagModel model=new TagModel();
        model.setName(content);
        list.add(0,model);
        Gson gson=new Gson();
        String search_history =gson.toJson(list);
        SPUtil.putString(this,Constants.SEARCH_HISTORY,search_history);
    }
    private void removeHistory(){
        SPUtil.delete(this,Constants.SEARCH_HISTORY);
    }
}
