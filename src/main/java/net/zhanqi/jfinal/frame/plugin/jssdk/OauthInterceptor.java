package net.zhanqi.jfinal.frame.plugin.jssdk;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhanqi on 2015/8/5.
 */
public class OauthInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {

        Controller controller = inv.getController();

        String openid = controller.getSessionAttr("openid");

        if (openid == null) {

            String appId = ApiConfigKit.getApiConfig().getAppId();
            String code = controller.getPara("code");

            if (code == null) {

                String redirectUri = PropKit.get("serverName") + controller.getRequest().getRequestURI();
                try {
                    redirectUri = URLEncoder.encode(redirectUri, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                // snsapi_base/snsapi_userinfo
                String url = SnsAccessTokenApi.getAuthorizeURL(appId, redirectUri, true);

                controller.redirect(url);
                return;
            }

            SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId,
                    ApiConfigKit.getApiConfig().getAppSecret(), code);
            controller.setSessionAttr("openid", snsAccessToken.getOpenid());
        }


        inv.invoke();

    }

    public String getScope() {
        return "snsapi_base";
    }
}
