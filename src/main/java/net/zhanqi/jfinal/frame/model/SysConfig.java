package net.zhanqi.jfinal.frame.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务参数
 *
 * @author zhanqi
 * @since 2013-10-08
 */
public class SysConfig extends Model<SysConfig> {

    public static final SysConfig me = new SysConfig();

    public Long getId() {
        return get("configid");
    }

    public String getName() {
        return get("name");
    }

    public Long getDeptId() {
        return get("deptid");
    }

    public String getValue() {
        return get("value");
    }

    public Map<String, String> queryConfigMap() {
        String sql = "select * from sys_config where deptid is null and name<>'@ROOT'";
        List<SysConfig> configList = find(sql);

        Map<String, String> configMap = new HashMap<String, String>();
        for (SysConfig config : configList) {
            configMap.put(config.getName(), config.getValue());
        }

        return configMap;
    }
}
