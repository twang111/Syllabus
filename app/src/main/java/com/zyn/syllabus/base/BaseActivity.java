package com.zyn.syllabus.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;

import com.zyn.syllabus.R;

public abstract class BaseActivity extends Activity {

    public Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addActivityInList();
        initView();
        mContext = this;
        initData();
        setListener();
	}

    @Override
    protected void onPause() {
        super.onPause();
        doPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivityInList();
        doDestory();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	doResume();
    }

    /*------------------------------base class function------------------------------*/
    protected abstract void initView();//initialize view

    protected abstract void initData();//initialize data

    protected abstract void setListener();//set listener
    
    protected abstract void doResume();

    protected abstract void doPause();

    protected abstract void doDestory();

    /*------------------------------base class function------------------------------*/

    /**
     * exit program
     */
    public void exitApp() {
    	new AlertDialog.Builder(this).setTitle("Are you sure to exit?")
        .setNeutralButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	BaseApplication.getInstance().exit();
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    /**
     * add current Activity into a list
     */
    private void addActivityInList() {
    	BaseApplication.getInstance().addActivity(this);
    }

    /**
     * remove current Activity into a list
     */
    private void removeActivityInList(){
    	BaseApplication.getInstance().removeActivity(this);
    }

    public void jump(Activity activity, Class to_class, boolean isClose) {
        Intent intent = new Intent(activity, to_class);
        startActivity(intent);
        if(isClose == true){
            activity.finish();//close activity
        }
        overridePendingTransition(R.anim.enter, R.anim.out);//flash
    }

    public void jump(Activity activity, Class to_class, String key, Bundle bundle, boolean isClose) {
        Intent intent = new Intent(activity, to_class);
        intent.putExtra(key, bundle);
        startActivity(intent);
        if(isClose == true){
            activity.finish();//close activity
        }
        overridePendingTransition(R.anim.enter, R.anim.out);//flash
    }
}
