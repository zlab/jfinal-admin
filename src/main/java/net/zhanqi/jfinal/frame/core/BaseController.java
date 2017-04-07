package net.zhanqi.jfinal.frame.core;

import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.ApiConfig;

/**
 * Created by zhanqi on 2015/8/20.
 */
public abstract class BaseController extends Controller {
    public abstract ApiConfig getApiConfig();
}
