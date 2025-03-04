package com.uitgis.ciams.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ValidUtil {
    /**
     * Object type 변수가 비어있는지 체크
     *
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean empty(Object obj) {
        if (obj instanceof String) return "".equals(obj.toString().trim());
        else if (obj instanceof List) return ((List<?>) obj).isEmpty();
        else if (obj instanceof Map) return ((Map<?, ?>) obj).isEmpty();
        else if (obj instanceof Object[]) return Array.getLength(obj) == 0;
        else return obj == null;
    }

    /**
     * Object type 변수가 비어있지 않은지 체크
     *
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean notEmpty(Object obj) {
        return !empty(obj);
    }


    public static boolean isNumericDataType(String type) {
        List<String> numericDataType = Arrays.asList("INTEGER", "DOUBLE");
        return numericDataType.contains(type.toUpperCase(Locale.ROOT));
    }

    public static String toStr(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    public static Integer toInteger(Object obj) {
        if (empty(obj)) {
            return null;
        }
        return Integer.valueOf(String.valueOf(obj));
    }

    public static Integer toInteger(Object obj, int replace) {
        if (empty(obj)) {
            return replace;
        }
        return (int) obj;
    }
}
