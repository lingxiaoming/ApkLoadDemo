package com.zyy.pluginlibrary;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 换肤操作
 */
public class ThemeChangeUtils {

    private ThemeChangeUtils() {
    }

    public static ThemeChangeUtils getInstance() {
        return Instance.utils;
    }

    static class Instance {
        public static ThemeChangeUtils utils = new ThemeChangeUtils();
    }


    Map<Object, List<FieldBean>> objectMap = new HashMap<>();

    class FieldBean {
        Field field;
        PluginResourceFirst pluginResourceFirst;
    }


    /**
     * 注册页面，拿到所有PluginResourceFrist的注解控件
     *
     * @param object
     */
    public void register(Object object) {
        List<FieldBean> fieldArrayList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();//所有本来中的方法(可以优化到包括所有父类方法）
        for (Field field : fields) {
            PluginResourceFirst resourceFirst = field.getAnnotation(PluginResourceFirst.class);
            if (resourceFirst != null) {
                FieldBean fieldBean = new FieldBean();
                fieldBean.field = field;
                fieldBean.pluginResourceFirst = resourceFirst;
                fieldArrayList.add(fieldBean);
            }
        }

        objectMap.put(object, fieldArrayList);
    }


    /**
     * 更新主题皮肤
     */
    public void updateTheme() {
        Set<?> set = objectMap.keySet();
        Iterator<?> iterator = set.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            List<FieldBean> fields = objectMap.get(obj);

            for (FieldBean fieldBean : fields) {

                if (View.class.isAssignableFrom(fieldBean.field.getType())) {
                    Field field = fieldBean.field;
                    try {
                        View view = (View) field.get(obj);
                        Class clazz = fieldBean.field.getType();
                        PluginResourceFirst pluginResourceFirst = fieldBean.pluginResourceFirst;
                        int bg_color = pluginResourceFirst.bg_color();
                        int text_color = pluginResourceFirst.text_color();
                        int bg_image = pluginResourceFirst.bg_image();
                        int src_image = pluginResourceFirst.src_image();
                        int text_str = pluginResourceFirst.text_str();

                        System.out.println(String.format("view:%s, bg_color:%s, text_color:%s, bg_image:%s, src_image:%s, text_str:%s",
                                view.getClass().getSimpleName(), bg_color, text_color, bg_image, src_image, text_str));


                        if (View.class.isAssignableFrom(clazz)) {//只要是view，就可以换背景
                            if (bg_color > 0) {
                                view.setBackgroundColor(MyResource.getResource().getColor(bg_color));
                            }

                            if (bg_image > 0) {
                                view.setBackground(MyResource.getResource().getDrawable(bg_image));
                            }
                        }


                        if (TextView.class == clazz) {
                            TextView textView = (TextView) view;
                            if(text_color > 0){
                                textView.setTextColor(MyResource.getResource().getColor(text_color));
                            }

                            if(text_str > 0){
                                textView.setText(MyResource.getResource().getString(text_str));
                            }

                        } else if (ImageView.class == clazz || ImageButton.class == clazz) {
                            ImageView imageView = (ImageView) view;
                            if(src_image > 0){
                                imageView.setImageDrawable(MyResource.getResource().getDrawable(src_image));
                            }
                        } else if (Button.class == clazz) {
                            Button button = (Button) view;
                            if(text_color > 0){
                                button.setTextColor(MyResource.getResource().getColor(text_color));
                            }

                            if(text_str > 0){
                                button.setText(MyResource.getResource().getString(text_str));
                            }
                        }


                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }


}
