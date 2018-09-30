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
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IMessageDetailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageDetailPresenter {
    private IMessageDetailView mView;

    public MessageDetailPresenter(IMessageDetailView view) {
        this.mView = view;
    }
    /**
     * 获取消息列表
     * @param activity
     */
    public void getMessageList(final BaseActivity activity, final int pagenum, int pushtype, final boolean isRefresh) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",String.valueOf(pagenum));
        urlMap.put("pushtype",String.valueOf(pushtype));
        HomeNet.getHomeApi().getMessageList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<MessageData.MessageModel>>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<List<MessageData.MessageModel>> response) {
                        if (response.getData()!=null) {
                            if(isRefresh){
                                mView.refreshMessageList(response.getData());
                            }else {
                                mView.getMessageList(response.getData());
                            }
                        }else if(pagenum==1){
                            mView.noMessageList();
                        }else {//后台返回null......
                            mView.getMessageList(new ArrayList<MessageData.MessageModel>());
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
}
