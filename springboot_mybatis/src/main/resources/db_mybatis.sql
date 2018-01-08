/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50558
Source Host           : 127.0.0.1:3306
Source Database       : db_mybatis

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-01-08 17:49:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'tom', '20');
INSERT INTO `test` VALUES ('2', 'tom2', '22');
INSERT INTO `test` VALUES ('3', 'tom3', '33');
INSERT INTO `test` VALUES ('4', 'tom4', '34');
INSERT INTO `test` VALUES ('5', 'tom5', '25');
INSERT INTO `test` VALUES ('6', 'tom6', '26');
INSERT INTO `test` VALUES ('7', 'tom7', '27');
INSERT INTO `test` VALUES ('8', 'tom8', '28');
INSERT INTO `test` VALUES ('9', 'tom9', '29');
INSERT INTO `test` VALUES ('11', 'tom11', '31');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'howard', '23');
INSERT INTO `user` VALUES ('2', 'tom', '25');
INSERT INTO `user` VALUES ('3', 'tom2', '25');
