package com.zyn.syllabus.presenter;

import android.content.Context;
import android.widget.Toast;

import com.zyn.syllabus.activities.MainActivity;
import com.zyn.syllabus.bean.ClassBean;
import com.zyn.syllabus.listeners.OnRequestListener;
import com.zyn.syllabus.model.IMainModel;
import com.zyn.syllabus.model.MainModel;
import com.zyn.syllabus.utils.Contast;
import com.zyn.syllabus.utils.TimeUtils;

import java.util.List;

/**
 MainPresenter.java
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */

public class MainPresenter implements IMainPresenter {
    private Context mContext;
    private MainActivity mMainActivity;
    private IMainModel mIMainModel;

    public MainPresenter(Context context, MainActivity mMainActivity) {
        this.mContext = context;
        this.mMainActivity = mMainActivity;
        this.mIMainModel = new MainModel(mMainActivity);
    }

    @Override
    public void getAllClass() {
        mIMainModel.getMondayClass(new OnRequestListener() {

            private int mCount;
            private int index = 0;

            @Override
            public void success(List<ClassBean> classBeen) {
                int count = mMainActivity.getL1().getChildCount();
//                for (int i = 1; i < count; i++) {
//                    mMainActivity.getL1().removeViewAt(i);
//                }
                if (classBeen.size() <= 0) {
                    mMainActivity.setNoClass(mMainActivity.getL1(), 28);
                } else {
                    //begin to set the course
                    for (int i = 0; i < classBeen.size(); i++) {
                        long startTime = 0;
                        if (i == 0) {
                            startTime = classBeen.get(i).getStartTime() - TimeUtils.getTimeByTimestemp("08:00");
                        } else {
                            startTime = classBeen.get(i).getStartTime() - classBeen.get(i - 1).getEndTime();
                        }
                        if (startTime > 0) {
                            //get the number of course
                            mCount = (int) (startTime / 1000 / 60 / 30);
                        }
                        //set the first course
                        mMainActivity.setNoClass(mMainActivity.getL1(), mCount);
                        //set the next one
                        int mCount = (int) ((classBeen.get(i).getEndTime() - classBeen.get(i).getStartTime()) / 1000 / 60 / 30);
                        mMainActivity.setClass(mMainActivity.getL1(), classBeen.get(i).getId(), classBeen.get(i).getClassName(), classBeen.get(i).getAddr(), classBeen.get(i).getTeacherName(), TimeUtils.getTimeStempByTime(classBeen.get(i).getStartTime()) + "-" + TimeUtils.getTimeStempByTime(classBeen.get(i).getEndTime()), mCount, index);
                        index = (index + 1) % Contast.colors.length;
                    }
                }
            }

            @Override
            public void fail(String errorData) {
                //otherwise, function to set an empty day
                mMainActivity.setNoClass(mMainActivity.getL1(), 28);
            }
        });
//the following steps are same
        mIMainModel.getTuesdayClass(new OnRequestListener() {

            private int mCount;
            private int index = 0;

            @Override
            public void success(List<ClassBean> classBeen) {
                int count = mMainActivity.getL2().getChildCount();
//                for (int i = 1; i < count; i++) {
//                    mMainActivity.getL2().removeViewAt(i);
//                }
                if (classBeen.size() <= 0) {
                    mMainActivity.setNoClass(mMainActivity.getL2(), 28);
                } else {
                    //set course
                    for (int i = 0; i < classBeen.size(); i++) {
                        long startTime = 0;
                        if (i == 0) {
                            startTime = classBeen.get(i).getStartTime() - TimeUtils.getTimeByTimestemp("08:00");
                        } else {
                            startTime = classBeen.get(i).getStartTime() - classBeen.get(i - 1).getEndTime();
                        }
                        if (startTime > 0) {
                            //get the number of empty course
                            mCount = (int) (startTime / 1000 / 60 / 30);
                        }
                        //set the first one
                        mMainActivity.setNoClass(mMainActivity.getL2(), mCount);
                        //set the next
                        int mCount = (int) ((classBeen.get(i).getEndTime() - classBeen.get(i).getStartTime()) / 1000 / 60 / 30);
                        mMainActivity.setClass(mMainActivity.getL2(), classBeen.get(i).getId(), classBeen.get(i).getClassName(), classBeen.get(i).getAddr(), classBeen.get(i).getTeacherName(), TimeUtils.getTimeStempByTime(classBeen.get(i).getStartTime()) + "-" + TimeUtils.getTimeStempByTime(classBeen.get(i).getEndTime()), mCount, index);
                        index = (index + 1) % Contast.colors.length;
                    }
                }
            }

            @Override
            public void fail(String errorData) {
                //default statement
                mMainActivity.setNoClass(mMainActivity.getL2(), 28);
            }
        });

        mIMainModel.getWednesdayClass(new OnRequestListener() {

            private int mCount;
            private int index = 0;

            @Override
            public void success(List<ClassBean> classBeen) {
                int count = mMainActivity.getL3().getChildCount();
//                for (int i = 1; i < count; i++) {
//                    mMainActivity.getL3().removeViewAt(i);
//                }
                if (classBeen.size() <= 0) {
                    mMainActivity.setNoClass(mMainActivity.getL3(), 28);
                } else {
                    for (int i = 0; i < classBeen.size(); i++) {
                        long startTime = 0;
                        if (i == 0) {
                            startTime = classBeen.get(i).getStartTime() - TimeUtils.getTimeByTimestemp("08:00");
                        } else {
                            startTime = classBeen.get(i).getStartTime() - classBeen.get(i - 1).getEndTime();
                        }
                        if (startTime > 0) {
                            mCount = (int) (startTime / 1000 / 60 / 30);
                        }
                        mMainActivity.setNoClass(mMainActivity.getL3(), mCount);
                        int mCount = (int) ((classBeen.get(i).getEndTime() - classBeen.get(i).getStartTime()) / 1000 / 60 / 30);
                        mMainActivity.setClass(mMainActivity.getL3(), classBeen.get(i).getId(), classBeen.get(i).getClassName(), classBeen.get(i).getAddr(), classBeen.get(i).getTeacherName(), TimeUtils.getTimeStempByTime(classBeen.get(i).getStartTime()) + "-" + TimeUtils.getTimeStempByTime(classBeen.get(i).getEndTime()), mCount, index);
                        index = (index + 1) % Contast.colors.length;
                    }
                }
            }

            @Override
            public void fail(String errorData) {
                mMainActivity.setNoClass(mMainActivity.getL3(), 28);
            }
        });

        mIMainModel.getThursdayClass(new OnRequestListener() {

            private int mCount;
            private int index = 0;

            @Override
            public void success(List<ClassBean> classBeen) {
                int count = mMainActivity.getL4().getChildCount();
//                for (int i = 1; i < count; i++) {
//                    mMainActivity.getL4().removeViewAt(i);
//                }
                if (classBeen.size() <= 0) {
                    mMainActivity.setNoClass(mMainActivity.getL4(), 28);
                } else {

                    for (int i = 0; i < classBeen.size(); i++) {
                        long startTime = 0;
                        if (i == 0) {
                            startTime = classBeen.get(i).getStartTime() - TimeUtils.getTimeByTimestemp("08:00");
                        } else {
                            startTime = classBeen.get(i).getStartTime() - classBeen.get(i - 1).getEndTime();
                        }
                        if (startTime > 0) {
                            mCount = (int) (startTime / 1000 / 60 / 30);
                        }
                        mMainActivity.setNoClass(mMainActivity.getL4(), mCount);
                        int mCount = (int) ((classBeen.get(i).getEndTime() - classBeen.get(i).getStartTime()) / 1000 / 60 / 30);
                        mMainActivity.setClass(mMainActivity.getL4(), classBeen.get(i).getId(), classBeen.get(i).getClassName(), classBeen.get(i).getAddr(), classBeen.get(i).getTeacherName(), TimeUtils.getTimeStempByTime(classBeen.get(i).getStartTime()) + "-" + TimeUtils.getTimeStempByTime(classBeen.get(i).getEndTime()), mCount, index);
                        index = (index + 1) % Contast.colors.length;
                    }
                }
            }

            @Override
            public void fail(String errorData) {
                mMainActivity.setNoClass(mMainActivity.getL4(), 28);
            }
        });

        mIMainModel.getFridayClass(new OnRequestListener() {

            private int mCount;
            private int index = 0;

            @Override
            public void success(List<ClassBean> classBeen) {
                int count = mMainActivity.getL5().getChildCount();
//                for (int i = 1; i < count; i++) {
//                    mMainActivity.getL5().removeViewAt(i);
//                }
                if (classBeen.size() <= 0) {
                    mMainActivity.setNoClass(mMainActivity.getL5(), 28);
                } else {
                    for (int i = 0; i < classBeen.size(); i++) {
                        long startTime = 0;
                        if (i == 0) {
                            startTime = classBeen.get(i).getStartTime() - TimeUtils.getTimeByTimestemp("08:00");
                        } else {
                            startTime = classBeen.get(i).getStartTime() - classBeen.get(i - 1).getEndTime();
                        }
                        if (startTime > 0) {
                            mCount = (int) (startTime / 1000 / 60 / 30);
                        }
                        mMainActivity.setNoClass(mMainActivity.getL5(), mCount);
                        int mCount = (int) ((classBeen.get(i).getEndTime() - classBeen.get(i).getStartTime()) / 1000 / 60 / 30);
                        mMainActivity.setClass(mMainActivity.getL5(), classBeen.get(i).getId(), classBeen.get(i).getClassName(), classBeen.get(i).getAddr(), classBeen.get(i).getTeacherName(), TimeUtils.getTimeStempByTime(classBeen.get(i).getStartTime()) + "-" + TimeUtils.getTimeStempByTime(classBeen.get(i).getEndTime()), mCount, index);
                        index = (index + 1) % Contast.colors.length;
                    }
                }
            }

            @Override
            public void fail(String errorData) {
                mMainActivity.setNoClass(mMainActivity.getL5(), 28);
            }
        });

        mIMainModel.getStatudayClass(new OnRequestListener() {

            private int mCount;
            private int index = 0;

            @Override
            public void success(List<ClassBean> classBeen) {
                int count = mMainActivity.getL6().getChildCount();
//                for (int i = 1; i < count; i++) {
//                    mMainActivity.getL6().removeViewAt(i);
//                }
                if (classBeen.size() <= 0) {
                    mMainActivity.setNoClass(mMainActivity.getL6(), 28);
                } else {
                    for (int i = 0; i < classBeen.size(); i++) {
                        long startTime = 0;
                        if (i == 0) {
                            startTime = classBeen.get(i).getStartTime() - TimeUtils.getTimeByTimestemp("08:00");
                        } else {
                            startTime = classBeen.get(i).getStartTime() - classBeen.get(i - 1).getEndTime();
                        }
                        if (startTime > 0) {
                            mCount = (int) (startTime / 1000 / 60 / 30);
                        }
                        mMainActivity.setNoClass(mMainActivity.getL6(), mCount);
                        int mCount = (int) ((classBeen.get(i).getEndTime() - classBeen.get(i).getStartTime()) / 1000 / 60 / 30);
                        mMainActivity.setClass(mMainActivity.getL6(), classBeen.get(i).getId(), classBeen.get(i).getClassName(), classBeen.get(i).getAddr(), classBeen.get(i).getTeacherName(), TimeUtils.getTimeStempByTime(classBeen.get(i).getStartTime()) + "-" + TimeUtils.getTimeStempByTime(classBeen.get(i).getEndTime()), mCount, index);
                        index = (index + 1) % Contast.colors.length;
                    }
                }
            }

            @Override
            public void fail(String errorData) {
                mMainActivity.setNoClass(mMainActivity.getL6(), 28);
            }
        });

        mIMainModel.getSundayClass(new OnRequestListener() {

            private int mCount;
            private int index = 0;

            @Override
            public void success(List<ClassBean> classBeen) {
                int count = mMainActivity.getL7().getChildCount();
//                for (int i = 1; i < count; i++) {
//                    mMainActivity.getL7().removeViewAt(i);
//                }
                if (classBeen.size() <= 0) {
                    mMainActivity.setNoClass(mMainActivity.getL6(), 28);
                } else {
                    for (int i = 0; i < classBeen.size(); i++) {
                        long startTime = 0;
                        if (i == 0) {
                            startTime = classBeen.get(i).getStartTime() - TimeUtils.getTimeByTimestemp("08:00");
                        } else {
                            startTime = classBeen.get(i).getStartTime() - classBeen.get(i - 1).getEndTime();
                        }
                        if (startTime > 0) {
                            mCount = (int) (startTime / 1000 / 60 / 30);
                        }
                        mMainActivity.setNoClass(mMainActivity.getL7(), mCount);
                        int mCount = (int) ((classBeen.get(i).getEndTime() - classBeen.get(i).getStartTime()) / 1000 / 60 / 30);
                        mMainActivity.setClass(mMainActivity.getL7(), classBeen.get(i).getId(), classBeen.get(i).getClassName(), classBeen.get(i).getAddr(), classBeen.get(i).getTeacherName(), TimeUtils.getTimeStempByTime(classBeen.get(i).getStartTime()) + "-" + TimeUtils.getTimeStempByTime(classBeen.get(i).getEndTime()), mCount, index);
                        index = (index + 1) % Contast.colors.length;
                    }
                }
            }

            @Override
            public void fail(String errorData) {
                mMainActivity.setNoClass(mMainActivity.getL7(), 28);
            }
        });
    }

    @Override
    public void saveClass(ClassBean classBean) {
        mIMainModel.saveClass(classBean, new OnRequestListener() {
            @Override
            public void success(List<ClassBean> classBeen) {
                //successful save and refresh the interface
                mMainActivity.refreshActivity();
            }

            @Override
            public void fail(String errorData) {

            }
        });
    }

    @Override
    public void updateClass(ClassBean classBean) {
        mIMainModel.updateClass(classBean, new OnRequestListener(){

            @Override
            public void success(List<ClassBean> classBeen) {
                //successful save and refresh the interface
                mMainActivity.refreshActivity();
            }

            @Override
            public void fail(String errorData) {

            }
        });
    }

    @Override
    public void deleteClass(ClassBean classBean) {
        mIMainModel.deleteClass(classBean, new OnRequestListener(){
            @Override
            public void success(List<ClassBean> classBeen) {
                mMainActivity.refreshActivity();
            }

            @Override
            public void fail(String errorData) {

            }
        });
    }
}
