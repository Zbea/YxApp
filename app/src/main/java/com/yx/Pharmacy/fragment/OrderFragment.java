package com.yx.Pharmacy.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiyukf.unicorn.widget.ViewPagerFixed;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.AskForAfterSaleActivity;
import com.yx.Pharmacy.activity.OrderDetailActivity;
import com.yx.Pharmacy.activity.PayActivity;
import com.yx.Pharmacy.activity.WuliuActivity;
import com.yx.Pharmacy.adapter.BigPicAdapter;
import com.yx.Pharmacy.adapter.OrderAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.dialog.ComDialog;
import com.yx.Pharmacy.dialog.ListDialog;
import com.yx.Pharmacy.dialog.PhotoDialog;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.presenter.MyOrderListPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.view.IMyOrderListView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by KID on 2018/7/14.
 * 订单(待支付，待发货，待收货，已完成，售后)
 */

public class OrderFragment extends BaseFragment  implements IMyOrderListView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;

    private MyOrderListPresenter mPresenter;
    private int page=1;
    private int clickPosition=0;
    private boolean isUpload;
    private Dialog mPhotoDialog;
    private ViewPagerFixed mViewpager;
    private TextView mTvPosition;
    private BigPicAdapter  mBigPicAdapter;
    private OrderAdapter orderAdapter;
    private List<OrderModel> orderModels=new ArrayList<>();
    private String path;
    private static final String STATUS="status";
    private String status;
    public static OrderFragment newInstance(String status) {
        Bundle bundle = new Bundle();
        bundle.putString(STATUS, status);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser)
        {
            if (mPresenter!=null)
            {
                page=1;
                mPresenter.getMyOrderListData((BaseActivity)mContext,status,page,true);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_order;
    }

    @Override
    protected void init() {
        status=getArguments().getString(STATUS);
        initView();
        mPresenter = new MyOrderListPresenter(this);
        mPresenter.getMyOrderListData((BaseActivity)mContext,status,page,true);
    }

    private void initView() {

        final LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(layoutManager);
        int itemDecoration = DensityUtils.dp2px(mContext, 10);
        recyclerview.addItemDecoration(new SpacesItemDecoration(0, itemDecoration));
        orderAdapter=new OrderAdapter(mContext,R.layout.item_my_order,orderModels,false);
        recyclerview.setAdapter(orderAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        //只适用于LinearLayoutManager
//        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
//                    if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {
//                        initNestPage();
//                    }
//                }
//            }
//        });
        orderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initNestPage();
            }
        },recyclerview);
        orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDetailActivity.startActivity(mContext,orderAdapter.getData().get(position).orderid);
            }
        });
        orderAdapter.setListener(clickListener);

    }
    //加载下一页
    private void initNestPage() {
        mPresenter.getMyOrderListData((BaseActivity)mContext,status,page+1,true);
    }
    @Override
    public void getOrderList(List<OrderModel> data) {//获取下一页数据成功,page+1
        if(data.size()>0){
            orderAdapter.addData(data);
            page++;
            orderAdapter.loadMoreComplete();
        }else {
            orderAdapter.loadMoreEnd();
        }
    }
    @Override
    public void refreshOrderList(List<OrderModel> data) {
        orderAdapter.setNewData(data);
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        if(data.size()<4){//当数据不满一屏时，会出bug，所以加入此部操作
            orderAdapter.loadMoreEnd();
        }
    }
    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getMyOrderListData((BaseActivity)mContext,status,page,true);
    }
    @Override
    public void noOrderList() {//没有订单
        showNoData();
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
    public void cancelBack(int position) {//取消订单成功后删除适配器item
        orderAdapter.remove(position);
    }
    @Override
    public void comfirmBack(int position) {//确认收货成功后修改item数据
        orderAdapter.getData().get(position).status=7;
        orderAdapter.remove(position);
    }
    @Override
    public void upDateResult(String url) {
        orderAdapter.getData().get(clickPosition).pay_url=url;
        orderAdapter.notifyItemChanged(clickPosition);
    }


    @Override
    public void onErrorPage() {
        showErrorPage();
    }

    //适配器点击事件回调
    private OrderAdapter.ClickListener clickListener=new OrderAdapter.ClickListener() {
        @Override
        public void checkWuliu(String orderid) {//查看物流
            WuliuActivity.startActivity(mContext,orderid);
        }
        @Override
        public void checkTansfer(String orderid,int position) {//转账截图
            clickPosition=position;
                    if(TextUtils.isEmpty(orderAdapter.getData().get(position).pay_url))
                    {
                        isUpload=false;
                    }
                    else
                    {
                        isUpload=true;
                    }

            if (isUpload)
            {
                showListDialog();
            }
            else
            {
                showPhotoDialog();
            }

        }

        @Override
        public void cancleOrder(String orderid,int position) {//取消订单
            ComDialog comDialog=new ComDialog(mContext);
            comDialog.setTitle("是否确定取消当前订单？").setContent("取消订单后已支付金额将原路返回至您的账户，请注意查收")
                    .setCancle("否").setOk("是").builder().show();
            comDialog.setDialogClickListener(new ComDialog.DialogClickListener() {
                @Override
                public void cancle() {

                }
                @Override
                public void ok() {
                    mPresenter.cancelOrder((BaseActivity) mContext,orderid,position);
                }
            });
        }

        @Override
        public void goPay(String price, String orderid) {
            PayActivity.startActivity(mContext,price,orderid);
        }


        @Override
        public void bugAgain(String orderid) {//再次采购
            mPresenter.buyAgain((BaseActivity) mContext,orderid);
        }

        @Override
        public void notifySendOrder(String orderid) {//提醒发货
            mPresenter.notifySendOrder((BaseActivity) mContext,orderid);
        }

        @Override
        public void comfirmOrder(String orderid,int position) {//确认收货,暂时没有处理确认后的ui变化
            mPresenter.comfirmReceiveOrder((BaseActivity) mContext,orderid,position);

        }

        @Override
        public void askForAfterSale(String orderid) {//申请售后
            AskForAfterSaleActivity.startActivity((BaseActivity) mContext,orderid);
        }

        @Override
        public void cancelOrderBack(String orderbackid, int layoutPosition) {

        }

        @Override
        public void tuihuo(String orderbackid, int layoutPosition) {

        }
    };

    private void showPhotoDialog()
    {
        PhotoDialog photoDialog = new PhotoDialog(mContext);
        photoDialog.setDialogClickListener(new PhotoDialog.DialogClickListener() {
            @Override
            public void takePhoto() {
                PictureSelector.create(mContext)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }

            @Override
            public void pickPhoto() {
                PictureSelector.create(mContext)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        //                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(0)// 最小选择数量 int
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(false)// 是否可预览图片 true or false
                        .previewVideo(false)// 是否可预览视频 true or false
                        .enablePreviewAudio(false) // 是否可播放音频 true or false
                        .isCamera(false)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .enableCrop(true)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                        .isGif(false)// 是否显示gif图片 true or false
                        .selectionMedia(null)// 是否传入已选图片 List<LocalMedia> list
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        });
        photoDialog.builder().show();
    }

    private void showListDialog()
    {
        List<String> lists=new ArrayList<>();
        lists.add("查看截图");
        lists.add("重新上传");
        ListDialog listDialog=new ListDialog(mContext,lists).builder();
        listDialog.setDialogClickListener(new ListDialog.DialogClickListener() {
            @Override
            public void click(int position) {
                if (position==0)
                {
                    showBigPhotoDialog(orderAdapter.getData().get(position).pay_url);
                }
                else
                {
                    showPhotoDialog();
                }
            }
        });
        listDialog.show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {  //图片选择返回
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList!=null&&selectList.size()>0) {
                path=selectList.get(0).getPath();
                if (orderAdapter.getData().size()>0)
                    mPresenter.upFile((BaseActivity)mContext,path,orderAdapter.getData().get(clickPosition).orderid);
            }
        }
    }

    /**
     * 查看图片弹窗
     */
    private void showBigPhotoDialog(String fileName) {
        ArrayList<String> files = new ArrayList<>();
        if(TextUtils.isEmpty(fileName)){
            return;
        }else {
            files.add(fileName);
        }
        if(mPhotoDialog==null){
            mPhotoDialog = new Dialog(mContext, R.style.Dialog_Fullscreen);
            View view  = getLayoutInflater().inflate(R.layout.activity_big_pic, null);
            mViewpager = view.findViewById(R.id.viewpager);
            mBigPicAdapter = new BigPicAdapter(files);

            mBigPicAdapter.setOnClick(new BigPicAdapter.OnClickFinishListener() {
                @Override
                public void onClick() {
                    mPhotoDialog.dismiss();
                }
            });

            mViewpager.setAdapter(mBigPicAdapter);
            mPhotoDialog.setContentView(view);
            mPhotoDialog.show();
        }else {
            mBigPicAdapter.setData(files);
            mPhotoDialog.show();
        }
    }

    @OnClick({R.id.tv_reload})
    public void click(View v){
        switch (v.getId()){
            case R.id.tv_reload://重新加载
                List<OrderModel>models=new ArrayList<>();
                orderAdapter.setNewData(models);
                showNornaml();
                page=1;
                mPresenter.getMyOrderListData((BaseActivity) mContext,status,page,true);
                break;
        }
    }
}
