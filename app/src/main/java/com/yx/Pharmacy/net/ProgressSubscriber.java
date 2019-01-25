package com.yx.Pharmacy.net;

import android.app.Activity;
import android.net.ParseException;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.util.L;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.yx.Pharmacy.net.ProgressSubscriber.ExceptionReason.CONNECT_ERROR;
import static com.yx.Pharmacy.net.ProgressSubscriber.ExceptionReason.CONNECT_TIMEOUT;
import static com.yx.Pharmacy.net.ProgressSubscriber.ExceptionReason.PARSE_ERROR;
import static com.yx.Pharmacy.net.ProgressSubscriber.ExceptionReason.UNKNOWN_ERROR;

/**
 * Created time  2018/3/27 0027
 * @author : mcx
 * 类描述 : 
 */

public abstract class ProgressSubscriber<T extends BasisBean> implements Observer<T>
{
    private Activity activity;
    //  Activity 是否在执行onStop()时取消订阅
    private boolean isAddInStop = false;
    private CommonDialogUtils dialogUtils;
    public ProgressSubscriber(Activity activity) {
        this.activity = activity;
        if (activity!=null)
        {
            dialogUtils=new CommonDialogUtils();
            dialogUtils.showProgress(activity);
        }

    }

    public ProgressSubscriber(Activity activity, boolean isShowLoading) {
        this.activity = activity;
        if (activity!=null)
        {
            dialogUtils=new CommonDialogUtils();
            if (isShowLoading) {
                dialogUtils.showProgress(activity,"Loading...");
            }
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        dismissProgress();
        if (TextUtils.equals(response.getCode(),"200")) {
            onSuccess(response);
        } else {
            if (TextUtils.equals(response.getCode(),"204"))
            {
                onFail(response);
            }
            try {
                if(!TextUtils.isEmpty(response.getAlertmsg())){
                    NetUtil.getShortToastByString(response.getAlertmsg());
                }else if(!TextUtils.isEmpty(response.getMessage())){
                    NetUtil.getShortToastByString(response.getMessage());
                }else {
//                    NetUtil.getShortToastByString("请求出错啦");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dismissProgress(){
        if(dialogUtils!=null){
            dialogUtils.dismissProgress();
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissProgress();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);
    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {

    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                break;
            case CONNECT_TIMEOUT:
                break;
            case BAD_NETWORK:
                break;

            case PARSE_ERROR:
                break;

            case UNKNOWN_ERROR:
            default:
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
