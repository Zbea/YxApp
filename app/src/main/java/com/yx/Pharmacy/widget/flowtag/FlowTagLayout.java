package com.yx.Pharmacy.widget.flowtag;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式标签布局
 * 原理：重写{@link ViewGroup#onMeasure(int, int)}
 * 和{@link ViewGroup#onLayout(boolean, int, int, int, int)}
 * 方法
 * Created by HanHailong on 15/10/19.
 */
public class FlowTagLayout extends ViewGroup {

    private static final String TAG = FlowTagLayout.class.getSimpleName();

    public int maxSelectNum=100;

    public int selectNum=0;


    /**
     * FlowLayout support multi-select
     */
    public static final int FLOW_TAG_CHECKED_MULTI = 2;

    /**
     * Should be used by subclasses to listen to changes in the dataset
     */
    AdapterDataSetObserver mDataSetObserver;

    /**
     * The adapter containing the data to be displayed by this view
     */
    ListAdapter mAdapter;

    /**
     * the tag click event callback
     */
    OnTagClickListener mOnTagClickListener;

    /**
     * the tag select event callback
     */
    OnTagSelectListener mOnTagSelectListener;

    /**
     * 标签流式布局选中模式，默认是不支持选中的
     */
    private int mTagCheckMode = FLOW_TAG_CHECKED_MULTI;

    /**
     * 存储选中的tag
     */
    private SparseBooleanArray mCheckedTagArray = new SparseBooleanArray();

    public FlowTagLayout(Context context) {
        super(context);
    }

    public FlowTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取Padding
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //FlowLayout最终的宽度和高度值
        int resultWidth = 0;
        int resultHeight = 0;

        //测量时每一行的宽度
        int lineWidth = 0;
        //测量时每一行的高度，加起来就是FlowLayout的高度
        int lineHeight = 0;

        //遍历每个子元素
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);
            //测量每一个子view的宽和高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int realChildWidth = childWidth + mlp.leftMargin + mlp.rightMargin;
            int realChildHeight = childHeight + mlp.topMargin + mlp.bottomMargin;

            //如果当前一行的宽度加上要加入的子view的宽度大于父容器给的宽度，就换行
            if ((lineWidth + realChildWidth) > sizeWidth) {
                //换行
                resultWidth = Math.max(lineWidth, realChildWidth);
                resultHeight += realChildHeight;
                //换行了，lineWidth和lineHeight重新算
                lineWidth = realChildWidth;
                lineHeight = realChildHeight;
            } else {
                //不换行，直接相加
                lineWidth += realChildWidth;
                //每一行的高度取二者最大值
                lineHeight = Math.max(lineHeight, realChildHeight);
            }

            //遍历到最后一个的时候，肯定走的是不换行
            if (i == childCount - 1) {
                resultWidth = Math.max(lineWidth, resultWidth);
                resultHeight += lineHeight;
            }

            setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : resultWidth,
                    modeHeight == MeasureSpec.EXACTLY ? sizeHeight : resultHeight);

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int flowWidth = getWidth();

        int childLeft = 0;
        int childTop = 0;

        //遍历子控件，记录每个子view的位置
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);

            //跳过View.GONE的子View
            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();

            if (childLeft + mlp.leftMargin + childWidth + mlp.rightMargin > flowWidth) {
                //换行处理
                childTop += (mlp.topMargin + childHeight + mlp.bottomMargin);
                childLeft = 0;
            }
            //布局
            int left = childLeft + mlp.leftMargin;
            int top = childTop + mlp.topMargin;
            int right = childLeft + mlp.leftMargin + childWidth;
            int bottom = childTop + mlp.topMargin + childHeight;
            childView.layout(left, top, right, bottom);

            childLeft += (mlp.leftMargin + childWidth + mlp.rightMargin);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            reloadData();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }


    /**
     * 重新加载刷新数据
     */
    private void reloadData() {
        removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            final int j = i;
            mCheckedTagArray.put(i, false);
            final View childView = mAdapter.getView(i, null, this);
//            addView(childView,
//              new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));//这个构造方法所然能使用但是编译器会报错
            addView(childView, new MarginLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)));

            if (mAdapter instanceof OnInitSelectedPosition) {
                //判断一下模式
                mCheckedTagArray.put(i, false);
                childView.setSelected(false);

            }

            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG","当前选中个数=========================="+getSelectNum());

                       if(mCheckedTagArray.get(j)){
                           mCheckedTagArray.put(j, false);
                           childView.setSelected(false);
                           subSelectNum();
                       }else if(getSelectNum()==maxSelectNum){
                           Toast.makeText(getContext(),"最多只能选100个",Toast.LENGTH_LONG).show();
                           return;
                       }else {
                           mCheckedTagArray.put(j, true);
                           childView.setSelected(true);
                           addSelectNum();
                       }
                        //回调
                        if (mOnTagSelectListener != null) {
                            List<Integer> list = new ArrayList<Integer>();
                            for (int k = 0; k < mAdapter.getCount(); k++) {
                                if (mCheckedTagArray.get(k)) {
                                    list.add(k);
                                }
                            }
                            mOnTagSelectListener.onItemSelect(FlowTagLayout.this, list,j);
                        }

                }
            });
        }
    }

    /**
     * 清除所有被选择的选项
     *
     * @author https://github.com/wanyt
     *
     * @time 2016年11月13日16:07:23
     *
     */
    public void clearAllOption(){
        for (int i = 0; i < mAdapter.getCount(); i++) {
            if (mCheckedTagArray.get(i)) {
                getChildAt(i).setSelected(false);
            }
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public void setOnTagSelectListener(OnTagSelectListener onTagSelectListener) {
        this.mOnTagSelectListener = onTagSelectListener;
    }

    /**
     * 像ListView、GridView一样使用FlowLayout
     *
     * @param adapter
     */
    public void setAdapter(ListAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        //清除现有的数据
        removeAllViews();
        mAdapter = adapter;

        if (mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }



    /**
     * 设置标签选中模式
     *
     * @param tagMode
     */
    public void setTagCheckedMode(int tagMode) {
        this.mTagCheckMode = tagMode;
    }
    public void addSelectNum(){
        selectNum++;
    }
    public void subSelectNum(){
        selectNum--;
    }
    public int getSelectNum(){
        return selectNum;
    }

}
