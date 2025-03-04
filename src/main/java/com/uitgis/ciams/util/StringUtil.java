package com.uitgis.ciams.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String getFileNameWithoutExt(String fileName) {
        if (fileName == null) {
            return "";
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static String getFileExt(String fileName) {
        if (fileName == null) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    public static String fixNullReplace(String org, String replace) {
        if (org == null || org.equals("")) {
            return replace;
        }

        return org;
    }

    public static String fixNull(String str) {
        if (str == null) {
            return "";
        }

        return str;
    }

    /**
     * rgb(xxx, xxx, xxx)의 컬러 정보를 헥사코드 컬러값으로 변환
     *
     * @param rgb RGB 컬러 정보
     * @return 헥사코드 컬러 정보
     */
    public static String rgbToHex(String rgb) {

        Integer[] rgbColor = getRgb(rgb);
        if (rgbColor == null) {
            return null;
        }

        return rgbToHex(rgbColor);
    }


    /**
     * rgb(xxx, xxx, xxx)의 컬러 정보에서 rgb 갑 추출
     *
     * @param rgb RGB 컬러 정보
     * @return RGB 값
     */
    public static Integer[] getRgb(String rgb) {
        String regex = "rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(rgb);
        if (!matcher.find() || matcher.groupCount() != 3) {
            return null;
        }
        Integer[] rgbColor = new Integer[3];
        rgbColor[0] = Integer.parseInt(matcher.group(1));
        rgbColor[1] = Integer.parseInt(matcher.group(2));
        rgbColor[2] = Integer.parseInt(matcher.group(3));

        return rgbColor;
    }

    /**
     * rgb color array  to hex
     *
     * @param rgbColor rgb 컬러
     * @return 헥사코드 컬러
     */
    public static String rgbToHex(Integer[] rgbColor) {
        return String.format("#%02x%02x%02x", rgbColor[0], rgbColor[1], rgbColor[2]);
    }


    public static String camelToSnake(String str) {

        // Empty String
        StringBuilder result = new StringBuilder();

        char c = str.charAt(0);
        result.append(Character.toLowerCase(c));

        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }


}
