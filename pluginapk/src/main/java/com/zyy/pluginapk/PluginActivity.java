package com.zyy.pluginapk;

import android.content.Intent;
import android.os.Bundle;

import com.zyy.pluginlibrary.BaseActivity;
import com.zyy.pluginlibrary.PluginInterface;

public class PluginActivity extends BaseActivity implements PluginInterface {


    @Override
    public void onCreate(Bundle saveBundle) {
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_plugin);
        findViewById(R.id.tv_hello).setOnClickListener(view->{
            System.out.println("my name is "+this.getClass().getName());
            startActivity(new Intent(that, PluginActivity2.class));
        });
    }
}
