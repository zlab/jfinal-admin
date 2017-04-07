package net.zhanqi.jfinal.frame.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * 系统参数
 *
 * @author zhanqi
 * @since 2013-03-25
 */
public class SysParam extends Model<SysParam> {

    public static final SysParam me = new SysParam();

    public Long getId() {
        return get("paramid");
    }

    public Long getParentId() {
        return get("parentid");
    }

    public String getGcode() {
        return get("gcode");
    }

    public String getMcode() {
        return get("mcode");
    }

    public String getMname() {
        return get("mname");
    }

    public Integer getSort() {
        return getInt("sort");
    }

    public List<Record> queryParamList() {
        String sql = "select * from sys_param where gcode<>'@ROOT' order by sort";
        return Db.find(sql);
    }

}
