package com.zyn.syllabus.listeners;

import com.zyn.syllabus.bean.ClassBean;

import java.util.List;



/**
 OnRequestListener
 A recall Entrance for loading data for interface  加载数据的回调接口
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */

public interface OnRequestListener {
    void success(List<ClassBean> classBeen);
    void fail(String errorData);
}
