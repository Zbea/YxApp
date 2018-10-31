package com.yx.Pharmacy.plugin;

import android.content.Intent;

import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.activity.LoginActivity;
import com.yx.Pharmacy.activity.MyCouponActivity;
import com.yx.Pharmacy.activity.MyIntegralActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.BackHhBean;
import com.yx.Pharmacy.model.SelectStoreHhBean;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class CustomPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        LogUtils.e("execute: "+action);
        //积分明细
        if ("visitScoreDetail".equals(action)) {
            MyIntegralActivity.startActivity(cordova.getActivity());
        }
        //商品详情
        if ("visitGoodsDetail".equals(action)) {
            String    json   = args.getString(0);
            String        id        = "";
            JSONObject jsonObject     = new JSONObject(json);
            id = jsonObject.getString("goodsId");
            ProductDetailActivity.startActivity(cordova.getActivity(), id);
            return true;
        }
        //返回上一页
        if ("back".equals(action)) {
            EventBus.getDefault().post(new BackHhBean());
        }
        //返回首页
        if ("backRoot".equals(action)) {
            Intent intent =new Intent(this.cordova.getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.cordova.startActivityForResult(this, intent, 0);
        }
        //访问下一个html页面
        if ("visitNextPage".equals(action)) {
            String     json       = args.getString(0);
            JSONObject jsonObject = new JSONObject(json);
            String     webUrl     = jsonObject.getString("webUrl");
            Intent     intent     = new Intent(this.cordova.getActivity(), HHActivity.class);
            intent.putExtra(Constants.H5_URL, webUrl);
            this.cordova.startActivityForResult(this, intent, 0);
            this.coolMethod(json, callbackContext);
            return true;
        }
        //兑换优惠券成功
        if ("convertSuccess".equals(action)) {
            //更新积分
            String     json       = args.getString(0);
            JSONObject jsonObject = new JSONObject(json);
            String     score     = jsonObject.getString("score");
        }
        //我的优惠券
        if ("visitCoupon".equals(action)){
            MyCouponActivity.startActivity(cordova.getActivity());
        }
        //登录
        if ("login".equals(action)){
            LoginActivity.startActivity(cordova.getActivity(),1);
        }
        //选择门店
        if ("selectedStore".equals(action)){
            EventBus.getDefault().post(new SelectStoreHhBean());
        }
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    @Override
    public Boolean shouldAllowRequest(String url) {
        return true;
    }

    @Override
    public Boolean shouldAllowNavigation(String url) {
        return true;
    }

    @Override
    public Boolean shouldAllowBridgeAccess(String url) {
        return true;
    }


}
