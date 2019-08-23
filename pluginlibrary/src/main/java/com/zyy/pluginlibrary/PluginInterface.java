package com.zyy.pluginlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public interface PluginInterface {
    void onAttach(Activity activity);
    void onCreate(Bundle saveBundle);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestory();

    void onSaveInstanceState(Bundle state);
    boolean onTouchEvent(MotionEvent event);
    void onBackPressed();
}
