package com.yx.Pharmacy.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


/**
 * Created by Stay on 4/2/2016.
 * Powered by www.stay4it.com
 */
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    private static final long MAX_INTERVAL = 5 * 60 * 1000;
    private static AppStatusTracker tracker;
    private Application application;
    private int mAppStatus = ConstantValues.STATUS_ONLINE;
    private boolean isForground;
    private int activeCount;
    private long timestamp;
    private boolean isScreenOff;


    private AppStatusTracker(Application application) {
        this.application = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        tracker = new AppStatusTracker(application);
    }

    public static AppStatusTracker getInstance() {
        return tracker;
    }

    public void setAppStatus(int status) {
        this.mAppStatus = status;
    }

    public int getAppStatus() {
        return this.mAppStatus;
    }

    public boolean isForground() {
        return isForground;
    }

    private void onScreenOff(boolean isScreenOff) {
        this.isScreenOff = isScreenOff;
    }

    public boolean checkIfShowGesture() {
        if (mAppStatus == ConstantValues.STATUS_ONLINE) {
            if (isScreenOff) {
                return true;
            }
            if (timestamp != 0l && System.currentTimeMillis() - timestamp > MAX_INTERVAL) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//        L.e(activity.toString() + " onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
//        L.e(activity.toString() + " onActivityStarted");
        activeCount++;
    }

    Activity onResumActivity;
    @Override     
    public void onActivityResumed(Activity activity) {
//        L.e(activity.toString() + " onActivityResumed");
        onResumActivity=activity;
        isForground = true;
//        timestamp = 0l;
//        isScreenOff = false;
    }

    public  Activity  getOnResumActivity(){
        return  onResumActivity;
    }


    @Override
    public void onActivityPaused(Activity activity) {
//        L.e(activity.toString() + " onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        L.e(activity.toString() + " onActivityStopped");
        activeCount--;
        if (activeCount == 0) {
            isForground = false;
            timestamp = System.currentTimeMillis();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
//        L.e(activity.toString() + " onActivityDestroyed");
    }




}
