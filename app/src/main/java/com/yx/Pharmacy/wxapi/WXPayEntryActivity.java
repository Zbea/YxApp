package com.yx.Pharmacy.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yx.Pharmacy.activity.OrderDetailActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.StackManager;
import com.yx.Pharmacy.util.UiUtil;


public class WXPayEntryActivity extends Activity
		implements IWXAPIEventHandler
{
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	api = WXAPIFactory.createWXAPI(this, Constants.WX_ID);
		api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if(resp.errCode==0) {
			Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
			String ordernum = SPUtil.getString(UiUtil.getContext(), Constants.KEY_ORDER_NUMBER);
			if (!TextUtils.isEmpty(ordernum)) {
				OrderDetailActivity.startActivity(this, ordernum);
			}
			StackManager.getManagerStack().popAllActivityExceptOne();
		}else {
			L.i("resp.errCode:"+resp.errCode);
			Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
		}
		finish();

	}
}