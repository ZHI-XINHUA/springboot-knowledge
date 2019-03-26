/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.108(mysql)
Source Server Version : 50720
Source Host           : 192.168.3.108:3306
Source Database       : microserver

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-02-21 12:27:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(64) NOT NULL,
  `usercode` varchar(30) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `state` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('e98b0351-9fa9-4de7-92e9-cf5c79c663ef', 'zs', '张三', 'xxvcvc', 'dfdf@dd.com', null, null, null, '0');
INSERT INTO `user` VALUES ('fb0abbba-525a-46f3-9cd0-12adf3299203', 'lisi', '李四', 'xxvcvc', 'dfdf@dd.com', null, null, null, null);
