package com.yx.Pharmacy.manage;

import com.yx.Pharmacy.util.L;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Zbea
 * Date: 2018/9/27 10:11
 * Description: 消息页面已读红点数量显示管理
 */
public class MessageIsReadNumManage {

    private static MessageIsReadNumManage mMessageIsReadNumManage = null;
    private MessageIsReadNumManageListener mMessageIsReadNumManageListener;
    private Map<Integer,Integer> saves=new HashMap<>();
    private int numLogistics=0;
    private int numNotice=0;
    private int numSale=0;
    private int numWuliu=0;

    private MessageIsReadNumManage() {

    }

    public static MessageIsReadNumManage newInstance()
    {
        if (mMessageIsReadNumManage==null)
        {
            mMessageIsReadNumManage=new MessageIsReadNumManage();
        }
        return mMessageIsReadNumManage;
    }

    /**
     * 初始化总的未读数量
     * @param type 类型
     * @param num 数量
     */
    public void setInitNum(int type,int num)
    {
        saves.put(type,num);
    }

    /**
     * 清空
     */
    public void clearCache() {
        saves.clear();
    }



    /**
     * 修改未读数量
     * @param type
     */
    public void refreshDate(int type)
    {
        int count=saves.get(type);
        saves.put(type,count-1);
        mMessageIsReadNumManageListener.onRefreshReadNum(type,count-1);
    }

    public void allRead()
    {
        for (int i = 1; i <5 ; i++) {
            saves.put(i,0);
            mMessageIsReadNumManageListener.onRefreshReadNum(i,0);
        }
    }
    public void refresh()
    {
        if (mMessageIsReadNumManageListener!=null)
            mMessageIsReadNumManageListener.onRefreshReadNum(5,0);
    }

    public void print()
    {
        for (Map.Entry<Integer, Integer> entry : saves.entrySet()) {
            L.i("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }


    public MessageIsReadNumManageListener setMessageIsReadNumManageListener(MessageIsReadNumManageListener messageIsReadNumManageListener)
    {
        if (messageIsReadNumManageListener!=null)
            mMessageIsReadNumManageListener=messageIsReadNumManageListener;
        return mMessageIsReadNumManageListener;
    }

    public interface MessageIsReadNumManageListener
    {
        void onRefreshReadNum(int type,int num);
    }


}
