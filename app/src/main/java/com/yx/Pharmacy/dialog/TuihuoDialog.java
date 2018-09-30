package com.yx.Pharmacy.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.yx.Pharmacy.R;


/**
 * Created by KID on 2018/7/13.
 */

public class TuihuoDialog {

    private Context context;
    private AlertDialog alertDialog;
    private EditText edit_name;
    private EditText edit_orderid;
    public TuihuoDialog(Context context) {
        this.context = context;
    }
    public TuihuoDialog builder() {
        alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.mycustom_dialog)).create();
        alertDialog.setView(new EditText(context));
        alertDialog.show();
        Window win = alertDialog.getWindow();
        win.setWindowAnimations(R.style.mystyle);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        // 设置弹出框的宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 设置弹出框的位置
        win.setGravity(Gravity.BOTTOM);
        win.setAttributes(lp);
        win.setContentView(R.layout.dialog_tuihuo);
        edit_name=win.findViewById(R.id.edit_name);
        edit_orderid=win.findViewById(R.id.edit_orderid);

        win.findViewById(R.id.rl_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });


        win.findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edit_name.getText().toString().trim())){
                    Toast.makeText(context, "请输入快递公司名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edit_orderid.getText().toString().trim())){
                    Toast.makeText(context, "请输入快递单号", Toast.LENGTH_SHORT).show();
                    return;
                }
                cancle();
                if(dialogClickListener!=null)dialogClickListener.commitTuihuo(edit_name.getText().toString().trim(),edit_orderid.getText().toString().trim());
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
        void commitTuihuo(String name, String num);
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }

}
