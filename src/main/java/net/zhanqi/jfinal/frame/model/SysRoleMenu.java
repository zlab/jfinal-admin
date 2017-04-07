package net.zhanqi.jfinal.frame.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 角色菜单
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public class SysRoleMenu extends Model<SysRoleMenu> {

    public static final SysRoleMenu me = new SysRoleMenu();

    public Long getId() {
        return get("id");
    }
}
