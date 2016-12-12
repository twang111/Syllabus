package com.zyn.syllabus.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
ClassBean
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */

public class ClassBean implements Parcelable {
    private String id;
    private String className;
    private String teacherName;
    private long startTime;
    private long endTime;
    private String addr;
    private int week;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public ClassBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.className);
        dest.writeString(this.teacherName);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
        dest.writeString(this.addr);
        dest.writeInt(this.week);
    }

    protected ClassBean(Parcel in) {
        this.id = in.readString();
        this.className = in.readString();
        this.teacherName = in.readString();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
        this.addr = in.readString();
        this.week = in.readInt();
    }

    public static final Creator<ClassBean> CREATOR = new Creator<ClassBean>() {
        @Override
        public ClassBean createFromParcel(Parcel source) {
            return new ClassBean(source);
        }

        @Override
        public ClassBean[] newArray(int size) {
            return new ClassBean[size];
        }
    };
}
