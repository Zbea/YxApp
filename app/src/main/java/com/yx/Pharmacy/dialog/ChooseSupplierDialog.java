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
import com.yx.Pharmacy.adapter.SupplierAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.SupplierListModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by KID on 2018/7/13.
 */

public class ChooseSupplierDialog {

    private Context context;
    private AlertDialog alertDialog;
    private LinearLayout ll_dialog;
    private ListView mListView;
    private SupplierAdapter mAdapter;
    private List<SupplierListModel> models=new ArrayList<>();


    public ChooseSupplierDialog(Context context) {
        this.context = context;
        getStoreList((BaseActivity) context);
    }

    private void builder() {
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
        mAdapter=new SupplierAdapter(context,models);
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
        alertDialog.show();
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
        void select(SupplierListModel model);
    }
    public boolean isShown(){
        if (alertDialog!=null) {
            return alertDialog.isShowing();
        }else {
            return false;
        }
    }


    public void getStoreList(BaseActivity activity) {
        HomeNet.getHomeApi().storeList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<SupplierListModel>>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<List<SupplierListModel>> response) {
                        if (response.getCode().equals("200")) {
                            models=response.getData();
                            if (models.size()>0)
                            {
                                builder();
                            }

                        }else {
                            activity.getShortToastByString(response.getAlertmsg());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

}
