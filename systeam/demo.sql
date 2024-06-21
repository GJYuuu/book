/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 15/06/2024 14:40:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '12345678', '女', 21, '124538223452', '管理员', '1718354024764');
INSERT INTO `admin` VALUES (17, 'user2', '123456', '女', 20, '1286535677', '本科生', '1718368343374');
INSERT INTO `admin` VALUES (18, 'user1', '123456', '女', 23, '1234578', '研究生', 'avatar.jpg');
INSERT INTO `admin` VALUES (20, 'user3', '123456', '女', 30, '1345678832', '教师', 'avatar.jpg');
INSERT INTO `admin` VALUES (21, 'user4', '123456', NULL, NULL, NULL, '研究生', '1718353985434');

-- ----------------------------
-- Table structure for audit
-- ----------------------------
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请假原由',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请假日期',
  `day` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请假天数',
  `userId` int NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核状态',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audit
-- ----------------------------
INSERT INTO `audit` VALUES (1, 'aaaaaa', '2024-05-06', '3', 3, '审核通过', '同意');
INSERT INTO `audit` VALUES (2, 'aaaaaaaaaaaaaaaaaaaahjkhkjhkjhjhkjhjkhkjhkjhjhkjh', '2024-05-20', '6', 2, NULL, NULL);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '书名\r\n      ',
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `press` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版社',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `isbn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `typeId` int NULL DEFAULT NULL COMMENT '分类ID',
  `num` int NULL DEFAULT NULL COMMENT '剩余数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (3, 'python程序设计(第三版)', '69', 'John Zelle', '人民邮电出版社', '1718183216854', '9787115283252', 1, 3);
INSERT INTO `book` VALUES (6, '计算机科学技术百科全书（第三版）', '498', '张效祥', '清华大学出版社', '1718246221132', '9787302495727', 1, 5);
INSERT INTO `book` VALUES (7, '神经科学研究技术', '77.42', '[美]马特·卡特', '科学出版社', '1718248644159', '9787030293893', 1, 3);
INSERT INTO `book` VALUES (8, '自然科学史', '17.1', '詹姆斯·金斯', '中国大地出版社', '1718248769860', '9787802468320', 2, 1);
INSERT INTO `book` VALUES (9, '科学第一课', '36', '潘建伟', '科学出版社', '1718248929929', '9787030584915', 2, 7);
INSERT INTO `book` VALUES (10, '科学是什么', '49', '张天蓉', '清华大学出版社', '1718249305900', '9787302532941', 2, 4);
INSERT INTO `book` VALUES (11, '科学社会学', '27.6', '刘珺珺', '上海科学普及出版社', '1718249560958', '9787542847072', 3, 5);
INSERT INTO `book` VALUES (12, '城南旧事', '28', '林海音', '江苏文艺出版社', '1718249788126', '9787559411655', 4, 5);
INSERT INTO `book` VALUES (13, '月亮与六便士', '36', '[英] 毛姆', '中国华侨出版社', '1718250254491', '9787511379054', 4, 1);
INSERT INTO `book` VALUES (14, '中外美术鉴赏（第2版）', '56', '刘晨、徐改、赵亚华、张玉花、李妍', '清华大学出版社', '1718250376213', '9787302442721', 5, 4);
INSERT INTO `book` VALUES (15, '福尔摩斯探案', '11.3', '柯南道尔', '译林出版社', '1718369027680', '9787544768719', 4, 4);

-- ----------------------------
-- Table structure for imsingle
-- ----------------------------
DROP TABLE IF EXISTS `imsingle`;
CREATE TABLE `imsingle`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `fromuser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送人',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `touser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收信人',
  `readed` int NULL DEFAULT NULL COMMENT '收信人',
  `fromavatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送人',
  `toavatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送人',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '留言记录\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of imsingle
-- ----------------------------
INSERT INTO `imsingle` VALUES (66, '管理员_admin', '您好，您借阅的《python程序设计（第三版）》归还审核通过', '研究生_user4', 1, '1718354024764', '1718353985434', '2024-06-14 17:08:26');
INSERT INTO `imsingle` VALUES (67, '研究生_user4', '好的', '管理员_admin', 1, '1718353985434', '1718354024764', '2024-06-14 17:08:42');
INSERT INTO `imsingle` VALUES (70, '研究生_user1', '请问有福尔摩斯这本书吗', '管理员_admin', 1, 'avatar.jpg', '1718354024764', '2024-06-14 20:31:18');
INSERT INTO `imsingle` VALUES (71, '管理员_admin', '有的', '研究生_user1', 1, '1718354024764', 'avatar.jpg', '2024-06-14 20:31:27');
INSERT INTO `imsingle` VALUES (79, '研究生_user1', '11111', '管理员_admin', 1, 'avatar.jpg', '1718354024764', '2024-06-14 20:51:42');
INSERT INTO `imsingle` VALUES (80, '管理员_admin', '你好', '研究生_user1', 1, '1718354024764', 'avatar.jpg', '2024-06-15 14:22:37');
INSERT INTO `imsingle` VALUES (81, '研究生_user1', '你好', '管理员_admin', 1, 'avatar.jpg', '1718354024764', '2024-06-15 14:22:50');

-- ----------------------------
-- Table structure for lend
-- ----------------------------
DROP TABLE IF EXISTS `lend`;
CREATE TABLE `lend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `num` int NULL DEFAULT NULL COMMENT '可借阅数量',
  `day` int NULL DEFAULT NULL COMMENT '可借阅天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '借阅权限\r\n\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lend
-- ----------------------------
INSERT INTO `lend` VALUES (18, '本科生', 10, 30);
INSERT INTO `lend` VALUES (19, '教师', 20, 45);
INSERT INTO `lend` VALUES (20, '研究生', 15, 40);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类介绍',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 223 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '操作日志表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (192, '用户登录', '2024-06-14 19:15:36', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (193, '用户登录', '2024-06-14 19:43:16', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (194, '用户登录', '2024-06-14 19:45:12', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (195, '用户登录', '2024-06-14 19:46:09', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (196, '用户登录', '2024-06-14 19:48:01', 'user1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (197, '图书借阅', '2024-06-14 19:48:11', 'user1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (198, '图书借阅', '2024-06-14 19:48:13', 'user1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (199, '用户登录', '2024-06-14 20:32:07', 'user2', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (200, '用户登录', '2024-06-14 20:32:53', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (201, '用户登录', '2024-06-14 20:35:14', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (202, '用户登录', '2024-06-14 20:35:35', 'user3', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (203, '修改图书信息', '2024-06-14 20:44:31', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (204, '更新系统公告', '2024-06-14 20:47:19', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (205, '用户登录', '2024-06-14 20:48:06', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (206, '用户登录', '2024-06-14 20:48:49', 'user1', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES (207, '图书借阅', '2024-06-14 20:48:58', 'user1', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公告标题',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公告时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统公告\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (17, '欢迎使用', '2024-06-08 17:03:49', '欢迎使用图书管理系统!!');
INSERT INTO `notice` VALUES (18, '11111', '2024-06-08 16:50:09', '222222');
INSERT INTO `notice` VALUES (20, '软件设计实践', '2024-06-14 20:47:19', '图书管理系统');

-- ----------------------------
-- Table structure for private
-- ----------------------------
DROP TABLE IF EXISTS `private`;
CREATE TABLE `private`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公告标题',
  `userId` int NULL DEFAULT NULL COMMENT '收件人id',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公告时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '私信\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of private
-- ----------------------------
INSERT INTO `private` VALUES (21, '逾期提醒', 10, '2024-06-12 10:10:47', '您借阅的书已逾期，请尽快归还');

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookId` int NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '借阅时间',
  `retime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '到期时间',
  `back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '归还时间',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '借阅状态：已归还、借阅中、借阅审核中',
  `late` int NULL DEFAULT NULL COMMENT '逾期天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '借阅管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reserve
-- ----------------------------
INSERT INTO `reserve` VALUES (29, 3, 17, '2024-06-15 14:33:14', '2024-07-15 14:33:14', '2024-06-15 14:33:39', '已归还', 0);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '图书分类\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '技术科学类');
INSERT INTO `type` VALUES (2, '自然科学类');
INSERT INTO `type` VALUES (3, '社会生活类');
INSERT INTO `type` VALUES (4, '文学类');
INSERT INTO `type` VALUES (5, '艺术类');

SET FOREIGN_KEY_CHECKS = 1;
