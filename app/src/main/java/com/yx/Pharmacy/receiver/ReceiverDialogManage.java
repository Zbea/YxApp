package com.yx.Pharmacy.receiver;

import com.yx.Pharmacy.manage.MessageIsReadNumManage;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 刷新显示dialog弹框
 */
public class ReceiverDialogManage {


    private static ReceiverDialogManage mReceiverDialogManage= null;
    private ReceiverDialogManageListener mReceiverDialogManageListener;

    private ReceiverDialogManage() {

    }

    public static ReceiverDialogManage newInstance()
    {
        if (mReceiverDialogManage==null)
        {
            mReceiverDialogManage=new ReceiverDialogManage();
        }
        return mReceiverDialogManage;
    }

    public void showDialog(JSONObject object)
    {
        if (mReceiverDialogManageListener!=null)
            mReceiverDialogManageListener.onDialogShow(object);
    }


    public ReceiverDialogManageListener setReceiverDialogManageListener(ReceiverDialogManageListener receiverDialogManageListener)
    {
        if (receiverDialogManageListener!=null)
            mReceiverDialogManageListener=receiverDialogManageListener;
        return mReceiverDialogManageListener;
    }

    public interface ReceiverDialogManageListener
    {
        void onDialogShow(JSONObject jsonObject);
    }


}
