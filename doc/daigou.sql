/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : daigou

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-02-09 18:06:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dg_goods
-- ----------------------------
DROP TABLE IF EXISTS `dg_goods`;
CREATE TABLE `dg_goods` (
  `uuid` varchar(32) NOT NULL,
  `type` int(10) DEFAULT NULL COMMENT '商品类型',
  `name` varchar(32) DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '售价',
  `bid` decimal(10,2) DEFAULT NULL COMMENT '进价',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1：正常 2：删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of dg_goods
-- ----------------------------

-- ----------------------------
-- Table structure for dg_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `dg_goods_type`;
CREATE TABLE `dg_goods_type` (
  `type` int(10) NOT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Records of dg_goods_type
-- ----------------------------

-- ----------------------------
-- Table structure for dg_order
-- ----------------------------
DROP TABLE IF EXISTS `dg_order`;
CREATE TABLE `dg_order` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `user_uuid` varchar(32) DEFAULT NULL COMMENT '用户id',
  `goods_uuid` varchar(32) DEFAULT NULL COMMENT '商品uuid',
  `quantity` int(10) DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `op_uuid` varchar(32) DEFAULT NULL COMMENT '操作员uuid',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1：下单 2：完成 0删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `done_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of dg_order
-- ----------------------------

-- ----------------------------
-- Table structure for dg_user
-- ----------------------------
DROP TABLE IF EXISTS `dg_user`;
CREATE TABLE `dg_user` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信号',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1：正常 2：删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

-- ----------------------------
-- Records of dg_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_operator
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator`;
CREATE TABLE `sys_operator` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(40) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(64) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1：正常 2：删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_operator
-- ----------------------------
INSERT INTO `sys_operator` VALUES ('54c294528b5041ff838380676a2ed643', 'admin', '7abbc4d092252eb23a61f40f0656e2211fe3b958e65b1c0f', 'zcj', '13000000000', '2017-02-08 16:36:49', '0', null);
