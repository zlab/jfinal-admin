package net.zhanqi.jfinal.frame.plugin.jssdk;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhanqi on 2015/8/5.
 */
public class UserinfoOauthInterceptor extends BaseOauthInterceptor {

    @Override
    public String getScope() {
        return "snsapi_userinfo";
    }
}
