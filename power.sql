/*
 Navicat Premium Data Transfer

 Source Server         : yt
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : power

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 23/02/2023 21:45:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_car
-- ----------------------------
DROP TABLE IF EXISTS `sys_car`;
CREATE TABLE `sys_car`  (
  `car_id` int NOT NULL AUTO_INCREMENT COMMENT '汽车id',
  `car_brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '汽车品牌',
  `car_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '汽车型号',
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_car
-- ----------------------------
INSERT INTO `sys_car` VALUES (2, '宝马', 'X2');
INSERT INTO `sys_car` VALUES (3, '宝马', 'X3');
INSERT INTO `sys_car` VALUES (4, '奔驰', 'C1');
INSERT INTO `sys_car` VALUES (5, '奔驰', 'C2');
INSERT INTO `sys_car` VALUES (6, '比亚迪', '秦');
INSERT INTO `sys_car` VALUES (7, '比亚迪', '唐');
INSERT INTO `sys_car` VALUES (8, '特斯拉', 'modelY');
INSERT INTO `sys_car` VALUES (9, '特斯拉', 'model3');
INSERT INTO `sys_car` VALUES (14, '特斯拉', 'modelX');
INSERT INTO `sys_car` VALUES (15, '比亚迪', '宋');
INSERT INTO `sys_car` VALUES (16, '比亚迪', '元');
INSERT INTO `sys_car` VALUES (17, '比亚迪', '明');
INSERT INTO `sys_car` VALUES (18, '奔驰', 'C3');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pid` int NULL DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面路径',
  `sort_num` int NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (2, '数据报表', '/dashbord', 'el-icon-s-marketing', '11', NULL, 'Dashbord', 100);
INSERT INTO `sys_menu` VALUES (4, '系统管理', NULL, 'el-icon-s-grid', NULL, NULL, NULL, 300);
INSERT INTO `sys_menu` VALUES (5, '用户管理', '/user', 'el-icon-user', NULL, 4, 'User', 301);
INSERT INTO `sys_menu` VALUES (6, '角色管理', '/role', 'el-icon-s-custom', NULL, 4, 'Role', 302);
INSERT INTO `sys_menu` VALUES (7, '菜单管理', '/menu', 'el-icon-menu', NULL, 4, 'Menu', 303);
INSERT INTO `sys_menu` VALUES (8, '文件管理', '/file', 'el-icon-document', NULL, 4, 'File', 304);
INSERT INTO `sys_menu` VALUES (9, '请作者喝杯咖啡', '/donate', 'el-icon-coffee\r\n', NULL, NULL, 'Donate', 200);
INSERT INTO `sys_menu` VALUES (10, '主页', '/home', 'el-icon-house', NULL, NULL, 'Home', 0);
INSERT INTO `sys_menu` VALUES (39, '课程管理', '/course', 'el-icon-menu', NULL, NULL, 'Course', 201);
INSERT INTO `sys_menu` VALUES (40, '高德地图', '/map', 'el-icon-house', NULL, NULL, 'Map', 999);
INSERT INTO `sys_menu` VALUES (41, '文章管理', '/article', 'el-icon-menu', NULL, NULL, 'Article', 999);
INSERT INTO `sys_menu` VALUES (42, '级联查询', '/building', 'el-icon-menu', NULL, NULL, 'Building', 999);
INSERT INTO `sys_menu` VALUES (43, '时间范围查询', '/timeSearch', 'el-icon-menu', NULL, NULL, 'TimeSearch', 999);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员');
INSERT INTO `sys_role` VALUES (2, '换电站管理员');
INSERT INTO `sys_role` VALUES (3, '用户');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 39);
INSERT INTO `sys_role_menu` VALUES (1, 40);
INSERT INTO `sys_role_menu` VALUES (1, 41);
INSERT INTO `sys_role_menu` VALUES (1, 42);
INSERT INTO `sys_role_menu` VALUES (1, 43);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 10);
INSERT INTO `sys_role_menu` VALUES (3, 39);

-- ----------------------------
-- Table structure for sys_station
-- ----------------------------
DROP TABLE IF EXISTS `sys_station`;
CREATE TABLE `sys_station`  (
  `station_id` int NOT NULL AUTO_INCREMENT COMMENT '换电站id',
  `station_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '换电站编号',
  `station_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '换电站名',
  `station_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '换电站地址',
  `charge_number` int NULL DEFAULT NULL COMMENT '可用电池数量',
  `charge_total` int NULL DEFAULT NULL COMMENT '总电池数量',
  `status` int NULL DEFAULT 1 COMMENT '换电站状态',
  PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_station
-- ----------------------------
INSERT INTO `sys_station` VALUES (7, '202302121067027385', '易电1站', '四川省广安市', 3, 4, 1);
INSERT INTO `sys_station` VALUES (8, '202302121556644922', '石油站', '西南石油大学', 15, 15, 1);
INSERT INTO `sys_station` VALUES (11, '202302131646596637', '星球大站', '银河', 100, 100, 1);
INSERT INTO `sys_station` VALUES (12, '202302131136002217', '比亚迪', '比亚迪厂区', 15, 15, 1);
INSERT INTO `sys_station` VALUES (13, '202302131604168043', '特斯拉', '特斯拉厂区', 15, 15, 1);

-- ----------------------------
-- Table structure for sys_station_car
-- ----------------------------
DROP TABLE IF EXISTS `sys_station_car`;
CREATE TABLE `sys_station_car`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号',
  `station_id` int NULL DEFAULT NULL COMMENT '换电站id',
  `car_id` int NULL DEFAULT NULL COMMENT '汽车id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_station_car
-- ----------------------------
INSERT INTO `sys_station_car` VALUES (1, 4, 6);
INSERT INTO `sys_station_car` VALUES (2, 4, 7);
INSERT INTO `sys_station_car` VALUES (16, 7, 6);
INSERT INTO `sys_station_car` VALUES (17, 7, 7);
INSERT INTO `sys_station_car` VALUES (18, 8, 1);
INSERT INTO `sys_station_car` VALUES (19, 8, 2);
INSERT INTO `sys_station_car` VALUES (20, 8, 3);
INSERT INTO `sys_station_car` VALUES (21, 8, 4);
INSERT INTO `sys_station_car` VALUES (22, 8, 5);
INSERT INTO `sys_station_car` VALUES (23, 8, 6);
INSERT INTO `sys_station_car` VALUES (24, 8, 7);
INSERT INTO `sys_station_car` VALUES (25, 8, 8);
INSERT INTO `sys_station_car` VALUES (26, 8, 9);
INSERT INTO `sys_station_car` VALUES (27, 11, 2);
INSERT INTO `sys_station_car` VALUES (28, 11, 3);
INSERT INTO `sys_station_car` VALUES (29, 11, 4);
INSERT INTO `sys_station_car` VALUES (30, 11, 5);
INSERT INTO `sys_station_car` VALUES (31, 11, 6);
INSERT INTO `sys_station_car` VALUES (32, 11, 7);
INSERT INTO `sys_station_car` VALUES (33, 11, 15);
INSERT INTO `sys_station_car` VALUES (34, 11, 16);
INSERT INTO `sys_station_car` VALUES (35, 11, 17);
INSERT INTO `sys_station_car` VALUES (36, 11, 8);
INSERT INTO `sys_station_car` VALUES (37, 11, 9);
INSERT INTO `sys_station_car` VALUES (38, 11, 14);
INSERT INTO `sys_station_car` VALUES (62, 12, 6);
INSERT INTO `sys_station_car` VALUES (63, 12, 7);
INSERT INTO `sys_station_car` VALUES (64, 12, 15);
INSERT INTO `sys_station_car` VALUES (65, 12, 16);
INSERT INTO `sys_station_car` VALUES (66, 12, 17);
INSERT INTO `sys_station_car` VALUES (67, 13, 6);
INSERT INTO `sys_station_car` VALUES (68, 13, 7);
INSERT INTO `sys_station_car` VALUES (69, 13, 15);
INSERT INTO `sys_station_car` VALUES (70, 13, 16);
INSERT INTO `sys_station_car` VALUES (71, 13, 17);
INSERT INTO `sys_station_car` VALUES (72, 13, 8);
INSERT INTO `sys_station_car` VALUES (73, 13, 9);

-- ----------------------------
-- Table structure for sys_trouble
-- ----------------------------
DROP TABLE IF EXISTS `sys_trouble`;
CREATE TABLE `sys_trouble`  (
  `trouble_id` int NOT NULL AUTO_INCREMENT COMMENT '故障id',
  `trouble_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '故障描述',
  `station_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '换电站编号',
  `status` int NULL DEFAULT NULL COMMENT '故障状态',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '故障上报时间',
  `resolve_time` datetime NULL DEFAULT NULL COMMENT '故障处理时间',
  `station_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '换电站地址',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理人姓名',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理人手机号',
  PRIMARY KEY (`trouble_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_trouble
-- ----------------------------
INSERT INTO `sys_trouble` VALUES (1, '机械臂故障', '202302121067027385', -1, '2023-02-12 21:23:42', '2023-02-13 05:08:06', '四川省广安市', '杨涛', '15378590469');
INSERT INTO `sys_trouble` VALUES (2, '电路故障', '202302121556644922', -1, '2023-02-12 23:54:52', '2023-02-13 00:15:02', '西南石油大学', NULL, NULL);
INSERT INTO `sys_trouble` VALUES (3, '1223456', '202302121556644922', -1, '2023-02-13 05:07:45', '2023-02-13 20:52:57', '西南石油大学', '蛇皮', '15378594689');
INSERT INTO `sys_trouble` VALUES (4, '电站故障', '202302131604168043', 1, '2023-02-23 21:39:52', NULL, '特斯拉厂区', NULL, NULL);
INSERT INTO `sys_trouble` VALUES (5, '电站故障', '202302131646596637', 0, '2023-02-23 21:41:48', '2023-02-23 21:42:02', '银河', '曾德力', '15378590142');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `telephone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `station_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属换电站编号',
  `status` int NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '杨涛', '123456', '15378590469', 1, NULL, 1);
INSERT INTO `sys_user` VALUES (31, '蛇皮', '123456', '15378594689', 2, '202302131604168043', 1);
INSERT INTO `sys_user` VALUES (32, '曾德力', '123456', '15378590142', 2, '202302131646596637', 1);

-- ----------------------------
-- Table structure for sys_user_car
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_car`;
CREATE TABLE `sys_user_car`  (
  `car_id` int NULL DEFAULT NULL COMMENT '汽车id',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_car
-- ----------------------------

-- ----------------------------
-- Table structure for sys_video
-- ----------------------------
DROP TABLE IF EXISTS `sys_video`;
CREATE TABLE `sys_video`  (
  `video_id` int NOT NULL AUTO_INCREMENT COMMENT '视频id',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频流地址',
  `origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源',
  `agreement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '流传输协议',
  `band_width` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入口带宽',
  `car_number` int NULL DEFAULT NULL COMMENT '实时排队车辆结果',
  `status` int NULL DEFAULT 0 COMMENT '状态',
  `station_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属换电站编号',
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_video
-- ----------------------------
INSERT INTO `sys_video` VALUES (1, 'https://serveip/live/stream/.m3u8', '推流', 'RTMP', '300Mkbps', NULL, 0, '202302131604168043');
INSERT INTO `sys_video` VALUES (3, 'https://128.03.12/live/stream/.m3u8', '推流', 'RTMP', '300Mkdps', NULL, 0, '202302131604168043');
INSERT INTO `sys_video` VALUES (4, 'https://128.03.12/live/stream/.m3u8', '推流', 'RTSP', '500Mkbps', NULL, 1, '202302131646596637');

SET FOREIGN_KEY_CHECKS = 1;
