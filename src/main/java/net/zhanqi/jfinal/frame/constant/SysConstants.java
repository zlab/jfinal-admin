package net.zhanqi.jfinal.frame.constant;

public interface SysConstants {

    interface SysYesNo {
        String YES = "Y";
        String NO = "N";
    }

    interface SysOptype {
        String INSERT = "I";
        String UPDATE = "U";
        String DELETE = "D";
    }

    interface SysFlag {
        String ADD = "ADD";
        String EDIT = "EDIT";
        String VIEW = "VIEW";
    }

    interface ParamType {
        /**
         * 静态参数
         */
        String STATIC = "@STATIC";

        /**
         * 动态参数
         */
        String DYNAMIC = "@DYNAMIC";

        /**
         * 动态参数类型
         */
        String DYNAMIC_TYPE = "DYNAMIC_TYPE";
    }

    interface Gcode {
        String BIZ_CONSIGN_STATUS = "BIZ_CONSIGN_STATUS";

        String SYS_DEPT = "SYS_DEPT";

        String BIZ_CODE = "BIZ_CODE";
        String BIZ_PRINT_CODE = "BIZ_PRINT_CODE";

        String RES_REGION = "RES_REGION";
        String RES_PRINTER = "RES_PRINTER";
    }

    interface BizCode {
        /**
         * 修改密码
         */
        String SYS_CHG_PWD = "SYS_CHG_PWD";
    }

    interface RuleType {
        String ROOT = "@ROOT";
    }
}
