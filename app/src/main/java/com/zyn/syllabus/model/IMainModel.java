package com.zyn.syllabus.model;

import com.zyn.syllabus.bean.ClassBean;
import com.zyn.syllabus.listeners.OnRequestListener;

/**
 IMainModel.java
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */
public interface IMainModel {
    /**
     * loading Monday course.....
     * @param onRequestListener
     */
    void getMondayClass(OnRequestListener onRequestListener);

    void getTuesdayClass(OnRequestListener onRequestListener);

    void getWednesdayClass(OnRequestListener onRequestListener);

    void getThursdayClass(OnRequestListener onRequestListener);

    void getFridayClass(OnRequestListener onRequestListener);

    void getStatudayClass(OnRequestListener onRequestListener);

    void getSundayClass(OnRequestListener onRequestListener);

    void saveClass(ClassBean classBean, OnRequestListener onRequestListener);

    void updateClass(ClassBean classBean, OnRequestListener listener);

    void deleteClass(ClassBean classBean, OnRequestListener listener);
}
