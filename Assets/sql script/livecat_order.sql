/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL
 Source Server Type    : MySQL
 Source Server Version : 50728 (5.7.28-log)
 Source Host           : localhost:3306
 Source Schema         : livecat_order

 Target Server Type    : MySQL
 Target Server Version : 50728 (5.7.28-log)
 File Encoding         : 65001

 Date: 09/10/2022 17:02:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `customer_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `pay_expire_time` datetime NULL DEFAULT NULL,
  `pay_time` datetime NULL DEFAULT NULL,
  `payment_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `payment_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delivery_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of livecat_order
-- ----------------------------
INSERT INTO `livecat_order` VALUES ('1578956996609056769', '2', 1, '1244940138650423298', 198.00, '9', 2, '2022-10-09 11:54:17', '2022-10-09 11:54:35', '2022-10-09 15:49:24', '/order/mock-payment/3656a0ff04ab4ff092360ac9743fc16a', '3656a0ff04ab4ff092360ac9743fc16a', 'test@test.com', 'No.1 test street', '88889999');
INSERT INTO `livecat_order` VALUES ('1578963533591224322', '2', 1, '1244940138650423298', 198.00, '9', 1, '2022-10-09 12:20:17', '2022-10-09 12:50:17', NULL, '/order/mock-payment/275e6b7251834d32b08a1b9f7a053a10', '275e6b7251834d32b08a1b9f7a053a10', 'test@test.com', 'No.1 test street', '88889999');
INSERT INTO `livecat_order` VALUES ('1579012582855962625', '2', 1, '1244940138650423298', 198.00, '9', 1, '2022-10-09 15:35:10', '2022-10-09 16:05:10', NULL, '/order/mock-payment/6efb56a55b684cc9af9b5e05825f988f', '6efb56a55b684cc9af9b5e05825f988f', 'test@test.com', 'No.1 test street', '88889999');
INSERT INTO `livecat_order` VALUES ('1579013536980430850', '2', 1, '1244940138650423298', 198.00, '9', 1, '2022-10-09 15:38:59', '2022-10-09 16:08:59', NULL, '/order/mock-payment/c095e27c7c234ee5af04c3a389bd0e29', 'c095e27c7c234ee5af04c3a389bd0e29', 'test@test.com', 'No.1 test street', '88889999');
INSERT INTO `livecat_order` VALUES ('1579015256632487937', '2', 1, '1244940138650423298', 198.00, '9', 1, '2022-10-09 15:45:48', '2022-10-09 16:15:48', NULL, '/order/mock-payment/8d744f630bc0495faab516410b5bc5a8', '8d744f630bc0495faab516410b5bc5a8', 'test@test.com', 'No.1 test street', '88889999');
INSERT INTO `livecat_order` VALUES ('5555', '2', 1, '1244940138650423298', 198.00, '9', 1, '2022-10-08 22:38:50', '2022-10-08 23:38:54', NULL, 'https://www.visa.com.sg/', '12345678', 'ccc@qq.com', 'No 1 address', '23452345');

SET FOREIGN_KEY_CHECKS = 1;
