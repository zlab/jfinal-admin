package net.zhanqi.jfinal.frame.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import net.zhanqi.jfinal.ext.util.CheckUtils;

import java.util.List;

/**
 * 角色
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public class SysRole extends Model<SysRole> {

    public static final SysRole me = new SysRole();

    public Long getId() {
        return get("roleid");
    }

    public String getName() {
        return get("name");
    }

    public String getMemo() {
        return get("memo");
    }

    public SysRole queryOperDeptRole(Long operId, Long deptId) {

        CheckUtils.checkNull(operId, "用户ID不能为空");
        CheckUtils.checkNull(deptId, "部门ID不能为空");

        String sql = "select t.*, t.roleid id from sys_role t" +
                "  where exists(select 1 from sys_oper_dept_role" +
                "     where roleid=t.roleid and operid=? and deptid=?)";

        return findFirst(sql, operId, deptId);
    }

    public List<Record> queryRoleParamList() {
        String sql = "select 'SYS_ROLE' gcode, roleid mcode, name mname from sys_role";
        return Db.find(sql);
    }

}
