package com.zyy.pluginlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 插件管理类
 */
public class PluginManager {

    private PluginManager(){}

    public static PluginManager getDefault(){
        return PluginManagerInstance.pluginManager;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResource() {
        return resources;
    }


    static class PluginManagerInstance{
        public static PluginManager pluginManager = new PluginManager();
    }

    private Resources resources;//插件apk的资源

    private DexClassLoader dexClassLoader;//插件apk的classloader

    private Context context;//

    private PackageInfo packageInfo;//插件apk的包名


    public void setContext(Context context){
        this.context = context;
    }


    public Context getContext(){
        return this.context;
    }


    public PackageInfo getPackageInfo(){
        return this.packageInfo;
    }


    /**
     * 获取path路径的apk的资源和类加载器
     * @param path
     */
    public void loadPath(String path){
        //获取当前应用的私有存储路径
        File file = context.getDir("dex", Context.MODE_PRIVATE);
        //获取path路径下的dex文件的类加载器
        dexClassLoader = new DexClassLoader(path, file.getAbsolutePath(), null, context.getClassLoader());

        //获取包管理器
        PackageManager packageManager = context.getPackageManager();
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);


        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
            method.invoke(assetManager, path);//addAssetPath才能管理path路径下的apk

            resources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}
