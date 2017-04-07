package net.zhanqi.jfinal.frame.controller;

import net.zhanqi.jfinal.frame.core.BaseController;
import net.zhanqi.jfinal.frame.model.SysRole;
import net.zhanqi.jfinal.frame.service.RoleService;

public class RoleController extends BaseController {

    /**
     * 角色管理
     */
    public void main() {
        render("roleMain.ftl");
    }

    /**
     * 角色列表
     */
    public void list() {
        setPage(RoleService.me.queryRoleList(getPageNumber(), getPageSize(), null));
        render("roleList.ftl");
    }

    /**
     * 角色编辑
     */
    public void input() {
        if (getId() != null) {
            setAttr("role", RoleService.me.queryRole(getId()));
        }

        render("roleInput.ftl");
    }

    /**
     * 角色保存
     */
    public void saveRole() {
        RoleService.me.saveRole(getModel(SysRole.class, "role"));
        renderJson(true);
    }

    /**
     * 角色删除
     */
    public void deleteRole() {
        RoleService.me.deleteRole(getId());
        renderJson(true);
    }

    /**
     * 查询所有菜单，选中角色下的菜单
     */
    public void queryMenuNodeList() {
        renderJson(RoleService.me.queryMenuNodeList(getId()));
    }

    /**
     * 保存角色菜单
     */
    public void saveRoleMenu() {
        RoleService.me.saveRoleMenu(getId(), getParaValuesToLong("delIds"),
                getParaValuesToLong("addIds"));
        renderJson(true);
    }

}
