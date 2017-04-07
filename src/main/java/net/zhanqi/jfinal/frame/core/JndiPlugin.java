package net.zhanqi.jfinal.frame.core;

import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiPlugin implements IPlugin, IDataSourceProvider {

    private String name;
    private DataSource ds;

    public JndiPlugin(String name) {
        this.name = "java:comp/env/jdbc/" + name;
    }

    @Override
    public DataSource getDataSource() {
        return ds;
    }

    @Override
    public boolean start() {
        try {
            ds = (DataSource) new InitialContext().lookup(name);
            return true;
        } catch (NamingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean stop() {
        return true;
    }

}
