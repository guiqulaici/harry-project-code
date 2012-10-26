-- --------------------------------------------------------
-- Host:                         121.101.219.28
-- Server version:               5.1.59-log - MySQL Community Server (GPL)
-- Server OS:                    unknown-linux-gnu
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-10-26 18:00:01
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for yeahwapnetgame
CREATE DATABASE IF NOT EXISTS `yeahwapnetgame` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yeahwapnetgame`;


-- Dumping structure for table yeahwapnetgame.auth_operator
CREATE TABLE IF NOT EXISTS `auth_operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) DEFAULT NULL,
  `login` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table yeahwapnetgame.channel
CREATE TABLE IF NOT EXISTS `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '渠道提供者',
  `name` varchar(20) NOT NULL DEFAULT 'a',
  `isview` int(11) NOT NULL DEFAULT '0' COMMENT '0、启用；1、黑名单',
  `dateline` datetime NOT NULL DEFAULT '2012-07-01 01:01:01',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table yeahwapnetgame.cp
CREATE TABLE IF NOT EXISTS `cp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '游戏提供商',
  `name` varchar(20) NOT NULL DEFAULT 'a',
  `isview` int(11) NOT NULL DEFAULT '0' COMMENT '0、启用；1、黑名单',
  `dateline` datetime NOT NULL DEFAULT '2012-07-01 01:01:01',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table yeahwapnetgame.ng_from
CREATE TABLE IF NOT EXISTS `ng_from` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品渠道关联表产生来源ID',
  `name` varchar(50) DEFAULT 'a' COMMENT '关联名称',
  `product_id` int(11) NOT NULL DEFAULT '0' COMMENT '产品id',
  `channel_id` int(11) NOT NULL DEFAULT '0' COMMENT '渠道id',
  `frominfo` text COMMENT '关联简介',
  `isview` int(11) DEFAULT '0' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table yeahwapnetgame.pay
CREATE TABLE IF NOT EXISTS `pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付还不完善，后期补充',
  `name` int(11) NOT NULL DEFAULT '0',
  `isview` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table yeahwapnetgame.pay_log
CREATE TABLE IF NOT EXISTS `pay_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付日志',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `from_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联表来源表ID',
  `pay_id` int(11) NOT NULL DEFAULT '0' COMMENT '支付ID',
  `momey` int(11) NOT NULL DEFAULT '0' COMMENT '支付金额',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0，失败,1成功',
  `dateline` datetime NOT NULL DEFAULT '2012-07-01 01:01:01',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table yeahwapnetgame.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '游戏',
  `cp_id` int(11) NOT NULL DEFAULT '0' COMMENT '游戏所属提供商',
  `name` varchar(50) NOT NULL DEFAULT 'a' COMMENT '游戏名称',
  `isview` int(11) NOT NULL DEFAULT '0' COMMENT '0、启用；1、黑名单',
  `dateline` datetime DEFAULT '2012-07-01 01:01:01',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table yeahwapnetgame.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT 'a' COMMENT '平台用户名',
  `password` varchar(20) NOT NULL DEFAULT 'a' COMMENT '密码',
  `init_fromid` int(11) NOT NULL DEFAULT '0' COMMENT '平台用户注册时的初始来源',
  `dateline` datetime NOT NULL DEFAULT '2012-07-01 01:01:01',
  `phone` varchar(20) DEFAULT 'a',
  `email` varchar(50) DEFAULT 'a',
  `score` int(11) DEFAULT '0' COMMENT '账号的金额',
  `isview` int(11) NOT NULL DEFAULT '0' COMMENT '0、启用，1、黑名单',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '账号类型：1，本地账户，2,新浪微博，3，腾讯微博',
  `weibo_id` varchar(50) DEFAULT 'a',
  `token` varchar(100) DEFAULT 'a',
  `secret` varchar(100) DEFAULT 'a',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
