package net.zhanqi.jfinal.frame.core;

import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.frame.model.SysDept;
import net.zhanqi.jfinal.frame.model.SysMenu;
import net.zhanqi.jfinal.frame.model.SysOper;
import net.zhanqi.jfinal.frame.model.SysRole;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.io.Serializable;
import java.util.List;

public class LoginInfo implements Serializable {

    private SimpleAuthorizationInfo authorizationInfo;

    private boolean rememberMe;

    private Long deptId;
    private Long operId;
    private Long roleId;

    private SysOper oper;
    private SysDept dept;
    private SysRole role;

    private List<SysMenu> menuList;
    private List<SysRole> roleList;
    private List<SysDept> deptList;

    public LoginInfo(SysOper oper) {
        CheckUtils.checkNull(oper, "登录用户不能为空");
        CheckUtils.checkNull(oper.getId(), "登录用户ID不能为空");

        this.oper = oper;
        this.operId = oper.getId();

        this.role = new SysRole();
        this.dept = new SysDept();

        this.authorizationInfo = new SimpleAuthorizationInfo();
    }

    public SysOper getOper() {
        return oper;
    }

    public void setOper(SysOper oper) {
        this.oper = oper;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getOperId() {
        return operId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<SysDept> deptList) {
        this.deptList = deptList;
    }

    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }

    public SimpleAuthorizationInfo getAuthorizationInfo() {
        return authorizationInfo;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
