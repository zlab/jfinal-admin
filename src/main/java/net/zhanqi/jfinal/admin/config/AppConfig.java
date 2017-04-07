package net.zhanqi.jfinal.admin.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.FreeMarkerRender;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import net.zhanqi.jfinal.admin.controller.component.TreeController;
import net.zhanqi.jfinal.ext.core.FlagInterceptor;
import net.zhanqi.jfinal.ext.core.JSessionIdCleanHandler;
import net.zhanqi.jfinal.ext.core.Slf4jLoggerFactory;
import net.zhanqi.jfinal.frame.controller.*;
import net.zhanqi.jfinal.frame.core.ConfigInterceptor;
import net.zhanqi.jfinal.frame.core.ParamInterceptor;
import net.zhanqi.jfinal.frame.model.*;

/**
 * API引导式配置
 */
public class AppConfig extends JFinalConfig {

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        loadPropertyFile("config.properties");

        me.setDevMode(getPropertyToBoolean("devMode", false));
        me.setBaseViewPath(getProperty("baseViewPath"));

        me.setFreeMarkerViewExtension(".ftl");
        me.setUploadedFileSaveDirectory(getProperty("uploadDir"));
        me.setLoggerFactory(new Slf4jLoggerFactory());

        me.setError401View("/common/401.ftl");
        me.setError403View("/common/403.ftl");
        me.setError404View("/common/404.ftl");
        me.setError500View("/common/500.ftl");
        me.setErrorView(400, "/common/400.ftl");

        try {
            Configuration config = FreeMarkerRender.getConfiguration();

            config.setSharedVariable("ctx", JFinal.me().getContextPath());

        } catch (TemplateModelException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", FrameController.class, "/sys/frame");

        me.add("/sys/frame", FrameController.class);
        me.add("/sys/dept", DeptController.class);
        me.add("/sys/oper", OperController.class);
        me.add("/sys/menu", MenuController.class);
        me.add("/sys/role", RoleController.class);
        me.add("/sys/param", ParamController.class);
        me.add("/sys/config", ConfigController.class);

        me.add("/component/tree", TreeController.class, "/component");
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("url"), getProperty("username"),
                getProperty("password"));
        me.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        me.add(arp);

        arp.addMapping("sys_oper", "operid", SysOper.class);
        arp.addMapping("sys_oper_dept_role", SysOperDeptRole.class);
        arp.addMapping("sys_dept", "deptid", SysDept.class);
        arp.addMapping("sys_menu", "menuid", SysMenu.class);
        arp.addMapping("sys_role", "roleid", SysRole.class);
        arp.addMapping("sys_role_menu", SysRoleMenu.class);
        arp.addMapping("sys_param", "paramid", SysParam.class);
        arp.addMapping("sys_config", "configid", SysConfig.class);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
        // 控制层
        me.addGlobalActionInterceptor(new ConfigInterceptor());
        me.addGlobalActionInterceptor(new ParamInterceptor());
        me.addGlobalActionInterceptor(new FlagInterceptor());

        // 业务层
        me.addGlobalServiceInterceptor(new Tx());
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new JSessionIdCleanHandler());
        me.add(new UrlSkipHandler(".+\\.\\w{1,4}", false));
    }
}
