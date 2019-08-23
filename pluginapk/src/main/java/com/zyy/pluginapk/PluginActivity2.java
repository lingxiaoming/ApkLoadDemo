package com.zyy.pluginapk;

import android.os.Bundle;

import com.zyy.pluginlibrary.BaseActivity;
import com.zyy.pluginlibrary.PluginInterface;

public class PluginActivity2 extends BaseActivity implements PluginInterface {

    @Override
    public void onCreate(Bundle saveBundle) {
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_plugin2);
    }
}
