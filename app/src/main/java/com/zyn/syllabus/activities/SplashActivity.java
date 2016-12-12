package com.zyn.syllabus.activities;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zyn.syllabus.R;
import com.zyn.syllabus.base.BaseActivity;
import com.zyn.syllabus.base.BaseApplication;

/**
 SplashActivity.java
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initData() {
        BaseApplication.getInstance().execRunnable(new Runnable() {
            @Override
            public void run() {
                //simulation of loading data
                SystemClock.sleep(3000);
                //jump
                jump(SplashActivity.this, MainActivity.class, true);
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void doResume() {

    }

    @Override
    protected void doPause() {

    }

    @Override
    protected void doDestory() {

    }
}
