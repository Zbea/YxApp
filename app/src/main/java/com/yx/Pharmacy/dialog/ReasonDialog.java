package com.yx.Pharmacy.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ReasonAdapter;
import com.yx.Pharmacy.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by KID on 2018/7/13.
 */

public class ReasonDialog {

    private Context context;
    private AlertDialog alertDialog;
    private ListView mListView;
    private ReasonAdapter mAdapter;
    private List<String>reasons=new ArrayList<>();

    public ReasonDialog(Context context, List<String>reasons) {
        this.context = context;
        this.reasons=reasons;
    }
    public ReasonDialog builder() {
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
        win.setContentView(R.layout.dialog_reason);
        win.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });
        mListView=win.findViewById(R.id.lv_reason);
        mAdapter=new ReasonAdapter(context,reasons);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cancle();
                if(dialogClickListener!=null)dialogClickListener.reason(reasons.get(position));
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
        void reason(String reason);
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }

}
