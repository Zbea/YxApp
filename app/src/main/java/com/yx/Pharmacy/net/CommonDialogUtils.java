package com.yx.Pharmacy.net;

import android.content.Context;

/**
 * Created time  2018/3/28 0028
 * @author : mcx
 * 类描述 : 
 */

public class CommonDialogUtils {
    //  加载进度的dialog
    private CustomProgressDialog mProgressDialog;

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context, String msg) {
       if (context == null ) {
            return;
        }
        if(mProgressDialog==null){
            mProgressDialog= new CustomProgressDialog.Builder(context)
                    .setMessage(msg)
                    .setCancelOutside(false)
                    .create();
        }
        if(mProgressDialog!=null&&!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context) {
        /*if (activity == null || activity.isFinishing()) {
            return;
        }*/
        if(mProgressDialog==null){
            mProgressDialog= new CustomProgressDialog.Builder(context)
                    .setMessage("加载中...")
                    .setCancelOutside(false)
                    .create();
        }
        if(mProgressDialog!=null&&!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 取消ProgressDialog
     */
    public void dismissProgress() {
        if (mProgressDialog != null&&mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
