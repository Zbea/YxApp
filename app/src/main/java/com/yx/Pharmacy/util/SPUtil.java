package com.yx.Pharmacy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SPUtil {

    private static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }


    /**
     * 保存boolean类型的数据
     * @param context 上下文
     * @param key 需要保存的键
     * @param value 需要保存的值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        getSp(context).edit().putBoolean(key, value).apply();
    }

    /**
     * 读取boolean类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @return 取出的boolean值 如果没有值则默认为false
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 读取boolean类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @param defaultValue 如果没有值则将此值作为默认值
     * @return 取出的boolean值 如果没有则默认为false
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSp(context).getBoolean(key, defaultValue);
    }

    /**
     * 保存int类型的数据
     * @param context 上下文
     * @param key 需要保存的键
     * @param value 需要保存的值
     */
    public static void putInt(Context context, String key, int value) {
        getSp(context).edit().putInt(key, value).apply();
    }

    /**
     * 读取int类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @return 取出的int值 如果没有值则默认为0
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    /**
     * 读取int类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @param defaultValue 如果没有值则将此值作为默认值
     * @return 取出的int值 如果没有则默认为0
     */
    public static int getInt(Context context, String key, int defaultValue) {
        return getSp(context).getInt(key, defaultValue);
    }

    /**
     * 保存String类型的数据
     * @param context 上下文
     * @param key 需要保存的键
     * @param value 需要保存的值
     */
    public static void putString(Context context, String key, String value) {
        getSp(context).edit().putString(key, value).apply();
    }

    /**
     * 读取String类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @return 取出的int值 如果没有值则默认为空
     */
    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    /**
     * 读取String类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @param defaultValue 如果没有值则将此值作为默认值
     * @return 取出的int值 如果没有则默认为空
     */
    public static String getString(Context context, String key, String defaultValue) {
        return getSp(context).getString(key, defaultValue);
    }

    /**
     * 保存Long类型的数据
     * @param context 上下文
     * @param key 需要保存的键
     * @param value 需要保存的值
     */
    public static void putLong(Context context, String key, Long value) {
        getSp(context).edit().putLong(key, value).apply();
    }

    /**
     * 读取String类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @return 取出的int值 如果没有值则默认为空
     */
    public static Long getLong(Context context, String key) {
        return getLong(context, key,0L);
    }

    /**
     * 读取String类型的数据
     * @param context 上下文
     * @param key 需要读取的键
     * @param defaultValue 如果没有值则将此值作为默认值
     * @return 取出的int值 如果没有则默认为空
     */
    public static Long getLong(Context context, String key, Long defaultValue) {
        return getSp(context).getLong(key, defaultValue);
    }


    /**
     * 保存intList
     * @param key
     * @param datalist
     */
    public static void setDataList(Context context,String key, List<Integer> datalist) {
        if (null == datalist || datalist.size() <= 0) {
            return;
        }

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        putString(context,key,strJson);

    }

    /**
     * 获取intList
     * @param key
     * @return
     */
    public static List<Integer> getDataList(Context context,String key) {
        List<Integer> datalist=new ArrayList<Integer>();
        String strJson = getString(context,key);
        if (TextUtils.isEmpty(strJson)) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<Integer>>() {
        }.getType());
        return datalist;

    }

    /**
     * 保存stringList
     * @param key
     * @param list
     */
    public static void setStringList(Context context,String key, List<String> list) {
        if (null == list || list.size() <= 0) {
            return;
        }

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(list);
        putString(context,key,strJson);
    }

    /**
     * 获取stringList
     * @param key
     * @return
     */
    public static List<String> getStringList(Context context,String key) {
        List<String> list=new ArrayList<String>();
        String strJson = getString(context,key);
        if (TextUtils.isEmpty(strJson)) {
            return list;
        }
        Gson gson = new Gson();
        list = gson.fromJson(strJson, new TypeToken<List<String>>() {
        }.getType());
        return list;
    }




    /**
     * 删除key数据
     */
    public static void delete(Context context, String key) {
        getSp(context).edit().remove(key).apply();
    }
}
