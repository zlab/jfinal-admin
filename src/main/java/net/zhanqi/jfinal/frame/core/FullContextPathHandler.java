package net.zhanqi.jfinal.frame.core;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FullContextPathHandler extends Handler {

    private String fullContextPathName;

    public FullContextPathHandler() {
        fullContextPathName = "FULL_PATH";
    }

    public FullContextPathHandler(String fullContextPathName) {
        if (StrKit.isBlank(fullContextPathName))
            throw new IllegalArgumentException("fullContextPathName can not be blank.");
        this.fullContextPathName = fullContextPathName;
    }

    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {
        String fullPath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            fullPath += ":" + request.getServerPort();
        }

        fullPath += request.getContextPath();

        request.setAttribute(fullContextPathName, fullPath);

        nextHandler.handle(target, request, response, isHandled);
    }

}
