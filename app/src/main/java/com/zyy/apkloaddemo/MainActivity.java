package com.zyy.apkloaddemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zyy.pluginlibrary.MyResource;
import com.zyy.pluginlibrary.PluginManager;
import com.zyy.pluginlibrary.PluginResourceFirst;
import com.zyy.pluginlibrary.ProxyActivity;
import com.zyy.pluginlibrary.ThemeChangeUtils;

public class MainActivity extends AppCompatActivity {


    @PluginResourceFirst(bg_color = R.color.color_bg)
    public View rootView;

    @PluginResourceFirst(text_color = R.color.color_text, text_str = R.string.text_title)
    public TextView text;

    @PluginResourceFirst(src_image = R.mipmap.bg_image)
    public ImageView image;

    @PluginResourceFirst(bg_image = R.mipmap.bg_button, text_color = R.color.color_text)
    public Button btn_plugin;

    @PluginResourceFirst(bg_image = R.mipmap.bg_button, text_color = R.color.color_text)
    public Button btn_main;

    @PluginResourceFirst(bg_image = R.mipmap.bg_button, text_color = R.color.color_text)
    public Button btn_go;


    @PluginResourceFirst(src_image = R.mipmap.ic_1, bg_color = R.color.color_text)
    public ImageButton image_button_1;

    @PluginResourceFirst(src_image = R.mipmap.ic_2, bg_color = R.color.color_text)
    public ImageButton image_button_2;

    @PluginResourceFirst(src_image = R.mipmap.ic_3, bg_color = R.color.color_text)
    public ImageButton image_button_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(R.id.root_view);
        text = findViewById(R.id.text);
        image = findViewById(R.id.image);

        btn_plugin = findViewById(R.id.btn_plugin);
        btn_main = findViewById(R.id.btn_main);
        btn_go = findViewById(R.id.btn_go);

        image_button_1 = findViewById(R.id.image_button_1);
        image_button_2 = findViewById(R.id.image_button_2);
        image_button_3 = findViewById(R.id.image_button_3);

        PluginManager.getDefault().setContext(getApplicationContext());
        ThemeChangeUtils.getInstance().register(this);
    }

    private void checkPermissionOrLoadPlugin() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)  {
            Toast.makeText(this, "需要开启存储权限", Toast.LENGTH_LONG);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }else {
            loadApk();//有权限再加载apk
        }
    }

    /**
     * 加载插件
     */
    private void loadApk() {

        PluginManager.getDefault().loadPath(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pluginapk-debug.apk");

//        Toast.makeText(this, "加载插件"+(PluginManager.getDefault().getPackageInfo() != null?"成功":"失败"), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MainActivity finalize()");
        //测试，什么时候会调用这个方法
    }

    /**
     * 跳转到插件activity
     * @param view
     */
    public void pluginActivityGo(View view){

        PackageInfo packageInfo = PluginManager.getDefault().getPackageInfo();
        if(packageInfo == null){
            Toast.makeText(this, "没有找到插件哦！", Toast.LENGTH_SHORT).show();
            return;
        }

        //跳转到插件apk中
        Intent intent = new Intent(this, ProxyActivity.class);
        //拿到插件apk的第一个activity
        String name = packageInfo.activities[0].name;
        intent.putExtra("name", name);

        startActivity(intent);//这里是跳转到插件activity中
    }

    /**
     * 从插件资源文件中获取并切换主题
     * @param view
     */
    public void loadPluginResource(View view){

        checkPermissionOrLoadPlugin();

        PackageInfo packageInfo = PluginManager.getDefault().getPackageInfo();
        if(packageInfo == null){
            Toast.makeText(this, "没有找到插件哦！", Toast.LENGTH_SHORT).show();
            return;
        }
        MyResource.usePluginTheme = true;
        ThemeChangeUtils.getInstance().updateTheme();
    }

    /**
     * 切换回宿主主题
     * @param view
     */
    public void unLoadPluginResource(View view){
        MyResource.usePluginTheme = false;
        ThemeChangeUtils.getInstance().updateTheme();
    }
}
