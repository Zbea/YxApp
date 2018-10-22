package com.yx.Pharmacy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.util.ViewUtil;


/**
 * create by Weavey
 * on date 2016-03-12
 * TODO
 */
public class LoadingLayout
        extends FrameLayout
{

    public final static int Success = 0;
    public final static int Empty = 1;
    public final static int Error = 2;
    public final static int No_Network = 3;
    public final static int Loading = 4;
    private int state;

    private Context mContext;
    private View    loadingPage;
    private View    errorPage;
    private View    emptyPage;
    private View    networkPage;
    private View    defineLoadingPage;

    private ImageView errorImg;
    private ImageView emptyImg;
    private ImageView networkImg;

    private TextView errorText;
    private TextView emptyText;
    private TextView networkText;

    private TextView errorReloadBtn;
    private TextView networkReloadBtn;
    private TextView emptyReloadBtn;

    private View             contentView;
    private OnReloadListener listener;
    private boolean          isFirstVisible; //是否一开始显示contentview，默认不显示
    private int              pageBackground;
    private static Config mConfig = new Config();   //配置

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingLayout);
        isFirstVisible = a.getBoolean(R.styleable.LoadingLayout_isFirstVisible, false);
        pageBackground = a.getColor(R.styleable.LoadingLayout_pageBackground, ViewUtil.getColor(mContext, R.color
                .white));
        a.recycle();
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public LoadingLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }
        contentView = this.getChildAt(0);
        if (!isFirstVisible) {
            contentView.setVisibility(View.GONE);
        }
        build();
    }

    private void build() {

        if (mConfig.loadingView == null) {
            loadingPage = LayoutInflater.from(mContext).inflate(mConfig.loadingLayoutId, null);
        } else {
            loadingPage = mConfig.loadingView;
        }
        errorPage = LayoutInflater.from(mContext).inflate(R.layout.widget_error_page, null);
        emptyPage = LayoutInflater.from(mContext).inflate(R.layout.widget_empty_page, null);
        networkPage = LayoutInflater.from(mContext).inflate(R.layout.widget_nonetwork_page, null);
        defineLoadingPage = null;

        loadingPage.setBackgroundColor(pageBackground);
        errorPage.setBackgroundColor(pageBackground);
        emptyPage.setBackgroundColor(pageBackground);
        networkPage.setBackgroundColor(pageBackground);

        errorText = ViewUtil.findViewById(errorPage, R.id.error_text);
        emptyText = ViewUtil.findViewById(emptyPage, R.id.empty_text);
        networkText = ViewUtil.findViewById(networkPage, R.id.no_network_text);

        errorImg = ViewUtil.findViewById(errorPage, R.id.error_img);
        emptyImg = ViewUtil.findViewById(emptyPage, R.id.empty_img);
        networkImg = ViewUtil.findViewById(networkPage, R.id.no_network_img);

        errorReloadBtn = ViewUtil.findViewById(errorPage, R.id.error_reload_btn);
        networkReloadBtn = ViewUtil.findViewById(networkPage, R.id.no_network_reload_btn);
        emptyReloadBtn = ViewUtil.findViewById(emptyPage, R.id.error_reload_btn);

        errorReloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onReload(v);
                }
            }
        });
        networkReloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onReload(v);
                }
            }
        });
        emptyReloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onReload(v);
                }
            }
        });

        errorText.setText(mConfig.errorStr);
        emptyText.setText(mConfig.emptyStr);
        networkText.setText(mConfig.netwrokStr);

        errorText.setTextSize(mConfig.tipTextSize);
        emptyText.setTextSize(mConfig.tipTextSize);
        networkText.setTextSize(mConfig.tipTextSize);

        errorText.setTextColor(ViewUtil.getColor(mContext, mConfig.tipTextColor));
        emptyText.setTextColor(ViewUtil.getColor(mContext, mConfig.tipTextColor));
        networkText.setTextColor(ViewUtil.getColor(mContext, mConfig.tipTextColor));

        errorImg.setImageResource(mConfig.errorImgId);
        emptyImg.setImageResource(mConfig.emptyImgId);
        networkImg.setImageResource(mConfig.networkImgId);


        errorReloadBtn.setBackgroundResource(mConfig.reloadBtnId);
        networkReloadBtn.setBackgroundResource(mConfig.reloadBtnId);

        errorReloadBtn.setText(mConfig.reloadBtnStr);
        networkReloadBtn.setText(mConfig.reloadBtnStr);

        errorReloadBtn.setTextSize(mConfig.buttonTextSize);
        networkReloadBtn.setTextSize(mConfig.buttonTextSize);

        errorReloadBtn.setTextColor(ViewUtil.getColor(mContext, mConfig.buttonTextColor));
        networkReloadBtn.setTextColor(ViewUtil.getColor(mContext, mConfig.buttonTextColor));

        if (mConfig.buttonHeight != -1) {

            errorReloadBtn.setHeight(ViewUtil.dp2px(mContext, mConfig.buttonHeight));
            networkReloadBtn.setHeight(ViewUtil.dp2px(mContext, mConfig.buttonHeight));
        }
        if (mConfig.buttonWidth != -1) {

            errorReloadBtn.setWidth(ViewUtil.dp2px(mContext, mConfig.buttonWidth));
            networkReloadBtn.setWidth(ViewUtil.dp2px(mContext, mConfig.buttonWidth));
        }

        this.addView(networkPage);
        this.addView(emptyPage);
        this.addView(errorPage);
        this.addView(loadingPage);
    }

    public void setStatus(@Flavour int status) {

        this.state = status;

        switch (status) {
            case Success:

                contentView.setVisibility(View.VISIBLE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {

                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case Loading:

                contentView.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.VISIBLE);
                } else {
                    loadingPage.setVisibility(View.VISIBLE);
                }
                break;

            case Empty:

                contentView.setVisibility(View.GONE);
                emptyPage.setVisibility(View.VISIBLE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case Error:

                contentView.setVisibility(View.GONE);
                loadingPage.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.VISIBLE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case No_Network:

                contentView.setVisibility(View.GONE);
                loadingPage.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.VISIBLE);
                if (defineLoadingPage != null) {

                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            default:
                break;
        }

    }

    /**
     * 返回当前状态{Success, Empty, Error, No_Network, Loading}
     *
     * @return
     */
    public int getStatus() {

        return state;
    }

    /**
     * 设置Empty状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setEmptyText(String text) {

        emptyText.setText(text);
        return this;
    }

    /**
     * 设置Error状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setErrorText(String text) {

        errorText.setText(text);
        return this;
    }

    /**
     * 设置No_Network状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setNoNetworkText(String text) {

        networkText.setText(text);
        return this;
    }

    /**
     * 设置Empty状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setEmptyImage(@DrawableRes int id) {


        emptyImg.setImageResource(id);
        return this;
    }

    /**
     * 设置Error状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setErrorImage(@DrawableRes int id) {

        errorImg.setImageResource(id);
        return this;
    }

    /**
     * 设置No_Network状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setNoNetworkImage(@DrawableRes int id) {

        networkImg.setImageResource(id);
        return this;
    }

    /**
     * 设置Empty状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setEmptyTextSize(int sp) {

        emptyText.setTextSize(sp);
        return this;
    }

    /**
     * 设置Error状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setErrorTextSize(int sp) {

        errorText.setTextSize(sp);
        return this;
    }

    /**
     * 设置No_Network状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setNoNetworkTextSize(int sp) {

        networkText.setTextSize(sp);
        return this;
    }

    /**
     * 设置Empty状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public LoadingLayout setEmptyImageVisible(boolean bool) {

        if (bool) {
            emptyImg.setVisibility(View.VISIBLE);
        } else {
            emptyImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置Error状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public LoadingLayout setErrorImageVisible(boolean bool) {

        if (bool) {
            errorImg.setVisibility(View.VISIBLE);
        } else {
            errorImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置No_Network状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public LoadingLayout setNoNetworkImageVisible(boolean bool) {

        if (bool) {
            networkImg.setVisibility(View.VISIBLE);
        } else {
            networkImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置Empty状态重新加载显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public LoadingLayout setEmptyReloadBtnVisible(boolean bool) {

        if (bool) {
            emptyReloadBtn.setVisibility(View.VISIBLE);
        } else {
            emptyReloadBtn.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置ReloadButton的文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setReloadButtonText(@NonNull String text) {
        emptyReloadBtn.setText(text);
        errorReloadBtn.setText(text);
        networkReloadBtn.setText(text);
        return this;
    }
    /**
     * 设置ReloadButton的文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setErrorReloadButtonText(@NonNull String text) {

        errorReloadBtn.setText(text);
        return this;
    }
    /**
     * 设置ReloadButton的文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setEmptyReloadButtonText(@NonNull String text) {

        emptyReloadBtn.setText(text);
        return this;
    }

    /**
     * 设置ReloadButton的文本字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setReloadButtonTextSize(int sp) {

        errorReloadBtn.setTextSize(sp);
        networkReloadBtn.setTextSize(sp);
        return this;
    }

    /**
     * 设置ReloadButton的文本颜色，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setReloadButtonTextColor(@ColorRes int id) {

        errorReloadBtn.setTextColor(ViewUtil.getColor(mContext, id));
        networkReloadBtn.setTextSize(ViewUtil.getColor(mContext, id));
        return this;
    }

    /**
     * 设置ReloadButton的背景，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setReloadButtonBackgroundResource(@DrawableRes int id) {

        errorReloadBtn.setBackgroundResource(id);
        networkReloadBtn.setBackgroundResource(id);
        return this;
    }

    /**
     * 设置ReloadButton的监听器
     *
     * @param listener
     * @return
     */
    public LoadingLayout setOnReloadListener(OnReloadListener listener) {

        this.listener = listener;
        return this;
    }

    /**
     * 自定义加载页面，仅对当前所在的Activity有效
     *
     * @param view
     * @return
     */
    public LoadingLayout setLoadingPage(View view) {

        defineLoadingPage = view;
        this.removeView(loadingPage);
        defineLoadingPage.setVisibility(View.GONE);
        this.addView(view);
        return this;
    }

    /**
     * 自定义加载页面，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setLoadingPage(@LayoutRes int id) {

        this.removeView(loadingPage);
        View view = LayoutInflater.from(mContext).inflate(id, null);
        defineLoadingPage = view;
        defineLoadingPage.setVisibility(View.GONE);
        this.addView(view);
        return this;
    }

    /**
     * 设置各种状态下view的背景色，仅对当前所在的地方有效
     *
     * @param color
     * @return
     */
    public LoadingLayout setDefineBackgroundColor(@ColorRes int color) {

        if (defineLoadingPage == null) {
            loadingPage.setBackgroundColor(ViewUtil.getColor(mContext, color));
        } else {
            defineLoadingPage.setBackgroundColor(ViewUtil.getColor(mContext, color));
        }
        errorPage.setBackgroundColor(ViewUtil.getColor(mContext, color));
        emptyPage.setBackgroundColor(ViewUtil.getColor(mContext, color));
        networkPage.setBackgroundColor(ViewUtil.getColor(mContext, color));
        return this;
    }

    /**
     * 获取当前自定义的loadingpage
     *
     * @return
     */
    public View getLoadingPage() {

        return defineLoadingPage;
    }

    /**
     * 获取全局使用的loadingpage
     *
     * @return
     */
    public View getGlobalLoadingPage() {

        return loadingPage;
    }

    @IntDef({Success, Empty, Error, No_Network, Loading})
    public @interface Flavour {

    }

    public interface OnReloadListener {

        void onReload(View v);
    }

    /**
     * 获取全局配置的class
     *
     * @return
     */
    public static Config getConfig() {

        return mConfig;
    }

    /**
     * 全局配置的Class，对所有使用到的地方有效
     */
    public static class Config {

        String emptyStr        = "哎哟.暂时没有数据~";
        String errorStr        = "加载失败... 呼~";
        String netwrokStr      = "去追寻诗和远方了~";
        String reloadBtnStr    = "重新加载";
        int    emptyImgId      = R.drawable.zwtjmdwk;
        int    errorImgId      = R.drawable.zwtjmdwk;
        int    networkImgId    = R.drawable.zwtjmdwk;
        int    reloadBtnId     = R.drawable.shape_btn_main_bg_radiu;
        int    tipTextSize     = 14;
        int    buttonTextSize  = 14;
        int    tipTextColor    = R.color.base_text_color_light;
        int    buttonTextColor = R.color.white;
        int    buttonWidth     = -1;
        int    buttonHeight    = -1;
        int    loadingLayoutId = R.layout.widget_loading_page;
        View   loadingView     = null;
        int    backgroundColor = R.color.white;

        public Config setErrorText(@NonNull String text) {

            errorStr = text;
            return mConfig;
        }

        public Config setEmptyText(@NonNull String text) {

            emptyStr = text;
            return mConfig;
        }

        public Config setNoNetworkText(@NonNull String text) {

            netwrokStr = text;
            return mConfig;
        }

        public Config setReloadButtonText(@NonNull String text) {

            reloadBtnStr = text;
            return mConfig;
        }

        /**
         * 设置所有提示文本的字体大小
         *
         * @param sp
         * @return
         */
        public Config setAllTipTextSize(int sp) {

            tipTextSize = sp;
            return mConfig;
        }

        /**
         * 设置所有提示文本的字体颜色
         *
         * @param color
         * @return
         */
        public Config setAllTipTextColor(@ColorRes int color) {

            tipTextColor = color;
            return mConfig;
        }

        public Config setReloadButtonTextSize(int sp) {

            buttonTextSize = sp;
            return mConfig;
        }

        public Config setReloadButtonTextColor(@ColorRes int color) {

            buttonTextColor = color;
            return mConfig;
        }

        public Config setReloadButtonBackgroundResource(@DrawableRes int id) {

            reloadBtnId = id;
            return mConfig;
        }

        public Config setReloadButtonWidthAndHeight(int width_dp, int height_dp) {

            buttonWidth = width_dp;
            buttonHeight = height_dp;
            return mConfig;
        }

        public Config setErrorImage(@DrawableRes int id) {

            errorImgId = id;
            return mConfig;
        }

        public Config setEmptyImage(@DrawableRes int id) {

            emptyImgId = id;
            return this;
        }

        public Config setNoNetworkImage(@DrawableRes int id) {

            networkImgId = id;
            return mConfig;
        }

        public Config setLoadingPageLayout(@LayoutRes int id) {

            loadingLayoutId = id;
            return mConfig;
        }

        public Config setLoadingPageView(View view) {

            this.loadingView = view;
            return mConfig;
        }

        public Config setAllPageBackgroundColor(@ColorRes int color) {

            backgroundColor = color;
            return mConfig;
        }
    }
}
