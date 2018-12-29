package com.yx.Pharmacy.wxapi;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yx.Pharmacy.constant.Constants;

import cn.sharesdk.wechat.utils.WechatHandlerActivity;


public class WXEntryActivity extends WechatHandlerActivity
        implements IWXAPIEventHandler
{

    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private IWXAPI mWxApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWxApi = WXAPIFactory.createWXAPI(this, Constants.WX_ID, true);
        // 将该app注册到微信
        mWxApi.registerApp(Constants.WX_ID);
        mWxApi.handleIntent(getIntent(),this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:// 失败
                finish();
                break;
            case BaseResp.ErrCode.ERR_OK:

                break;
            default:
                finish();
                break;
        }
    }
}
