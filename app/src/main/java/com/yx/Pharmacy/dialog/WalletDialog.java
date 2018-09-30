package com.yx.Pharmacy.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.util.DensityUtils;


/**
 * Created by KID on 2018/7/13.
 */

public class WalletDialog implements View.OnClickListener {

    private Context context;
    private AlertDialog alertDialog;

    public WalletDialog(Context context) {
        this.context = context;
    }
    public WalletDialog builder() {
        alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.mycustom_dialog)).create();
        alertDialog.show();
        Window win = alertDialog.getWindow();
        win.setWindowAnimations(R.style.mystyle);
        win.getDecorView().setPadding(0, 0, 0, DensityUtils.dp2px(context,10));
        WindowManager.LayoutParams lp = win.getAttributes();
        // 设置弹出框的宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 设置弹出框的位置
        win.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        win.setAttributes(lp);
        win.setContentView(R.layout.dialog_wallet);
        win.findViewById(R.id.tv_tixian).setOnClickListener(this);
        win.findViewById(R.id.tv_chongzhi).setOnClickListener(this);
        win.findViewById(R.id.tv_zhichu).setOnClickListener(this);
        win.findViewById(R.id.tv_shouru_or_tuikuan).setOnClickListener(this);
        win.findViewById(R.id.tv_cancle).setOnClickListener(this);


        return this;
    }
    public void show(){
        alertDialog.show();
    }
    public void cancle(){
        alertDialog.dismiss();
    }
    public DialogClickListener dialogClickListener;

    public interface DialogClickListener{
        void tixian();
        void chongzhi();
        void zhichu();
        void shouru_tuikuan();
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_tixian://提现
                cancle();
                if(dialogClickListener!=null)dialogClickListener.tixian();
                break;
            case R.id.tv_chongzhi://充值
                cancle();
                if(dialogClickListener!=null)dialogClickListener.chongzhi();
                break;
            case R.id.tv_zhichu://支出
                cancle();
                if(dialogClickListener!=null)dialogClickListener.zhichu();
                break;
            case R.id.tv_shouru_or_tuikuan://收入/退款
                cancle();
                if(dialogClickListener!=null)dialogClickListener.shouru_tuikuan();
                break;
            case R.id.tv_cancle:
                cancle();
                break;
        }
    }

}
