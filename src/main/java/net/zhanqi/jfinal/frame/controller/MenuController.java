package net.zhanqi.jfinal.frame.controller;

import net.zhanqi.jfinal.frame.core.BaseController;
import net.zhanqi.jfinal.frame.model.SysMenu;
import net.zhanqi.jfinal.frame.service.MenuService;

public class MenuController extends BaseController {

    /**
     * 菜单管理
     */
    public void main() {
        render("menuMain.ftl");
    }

    /**
     * 菜单树节点
     */
    public void queryMenuNodeList() {
        renderJson(MenuService.me.queryMenuNodeList());
    }

    /**
     * 保存菜单
     */
    public void saveMenu() {
        MenuService.me.saveMenu(getModel(SysMenu.class, "menu"));
        renderJson(true);
    }

}