package net.zhanqi.jfinal.frame.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import net.zhanqi.jfinal.ext.util.CheckUtils;

import java.util.*;

/**
 * 菜单
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public class SysMenu extends Model<SysMenu> {

    public static final SysMenu me = new SysMenu();

    public Long getId() {
        return get("menuid");
    }

    public Long getParentId() {
        return get("parentid");
    }

    public List<SysMenu> getChildren() {
        return get("children");
    }

    public String getName() {
        return get("name");
    }

    public String getUri() {
        return get("uri");
    }

    public String getPerm() {
        return get("perm");
    }

    public String getBizcode() {
        return get("bizcode");
    }

    public Long getSort() {
        return get("sort");
    }

    public String getStatus() {
        return get("status");
    }

    public String getIconcls() {
        return get("iconcls");
    }

    /**
     * 角色菜单
     *
     * @param roleId
     * @return
     */
    public List<SysMenu> queryRoleMenuList(Long roleId) {
        CheckUtils.checkNull(roleId, "角色ID不能为空");

        String sql = "select t.*, t.menuid id from sys_menu t" +
                "  where exists (select 1 from sys_role_menu where roleid=? and menuid=t.menuid)" +
                "  or exists (select 1 from sys_menu t1, sys_role_menu t2" +
                "         where t2.roleid=? and t1.menuid=t2.menuid and t1.parentid = t.menuid)" +
                "   order by t.sort";

        return find(sql, roleId, roleId);
    }

    /**
     * 角色菜单权限
     *
     * @param roleId
     * @return
     */
    public Set<String> queryRoleMenuPermList(Long roleId) {
        CheckUtils.checkNull(roleId, "角色ID不能为空");

        Set<String> retSet = new HashSet<String>();

        List<SysMenu> menuList = queryRoleMenuList(roleId);

        if (menuList == null || menuList.isEmpty()) {
            return retSet;
        }

        for (SysMenu menu : menuList) {
            if (!StrKit.isBlank(menu.getPerm())) {
                retSet.add(menu.getPerm().trim());
            }
        }

        return retSet;
    }

    /**
     * 角色菜单，返回顶层
     *
     * @param roleId
     * @return
     */
    public List<SysMenu> queryTopRoleMenuList(Long roleId) {
        CheckUtils.checkNull(roleId, "角色ID不能为空");

        List<SysMenu> retList = new ArrayList<SysMenu>();

        List<SysMenu> menuList = queryRoleMenuList(roleId);

        if (menuList == null || menuList.isEmpty()) {
            return retList;
        }

        Map<Long, SysMenu> map = new HashMap<Long, SysMenu>();
        for (SysMenu menu : menuList) {
            menu.put("children", new ArrayList<SysMenu>());
            map.put(menu.getId(), menu);
        }

        for (SysMenu menu : menuList) {
            if (menu.getParentId() == null) {
                retList.add(menu);
                continue;
            }

            SysMenu parent = map.get(menu.getParentId());
            if (parent != null) {
                parent.getChildren().add(menu);
            }
        }

        return retList;
    }
}
