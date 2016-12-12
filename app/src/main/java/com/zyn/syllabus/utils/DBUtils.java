package com.zyn.syllabus.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;
import android.widget.TextView;

import com.zyn.syllabus.bean.ClassBean;

import java.util.ArrayList;
import java.util.List;

public class DBUtils extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "syllabus_db";
    private final static int DATABASE_VERSION = 1;

    public final String CLASS_TABLE = "class_table";
    public final String SCHEDULE_ID = "_id";
    public final String SCHEDULE_CLASSNAME = "syllabus_className";
    public final String SCHEDULE_TEACHERNAME = "syllabus_teacherName";
    public final String SCHEDULE_STARTTIME = "syllabus_startTime";
    public final String SCHEDULE_ENDTIME = "syllabus_endTime";
    public final String SCHEDULE_WEEK = "syllabus_week";
    public final String SCHEDULE_ADDR = "syllabus_addr";


    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "CREATE TABLE "
                + CLASS_TABLE
                + " ("
                + SCHEDULE_ID + " INTEGER primary key autoincrement, "
                + SCHEDULE_CLASSNAME + " text, "
                + SCHEDULE_TEACHERNAME + " text, "
                + SCHEDULE_STARTTIME + " BIGINT, "
                + SCHEDULE_ENDTIME + " BIGINT, "
                + SCHEDULE_WEEK + " int, "
                + SCHEDULE_ADDR + " text "
                + " )";
        db.execSQL(sql);
    }

    /**
     * insert a class
     * @param classBean
     * @return
     */
    public long insertClass(ClassBean classBean){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULE_CLASSNAME, classBean.getClassName()+"");
        contentValues.put(SCHEDULE_TEACHERNAME, classBean.getTeacherName()+"");
        contentValues.put(SCHEDULE_STARTTIME, classBean.getStartTime());
        contentValues.put(SCHEDULE_ENDTIME, classBean.getEndTime());
        contentValues.put(SCHEDULE_ADDR, classBean.getAddr()+"");
        contentValues.put(SCHEDULE_WEEK, classBean.getWeek());
        long row = db.insert(CLASS_TABLE, null, contentValues);
        db.close();
        return row;
    }

    /**
     * delete a class
     * @param classBean
     */
    public void deleteClass(ClassBean classBean){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = SCHEDULE_ID + " = ?";
        String[] whereValue = {classBean.getId()};
        db.delete(CLASS_TABLE, where, whereValue);
        db.close();
    }

    /**
     * modify a class
     * @param classBean
     */
    public void updateClass(ClassBean classBean){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = SCHEDULE_ID + " = ?";
        String[] whereValue = {classBean.getId()};

        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULE_CLASSNAME, classBean.getClassName()+"");
        contentValues.put(SCHEDULE_TEACHERNAME, classBean.getTeacherName()+"");
        contentValues.put(SCHEDULE_STARTTIME, classBean.getStartTime());
        contentValues.put(SCHEDULE_ENDTIME, classBean.getEndTime());
        contentValues.put(SCHEDULE_ADDR, classBean.getAddr());
        contentValues.put(SCHEDULE_WEEK, classBean.getWeek());
        db.update(CLASS_TABLE, contentValues, where, whereValue);
        db.close();
    }

    /**
     * weekday
     * @param week
     */
    public List<ClassBean> selectClassByWeek(int week){
        List<ClassBean> classBeanList = new ArrayList<ClassBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        String where = SCHEDULE_WEEK + "= ?";
        String[] whereValue = {String.valueOf(week)};
        String orderBy = SCHEDULE_STARTTIME+" asc";

        Cursor cursor = db.query(CLASS_TABLE, null, where, whereValue, null, null, orderBy);//sort depend on time
        while (cursor.moveToNext()){
            ClassBean classBean = new ClassBean();
            classBean.setId(cursor.getString(cursor.getColumnIndex(SCHEDULE_ID)));
            classBean.setClassName(cursor.getString(cursor.getColumnIndex(SCHEDULE_CLASSNAME)));
            classBean.setTeacherName(cursor.getString(cursor.getColumnIndex(SCHEDULE_TEACHERNAME)));
            classBean.setStartTime(cursor.getLong(cursor.getColumnIndex(SCHEDULE_STARTTIME)));
            classBean.setEndTime(cursor.getLong(cursor.getColumnIndex(SCHEDULE_ENDTIME)));
            classBean.setAddr(cursor.getString(cursor.getColumnIndex(SCHEDULE_ADDR)));
            classBean.setWeek(cursor.getInt(cursor.getColumnIndex(SCHEDULE_WEEK)));
            classBeanList.add(classBean);
        }
        db.close();
        return classBeanList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}