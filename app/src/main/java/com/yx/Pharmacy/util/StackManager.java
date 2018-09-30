package com.yx.Pharmacy.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2017/1/4.
 */
public class StackManager {
    private        Stack<Activity> activityStack;
    private static StackManager    instance;
    private StackManager(){}
    public static StackManager getManagerStack(){
        if(instance==null){
            synchronized (StackManager.class) {
                if(instance == null) {
                    instance = new StackManager();
                }
            }
        }
        return instance;
    }
    public void popActivity(){
        Activity activity =activityStack.lastElement();
        if(activity!=null){
            activity.finish();
            activityStack.remove(activity);
            activity=null;
        }
    }
    public void popActivity(Activity activity){
        if(activity!=null){
            activity.finish();
            activityStack.remove(activity);
            activity=null;
        }
    }

    public Activity currentActivity(){
        Activity activity =activityStack.lastElement();
        return activity;
    }

    public void pushActivity(Activity activity){
        if(activityStack==null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    public void popAllActivityExceptOne(){
        if(activityStack!=null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    public void clearAllActivity(){
        if(activityStack!=null)
            activityStack.clear();
    }


    public void popActivityTwo(){
        if(activityStack!=null&&activityStack.size()>1){
            activityStack.get(0).finish();
        }
    }

}

