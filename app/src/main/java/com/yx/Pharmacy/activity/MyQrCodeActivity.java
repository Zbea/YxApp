package com.yx.Pharmacy.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.SPUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的二维码
 */
public class MyQrCodeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_qrcode)
    ImageView iv_qrcode;
    private static final int INIT_QRCORD = 674;

    private String mQrcode;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyQrCodeActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_qr_code;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("我的二维码");
        getPathAndLoadImg();
        //https://blog.csdn.net/books1958/article/details/46346531   Zxing生成二维码

    }

    @OnClick({R.id.rl_back,R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_save:
                saveImage();
                break;
        }
    }

    private void saveImage() {
        new Thread(new Runnable() {
            Bitmap bitmap = null;

            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(mQrcode);
                    InputStream         is  = null;
                    BufferedInputStream bis = null;
                    try {
                        is = url.openConnection().getInputStream();
                        bis = new BufferedInputStream(is);
                        bitmap = BitmapFactory.decodeStream(bis);

                        File file = new File(Environment.getExternalStorageDirectory(), "/yxyy/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        file.createNewFile();
                        OutputStream outStream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                        outStream.flush();
                        outStream.close();
                        bitmap.recycle();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void  getPathAndLoadImg(){
        mQrcode = SPUtil.getString(this, Constants.KEY_QRCODE);
        if(!TextUtils.isEmpty(mQrcode)){
            GlideUtil.loadImgError(this,mQrcode,iv_qrcode,R.drawable.icon_logo);
        }else {
            iv_qrcode.setImageResource(R.drawable.icon_logo);
        }
    }

}
