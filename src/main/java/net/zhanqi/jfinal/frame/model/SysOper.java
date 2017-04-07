package net.zhanqi.jfinal.frame.model;

import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * 操作员
 *
 * @author zhanqi
 * @since 2013-05-21
 */
public class SysOper extends Model<SysOper> {

    public static final SysOper me = new SysOper();

    public Long getId() {
        return get("operid");
    }

    public String getPassword() {
        return get("password");
    }

    public String getUsername() {
        return get("username");
    }

    public String getStatus() {
        return get("status");
    }

    public String getName() {
        return get("name");
    }

    public String getGender() {
        return get("gender");
    }

    public Integer getAge() {
        return get("age");
    }

    public String getPhone() {
        return get("phone");
    }

    public Date getCreateTime() {
        return get("createtime");
    }

    public Long getDeptId() {
        return get("deptid");
    }

    public SysOper setPassword(String password) {
        return set("password", password);
    }

    /**
     * 密码加密策略
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return HashKit.md5(password);
    }
}
