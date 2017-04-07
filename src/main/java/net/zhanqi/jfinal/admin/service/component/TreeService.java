package net.zhanqi.jfinal.admin.service.component;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class TreeService {

    public static final TreeService me = Enhancer.enhance(TreeService.class);

    public List<Record> queryDeptNodeList() {
        String sql = "select t.*, t.deptid id from sys_dept t";
        return Db.find(sql);
    }



}
