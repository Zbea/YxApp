package com.yx.Pharmacy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.activity.MyShopAddActivity;
import com.yx.Pharmacy.activity.AfterOrderDetailActivity;
import com.yx.Pharmacy.activity.CommendProductActivity;
import com.yx.Pharmacy.activity.OrderDetailActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.activity.SearchActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.manage.MessageIsReadNumManage;
import com.yx.Pharmacy.util.L;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created time  2018/8/1 0001
 *
 * @author : mcx
 * 类描述 :
 */

public class YxJPushReceiver
        extends BroadcastReceiver {
    private static String TAG = "pushreceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            L.i("111111111111111");
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[YxJPushReceiver] 接收Registration Id : " + regId);

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            L.i("222222222222222");
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            // 收到了自定义消息@@消息内容是: content
            // 收到了自定义消息@@消息extra是: extra
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            if (bundle != null) {
                L.i("33333333333");
                EventBus.getDefault().post(0);
                MessageIsReadNumManage.newInstance().refresh();
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(extra);
                    ReceiverDialogManage.newInstance().showDialog(jsonObject);
//                    String pushtype = jsonObject.optString("pushtype");
//                    JSONObject finalJsonObject = jsonObject;
//                    ComDialog comDialog = new ComDialog(context);
//                    comDialog.setTitleView(false).setContent("有新的消息请您查看").setOk("去看看").builder().show();
//                    comDialog.setDialogClickListener(new ComDialog.DialogClickListener() {
//                        @Override
//                        public void cancle() {
//                            comDialog.cancle();
//                        }
//
//                        @Override
//                        public void ok() {
//                            Intent i = null;
//                            if (TextUtils.equals(pushtype, "1")) {
//                                //app跳转活动模块
//                                String weburl = finalJsonObject.optString("weburl");
//                                i = new Intent(context, HHActivity.class);
//                                i.putExtra(Constants.H5_URL, weburl);
//                            } else if (TextUtils.equals(pushtype, "2")) {
//                                //app跳转商品详情
//                                String goodsid = finalJsonObject.optString("goodsid");
//                                i = new Intent(context, ProductDetailActivity.class);
//                                i.putExtra(Constants.KEY_ITEM_ID, goodsid);
//                            } else if (TextUtils.equals(pushtype, "3")) {
//                                //app跳转门店认证页
//                                String storeid = finalJsonObject.optString("storeid");
//                                i = new Intent(context, MyShopAddActivity.class);
//                                i.putExtra("itemid", storeid);
//                            } else if (TextUtils.equals(pushtype, "4")) {
//                                //app携带关键字跳转至搜索页
//                                String keyword = finalJsonObject.optString("keyword");
//                                i = new Intent(context, SearchActivity.class);
//                                i.putExtra("keyword", keyword);
//                            } else if (TextUtils.equals(pushtype, "5")) {
//                                //跳转到其他专区的活动
//                                String activityname = finalJsonObject.optString("activityname");
//                                String levelid = finalJsonObject.optString("levelid");
//                                String type = finalJsonObject.optString("type");
//                                i = new Intent(context, CommendProductActivity.class);
//                                i.putExtra("activityname", activityname);
//                                i.putExtra("levelid", levelid);
//                                i.putExtra("type", type);
//                            } else if (TextUtils.equals(pushtype, "6")) {
//                                //app跳转订单详情
//                                String orderid = finalJsonObject.optString("orderid");
//                                i = new Intent(context, OrderDetailActivity.class);
//                                i.putExtra("orderid", orderid);
//                            } else if (TextUtils.equals(pushtype, "7")) {
//                                //app跳转售后订单详情
//                                String orderid = finalJsonObject.optString("orderid");
//                                i = new Intent(context, AfterOrderDetailActivity.class);
//                                i.putExtra("orderbackid", orderid);
//                            } else if (TextUtils.equals(pushtype, "8")) {
//                                //app跳转商品详情
//                                String goodsid = finalJsonObject.optString("goodsid");
//                                i = new Intent(context, ProductDetailActivity.class);
//                                i.putExtra(Constants.KEY_ITEM_ID, goodsid);
//                            } else {
//                                comDialog.cancle();
//                            }
//                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            context.startActivity(i);
//                        }
//                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            // 收到了通知 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            // 用户点击打开了通知 在这里可以自己写代码去定义用户点击后的行为
            L.i("444444444444444");
            if (bundle != null) {
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                try {
                    JSONObject jsonObject = new JSONObject(extra);
                    String pushtype = jsonObject.optString("pushtype");

                    Intent i = null;
                    if (TextUtils.equals(pushtype, "1")) {
                        //app跳转活动模块
                        String weburl = jsonObject.optString("weburl");
                        i = new Intent(context, HHActivity.class);
                        i.putExtra(Constants.H5_URL, weburl);
                    } else if (TextUtils.equals(pushtype, "2")) {
                        //app跳转商品详情
                        String goodsid = jsonObject.optString("goodsid");
                        i = new Intent(context, ProductDetailActivity.class);
                        i.putExtra(Constants.KEY_ITEM_ID, goodsid);
                    } else if (TextUtils.equals(pushtype, "3")) {
                        //app跳转门店认证页
                        String storeid = jsonObject.optString("storeid");
                        i = new Intent(context, MyShopAddActivity.class);
                        i.putExtra("itemid", storeid);
                    } else if (TextUtils.equals(pushtype, "4")) {
                        //app携带关键字跳转至搜索页
                        String keyword = jsonObject.optString("keyword");
                        i = new Intent(context, SearchActivity.class);
                        i.putExtra("keyword", keyword);
                    } else if (TextUtils.equals(pushtype, "5")) {
                        //跳转到其他专区的活动
                        String activityname = jsonObject.optString("activityname");
                        String levelid = jsonObject.optString("levelid");
                        String type = jsonObject.optString("type");
                        i = new Intent(context, CommendProductActivity.class);
                        i.putExtra("activityname", activityname);
                        i.putExtra("levelid", levelid);
                        i.putExtra("type", type);
                    } else if (TextUtils.equals(pushtype, "6")) {
                        //app跳转订单详情
                        String orderid = jsonObject.optString("orderid");
                        i = new Intent(context, OrderDetailActivity.class);
                        i.putExtra("orderid", orderid);
                    } else if (TextUtils.equals(pushtype, "7")) {
                        //app跳转售后订单详情
                        String orderid = jsonObject.optString("orderid");
                        i = new Intent(context, AfterOrderDetailActivity.class);
                        i.putExtra("orderbackid", orderid);
                    } else if (TextUtils.equals(pushtype, "8")) {
                        //app跳转商品详情
                        String goodsid = jsonObject.optString("goodsid");
                        i = new Intent(context, ProductDetailActivity.class);
                        i.putExtra(Constants.KEY_ITEM_ID, goodsid);
                    } else {
                        i = new Intent(context, MainActivity.class);
                    }

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
