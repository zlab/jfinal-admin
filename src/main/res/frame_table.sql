
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `configid` bigint(20) NOT NULL AUTO_INCREMENT,
  `deptid` bigint(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `memo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`configid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `deptid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`deptid`),
  KEY `FK_enibh76gjhdd24o5ucm8yxisn` (`parentid`),
  CONSTRAINT `sys_dept_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `sys_dept` (`deptid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menuid` bigint(20) NOT NULL AUTO_INCREMENT,
  `bizcode` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `iconcls` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `uri` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `perm` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`menuid`),
  KEY `FK_hi0avud5o4xo0rfarhhb3h0sy` (`parentid`),
  CONSTRAINT `sys_menu_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `sys_menu` (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for sys_oper
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper`;
CREATE TABLE `sys_oper` (
  `operid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deptid` bigint(20) DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`operid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for sys_oper_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_dept_role`;
CREATE TABLE `sys_oper_dept_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deptid` bigint(20) DEFAULT NULL,
  `operid` bigint(20) DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `paramid` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gcode` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mcode` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`paramid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` bigint(20) NOT NULL AUTO_INCREMENT,
  `memo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Function structure for translate
-- ----------------------------
/*
DROP FUNCTION IF EXISTS `translate`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `translate`(v_gcode varchar(255), v_mcode varchar(255)) RETURNS varchar(255) CHARSET utf8 COLLATE utf8_bin
  begin
    declare v_mname varchar(255) default '';

    if v_gcode is null or v_gcode = '' then
      return '';
    end if;

    if v_mcode is null or v_mcode = '' then
      return '';
    end if;

    select mname into v_mname from sys_param where gcode=v_gcode and mcode=v_mcode;

    return v_mname;
  end
;;*/
