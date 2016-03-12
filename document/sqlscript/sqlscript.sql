DROP DATABASE IF EXISTS `mypos`;

CREATE DATABASE `mypos`;

USE `mypos`;

-- 商品定义表
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int NOT NULL auto_increment,
  `code` VARCHAR(50) DEFAULT '' COMMENT '商品条码',
  `name` VARCHAR(50) DEFAULT '' COMMENT '商品名称',
  `unit` VARCHAR(10) DEFAULT '' COMMENT '数量单位',
  `unit_price` DECIMAL(10,2) DEFAULT '0' COMMENT '商品单价',
  `created_ts` datetime DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

INSERT INTO `item`(`code`,`name`,`unit`,`unit_price`,`created_ts`,`updated_ts`) VALUES 
('ITEM000001','羽毛球','个','1.00',now(),now()),
('ITEM000003','苹果','斤','5.50',now(),now()),
('ITEM000005','可口可乐','瓶','3.00',now(),now());

-- 优惠活动定义表
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int NOT NULL auto_increment,
  `code` VARCHAR(50) DEFAULT '' COMMENT '活动编码',
  `name` VARCHAR(50) DEFAULT '' COMMENT '活动名称',
  `impl_class_name` VARCHAR(100) DEFAULT '' COMMENT '活动实现类名称',
  `created_ts` datetime DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
INSERT INTO `activity`(`code`,`name`,`impl_class_name`,`created_ts`,`updated_ts`) VALUES 
('ACTIVITY_1','买二赠一','com.thoughtworks.activity.impl.CouponActivity',now(),now()),
('ACTIVITY_2','95折','com.thoughtworks.activity.impl.DiscountActivity',now(),now());

-- 商品活动关系表
DROP TABLE IF EXISTS `item_activity_relation`;
CREATE TABLE `item_activity_relation` (
  `id` int NOT NULL auto_increment,
  `item_code` VARCHAR(50) NOT NULL COMMENT '商品条码',
  `activity_code` VARCHAR(50) NOT NULL COMMENT '活动编码',
  `seq_no` int NOT NULL DEFAULT '0' COMMENT '优先级排序',
  `created_ts` datetime DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 取消可口可乐的买二送一
-- DELETE FROM `item_activity_relation` WHERE `item_code` = 'ITEM000005' AND `activity_code` = 'ACTIVITY_1';
-- 设置可口可乐买二送一,加载优先级为1
-- INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000005','ACTIVITY_1','1',now(),now());
-- 取消可口可乐的95%折扣
-- DELETE FROM `item_activity_relation` WHERE `item_code` = 'ITEM000005' AND `activity_code` = 'ACTIVITY_2';
-- 设置可口可乐的95%折扣,加载优先级为2
-- INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000005','ACTIVITY_2','2',now(),now());


-- 取消羽毛球的买二送一
-- DELETE FROM `item_activity_relation` WHERE `item_code` = 'ITEM000001' AND `activity_code` = 'ACTIVITY_1';
-- 设置羽毛球买二送一,加载优先级为1
-- INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000001','ACTIVITY_1','1',now(),now());
-- 取消羽毛球的95%折扣
-- DELETE FROM `item_activity_relation` WHERE `item_code` = 'ITEM000001' AND `activity_code` = 'ACTIVITY_2';
-- 设置羽毛球的95%折扣,加载优先级为2
-- INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000001','ACTIVITY_2','2',now(),now());


-- 取消苹果的买二送一
-- DELETE FROM `item_activity_relation` WHERE `item_code` = 'ITEM000003' AND `activity_code` = 'ACTIVITY_1';
-- 设置苹果买二送一,加载优先级为1
-- INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000003','ACTIVITY_1','1',now(),now());
-- 取消苹果的95%折扣
-- DELETE FROM `item_activity_relation` WHERE `item_code` = 'ITEM000003' AND `activity_code` = 'ACTIVITY_2';
-- 设置苹果的95%折扣,加载优先级为2
-- INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000003','ACTIVITY_2','2',now(),now());

