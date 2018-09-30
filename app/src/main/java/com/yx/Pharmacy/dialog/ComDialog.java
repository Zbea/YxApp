package com.yx.Pharmacy.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;


/**
 * Created by dell on 2017/10/23.
 */

public class ComDialog {
    private Context context;
    private AlertDialog dialog;
    private Display display;

    private CardView cardView;
    private TextView titleTv;
    private TextView contentTv;
    private TextView cancleTv;
    private TextView okTv;


    public ComDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }
    private String title;
    private boolean is;
    private String content;//提示文案
    private String cancle="取消";//取消文案
    private String ok ="确认";//确认文案
    public ComDialog setTitle(String title) {
        this.title = title;
        return this;
    }
    public ComDialog setContent(String content) {
        this.content = content;
        return this;
    }
    public ComDialog setCancle(String cancle) {
        this.cancle = cancle;
        return this;
    }
    public ComDialog setOk(String ok) {
        this.ok = ok;
        return this;
    }

    public ComDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_com, null);

        cardView=view.findViewById(R.id.cardView);
        titleTv=view.findViewById(R.id.tv_dialog_title);
        contentTv=view.findViewById(R.id.tv_dialog_content);
        cancleTv=view.findViewById(R.id.tv_cancle);
        okTv=view.findViewById(R.id.tv_ok);

        if(!TextUtils.isEmpty(title))titleTv.setText(title);
        if (!is)
        {
            titleTv.setVisibility(View.GONE);
            contentTv.setMinHeight(200);
        }
        if(!TextUtils.isEmpty(content))contentTv.setText(content);
        if(!TextUtils.isEmpty(cancle))cancleTv.setText(cancle);
        if(!TextUtils.isEmpty(ok))okTv.setText(ok);

        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
                if(dialogClickListener!=null)dialogClickListener.cancle();
            }
        });
        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
                if(dialogClickListener!=null)dialogClickListener.ok();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog=builder.create();
//        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        cardView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public ComDialog setTitleView(boolean is) {
        this.is=is;
        return this;
    }

    public void show() {
        dialog.show();
    }
    public void cancle() {
        dialog.cancel();
    }
    public DialogClickListener dialogClickListener;
    public interface DialogClickListener{
        void cancle();
        void ok();
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }
}
