package com.yx.Pharmacy.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yx.Pharmacy.R;

import java.util.List;

/**
 * Created by KID on 2018/7/22.
 */

public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener{

    //轮播下一张图片
    private static final int MESSAGE_PLAY_IMAGE=1001;
    //轮播间隔时间
    private static final long PLAY_DELAY=2000L;
    //第一张图片间隔多久开始轮播
    private static final long FIRST_DELAY=4000L;

    private ViewPager viewPager;
    private Context context;
    private List<String> imgUrls;
    private BannerPageAdapter bannerPageAdapter;

    private int cusPosition;//当前看到的选中位置
    private boolean isAutoPlay=false;

    private BannerListener bannerListener;
    public interface BannerListener{
        void OnBannerSelect(int position);
        void OnBannerClick(int position);
    }
    public void setBannerListener(BannerListener bannerListener){
        this.bannerListener=bannerListener;
    }

    private boolean isStill;//是否静止
    int pos ;//看到的position

    public BannerView(Context context) {
        super(context);
        this.context=context;
    }
    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init(context);
    }
    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    /**
     * @param isAutoPlay 是否自动播放 ，实现比较暴力，直接不让handler消息执行
     */
    public void setAutoPlay(boolean isAutoPlay){
        this.isAutoPlay=isAutoPlay;
    }
    /**
     * 设置图片
     * @param imageList
     */
    public void setImageList(List<String>imageList){
        imgUrls=imageList;
        bannerPageAdapter=new BannerPageAdapter();
        viewPager.setAdapter(bannerPageAdapter);
        viewPager.addOnPageChangeListener(this);

        //第一次进入页面开始轮播图的间隔要久一点,要等图片,还有你其他的页面数据加载出来以后才轮播，提高用户体验
        mHandler.sendEmptyMessageDelayed(MESSAGE_PLAY_IMAGE,FIRST_DELAY);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_banner, this);
        viewPager= (ViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset==0&&positionOffsetPixels==0){
            isStill=true;
        }else {
            isStill=false;
            mHandler.removeMessages(MESSAGE_PLAY_IMAGE);
        }
    }

    @Override
    public void onPageSelected(int position) {
        //打印position为真实的position
        pos=position%imgUrls.size();
        if(cusPosition!=pos){//引入自动播放后,会在某一位置做一个无动画效果的页面切换，加入这步判断才回调防止重复调用
            bannerListener.OnBannerSelect(pos);
        }
        cusPosition=pos;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==0){//静止
            isStill=true;
            mHandler.removeMessages(MESSAGE_PLAY_IMAGE);
            mHandler.sendEmptyMessageDelayed(MESSAGE_PLAY_IMAGE,PLAY_DELAY);
        }
    }


    class BannerPageAdapter extends PagerAdapter {
        private boolean isPlay=false;
        public BannerPageAdapter() {
        }
        //是否自动播放第一张图片到第二张
        public void setPlayingFirstItem(boolean isPlay){
            this.isPlay=isPlay;
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            final int pos = position%imgUrls.size();
            View view = LayoutInflater.from(context).inflate(R.layout.item_vp, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.img_item);
            ImageView img_scale =view.findViewById(R.id.img_scale);
            Glide.with(context).load(imgUrls.get(pos)).into(imageView);
            Glide.with(context).load(imgUrls.get(pos)).into(img_scale);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    bannerListener.OnBannerClick(pos);
                }
            });
            container.addView(view);
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        @Override
        public int getCount() {
//          理论上当图片不为1张时,getCount可以设置无穷大，这里设置成图片4倍只是想看会不会滑到头,实际上3倍就够了,设2倍的话,当图片只有2张时可能会碰到头
//          return Integer.MAX_VALUE;
            return imgUrls.size()==1?imgUrls.size():imgUrls.size()*4;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //当显示界面加载完时调用该方法
        @Override
        public void finishUpdate(ViewGroup container) {
            Log.d("BannerView","finishUpdate position======"+viewPager.getCurrentItem());
            int position = viewPager.getCurrentItem();
            if(imgUrls.size()==1){
                //TODO 但轮播图片只有一张的时候，什么都不做,或者隐藏小圆点，提示文本之类。当然，如果你只有一张图片，你还想重复滑出这张图片的话，在这里开始你的骚操作

            } else {
                //TODO 但轮播图片超过3张时，每当图片的position超过图片数量时，切换viewpager当前选择item(去除切换动画效果)
                if (position == 0){
                    position = imgUrls.size();
                    //TODO 自动轮播的时候,需要这部判断来让第一张和第二张平滑过渡
                    if(!isPlay){
                        viewPager.setCurrentItem(position,false);
                    }
                } else if(position>imgUrls.size()+1){
                    viewPager.setCurrentItem(position%imgUrls.size(),false);
                }
            }
        }
    }
    /**
     * 消息处理
     */
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**滑动中，同步播放进度*/
                case MESSAGE_PLAY_IMAGE:
                    if(isStill&&isAutoPlay){
                        if(pos==0){
                            bannerPageAdapter.setPlayingFirstItem(true);
                            viewPager.setCurrentItem(0,false);
                            viewPager.setCurrentItem(pos+1);
                            bannerPageAdapter.setPlayingFirstItem(false);
                        }else {
                            viewPager.setCurrentItem(pos+1);
                            bannerPageAdapter.setPlayingFirstItem(false);
                        }
                    }
                    break;
            }
        }
    };

    /**
     * 退出页面后防止handler还执行
     */
    public void stopPlay(){
        if(mHandler!=null)mHandler.removeMessages(MESSAGE_PLAY_IMAGE);
    }
}
