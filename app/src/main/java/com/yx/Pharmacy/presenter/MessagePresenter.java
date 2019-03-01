package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   MessagePresenter
 *  @创建者:   CC
 *  @创建时间:  2018/8/7 22:41
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.MessageData;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IMessageView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessagePresenter {
    private IMessageView mView;

    public MessagePresenter(IMessageView view) {
        this.mView = view;
    }
    /**
     * 获取消息列表
     * @param activity
     */
    public void getMessageData(final BaseActivity activity, final int pagenum) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",String.valueOf(pagenum));
        HomeNet.getHomeApi().getMessageData(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<MessageData>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<MessageData> response) {
                        if(response.getData()!=null){
                            if(pagenum==1){
                                mView.getMessageData(response.getData());
                                mView.refreshMessageList(response.getData().message);
                            }else {
                                mView.addMessage(response.getData().message);
                            }
                        }else {
                            mView.noMessageData();
                        }

                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.onErrorPage();
                    }
                });
    }
    /**
     * 发送消息已读报告
     * @param activity
     */
    public void sendReadMessage(final BaseActivity activity, final String messageid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("messageid",String.valueOf(messageid));
        HomeNet.getHomeApi().sendReadMessage(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Object>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Object> response) {

                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
    /**
     * 发送全部已读报告
     * @param activity
     */
    public void sendReadAllMessage(final BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().sendReadAllMessage(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Object>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Object> response) {

                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 清空消息列表
     * @param activity
     */
    public void deleteAllMessage(final BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("action","all");
        HomeNet.getHomeApi().delMessage(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<String>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        if(response.getData()==null){
                            activity.getShortToastByString("清空成功");
                            mView.onDelete();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }


    /**
     * 删除消息列表
     * @param activity
     */
    public void deleteMessage(final BaseActivity activity,String sid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("action","single");
        urlMap.put("msgid",sid);
        HomeNet.getHomeApi().delMessage(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<String>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        if(response.getData()==null){
                            activity.getShortToastByString("删除成功");
                            mView.onDelete();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

}
