package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BasePermissionActivity;
import com.yx.Pharmacy.dialog.ConfirmDialog;
import com.yx.Pharmacy.dialog.LoadingDialog;
import com.yx.Pharmacy.dialog.ScanAfterDialog;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.ScanPresenter;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SelectStoreUtil;
import com.yx.Pharmacy.view.IScanView;
import com.yx.Pharmacy.zxing.camera.CameraManager;
import com.yx.Pharmacy.zxing.decoding.CaptureActivityHandler;
import com.yx.Pharmacy.zxing.decoding.InactivityTimer;
import com.yx.Pharmacy.zxing.decoding.RGBLuminanceSource;
import com.yx.Pharmacy.zxing.utils.MiPictureHelper;
import com.yx.Pharmacy.zxing.view.ViewfinderView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Vector;

import butterknife.OnClick;

import static com.yx.Pharmacy.activity.LoginActivity.START_LOGIN_RESULT;


public class CaptureActivity extends BasePermissionActivity implements SurfaceHolder.Callback, View.OnClickListener, IScanView {
    private static final int REQUEST_CODE_SCAN_GALLERY = 100;

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private ProgressDialog mProgress;
    private String photoPath;
    private Bitmap scanBitmap;
    //	private Button cancelScanButton;
    public static final int RESULT_CODE_QR_SCAN = 1008;
    public static final String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";


    private TextView tv_more;
    private ImageView iv_light;

    LoadingDialog loadingDialog;
    private ScanPresenter mPresenter;
    private SelectStoreUtil mSelectStoreUtil;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, CaptureActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_capture;
    }
    @Override
    protected void init() {
        initView();
    }
    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.color_main, this, true);

        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_content);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        tv_more= (TextView) findViewById(R.id.tv_more);
//        tv_more.setVisibility(View.VISIBLE);
        tv_more.setText("相册");
        tv_more.setOnClickListener(this);
        iv_light=findViewById(R.id.iv_light);

        mPresenter=new ScanPresenter(this);

    }
    private boolean isLightOn=false;//闪光灯是否打开
    @OnClick({R.id.tv_more,R.id.iv_light,R.id.rl_back})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                if(isLightOn){
                    turnFlashLightOff();
                }
                finish();
                break;
            case R.id.tv_more:
                if(isLightOn){
                    turnFlashLightOff();
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 使用以上这种模式，并添加以上两句
                startActivityForResult(intent, REQUEST_CODE_SCAN_GALLERY);
                break;
            case R.id.iv_light:
                if(isLightOn){
                    turnFlashLightOff();
                }else {
                    turnFlashlightOn();
                }
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent intent) {
        if (resultCode==RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SCAN_GALLERY:

                    // 获取选中图片的路径
                    Cursor cursor = getContentResolver().query(intent.getData(), null, null, null, null);
                    String pickPath = MiPictureHelper.getPath(CaptureActivity.this, intent.getData());  // 获取图片路径的方法调用
                    if (null != cursor && cursor.moveToFirst()) {
                        photoPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        cursor.close();
                    } else {
                        photoPath = pickPath;
                    }

                    loadingDialog=new LoadingDialog(CaptureActivity.this);
                    loadingDialog.builder().setMessage("正在识别").setCancelable(false).show();

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            Result result = scanningImage(photoPath);
                            L.i("dddddddddddddddddddddddddd");
                            if (result != null) {
                                Message msg = mHandler.obtainMessage();
                                msg.what=MESSAGE_SUCCESS;
                                msg.obj=result.getText();
                                mHandler.sendMessage(msg);
                            } else {
                                //失败
                                mHandler.sendEmptyMessage(MESSAGE_FAIL);
                            }
                        }
                    }).start();
                    break;
            }
        }else if (resultCode==START_LOGIN_RESULT) {
            mSelectStoreUtil = new SelectStoreUtil(this, (MyShopModel myShopModel) -> {
            });
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.scanner_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
        if(myHandler!=null){//防止空指针
            myHandler.removeMessages(10001);
        }
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        //FIXME
        if (TextUtils.isEmpty(resultString)) {
            Toast.makeText(CaptureActivity.this, "未识别到二维码", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("kid","handleDecode===="+resultString);
            doWithResultText(resultString);
        }
//        CaptureActivity.this.finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    private final static int MESSAGE_FAIL=1010;
    private final static int MESSAGE_SUCCESS=1011;

    private Handler mHandler = new MyHandler(this);


    class MyHandler extends Handler {

        private WeakReference<Activity> activityReference;

        public MyHandler(Activity activity) {
            activityReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MESSAGE_FAIL://扫码识别失败
                    loadingDialog.cancle();
                    Toast.makeText(CaptureActivity.this, "图片中未识别到二维码", Toast.LENGTH_SHORT).show();
                    break;
                case MESSAGE_SUCCESS://扫码识别成功
                    loadingDialog.cancle();
                    String resultText= (String) msg.obj;
                    Log.e("kid","判断内容="+resultText);
                    doWithResultText(resultText);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private void doWithResultText(String resultText) {
        mPresenter.getScanData(this,resultText);
    }

    /**
     * 扫描二维码图片的方法
     * @param path
     * @return
     */
    public Result scanningImage(String path) {
        if(TextUtils.isEmpty(path)){
            return null;
        }
        Hashtable<DecodeHintType, String> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF8"); //设置二维码内容的编码

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        scanBitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 获取新的大小
        int sampleSize = (int) (options.outHeight / (float) 200);
        if (sampleSize <= 0)
            sampleSize = 1;
        options.inSampleSize = sampleSize;
        scanBitmap = BitmapFactory.decodeFile(path, options);
        RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        try {
            return reader.decode(bitmap1, hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void turnFlashlightOn() {
        try {
            CameraManager.get().setFlashLight(true);
            isLightOn = true;
//            iv_light.setImageResource(R.drawable.scan_flashligh_open);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void turnFlashLightOff() {
        try {
            CameraManager.get().setFlashLight(false);
            isLightOn = false;
//            iv_light.setImageResource(R.drawable.scan_flashligh_close);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(isLightOn){
                turnFlashLightOff();
            }
            finish();
            return true;
        }
        return false;
    }
    private ScanAfterDialog scanAfterDialog;
    @Override
    public void scanResultProduct(DrugModel data) {
//        getShortToastByString("扫描结果");
        scanAfterDialog=new ScanAfterDialog(this,data);
        scanAfterDialog.builder().show();
        scanAfterDialog.setDialogClickListener(new ScanAfterDialog.DialogClickListener() {
            @Override
            public void addShopCar(boolean isRemember, DrugModel model) {
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(CaptureActivity.this,1);
                    return;
                }else if(TextUtils.isEmpty(NetUtil.getStoreid())){
                    if (mSelectStoreUtil!=null) {
                        mSelectStoreUtil.loadMyShop(CaptureActivity.this,true);
                        return;
                    }else {
                        mSelectStoreUtil = new SelectStoreUtil(CaptureActivity.this, new SelectStoreUtil.OnSelectStoreListener() {
                            @Override
                            public void onSelect(MyShopModel myShopModel) {
                            }
                        });
                    }
                    return;
                }
                if(model.getType()==1||model.getType()==2){ //特价商品特殊处理
                    showComfirmDialog1(model);
                }else {
                    mPresenter.addCartProduct(CaptureActivity.this,model);
                }
            }

            @Override
            public void goProductDetail(boolean isRemember, DrugModel model) {
                ProductDetailActivity.startActivity(CaptureActivity.this,model.getItemid()+"");
            }

            @Override
            public void restart() {
                handler.restartPreviewAndDecode();
            }
        });
    }
    @Override
    public void scanNoData() {//扫描结果无此商品
        getShortToastByString("暂无匹配到当前商品,请再试一次");
        myHandler.sendEmptyMessageDelayed(10001,2000L);

//        ScanTipDialog scanTipDialog=new ScanTipDialog(this);
//        scanTipDialog.builder().show();
//        scanTipDialog.setDialogClickListener(new ScanTipDialog.DialogClickListener() {
//            @Override
//            public void goHome() {
//                if(isLightOn){
//                    turnFlashLightOff();
//                }
//                finish();
//            }
//        });
    }


    Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 10001:
                    handler.restartPreviewAndDecode();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    @Override
    public void ifFuGai() {
        showComfirmDialog2();
    }

    @Override
    public void compelete() {

    }
    private DrugModel pModel;
    private void showComfirmDialog1(DrugModel model) {
        pModel=model;
        ConfirmDialog confirmDialog=new ConfirmDialog(this);
        confirmDialog.setTitle("温馨提示").setContent("当前商品为限时抢购商品").setcancle("原价购买").setOk(TextUtils.equals(model.getType()+"", "1")?"秒杀购买":"特价购买").builder().show();;
        confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
            @Override
            public void ok() {//第一次特价购买，不需要传是否覆盖
                confirmDialog.cancle();
                mPresenter.miaoshaBuy(CaptureActivity.this,pModel,"1");
            }
            @Override
            public void cancle() {//原价购买(加入购物车)
                confirmDialog.cancle();
                mPresenter.addCartProduct(CaptureActivity.this,model);
            }
        });
    }
    //询问是否覆盖
    private void showComfirmDialog2() {
        ConfirmDialog confirmDialog=new ConfirmDialog(this);
        confirmDialog.setTitle("温馨提示").setContent("购物车中已有秒杀商品，是否覆盖").setcancle("否").setOk("是").builder().show();
        confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
            @Override
            public void ok() {//覆盖
                confirmDialog.cancle();
                mPresenter.miaoshaBuy(CaptureActivity.this,pModel,"1");
            }
            @Override
            public void cancle() {//不覆盖
                confirmDialog.cancle();
                mPresenter.miaoshaBuy(CaptureActivity.this,pModel,"0");
            }
        });
    }
}