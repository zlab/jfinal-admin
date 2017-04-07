package net.zhanqi.jfinal.frame.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 操作员部门角色
 *
 * @author zhanqi
 * @since 2013-03-08
 */
public class SysOperDeptRole extends Model<SysOperDeptRole> {

    public static final SysOperDeptRole me = new SysOperDeptRole();

    public Long getId() {
        return get("id");
    }

    public Long getOperId() {
        return get("operid");
    }

    public Long getDeptId() {
        return get("deptid");
    }

    public Long getRoleId() {
        return get("roleid");
    }

}
