package net.zhanqi.jfinal.frame.util;

import net.zhanqi.jfinal.ext.core.CustomException;

import java.util.Collection;

/**
 * CheckUtils
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public final class CheckUtils {

    public static void throwException(String format, Object... args) {
        throw new CustomException(String.format(format, args));
    }

    public static void checkNull(Object obj, String format, Object... args) {
        if (obj == null) {
            throwException(format, args);
        }
    }

    public static void checkBlank(String str, String format, Object... args) {
        if (str == null || str.trim().length() == 0) {
            throwException(format, args);
        }
    }

    public static void checkEmpty(Collection<?> collection, String format,
                                  Object... args) {
        if (collection == null || collection.isEmpty()) {
            throwException(format, args);
        }
    }

    public static void checkEmpty(Object[] array, String format, Object... args) {
        if (array == null || array.length == 0) {
            throwException(format, args);
        }
    }

    public static void checkTrue(boolean val, String format, Object... args) {
        if (val) {
            throwException(format, args);
        }
    }

}
