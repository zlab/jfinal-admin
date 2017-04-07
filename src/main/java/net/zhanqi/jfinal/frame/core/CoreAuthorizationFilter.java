package net.zhanqi.jfinal.frame.core;

import com.jfinal.render.RenderFactory;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限验证
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public abstract class CoreAuthorizationFilter extends AccessControlFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse res)
            throws IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        RenderFactory.me().getErrorRender(403).setContext(request, response).render();

        return false;
    }
}
