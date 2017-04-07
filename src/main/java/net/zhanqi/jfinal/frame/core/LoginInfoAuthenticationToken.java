package net.zhanqi.jfinal.frame.core;

import net.zhanqi.jfinal.ext.util.CheckUtils;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class LoginInfoAuthenticationToken implements RememberMeAuthenticationToken {

    private LoginInfo loginInfo;

    public LoginInfoAuthenticationToken(LoginInfo loginInfo) {
        CheckUtils.checkNull(loginInfo, "登录信息不能为空");
        this.loginInfo = loginInfo;
    }

    @Override
    public Object getPrincipal() {
        return loginInfo;
    }

    @Override
    public Object getCredentials() {
        return loginInfo;
    }

    @Override
    public boolean isRememberMe() {
        return loginInfo.isRememberMe();
    }
}
