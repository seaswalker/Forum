/*
Navicat MySQL Data Transfer

Source Server         : skywalker
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : myforum

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-05-31 18:51:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '2');

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL,
  `content` longtext NOT NULL,
  `createtime` datetime NOT NULL,
  `clickcount` int(11) NOT NULL,
  `replycount` int(11) NOT NULL,
  `sectionid` int(11) NOT NULL,
  `visible` bit(1) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1',
  `categoryid` int(11) NOT NULL,
  `lastreplyname` varchar(10) DEFAULT NULL,
  `lastreplytime` varchar(20) DEFAULT NULL,
  `authorid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sectionid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '第一篇帖子', '<p>你妹的</p>\r\n', '2015-04-15 15:40:46', '20', '3', '12', '', '2', '3', 'skywalker', '04月18日 16:24', '2');
INSERT INTO `article` VALUES ('2', '权限模块怎么搞?', '<p>求助</p>\r\n', '2015-04-18 15:59:43', '59', '6', '12', '', '3', '1', 'skywalker', '04月27日 09:03', '2');
INSERT INTO `article` VALUES ('3', '这是windows7吗', '<p>好冷清</p>\r\n', '2015-04-18 16:28:16', '3', '0', '13', '', '1', '3', 'skywalker', '04月18日 16:28', '2');
INSERT INTO `article` VALUES ('4', '人呢，都特么死哪去了?', '<p>没看见老子来了吗</p>\r\n', '2015-04-29 12:02:57', '24', '1', '15', '', '1', '1', '习近平', '04月29日 12:03', '3');
INSERT INTO `article` VALUES ('5', '论怎样成为大水逼', '<p>鲁雄薪</p>\r\n', '2015-05-01 19:47:21', '3', '1', '13', '', '1', '4', '习近平', '05月01日 19:48', '2');
INSERT INTO `article` VALUES ('6', '第一帖', '<p>呵呵哒</p>\r\n', '2015-05-01 19:49:34', '0', '0', '24', '', '1', '2', '习近平', '05月01日 19:49', '3');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `visible` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '技术交流', '');
INSERT INTO `category` VALUES ('2', '疑难解答', '');
INSERT INTO `category` VALUES ('3', '大水比', '');
INSERT INTO `category` VALUES ('4', '经验交流', '');
INSERT INTO `category` VALUES ('5', '日经贴', '');
INSERT INTO `category` VALUES ('6', '月经贴', '');
INSERT INTO `category` VALUES ('7', '呵呵哒', '');
INSERT INTO `category` VALUES ('8', '日经', '');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `createtime` datetime NOT NULL,
  `visible` bit(1) NOT NULL,
  `articleid` int(11) NOT NULL,
  `authorid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `articleid` (`articleid`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`articleid`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '<p>给你捧捧场</p>\r\n', '2015-04-15 20:44:44', '', '1', '2');
INSERT INTO `reply` VALUES ('2', '<blockquote>给你捧捧场</blockquote>\r\n\r\n<p>去你妹的&nbsp;</p>\r\n', '2015-04-15 20:48:38', '', '1', '2');
INSERT INTO `reply` VALUES ('3', '<p>1分钟无人回复惨案现场。。。</p>\r\n', '2015-04-18 16:00:30', '', '2', '2');
INSERT INTO `reply` VALUES ('4', '<p>1分钟无人回复惨案现场。。。</p>\r\n', '2015-04-18 16:01:13', '', '2', '2');
INSERT INTO `reply` VALUES ('5', '<p>测试回复</p>\r\n', '2015-04-18 16:04:38', '', '2', '2');
INSERT INTO `reply` VALUES ('6', '<blockquote>测试回复</blockquote>\r\n\r\n<p>好了没&nbsp;</p>\r\n', '2015-04-18 16:06:41', '', '2', '2');
INSERT INTO `reply` VALUES ('7', '<p>我是新回复</p>\r\n', '2015-04-18 16:23:08', '', '2', '2');
INSERT INTO `reply` VALUES ('8', '<blockquote>去你妹的&nbsp;</blockquote>\r\n\r\n<p>去去去去你妹的&nbsp;</p>\r\n', '2015-04-18 16:24:08', '', '1', '2');
INSERT INTO `reply` VALUES ('9', '<p>好了吗</p>\r\n', '2015-04-27 09:03:31', '', '2', '2');
INSERT INTO `reply` VALUES ('10', '<p>自抢2楼</p>\r\n', '2015-04-29 12:03:21', '', '4', '3');
INSERT INTO `reply` VALUES ('11', '<p>朕来抢个二楼</p>\r\n', '2015-05-01 19:48:07', '', '5', '3');

-- ----------------------------
-- Table structure for `section`
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `pid` int(11) NOT NULL,
  `lastreplyid` int(11) DEFAULT NULL,
  `visible` bit(1) NOT NULL,
  `clickcount` int(11) NOT NULL,
  `articlecount` int(11) NOT NULL,
  `manager` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('11', 'Windows讨论区', '0', '0', '', '0', '0', '');
INSERT INTO `section` VALUES ('12', 'Windows8.1', '11', '2', '', '46', '2', ' skywalker 习近平');
INSERT INTO `section` VALUES ('13', 'Windows7讨论区', '11', '5', '', '9', '2', ' skywalker');
INSERT INTO `section` VALUES ('14', 'Java讨论区', '0', '0', '', '0', '0', null);
INSERT INTO `section` VALUES ('15', 'Java SE', '14', '4', '', '4', '1', null);
INSERT INTO `section` VALUES ('16', 'Spring讨论区', '14', '0', '', '0', '0', null);
INSERT INTO `section` VALUES ('17', 'Windows XP讨论区', '11', '0', '', '0', '0', '');
INSERT INTO `section` VALUES ('18', 'Hibernate讨论区', '14', '0', '', '0', '0', '');
INSERT INTO `section` VALUES ('19', 'Windows XP讨论区', '11', '0', '', '0', '0', '');
INSERT INTO `section` VALUES ('23', 'chrome讨论区', '0', '0', '', '0', '0', ' skywalker');
INSERT INTO `section` VALUES ('24', '64位讨论区', '23', '6', '', '2', '1', ' skywalker');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(32) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `visible` bit(1) NOT NULL,
  `isAdmin` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'skywalker', '20f3d6b07200779f6c7c2ccbc4f8a0cf', 'avatar/default.gif', 'xsdwem7@hotmail.com', '', '');
INSERT INTO `user` VALUES ('3', '习近平', 'fcea920f7412b5da7be0cf42b8c93759', 'avatar/default.gif', '985744197@qq.com', '', '');

-- ----------------------------
-- Table structure for `user_section`
-- ----------------------------
DROP TABLE IF EXISTS `user_section`;
CREATE TABLE `user_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_section
-- ----------------------------
INSERT INTO `user_section` VALUES ('2', '2', '12');
INSERT INTO `user_section` VALUES ('3', '2', '13');
INSERT INTO `user_section` VALUES ('4', '2', '23');
INSERT INTO `user_section` VALUES ('5', '2', '24');
INSERT INTO `user_section` VALUES ('6', '3', '12');
INSERT INTO `user_section` VALUES ('7', '2', '11');

-- ----------------------------
-- Table structure for `user_shield`
-- ----------------------------
DROP TABLE IF EXISTS `user_shield`;
CREATE TABLE `user_shield` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `endtime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_shield
-- ----------------------------

-- ----------------------------
-- Table structure for `verify`
-- ----------------------------
DROP TABLE IF EXISTS `verify`;
CREATE TABLE `verify` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `expire` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(255) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of verify
-- ----------------------------
