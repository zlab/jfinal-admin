package net.zhanqi.jfinal.frame.core;

import com.jfinal.core.JFinal;
import com.jfinal.render.ErrorRender;

/**
 * CustomErrorRender
 */
public class CustomErrorRender extends ErrorRender {

    private String errorMsg;

    public CustomErrorRender(String errorMsg) {
        super(400, JFinal.me().getConstants().getErrorView(400));
        this.errorMsg = errorMsg;
    }

    @Override
    public void render() {

        request.setAttribute("errorMsg", errorMsg);

        super.render();
    }
}
