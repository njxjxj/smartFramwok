package org.smartXj.framwork.util;

/**
 * 类型转型工具类
 */
public final class CastUtil {
    /**
     * 转为string类型
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static String castString(Object object) {
        return castString(object, "");
    }

    public static double castDouble(Object object) {
        return castDouble(object, 0);
    }

    /***
     * 转为double类型
     * @param object
     * @param defaultVale
     * @return
     */
    public static double castDouble(Object object, double defaultVale) {
        double doubleValue = defaultVale;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultVale;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为LONG类型
     *
     * @param object
     * @param defaultVale
     * @return
     */
    public static long castLong(Object object, long defaultVale) {
        long LongValue = defaultVale;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    LongValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    LongValue = defaultVale;
                }
            }
        }
        return LongValue;
    }

    public static long castLong(Object obj) {
        return castLong(obj, 0);
    }


    /**
     * 转为int类型
     *
     * @param object
     * @param defaultVale
     * @return
     */
    public static int castInt(Object object, int defaultVale) {
        int IntValue = defaultVale;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    IntValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    IntValue = defaultVale;
                }
            }
        }
        return IntValue;
    }


    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }


    /**
     * 转为boolean类型
     *
     * @param object
     * @param defaultVale
     */
    public static boolean castBoolen(Object object, boolean defaultVale) {
        boolean boolenValue = defaultVale;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    boolenValue = Boolean.parseBoolean(strValue);
                } catch (NumberFormatException e) {
                    boolenValue = defaultVale;
                }
            }
        }
        return boolenValue;
    }

    public static boolean castBoolen(Object object) {
        return castBoolen(object, false);
    }
}

