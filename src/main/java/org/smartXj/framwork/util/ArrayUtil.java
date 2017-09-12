package org.smartXj.framwork.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ArrayUtil {
    public static boolean isEmpty(Array array) {
        if (array != null) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(Array array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(Field[] fields) {
        if (fields == null || fields.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Field[] fields) {
        return !isEmpty(fields);
    }
}
