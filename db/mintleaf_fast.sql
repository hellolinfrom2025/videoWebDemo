/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50717
Source Host           : 10.4.234.82:3306
Source Database       : mintleaf_fast

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-09 17:02:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for core_button
-- ----------------------------
DROP TABLE IF EXISTS `core_button`;
CREATE TABLE `core_button` (
  `id` int(200) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `menuid` int(48) DEFAULT NULL COMMENT '菜单id',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_button
-- ----------------------------
INSERT INTO `core_button` VALUES ('1135145', '新增', '3', 'auth:user:add');
INSERT INTO `core_button` VALUES ('1142456', '修改', '3', 'auth:user:edit');
INSERT INTO `core_button` VALUES ('3321456', '删除', '3', 'auth:user:delect');

-- ----------------------------
-- Table structure for core_logger
-- ----------------------------
DROP TABLE IF EXISTS `core_logger`;
CREATE TABLE `core_logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_ip` varchar(30) DEFAULT NULL COMMENT '客户端请求IP地址',
  `uri` varchar(100) DEFAULT NULL COMMENT '日志请求地址',
  `type` varchar(50) DEFAULT NULL COMMENT '终端请求方式,普通请求,ajax请求',
  `method` varchar(10) DEFAULT NULL COMMENT '请求方式method,post,get等',
  `param_data` longtext COMMENT '请求参数内容,json',
  `session_id` varchar(100) DEFAULT NULL COMMENT '请求接口唯一session标识',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '请求时间',
  `returm_time` varchar(50) DEFAULT NULL COMMENT '接口返回时间',
  `return_data` longtext COMMENT '接口返回数据json',
  `http_status_code` varchar(10) DEFAULT NULL COMMENT '请求时httpStatusCode代码，如：200,400,404等',
  `time_consuming` int(8) DEFAULT '0' COMMENT '请求耗时（毫秒单位）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=368749 DEFAULT CHARSET=utf8 COMMENT='请求日志信息表';

-- ----------------------------
-- Records of core_logger
-- ----------------------------

-- ----------------------------
-- Table structure for core_menu
-- ----------------------------
DROP TABLE IF EXISTS `core_menu`;
CREATE TABLE `core_menu` (
  `id` int(48) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `url` varchar(500) DEFAULT NULL COMMENT '网址',
  `icon` varchar(50) DEFAULT NULL COMMENT '显示的图标',
  `menutype` enum('0','1','2') DEFAULT '0' COMMENT '类型，0 菜单，1 连接网址,2 隐藏连接',
  `display` int(11) DEFAULT '1' COMMENT '显示排序',
  `parentid` int(48) DEFAULT '0' COMMENT '父级的id，引用本表id字段',
  `creator` int(11) DEFAULT '0' COMMENT '创建者id，0为超级管理员',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateuser` int(11) DEFAULT NULL COMMENT '更新者id',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `flag` enum('0','1') DEFAULT '1' COMMENT '是否启用，0 禁用，1启用',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_menu
-- ----------------------------
INSERT INTO `core_menu` VALUES ('2', '系统模块', '', 'fa-cog', '0', '1', '0', '0', '2017-03-31 20:16:43', '0', '2018-09-14 15:23:54', '1', 'null');
INSERT INTO `core_menu` VALUES ('3', '用户管理', '/coreUser/index.html', 'fa-user', '1', '2', '2', '0', '2017-03-31 20:16:54', '0', '2018-08-26 22:25:58', '1', 'auth:user:view');
INSERT INTO `core_menu` VALUES ('4', '角色管理', '/coreRole/index.html', 'fa-user-circle', '0', '2', '2', '0', '2017-03-31 20:16:48', '0', '2018-09-14 15:23:29', '1', 'auth:role:view');
INSERT INTO `core_menu` VALUES ('5', '菜单管理', '/coreMenu/index.html', 'fa-list', '0', '1', '2', '0', '2017-03-31 20:16:45', '0', '2018-09-14 15:23:43', '1', 'auth:menu:view');
INSERT INTO `core_menu` VALUES ('6', '首页', '/home.html', 'fa-home', '0', '1', '43', '0', '2018-08-21 08:07:46', null, '2018-10-09 10:57:05', '1', 'null');
INSERT INTO `core_menu` VALUES ('7', '视频模块', '/stshipinb/index.html', 'fa-video-camera', '0', '1', '0', '0', '2018-08-21 15:02:35', null, '2018-09-13 15:18:27', '1', 'null');
INSERT INTO `core_menu` VALUES ('31', '文件上传', '/upload/index.html', 'fa-cloud-upload', '0', '1', '2', '0', '2018-08-23 13:09:37', null, '2018-09-11 12:00:01', '1', 'auth:upload:view');
INSERT INTO `core_menu` VALUES ('32', '调试功能', '', 'fa-bug', '0', '1', '43', '0', '2018-08-23 13:14:10', null, '2018-10-09 09:13:54', '1', 'null');
INSERT INTO `core_menu` VALUES ('33', '实时监控', '/stshipinb/index.html', 'layui-icon-console', '0', '1', '7', '0', '2018-08-23 13:14:39', null, '2018-09-14 15:26:07', '1', 'null');
INSERT INTO `core_menu` VALUES ('34', '数据维护', '/stshipinb/list.html', 'layui-icon-table', '0', '1', '7', '0', '2018-08-23 13:16:40', null, '2018-09-14 15:26:13', '1', 'null');
INSERT INTO `core_menu` VALUES ('36', 'Druid监控', '/druid/index.html', 'fa-line-chart', '0', '1', '2', '0', '2018-08-25 15:05:34', null, '2018-10-01 00:23:08', '1', 'null');
INSERT INTO `core_menu` VALUES ('37', '代码生成', '/gen/index.html', 'fa-code', '0', '1', '2', '0', '2018-09-05 19:44:14', null, '2018-09-14 15:23:00', '1', 'auth:gen:view');
INSERT INTO `core_menu` VALUES ('42', '图像识别', 'plugins/tracking/index.html', 'layui-icon-face-smile', '0', '1', '7', '0', '2018-09-13 15:11:35', null, '2018-09-25 10:17:54', '1', 'null');
INSERT INTO `core_menu` VALUES ('43', '应用模块', '', 'layui-icon-app', '0', '1', '0', '0', '2018-09-13 15:14:22', null, '2018-09-13 15:27:55', '1', 'null');
INSERT INTO `core_menu` VALUES ('44', '404页面', '/404.html', 'layui-icon-404', '0', '1', '32', '0', '2018-09-14 15:27:29', null, null, '1', 'null');
INSERT INTO `core_menu` VALUES ('45', '图片管理', '/img/index.html', 'layui-icon-picture', '0', '1', '43', '0', '2018-09-25 09:57:33', null, '2018-09-25 11:36:37', '1', 'null');
INSERT INTO `core_menu` VALUES ('46', '403页面', '/403.html', 'layui-icon-face-surprised', '0', '1', '32', '0', '2018-09-25 11:22:21', null, '2018-09-25 11:42:33', '1', 'null');
INSERT INTO `core_menu` VALUES ('47', 'API管理', '/swagger-ui.html', 'layui-icon-read', '0', '1', '2', '0', '2018-09-28 10:36:22', null, '2018-09-28 10:53:14', '1', 'null');
INSERT INTO `core_menu` VALUES ('48', '系统日志', '/coreLogger/index.html', 'layui-icon-form', '0', '1', '2', '0', '2018-10-01 00:05:28', null, '2018-10-08 08:32:05', '1', 'null');
INSERT INTO `core_menu` VALUES ('49', '组织管理', '/debugfun/treegrid.html', 'layui-icon-user', '0', '1', '2', '0', '2018-10-08 10:36:03', null, '2018-10-09 09:09:43', '1', 'null');
INSERT INTO `core_menu` VALUES ('50', '树形数据表格', '/debugfun/treegrid.html', 'layui-icon-table', '0', '1', '32', '0', '2018-10-09 08:50:31', null, '2018-10-09 09:11:01', '1', 'null');
INSERT INTO `core_menu` VALUES ('51', '树形下拉选择器', '/debugfun/treeselect.html', 'layui-icon-tree', '0', '1', '32', '0', '2018-10-09 08:57:55', null, null, '1', 'null');
INSERT INTO `core_menu` VALUES ('52', '了解MintLeaf-Fast', '/home.html', 'layui-icon-tips', '0', '1', '6', '0', '2018-10-09 10:42:47', null, null, '1', 'null');

-- ----------------------------
-- Table structure for core_role
-- ----------------------------
DROP TABLE IF EXISTS `core_role`;
CREATE TABLE `core_role` (
  `id` int(48) NOT NULL AUTO_INCREMENT COMMENT '角色表主键',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` int(11) DEFAULT '0' COMMENT '用户id，0为角色，有关联时则为用户的单独权限',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `updateuser` int(48) DEFAULT NULL COMMENT '更新者id',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role
-- ----------------------------
INSERT INTO `core_role` VALUES ('1', '超级用户', '2018-03-05 23:00:43', '0', '拥有所有菜单权限', '-1', '2018-08-26 10:07:52');
INSERT INTO `core_role` VALUES ('2', '普通用户', '2018-08-20 11:46:45', '0', '拥有部分菜单权限', '-1', '2018-09-06 18:41:49');

-- ----------------------------
-- Table structure for core_role_button
-- ----------------------------
DROP TABLE IF EXISTS `core_role_button`;
CREATE TABLE `core_role_button` (
  `roleid` int(48) NOT NULL COMMENT '角色id',
  `buttonid` int(200) NOT NULL COMMENT '按钮id',
  `flag` int(48) DEFAULT '1' COMMENT '1为有权限，0为没有权限（防止以后会出现角色有权限但是个人没有权限的情况）',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`roleid`,`buttonid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role_button
-- ----------------------------
INSERT INTO `core_role_button` VALUES ('1', '1135145', '1', '2018-10-09 10:43:20');
INSERT INTO `core_role_button` VALUES ('1', '1142456', '1', '2018-10-09 10:43:20');
INSERT INTO `core_role_button` VALUES ('1', '3321456', '1', '2018-10-09 10:43:20');
INSERT INTO `core_role_button` VALUES ('2', '1135145', '1', '2018-09-29 17:15:38');

-- ----------------------------
-- Table structure for core_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `core_role_menu`;
CREATE TABLE `core_role_menu` (
  `roleid` int(48) NOT NULL COMMENT '角色id',
  `menuid` int(48) NOT NULL COMMENT '菜单id',
  `flag` int(1) DEFAULT '1' COMMENT '1为有权限，0为没有权限（防止以后会出现角色有权限但是个人没有权限的情况）',
  `creator` int(48) DEFAULT NULL COMMENT '创建人，0为初始化',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`menuid`,`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role_menu
-- ----------------------------
INSERT INTO `core_role_menu` VALUES ('5', '1', '1', null, '2018-08-25 12:52:26');
INSERT INTO `core_role_menu` VALUES ('1', '2', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '2', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '3', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '3', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '4', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '4', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '5', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '5', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '6', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '6', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('5', '6', '1', null, '2018-08-25 12:52:26');
INSERT INTO `core_role_menu` VALUES ('1', '7', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('5', '7', '1', null, '2018-08-25 12:52:26');
INSERT INTO `core_role_menu` VALUES ('1', '31', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('5', '31', '1', null, '2018-08-25 12:52:26');
INSERT INTO `core_role_menu` VALUES ('1', '32', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '32', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('5', '32', '1', null, '2018-08-25 12:52:26');
INSERT INTO `core_role_menu` VALUES ('1', '33', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('5', '33', '1', null, '2018-08-25 12:52:26');
INSERT INTO `core_role_menu` VALUES ('1', '34', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('5', '34', '1', null, '2018-08-25 12:52:26');
INSERT INTO `core_role_menu` VALUES ('1', '36', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '36', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '37', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('1', '42', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('1', '43', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '43', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '44', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '44', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '45', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '45', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '46', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('2', '46', '1', null, '2018-09-29 17:15:38');
INSERT INTO `core_role_menu` VALUES ('1', '47', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('1', '48', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('1', '49', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('1', '50', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('1', '51', '1', null, '2018-10-09 10:43:20');
INSERT INTO `core_role_menu` VALUES ('1', '52', '1', null, '2018-10-09 10:43:20');

-- ----------------------------
-- Table structure for core_user
-- ----------------------------
DROP TABLE IF EXISTS `core_user`;
CREATE TABLE `core_user` (
  `id` int(48) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `tenantid` int(11) DEFAULT NULL COMMENT '租户id，0为系统用户',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `psw` varchar(32) DEFAULT NULL COMMENT '用户密码MD5加密',
  `email` varchar(32) DEFAULT NULL COMMENT '用户邮箱',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，0为初始化',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `flag` int(1) DEFAULT '1' COMMENT '用户状态，1启用，0禁用',
  `logintime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `updateuser` int(11) DEFAULT NULL COMMENT '更新者id',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_user
-- ----------------------------
INSERT INTO `core_user` VALUES ('-1', '0', 'root', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin@raye.wang', '0', '2018-09-28 15:07:39', '1', '2017-04-07 22:23:15', '-1', '2018-09-28 15:21:01');
INSERT INTO `core_user` VALUES ('26', null, 'zhangmengchu', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, '2018-09-29 17:13:37', '1', '2018-09-29 17:13:08', null, '2018-09-29 17:27:01');

-- ----------------------------
-- Table structure for core_user_role
-- ----------------------------
DROP TABLE IF EXISTS `core_user_role`;
CREATE TABLE `core_user_role` (
  `userid` int(48) NOT NULL COMMENT '用户id',
  `roleid` int(48) NOT NULL COMMENT '角色id',
  `creator` int(48) DEFAULT NULL COMMENT '创建人，0为初始化',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_user_role
-- ----------------------------
INSERT INTO `core_user_role` VALUES ('-1', '1', null, '2018-09-28 15:07:39');
INSERT INTO `core_user_role` VALUES ('-1', '2', null, '2018-09-28 15:07:39');
INSERT INTO `core_user_role` VALUES ('26', '2', null, '2018-09-29 17:13:37');

-- ----------------------------
-- Procedure structure for delete_menu
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete_menu`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `delete_menu`(IN `menuid` int)
BEGIN

	DECLARE rowNUM INT DEFAULT 0;
	create temporary table if not exists menu_del_temp -- 不存在则创建临时表
  (
     id INT
  );
	create temporary table if not exists menu_del_temp2 -- 不存在则创建临时表
  (
     id INT
  );
create temporary table if not exists menu_del_temp3 -- 不存在则创建临时表
  (
     id INT
  );
	TRUNCATE TABLE menu_del_temp2;
	TRUNCATE TABLE menu_del_temp; -- 清空临时表
		INSERT INTO menu_del_temp SELECT id FROM  menu where parent_id=menuid;
	-- DELETE FROM category WHERE ID IN (SELECT id FROM category_del_temp);
	INSERT INTO menu_del_temp2 SELECT id FROM  menu where parent_id IN (SELECT id FROM menu_del_temp);
	SELECT COUNT(id) INTO rowNUM FROM menu_del_temp2;
	WHILE rowNUM > 0 DO
		INSERT INTO menu_del_temp SELECT id FROM menu_del_temp2;
		TRUNCATE TABLE menu_del_temp3;
		INSERT INTO menu_del_temp3 SELECT id FROM menu_del_temp2;
		TRUNCATE TABLE menu_del_temp2;
		INSERT INTO menu_del_temp2 SELECT id FROM  menu where parent_id IN (SELECT id FROM menu_del_temp3);
		SELECT COUNT(id) INTO rowNUM FROM menu_del_temp2;
	END WHILE;
	INSERT INTO menu_del_temp(id) values(menuid);
	DELETE FROM menu WHERE id IN (SELECT id FROM menu_del_temp);
	DELETE FROM role_menu WHERE menuid IN (SELECT id FROM menu_del_temp);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for role_menu_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `role_menu_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `role_menu_update`(IN menuids varchar(3000),IN i_roleid INT,IN userid INT)
BEGIN

-- 拆分结果

DECLARE cnt INT DEFAULT 0;

DECLARE i INT DEFAULT 0;

SET cnt = func_split_TotalLength(menuids,',');
DELETE FROM role_menu WHERE roleid = i_roleid;

WHILE i < cnt

DO

    SET i = i + 1;

    INSERT INTO role_menu(roleid,menuid,creator) VALUES (i_roleid,func_split(menuids,',',i),userid);

END WHILE;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for user_role_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `user_role_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `user_role_update`(IN roleids varchar(3000),IN i_userid INT,IN i_creator INT)
BEGIN

-- 拆分结果

DECLARE cnt INT DEFAULT 0;

DECLARE i INT DEFAULT 0;

SET cnt = func_split_TotalLength(roleids,',');
DELETE FROM user_role WHERE userid = i_userid;

WHILE i < cnt

DO

    SET i = i + 1;

    INSERT INTO user_role(userid,roleid,creator) VALUES (i_userid,func_split(roleids,',',i),i_creator);

END WHILE;

END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for func_split
-- ----------------------------
DROP FUNCTION IF EXISTS `func_split`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `func_split`(f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8
BEGIN
        declare result varchar(255) default '';

        set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));

        return result;

END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for func_split_TotalLength
-- ----------------------------
DROP FUNCTION IF EXISTS `func_split_TotalLength`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `func_split_TotalLength`(f_string varchar(1000),f_delimiter varchar(5)) RETURNS int(11)
BEGIN

    return 1+(length(f_string) - length(replace(f_string,f_delimiter,'')));

END
;;
DELIMITER ;
