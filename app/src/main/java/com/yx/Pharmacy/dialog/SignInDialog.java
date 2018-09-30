package com.yx.Pharmacy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.SignInModel;


/**
 * Created by KID on 2018/7/13.
 */

public class SignInDialog {

    private Context context;
    private Dialog dialog;
    private RelativeLayout rl_dialog;

    private SignInModel model;


    public SignInDialog(Context context, SignInModel model) {
        this.context = context;
        this.model=model;
    }

    public SignInDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_sign_in, null);

        rl_dialog=view.findViewById(R.id.rl_dialog);

        dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setCancelable(true);
        dialog.setContentView(view);


        rl_dialog.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }
    public void show() {
        dialog.show();
    }
    public void cancle() {
        dialog.cancel();
    }
}
