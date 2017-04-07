-- 用户
TRUNCATE sys_oper;
INSERT INTO sys_oper (operid, username, password, name, gender)
VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员', '1');

-- 部门
TRUNCATE sys_dept;
INSERT INTO sys_dept (deptid, name) VALUES (11, '湖南分公司');
INSERT INTO sys_dept (deptid, name, parentid) VALUES (111, '长沙分公司', 11);
INSERT INTO sys_dept (deptid, name, parentid) VALUES (1111, '长沙开发部', 111);
INSERT INTO sys_dept (deptid, name, parentid) VALUES (1112, '长沙财务部', 111);
INSERT INTO sys_dept (deptid, name, parentid) VALUES (1113, '长沙系统管理部', 111);
INSERT INTO sys_dept (deptid, name, parentid) VALUES (1114, '长沙业务部', 111);

INSERT INTO sys_dept (deptid, name, parentid) VALUES (112, '衡阳分公司', 11);
INSERT INTO sys_dept (deptid, name, parentid) VALUES (1121, '常宁分公司', 112);

INSERT INTO sys_dept (deptid, name) VALUES (12, '北京分公司');
INSERT INTO sys_dept (deptid, name, parentid) VALUES (121, '北京总部', 12);

-- 角色
TRUNCATE sys_role;
INSERT INTO sys_role (roleid, name) VALUES (1, '系统管理员');
INSERT INTO sys_role (roleid, name) VALUES (2, '配置管理员');
INSERT INTO sys_role (roleid, name) VALUES (3, '微信管理员');

-- 用户部门角色
TRUNCATE sys_oper_dept_role;
INSERT INTO sys_oper_dept_role (operid, deptid, roleid) VALUES (1, 1111, 1);

-- 菜单
TRUNCATE sys_menu;
INSERT INTO sys_menu (menuid, name) VALUES (1, '系统管理');
INSERT INTO sys_menu (menuid, parentid, name, uri, perm) VALUES (101, 1, '菜单管理', '/sys/menu/main/', 'menu');
INSERT INTO sys_menu (menuid, parentid, name, uri, perm) VALUES (102, 1, '用户管理', '/sys/oper/main/', 'oper');
INSERT INTO sys_menu (menuid, parentid, name, uri, perm) VALUES (103, 1, '角色管理', '/sys/role/main/', 'role');
INSERT INTO sys_menu (menuid, parentid, name, uri, perm) VALUES (104, 1, '部门管理', '/sys/dept/main/', 'dept');
INSERT INTO sys_menu (menuid, parentid, name, uri, perm) VALUES (105, 1, '参数管理', '/sys/param/main/', 'param');

INSERT INTO sys_menu (menuid, name) VALUES (2, '参数配置');
INSERT INTO sys_menu (menuid, parentid, name, uri, perm) VALUES (201, 2, '配置管理', '/sys/config/main/', 'config');

INSERT INTO sys_menu (menuid, name) VALUES (3, '微信管理');
INSERT INTO sys_menu (menuid, parentid, name, uri, perm) VALUES (301, 3, '微信管理', '/sys/config/main/', 'config');

INSERT INTO sys_menu (menuid, name) VALUES (4, '业务处理');

INSERT INTO sys_menu (menuid, name) VALUES (5, '财务管理');

INSERT INTO sys_menu (menuid, name) VALUES (6, '查询统计');

-- 角色菜单
TRUNCATE sys_role_menu;
INSERT INTO sys_role_menu (roleid, menuid) VALUES (1, 101);
INSERT INTO sys_role_menu (roleid, menuid) VALUES (1, 102);
INSERT INTO sys_role_menu (roleid, menuid) VALUES (1, 103);
INSERT INTO sys_role_menu (roleid, menuid) VALUES (1, 104);
INSERT INTO sys_role_menu (roleid, menuid) VALUES (1, 105);
INSERT INTO sys_role_menu (roleid, menuid) VALUES (1, 201);

-- 系统参数
TRUNCATE sys_param;
INSERT INTO sys_param (paramid, gcode, mcode, mname) VALUES (1, '@ROOT', 'SYS_GENDER', '性别');
INSERT INTO sys_param (paramid, gcode, mcode, mname) VALUES (2, 'SYS_GENDER', '1', '男');
INSERT INTO sys_param (paramid, gcode, mcode, mname) VALUES (3, 'SYS_GENDER', '2', '女');

-- 业务参数
TRUNCATE sys_config;
INSERT INTO sys_config (name, value, memo) VALUES ('@ROOT', 'OPER_DEFAULT_PWD', '密码重置');
INSERT INTO sys_config (name, value, memo) VALUES ('OPER_DEFAULT_PWD', '123456', '所有部门');
INSERT INTO sys_config (name, value, deptid, memo) VALUES ('OPER_DEFAULT_PWD', '123456', 1, '总公司');

INSERT INTO sys_config (name, value, memo) VALUES ('@ROOT', 'APP_NAME', '系统名称');
INSERT INTO sys_config (name, value, memo) VALUES ('APP_NAME', '开发测试系统', '所有部门');

INSERT INTO sys_config (name, value, memo) VALUES ('@ROOT', 'COMPANY_NAME', '公司名称');
INSERT INTO sys_config (name, value, memo) VALUES ('COMPANY_NAME', '长沙软件', '所有部门');