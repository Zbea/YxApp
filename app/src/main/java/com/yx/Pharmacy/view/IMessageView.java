package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IMessageView
 *  @创建者:   CC
 *  @创建时间:  2018/8/7 22:41
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.MessageData;

import java.util.List;

public interface IMessageView {
    void getMessageData(MessageData data);
    void refreshMessageList(List<MessageData.MessageModel> data);
    void addMessage(List<MessageData.MessageModel> data);

    void noMessageData();

    void onErrorPage();
    void onDelete();
}
