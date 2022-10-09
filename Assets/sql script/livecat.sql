/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL
 Source Server Type    : MySQL
 Source Server Version : 50728 (5.7.28-log)
 Source Host           : localhost:3306
 Source Schema         : livecat

 Target Server Type    : MySQL
 Target Server Version : 50728 (5.7.28-log)
 File Encoding         : 65001

 Date: 09/10/2022 17:01:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user`  (
  `id` bigint(20) NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `head` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `register_date` datetime NULL DEFAULT NULL,
  `last_login_date` datetime NULL DEFAULT NULL,
  `login_count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (13111897391, 'Leo', '5e7b3a9754c2777f96174d4ccb980d23', '1a2b3c4d', NULL, '2022-10-04 20:16:40', '2022-10-04 20:16:45', NULL);

-- ----------------------------
-- Table structure for livecat_event
-- ----------------------------
DROP TABLE IF EXISTS `livecat_event`;
CREATE TABLE `livecat_event`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `html_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `event_start_time` datetime NULL DEFAULT NULL,
  `event_end_time` datetime NULL DEFAULT NULL,
  `venue` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint(3) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `provider_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of livecat_event
-- ----------------------------
INSERT INTO `livecat_event` VALUES ('1244940138650423298', 'Maroon 5 World Tour 2022', 'string', 'string', 'string', '2022-10-28 20:00:00', '2022-10-28 20:00:00', 'National Stadium', 'Singapore', 'band', 1, '2022-09-19 15:56:32', 'pid0001');
INSERT INTO `livecat_event` VALUES ('2222', 'Maroon 2', 'string', 'string', 'string', '2022-10-28 20:00:00', '2022-10-28 20:00:00', 'National Stadium', 'Singapore', 'band', 1, '2022-09-19 15:56:32', 'pid0001');
INSERT INTO `livecat_event` VALUES ('3333', 'Maroon 3', 'string', 'string', 'string', '2022-10-28 20:00:00', '2022-10-28 20:00:00', 'National Stadium', 'Singapore', 'band', 1, '2022-09-19 15:56:32', 'pid0001');
INSERT INTO `livecat_event` VALUES ('4444', 'Maroon 4', 'string', 'string', 'string', '2022-10-28 20:00:00', '2022-10-28 20:00:00', 'National Stadium', 'Singapore', 'band', 1, '2022-09-19 15:56:32', 'pid0001');
INSERT INTO `livecat_event` VALUES ('5555', 'Maroon 5', 'string', 'string', 'string', '2022-10-28 20:00:00', '2022-10-28 20:00:00', 'National Stadium', 'Singapore', 'band', 1, '2022-09-19 15:56:32', 'pid0001');
INSERT INTO `livecat_event` VALUES ('6666', 'Maroon 6', 'string', 'string', 'string', '2022-10-28 20:00:00', '2022-10-28 20:00:00', 'National Stadium', 'Singapore', 'band', 1, '2022-09-19 15:56:32', 'pid0001');
INSERT INTO `livecat_event` VALUES ('7777', 'Maroon 7', 'string', 'string', 'string', '2022-10-28 20:00:00', '2022-10-28 20:00:00', 'National Stadium', 'Singapore', 'band', 1, '2022-09-19 15:56:32', 'pid0001');

-- ----------------------------
-- Table structure for livecat_ticket
-- ----------------------------
DROP TABLE IF EXISTS `livecat_ticket`;
CREATE TABLE `livecat_ticket`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `stock_count` bigint(20) NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_selling_date` datetime NULL DEFAULT NULL,
  `end_selling_date` datetime NULL DEFAULT NULL,
  `event_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of livecat_ticket
-- ----------------------------
INSERT INTO `livecat_ticket` VALUES ('1', 'CAT 1 GENERAL STANDING', 'CAT 1 GENERAL STANDING', 278.00, 10, NULL, '2022-09-01 00:00:00', '2022-11-30 00:00:00', '1244940138650423298');
INSERT INTO `livecat_ticket` VALUES ('2', 'CAT 2 GENERAL STANDING', 'CAT 1 GENERAL CAT 2 GENERAL STANDING', 198.00, 10, NULL, '2022-09-01 00:00:00', '2022-11-30 00:00:00', '1244940138650423298');
INSERT INTO `livecat_ticket` VALUES ('3', 'CAT 3', 'CAT 3', 278.00, 30, NULL, '2022-09-01 00:00:00', '2022-11-30 00:00:00', '1244940138650423298');
INSERT INTO `livecat_ticket` VALUES ('4', 'CAT 6', 'CAT 6', 168.00, 30, NULL, '2022-09-01 00:00:00', '2022-11-30 00:00:00', '1244940138650423298');

SET FOREIGN_KEY_CHECKS = 1;
