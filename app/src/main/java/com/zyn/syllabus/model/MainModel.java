package com.zyn.syllabus.model;

import android.content.Context;

import com.zyn.syllabus.bean.ClassBean;
import com.zyn.syllabus.listeners.OnRequestListener;
import com.zyn.syllabus.utils.DBUtils;

import java.util.List;

/**
 MainModel.java
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */

public class MainModel implements IMainModel {
    private Context mContext;
    private DBUtils mDBUtils;

    public MainModel(Context context){
        this.mContext = context;
        mDBUtils = new DBUtils(context);
    }

    @Override
    public void getMondayClass(OnRequestListener onRequestListener) {
        List<ClassBean> classBeenList_monday = mDBUtils.selectClassByWeek(1);
        if(classBeenList_monday != null){
            onRequestListener.success(classBeenList_monday);
        }else{
            onRequestListener.fail("Empty");
        }
    }

    @Override
    public void getTuesdayClass(OnRequestListener onRequestListener) {
        List<ClassBean> classBeenList_monday = mDBUtils.selectClassByWeek(2);
        if(classBeenList_monday != null){
            onRequestListener.success(classBeenList_monday);
        }else{
            onRequestListener.fail("Empty");
        }
    }

    @Override
    public void getWednesdayClass(OnRequestListener onRequestListener) {
        List<ClassBean> classBeenList_monday = mDBUtils.selectClassByWeek(3);
        if(classBeenList_monday != null){
            onRequestListener.success(classBeenList_monday);
        }else{
            onRequestListener.fail("Empty");
        }
    }

    @Override
    public void getThursdayClass(OnRequestListener onRequestListener) {
        List<ClassBean> classBeenList_monday = mDBUtils.selectClassByWeek(4);
        if(classBeenList_monday != null){
            onRequestListener.success(classBeenList_monday);
        }else{
            onRequestListener.fail("Empty");
        }
    }

    @Override
    public void getFridayClass(OnRequestListener onRequestListener) {
        List<ClassBean> classBeenList_monday = mDBUtils.selectClassByWeek(5);
        if(classBeenList_monday != null){
            onRequestListener.success(classBeenList_monday);
        }else{
            onRequestListener.fail("Empty");
        }
    }

    @Override
    public void getStatudayClass(OnRequestListener onRequestListener) {
        List<ClassBean> classBeenList_monday = mDBUtils.selectClassByWeek(6);
        if(classBeenList_monday != null){
            onRequestListener.success(classBeenList_monday);
        }else{
            onRequestListener.fail("Empty");
        }
    }

    @Override
    public void getSundayClass(OnRequestListener onRequestListener) {
        List<ClassBean> classBeenList_monday = mDBUtils.selectClassByWeek(7);
        if(classBeenList_monday != null){
            onRequestListener.success(classBeenList_monday);
        }else{
            onRequestListener.fail("Empty");
        }
    }

    @Override
    public void saveClass(ClassBean classBean, OnRequestListener onRequestListener) {
        long id = mDBUtils.insertClass(classBean);
        if(id != -1){
            onRequestListener.success(null);
        }else{
            onRequestListener.fail("Fail to insert");
        }
    }

    @Override
    public void updateClass(ClassBean classBean, OnRequestListener listener) {
        mDBUtils.updateClass(classBean);
        listener.success(null);
    }

    @Override
    public void deleteClass(ClassBean classBean, OnRequestListener listener) {
        mDBUtils.deleteClass(classBean);
        listener.success(null);
    }
}
