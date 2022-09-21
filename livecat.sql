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

 Date: 21/09/2022 19:06:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
-- Table structure for livecat_order
-- ----------------------------
DROP TABLE IF EXISTS `livecat_order`;
CREATE TABLE `livecat_order`  (
                                  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  `ticket_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `ticket_count` bigint(20) NULL DEFAULT NULL,
                                  `event_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `total_price` decimal(10, 2) NULL DEFAULT NULL,
                                  `status` tinyint(4) NULL DEFAULT NULL,
                                  `create_time` datetime NULL DEFAULT NULL,
                                  `pay_expire_time` datetime NULL DEFAULT NULL,
                                  `pay_time` datetime NULL DEFAULT NULL,
                                  `delivery_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of livecat_order
-- ----------------------------

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
