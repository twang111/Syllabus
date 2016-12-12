package com.zyn.syllabus.presenter;

import com.zyn.syllabus.bean.ClassBean;

/**
 IMainPresenter.java
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */
public interface IMainPresenter {
    /**
     * get all course
     */
    void getAllClass();

    /**
     * save the input of course
     */
    void saveClass(ClassBean classBean);

    /**
     * modify the course
     * @param classBean
     */
    void updateClass(ClassBean classBean);

    void deleteClass(ClassBean classBean);
}
