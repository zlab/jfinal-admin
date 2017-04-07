package net.zhanqi.jfinal.frame.service;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.ext.util.DaoUtils;
import net.zhanqi.jfinal.frame.model.SysMenu;
import net.zhanqi.jfinal.frame.model.SysRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleService {

    public static final RoleService me = Enhancer.enhance(RoleService.class);

    public SysRole queryRole(Long roleId) {
        CheckUtils.checkNull(roleId, "角色ID不能为空");
        return SysRole.me.findById(roleId);
    }

    public Page<SysRole> queryRoleList(int pageNumber, int pageSize, SysRole role) {
        String select = "select *";
        String from = "from sys_role";
        return SysRole.me.paginate(pageNumber, pageSize, select, from);
    }

    public void deleteRole(Long roleId) {

        CheckUtils.checkNull(roleId, "角色ID不能为空");

        // 删除用户部门角色
        String sql = "delete from sys_oper_dept_role where roleid=?";
        Db.update(sql, roleId);

        // 删除角色菜单
        sql = "delete from sys_role_menu where roleid=?";
        Db.update(sql, roleId);

        // 删除角色
        SysRole.me.deleteById(roleId);
    }

    public void saveRole(SysRole role) {

        CheckUtils.checkBlank(role.getName(), "角色名称不能为空");

        if (role.getId() == null) {
            role.save();
        } else {
            role.update();
        }
    }

    public void saveRoleMenu(Long roleId, Long[] delIds, Long[] addIds) {

        CheckUtils.checkNull(roleId, "角色ID不能为空");

        List<Object> params;

        if (delIds != null && delIds.length > 0) {

            // 删除角色菜单
            String sql = "delete from sys_role_menu where roleid=? and menuid in " +
                    DaoUtils.createInStatement(delIds.length);

            params = new ArrayList<Object>();
            params.addAll(Arrays.asList(delIds));
            params.add(0, roleId);

            Db.update(sql, params.toArray());
        }

        if (addIds != null && addIds.length > 0) {
            // 新增角色菜单
            String sql = "insert into sys_role_menu(roleid, menuid) values ";

            StringBuilder sb = new StringBuilder();
            params = new ArrayList<Object>();
            for (Long menuId : addIds) {
                sb.append(", (?, ?)");
                params.add(roleId);
                params.add(menuId);
            }

            Db.update(sql + sb.toString().substring(2), params.toArray());
        }

    }

    public List<SysMenu> queryMenuNodeList(Long roleId) {
        CheckUtils.checkNull(roleId, "角色ID不能为空");


        String sql = "select t1.menuid id," +
                "       t1.name," +
                "       t1.parentid," +
                "       t2.id is not null checked," +
                "       t2.id is not null owned" +
                "  from sys_menu t1" +
                "  left join sys_role_menu t2" +
                "    on t1.menuid = t2.menuid" +
                "   and t2.roleid = ? order by t1.sort";

        return SysMenu.me.find(sql, roleId);
    }

}
