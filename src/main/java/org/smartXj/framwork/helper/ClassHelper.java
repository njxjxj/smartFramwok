package org.smartXj.framwork.helper;

import org.smartXj.framwork.util.ClassUtil;

import java.util.Set;

/**
 * 类操作助手
 */
public class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackge = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackge); //在本来还未初始化时就加载基础包下所有的类
    }

    /**
     * 获取应用包名下所有的类
     * @return
     */
    public static Set<Class<?>> getClassSet()
    {
        return CLASS_SET;
    }


}
