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
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.util.GlideUtil;


/**
 * Created by KID on 2018/7/13.
 * 扫描提示
 */

public class ScanAfterDialog implements View.OnClickListener {

    private Context context;
    private Dialog dialog;
    private LinearLayout ll_dialog;
    private DrugModel drugModel;
    private boolean isRemember=false;
    ImageView iv_jizhu;

    public ScanAfterDialog(Context context,DrugModel drugModel) {
        this.context = context;
        this.drugModel=drugModel;

    }

    public ScanAfterDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_scan_after, null);

        ll_dialog=view.findViewById(R.id.ll_dialog);
        TextView tv_title=view.findViewById(R.id.tv_title);
        tv_title.setText(drugModel.getTitle());
        TextView tv_price=view.findViewById(R.id.tv_price);
        tv_price.setText(drugModel.getPrice());
        iv_jizhu=view.findViewById(R.id.iv_jizhu);

        ImageView iv_product =view.findViewById(R.id.iv_product);
        GlideUtil.loadRoundImg(context,drugModel.getThumb(),iv_product);
        view.findViewById(R.id.ll_add_shopcar).setOnClickListener(this);
        view.findViewById(R.id.ll_go_product_detail).setOnClickListener(this);
        view.findViewById(R.id.ll_remember).setOnClickListener(this);
        view.findViewById(R.id.iv_cancel).setOnClickListener(this);

        dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setCancelable(false);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_remember:
                if(isRemember){
                    isRemember=false;
                    iv_jizhu.setImageResource(R.drawable.jizhu_uncheck);
                }else {
                    isRemember=true;
                    iv_jizhu.setImageResource(R.drawable.jizhu_check);
                }
                break;
            case R.id.ll_add_shopcar:
                cancle();
                if(dialogClickListener!=null)dialogClickListener.addShopCar(isRemember,drugModel);
                break;
            case R.id.ll_go_product_detail:
                cancle();
                if(dialogClickListener!=null)dialogClickListener.goProductDetail(isRemember,drugModel);
                break;
            case R.id.iv_cancel:
                cancle();
                if(dialogClickListener!=null)dialogClickListener.restart();
                break;
        }

    }

    public interface DialogClickListener{
        void addShopCar(boolean isRemember, DrugModel model);
        void goProductDetail(boolean isRemember, DrugModel model);
        void restart();
    }
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }
}
