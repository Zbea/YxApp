package com.yx.Pharmacy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;


/**
 * Created by KID on 2018/7/13.
 * 扫描提示
 */

public class ScanTipDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout ll_dialog;

//    private String content="";
//    private String title="";
//    private String ok="";
//    private String cancle="";
//    private String desc="";

    private TextView tv_dialog_title;
    private TextView tv_content;
    private TextView tv_ok;
    private TextView tv_cancle;
    private TextView tv_desc;

//    public ScanTipDialog setContent(String content) {
//        this.content = content;
//        return this;
//    }
//    public ScanTipDialog setTitle(String title) {
//        this.title = title;
//        return this;
//    }
//    public ScanTipDialog setOk(String ok) {
//        this.ok = ok;
//        return this;
//    }
//    public ScanTipDialog setcancle(String cancle) {
//        this.cancle = cancle;
//        return this;
//    }
//    public ScanTipDialog setDesc(String desc) {
//        this.desc = desc;
//        return this;
//    }



    public ScanTipDialog(Context context) {
        this.context = context;
    }

    public ScanTipDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_scan_tip, null);

        ll_dialog=view.findViewById(R.id.ll_dialog);
        tv_dialog_title=view.findViewById(R.id.tv_dialog_title);
        tv_content=view.findViewById(R.id.tv_content);
        tv_ok=view.findViewById(R.id.tv_ok);
        tv_cancle=view.findViewById(R.id.tv_cancle);
        tv_desc=view.findViewById(R.id.tv_desc);

//        tv_dialog_title.setText(title);
//        tv_content.setText(content);
//        tv_ok.setText(ok);
//        tv_cancle.setText(cancle);
//        tv_desc.setText(desc);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogClickListener!=null)dialogClickListener.goHome();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });

        dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setCancelable(true);
        dialog.setContentView(view);


        ll_dialog.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }
    public void show() {
        if(dialog!=null)dialog.show();
    }
    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }
    public DialogClickListener dialogClickListener;
    public interface DialogClickListener{
        void goHome();
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }

}
