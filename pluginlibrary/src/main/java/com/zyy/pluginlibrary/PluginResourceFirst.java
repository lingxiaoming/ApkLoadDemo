package com.zyy.pluginlibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 以加载插件资源优先，注意插件的所有资源名字要和宿主资源名字一样
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginResourceFirst {

    int text_color() default 0;
    int bg_color() default 0;
    int bg_image() default 0;
    int src_image() default 0;
    int text_str() default 0;

}
