package net.zhanqi.jfinal.frame.service;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.frame.model.SysOper;
import net.zhanqi.jfinal.frame.model.SysOperDeptRole;

public class OperService {

    public static final OperService me = Enhancer.enhance(OperService.class);

    public SysOper queryOper(Long operId) {
        CheckUtils.checkNull(operId, "用户ID不能为空");
        return SysOper.me.findById(operId);
    }

    public Page<SysOper> queryOperList(int pageNumber, int pageSize, SysOper oper) {
        String select = "select t.*";
        String from = "from sys_oper t";
        return SysOper.me.paginate(pageNumber, pageSize, select, from);
    }

    public void deleteOper(Long operId) {
        CheckUtils.checkNull(operId, "用户ID不能为空");
        SysOper.me.deleteById(operId);
    }

    public void saveOper(SysOper oper) {

        CheckUtils.checkBlank(oper.getUsername(), "账号不能为空");

        if (oper.getId() == null) {
            String sql = "select count(1) from sys_oper where username=?";
            long count = Db.queryLong(sql, oper.getUsername());
            CheckUtils.checkTrue(count > 0, "帐号【%s】已经存在", oper.getUsername());
            oper.setPassword(SysOper.encryptPassword("123456")).save();
        } else {
            oper.update();
        }
    }

    public String resetPassword(Long operId) {
        CheckUtils.checkNull(operId, "用户ID不能为空");

        SysOper oper = SysOper.me.findById(operId);
        CheckUtils.checkNull(oper, "用户没有找到【%s】", operId);

        oper.setPassword(SysOper.encryptPassword("123456")).update();

        return "123456";
    }

    public void saveOperDeptRole(SysOperDeptRole deptRole) {

        CheckUtils.checkNull(deptRole.getOperId(), "用户不能为空");
        CheckUtils.checkNull(deptRole.getDeptId(), "部门不能为空");
        CheckUtils.checkNull(deptRole.getRoleId(), "角色不能为空");

        String sql = "select count(1) from sys_oper_dept_role where operid=? and deptid=?";
        long count = Db.queryLong(sql, deptRole.getOperId(), deptRole.getDeptId());
        CheckUtils.checkTrue(count > 0, "一个部门只能有一个角色，请先删除该部门下的角色");

        deptRole.save();
    }

    public Page<SysOperDeptRole> queryOperDeptRoleList(int pageNumber, int pageSize, Long operId) {

        CheckUtils.checkNull(operId, "用户ID不能为空");

        String select = "select t.id," +
                " (select name from sys_oper where operid=t.operid) opername," +
                " (select name from sys_dept where deptid=t.deptid) deptname," +
                " (select name from sys_role where roleid=t.roleid) rolename";

        String from = "from sys_oper_dept_role t where t.operId=?";

        return SysOperDeptRole.me.paginate(pageNumber, pageSize, select, from, operId);
    }

    public void deleteOperDeptRole(Long id) {
        CheckUtils.checkNull(id, "部门角色ID不能为空");
        SysOperDeptRole.me.deleteById(id);
    }

}
