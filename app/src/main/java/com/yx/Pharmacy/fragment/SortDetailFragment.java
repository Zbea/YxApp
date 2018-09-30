package com.yx.Pharmacy.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.CategoryDetailActivity;
import com.yx.Pharmacy.adapter.ClassifyDetailAdapter;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.model.RightBean;
import com.yx.Pharmacy.model.YaoType1;
import com.yx.Pharmacy.model.YaoType2;
import com.yx.Pharmacy.view.CheckListener;
import com.yx.Pharmacy.widget.ItemHeaderDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by KID on 2018/7/14.
 */

public class  SortDetailFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView mRv;
    private ClassifyDetailAdapter mAdapter;
    private GridLayoutManager mManager;
    private List<RightBean> mDatas = new ArrayList<>();

    //联动逻辑
    private ItemHeaderDecoration mDecoration;
    private boolean move = false;
    private int mIndex = 0;
    private CheckListener checkListener;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_sort_detail;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }
    private void initView() {

        mManager = new GridLayoutManager(mContext, 3);
        //通过isTitle的标志来判断是否是title
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mDatas.get(position).isTitle() ? 3 : 1;
            }
        });
        mRv.setLayoutManager(mManager);
        mAdapter = new ClassifyDetailAdapter(mContext, mDatas, new ClassifyDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(mContext, "当前点击的是=="+ mDatas.get(position).getCatname()+"catid=="+
//                        mDatas.get(position).getCatid(), Toast.LENGTH_SHORT).show();
                CategoryDetailActivity.startActivity(mContext,mDatas.get(position).getCatid(),mDatas.get(position).getCatname());
            }
        });

        mRv.setAdapter(mAdapter);
        mDecoration = new ItemHeaderDecoration(mContext, mDatas);
        mRv.addItemDecoration(mDecoration);
        mRv.addOnScrollListener(new RecyclerViewListener());
        mDecoration.setCheckListener(checkListener);

    }
    private void initData() {
        ArrayList<YaoType1> rightList = getArguments().getParcelableArrayList("right");
        for (int i = 0; i < rightList.size(); i++) {
            RightBean head = new RightBean(rightList.get(i).catname);
            //头部设置为true
            head.setTitle(true);
            head.setTitleName(rightList.get(i).catname);
            head.setCatname(rightList.get(i).catname);
            head.setTag(String.valueOf(i));
            head.setCatid(rightList.get(i).catid);
            mDatas.add(head);
            List<YaoType2> categoryTwoArray = rightList.get(i).child;
            for (int j = 0; j < categoryTwoArray.size(); j++) {
                RightBean body = new RightBean(categoryTwoArray.get(j).catname);
                //非头部为false
                body.setTitle(false);
                body.setTag(String.valueOf(i));
                body.setTitleName(rightList.get(i).catname);
                String name = categoryTwoArray.get(j).catname;
                body.setCatname(name);
                body.setCatid(categoryTwoArray.get(j).catid);
                body.setThumb(categoryTwoArray.get(j).thumb);
                mDatas.add(body);
            }

        }

        mAdapter.notifyDataSetChanged();
        mDecoration.setData(mDatas);
    }
    public void setListener(CheckListener listener) {
        this.checkListener = listener;
    }
    //设置选中数据
    public void setData(int n) {
        mIndex = n;
        mRv.stopScroll();
        smoothMoveToPosition(n);
    }
    private void smoothMoveToPosition(int n) {
        int firstItem = mManager.findFirstVisibleItemPosition();
        int lastItem = mManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRv.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRv.getChildAt(n - firstItem).getTop();
            mRv.scrollBy(0, top);
        } else {
            mRv.scrollToPosition(n);
            move = true;
        }
    }
    /**
     * recyclerView滚动监听
     */
    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    mRv.smoothScrollBy(0, top);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    mRv.scrollBy(0, top);
                }
            }
        }
    }
}
