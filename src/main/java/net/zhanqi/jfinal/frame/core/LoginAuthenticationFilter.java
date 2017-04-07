package net.zhanqi.jfinal.frame.core;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 登陆认证
 *
 * @author zhanqi
 * @since 2014-12-26
 */
public class LoginAuthenticationFilter extends CoreAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        return getSubject(request, response).isAuthenticated();
    }
}
