package com.yx.Pharmacy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yx.Pharmacy.R;


/**
 * Created by KID on 2017/11/13.
 */

public class LoadingDialog {

    public static final int Loading=1;
    public static final int Load_success=2;
    public static final int Load_fail=3;



    private Context context;
    private Dialog dialog;

    private String message;
    private boolean isShowMessage=true;
    private boolean isCancelable=false;
    private boolean isCancelOutside=false;

    TextView msgText;
    ProgressBar progressBar_load;

    /**
     * 设置提示信息
     * @param message
     * @return
     */
    public LoadingDialog setMessage(String message) {
        this.message = message;
        msgText.setText(message);
        return this;
    }
    /**
     * 设置是否显示提示信息
     * @param isShowMessage
     * @return
     */
    public LoadingDialog setShowMessage(boolean isShowMessage){
        this.isShowMessage=isShowMessage;
        return this;
    }
    /**
     * 设置是否可以按返回键取消
     * @param isCancelable
     * @return
     */

    public LoadingDialog setCancelable(boolean isCancelable){
        this.isCancelable=isCancelable;
        return this;
    }
    /**
     * 设置是否可以取消
     * @param isCancelOutside
     * @return
     */
    public LoadingDialog setCancelOutside(boolean isCancelOutside){
        this.isCancelOutside=isCancelOutside;
        return this;
    }


    public LoadingDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
    }

    public LoadingDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_loading, null);

        dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setContentView(view);

        msgText= (TextView) view.findViewById(R.id.tipTextView);
        progressBar_load=view.findViewById(R.id.progressBar1);

        if(isShowMessage){
            msgText.setVisibility(View.VISIBLE);
        }else{
            msgText.setVisibility(View.GONE);
        }
        dialog.setContentView(view);
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelOutside);


        return this;
    }
    public void show() {
        if(dialog!=null)
        dialog.show();
    }
    public void cancle() {
        if(dialog!=null)
        dialog.cancel();
    }

    /**
     * 动态设置提示信息
     * @param tip
     */
    public void setTip(String tip){
        msgText.setText(tip);
    }

    /**
     * 设置加载过程图片
     * @param loadState
     */
    public void setLoadState(int loadState){
        switch (loadState){
            case Loading:
                progressBar_load.setVisibility(View.VISIBLE);
                break;
            case Load_success:
                progressBar_load.setVisibility(View.GONE);
                //2秒后隐藏加载框
                cancleDelayed();
                break;
            case Load_fail:
                progressBar_load.setVisibility(View.GONE);
                //2秒后隐藏加载框
                cancleDelayed();
                break;
        }
    }

    private void cancleDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cancle();
            }
        }, 2000L);
    }
}
