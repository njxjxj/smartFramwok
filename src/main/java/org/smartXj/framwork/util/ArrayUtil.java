package org.smartXj.framwork.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ArrayUtil {

    public static boolean isEmpty(Field[] fields) {
        if (fields == null || fields.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Field[] fields) {
        return !isEmpty(fields);
    }


    public static boolean isNotEmpty(Method[] methods) {
        if (methods == null || methods.length == 0) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }
    public static boolean isNotEmpty(Object[] array)
    {
        return !isEmpty(array);
    }
}
