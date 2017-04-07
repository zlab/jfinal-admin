package net.zhanqi.jfinal.frame.controller;

import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.frame.core.BaseController;
import net.zhanqi.jfinal.frame.service.FrameService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;

public class FrameController extends BaseController {

    /**
     * 首页
     */
    public void index() {
        setAttr("loginInfo", getLoginInfo());
        render("workspace.ftl");
    }

    /**
     * 登录
     */
    public void login() {
        if (isPost()) {
            String username = getPara("username");
            String password = getPara("password");
            boolean rememberMe = getParaToBoolean("rememberMe", false);
            FrameService.me.login(username, password, rememberMe);
            renderJson(true);
        } else {
            try {
                // 先注销
                SecurityUtils.getSubject().logout();
            } catch (SessionException e) {
                e.printStackTrace();
            }

            render("login.ftl");
        }
    }

    /**
     * 选择部门
     */
    public void chgDept() {
        if (isPost()) {
            Long deptId = getParaToLong("deptId");
            FrameService.me.chgDept(deptId, getLoginInfo());
            renderJson(true);
        } else {
            setAttr("loginInfo", getLoginInfo());
            render("chgDept.ftl");
        }
    }

    /**
     * 用户首页
     */
    public void dashboard() {
        setAttr("loginInfo", getLoginInfo());
        render("dashboard.ftl");
    }

    /**
     * 修改密码
     */
    public void chgPwd() {
        if (getPara() == null) {
            setAttr("loginInfo", getLoginInfo());
            render("chgPwd.ftl");
        } else {
            String oldPwd = getPara("oldPwd");
            String newPwd = getPara("newPwd");
            String confirmPwd = getPara("confirmPwd");

            CheckUtils.checkBlank(oldPwd, "请输入原密码");
            CheckUtils.checkBlank(newPwd, "请输入新密码");
            CheckUtils.checkBlank(confirmPwd, "请输入确认密码");
            CheckUtils.checkTrue(!newPwd.equals(confirmPwd), "确认密码输入不一致");

            FrameService.me.chgPwd(oldPwd, newPwd, getLoginInfo());

            renderJson(true);
        }
    }
}
