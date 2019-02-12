/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : parking

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-01-16 09:06:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_card
-- ----------------------------
DROP TABLE IF EXISTS `t_card`;
CREATE TABLE `t_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `car_no` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `place_no` int(11) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9nnpcgj7notms30m3mhynq4oj` (`place_no`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_card
-- ----------------------------
INSERT INTO `t_card` VALUES ('9', '地址1', 'G11111', '男', '张三', '9', '\0');
INSERT INTO `t_card` VALUES ('10', '地址2', 'G22222', '男', '李四', '10', '');
INSERT INTO `t_card` VALUES ('11', '地址3', 'G33333', '女', '王五', '11', '\0');

-- ----------------------------
-- Table structure for t_card_temporary
-- ----------------------------
DROP TABLE IF EXISTS `t_card_temporary`;
CREATE TABLE `t_card_temporary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place_no` int(11) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmls2qonce99r3l4jik8hwm38l` (`place_no`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_card_temporary
-- ----------------------------

-- ----------------------------
-- Table structure for t_park_fixed
-- ----------------------------
DROP TABLE IF EXISTS `t_park_fixed`;
CREATE TABLE `t_park_fixed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) DEFAULT NULL,
  `entry_time` datetime DEFAULT NULL,
  `leave_time` datetime DEFAULT NULL,
  `car_no` varchar(255) DEFAULT NULL,
  `place_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_park_fixed
-- ----------------------------
INSERT INTO `t_park_fixed` VALUES ('5', '9', '2019-01-03 12:03:35', '2019-01-03 12:03:43', 'G11111', 'A10001');
INSERT INTO `t_park_fixed` VALUES ('6', '10', '2019-01-03 12:03:41', null, 'G22222', 'A10002');

-- ----------------------------
-- Table structure for t_park_temporary
-- ----------------------------
DROP TABLE IF EXISTS `t_park_temporary`;
CREATE TABLE `t_park_temporary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_no` varchar(255) DEFAULT NULL,
  `card_id` varchar(255) DEFAULT NULL,
  `entry_time` datetime DEFAULT NULL,
  `level_time` datetime DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `leave_time` datetime DEFAULT NULL,
  `place_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_park_temporary
-- ----------------------------
INSERT INTO `t_park_temporary` VALUES ('18', '555', '11', '2019-01-03 15:53:00', null, '10', '10', '2019-01-03 15:53:00', 'B10001');
INSERT INTO `t_park_temporary` VALUES ('15', '555', '8', '2019-01-03 15:42:00', null, '10', '10', '2019-01-03 15:42:00', 'B10002');
INSERT INTO `t_park_temporary` VALUES ('16', '555', '7', '2019-01-03 15:43:00', null, '10', '10', '2019-01-03 15:43:00', 'B10001');
INSERT INTO `t_park_temporary` VALUES ('17', '555', '8', '2019-01-03 15:44:00', null, '10', '10', '2019-01-03 15:44:00', 'B10002');
INSERT INTO `t_park_temporary` VALUES ('14', '5555', '8', '2019-01-03 14:40:00', null, '10', '10', '2019-01-03 14:40:00', 'B10002');
INSERT INTO `t_park_temporary` VALUES ('13', '555', '8', '2019-01-03 14:35:00', null, '10', '10', '2019-01-03 14:35:00', 'B10002');
INSERT INTO `t_park_temporary` VALUES ('19', '5555', '12', '2019-01-03 15:56:00', null, '10', '10', '2019-01-03 15:56:00', 'B10001');

-- ----------------------------
-- Table structure for t_place
-- ----------------------------
DROP TABLE IF EXISTS `t_place`;
CREATE TABLE `t_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(255) DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_place
-- ----------------------------
INSERT INTO `t_place` VALUES ('13', 'A10005', '固定车位', '\0', '');
INSERT INTO `t_place` VALUES ('12', 'A10004', '固定车位', '\0', '');
INSERT INTO `t_place` VALUES ('11', 'A10003', '固定车位', '', '');
INSERT INTO `t_place` VALUES ('10', 'A10002', '固定车位', '', '');
INSERT INTO `t_place` VALUES ('9', 'A10001', '固定车位', '', '');
INSERT INTO `t_place` VALUES ('14', 'B10001', '临时车位', '\0', '');
INSERT INTO `t_place` VALUES ('15', 'B10002', '临时车位', '\0', '');
INSERT INTO `t_place` VALUES ('19', 'B10004', '临时车位', '\0', '');
INSERT INTO `t_place` VALUES ('18', 'B10003', '临时车位', '\0', '');
INSERT INTO `t_place` VALUES ('20', 'B10005', '临时车位', '\0', '');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m5bu5erj83eubjsa1nyms0t89` (`phone`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('6', 'admin', '18730291528', '王');
INSERT INTO `t_user` VALUES ('1', '111', '18730291527', 'admin');

-- ----------------------------
-- View structure for v_card
-- ----------------------------
DROP VIEW IF EXISTS `v_card`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_card` AS select `card`.`card_id` AS `card_id`,`card`.`seat_id` AS `seat_id`,`card`.`user_name` AS `user_name`,`card`.`user_gender` AS `user_gender`,`card`.`user_addr` AS `user_addr`,`card`.`car_num` AS `car_num`,`seat`.`seat_num` AS `seat_num` from (`card` join `seat` on((`card`.`seat_id` = `seat`.`seat_id`))) ;

-- ----------------------------
-- View structure for v_fixed
-- ----------------------------
DROP VIEW IF EXISTS `v_fixed`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_fixed` AS select `fixed`.`fixed_id` AS `fixed_id`,`fixed`.`card_id` AS `card_id`,`fixed`.`entry_date` AS `entry_date`,`fixed`.`entry_time` AS `entry_time`,`fixed`.`out_date` AS `out_date`,`fixed`.`out_time` AS `out_time`,`card`.`car_num` AS `car_num`,`card`.`user_name` AS `user_name` from (`fixed` join `card` on((`fixed`.`card_id` = `card`.`card_id`))) ;

-- ----------------------------
-- View structure for v_user
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user` AS select `user`.`user_id` AS `user_id`,`user`.`role_id` AS `role_id`,`user`.`user_name` AS `user_name`,`user`.`real_name` AS `real_name`,`user`.`user_pwd` AS `user_pwd`,`user`.`user_phone` AS `user_phone`,`role`.`role_name` AS `role_name` from (`user` join `role` on((`user`.`role_id` = `role`.`role_id`))) ;
