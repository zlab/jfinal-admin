package net.zhanqi.jfinal.frame.controller;

import net.zhanqi.jfinal.frame.core.BaseController;
import net.zhanqi.jfinal.frame.service.ParamService;

public class ParamController extends BaseController {

    public void main() {
        render("paramMain.ftl");
    }

    /**
     * 参数节点，所有数据
     */
    public void queryParamNodeList() {
        renderJson(ParamService.me.queryParamNodeList());
    }
}
