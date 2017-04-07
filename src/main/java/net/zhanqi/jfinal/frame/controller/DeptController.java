package net.zhanqi.jfinal.frame.controller;

import net.zhanqi.jfinal.frame.core.BaseController;
import net.zhanqi.jfinal.frame.model.SysDept;
import net.zhanqi.jfinal.frame.service.DeptService;

public class DeptController extends BaseController {

    /**
     * 部门管理
     */
    public void main() {
        render("deptMain.ftl");
    }

    /**
     * 部门树节点
     */
    public void queryDeptNodeList() {
        renderJson(DeptService.me.queryDeptNodeList());
    }

    /**
     * 部门编辑
     */
    public void input() {
        if (getId() != null) {
            setAttr("dept", DeptService.me.queryDept(getId()));
        }

        setAttr("parent", DeptService.me.queryDept(getParaToLong("parentid")));

        render("deptInput.ftl");
    }

    /**
     * 保存部门
     */
    public void saveDept() {
        SysDept dept = getModel(SysDept.class, "dept");
        DeptService.me.saveDept(dept);

        dept.put("parentid", getParaToLong("parentid"));

        renderJson(dept);
    }

    /**
     * 删除部门
     */
    public void deleteDept() {
        DeptService.me.deleteDept(getId());
        renderJson(true);
    }
}
