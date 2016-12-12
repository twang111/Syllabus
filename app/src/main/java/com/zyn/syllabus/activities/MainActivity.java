package com.zyn.syllabus.activities;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zyn.syllabus.R;
import com.zyn.syllabus.base.BaseActivity;
import com.zyn.syllabus.bean.ClassBean;
import com.zyn.syllabus.presenter.IMainPresenter;
import com.zyn.syllabus.presenter.MainPresenter;
import com.zyn.syllabus.utils.Contast;
import com.zyn.syllabus.utils.DentityUtils;
import com.zyn.syllabus.utils.TimeUtils;

import java.util.Calendar;
import java.util.Locale;

/**
 IMainView.java
 Final Project --- "SB Schedule"
 Copyright (c) 2016 Boston University. All rights reserved.
 Author: Zexing Gao
 BUID: U47145540
 */
public class MainActivity extends BaseActivity implements IMainView{

    private LinearLayout mLl1;
    private LinearLayout mLl2;
    private LinearLayout mLl3;
    private LinearLayout mLl4;
    private LinearLayout mLl5;
    private LinearLayout mLl6;
    private LinearLayout mLl7;

    private ImageButton ib_add;

    private IMainPresenter mIMainPresenter;

    @Override
    protected void initView() {

        mIMainPresenter = new MainPresenter(mContext, MainActivity.this);

        setContentView(R.layout.activity_main);
        //name from Monday to Sunday
        mLl1 = (LinearLayout) findViewById(R.id.ll1);
        mLl2 = (LinearLayout) findViewById(R.id.ll2);
        mLl3 = (LinearLayout) findViewById(R.id.ll3);
        mLl4 = (LinearLayout) findViewById(R.id.ll4);
        mLl5 = (LinearLayout) findViewById(R.id.ll5);
        mLl6 = (LinearLayout) findViewById(R.id.ll6);
        mLl7 = (LinearLayout) findViewById(R.id.ll7);

        ib_add = (ImageButton) findViewById(R.id.ib_add);
    }

    @Override
    protected void initData() {
        mIMainPresenter.getAllClass();
    }

    /**
     * method to set the daliy course
     *
     * @param ll
     * @param title
     * @param place
     * @param teacher
     * @param time
     * @param classes
     * @param color
     */
    @Override
    public void setClass(final LinearLayout ll, final String id, final String title, final String place,
                         final String teacher, final String time, int classes, int color) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_class, null);
        view.setMinimumHeight(DentityUtils.dip2px(this, classes * 48));
        view.setBackgroundColor(Contast.colors[color]);
        ((TextView) view.findViewById(R.id.title)).setText(title);
        ((TextView) view.findViewById(R.id.place)).setText(place);
        ((TextView) view.findViewById(R.id.last)).setText(teacher);
        ((TextView) view.findViewById(R.id.time)).setText(time);
        ((TextView) view.findViewById(R.id.tv_id)).setText(id);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the library of java
                //click modify
                //add course
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCustomTitle(View.inflate(mContext, R.layout.item_title, null));
                View dialogView = View.inflate(mContext, R.layout.dialog_add, null);

                final EditText et_className = (EditText) dialogView.findViewById(R.id.et_className);
                final EditText et_teacherName = (EditText) dialogView.findViewById(R.id.et_teacherName);
                final EditText et_addr = (EditText) dialogView.findViewById(R.id.et_addr);
                final Spinner sp_week = (Spinner) dialogView.findViewById(R.id.sp_week);

                //link to ArrayAdapter
                ArrayAdapter adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.week, android.R.layout.simple_spinner_item);
                //set the drop down view style
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //add first adapter into spinner (ganna use another one in another function)
                sp_week.setAdapter(adapter);

                final TextView tv_startTime = (TextView) dialogView.findViewById(R.id.tv_startTime);
                final TextView tv_endTime = (TextView) dialogView.findViewById(R.id.tv_endTime);

                et_className.setText(title);
                et_teacherName.setText(teacher);
                et_addr.setText(place);
                String startTime_str = time.split("-")[0];
                tv_startTime.setText(startTime_str);
                String endTime_str = time.split("-")[1];
                tv_endTime.setText(endTime_str);
                if(ll.getId() == R.id.ll1){
                    sp_week.setSelection(1);
                }else if(ll.getId() == R.id.ll2){
                    sp_week.setSelection(2);
                }else if(ll.getId() == R.id.ll3){
                    sp_week.setSelection(3);
                }else if(ll.getId() == R.id.ll4){
                    sp_week.setSelection(4);
                }else if(ll.getId() == R.id.ll5){
                    sp_week.setSelection(5);
                }else if(ll.getId() == R.id.ll6){
                    sp_week.setSelection(6);
                }else if(ll.getId() == R.id.ll7){
                    sp_week.setSelection(0);
                }

                tv_startTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

//                                timePicker.setIs24HourView(true);
//                                if(timePicker.getCurrentHour() > 12){
//                                    //afternoon
//                                    tv_startTime.setText((i - 12)+":"+i1+"p");
//                                }else{
//                                    //noon
//                                    tv_startTime.setText(i+":"+i1+"a");
//                                }
                                tv_startTime.setText(i+":"+i1);
                            }
                        }, Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY), Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE), true);
                        timePickerDialog.show();
                    }
                });

                tv_endTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

//                                timePicker.setIs24HourView(true);
//                                if(timePicker.getCurrentHour() > 12){
//                                    //afternoon
//                                    tv_endTime.setText((i - 12)+":"+i1+"p");
//                                }else{
//                                    timePicker.setIs24HourView(false);
//                                    //noon
//                                    tv_endTime.setText(i+":"+i1+"a");
//                                }
                                tv_endTime.setText(i+":"+i1);
                            }
                        }, Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY), Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE), true);
                        timePickerDialog.show();
                    }
                });

                builder.setView(dialogView);
                builder.setNegativeButton("Cancle", null);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //save the change in datebase
                        ClassBean classBean = new ClassBean();
                        classBean.setClassName(et_className.getText().toString());
                        classBean.setTeacherName(et_teacherName.getText().toString());
                        classBean.setAddr(et_addr.getText().toString());
                        classBean.setStartTime(TimeUtils.getTimeByTimestemp(tv_startTime.getText().toString()));
                        classBean.setEndTime(TimeUtils.getTimeByTimestemp(tv_endTime.getText().toString()));
                        int week = sp_week.getSelectedItemPosition();
                        if(week == 0){
                            //sunday
                            week = 7;
                        }
                        classBean.setWeek(week);
                        classBean.setId(id);
                        mIMainPresenter.updateClass(classBean);
                    }
                });
                builder.show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //press long time to delete
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCustomTitle(View.inflate(mContext, R.layout.item_title_warning, null));
                builder.setMessage("Confirm delete?");
                builder.setNegativeButton("Cancle", null);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete part
                        ClassBean classBean = new ClassBean();
                        classBean.setClassName(title);
                        classBean.setTeacherName(teacher);
                        classBean.setAddr(place);
                        String startTime_str = time.split("-")[0];
                        String endTime_str = time.split("-")[1];
                        classBean.setStartTime(TimeUtils.getTimeByTimestemp(startTime_str));
                        classBean.setEndTime(TimeUtils.getTimeByTimestemp(endTime_str));
                        int week = 0;
                        if(ll.getId() == R.id.ll1){
                            week = 1;
                        }else if(ll.getId() == R.id.ll2){
                            week = 2;
                        }else if(ll.getId() == R.id.ll3){
                            week = 3;
                        }else if(ll.getId() == R.id.ll4){
                            week = 4;
                        }else if(ll.getId() == R.id.ll5){
                            week = 5;
                        }else if(ll.getId() == R.id.ll6){
                            week = 6;
                        }else if(ll.getId() == R.id.ll7){
                            week = 0;
                        }
                        classBean.setWeek(week);
                        classBean.setId(id);
                        mIMainPresenter.deleteClass(classBean);
                    }
                });
                builder.show();
                return true;
            }
        });
        TextView blank1 = new TextView(this);
        TextView blank2 = new TextView(this);
        blank1.setHeight(DentityUtils.dip2px(this, classes));
        blank2.setHeight(DentityUtils.dip2px(this, classes));
        ll.addView(blank1);
        ll.addView(view);
        ll.addView(blank2);
    }

    @Override
    public LinearLayout getL1() {
        return mLl1;
    }

    @Override
    public LinearLayout getL2() {
        return mLl2;
    }

    @Override
    public LinearLayout getL3() {
        return mLl3;
    }

    @Override
    public LinearLayout getL4() {
        return mLl4;
    }

    @Override
    public LinearLayout getL5() {
        return mLl5;
    }

    @Override
    public LinearLayout getL6() {
        return mLl6;
    }

    @Override
    public LinearLayout getL7() {
        return mLl7;
    }

    /**
     * method to set empty course
     * @param ll
     * @param classes length of the empty course
     */
    @Override
    public void setNoClass(LinearLayout ll, int classes) {
        TextView blank = new TextView(this);
        blank.setMinHeight(DentityUtils.dip2px(this, classes * 50));
        blank.setBackgroundColor(Contast.transColor);
        ll.addView(blank);
    }

    @Override
    protected void setListener() {
        ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add course
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCustomTitle(View.inflate(mContext, R.layout.item_title, null));
                View dialogView = View.inflate(mContext, R.layout.dialog_add, null);

                final EditText et_className = (EditText) dialogView.findViewById(R.id.et_className);
                final EditText et_teacherName = (EditText) dialogView.findViewById(R.id.et_teacherName);
                final EditText et_addr = (EditText) dialogView.findViewById(R.id.et_addr);
                final Spinner sp_week = (Spinner) dialogView.findViewById(R.id.sp_week);

                //connect the course with array adapter
                ArrayAdapter adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.week, android.R.layout.simple_spinner_item);
                //set the drop down view style
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //add second adapter into spinner
                sp_week.setAdapter(adapter);

                final TextView tv_startTime = (TextView) dialogView.findViewById(R.id.tv_startTime);
                final TextView tv_endTime = (TextView) dialogView.findViewById(R.id.tv_endTime);

                tv_startTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

//                                timePicker.setIs24HourView(true);
//                                if(timePicker.getCurrentHour() > 12){
//
//                                    tv_startTime.setText((i - 12)+":"+i1+"p");
//                                }else{
//
//                                    tv_startTime.setText(i+":"+i1+"a");
//                                }
                                tv_startTime.setText(i+":"+i1);
                            }
                        }, Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY), Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE), true);
                        timePickerDialog.show();
                    }
                });

                tv_endTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

//                                timePicker.setIs24HourView(true);
//                                if(timePicker.getCurrentHour() > 12){
//                                    tv_endTime.setText((i - 12)+":"+i1+"p");
//                                }else{
//                                    timePicker.setIs24HourView(false);
//                                    tv_endTime.setText(i+":"+i1+"a");
//                                }
                                tv_endTime.setText(i+":"+i1);
                            }
                        }, Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY), Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE), true);
                        timePickerDialog.show();
                    }
                });

                builder.setView(dialogView);
                builder.setNegativeButton("Cancle", null);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //save to data base
                        ClassBean classBean = new ClassBean();
                        classBean.setClassName(et_className.getText().toString());
                        classBean.setTeacherName(et_teacherName.getText().toString());
                        classBean.setAddr(et_addr.getText().toString());
                        classBean.setStartTime(TimeUtils.getTimeByTimestemp(tv_startTime.getText().toString()));
                        classBean.setEndTime(TimeUtils.getTimeByTimestemp(tv_endTime.getText().toString()));
                        int week = sp_week.getSelectedItemPosition();
                        if(week == 0){
                            //sunday
                            week = 7;
                        }
                        classBean.setWeek(week);
                        mIMainPresenter.saveClass(classBean);
                    }
                });
                builder.show();
            }
        });
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

    @Override
    public void refreshActivity(){
        jump(MainActivity.this, MainActivity.class, true);
    }
}
