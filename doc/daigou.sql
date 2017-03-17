/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : daigou

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-03-17 16:06:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dg_goods
-- ----------------------------
DROP TABLE IF EXISTS `dg_goods`;
CREATE TABLE `dg_goods` (
  `uuid` varchar(32) NOT NULL,
  `type_uuid` varchar(32) DEFAULT '0' COMMENT '商品类型',
  `name` varchar(32) DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '售价',
  `bid` decimal(10,2) DEFAULT '0.00' COMMENT '进价',
  `counter` decimal(10,2) DEFAULT '0.00' COMMENT '专柜价',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 1：正常 0：删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of dg_goods
-- ----------------------------
INSERT INTO `dg_goods` VALUES ('007da1178b0341f6bbf0c7b2806ce9e', '2726a734414a40a392cb1837e91d8799', '爱上啊', '150.55', '120.55', '110.01', '2017-02-15 17:17:21', '2017-02-23 15:52:55', '1', '');
INSERT INTO `dg_goods` VALUES ('9f3724bf5a7140a983aba8dbffb7a005', '3e6ea40386c644efb3f4c1fad2c36f84', '大保健', '111.00', '110.00', '222.00', '2017-02-23 15:54:29', null, '0', '一把大保健');
INSERT INTO `dg_goods` VALUES ('cdae6a94295044e283f8cac3cb4dc252', '6aaebf878145409fad81ecc02a63e8c7', 'bb爽', '212.00', '180.00', '300.00', '2017-03-14 11:19:13', null, '1', 'bbbb');

-- ----------------------------
-- Table structure for dg_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `dg_goods_type`;
CREATE TABLE `dg_goods_type` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 1：正常 0：删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Records of dg_goods_type
-- ----------------------------
INSERT INTO `dg_goods_type` VALUES ('1212', 'asd', '0', '2017-02-20 10:09:59', null);
INSERT INTO `dg_goods_type` VALUES ('22', '包12', '0', '2017-02-20 10:09:59', '2017-02-20 11:37:25');
INSERT INTO `dg_goods_type` VALUES ('233', '化妆品1', '0', '2017-02-20 10:09:59', null);
INSERT INTO `dg_goods_type` VALUES ('2726a734414a40a392cb1837e91d8799', '衣服', '1', '2017-02-23 15:51:59', null);
INSERT INTO `dg_goods_type` VALUES ('3e6ea40386c644efb3f4c1fad2c36f84', '保健品', '1', '2017-02-23 15:54:05', null);
INSERT INTO `dg_goods_type` VALUES ('6aaebf878145409fad81ecc02a63e8c7', '化妆品', '1', '2017-02-20 15:32:23', null);
INSERT INTO `dg_goods_type` VALUES ('ae31ba9588e846b8a9c19d937e79b3b3', '包', '1', '2017-02-20 15:30:56', null);

-- ----------------------------
-- Table structure for dg_order
-- ----------------------------
DROP TABLE IF EXISTS `dg_order`;
CREATE TABLE `dg_order` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `user_uuid` varchar(32) DEFAULT NULL COMMENT '用户id',
  `account` decimal(10,2) DEFAULT '0.00' COMMENT '总价',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `done_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `op_uuid` varchar(32) DEFAULT NULL COMMENT '操作员uuid',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 1：下单 2：完成 0：删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of dg_order
-- ----------------------------
INSERT INTO `dg_order` VALUES ('0057907c7eb7440c96b705648b1cae46', '007daff78b0341f22bf0c7b28065ce9e', '222.22', null, null, null, '0', '2017-02-24 11:52:55', null);
INSERT INTO `dg_order` VALUES ('0057907c7eb7440c96b705648b1caf76', '007daff78b03qwf6bbf0c7b28065ce9e', '222.00', '', null, '', '1', '2017-02-24 10:59:29', '2017-02-27 10:42:00');

-- ----------------------------
-- Table structure for dg_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `dg_order_detail`;
CREATE TABLE `dg_order_detail` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `order_uuid` varchar(32) DEFAULT NULL COMMENT '订单uuid',
  `goods_uuid` varchar(32) DEFAULT NULL COMMENT '商品uuid',
  `quantity` int(10) DEFAULT '0' COMMENT '商品数量',
  `account` decimal(10,2) DEFAULT '0.00' COMMENT '总价',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 1：下单 2：完成 0：删除',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dg_order_detail
-- ----------------------------
INSERT INTO `dg_order_detail` VALUES ('007da1178b0341f6bbf0c7b282cce9e', '0057907c7eb7440c96b705648b1caf76', '007da1178b0341f6bbf0c7b2806ce9e', '1', '222.00', '1');

-- ----------------------------
-- Table structure for dg_user
-- ----------------------------
DROP TABLE IF EXISTS `dg_user`;
CREATE TABLE `dg_user` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(32) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 1：正常 0：删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `invite_user` varchar(32) DEFAULT NULL COMMENT '邀请人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`),
  KEY `idx_uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

-- ----------------------------
-- Records of dg_user
-- ----------------------------
INSERT INTO `dg_user` VALUES ('0057907c7eb7440c96b705648b1d6f76', '阿斯达', null, '哦哦', '13012341234', '的所发生的', '1', '是的', null, '2017-02-10 16:03:59', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('00712ff78b0341f6bbf0c7b28065ce9e', '啊实打实的5', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007da1178b0341f6bbf0c7b28065ce9e', '啊实打实的14', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007dafas8b0341f6bbf0c7b28065ce9e', '啊实打实的16', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '0', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff12b0341f6bbf0c7b28065ce9e', '啊实打实的3', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '0', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff7823341f6bbf0c7b28065ce9e', '啊实打实的8', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff7876341f6bbf0c7b28065ce9e', '啊实打实的10', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', '啊实打实的16', '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0121f6bbf0c7b28065ce9e', '啊实打实的4', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0141f23bf0c7b28065ce9e', '啊实打实的6', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f22bf0c7b28065ce9e', '啊实打实的13', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6bb54c7b28065ce9e', '啊实打实的9', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6bbf0c7538065ce9e', '啊实打实的12', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6bbf0c7b28065c12e', '啊实打实的11', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6bbf0c7b28065ce9e', '啊实打实的', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6bbf0c7bzx065ce9e', '啊实打实的22', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6bth0c7b28065ce9e', '啊实打实的21', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6qwf0c7b28065ce9e', '啊实打实的26', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341f6xcf0c7b28065ce9e', '啊实打实的18', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341fczbf0c7b28065ce9e', '啊实打实的23', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341qw12f0c7b28065ce9e', '啊实打实的1', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0341vwbbf0c7b28065ce9e', '啊实打实的20', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b034326bbf0c7b28065ce9e', '啊实打实的7', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b034as6bbf0c7b28065ce9e', '啊实打实的17', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b034eh6bbf0c7b28065ce9e', '啊实打实的30', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b03qff6bbf0c7b28065ce9e', '啊实打实的19', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b03qwf6bbf0c7b28065ce9e', '啊实打实的27', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b0my1f6bbf0c7b28065ce9e', '啊实打实的25', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78b1241f6bbf0c7b28065ce9e', '啊实打实的2', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78bxr41f6bbf0c7b28065ce9e', '啊实打实的29', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007daff78qf341f6bbf0c7b28065ce9e', '啊实打实的24', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('007dafht8b0341f6bbf0c7b28065ce9e', '啊实打实的28', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('071daff78b0341f6bbf0c7b28065ce9e', '啊实打实的15', null, '哈哈', '13012345678', '撒大声地阿斯达爱上', '1', '阿斯达爱上', null, '2017-02-10 16:03:37', '2017-02-15 10:20:38');
INSERT INTO `dg_user` VALUES ('0d179c8eae7d44d9ba5167c459d7e988', 'dasdasd', null, '123123', '134123123123', 'adasdasd', '1', '12132312', null, '2017-02-15 14:19:55', null);
INSERT INTO `dg_user` VALUES ('1310abaf51dd4ba9862c96ad789d3539', '412rdf1d1dwe', null, 'sfasdfsdf', '14131423', 'casdcsc', '1', null, null, '2017-02-15 14:16:25', null);
INSERT INTO `dg_user` VALUES ('2bf7293fb37b47278b318dfb0b78bc99', 'asasdads11212312', null, '12e12d1', '12361234124', 'sccac', '1', '121212', null, '2017-02-15 14:03:18', null);
INSERT INTO `dg_user` VALUES ('2d9b2f3572f74bcda0cd27b62ad8f738', '14314fsdfasdf1', null, null, '12345123412', null, '0', null, null, '2017-02-15 14:12:05', null);
INSERT INTO `dg_user` VALUES ('30d16e006cf44fa0a5d61c9f745541a7', 'dasdasd', null, '123123', '134123123123', 'adasdasd', '1', '12132312', null, '2017-02-15 14:18:38', null);
INSERT INTO `dg_user` VALUES ('3225dfd23a9445f2b5a49506601573fd', 'dasdadad', null, null, '131313562135', null, '0', null, null, '2017-02-15 14:26:55', null);
INSERT INTO `dg_user` VALUES ('3a01ca2099d44e008f6d19f25d7f2a7e', 'fasdfsdf', null, 'da', '313131', 'asdasd', '0', null, null, '2017-02-15 14:40:15', null);
INSERT INTO `dg_user` VALUES ('3f5ac11f2bce4fcd8da03c300b51a5ba', 'aa4a1241234', null, null, '1313', null, '0', null, null, '2017-02-15 14:42:54', null);
INSERT INTO `dg_user` VALUES ('6a8c6c76a95d4daaa490a9a9c052642c', '13123112sdadas123', null, '12321312', 'dasdasd123', 'asdasdasd', '1', 'asdasdasd', null, '2017-02-15 14:23:17', null);
INSERT INTO `dg_user` VALUES ('711a693dcc064eb3806f8d745d44704f', 'dasdasd', null, '123123', '134123123123', 'adasdasd', '1', '12132312', null, '2017-02-15 14:19:59', null);
INSERT INTO `dg_user` VALUES ('a2d9bf20a4274e87a8347dd5650ce977', 'fsfa2121', null, 'fsdfs', '1332233223', '3223', '1', 'dasd', null, '2017-02-28 17:42:54', null);
INSERT INTO `dg_user` VALUES ('a6ae7f73193b4549b6e617fb8b019fdf', '14314fsssdfasdf1', null, '1243123', '12345123412', 'sdfasdas', '1', 'cascascasc', null, '2017-02-15 14:12:32', null);
INSERT INTO `dg_user` VALUES ('bd179289f9f445f0a30c0f9eb877e15e', '14314fsssdfasdf1', null, '1243123', '12345123412', 'sdfasdas', '1', 'cascascasc', null, '2017-02-15 14:13:14', null);
INSERT INTO `dg_user` VALUES ('d11a36e0645a47919c3ed2b2831208a8', '14243dsfsafsd111', null, 'fsaffsdf', '12412341234', 'sdfsdf', '1', '', null, '2017-02-15 14:25:19', '2017-02-15 15:22:50');
INSERT INTO `dg_user` VALUES ('e3fc0977acd94218b6d0d7e7a28fe026', 'asdasd1', null, null, '114124', null, '0', null, null, '2017-02-15 14:24:03', null);
INSERT INTO `dg_user` VALUES ('e76718c9de2940dda3eb586a1126e84b', '123123', null, '3123', '12312', '123123', '0', '123123', null, '2017-03-16 12:01:28', null);
INSERT INTO `dg_user` VALUES ('f3385883ced841cea2d2c06b54bcaeb0', 'dasdasd22222111', null, '123123', '134123123123', 'adasdasd', '1', '12132312', null, '2017-02-15 14:19:51', '2017-02-15 15:02:40');
INSERT INTO `dg_user` VALUES ('f3440b7858e247c1aeed4cc44870744a', 'fasdasd', null, 'fds', '312312313', 'ad', '0', null, null, '2017-02-15 14:34:26', null);
INSERT INTO `dg_user` VALUES ('ffcfa410ead641b6ad75649144909780', 'asASsS', null, null, '1331313131', null, '0', null, null, '2017-02-15 14:55:43', null);

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
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 1：正常 2：删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_operator
-- ----------------------------
INSERT INTO `sys_operator` VALUES ('54c294528b5041ff838380676a2ed643', 'admin', '7abbc4d092252eb23a61f40f0656e2211fe3b958e65b1c0f', 'zcj', '13000000000', '2017-02-08 16:36:49', '0', null);
