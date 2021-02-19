/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 39.96.55.147:3306
 Source Schema         : word

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 19/02/2021 20:15:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '书名',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '书封面',
  `classify_id` int NULL DEFAULT NULL COMMENT '分类ID',
  `count` int NULL DEFAULT 0 COMMENT '单词数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '小学汇总词汇', 'https://media-image1.baydn.com/wordmaster_pub_image/qayvad/8f2ec54502a08adc5fbed277fa22939f.2403af2a14c952cf145383d5b4a1c9fd.png?x-oss-process=image/quality,Q_80', 7, 1000);

-- ----------------------------
-- Table structure for study_set
-- ----------------------------
DROP TABLE IF EXISTS `study_set`;
CREATE TABLE `study_set`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `new_word` int NOT NULL COMMENT '每日新词',
  `review_word` int NOT NULL COMMENT '每日复习',
  `start_time` date NOT NULL COMMENT '开始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  `day` date NOT NULL COMMENT '日期',
  `day_new_word` int NOT NULL COMMENT '今日应学',
  `day_review_word` int NOT NULL COMMENT '今日复习',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_set
-- ----------------------------
INSERT INTO `study_set` VALUES (2, 2, 2, '2021-02-19', '2022-07-04', '2021-02-19', 0, 0);
INSERT INTO `study_set` VALUES (3, 2, 2, '2021-02-19', '2022-07-04', '2021-02-19', 0, 0);
INSERT INTO `study_set` VALUES (4, 4, 4, '2021-02-19', '2021-10-27', '2021-02-19', 0, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) NULL DEFAULT 1,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_id` int NULL DEFAULT NULL COMMENT '正在学习单词书ID',
  `book_date` date NULL DEFAULT NULL COMMENT '开始学习时间',
  `book_day` int NOT NULL DEFAULT 0 COMMENT '学习天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'lhf', NULL, NULL, NULL, 1, 'lhf', '123456', NULL, NULL, 1, '2021-02-19', 0);
INSERT INTO `user` VALUES (3, '测试', NULL, NULL, NULL, 1, 'ceshi', '123456', NULL, NULL, 1, '2021-02-19', 0);
INSERT INTO `user` VALUES (4, '李行富', NULL, NULL, NULL, 1, 'lihangfu', '123456', NULL, NULL, 1, '2021-02-19', 0);

-- ----------------------------
-- Table structure for user_word
-- ----------------------------
DROP TABLE IF EXISTS `user_word`;
CREATE TABLE `user_word`  (
  `user_id` int NOT NULL,
  `word_id` int NOT NULL,
  `flag` int NOT NULL DEFAULT 0,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `word_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_word
-- ----------------------------
INSERT INTO `user_word` VALUES (2, 1, 1, '2021-02-19 08:05:14');
INSERT INTO `user_word` VALUES (2, 2, 1, '2021-02-19 08:05:21');
INSERT INTO `user_word` VALUES (2, 3, 0, '2021-02-19 08:05:08');
INSERT INTO `user_word` VALUES (2, 4, 0, '2021-02-19 08:05:08');
INSERT INTO `user_word` VALUES (3, 1, 1, '2021-02-19 08:58:28');
INSERT INTO `user_word` VALUES (3, 2, 1, '2021-02-19 08:58:31');
INSERT INTO `user_word` VALUES (3, 3, 0, '2021-02-19 08:56:45');
INSERT INTO `user_word` VALUES (3, 4, 0, '2021-02-19 08:56:45');
INSERT INTO `user_word` VALUES (4, 1, 1, '2021-02-19 09:01:06');
INSERT INTO `user_word` VALUES (4, 2, 1, '2021-02-19 09:01:15');
INSERT INTO `user_word` VALUES (4, 3, 1, '2021-02-19 09:01:11');
INSERT INTO `user_word` VALUES (4, 4, 1, '2021-02-19 09:01:17');

-- ----------------------------
-- Table structure for word
-- ----------------------------
DROP TABLE IF EXISTS `word`;
CREATE TABLE `word`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phonetic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `explain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `example` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of word
-- ----------------------------
INSERT INTO `word` VALUES (1, 'these', '/ðiːz/;/ðiːz/', ' adj. 这些;pron. 这些', 'These two are my friends.;这两个人是我朋友。;These are the days we won\'t forget.;这些日子我们不会忘却。', 1);
INSERT INTO `word` VALUES (2, 'bored', '/bɔːd/;/bɔːrd/', 'adj. 无聊的,感到厌倦的', 'He didn\'t notice how bored she was.;他没注意到她有多么腻烦。', 1);
INSERT INTO `word` VALUES (3, 'building', '/\'bɪldɪŋ/;/\'bɪldɪŋ/', 'n. 建筑物，楼房', 'The construction of this building took several months.;建造这栋建筑花了好几个月。', 1);
INSERT INTO `word` VALUES (4, 'bad', '/bæd/;/bæd/', 'adj. 坏的；严重的', 'I\'m having a really bad day.;我今天倒霉透了。', 1);

SET FOREIGN_KEY_CHECKS = 1;
