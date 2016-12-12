package com.zyn.syllabus.base;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseApplication extends Application{
    public List<Activity> activityList = new LinkedList<Activity>();

    private static BaseApplication instance;
    public static BaseApplication getInstance(){
        return instance;
    }

    private ExecutorService es;

    public void execRunnable(Runnable r){
        es.execute(r);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        es = Executors.newFixedThreadPool(3);
    }

    public void exit(){
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    // add Activity into activity
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // remove Activity into activity
    public void removeActivity(Activity activity){ activityList.remove(activity); }
}
