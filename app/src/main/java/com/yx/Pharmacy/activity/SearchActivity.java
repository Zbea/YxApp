package com.yx.Pharmacy.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ProductCategoryGridAdapter;
import com.yx.Pharmacy.adapter.DrugTagAdapter;
import com.yx.Pharmacy.adapter.SearchAutoAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.SearchAutoModel;
import com.yx.Pharmacy.model.TagModel;
import com.yx.Pharmacy.presenter.SearchPresenter;
import com.yx.Pharmacy.util.ComMethodsUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
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

public class SearchActivity extends BaseActivity implements OnTagSelectListener, ISearchView{

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


    private static final int TYPE_zonghe=3;
    private static final int TYPE_jiage=1;
    private static final int TYPE_xiaoliang=2;
    private int curType=3;//当前选中类型
    private boolean isUp=false;//true升序  false降序
    private int page=1;//当前页码
    private int curLayout=0;
    private static final int mode_linear=0;
    private static final int mode_grid=1;

    @BindView(R.id.iv_back)
    ImageView  ivBack;






    private String keyword;
    private SearchAutoAdapter searchAutoAdapter;
    private  PopupWindow popupWindow;
    private boolean isShowPop=true;

    public static void startActivity(Activity activity,String keyword) {
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("keyword",keyword);
        activity.startActivity(intent);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {


        ImmersionBarUtil.setBarColor(R.color.color_main,this,false);
        keyword=getIntent().getStringExtra("keyword");
        mPresenter = new SearchPresenter(this);
        initView();
        initData();
    }

    private void initView() {
        //历史
        isShowPop=true;
        historyAdapter=new DrugTagAdapter(this);
        flowlayout_history.setAdapter(historyAdapter);
        flowlayout_history.setOnTagSelectListener(this);
        //热门
        hotAdapter= new DrugTagAdapter(this);
        flowlayout_hot.setAdapter(hotAdapter);
        flowlayout_hot.setOnTagSelectListener(this);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0)
                {
                    if (popupWindow!=null&&popupWindow.isShowing())
                        popupWindow.dismiss();
                }
                else
                {
                    mPresenter.productSearchAuto(SearchActivity.this,s.toString());
                }
            }
        });

        //监听搜索按钮
        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH) {
                    search(edit_search.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });

        if (!TextUtils.isEmpty(keyword)) search(keyword);
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
        edit_search.setSelection(name.length());
        search(name);

    }

    /**
     * 搜索
     * @param search
     */
    private void search(String search)
    {
        if (popupWindow!=null&&popupWindow.isShowing())
        {
            popupWindow.dismiss();
        }
        isShowPop=false;
        ProductItemActivity.startActivity(mContext,1,search+"",search);
        if(!TextUtils.isEmpty(search)){
            ComMethodsUtil.hideSoftKeyBoard(SearchActivity.this);
            addSearchHistory(search);
        }
    }


    @Override
    public void getHotSearchList(List<TagModel> data) {//获取到热门数据
        historyAdapter.onlyAddAll(historyModels);
        hotModels=data;
        hotAdapter.onlyAddAll(hotModels);
    }

    @Override
    public void getAutoSearchList(List<SearchAutoModel> data) {
        if (data!=null)
        {
            if (data.size()>0)
            {
                L.i("isShowPop:"+isShowPop);
                showAuto(data);
            }
            else
            {
                if (popupWindow!=null) popupWindow.dismiss();
            }
        }
        else
        {
            if (popupWindow!=null) popupWindow.dismiss();
        }
    }

    private void showAuto(List<SearchAutoModel> data)
    {
        if (popupWindow==null)
        {
            View layoutOfList = LayoutInflater.from(mContext).inflate(R.layout.popwindow_search_auto, (ViewGroup)null);
            RecyclerView autoView = layoutOfList.findViewById(R.id.recyclerView);
            autoView.setLayoutManager(new LinearLayoutManager(this));
            int itemDecoration = 1;
            autoView.addItemDecoration(new SpacesItemDecoration(itemDecoration, itemDecoration));
            searchAutoAdapter=new SearchAutoAdapter(R.layout.item_search_auto,data);
            autoView.setAdapter(searchAutoAdapter);
            searchAutoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String s=searchAutoAdapter.getData().get(position).title;
                    edit_search.setText(s);
                    edit_search.setSelection(s.length());
                    search(s);
                }
            });
            ImageView tvCancel = layoutOfList.findViewById(R.id.iv_close);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupWindow!=null) popupWindow.dismiss();
                }
            });

            popupWindow = new PopupWindow(this);
            popupWindow.setWidth(DensityUtils.getScreenWidth());
            popupWindow.setContentView(layoutOfList);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            popupWindow.setOutsideTouchable(false);
            popupWindow.setFocusable(false);
            popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            if (isShowPop)
            {
                popupWindow.showAsDropDown(edit_search,0,20);
            }
            else
            {
                isShowPop=true;
            }
        }
        else
        {
            searchAutoAdapter.getData().clear();
            searchAutoAdapter.setNewData(data);
            if (!popupWindow.isShowing())
            {
                if (isShowPop)
                {
                    popupWindow.showAsDropDown(edit_search,0,20);
                }
                else
                {
                    isShowPop=true;
                }
            }
        }

    }









    @OnClick({R.id.iv_back, R.id.tv_clear_history,R.id.tv_cancel})
    public void click(View v){
        switch (v.getId()){
            case R.id.iv_back://切换布局
                ComMethodsUtil.hideSoftKeyBoard(SearchActivity.this);
                finish();
                break;
            case R.id.tv_clear_history://清除历史
                removeHistory();
                flowlayout_history.setVisibility(View.GONE);
                break;
            case R.id.tv_cancel:
                search(edit_search.getText().toString());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ComMethodsUtil.hideSoftKeyBoard(SearchActivity.this);
    }
}
