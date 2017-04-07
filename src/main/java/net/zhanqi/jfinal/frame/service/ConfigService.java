package net.zhanqi.jfinal.frame.service;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import net.zhanqi.jfinal.ext.util.CheckUtils;
import net.zhanqi.jfinal.frame.model.SysConfig;

import java.util.List;

public class ConfigService {

    public static final ConfigService me = Enhancer.enhance(ConfigService.class);

    public List<SysConfig> queryConfigNodeList() {
        String sql = "select t.*, t.configid id," +
                " (select configid from sys_config where name='@ROOT' and value=t.name) parentid," +
                " (select name from sys_dept where deptid=t.deptid) deptname" +
                " from sys_config t";
        return SysConfig.me.find(sql);
    }

    public SysConfig queryConfig(Long configId) {
        CheckUtils.checkNull(configId, "配置ID不能为空");

        String sql = "select t.*, t.configid id," +
                " (select name from sys_dept where deptid=t.deptid) deptname" +
                " from sys_config t where configid=?";

        return SysConfig.me.findFirst(sql, configId);
    }

    public void saveConfig(SysConfig config) {
        CheckUtils.checkBlank(config.getName(), "配置名称不能为空");
        CheckUtils.checkBlank(config.getValue(), "配置值不能为空");
        CheckUtils.checkTrue("@ROOT".equals(config.getName()), "配置名称不能是@ROOT");

        if (config.getId() == null) {
            if (config.getDeptId() == null) {
                String sql = "select count(1) from sys_config where name=? and deptid is null";
                CheckUtils.checkTrue(Db.queryLong(sql, config.getName()) > 0, "只能有一个默认配置");
            }

            config.save();
        } else {
            config.update();

            // 回滚
            String sql = "select count(1) from sys_config where name=? and deptid is null";
            CheckUtils.checkTrue(Db.queryLong(sql, config.getName()) == 0, "必须保留默认配置");

            sql = "select count(1) from sys_config where name=? and deptid is null";
            CheckUtils.checkTrue(Db.queryLong(sql, config.getName()) > 1, "只能有一个默认配置");
        }
    }

    public void deleteConfig(Long configId) {
        CheckUtils.checkNull(configId, "配置ID不能为空");

        SysConfig config = SysConfig.me.findById(configId);
        CheckUtils.checkNull(config, "配置不存在");

        CheckUtils.checkTrue("@ROOT".equals(config.getName()), "不能删除配置");

        config.delete();

        // 回滚
        String sql = "select count(1) from sys_config where name=? and deptid is null";
        CheckUtils.checkTrue(Db.queryLong(sql, config.getName()) == 0, "必须保留默认配置");
    }
}
