package net.zhanqi.jfinal.frame.core;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * AuthRealm
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public class CoreAuthorizingRealm extends AuthorizingRealm {

    @Override
    protected void onInit() {
        setAuthenticationTokenClass(LoginInfoAuthenticationToken.class);
        setCredentialsMatcher(new AllowAllCredentialsMatcher());
//        setCacheManager(new MemoryConstrainedCacheManager());
        setCachingEnabled(false);
        super.onInit();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        return new SimpleAuthenticationInfo(token.getPrincipal(),
                token.getCredentials(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        LoginInfo loginInfo = (LoginInfo) principals.getPrimaryPrincipal();

        return loginInfo.getAuthorizationInfo();
    }

}
