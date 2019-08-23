package com.zyy.pluginlibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends Activity implements PluginInterface {
    public Activity that;




    @Override
    public void onAttach(Activity activity) {
        that=activity;
    }

    @Override
    public void setContentView(View view) {
        if(that == null){
            super.setContentView(view);
        }else {
            that.setContentView(view);
        }
    }


    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }


    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent inte = new Intent();
        inte.putExtra("name", intent.getComponent().getClassName());
        that.startActivity(inte);
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle saveBundle) {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestory() {

    }

    @Override
    public void onSaveInstanceState(Bundle state) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }


    @Override
    public void onBackPressed() {
    }
}
