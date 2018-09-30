package com.yx.Pharmacy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;


/**
 * Created by KID on 2018/7/13.
 */

public class ConfirmDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout ll_dialog;

    private String content="";
    private String title="";
    private String ok="";
    private String cancle="";
    private int bgType=1;

    private TextView tv_dialog_title;
    private TextView tv_content;
    private TextView tv_ok;
    private TextView tv_cancle;
    private ImageView iv_top_bg;

    public ConfirmDialog setContent(String content) {
        this.content = content;
        return this;
    }
    public ConfirmDialog setTitle(String title) {
        this.title = title;
        return this;
    }
    public ConfirmDialog setOk(String ok) {
        this.ok = ok;
        return this;
    }
    public ConfirmDialog setcancle(String cancle) {
        this.cancle = cancle;
        return this;
    }
    public ConfirmDialog setTopBg(int bgType){
        this.bgType=bgType;
        return this;
    }
    public ConfirmDialog(Context context) {
        this.context = context;
    }

    public ConfirmDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_comfirm, null);

        ll_dialog=view.findViewById(R.id.ll_dialog);
        tv_dialog_title=view.findViewById(R.id.tv_dialog_title);
        tv_content=view.findViewById(R.id.tv_content);
        tv_ok=view.findViewById(R.id.tv_ok);
        tv_cancle=view.findViewById(R.id.tv_cancle);
        iv_top_bg=view.findViewById(R.id.iv_top_bg);

        tv_dialog_title.setText(title);
        tv_content.setText(content);
        tv_ok.setText(ok);
        tv_cancle.setText(cancle);
        if(bgType==1){
            iv_top_bg.setImageResource(R.drawable.tchtybj);
        }else {
            iv_top_bg.setImageResource(R.drawable.tcbtbj);
        }

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogClickListener!=null)dialogClickListener.ok();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
                if(dialogClickListener!=null)dialogClickListener.cancle();
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
    private DialogClickListener dialogClickListener;
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }
    public interface DialogClickListener{
        void ok();
        void cancle();
    }
}
