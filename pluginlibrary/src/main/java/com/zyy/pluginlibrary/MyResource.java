package com.zyy.pluginlibrary;

import android.content.res.Resources;

/**
 * 用自定义的resource给控件设置资源，这个resource可能是插件apk中的
 */
public class MyResource {


    public static boolean usePluginTheme = false;//是否使用插件主题

    public static Resources getResource(){

        if(usePluginTheme && PluginManager.getDefault().getPackageInfo() != null){
            return PluginManager.getDefault().getResource();
        }else {
            return PluginManager.getDefault().getContext().getResources();
        }

    }

}
