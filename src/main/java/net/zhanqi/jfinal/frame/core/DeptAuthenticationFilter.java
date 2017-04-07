package net.zhanqi.jfinal.frame.core;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 部门认证
 *
 * @author zhanqi
 * @since 2014-12-26
 */
public class DeptAuthenticationFilter extends CoreAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                      Object mappedValue) {

        LoginInfo loginInfo = (LoginInfo) getSubject(request, response).getPrincipal();

        return loginInfo.getDeptId() != null;
    }
}
