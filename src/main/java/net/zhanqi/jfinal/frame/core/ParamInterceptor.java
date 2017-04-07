package net.zhanqi.jfinal.frame.core;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Record;
import net.zhanqi.jfinal.frame.model.SysParam;

import java.util.List;

/**
 * ParamInterceptor
 */
public class ParamInterceptor implements Interceptor {

    public void intercept(Invocation inv) {

        List<Record> paramList = SysParam.me.queryParamList();

        inv.getController().setAttr("_paramList", paramList);

        inv.invoke();
    }
}
