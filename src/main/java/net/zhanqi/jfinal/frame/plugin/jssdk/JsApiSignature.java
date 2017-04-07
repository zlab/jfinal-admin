package net.zhanqi.jfinal.frame.plugin.jssdk;

import com.jfinal.kit.HashKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.JsTicket;
import com.jfinal.weixin.sdk.api.JsTicketApi;
import net.zhanqi.jfinal.ext.util.CheckUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JsApi 签名
 * <p/>
 * Created by zhanqi on 2015/8/5.
 */
public class JsApiSignature {

    /**
     * 签名算法
     *
     * @param url 当前网页的完整URL，不包含#及其后面部分
     * @return map
     */
    public static Map<String, String> signature(String url) {

        CheckUtils.checkBlank(url, "JsSdk权限签名URL不能为空");

        JsTicket jsTicket = JsTicketApi.getTicket(JsTicketApi.JsApiType.jsapi);
        CheckUtils.checkTrue(!jsTicket.isSucceed(), jsTicket.getErrorMsg());

        String nonceStr = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();

        //注意这里参数名必须全部小写，且必须有序
        String signStr = "jsapi_ticket=" + jsTicket.getTicket() +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        String signature = HashKit.sha1(signStr);
        String appId = ApiConfigKit.getApiConfig().getAppId();

        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("appId", appId);
        retMap.put("nonceStr", nonceStr);
        retMap.put("timestamp", timestamp + "");
        retMap.put("signature", signature);

        return retMap;
    }
}
