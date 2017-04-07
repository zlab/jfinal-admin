package net.zhanqi.jfinal.frame.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import net.zhanqi.jfinal.ext.util.CheckUtils;

import java.util.List;

/**
 * 部门
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public class SysDept extends Model<SysDept> {

    public static final SysDept me = new SysDept();

    public Long getId() {
        return get("deptid");
    }

    public String getName() {
        return get("name");
    }

    public Long getParentId() {
        return get("parentid");
    }

    public List<SysMenu> getChildren() {
        return get("children");
    }

    /**
     * 操作员部门
     *
     * @param operId 操作员ID
     * @return 列表
     */
    public List<SysDept> queryOperDeptList(Long operId) {

        CheckUtils.checkNull(operId, "用户ID不能为空");

        String sql = "select t.*, t.deptid id from sys_dept t" +
                " where exists (select 1 from sys_oper_dept_role" +
                "     where deptid=t.deptid and operid=?)";

        return find(sql, operId);
    }

    public List<Record> queryDeptParamList() {
        String sql = "select 'SYS_DEPT' gcode, deptid mcode, name mname from sys_dept";
        return Db.find(sql);
    }
}
