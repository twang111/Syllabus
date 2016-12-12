package com.zyn.syllabus.activities;

import android.widget.LinearLayout;

/**
 IMainView.java
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */

public interface IMainView {
    /**
     * Set the empty course
     * @param ll
     * @param classes
     */
    void setNoClass(LinearLayout ll, int classes);

    /**
     * Set the course
     * @param ll
     * @param title
     * @param place
     * @param last
     * @param time
     * @param classes
     * @param color
     */
    void setClass(LinearLayout ll, String id,  String title, String place,
                  String last, String time, int classes, int color);

    LinearLayout getL1();
    LinearLayout getL2();
    LinearLayout getL3();
    LinearLayout getL4();
    LinearLayout getL5();
    LinearLayout getL6();
    LinearLayout getL7();

    void refreshActivity();
}
