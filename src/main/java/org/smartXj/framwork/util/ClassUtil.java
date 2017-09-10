package org.smartXj.framwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 类操作工具
 */
public  final class ClassUtil {
    private static  final Logger LOGGER= LoggerFactory.getLogger("ClassUtil");

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader()
    {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String className,boolean isInitialized)
    {

    }

    /**
     * 获取指定包名下所有类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName)
    {

    }

}
