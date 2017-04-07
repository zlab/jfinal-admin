package net.zhanqi.jfinal.frame.core;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * FlagInterceptor
 */
public class FlagInterceptor implements Interceptor {

    public void intercept(Invocation inv) {

        Controller controller = inv.getController();

        String flag = controller.getPara("flag");

        if ("view".equals(flag)) {
            controller.setAttr("disabled", "disabled");
            controller.setAttr("readonly", "readonly");
        }

        inv.invoke();
    }
}
