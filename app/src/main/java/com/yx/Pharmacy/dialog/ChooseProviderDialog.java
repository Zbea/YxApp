package com.yx.Pharmacy.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.StoreAdapter;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by KID on 2018/7/13.
 */

public class ChooseProviderDialog {

    private Context context;
    private AlertDialog alertDialog;
    private LinearLayout ll_dialog;
    private ListView mListView;
    private StoreAdapter mAdapter;
    private List<MyShopModel> models=new ArrayList<>();


    public ChooseProviderDialog(Context context, List<MyShopModel> models) {
        this.context = context;
        this.models=models;
    }

    public ChooseProviderDialog builder() {
        alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.mycustom_dialog)).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        Window win = alertDialog.getWindow();
        win.setWindowAnimations(R.style.mystyle);
        win.getDecorView().setPadding(0, 0, 0, DensityUtils.dp2px(context,10));
        WindowManager.LayoutParams lp = win.getAttributes();
        // 设置弹出框的宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 设置弹出框的位置
        win.setGravity(Gravity.CENTER);
        win.setAttributes(lp);
        win.setContentView(R.layout.dialog_choose_store);

        mListView=win.findViewById(R.id.lv_store);
        mAdapter=new StoreAdapter(context,models);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cancle();
                if(dialogClickListener!=null)dialogClickListener.select(models.get(position));
            }
        });
        win.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });


        return this;
    }
    public void show() {
        alertDialog.show();
    }
    public void cancle() {
        alertDialog.cancel();
    }
    private DialogClickListener dialogClickListener;
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }
    public interface DialogClickListener{
        void select(MyShopModel model);
    }
    public boolean isShown(){
        if (alertDialog!=null) {
            return alertDialog.isShowing();
        }else {
            return false;
        }
    }
}
