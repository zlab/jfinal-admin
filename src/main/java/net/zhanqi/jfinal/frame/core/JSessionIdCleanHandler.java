package net.zhanqi.jfinal.frame.core;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JSessionIdCleanHandler
 *
 * @author zhanqi
 * @since 2012-08-08
 */
public class JSessionIdCleanHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {

        if (target.contains(";jsessionid")) {
            target = target.replaceFirst(";jsessionid.*", "");
        }

        nextHandler.handle(target, request, response, isHandled);
    }

}
