package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.BigPicAdapter;
import com.yx.Pharmacy.adapter.ImageListAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.presenter.QiyeZizhiPresenter;
import com.yx.Pharmacy.view.IQiyeZizhiView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created time  2018/8/28 0028
 * @author : mcx
 * 类描述 : 
 */

public class QiyeZizhiActivity
        extends BaseActivity implements IQiyeZizhiView
{

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView       mTvTitle;
    @BindView(R.id.rv_zizhi)
    RecyclerView   mRvZizhi;
    private QiyeZizhiPresenter mPresenter;
    private ImageListAdapter mAdapter;
    private Dialog mPhotoDialog= null;
    private ArrayList<String> imgs=new ArrayList<>();

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, QiyeZizhiActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qiye_zizhi;
    }

    @Override
    protected void init() {
        mTvTitle.setText("企业资质");
        mPresenter = new QiyeZizhiPresenter(this);

        initRecycler();
        mPresenter.getStoreValide(this);
    }

    private void initRecycler() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvZizhi.setLayoutManager(layoutManager);
        mAdapter = new ImageListAdapter(R.layout.item_qiye_zizhi);
        mRvZizhi.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ImageListAdapter.OnItemClickListener() {
            @Override
            public void onClick() {
                showBigPhotoDialog();
            }
        });
    }


    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showList(List<String> data) {
        imgs= (ArrayList<String>) data;
        mAdapter.setNewData(data);
    }

    /**
     * 查看图片弹窗
     */
    private void showBigPhotoDialog() {


        if (mPhotoDialog == null) {
            mPhotoDialog = new Dialog(this, R.style.Dialog_Fullscreen);
            View view = getLayoutInflater().inflate(R.layout.activity_big_pic, null);
            ViewPager mViewpager = view.findViewById(R.id.viewpager);
            LinearLayout ll=view.findViewById(R.id.ll_num);
            ll.setVisibility(View.GONE);
            BigPicAdapter mBigPicAdapter = new BigPicAdapter(imgs);
            mBigPicAdapter.setOnClick(new BigPicAdapter.OnClickFinishListener() {
                @Override
                public void onClick() {
                    mPhotoDialog.dismiss();
                }
            });
            mViewpager.setAdapter(mBigPicAdapter);
            mPhotoDialog.setContentView(view);
            mPhotoDialog.show();
        }
        else
        {
            mPhotoDialog.show();
        }
    }
}
