package com.yx.Pharmacy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.loader.GlideImageLoader;
import com.yx.Pharmacy.model.ProductDetailModel;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.widget.AmountView;


/**
 * Created by KID on 2018/7/13.
 */

public class FunctionGuidDialog {

    private Context context;
    private Dialog alertDialog;
    private int[] mainStrs={R.drawable.icon_function_guid_main_qiandao,R.drawable.icon_function_guid_main_servier,R.drawable.icon_function_guid_main_cart};
    private int[] myStrs={R.drawable.icon_function_guid_my_jifen,R.drawable.icon_function_guid_my_yue,R.drawable.icon_function_guid_my_coupon};
    private int position=0;
    private int[] strs;
    private  ImageView ivGuid;

    public FunctionGuidDialog(Context context, int type) {
        this.context = context;
        strs=type==0?mainStrs:myStrs;
    }
    public FunctionGuidDialog builder() {
        alertDialog = new Dialog(context, R.style.DialogStyle);
        alertDialog.show();
        Window win = alertDialog.getWindow();
        win.setWindowAnimations(R.style.mystyle);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        // 设置弹出框的宽高
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);
        win.setContentView(R.layout.activity_function_guid);

        ivGuid=win.findViewById(R.id.iv_guid);
        setClick();
        ivGuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==2)
                {
                    alertDialog.dismiss();
                }
                else
                {
                    position++;
                    setClick();
                }
            }
        });
        return this;
    }
    private void setClick()
    {
        ivGuid.setBackgroundResource(strs[position]);
    }

}
