package net.zhanqi.jfinal.frame.plugin.jssdk;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.kit.ParaMap;
import net.zhanqi.jfinal.ext.core.BaseController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhanqi on 2015/8/5.
 */
public class BaseOauthInterceptor implements Interceptor {

    public static String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s#wechat_redirect";

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        if (!(controller instanceof BaseController))
            throw new RuntimeException("控制器需要继承 BaseController");

        try {
            ApiConfigKit.setThreadLocalApiConfig(((BaseController) controller).getApiConfig());


            String appId = ApiConfigKit.getApiConfig().getAppId();
            String openid = controller.getSessionAttr("openid");

            if (openid == null) {

                String code = controller.getPara("code");

                if (code == null) {
                    String redirectUri = controller.getRequest().getRequestURL().toString();
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

        } finally {
            ApiConfigKit.removeThreadLocalApiConfig();
        }

    }

    public String getScope() {
        return "snsapi_base";
    }
}
