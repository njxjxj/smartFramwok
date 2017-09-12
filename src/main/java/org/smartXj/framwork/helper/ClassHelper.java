package org.smartXj.framwork.helper;

import org.smartXj.framwork.annotation.Controller;
import org.smartXj.framwork.annotation.Service;
import org.smartXj.framwork.util.ClassUtil;

import java.util.HashSet;
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
     *
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }


    /**
     * 获取所有Service类
     *
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> ClassSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                ClassSet.add(cls);
            }
        }
        return ClassSet;
    }

    /**
     * 获取所有controller类
     *
     * @return
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> ClassSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                ClassSet.add(cls);
            }
        }
        return ClassSet;
    }

    /**
     * 获取应用包下的所有Bean
     * @return
     */
    public  static  Set<Class<?>> getBeanClassSet()
    {
        Set<Class<?>> Bean=new HashSet<Class<?>>();
        Bean.addAll(getServiceClassSet());
        Bean.addAll(getControllerClassSet());
        return Bean;

    }

}
