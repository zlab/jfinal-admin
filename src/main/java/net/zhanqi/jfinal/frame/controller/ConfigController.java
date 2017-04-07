package net.zhanqi.jfinal.frame.controller;

import net.zhanqi.jfinal.frame.core.BaseController;
import net.zhanqi.jfinal.frame.model.SysConfig;
import net.zhanqi.jfinal.frame.service.ConfigService;

public class ConfigController extends BaseController {

    public void main() {
        render("configMain.ftl");
    }

    /**
     * 配置树节点
     */
    public void queryConfigNodeList() {
        renderJson(ConfigService.me.queryConfigNodeList());
    }

    /**
     * 配置录入
     */
    public void input() {
        if (getId() != null) {
            setAttr("config", ConfigService.me.queryConfig(getId()));
        }

        setAttr("parent", ConfigService.me.queryConfig(getParaToLong("parentid")));

        render("configInput.ftl");
    }

    /**
     * 保存配置
     */
    public void saveConfig() {
        SysConfig config = getModel(SysConfig.class, "config");
        ConfigService.me.saveConfig(config);

        config.put("parentid", getParaToLong("parentid"));

        renderJson(config);
    }

    /**
     * 删除配置
     */
    public void deleteConfig() {
        ConfigService.me.deleteConfig(getId());
        renderJson(true);
    }

}
