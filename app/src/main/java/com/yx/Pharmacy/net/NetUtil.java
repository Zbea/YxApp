package com.yx.Pharmacy.net;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2017/5/20.
 */

public class NetUtil {

    private static HashMap<String,String> urlMap;
    private static Toast toast;

    public static String getToken() {
        return SPUtil.getString(UiUtil.getContext(), Constants.KEY_TOKEN);
    }
    public static String getStoreid() {
        return SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORE_ID);
    }
    public static String getItemId() {
        return SPUtil.getString(UiUtil.getContext(), Constants.KEY_ITEM_ID);
    }

    public static String getVersion() {
        //获取包管理器
        PackageManager pm = UiUtil.getContext().getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(UiUtil.getContext().getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }



    public static HashMap<String,String> getUrlMap() {
        if(urlMap==null){
            urlMap = new HashMap<>();
        }
        urlMap.clear();
        return urlMap;
    }

    public static String isStringNull(String string) {
        return TextUtils.isEmpty(string)?"":string;
    }

    public  static void getShortToastByString(String hint){
        if (toast == null) {
            toast = Toast.makeText(UiUtil.getContext(), hint, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
    }

    /**
     * 过滤掉常见特殊字符,常见的表情
     */
    public static void setEtFilter(EditText et,int type,int count) {

        if (et == null) {
            return;
        }
        //表情过滤器
        InputFilter emojiFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                       int dstart, int dend) {
                Pattern emoji = Pattern.compile(
                        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    return "";
                }
                return null;
            }
        };
        //特殊字符过滤器
        InputFilter specialCharFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regexStr = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(regexStr);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.matches()) {
                    return "";
                } else {
                    return null;
                }

            }
        };

        if (type==0) {
            if (count!=0) {
                et.setFilters(new InputFilter[]{emojiFilter,new InputFilter.LengthFilter(count)});
            }else {
                et.setFilters(new InputFilter[]{emojiFilter});
            }
        }else {
            if (count!=0) {
                et.setFilters(new InputFilter[]{emojiFilter,
                                                specialCharFilter,new InputFilter.LengthFilter(count)});
            }else {
                et.setFilters(new InputFilter[]{emojiFilter,
                                                specialCharFilter});
            }
        }
    }



    public static boolean isAvilible(Context context, String packageName){
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
