package com.zyy.pluginlibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 代理插件apk，调用第三方apk插件activity的生命周期
 */
public class ProxyActivity extends Activity {
    PluginInterface pluginInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String activityName = getIntent().getStringExtra("name");

        try {
            Class<?> clazz = PluginManager.getDefault().getDexClassLoader().loadClass(activityName);
            Object obj = clazz.newInstance();

            if(obj instanceof PluginInterface){
                pluginInterface = (PluginInterface)obj;
                pluginInterface.onAttach(this);
                pluginInterface.onCreate(new Bundle());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }


    @Override
    public Resources getResources() {
        return PluginManager.getDefault().getResource();
    }


    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getDefault().getDexClassLoader();
    }


    @Override
    public void startActivity(Intent intent) {
        String name = intent.getStringExtra("name");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("name", name);
        super.startActivity(intent1);
    }


    @Override
    protected void onStart() {
        super.onStart();
        pluginInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pluginInterface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pluginInterface.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
        pluginInterface.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginInterface.onDestory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        pluginInterface.onSaveInstanceState(outState);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return pluginInterface.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pluginInterface.onBackPressed();
    }
}
