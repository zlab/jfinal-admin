package net.zhanqi.jfinal.frame.core;

import com.jfinal.render.RenderFactory;
import net.zhanqi.jfinal.ext.util.ActionUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录认证
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public abstract class CoreAuthenticationFilter extends AccessControlFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse res) throws Exception {

        HttpServletRequest request = WebUtils.toHttp(req);
        HttpServletResponse response = WebUtils.toHttp(res);

        if (ActionUtils.isAjax(request)) {
            RenderFactory.me().getErrorRender(401).setContext(request, response).render();
        } else {
            saveRequestAndRedirectToLogin(request, response);
        }

        return false;
    }
}
