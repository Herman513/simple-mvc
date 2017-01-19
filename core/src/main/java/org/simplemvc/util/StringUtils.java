package org.simplemvc.util;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperFirst(String str) {
        if (StringUtils.isEmpty(str))
            return str;

        char ch = str.charAt(0);
        if (ch < 'a' || ch > 'z')
            return str;

        char[] cs = str.toCharArray();
        cs[0] = Character.toUpperCase(ch);
        return String.valueOf(cs);
    }
}
