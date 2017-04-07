package net.zhanqi.jfinal.frame.core;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * PermanentRedirect
 *
 * @author zhanqi
 * @since 2012-08-08
 */
public class PermanentRedirectHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {

        // 过滤资源文件
        if (target.contains(".")) {
            return;
        }

        // 永久重定向
        if (!target.endsWith("/")) {
            StringBuffer sb = request.getRequestURL().append("/");
            if (request.getQueryString() != null) {
                sb.append("?").append(request.getQueryString());
            }

            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", sb.toString());
            response.setHeader("Connection", "close");

            isHandled[0] = true;
            return;
        }

        target = target.substring(0, target.length() - 1);
        nextHandler.handle(target, request, response, isHandled);
    }

}
