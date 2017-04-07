package net.zhanqi.jfinal.frame.service;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.frame.model.SysDept;

import java.util.List;

public class DeptService {

    public static final DeptService me = Enhancer.enhance(DeptService.class);

    public List<SysDept> queryDeptNodeList() {
        String sql = "select t.*, t.deptid id from sys_dept t";
        return SysDept.me.find(sql);
    }

    public SysDept queryDept(Long deptId) {
        CheckUtils.checkNull(deptId, "部门ID不能为空");
        return SysDept.me.findById(deptId);
    }

    public List<SysDept> queryDeptSiblings(Long deptId) {
        CheckUtils.checkNull(deptId, "部门ID不能为空");

        String sql = "select t.*, t.deptid id from sys_dept t where exists" +
                " (select 1 from sys_dept where parentid=t.parentid and deptid=?)";

        return SysDept.me.find(sql, deptId);
    }

    public void saveDept(SysDept dept) {
        CheckUtils.checkBlank(dept.getName(), "部门名称不能为空");

        if (dept.getId() == null) {
            dept.save();
        } else {
            dept.update();
        }
    }

    public void deleteDept(Long deptId) {
        CheckUtils.checkNull(deptId, "部门ID不能为空");

        String sql = "select count(1) from sys_dept where parentid=?";
        long count = Db.queryLong(sql, deptId);
        CheckUtils.checkTrue(count > 0, "请先删除该部门的所有下级部门");

        // 删除用户部门角色
        Db.update("delete from sys_oper_dept_role where deptid=?", deptId);

        // 删除部门
        SysDept.me.deleteById(deptId);
    }

}
