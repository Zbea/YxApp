package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.util.HttpDownloader;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Zbea
 * Date: 2018/9/28 9:56
 * Description:  pdf显示
 */
public class PdfActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.pv_pdf)
    PDFView pvPdf;

    private File file;
    private boolean isExists;
    private String url;
    private String id;

    public static void startActivity(Activity activity,String url,String id) {
        Intent intent = new Intent(activity, PdfActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void init() {
        id=getIntent().getStringExtra("id");
        url=getIntent().getStringExtra("url");
//        url="https://invtest.jss.com.cn/group1/M00/06/DA/wKjScVubskqANo4AAABsr7DQC5o273.pdf";
        tvTitle.setText("发票展示");
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        file = new File(getExternalCacheDir()+"/pdf/"+id+ ".pdf");
        //判断是否存在文件
        if (file.exists())
        {
            isExists = true;
        }
        if (isExists)
        {
            LoadPdf();
        } else
        {
            FetchPdf();
        }
    }

    private Handler mhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            LoadPdf();
        }
    };

    private void LoadPdf()
    {
        pvPdf.fromFile(file).load();
    }

    private void FetchPdf()
    {
        HttpDownloader.doGetDown(url, file, new HttpDownloader.OnResponseListener()
        {
            @Override
            public void onResponse(Object o)
            {
                if (o!=null)
                    mhandler.sendEmptyMessage(1);
            }
        });

    }

}
