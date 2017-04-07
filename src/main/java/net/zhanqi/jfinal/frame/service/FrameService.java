package net.zhanqi.jfinal.frame.service;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.frame.core.LoginInfo;
import net.zhanqi.jfinal.frame.core.LoginInfoAuthenticationToken;
import net.zhanqi.jfinal.frame.model.SysDept;
import net.zhanqi.jfinal.frame.model.SysMenu;
import net.zhanqi.jfinal.frame.model.SysOper;
import net.zhanqi.jfinal.frame.model.SysRole;
import org.apache.shiro.SecurityUtils;

import java.util.List;
import java.util.Set;

public class FrameService {

    public static final FrameService me = Enhancer.enhance(FrameService.class);

    public void login(String username, String password, boolean rememberMe) {

        CheckUtils.checkBlank(username, "请输入帐号");
        CheckUtils.checkBlank(password, "请输入密码");

        SysOper oper = SysOper.me.findFirst("select * from sys_oper where username=?", username);

        // 帐号
        CheckUtils.checkNull(oper, "帐号【%s】不存在", username);

        // 密码
        String encryptPassword = SysOper.encryptPassword(password);
        CheckUtils.checkTrue(!encryptPassword.equals(oper.getPassword()), "密码错误");

        // 部门
        List<SysDept> deptList = SysDept.me.queryOperDeptList(oper.getId());

        // 部门检查
        CheckUtils.checkEmpty(deptList, "没有配置部门");

        // 登陆成功
        LoginInfo loginInfo = new LoginInfo(oper);
        loginInfo.setRememberMe(rememberMe);
        loginInfo.setDeptList(deptList);

        SecurityUtils.getSubject().login(new LoginInfoAuthenticationToken(loginInfo));

        if (loginInfo.getDeptList().size() == 1) {
            chgDept(loginInfo.getDeptList().get(0).getId(), loginInfo);
        }
    }

    public void chgDept(Long deptId, LoginInfo loginInfo) {
        CheckUtils.checkNull(deptId, "请选择部门");

        Long operId = loginInfo.getOperId();

        // 部门
        SysDept dept = SysDept.me.findById(deptId);

        // 部门检查
        CheckUtils.checkNull(dept, "部门不存在【%s】", deptId);

        // 检查权限
        String sql = "select count(1) from sys_oper_dept_role where operid=? and deptid=?";
        long count = Db.queryLong(sql, loginInfo.getOperId(), deptId);
        CheckUtils.checkTrue(count == 0, "没有配置部门角色");

        // 部门
        List<SysDept> deptList = SysDept.me.queryOperDeptList(operId);
        CheckUtils.checkEmpty(deptList, "用户没有配置部门");

        // 角色
        SysRole role = SysRole.me.queryOperDeptRole(operId, deptId);
        CheckUtils.checkNull(role, "用户在该部门下没有角色");

        Long roleId = role.getId();

        // 菜单
        List<SysMenu> menuList = SysMenu.me.queryTopRoleMenuList(roleId);
        CheckUtils.checkEmpty(menuList, "角色没有配置菜单");

        // 权限
        Set<String> permList = SysMenu.me.queryRoleMenuPermList(roleId);
        CheckUtils.checkEmpty(permList, "角色没有配置权限");

        // 授权
        loginInfo.getAuthorizationInfo().setStringPermissions(permList);

        // 更新登陆信息，放到最后
        loginInfo.setDeptId(deptId);
        loginInfo.setDept(dept);
        loginInfo.setRole(role);
        loginInfo.setRoleId(roleId);
        loginInfo.setDeptList(deptList);
        loginInfo.setMenuList(menuList);
    }

    public void chgPwd(String oldPwd, String newPwd, LoginInfo loginInfo) {

        CheckUtils.checkBlank(oldPwd, "请输入原密码");
        CheckUtils.checkBlank(newPwd, "请输入新密码");

        SysOper oper = loginInfo.getOper();

        // 密码验证
        String encryptPassword = SysOper.encryptPassword(oldPwd);
        CheckUtils.checkTrue(!encryptPassword.equals(oper.getPassword()), "原密码错误");

        // 更新密码
        oper.setPassword(SysOper.encryptPassword(newPwd));

        oper.update();
    }
}
