package net.zhanqi.jfinal.frame.service;

import com.jfinal.aop.Enhancer;
import net.zhanqi.jfinal.frame.model.SysParam;

import java.util.List;

public class ParamService {

    public static final ParamService me = Enhancer.enhance(ParamService.class);

    public List<SysParam> queryParamNodeList() {
        String sql = "select t.*, t.paramid id," +
                " (select paramid from sys_param where gcode='@ROOT' and mcode=t.gcode) parentid" +
                " from sys_param t" +
                " order by gcode, sort";

        return SysParam.me.find(sql);
    }

}
