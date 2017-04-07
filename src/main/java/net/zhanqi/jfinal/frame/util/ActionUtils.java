package net.zhanqi.jfinal.frame.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ActionUtils
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public class ActionUtils {

    /**
     * 是否ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    /**
     * 是否Open请求
     */
    public static boolean isJsonFormat(HttpServletRequest request) {
        return "json".equalsIgnoreCase(request.getParameter("format"));
    }

    /**
     * 是否请求HTML
     */
    public static boolean isHtml(HttpServletRequest request) {
        return "html".equalsIgnoreCase(request.getParameter("format"));
    }
}
