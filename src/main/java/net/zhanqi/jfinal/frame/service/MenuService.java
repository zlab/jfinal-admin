package net.zhanqi.jfinal.frame.service;

import com.jfinal.aop.Enhancer;
import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.frame.model.SysMenu;

import java.util.List;

public class MenuService {

    public static final MenuService me = Enhancer.enhance(MenuService.class);

    public List<SysMenu> queryMenuNodeList() {
        String sql = "select t.*, t.menuid id from sys_menu t order by sort";
        return SysMenu.me.find(sql);
    }

    public void saveMenu(SysMenu menu) {
        CheckUtils.checkNull(menu.getId(), "菜单ID不能为空");
        menu.update();
    }

}
