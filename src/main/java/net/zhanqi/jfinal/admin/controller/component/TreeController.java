package net.zhanqi.jfinal.admin.controller.component;

import net.zhanqi.jfinal.admin.service.component.TreeService;
import net.zhanqi.jfinal.frame.core.BaseController;

/**
 * Created by zhanqi on 2015/8/12.
 */
public class TreeController extends BaseController {

    public void index() {
        render("tree.ftl");
    }

    public void dept() {
        renderJson(TreeService.me.queryDeptNodeList());
    }

}
