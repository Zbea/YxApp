package com.yx.Pharmacy.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.SPUtil;


/**
 * Created by KID on 2018/7/13.
 */

public class HomeAdDialog {

    private Context context;
    private AlertDialog alertDialog;
    private HomeAdvanceModel.GoldBean alert;

    public HomeAdDialog(Context context,HomeAdvanceModel.GoldBean alert) {
        this.context = context;
        this.alert=alert;
    }
    public HomeAdDialog builder() {
        alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.mycustom_dialog)).create();
        alertDialog.show();
        Window win = alertDialog.getWindow();
        win.setWindowAnimations(R.style.mystyle);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        // 设置弹出框的宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        // 设置弹出框的位置
        win.setGravity(Gravity.CENTER);
        win.setAttributes(lp);
        win.setContentView(R.layout.dialog_home_ad);
        win.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });
        win.findViewById(R.id.tv_no_notice_today).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
                SPUtil.putLong(context, Constants.KEY_LAST_CLICK_SP_AD,System.currentTimeMillis());
            }
        });
        ImageView iv_ad=win.findViewById(R.id.iv_ad);
        GlideUtil.loadImgNoStyle(context,alert.image_src,iv_ad);
        iv_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
                if(dialogClickListener!=null)dialogClickListener.clickAd();
            }
        });


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
        void clickAd();
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }

}
