package net.zhanqi.jfinal.frame.controller;

import com.jfinal.plugin.activerecord.Record;
import net.zhanqi.jfinal.frame.core.BaseController;
import net.zhanqi.jfinal.frame.model.SysDept;
import net.zhanqi.jfinal.frame.model.SysOper;
import net.zhanqi.jfinal.frame.model.SysRole;
import net.zhanqi.jfinal.frame.service.OperService;

import java.util.List;

public class OperController extends BaseController {

    /**
     * 用户管理
     */
    public void main() {
        List<Record> paramList = getAttr("_paramList");
        paramList.addAll(SysRole.me.queryRoleParamList());

        render("operMain.ftl");
    }

    /**
     * 用户列表
     */
    public void list() {
        setAttr("page", OperService.me.queryOperList(getPageNumber(), getPageSize(), null));
        render("operList.ftl");
    }

    /**
     * 用户录入
     */
    public void input() {
        if (getId() != null) {
            setAttr("oper", OperService.me.queryOper(getId()));
        }

        render("operInput.ftl");
    }

    /**
     * 用户保存
     */
    public void saveOper() {
        OperService.me.saveOper(getModel(SysOper.class, "oper"));
        renderJson(true);
    }

    /**
     * 用户删除
     */
    public void deleteOper() {
        OperService.me.deleteOper(getId());
        renderJson(true);
    }

    /**
     * 密码重置
     */
    public void resetPassword() {
        renderJson("password", OperService.me.resetPassword(getId()));
    }

    /**
     * 用户部门角色列表
     */
    public void queryOperDeptRoleList() {
        setPage(OperService.me.queryOperDeptRoleList(getPageNumber(), getPageSize(), getId()));
        render("operDeptRoleList.ftl");
    }

    /**
     * 保存用户部门角色关系
     */
    public void saveOperDeptRole() {
//        OperService.me.saveOperDeptRole(deptRole);
        renderJson(true);
    }

    /**
     * 删除用户部门角色
     */
    public void deleteOperDeptRole() {
        OperService.me.deleteOperDeptRole(getId());
        renderJson(true);
    }
}
