/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.31 : Database - lds
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lds` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `lds`;

/*Table structure for table `lds_allocation_list` */

DROP TABLE IF EXISTS `lds_allocation_list`;

CREATE TABLE `lds_allocation_list` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `substation_id` int DEFAULT NULL,
  `substation_name` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int DEFAULT '0' COMMENT '0未分发1已分发2已收3错误数量',
  `cs_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `product_name` varchar(128) DEFAULT NULL,
  `num` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_allocation_list` */

insert  into `lds_allocation_list`(`id`,`order_id`,`substation_id`,`substation_name`,`create_time`,`update_time`,`status`,`cs_id`,`product_id`,`product_name`,`num`) values (1,20,1,'山西分站','2023-07-06 20:42:07','2023-07-06 20:42:07',2,3,NULL,NULL,NULL),(2,19,2,'辽宁分站','2023-07-06 20:42:53','2023-07-06 20:42:53',0,3,NULL,NULL,NULL),(5,18,2,'辽宁分站','2023-07-06 20:52:01','2023-07-08 16:32:31',2,3,NULL,NULL,NULL),(7,17,1,'山西分站','2023-07-07 09:17:10','2023-07-07 23:55:27',2,3,NULL,NULL,NULL),(16,21,1,'山西分站','2023-07-07 15:01:38','2023-07-07 15:01:38',0,3,NULL,NULL,NULL),(17,22,1,'山西分站','2023-07-08 00:07:11','2023-07-08 00:07:43',2,3,NULL,NULL,NULL),(18,14,2,'辽宁分站','2023-07-08 16:39:55','2023-07-08 16:43:52',2,3,NULL,NULL,NULL),(19,31,1,'山西分站','2023-07-10 12:45:44','2023-07-10 12:47:13',2,3,NULL,NULL,NULL),(20,33,1,'山西分站','2023-07-11 10:08:09','2023-07-11 10:08:09',0,4,NULL,NULL,NULL),(21,37,1,'山西分站','2023-07-11 13:23:03','2023-07-11 13:24:16',2,3,NULL,NULL,NULL),(22,38,1,'山西分站','2023-07-11 13:39:05','2023-07-11 13:39:20',2,3,NULL,NULL,NULL),(23,39,1,'山西分站','2023-07-11 13:41:55','2023-07-11 13:42:07',2,3,NULL,NULL,NULL),(24,40,1,'山西分站','2023-07-11 13:44:38','2023-07-11 13:44:53',2,3,NULL,NULL,NULL),(25,41,1,'山西分站','2023-07-11 13:58:12','2023-07-11 13:58:25',2,3,NULL,NULL,NULL),(26,42,1,'山西分站','2023-07-11 14:59:10','2023-07-11 14:59:23',2,3,8,'小米电视55寸',2),(27,43,1,'山西分站','2023-07-11 15:03:16','2023-07-11 15:04:14',2,3,3,'机械革命',2);

/*Table structure for table `lds_center` */

DROP TABLE IF EXISTS `lds_center`;

CREATE TABLE `lds_center` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_center` */

insert  into `lds_center`(`id`,`name`,`password`,`phone`,`create_time`,`update_time`) values (1,'center','$2a$10$W/oUVfkWgBUMI0ObeLoLsuR82QiIAk9oUfm5hWCNdAyyn/zbeNGva',NULL,'2023-07-07 22:30:06','2023-07-07 22:30:06');

/*Table structure for table `lds_client` */

DROP TABLE IF EXISTS `lds_client`;

CREATE TABLE `lds_client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `identity` varchar(128) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `image_delete` varchar(256) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_client` */

insert  into `lds_client`(`id`,`name`,`identity`,`phone`,`address`,`email`,`createtime`,`updatetime`,`image_url`,`image_delete`,`password`) values (1,'mjj','12343241','13412341','qwqrqrqwr','qwrrqwr','2023-06-23 14:43:15','2023-06-23 14:43:23','https://s2.loli.net/2023/06/25/Y9qhieEIG5x3Sbk.png','https://smms.app/delete/6obVSmTNZMXGWAFa9KOCjs15qz','$2a$10$48j5Q3tPUanv7kYXYPuLaeIinmzOxt0A5e12D4jQIszdSLPWICWI.'),(2,'yctf','123123','123123123','qweqwe','q','2023-06-21 16:58:27','2023-06-23 14:43:55','https://s2.loli.net/2023/06/25/IuscaC3TZ4kSYwN.png','https://smms.app/delete/QuBWe3CkFPjVo7tsEwzxUKyMcZ','$2a$10$nvk8h5p/bvU8lhWEoS5oPuqunS/e9rup.i80xcdQiT8mfjApDwAFW'),(3,'plm','123321','123123','zcxzcx','df','2023-06-23 14:44:00','2023-06-14 16:58:30','https://s2.loli.net/2023/06/25/rL6atJQ9iXI7j3k.png','https://smms.app/delete/ifLn47uNdtC6rFTMSRq8zAEYXB','$2a$10$OrjzKrKvzzNsHnISGHmkce6T70debSiDlHUXsxgZr.UO3xi5Vo2fi'),(4,'tjzz','12312412','123123123','1231wqeqw','qweqwe','2023-06-23 14:44:35','2023-06-23 14:44:37','https://s2.loli.net/2023/06/25/rL6atJQ9iXI7j3k.png',NULL,'$2a$10$M1fR1NRV0pOvOz7OHIMY9eokaDNg5/rGMxzLQG.UvO5QUKjiPTIVS'),(5,'sbhhhhj','12312311','124124','awqre','awdawdew','2023-06-23 23:51:35','2023-06-23 23:51:35','https://s2.loli.net/2023/06/25/abDoet8wS241REG.png','https://smms.app/delete/NJXmqBraQ8FfZjxPG5i1D3uheO','$2a$10$4WQ9BK5siYAYTcg9KLrOgeffkfUJwOHBctmEm5b5SagF21/HgV0Pm'),(6,'hh','123123123','123123123','vdfvdf','eretet','2023-06-23 14:45:17','2023-06-23 14:45:20','https://s2.loli.net/2023/06/25/myGOcEXjvRp7hsI.png','https://smms.app/delete/9AyrOuW4HoMGgBxEvP8lhUq2YQ','$2a$10$nS/MI9NHsULhs9bzCECzteeNEAbpUZRX9eUOlfd1f/pTeO40sMU/m'),(7,'mjj2','1231234','18404981127','shenyang','bkbknice@qq.com','2023-06-23 20:40:20','2023-06-23 20:40:20','https://s2.loli.net/2023/06/25/ZIUfNv15eEb37SX.png','https://smms.app/delete/lRLhVgFxECpndqU1azX4Z72BIs','$2a$10$dyYfsvLRgNg96sJH2J0w..lSev7MOxq6UTSqflsOwN1EjjxFkUJZu'),(8,'mjj3','12312345','1241234','shenyang','bkbknice@qq.comr','2023-06-23 20:41:50','2023-06-23 20:41:50','https://s2.loli.net/2023/06/25/pmLndDRHY3ayor2.png','https://smms.app/delete/gFCQVXtnjbiPIYHWZDuGA9avhM','$2a$10$B3B77W61su/J.jvkKGBXZOOSynbLcUAjG0V/ahViC5iENAuagtHqG'),(9,'qweqweq','1231231233','1231231231','qweqweqwe','qqweqweqweq','2023-06-23 22:03:03','2023-06-23 22:03:03','https://s2.loli.net/2023/06/25/jiVNFTU4kLytuwY.png','https://smms.app/delete/zsYW9v1N6Mnkg3TmEDLQtfyFZo','$2a$10$sDfuOb6vjNIVY0suhSkjDeA.n43/ThjPRvKsti3l0/JBAyw6e5vtG'),(10,'qweqwe','qweqwe','qweqwe','qweqwe','qweqwee','2023-06-23 22:13:16','2023-06-23 22:13:16','https://s2.loli.net/2023/06/25/SGowzT69uYEFbeI.png','https://smms.app/delete/YlyPoUn6MHb3GJNuFSeLO12Z78','$2a$10$XpLQItmyzJeJyTUkXqpdWezJT6FqoxYGDrTtXaL05m2hG0xLt43Ym'),(11,'qweqwehh','1231232','qweqwe3','qweqwe','qweqweew','2023-06-23 22:15:12','2023-06-23 22:15:12','https://s2.loli.net/2023/06/25/XTZD3jgL8h7IdKM.png','https://smms.app/delete/5Lz9usRbdrjfU7TGeNIPnZa2qS','$2a$10$O29tCC9AIVO8ac5DeIg/d.MT7SwowkOjHAAZ4e7IsK7FX0Y/BUtn.'),(13,'bkbknice','111111111','111111111','东北大学浑南校区','bkbknice1@qq.com','2023-06-23 22:48:34','2023-06-23 22:48:34','https://s2.loli.net/2023/06/25/Hct1sX4EmQbej2w.png','https://smms.app/delete/Anf3UZWxBLOVa1It24vQKy5huX','$2a$10$uIguOyaj7LUrFDfsPQiWg.gwtUkO0aHLPVx75iifoLrYhyRWXQm3K'),(14,'周通hhh','1111111111111','11111111111111','neuneu','neuneu','2023-06-23 23:45:18','2023-06-25 10:06:35','https://s2.loli.net/2023/06/25/ChAg2pGxXzkUu1O.png','https://smms.app/delete/CoLRI3j5YO6xBWT2EMemthQGFr','$2a$10$gz6BWp6YNzoRnFmyKs920OglLwjZyAEfesF2fdc2TV4Uwab.Mdwvu'),(21,'mj','123123245','12412324','shenyang','bkb2knice@qq.comr','2023-06-27 11:27:24','2023-06-27 11:27:24','https://s2.loli.net/2023/06/28/z2LhaMSFf67sxIO.png','https://smms.app/delete/sG1cU9WBz5toEfJiwT6KLQyP7j','$2a$10$BwA2pmSDS2.H5/Cq1ADYE.yATFUpsHthcB3PHFCXr3qI.HcNhBnrW'),(22,'奥尔加飞鸟','124124124124','121241241','山西晋中平遥','134141@gg.com','2023-06-27 12:06:02','2023-06-27 12:06:02','https://s2.loli.net/2023/06/28/4ETKk6dhMbCNIcZ.png','https://smms.app/delete/hdDfq8IRbaQiKugz7MFlvsNU2C','$2a$10$/Al3tyi.tle55sJ.BrHC1.rDge8/4P9B0IVb65KewL0FuOJTYdtdK'),(24,'1','123123123123','222223','22222','22222','2023-06-27 14:48:32','2023-06-28 09:28:10','https://s2.loli.net/2023/07/01/1UmJcAeOFN9tdPS.png','https://smms.app/delete/T2ac7o6BgRdQyiKDMCxWsbe8vn','$2a$10$0MgK8IQy1LoE6zjdoFUUtuZg/0xSaMj2.ryGk4DbWcCLfyhw2IXbG'),(25,'2','123123123123123','77777777777','地球123','邮箱@邮箱。邮箱','2023-06-27 15:27:22','2023-06-28 11:19:34','https://s2.loli.net/2023/06/27/vmHs7bDcp9t1FVW.png','https://smms.app/delete/vVwbYgCkPS8MW4rZ31FRHaXepG','$2a$10$hw8nj/P8fe0um5iXzgcCNea1Z0XpY5o8YKaXmxltYTgBsR8T7kDTi'),(26,'new','newnew','newnew','newnew','newnew','2023-06-28 14:10:09','2023-06-28 14:10:32','https://s2.loli.net/2023/06/28/EsKtjb4Gh1vY6WX.png',NULL,'$2a$10$S1N0Nh6r3FAiUb/27k27O.j.pGDFC3QHbEzt3cDCL9hYXpISJ0xVm'),(27,'hrx',NULL,NULL,NULL,NULL,'2023-07-03 09:37:30','2023-07-03 09:37:30','https://s2.loli.net/2023/07/03/WiywheSrJtxVM98.png','https://smms.app/delete/Evy9FHxYpiwQLsnbNBgSqPjZW7','$2a$10$4wSxVgYn6tpLACa6EvieE.VJregV3ormMDvKue4DtAYOuV/D2uQEq'),(28,'ljc',NULL,NULL,NULL,NULL,'2023-07-10 12:44:46','2023-07-10 12:44:46',NULL,NULL,'$2a$10$nOyhb.c7.EJ78tQl3RAYiu.OeeFTVGcvhXdiiUN.9dyy2XuvgHIRu');

/*Table structure for table `lds_cs` */

DROP TABLE IF EXISTS `lds_cs`;

CREATE TABLE `lds_cs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_cs` */

insert  into `lds_cs`(`id`,`name`,`password`,`createtime`,`updatetime`) values (1,'cs1','cs1','2023-06-25 14:33:36','2023-06-25 14:33:39'),(2,'cs2','cs2','2023-06-25 14:33:54','2023-06-25 14:33:58'),(3,'cs3','$2a$10$xC8NlA6wSmuSE7ThBBUbneT5OYkgbRuh1Xd6NtJbOseBLk4idIBQq',NULL,NULL),(4,'cs4','$2a$10$cEZhFGcwHjBrFAayncVVweLTdX0ejtv0v266kNZjx0OadLxUoKHRO','2023-06-27 09:58:09','2023-06-27 09:58:09');

/*Table structure for table `lds_deliveryman` */

DROP TABLE IF EXISTS `lds_deliveryman`;

CREATE TABLE `lds_deliveryman` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_deliveryman` */

insert  into `lds_deliveryman`(`id`,`name`,`phone`,`create_time`,`update_time`,`address`,`password`) values (1,'王美团','18923451234','2023-07-04 14:33:28','2023-07-04 14:33:31','山西太原','$2a$10$Js79QxXpFTOlD4VBhOz6Y.TRVZA52Xh3wxFUuiPWjh6ofZVAGrFHW'),(2,'李快递','17823456789','2023-07-04 14:33:51','2023-07-04 14:33:54','辽宁沈阳','$2a$10$jsRcmrBL7CfNYwr9.ZThg.TJhEAw7XHmDzdxVMEjJ1LuqQfiPMFN2');

/*Table structure for table `lds_dispatch_task_list` */

DROP TABLE IF EXISTS `lds_dispatch_task_list`;

CREATE TABLE `lds_dispatch_task_list` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `substation_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `deliveryman_id` int DEFAULT NULL,
  `deliveryman_name` varchar(128) DEFAULT NULL,
  `deliveryman_phone` varchar(128) DEFAULT NULL,
  `substation_name` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `allocation_id` int DEFAULT NULL,
  `status` int DEFAULT '0' COMMENT '0未到货1已到货2配送中3送达4收款',
  `client_name` varchar(128) DEFAULT NULL,
  `client_phone` varchar(128) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `money` double DEFAULT NULL COMMENT '收款金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_dispatch_task_list` */

insert  into `lds_dispatch_task_list`(`id`,`substation_id`,`order_id`,`deliveryman_id`,`deliveryman_name`,`deliveryman_phone`,`substation_name`,`create_time`,`update_time`,`allocation_id`,`status`,`client_name`,`client_phone`,`address`,`price`,`money`) values (1,1,20,1,'王美团','18923451234','山西分站','2023-07-06 20:42:07','2023-07-07 14:44:48',1,2,NULL,NULL,NULL,NULL,NULL),(2,2,19,NULL,NULL,NULL,'辽宁分站','2023-07-06 20:42:53','2023-07-06 20:42:53',2,0,NULL,NULL,NULL,NULL,NULL),(3,2,18,1,'王美团','18923451234','辽宁分站','2023-07-06 20:52:01','2023-07-08 16:32:44',5,2,NULL,NULL,NULL,NULL,NULL),(4,1,17,2,'李快递','17823456789','山西分站','2023-07-07 09:17:10','2023-07-07 23:55:40',7,2,NULL,NULL,NULL,NULL,NULL),(13,1,21,NULL,NULL,NULL,'山西分站','2023-07-07 15:01:38','2023-07-07 15:01:38',16,0,NULL,NULL,NULL,NULL,NULL),(14,1,22,1,'王美团','18923451234','山西分站','2023-07-08 00:07:11','2023-07-08 00:07:51',17,2,NULL,NULL,NULL,NULL,NULL),(15,2,14,2,'李快递','17823456789','辽宁分站','2023-07-08 16:39:55','2023-07-08 16:46:11',18,2,NULL,NULL,NULL,NULL,NULL),(16,1,31,1,'王美团','18923451234','山西分站','2023-07-10 12:45:44','2023-07-11 11:52:20',19,4,NULL,NULL,NULL,NULL,9999),(17,1,33,NULL,NULL,NULL,'山西分站','2023-07-11 10:08:09','2023-07-11 10:08:09',20,0,NULL,NULL,NULL,NULL,NULL),(18,1,37,1,'王美团','18923451234','山西分站','2023-07-11 13:23:03','2023-07-11 13:25:24',21,4,'1','18404981127','山西晋中平遥',4500,4500),(19,1,38,1,'王美团','18923451234','山西分站','2023-07-11 13:39:05','2023-07-11 13:39:37',22,4,'1','123123','山西阿瓦达',10000,10000),(20,1,39,1,'王美团','18923451234','山西分站','2023-07-11 13:41:55','2023-07-11 13:42:20',23,4,'1','134355','山西123啊达瓦',12000,12000),(21,1,40,1,'王美团','18923451234','山西分站','2023-07-11 13:44:38','2023-07-11 13:45:13',24,4,'1','12312414','山西',20,20),(22,1,41,1,'王美团','18923451234','山西分站','2023-07-11 13:58:12','2023-07-11 13:58:36',25,4,'2','123123','山西相似性',222,222),(23,1,42,1,'王美团','18923451234','山西分站','2023-07-11 14:59:10','2023-07-11 14:59:37',26,4,'1','12314431','山西',6000,6000),(24,1,43,1,'王美团','18923451234','山西分站','2023-07-11 15:03:16','2023-07-11 15:04:26',27,4,'2','23345345','shanxi',12000,12000);

/*Table structure for table `lds_inventory` */

DROP TABLE IF EXISTS `lds_inventory`;

CREATE TABLE `lds_inventory` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `quantity` int unsigned DEFAULT NULL,
  `max_quantity` int DEFAULT NULL,
  `forewarning` int DEFAULT NULL,
  `assigned` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_inventory` */

insert  into `lds_inventory`(`id`,`product_id`,`quantity`,`max_quantity`,`forewarning`,`assigned`,`create_time`,`update_time`) values (1,1,22,9999,100,6,NULL,NULL),(2,2,994,9999,100,5,NULL,NULL),(3,3,990,9999,100,9,NULL,NULL),(4,4,996,9999,100,3,NULL,NULL),(5,5,990,NULL,NULL,9,NULL,NULL),(6,6,996,NULL,NULL,3,NULL,NULL),(7,7,994,NULL,NULL,5,NULL,NULL),(8,8,994,NULL,NULL,5,NULL,NULL),(9,9,996,NULL,NULL,3,NULL,NULL),(10,10,996,NULL,NULL,3,NULL,NULL),(11,11,996,NULL,NULL,3,NULL,NULL),(12,12,996,NULL,NULL,3,NULL,NULL),(13,13,996,NULL,NULL,3,NULL,NULL),(14,14,4,NULL,NULL,0,'2023-07-11 23:01:36','2023-07-11 23:01:36'),(15,15,0,NULL,NULL,0,'2023-07-11 23:08:00','2023-07-11 23:08:00'),(16,16,0,NULL,NULL,0,'2023-07-11 23:15:42','2023-07-11 23:15:42');

/*Table structure for table `lds_product` */

DROP TABLE IF EXISTS `lds_product`;

CREATE TABLE `lds_product` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `name` varchar(128) NOT NULL,
  `main_image` varchar(500) DEFAULT NULL,
  `sub_images` text,
  `sub_title` varchar(200) DEFAULT NULL,
  `detail` text,
  `price` decimal(20,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `status` tinyint DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_product` */

insert  into `lds_product`(`id`,`category_id`,`name`,`main_image`,`sub_images`,`sub_title`,`detail`,`price`,`stock`,`status`,`create_time`,`update_time`) values (1,13,'一加8手机','https://s2.loli.net/2023/07/01/98NWCZsMBx3uUrP.webp','https://s2.loli.net/2023/07/01/98NWCZsMBx3uUrP.webp,https://s2.loli.net/2023/07/01/btINToYKPrGyHnf.webp','一加手机，256G+12G,黑色','OnePlus 8是一加科技旗下的手机产品，于2020年4月14日在海外线上发布，于2020年4月16日在中国大陆发布。 [1] [7]\r\nOnePlus 8的正面是一块6.55英寸的90Hz刷新率AMOLED屏幕，支持sRGB、Display P3色域，高度160.2毫米，宽度72.9毫米，厚度8.0毫米，重量180克，提供“青空”、“银翼”和“黑镜”三色可选。 [2]\r\nOnePlus 8搭载骁龙865处理器，内置4300mAh电池，支持30W快充，搭载氢OS系统。 [3]','1500.00',10000,1,'2023-06-29 10:37:12','2023-06-29 10:37:14'),(2,13,'APPLE:15','https://s2.loli.net/2023/07/01/Ec9CAwDi32oFsv4.webp',NULL,'苹果15花为爱国版',NULL,'5000.00',9999,1,'2023-06-29 10:39:58','2023-06-29 10:40:01'),(3,14,'机械革命','https://s2.loli.net/2023/07/01/RoPGVkWij2MLQT4.webp',NULL,'机械革命深海幽灵',NULL,'6000.00',111,1,'2023-06-29 10:42:16','2023-06-29 10:42:20'),(4,14,'macbook','https://s2.loli.net/2023/07/01/J4iX6PBmNVOvf19.webp','awdawd','macbookair','awdawd','9999.00',1111,1,'2023-06-29 10:43:02','2023-06-29 10:43:05'),(5,17,'香蕉','https://s2.loli.net/2023/07/01/XGQ4UcFkHMCodJ6.webp',NULL,'沈阳香蕉1kg',NULL,'10.00',100,1,'2023-06-29 10:44:41','2023-06-29 10:44:43'),(6,17,'西瓜','https://s2.loli.net/2023/07/01/btINToYKPrGyHnf.webp',NULL,'东北西瓜10kg',NULL,'30.00',443,1,'2023-06-29 10:45:25','2023-06-29 10:45:28'),(7,11,'牛仔裤','https://s2.loli.net/2023/07/01/5OvSZ9I2TwPcxj7.webp',NULL,'男牛仔裤',NULL,'111.00',222,1,'2023-06-29 10:47:47','2023-06-29 10:47:50'),(8,21,'小米电视55寸','https://s2.loli.net/2023/07/01/I2Pt7bJwoxD8X1u.webp',NULL,'小米点使',NULL,'3000.00',11111,1,'2023-06-29 10:48:52','2023-06-29 10:48:55'),(9,21,'华为电视66寸','https://s2.loli.net/2023/07/01/TgNSrdIFMsoHLBQ.webp',NULL,'华为电视',NULL,'11111.00',22222,1,'2023-06-29 10:49:31','2023-06-29 10:49:34'),(10,18,'雪糕','https://s2.loli.net/2023/07/01/zomMVT3SfXJxcPu.webp',NULL,'雪高一箱',NULL,'333.00',222,1,'2023-06-30 13:30:24','2023-06-30 13:30:28'),(11,18,'面包','https://s2.loli.net/2023/07/01/oglVU3e9Mbiy48J.webp',NULL,'阿瓦达面包1箱',NULL,'33.00',555,1,'2023-06-30 13:31:38','2023-06-30 13:31:43'),(12,12,'男运动鞋','https://s2.loli.net/2023/07/01/VpvPreIBiRWaYZN.webp',NULL,'男运动鞋白色',NULL,'444.00',122,1,'2023-06-30 13:32:52','2023-06-30 13:32:55'),(13,12,'男拖鞋','https://s2.loli.net/2023/07/01/ZiTlEHmgwdRQ2eX.webp',NULL,'男拖鞋蓝色',NULL,'33.00',2000,1,'2023-06-30 13:33:33','2023-06-30 13:33:36'),(14,9,'女外套','https://s2.loli.net/2023/07/11/ruJaMLzecjdR7nU.png','https://s2.loli.net/2023/07/11/LNEoitS4xeb9WB3.png,https://s2.loli.net/2023/07/11/NusWMJQXdaiBlmr.png,','女外套','女外套','122.00',0,1,'2023-07-11 23:01:36','2023-07-11 23:01:36'),(15,10,'女运动鞋','https://s2.loli.net/2023/07/11/pE2xDSOC5TlsdM3.png','https://s2.loli.net/2023/07/11/rERuMXBcl6z3T1S.png,https://s2.loli.net/2023/07/11/vxXIyrPbpgMqfoJ.png,','女运动鞋','女运动鞋','122.00',0,1,'2023-07-11 23:08:00','2023-07-11 23:08:00'),(16,15,'床','https://s2.loli.net/2023/07/11/y4uYIme7EgDPbzB.png','https://s2.loli.net/2023/07/11/F4UCPfOJh6BMT2e.png,https://s2.loli.net/2023/07/11/xKJow1Mk5uZBRqr.png,','床','床','12222.00',0,1,'2023-07-11 23:15:42','2023-07-11 23:15:42');

/*Table structure for table `lds_product_category` */

DROP TABLE IF EXISTS `lds_product_category`;

CREATE TABLE `lds_product_category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int unsigned NOT NULL COMMENT '0为一级目录',
  `name` varchar(128) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `order` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_product_category` */

insert  into `lds_product_category`(`id`,`parent_id`,`name`,`status`,`order`,`create_time`,`update_time`) values (1,0,'女装',NULL,NULL,'2023-06-29 10:20:56','2023-06-29 10:21:00'),(2,0,'男装',NULL,NULL,'2023-06-29 10:21:16','2023-06-29 10:21:19'),(4,0,'家装',NULL,NULL,'2023-06-29 10:22:50','2023-06-29 10:23:00'),(5,0,'食品',NULL,NULL,'2023-06-29 10:22:52','2023-06-29 10:23:02'),(6,0,'医药',NULL,NULL,'2023-06-29 10:22:55','2023-06-29 10:23:05'),(7,0,'电器',NULL,NULL,'2023-06-22 10:24:22','2023-06-29 10:22:40'),(8,0,'电子产品',NULL,NULL,'2023-06-23 10:24:26','2023-06-29 10:22:45'),(9,1,'女装外套',NULL,NULL,'2023-06-22 10:26:53','2023-06-29 10:26:58'),(10,1,'女鞋',NULL,NULL,'2023-07-07 10:27:18','2023-06-29 10:27:22'),(11,2,'男裤',NULL,NULL,'2023-06-29 10:28:26','2023-06-29 10:28:29'),(12,2,'男鞋',NULL,NULL,'2023-06-29 10:28:50','2023-06-29 10:28:53'),(13,8,'手机',NULL,NULL,'2023-06-29 10:49:47','2023-06-29 10:30:09'),(14,8,'电脑',NULL,NULL,'2023-06-29 10:30:35','2023-06-29 10:30:38'),(15,4,'床',NULL,NULL,'2023-06-29 10:31:01','2023-06-29 10:31:03'),(16,4,'沙发',NULL,NULL,'2023-06-29 10:31:14','2023-06-29 10:31:18'),(17,5,'水果',NULL,NULL,'2023-06-29 10:31:32','2023-06-29 10:31:35'),(18,5,'零食',NULL,NULL,'2023-06-29 10:31:45','2023-06-29 10:31:48'),(19,6,'保健',NULL,NULL,'2023-06-29 10:32:22','2023-06-29 10:32:25'),(20,7,'冰箱',NULL,NULL,'2023-06-29 10:32:47','2023-06-29 10:32:50'),(21,7,'电视',NULL,NULL,'2023-06-29 10:33:00','2023-06-29 10:33:03');

/*Table structure for table `lds_substation` */

DROP TABLE IF EXISTS `lds_substation`;

CREATE TABLE `lds_substation` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lds_substation` */

insert  into `lds_substation`(`id`,`name`,`address`,`phone`,`create_time`,`update_time`,`password`) values (1,'山西分站','山西','3423513','2023-07-06 20:17:13','2023-07-06 19:25:50','$2a$10$UhJEcAqQxh04O8uhIImiFO7Ja.nEW4OpnxDuJF5uCaZ.c74zYi2vS'),(2,'辽宁分站','辽宁','1244144','2023-07-06 20:17:15','2023-07-06 19:25:54','$2a$10$J1b4K5HmAwPRkx3ANbLlmOsqzN5bbvj8DruFum.epC9zeOOwLKmFC'),(3,'北京分站','北京','123124','2023-07-07 11:57:04','2023-07-07 11:57:04','$2a$10$VUqVJEsp/atVCCziJwT2POtvXUhSoKlMesjUJzyPkBLBdZD0eNpc6');

/*Table structure for table `shop_order` */

DROP TABLE IF EXISTS `shop_order`;

CREATE TABLE `shop_order` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `client_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  `product_name` varchar(128) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `client_name` varchar(128) DEFAULT NULL,
  `time` datetime DEFAULT NULL COMMENT '送货时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` tinyint DEFAULT NULL COMMENT '订单类型：1普通配送订单、2异地收款订单、3换货单、4退货单',
  `measurement` varchar(64) DEFAULT NULL COMMENT '计量单位',
  `status` int DEFAULT NULL COMMENT '0等待调度1缺货2配送中3完成',
  `main_image` varchar(128) DEFAULT NULL,
  `substation_id` int DEFAULT NULL,
  `substation_name` varchar(128) DEFAULT NULL,
  `task_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `shop_order` */

insert  into `shop_order`(`id`,`client_id`,`product_id`,`num`,`product_name`,`price`,`phone`,`address`,`remark`,`client_name`,`time`,`create_time`,`update_time`,`type`,`measurement`,`status`,`main_image`,`substation_id`,`substation_name`,`task_id`) values (14,24,4,2,'macbook',9999,'1234','neu',NULL,'1',NULL,'2023-07-03 10:32:23','2023-07-08 16:39:55',NULL,NULL,2,'https://s2.loli.net/2023/07/01/J4iX6PBmNVOvf19.webp',2,'辽宁分站',15),(15,24,8,2,'小米电视55寸',3000,'12314','china',NULL,'1',NULL,'2023-07-03 10:33:05','2023-07-03 10:33:05',NULL,NULL,0,'https://s2.loli.net/2023/07/01/I2Pt7bJwoxD8X1u.webp',NULL,NULL,NULL),(16,24,11,1,'面包',33,'12312312123a','213123',NULL,'1',NULL,'2023-07-03 10:35:51','2023-07-03 10:35:51',NULL,NULL,0,'https://s2.loli.net/2023/07/01/oglVU3e9Mbiy48J.webp',NULL,NULL,NULL),(17,24,13,1,'男拖鞋',33,'123123123','123',NULL,'1',NULL,'2023-07-03 10:36:16','2023-07-07 09:17:10',NULL,NULL,2,'https://s2.loli.net/2023/07/01/ZiTlEHmgwdRQ2eX.webp',1,'山西分站',4),(18,24,5,1,'香蕉',10,'123123123','123123',NULL,'1',NULL,'2023-07-03 10:38:27','2023-07-06 20:52:01',NULL,NULL,2,'https://s2.loli.net/2023/07/01/XGQ4UcFkHMCodJ6.webp',2,'辽宁分站',3),(19,27,9,1,'华为电视66寸',11111,'12312312','123',NULL,'hrx',NULL,'2023-07-03 10:40:53','2023-07-06 20:42:53',NULL,NULL,2,'https://s2.loli.net/2023/07/01/TgNSrdIFMsoHLBQ.webp',2,'辽宁分站',2),(20,27,10,1,'雪糕',333,'123','123',NULL,'hrx',NULL,'2023-07-03 10:41:12','2023-07-06 20:42:07',NULL,NULL,2,'https://s2.loli.net/2023/07/01/zomMVT3SfXJxcPu.webp',1,'山西分站',1),(21,14,2,1,'APPLE:15',5000,'123123','河南',NULL,'周通hhh',NULL,'2023-07-07 14:58:23','2023-07-07 15:01:38',NULL,NULL,2,'https://s2.loli.net/2023/07/01/Ec9CAwDi32oFsv4.webp',1,'山西分站',13),(22,25,12,1,'男运动鞋',444,'13456788765','山西晋中xxxx',NULL,'2',NULL,'2023-07-08 00:06:45','2023-07-08 00:07:11',NULL,NULL,2,'https://s2.loli.net/2023/07/01/VpvPreIBiRWaYZN.webp',1,'山西分站',14),(23,24,2,1,'APPLE:15',5000,'123123123','辽宁东北大学浑南',NULL,'1',NULL,'2023-07-10 11:05:29','2023-07-10 11:05:29',NULL,NULL,0,'https://s2.loli.net/2023/07/01/Ec9CAwDi32oFsv4.webp',NULL,NULL,NULL),(24,24,5,1,'香蕉',10,'11','辽宁',NULL,'1',NULL,'2023-07-10 11:09:42','2023-07-10 11:09:42',NULL,NULL,0,'https://s2.loli.net/2023/07/01/XGQ4UcFkHMCodJ6.webp',NULL,NULL,NULL),(25,24,10,1,'雪糕',333,'123','北京',NULL,'1',NULL,'2023-07-10 11:12:48','2023-07-10 11:12:48',NULL,NULL,0,'https://s2.loli.net/2023/07/01/zomMVT3SfXJxcPu.webp',NULL,NULL,NULL),(26,24,3,1,'机械革命',6000,'123','123',NULL,'1',NULL,'2023-07-10 11:13:18','2023-07-10 11:13:18',NULL,NULL,0,'https://s2.loli.net/2023/07/01/RoPGVkWij2MLQT4.webp',NULL,NULL,NULL),(27,25,8,1,'小米电视55寸',3000,'12314235','beijing',NULL,'2',NULL,'2023-07-10 11:15:09','2023-07-10 11:15:09',NULL,NULL,0,'https://s2.loli.net/2023/07/01/I2Pt7bJwoxD8X1u.webp',NULL,NULL,NULL),(28,25,1,1,'一加8手机',1500,'21314','北京',NULL,'2',NULL,'2023-07-10 11:43:24','2023-07-10 11:43:24',NULL,NULL,0,'https://s2.loli.net/2023/07/01/98NWCZsMBx3uUrP.webp',NULL,NULL,NULL),(29,25,6,1,'西瓜',30,'123','sx',NULL,'2',NULL,'2023-07-10 11:45:25','2023-07-10 11:45:25',NULL,NULL,0,'https://s2.loli.net/2023/07/01/btINToYKPrGyHnf.webp',NULL,NULL,NULL),(30,24,12,1,'男运动鞋',444,'13454545','sx',NULL,'1',NULL,'2023-07-10 12:11:51','2023-07-10 12:11:51',NULL,NULL,0,'https://s2.loli.net/2023/07/01/VpvPreIBiRWaYZN.webp',NULL,NULL,NULL),(31,28,4,1,'macbook',9999,'134234','sx',NULL,'ljc',NULL,'2023-07-10 12:45:16','2023-07-10 12:45:44',NULL,NULL,3,'https://s2.loli.net/2023/07/01/J4iX6PBmNVOvf19.webp',1,'山西分站',16),(32,24,1,3,'一加8手机',1500,'123','123','xixi','1','2023-07-13 00:00:00','2023-07-10 15:16:49','2023-07-10 15:16:49',0,NULL,0,'https://s2.loli.net/2023/07/01/98NWCZsMBx3uUrP.webp',NULL,NULL,NULL),(33,28,5,4,'香蕉',10,'1231231','neuxxm','neuxxm','ljc','2023-07-12 00:00:00','2023-07-11 09:39:33','2023-07-11 10:08:09',0,NULL,2,'https://s2.loli.net/2023/07/01/XGQ4UcFkHMCodJ6.webp',1,'山西分站',17),(34,28,4,3,'macbook',9999,'123321','山西晋中','','ljc','2023-07-19 00:00:00','2023-07-11 09:44:42','2023-07-11 09:44:42',0,NULL,0,'https://s2.loli.net/2023/07/01/J4iX6PBmNVOvf19.webp',NULL,NULL,NULL),(35,28,3,2,'机械革命',6000,'123','北京','remake','ljc','2023-07-19 00:00:00','2023-07-11 09:47:22','2023-07-11 09:47:22',0,NULL,0,'https://s2.loli.net/2023/07/01/RoPGVkWij2MLQT4.webp',NULL,NULL,NULL),(36,28,1,4,'一加8手机',1500,'11134534','beijing23','','ljc','2023-07-12 00:00:00','2023-07-11 09:48:23','2023-07-11 09:48:23',0,NULL,1,'https://s2.loli.net/2023/07/01/98NWCZsMBx3uUrP.webp',NULL,NULL,NULL),(37,24,1,3,'一加8手机',1500,'18404981127','山西晋中平遥','呃呃','1','2023-07-12 00:00:00','2023-07-11 13:22:01','2023-07-11 13:23:03',0,NULL,3,'https://s2.loli.net/2023/07/01/98NWCZsMBx3uUrP.webp',1,'山西分站',18),(38,24,2,2,'APPLE:15',5000,'123123','山西阿瓦达','123134','1','2023-07-12 00:00:00','2023-07-11 13:35:57','2023-07-11 13:39:05',0,NULL,3,'https://s2.loli.net/2023/07/01/Ec9CAwDi32oFsv4.webp',1,'山西分站',19),(39,24,3,2,'机械革命',6000,'134355','山西123啊达瓦','阿瓦达','1','2023-07-12 00:00:00','2023-07-11 13:41:30','2023-07-11 13:41:55',0,NULL,3,'https://s2.loli.net/2023/07/01/RoPGVkWij2MLQT4.webp',1,'山西分站',20),(40,24,5,2,'香蕉',10,'12312414','山西','123123','1','2023-07-12 00:00:00','2023-07-11 13:44:24','2023-07-11 13:44:38',0,NULL,3,'https://s2.loli.net/2023/07/01/XGQ4UcFkHMCodJ6.webp',1,'山西分站',21),(41,25,7,2,'牛仔裤',111,'123123','山西相似性','阿文为为我','2','2023-07-20 00:00:00','2023-07-11 13:57:40','2023-07-11 13:58:12',0,NULL,3,'https://s2.loli.net/2023/07/01/5OvSZ9I2TwPcxj7.webp',1,'山西分站',22),(42,24,8,2,'小米电视55寸',3000,'12314431','山西','赫赫','1','2023-07-12 00:00:00','2023-07-11 14:58:32','2023-07-11 14:59:10',0,NULL,3,'https://s2.loli.net/2023/07/01/I2Pt7bJwoxD8X1u.webp',1,'山西分站',23),(43,25,3,2,'机械革命',6000,'23345345','shanxi','yctf','2','2023-07-12 00:00:00','2023-07-11 15:02:42','2023-07-11 15:03:16',0,NULL,3,'https://s2.loli.net/2023/07/01/RoPGVkWij2MLQT4.webp',1,'山西分站',24);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
