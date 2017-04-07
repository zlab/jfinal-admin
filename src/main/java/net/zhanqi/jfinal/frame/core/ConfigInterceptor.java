package net.zhanqi.jfinal.frame.core;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import net.zhanqi.jfinal.frame.model.SysConfig;

/**
 * ConfigInterceptor
 */
public class ConfigInterceptor implements Interceptor {

    public void intercept(Invocation inv) {

        inv.getController().setAttr("_configMap", SysConfig.me.queryConfigMap());

        inv.invoke();
    }
}
