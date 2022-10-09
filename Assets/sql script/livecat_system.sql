/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL
 Source Server Type    : MySQL
 Source Server Version : 50728 (5.7.28-log)
 Source Host           : localhost:3306
 Source Schema         : livecat_system

 Target Server Type    : MySQL
 Target Server Version : 50728 (5.7.28-log)
 File Encoding         : 65001

 Date: 09/10/2022 17:02:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for livecat_user
-- ----------------------------
DROP TABLE IF EXISTS `livecat_user`;
CREATE TABLE `livecat_user`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'user_id',
  `username` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'username',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'password, encryted, admin/1234',
  `is_account_non_expired` int(2) NULL DEFAULT 1 COMMENT '1 expire, 0 not expire',
  `is_account_non_locked` int(2) NULL DEFAULT 1 COMMENT '1 locked, 0 not locked',
  `is_credentials_non_expired` int(2) NULL DEFAULT 1 COMMENT '1 expire, 0 not expire',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '1 enable 0 unenable',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'nick_name',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mobile',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'email',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_date',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update_date',
  `pwd_update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'pwd_update_date',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `mobile`(`mobile`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'user info table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of livecat_user
-- ----------------------------
INSERT INTO `livecat_user` VALUES ('10', 'test', '$2a$10$uA51hWL5yusFBoEvZOAZbeaYYqUaFV7xjdDB8GA.4iViNiCSK9xKO', 1, 1, 1, 1, 'Test', '16888886666', 'test1@qq.com', '2023-08-08 11:11:11', '2020-05-22 15:05:57', '2020-04-10 09:41:51');
INSERT INTO `livecat_user` VALUES ('11', 'string', '$2a$10$uA51hWL5yusFBoEvZOAZbeaYYqUaFV7xjdDB8GA.4iViNiCSK9xKO', 0, 1, 0, 0, 'string', 'string', 'string', '2020-04-11 21:37:25', '2020-04-17 16:43:19', '2020-04-11 21:37:25');
INSERT INTO `livecat_user` VALUES ('1253583309139775489', 'root', '$2a$10$qci2y9rouzWgEP/injjeDeUAFbWIGP7wQILjqmDE61S1ZMwWqiGqi', 0, 1, 1, 1, 'root', '15888888888', NULL, '2020-04-24 15:15:26', '2020-07-25 09:16:36', '2020-04-24 15:15:26');
INSERT INTO `livecat_user` VALUES ('1578727434793062401', 'musicfan1', '$2a$10$EsyOB3ExsLDeBVOq6fA1/.2rpYjn8sziWk9wh.FKc9A7bnjfwOo6W', 1, 1, 1, 1, 'musicfan1nick', '88886666', '888@cc.com', '2022-10-08 20:42:06', '2022-10-08 20:42:06', '2022-10-08 20:42:06');
INSERT INTO `livecat_user` VALUES ('1579003631477309442', 'david', '$2a$10$QVHqLdTFuedmAdD1qxcFIuStSkGokqa9zwF1chJ3C6lynlrx/Kaxe', 1, 1, 1, 1, 'musicfan2nick', '88886668', '8887@cc.com', '2022-10-09 14:59:37', '2022-10-09 14:59:37', '2022-10-09 14:59:37');
INSERT INTO `livecat_user` VALUES ('1579030788882632706', 'david6', '$2a$10$3V9i4Mux4v.U4fihIqGNP.gPjHHaT3hA8LaNsFXoyrvY70fUqPW/S', 1, 1, 1, 1, 'musicfan2nick', '88886665', '73887@cc.com', '2022-10-09 16:47:32', '2022-10-09 16:47:32', '2022-10-09 16:47:32');
INSERT INTO `livecat_user` VALUES ('9', 'admin', '$2a$10$2c6uqCzY3ObyCBU7WnY/AORFVU6ZAR.JfUnsogxX3ixgsszCJeiWW', 1, 1, 1, 1, 'Livedog', '18888888888', 'mengxuegu888@163.com', '2023-08-08 11:11:11', '2020-05-22 22:30:14', '2020-04-10 09:41:51');

SET FOREIGN_KEY_CHECKS = 1;
