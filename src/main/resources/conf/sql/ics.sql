/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : ics

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 06/07/2020 14:11:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `ics`;
CREATE DATABASE `ics`;
USE `ics`;

-- ----------------------------
-- Table structure for t_an_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_an_answer`;
CREATE TABLE `t_an_answer` (
  `id` varchar(55) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_answer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_checkbox
-- ----------------------------
DROP TABLE IF EXISTS `t_an_checkbox`;
CREATE TABLE `t_an_checkbox` (
  `id` varchar(55) NOT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `other_text` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_item_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_checkbox
-- ----------------------------
BEGIN;
INSERT INTO `t_an_checkbox` VALUES ('ff808081730d063c01730d8407f9002c', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '', 'ff80808173099b5a017309ab19bd0009', 'ff80808173099b5a017309ab19bd000a', 1);
INSERT INTO `t_an_checkbox` VALUES ('ff808081730d063c01730d8407f9002d', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5dbfbd0007', 'ff808081730d063c01730d5dbfc00008', 1);
INSERT INTO `t_an_checkbox` VALUES ('ff808081730d063c01730d87288a0039', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '', 'ff80808173099b5a017309ab19bd0009', 'ff80808173099b5a017309ab19bd000c', 1);
INSERT INTO `t_an_checkbox` VALUES ('ff808081730d063c01730d87288a003a', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5dbfbd0007', 'ff808081730d063c01730d5dbfc00008', 1);
INSERT INTO `t_an_checkbox` VALUES ('ff808081730d063c01730d8d2e3a0045', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '', 'ff80808173099b5a017309ab19bd0009', 'ff80808173099b5a017309ab19bd000a', 1);
INSERT INTO `t_an_checkbox` VALUES ('ff808081730d063c01730d8d2e3a0046', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5dbfbd0007', 'ff808081730d063c01730d5dbfc00008', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_an_chen_checkbox
-- ----------------------------
DROP TABLE IF EXISTS `t_an_chen_checkbox`;
CREATE TABLE `t_an_chen_checkbox` (
  `id` varchar(55) NOT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_col_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_row_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_chen_checkbox
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_chen_fbk
-- ----------------------------
DROP TABLE IF EXISTS `t_an_chen_fbk`;
CREATE TABLE `t_an_chen_fbk` (
  `id` varchar(55) NOT NULL,
  `answer_value` varchar(255) DEFAULT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_col_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_row_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_chen_fbk
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_chen_radio
-- ----------------------------
DROP TABLE IF EXISTS `t_an_chen_radio`;
CREATE TABLE `t_an_chen_radio` (
  `id` varchar(55) NOT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_col_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_row_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_chen_radio
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_chen_score
-- ----------------------------
DROP TABLE IF EXISTS `t_an_chen_score`;
CREATE TABLE `t_an_chen_score` (
  `id` varchar(55) NOT NULL,
  `answser_score` varchar(255) DEFAULT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_col_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_row_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_chen_score
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_comp_chen_radio
-- ----------------------------
DROP TABLE IF EXISTS `t_an_comp_chen_radio`;
CREATE TABLE `t_an_comp_chen_radio` (
  `id` varchar(55) NOT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_col_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_option_id` varchar(255) DEFAULT NULL,
  `qu_row_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_comp_chen_radio
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_dfillblank
-- ----------------------------
DROP TABLE IF EXISTS `t_an_dfillblank`;
CREATE TABLE `t_an_dfillblank` (
  `id` varchar(55) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_item_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_dfillblank
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_enumqu
-- ----------------------------
DROP TABLE IF EXISTS `t_an_enumqu`;
CREATE TABLE `t_an_enumqu` (
  `id` varchar(55) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `enum_item` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_enumqu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_fillblank
-- ----------------------------
DROP TABLE IF EXISTS `t_an_fillblank`;
CREATE TABLE `t_an_fillblank` (
  `id` varchar(55) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_fillblank
-- ----------------------------
BEGIN;
INSERT INTO `t_an_fillblank` VALUES ('ff80808173088af501730973cd560022', '122222', 'ff80808173088af501730973cd560021', 'ff80808173088af501730971d868000c', 'ff80808173088af5017309722795000e', 1);
INSERT INTO `t_an_fillblank` VALUES ('ff808081730d063c01730d8407f90027', 's‘d', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', 'ff808081730d063c01730d6839dd001b', 1);
INSERT INTO `t_an_fillblank` VALUES ('ff808081730d063c01730d87288a0034', '11', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', 'ff808081730d063c01730d6839dd001b', 1);
INSERT INTO `t_an_fillblank` VALUES ('ff808081730d063c01730d8d2e390040', '111', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', 'ff808081730d063c01730d6839dd001b', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_an_order
-- ----------------------------
DROP TABLE IF EXISTS `t_an_order`;
CREATE TABLE `t_an_order` (
  `id` varchar(55) NOT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `ordery_num` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_row_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_order
-- ----------------------------
BEGIN;
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8407f9002e', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '2', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5ea0017', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8407f9002f', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '3', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5eb0018', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8407f90030', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '1', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5eb0019', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8407f90031', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '4', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d662071001a', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d87288b003b', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '1', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5ea0017', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d87288b003c', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '2', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5eb0018', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d87288b003d', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '3', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5eb0019', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d87288b003e', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '4', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d662071001a', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8d2e3a0047', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '1', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5ea0017', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8d2e3a0048', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '2', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5eb0018', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8d2e3a0049', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '3', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d65e5eb0019', 1);
INSERT INTO `t_an_order` VALUES ('ff808081730d063c01730d8d2e3a004a', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '4', 'ff808081730d063c01730d65e5ea0016', 'ff808081730d063c01730d662071001a', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_an_radio
-- ----------------------------
DROP TABLE IF EXISTS `t_an_radio`;
CREATE TABLE `t_an_radio` (
  `id` varchar(55) NOT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `other_text` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_item_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_radio
-- ----------------------------
BEGIN;
INSERT INTO `t_an_radio` VALUES ('ff80808173088af501730973cd560023', 'ff80808173088af501730973cd560021', 'ff80808173088af501730971d868000c', '', 'ff80808173088af50173097227a4000f', 'ff80808173088af50173097227a40010', 1);
INSERT INTO `t_an_radio` VALUES ('ff80808173088af501730973cd560024', 'ff80808173088af501730973cd560021', 'ff80808173088af501730971d868000c', '', 'ff80808173088af50173097227b10012', 'ff80808173088af50173097227b10015', 1);
INSERT INTO `t_an_radio` VALUES ('ff80808173088af501730973cd560025', 'ff80808173088af501730973cd560021', 'ff80808173088af501730971d868000c', '', 'ff80808173088af50173097275b20018', 'ff80808173088af50173097275b20019', 1);
INSERT INTO `t_an_radio` VALUES ('ff80808173088af501730973cd560026', 'ff80808173088af501730973cd560021', 'ff80808173088af501730971d868000c', '', 'ff80808173088af501730972c3d2001e', 'ff80808173088af501730972c3d20020', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8407f90028', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5e1edc000b', 'ff808081730d063c01730d5e1edc000c', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8407f90029', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5f048f000e', 'ff808081730d063c01730d5f0490000f', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8407f9002a', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d689ab3001c', 'ff808081730d063c01730d689ab4001e', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8407f9002b', 'ff808081730d063c01730d8407f80026', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d690d01001f', 'ff808081730d063c01730d690d010021', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d87288a0035', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5e1edc000b', 'ff808081730d063c01730d5e1edc000c', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d87288a0036', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5f048f000e', 'ff808081730d063c01730d5f0490000f', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d87288a0037', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d689ab3001c', 'ff808081730d063c01730d689ab3001d', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d87288a0038', 'ff808081730d063c01730d87288a0033', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d690d01001f', 'ff808081730d063c01730d690d010023', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8d2e3a0041', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5e1edc000b', 'ff808081730d063c01730d5e1edc000c', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8d2e3a0042', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d5f048f000e', 'ff808081730d063c01730d5f04910010', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8d2e3a0043', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d689ab3001c', 'ff808081730d063c01730d689ab3001d', 1);
INSERT INTO `t_an_radio` VALUES ('ff808081730d063c01730d8d2e3a0044', 'ff808081730d063c01730d8d2e39003f', 'ff80808173099b5a017309a41c070006', '', 'ff808081730d063c01730d690d01001f', 'ff808081730d063c01730d690d010020', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_an_score
-- ----------------------------
DROP TABLE IF EXISTS `t_an_score`;
CREATE TABLE `t_an_score` (
  `id` varchar(55) NOT NULL,
  `answser_score` varchar(255) DEFAULT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `qu_row_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_score
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_an_yesno
-- ----------------------------
DROP TABLE IF EXISTS `t_an_yesno`;
CREATE TABLE `t_an_yesno` (
  `id` varchar(55) NOT NULL,
  `belong_answer_id` varchar(255) DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  `yesno_answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_an_yesno
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_import_error
-- ----------------------------
DROP TABLE IF EXISTS `t_import_error`;
CREATE TABLE `t_import_error` (
  `id` varchar(55) NOT NULL,
  `cell1value` varchar(255) DEFAULT NULL,
  `cell2value` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `db_id` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `row_index` int DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_import_error
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_mail_invite_inbox
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_invite_inbox`;
CREATE TABLE `t_mail_invite_inbox` (
  `id` varchar(55) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sendcloud_id` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `survey_mail_invite_id` varchar(255) DEFAULT NULL,
  `us_contacts_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_mail_invite_inbox
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_qu_checkbox
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_checkbox`;
CREATE TABLE `t_qu_checkbox` (
  `id` varchar(55) NOT NULL,
  `check_type` int DEFAULT NULL,
  `is_note` int DEFAULT NULL,
  `is_required_fill` int DEFAULT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `option_title` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_checkbox
-- ----------------------------
BEGIN;
INSERT INTO `t_qu_checkbox` VALUES ('402880e55cb9c629015cb9e366880009', 0, 0, 0, '选项1', NULL, 0, '402880e55cb9c629015cb9e366880008', 1);
INSERT INTO `t_qu_checkbox` VALUES ('402880e55cb9c629015cb9e36688000a', 0, 0, 0, '选项2', NULL, 1, '402880e55cb9c629015cb9e366880008', 1);
INSERT INTO `t_qu_checkbox` VALUES ('402880e55cb9c629015cb9e36688000b', 0, 0, 0, '选项3', NULL, 2, '402880e55cb9c629015cb9e366880008', 1);
INSERT INTO `t_qu_checkbox` VALUES ('402880057308184401730818cef60006', 0, 0, 0, '选项1', NULL, 0, '402880057308184401730818cef50005', 1);
INSERT INTO `t_qu_checkbox` VALUES ('402880057308184401730818cef60007', 0, 0, 0, '选项2', NULL, 1, '402880057308184401730818cef50005', 1);
INSERT INTO `t_qu_checkbox` VALUES ('402880057308184401730818cef60008', 0, 0, 0, '选项3', NULL, 2, '402880057308184401730818cef50005', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff80808173099b5a017309ab19bd000a', 0, 0, 0, '很', NULL, 0, 'ff80808173099b5a017309ab19bd0009', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff80808173099b5a017309ab19bd000b', 0, 0, 0, '不好', NULL, 1, 'ff80808173099b5a017309ab19bd0009', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff80808173099b5a017309ab19bd000c', 0, 0, 0, '不确定', NULL, 2, 'ff80808173099b5a017309ab19bd0009', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff808081730d063c01730d3facdd0001', 0, 0, 0, '选项1', NULL, 0, 'ff808081730d063c01730d3facd90000', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff808081730d063c01730d3facdd0002', 0, 0, 0, '选项2', NULL, 1, 'ff808081730d063c01730d3facd90000', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff808081730d063c01730d3facdd0003', 0, 0, 0, '选项3', NULL, 2, 'ff808081730d063c01730d3facd90000', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff808081730d063c01730d5dbfc00008', 0, 0, 0, '选项1', NULL, 0, 'ff808081730d063c01730d5dbfbd0007', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff808081730d063c01730d5dbfc20009', 0, 0, 0, '选项2', NULL, 1, 'ff808081730d063c01730d5dbfbd0007', 1);
INSERT INTO `t_qu_checkbox` VALUES ('ff808081730d063c01730d5dbfc4000a', 0, 0, 0, '选项3', NULL, 2, 'ff808081730d063c01730d5dbfbd0007', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_qu_chen_column
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_chen_column`;
CREATE TABLE `t_qu_chen_column` (
  `id` varchar(55) NOT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_chen_column
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_qu_chen_option
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_chen_option`;
CREATE TABLE `t_qu_chen_option` (
  `id` varchar(55) NOT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_chen_option
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_qu_chen_row
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_chen_row`;
CREATE TABLE `t_qu_chen_row` (
  `id` varchar(55) NOT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_chen_row
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_qu_multi_fillblank
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_multi_fillblank`;
CREATE TABLE `t_qu_multi_fillblank` (
  `id` varchar(55) NOT NULL,
  `check_type` int DEFAULT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `option_title` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_multi_fillblank
-- ----------------------------
BEGIN;
INSERT INTO `t_qu_multi_fillblank` VALUES ('ff80808173088af50173092611a60008', NULL, '选项1', NULL, 0, 'ff80808173088af50173092611a60007', 1);
INSERT INTO `t_qu_multi_fillblank` VALUES ('ff80808173088af50173092611a60009', NULL, '选项2', NULL, 1, 'ff80808173088af50173092611a60007', 1);
INSERT INTO `t_qu_multi_fillblank` VALUES ('ff80808173088af50173092611a6000a', NULL, '选项3', NULL, 2, 'ff80808173088af50173092611a60007', 1);
INSERT INTO `t_qu_multi_fillblank` VALUES ('ff808081730d063c01730d64383a0013', NULL, '选项1', NULL, 0, 'ff808081730d063c01730d64383a0012', 1);
INSERT INTO `t_qu_multi_fillblank` VALUES ('ff808081730d063c01730d64383a0014', NULL, '选项2', NULL, 1, 'ff808081730d063c01730d64383a0012', 1);
INSERT INTO `t_qu_multi_fillblank` VALUES ('ff808081730d063c01730d64383a0015', NULL, '选项3', NULL, 2, 'ff808081730d063c01730d64383a0012', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_qu_orderby
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_orderby`;
CREATE TABLE `t_qu_orderby` (
  `id` varchar(55) NOT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `option_title` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_orderby
-- ----------------------------
BEGIN;
INSERT INTO `t_qu_orderby` VALUES ('ff808081730d063c01730d65e5ea0017', '选项1', NULL, 0, 'ff808081730d063c01730d65e5ea0016', 1);
INSERT INTO `t_qu_orderby` VALUES ('ff808081730d063c01730d65e5eb0018', '选项2', NULL, 1, 'ff808081730d063c01730d65e5ea0016', 1);
INSERT INTO `t_qu_orderby` VALUES ('ff808081730d063c01730d65e5eb0019', '选项3', NULL, 2, 'ff808081730d063c01730d65e5ea0016', 1);
INSERT INTO `t_qu_orderby` VALUES ('ff808081730d063c01730d662071001a', 'ddd', NULL, 3, 'ff808081730d063c01730d65e5ea0016', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_qu_radio
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_radio`;
CREATE TABLE `t_qu_radio` (
  `id` varchar(55) NOT NULL,
  `check_type` int DEFAULT NULL,
  `is_note` int DEFAULT NULL,
  `is_required_fill` int DEFAULT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `option_title` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_radio
-- ----------------------------
BEGIN;
INSERT INTO `t_qu_radio` VALUES ('402880e55cb9c629015cb9dbd75d0003', 0, 0, 0, '选项1', NULL, 0, '402880e55cb9c629015cb9dbd75d0002', 1);
INSERT INTO `t_qu_radio` VALUES ('402880e55cb9c629015cb9dbd75d0004', 0, 0, 0, '选项2', NULL, 1, '402880e55cb9c629015cb9dbd75d0002', 1);
INSERT INTO `t_qu_radio` VALUES ('402880e55cb9c629015cb9dbd75e0005', 0, 0, 0, '332423', NULL, 2, '402880e55cb9c629015cb9dbd75d0002', 1);
INSERT INTO `t_qu_radio` VALUES ('402880057308184401730818cec50003', 0, 0, 0, '选项1', NULL, 0, '402880057308184401730818cec50002', 1);
INSERT INTO `t_qu_radio` VALUES ('402880057308184401730818cec50004', 0, 0, 0, '选项2', NULL, 1, '402880057308184401730818cec50002', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730925d7150003', 0, 0, 0, '博士', NULL, 0, 'ff80808173088af501730925d7150002', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730925d7150004', 0, 0, 0, '硕士', NULL, 1, 'ff80808173088af501730925d7150002', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730925d7150005', 0, 0, 0, '本科', NULL, 2, 'ff80808173088af501730925d7150002', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730925d7150006', 0, 0, 0, '专科', NULL, 3, 'ff80808173088af501730925d7150002', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097227a40010', 0, 0, 0, '男', NULL, 0, 'ff80808173088af50173097227a4000f', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097227a40011', 0, 0, 0, '女', NULL, 1, 'ff80808173088af50173097227a4000f', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097227b10013', 0, 0, 0, '博士', NULL, 0, 'ff80808173088af50173097227b10012', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097227b10014', 0, 0, 0, '硕士', NULL, 1, 'ff80808173088af50173097227b10012', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097227b10015', 0, 0, 0, '本科', NULL, 2, 'ff80808173088af50173097227b10012', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097227b10016', 0, 0, 0, '专科', NULL, 3, 'ff80808173088af50173097227b10012', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af5017309724ea50017', 0, 0, 0, '其它', NULL, 4, 'ff80808173088af50173097227b10012', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097275b20019', 0, 0, 0, '3000元以下', NULL, 0, 'ff80808173088af50173097275b20018', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097275b2001a', 0, 0, 0, '3000-5000元', NULL, 1, 'ff80808173088af50173097275b20018', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097275b2001b', 0, 0, 0, '5000-10000元', NULL, 2, 'ff80808173088af50173097275b20018', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097275b3001c', 0, 0, 0, '10000-20000元', NULL, 3, 'ff80808173088af50173097275b20018', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af50173097275b3001d', 0, 0, 0, '20000元以上', NULL, 4, 'ff80808173088af50173097275b20018', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730972c3d2001f', 0, 0, 0, '是', NULL, 0, 'ff80808173088af501730972c3d2001e', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730972c3d20020', 0, 0, 0, '否', NULL, 1, 'ff80808173088af501730972c3d2001e', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730999d2fc002d', 0, 0, 0, '选项1', NULL, 0, 'ff80808173088af501730999d2fc002c', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730999d2fd002e', 0, 0, 0, '选项2', NULL, 1, 'ff80808173088af501730999d2fc002c', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730999d2fd002f', 0, 0, 0, 'ddd', NULL, 2, 'ff80808173088af501730999d2fc002c', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173088af501730999d2fd0030', 0, 0, 0, 'ddd', NULL, 3, 'ff80808173088af501730999d2fc002c', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173099b5a0173099bbca10001', 0, 0, 0, '选项1', NULL, 0, 'ff80808173099b5a0173099bbc9e0000', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173099b5a0173099bbca10002', 0, 0, 0, '选项2', NULL, 1, 'ff80808173099b5a0173099bbc9e0000', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173099b5a0173099db89c0004', 0, 0, 0, '选项1', NULL, 0, 'ff80808173099b5a0173099db89b0003', 1);
INSERT INTO `t_qu_radio` VALUES ('ff80808173099b5a0173099db89e0005', 0, 0, 0, '选项2', NULL, 1, 'ff80808173099b5a0173099db89b0003', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d5466090005', 0, 0, 0, '选项1', NULL, 0, 'ff808081730d063c01730d5466080004', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d54660a0006', 0, 0, 0, '选项2', NULL, 1, 'ff808081730d063c01730d5466080004', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d5e1edc000c', 0, 0, 0, '选项1', NULL, 0, 'ff808081730d063c01730d5e1edc000b', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d5e1edd000d', 0, 0, 0, '选项2', NULL, 1, 'ff808081730d063c01730d5e1edc000b', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d5f0490000f', 0, 0, 0, '选项1', NULL, 0, 'ff808081730d063c01730d5f048f000e', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d5f04910010', 0, 0, 0, '选项2', NULL, 1, 'ff808081730d063c01730d5f048f000e', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d689ab3001d', 0, 0, 0, '男', NULL, 0, 'ff808081730d063c01730d689ab3001c', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d689ab4001e', 0, 0, 0, '女', NULL, 1, 'ff808081730d063c01730d689ab3001c', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d690d010020', 0, 0, 0, '博士', NULL, 0, 'ff808081730d063c01730d690d01001f', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d690d010021', 0, 0, 0, '硕士', NULL, 1, 'ff808081730d063c01730d690d01001f', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d690d010022', 0, 0, 0, '本科', NULL, 2, 'ff808081730d063c01730d690d01001f', 1);
INSERT INTO `t_qu_radio` VALUES ('ff808081730d063c01730d690d010023', 0, 0, 0, '专科', NULL, 3, 'ff808081730d063c01730d690d01001f', 1);
INSERT INTO `t_qu_radio` VALUES ('ff8080817321c99d0173221701190001', 0, 0, 0, '选项1', NULL, 0, 'ff8080817321c99d0173221701130000', 1);
INSERT INTO `t_qu_radio` VALUES ('ff8080817321c99d0173221701190002', 0, 0, 0, '选项2', NULL, 1, 'ff8080817321c99d0173221701130000', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_qu_score
-- ----------------------------
DROP TABLE IF EXISTS `t_qu_score`;
CREATE TABLE `t_qu_score` (
  `id` varchar(55) NOT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `option_title` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qu_score
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` varchar(55) NOT NULL,
  `answer_input_row` int DEFAULT NULL,
  `answer_input_width` int DEFAULT NULL,
  `belong_id` varchar(255) DEFAULT NULL,
  `cell_count` int DEFAULT NULL,
  `check_type` int DEFAULT NULL,
  `contacts_attr` int DEFAULT NULL,
  `contacts_field` varchar(255) DEFAULT NULL,
  `copy_from_id` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `hv` int DEFAULT NULL,
  `is_required` int DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `order_by_id` int DEFAULT NULL,
  `param_int01` int DEFAULT NULL,
  `param_int02` int DEFAULT NULL,
  `parent_qu_id` varchar(255) DEFAULT NULL,
  `qu_name` varchar(255) DEFAULT NULL,
  `qu_note` text,
  `qu_tag` int DEFAULT NULL,
  `qu_title` text,
  `qu_type` int DEFAULT NULL,
  `rand_order` int DEFAULT NULL,
  `tag` int DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  `yesno_option` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------
BEGIN;
INSERT INTO `t_question` VALUES ('402880e55cb9c629015cb9dbd75d0002', NULL, NULL, '402880e55cb9c629015cb9d31eff0000', 0, NULL, 0, '0', NULL, '2017-06-18 14:20:11', 2, 1, NULL, 1, 3, 10, NULL, NULL, NULL, 1, '题标题？222', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('402880e55cb9c629015cb9e366880008', NULL, NULL, '402880e55cb9c629015cb9d31eff0000', 0, NULL, 0, '0', NULL, '2017-06-18 14:28:26', 2, 1, NULL, 2, 3, 10, NULL, NULL, NULL, 1, '题标题？', 2, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('402880057308184401730818cec50002', NULL, NULL, '402880057308184401730818a5bc0000', 0, NULL, 0, '0', NULL, '2020-06-30 20:58:30', 2, 1, NULL, 1, 3, 10, NULL, NULL, NULL, 1, '题标题？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('402880057308184401730818cef50005', NULL, NULL, '402880057308184401730818a5bc0000', 0, NULL, 0, '0', NULL, '2020-06-30 20:58:30', 2, 1, NULL, 2, 3, 10, NULL, NULL, NULL, 1, '题标题？', 2, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af501730925d7150002', NULL, NULL, 'ff80808173088af501730924eb7f0000', 0, NULL, 0, '6', NULL, '2020-07-01 01:52:22', 1, 1, NULL, 2, 3, 10, NULL, NULL, NULL, 1, '您的最高学历是？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af50173092611a60007', NULL, NULL, 'ff80808173088af501730924eb7f0000', 0, NULL, 0, NULL, NULL, '2020-07-01 01:52:37', 2, 1, NULL, 1, 1, 10, NULL, NULL, NULL, 1, '请问你的年级是？', 6, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af50173092686d8000b', 1, 300, 'ff80808173088af501730924eb7f0000', 0, 0, 0, '0', NULL, '2020-07-01 01:53:07', 2, 1, NULL, 3, 3, 10, NULL, NULL, NULL, 1, '你好', 3, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af5017309722795000e', 1, 300, 'ff80808173088af501730971d868000c', 0, 1, 0, '5', NULL, '2020-07-01 03:15:43', 2, 1, NULL, 2, 3, 10, NULL, NULL, NULL, 1, '您的邮箱是？', 3, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af50173097227a4000f', NULL, NULL, 'ff80808173088af501730971d868000c', 0, NULL, 0, '6', NULL, '2020-07-01 03:15:43', 1, 1, NULL, 3, 3, 10, NULL, NULL, NULL, 1, '您的性别是？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af50173097227b10012', NULL, NULL, 'ff80808173088af501730971d868000c', 0, NULL, 0, '6', NULL, '2020-07-01 03:15:43', 1, 1, NULL, 4, 3, 10, NULL, NULL, NULL, 1, '您的最高学历是？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af50173097275b20018', NULL, NULL, 'ff80808173088af501730971d868000c', 0, NULL, 0, '6', NULL, '2020-07-01 03:16:03', 1, 1, NULL, 5, 3, 10, NULL, NULL, NULL, 1, '您的月平均收入是？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af501730972c3d2001e', NULL, NULL, 'ff80808173088af501730971d868000c', 0, NULL, 0, '6', NULL, '2020-07-01 03:16:23', 1, 1, NULL, 1, 3, 10, NULL, NULL, NULL, 1, '请问您婚否？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173088af501730999d2fc002c', NULL, NULL, 'ff80808173088af5017309917094002a', 0, NULL, 0, '0', NULL, '2020-07-01 03:59:03', 2, 1, NULL, 1, 3, 10, NULL, NULL, NULL, 1, '<p>题标题？<img src=\"/ueditor/jsp/upload/image/20200702/1593666873994032456.jpeg\" title=\"1593666873994032456.jpeg\" alt=\"WechatIMG2931.jpeg\"></p>', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173099b5a0173099bbc9e0000', NULL, NULL, 'ff80808173088af5017309917094002a', 0, NULL, 0, '0', NULL, '2020-07-01 04:01:08', 2, 1, NULL, 3, 3, 10, NULL, NULL, NULL, 1, '题标题？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173099b5a0173099db89b0003', NULL, NULL, 'ff80808173088af5017309917094002a', 0, NULL, 0, '0', NULL, '2020-07-01 04:02:20', 2, 1, NULL, 2, 3, 10, NULL, NULL, NULL, 1, '<p>题标题？<img src=\"/ueditor/jsp/upload/image/20200702/1593666897131006219.jpg\" title=\"1593666897131006219.jpg\" alt=\"B600CBBC1BBB071181787F596619DF10.jpg\"></p>', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff80808173099b5a017309ab19bd0009', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, '0', NULL, '2020-07-01 04:17:55', 2, 1, NULL, 2, 3, 10, NULL, NULL, NULL, 1, '你好啊', 2, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d5dbfbd0007', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, '0', NULL, '2020-07-01 21:31:54', 2, 1, NULL, 1, 3, 10, NULL, NULL, NULL, 1, '<p><img src=\"/ueditor/jsp/upload/image/20200702/1593658890997086884.png\" title=\"1593658890997086884.png\" alt=\"淮师校徽.png\"></p>', 2, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d5e1edc000b', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, '0', NULL, '2020-07-01 21:32:19', 2, 1, NULL, 5, 3, 10, NULL, NULL, NULL, 1, '题标题？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d5f048f000e', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, '0', NULL, '2020-07-01 21:33:14', 2, 1, NULL, 3, 3, 10, NULL, NULL, NULL, 1, '题标题？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d6839dd001b', 1, 300, 'ff80808173099b5a017309a41c070006', 0, 1, 0, '5', NULL, '2020-07-01 21:43:19', 2, 1, NULL, 6, 3, 10, NULL, NULL, NULL, 1, '您的邮箱是？', 3, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d65e5ea0016', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, NULL, NULL, '2020-07-01 21:40:49', 2, 1, NULL, 4, 3, 10, NULL, NULL, NULL, 1, '题标题？', 11, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d689ab3001c', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, '6', NULL, '2020-07-01 21:43:46', 1, 1, NULL, 8, 3, 10, NULL, NULL, NULL, 1, '您的性别是？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d690d01001f', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, '6', NULL, '2020-07-01 21:44:15', 1, 1, NULL, 10, 3, 10, NULL, NULL, NULL, 1, '您的最高学历是？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d6aef070024', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, NULL, NULL, '2020-07-01 21:46:19', 2, 1, NULL, 7, 3, 10, NULL, NULL, NULL, 1, NULL, 18, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff808081730d063c01730d6b29850025', NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, NULL, 0, NULL, NULL, '2020-07-01 21:46:34', 2, 1, NULL, 9, 3, 10, NULL, NULL, NULL, 1, NULL, 18, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff8080817321c99d0173221701130000', NULL, NULL, 'ff808081730df24101730e122f140002', 0, NULL, 0, '0', NULL, '2020-07-05 22:06:40', 2, 1, NULL, 1, 3, 10, NULL, NULL, NULL, 1, '题标题？', 1, 0, 2, 1, NULL);
INSERT INTO `t_question` VALUES ('ff8080817321c99d01732217e6380003', 1, 300, 'ff808081730df24101730e122f140002', 0, 0, 0, '0', NULL, '2020-07-05 22:07:37', 2, 1, NULL, 2, 3, 10, NULL, NULL, NULL, 1, '题标题？', 3, 0, 2, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_question_bank
-- ----------------------------
DROP TABLE IF EXISTS `t_question_bank`;
CREATE TABLE `t_question_bank` (
  `id` varchar(55) NOT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_note` varchar(255) DEFAULT NULL,
  `bank_state` int DEFAULT NULL,
  `bank_tag` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `dir_type` int DEFAULT NULL,
  `excerpt_num` int DEFAULT NULL,
  `group_id1` varchar(255) DEFAULT NULL,
  `group_id2` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `qu_num` int DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question_bank
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_question_logic
-- ----------------------------
DROP TABLE IF EXISTS `t_question_logic`;
CREATE TABLE `t_question_logic` (
  `id` varchar(55) NOT NULL,
  `cg_qu_item_id` varchar(255) DEFAULT NULL,
  `ck_qu_id` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `ge_le` varchar(255) DEFAULT NULL,
  `logic_type` varchar(255) DEFAULT NULL,
  `score_num` int DEFAULT NULL,
  `sk_qu_id` varchar(255) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question_logic
-- ----------------------------
BEGIN;
INSERT INTO `t_question_logic` VALUES ('ff808081730e3e1c01730e4b776a0000', 'ff808081730d063c01730d5f0490000f', 'ff808081730d063c01730d5f048f000e', '2020-07-02 01:51:59', NULL, '2', NULL, 'ff808081730d063c01730d5dbfbd0007', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_survey_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_survey_answer`;
CREATE TABLE `t_survey_answer` (
  `id` varchar(55) NOT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `bg_an_date` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `complete_item_num` int DEFAULT NULL,
  `complete_num` int DEFAULT NULL,
  `data_source` int DEFAULT NULL,
  `end_an_date` datetime DEFAULT NULL,
  `handle_state` int DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `is_complete` int DEFAULT NULL,
  `is_effective` int DEFAULT NULL,
  `pc_mac` varchar(255) DEFAULT NULL,
  `qu_num` int DEFAULT NULL,
  `survey_id` varchar(255) DEFAULT NULL,
  `total_time` float DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_survey_answer
-- ----------------------------
BEGIN;
INSERT INTO `t_survey_answer` VALUES ('ff80808173088af501730973cd560021', '本机地址', '2020-07-01 03:17:31', '本机地址', 5, NULL, 0, '2020-07-01 03:17:31', 0, '127.0.0.1', 1, 1, NULL, NULL, 'ff80808173088af501730971d868000c', 0, '1');
INSERT INTO `t_survey_answer` VALUES ('ff808081730d063c01730d8407f80026', '本机地址', '2020-07-01 22:13:43', '本机地址', 11, NULL, 0, '2020-07-01 22:13:43', 0, '127.0.0.1', 1, 1, NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, '1');
INSERT INTO `t_survey_answer` VALUES ('ff808081730d063c01730d87288a0033', '局域网', '2020-07-01 22:17:08', '局域网', 11, NULL, 0, '2020-07-01 22:17:08', 0, '192.168.0.133', 1, 1, NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, '1');
INSERT INTO `t_survey_answer` VALUES ('ff808081730d063c01730d8d2e39003f', '本机地址', '2020-07-01 22:23:43', '本机地址', 11, NULL, 0, '2020-07-01 22:23:43', 0, '127.0.0.1', 1, 1, NULL, NULL, 'ff80808173099b5a017309a41c070006', 0, '1');
COMMIT;

-- ----------------------------
-- Table structure for t_survey_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_survey_detail`;
CREATE TABLE `t_survey_detail` (
  `id` varchar(55) NOT NULL,
  `an_item_least_num` int DEFAULT NULL,
  `an_item_most_num` int DEFAULT NULL,
  `dir_id` varchar(255) DEFAULT NULL,
  `effective` int DEFAULT NULL,
  `effective_ip` int DEFAULT NULL,
  `effective_time` int DEFAULT NULL,
  `end_num` int DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `end_type` int DEFAULT NULL,
  `mail_only` int DEFAULT NULL,
  `refresh` int DEFAULT NULL,
  `refresh_num` int DEFAULT NULL,
  `rule` int DEFAULT NULL,
  `rule_code` varchar(255) DEFAULT NULL,
  `show_answer_da` int DEFAULT NULL,
  `show_share_survey` int DEFAULT NULL,
  `survey_note` text,
  `survey_qu_num` int DEFAULT NULL,
  `yn_end_num` int DEFAULT NULL,
  `yn_end_time` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_survey_detail
-- ----------------------------
BEGIN;
INSERT INTO `t_survey_detail` VALUES ('402880e55cb9c629015cb9d31f160001', 0, 0, '402880e55cb9c629015cb9d31eff0000', 0, 0, 5, 1000, '2017-07-13 23:21:00', 1, 0, 1, 3, 0, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('402880057308184401730818a5c20001', 0, 0, '402880057308184401730818a5bc0000', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('402880057308184401730822e304000a', 0, 0, '402880057308184401730822e3020009', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff80808173088af501730924eb860001', 0, 0, 'ff80808173088af501730924eb7f0000', 0, 0, 5, 1000, '2020-07-01 01:51:42', 1, 0, 1, 3, 0, '令牌', 0, 0, '', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff80808173088af501730971d86c000d', 0, 0, 'ff80808173088af501730971d868000c', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff80808173088af501730974f2ec0029', 0, 0, 'ff80808173088af501730974f2ea0028', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff80808173088af5017309917096002b', 0, 0, 'ff80808173088af5017309917094002a', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff80808173099b5a017309a41c0a0007', 0, 0, 'ff80808173099b5a017309a41c070006', 0, 0, 5, 1000, '2020-07-01 21:52:16', 1, 0, 1, 3, 0, '令牌', 0, 0, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 1, 0);
INSERT INTO `t_survey_detail` VALUES ('ff808081730df24101730e122f160003', 0, 0, 'ff808081730df24101730e122f140002', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff8080817321c99d0173224125890005', 0, 0, 'ff8080817321c99d0173224125860004', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff8080817321c99d017322416c890007', 0, 0, 'ff8080817321c99d017322416c870006', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
INSERT INTO `t_survey_detail` VALUES ('ff8080817321c99d017322418ea90009', 0, 0, 'ff8080817321c99d017322418ea70008', 1, 0, 5, 1000, NULL, 1, 0, 1, 3, 1, '令牌', 0, 1, '非常感谢您的参与！如有涉及个人信息，我们将严格保密。', 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_survey_directory
-- ----------------------------
DROP TABLE IF EXISTS `t_survey_directory`;
CREATE TABLE `t_survey_directory` (
  `id` varchar(55) NOT NULL,
  `an_item_least_num` int DEFAULT NULL,
  `answer_num` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `dir_type` int DEFAULT NULL,
  `excerpt_num` int DEFAULT NULL,
  `html_path` varchar(255) DEFAULT NULL,
  `is_share` int DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `survey_detail_id` varchar(255) DEFAULT NULL,
  `survey_model` int DEFAULT NULL,
  `survey_name` varchar(255) DEFAULT NULL,
  `survey_qu_num` int DEFAULT NULL,
  `survey_state` int DEFAULT NULL,
  `survey_tag` int DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `view_answer` int DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_survey_directory
-- ----------------------------
BEGIN;
INSERT INTO `t_survey_directory` VALUES ('402880e55cb9c629015cb9d31eff0000', 0, NULL, '2017-06-18 14:10:39', 2, 0, 'WEB-INF/wjHtml/2017/06/18/402880e55cb9c629015cb9d31eff0000.html', 1, '', '2p4tsrc5cvz', NULL, 1, '第一份测试问卷', 2, 1, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('402880057308184401730818a5bc0000', 0, NULL, '2020-06-30 20:58:20', 2, 0, 'WEB-INF/wjHtml/2020/07/01/402880057308184401730818a5bc0000.html', 1, '', 'nnezeur', NULL, 1, 'sdd', 2, 1, 1, '1', 0, 0);
INSERT INTO `t_survey_directory` VALUES ('402880057308184401730822e3020009', 0, NULL, '2020-06-30 21:09:31', 2, 0, NULL, 1, '', '3f1h1bim', NULL, 1, 'kjj', 0, 0, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff80808173088af501730924eb7f0000', 0, NULL, '2020-07-01 01:51:21', 2, 0, NULL, 0, '', 'f8b33vtzhs', NULL, 1, '你好', 0, 0, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff80808173088af501730971d868000c', 0, 1, '2020-07-01 03:15:23', 2, 0, 'WEB-INF/wjHtml/2020/07/01/ff80808173088af501730971d868000c.html', 1, '', 'njaa0c63', NULL, 1, 'hhh', 5, 2, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff80808173088af501730974f2ea0028', 0, NULL, '2020-07-01 03:18:46', 2, 0, 'WEB-INF/wjHtml/2020/07/01/ff80808173088af501730974f2ea0028.html', 1, '', '7ce04y', NULL, 1, '低调低调', 0, 2, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff80808173088af5017309917094002a', 0, NULL, '2020-07-01 03:49:53', 2, 0, 'WEB-INF/wjHtml/2020/07/01/ff80808173088af5017309917094002a.html', 1, '', 'ym58xt3ua4f', NULL, 1, '请输入问卷标题', 3, 1, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff80808173099b5a017309a41c070006', 0, 3, '2020-07-01 04:10:17', 2, 0, 'WEB-INF/wjHtml/2020/07/01/ff80808173099b5a017309a41c070006.html', 0, '', '1jhj2z5qgjj', NULL, 1, '好好好', 10, 2, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff808081730df24101730e122f140002', 0, NULL, '2020-07-02 00:49:00', 2, 0, 'WEB-INF/wjHtml/2020/07/02/ff808081730df24101730e122f140002.html', 1, '', 'puhxx9de8q', NULL, 1, '更好的问卷', 0, 2, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff8080817321c99d0173224125860004', 0, NULL, '2020-07-05 22:52:42', 2, 0, NULL, 1, '', 'y75v1hlvvas', NULL, 1, 'jjj', 0, 0, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff8080817321c99d017322416c870006', 0, NULL, '2020-07-05 22:53:00', 2, 0, NULL, 1, '', 'gs4tb2cz', NULL, 1, 'kkk', 0, 0, 1, '1', 0, 1);
INSERT INTO `t_survey_directory` VALUES ('ff8080817321c99d017322418ea70008', 0, NULL, '2020-07-05 22:53:09', 2, 0, NULL, 1, '', '6rcgubf0', NULL, 1, '333', 0, 0, 1, '1', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_survey_mail_invite
-- ----------------------------
DROP TABLE IF EXISTS `t_survey_mail_invite`;
CREATE TABLE `t_survey_mail_invite` (
  `id` varchar(55) NOT NULL,
  `audit` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `dw_send_user_mail` varchar(255) DEFAULT NULL,
  `dw_send_user_name` varchar(255) DEFAULT NULL,
  `dw_survey_link` varchar(255) DEFAULT NULL,
  `dw_survey_name` varchar(255) DEFAULT NULL,
  `error_msg` varchar(255) DEFAULT NULL,
  `fail_num` int DEFAULT NULL,
  `inbox_num` int DEFAULT NULL,
  `send_num` int DEFAULT NULL,
  `sendcloud_msg_id` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `success_num` int DEFAULT NULL,
  `survey_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_survey_mail_invite
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_survey_req_url
-- ----------------------------
DROP TABLE IF EXISTS `t_survey_req_url`;
CREATE TABLE `t_survey_req_url` (
  `id` varchar(55) NOT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `state` int DEFAULT NULL,
  `survey_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_survey_req_url
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_survey_stats
-- ----------------------------
DROP TABLE IF EXISTS `t_survey_stats`;
CREATE TABLE `t_survey_stats` (
  `id` varchar(55) NOT NULL,
  `an_avg_time` int DEFAULT NULL,
  `an_min_time` int DEFAULT NULL,
  `answer_num` int DEFAULT NULL,
  `complete_num` int DEFAULT NULL,
  `effective_num` int DEFAULT NULL,
  `first_answer` datetime DEFAULT NULL,
  `handle_pass_num` int DEFAULT NULL,
  `handle_un_pass_num` int DEFAULT NULL,
  `import_num` int DEFAULT NULL,
  `input_num` int DEFAULT NULL,
  `is_new_data` int DEFAULT NULL,
  `last_answer` datetime DEFAULT NULL,
  `mobile_num` int DEFAULT NULL,
  `online_num` int DEFAULT NULL,
  `survey_id` varchar(255) DEFAULT NULL,
  `un_complete_num` int DEFAULT NULL,
  `un_effective_num` int DEFAULT NULL,
  `un_handle_num` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_survey_stats
-- ----------------------------
BEGIN;
INSERT INTO `t_survey_stats` VALUES ('ff80808173088af501730973cd5f0027', 0, 0, 0, 0, 0, NULL, 0, 0, 0, 0, 0, NULL, 0, 0, 'ff80808173088af501730971d868000c', 0, 0, 0);
INSERT INTO `t_survey_stats` VALUES ('ff808081730d063c01730d8408040032', 0, 0, 0, 0, 0, NULL, 0, 0, 0, 0, 0, NULL, 0, 0, 'ff80808173099b5a017309a41c070006', 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_survey_style
-- ----------------------------
DROP TABLE IF EXISTS `t_survey_style`;
CREATE TABLE `t_survey_style` (
  `id` varchar(55) NOT NULL,
  `body_bg_color` varchar(255) DEFAULT NULL,
  `body_bg_image` varchar(255) DEFAULT NULL,
  `show_survey_haed` int DEFAULT NULL,
  `survey_bg_color` varchar(255) DEFAULT NULL,
  `survey_bg_image` varchar(255) DEFAULT NULL,
  `survey_content_bg_colo_topr` varchar(255) DEFAULT NULL,
  `survey_content_bg_color_bottom` varchar(255) DEFAULT NULL,
  `survey_content_bg_color_middle` varchar(255) DEFAULT NULL,
  `survey_content_bg_image_bottom` varchar(255) DEFAULT NULL,
  `survey_content_bg_image_middle` varchar(255) DEFAULT NULL,
  `survey_content_bg_image_top` varchar(255) DEFAULT NULL,
  `survey_content_padding_bottom` int DEFAULT NULL,
  `survey_content_padding_left` int DEFAULT NULL,
  `survey_content_padding_right` int DEFAULT NULL,
  `survey_content_padding_top` int DEFAULT NULL,
  `survey_content_width` int DEFAULT NULL,
  `survey_head_bg_color` varchar(255) DEFAULT NULL,
  `survey_head_bg_image` varchar(255) DEFAULT NULL,
  `survey_head_height` int DEFAULT NULL,
  `survey_head_padding_bottom` int DEFAULT NULL,
  `survey_head_padding_left` int DEFAULT NULL,
  `survey_head_padding_right` int DEFAULT NULL,
  `survey_head_padding_top` int DEFAULT NULL,
  `survey_head_width` int DEFAULT NULL,
  `survey_id` varchar(55) DEFAULT NULL,
  `survey_padding_bottom` varchar(255) DEFAULT NULL,
  `survey_padding_left` varchar(255) DEFAULT NULL,
  `survey_padding_right` varchar(255) DEFAULT NULL,
  `survey_padding_top` varchar(255) DEFAULT NULL,
  `survey_style_type` int DEFAULT NULL,
  `survey_width` int DEFAULT NULL,
  `show_body_bi` int DEFAULT NULL,
  `show_survey_bi` int DEFAULT NULL,
  `show_survey_cbim` int DEFAULT NULL,
  `show_survey_hbgi` int DEFAULT NULL,
  `survey_content_bg_color_top` varchar(255) DEFAULT NULL,
  `show_survey_logo` int DEFAULT NULL,
  `survey_logo_image` varchar(255) DEFAULT NULL,
  `question_option_text_color` varchar(255) DEFAULT NULL,
  `question_title_text_color` varchar(255) DEFAULT NULL,
  `survey_note_text_color` varchar(255) DEFAULT NULL,
  `survey_title_text_color` varchar(255) DEFAULT NULL,
  `survey_btn_bg_color` varchar(255) DEFAULT NULL,
  `show_sur_note` int DEFAULT NULL,
  `show_sur_title` int DEFAULT NULL,
  `show_progressbar` int DEFAULT NULL,
  `show_ti_num` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_survey_style
-- ----------------------------
BEGIN;
INSERT INTO `t_survey_style` VALUES ('402880e54794e4a4014794e522060000', '#ffffff', '/file/images/rydbnwol4n62lw6.jpg', NULL, NULL, NULL, NULL, NULL, '#ffffff', NULL, '/file/images/2gi029de7g1521x.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#ffffff', '/file/images/01w071czz9yl5za.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880ea4675ac62014675ac7b3a0000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 1, NULL, 0, '/images/1279780388A5rVCG.jpg', '#2f363b', '#18191a', '#0b0c0d', '#1a1b1c', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e55c733da2015c7340eda10005', '#E8E9EB', '/file/images/r4vs89f647uvnyb.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#b5ba1a', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402881e858e6a24f0158e74968c30006', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#000000', '#000000', '#d5dfe6', '#13171a', '#12b0ee', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880e55ca74afc015ca768373e0000', '#E8E9EB', '/file/images/fm6s7rin7g9zryy.png', NULL, NULL, NULL, NULL, NULL, '#f0f3f5', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#063f5a', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402881e858e6a24f0158e6a3811d0000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#17191a', '#0e0f0f', '#f2f4f5', '#f0f3f5', '#7EA800', 0, 0, 0, 0);
INSERT INTO `t_survey_style` VALUES ('402880e55cb9c629015cb9e0fa5b0007', '#E8E9EB', '/file/images/fm6s7rin7g9zryy.png', NULL, NULL, NULL, NULL, NULL, '#f0f3f5', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#063f5a', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880e55cb9c629015cb9d31eff0000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#17191a', '#0e0f0f', '#f2f4f5', '#f0f3f5', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880e654c939790154c93a76fa0000', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402880e654ae3ec40154ae54c9f70000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880e74df7133e014df733e1d20000', '#E8E9EB', '/file/images/3pv3u4ns5by2uhd.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#b6ccdb', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880e54da29a5b014da2b6481c0011', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#1c1f21', '#121314', '#101214', '#101314', '#12b0ee', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e74df770a9014df7731e950001', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402880e54d38852e014d38863f010000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 1, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 0, 0);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f104e47d0000', '#E8E9EB', '/file/images/9fb98sbm9147pm8.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#159e3e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#0d1012', '#070b0d', '#0b1114', '#0a0d0f', '#12b0ee', NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f1090d600001', '#E8E9EB', '/file/images/twc94he4j7m0gj5.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#dfe4e8', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#0c0f12', '#040608', '#090b0d', '#0b0e0f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f10c0c7e0002', '#E8E9EB', '/file/images/t7c69ulxn9zi830.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#202426', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#0a0c0d', '#06090a', '#a2b0ba', '#cbd2d6', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f10edd0c0003', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f1121f320004', '#E8E9EB', '/file/images/qfsv0ui7nyxmtmu.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#111c36', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#16181a', '#131517', '#dadfe3', '#dde5eb', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f1156b3d0005', '#191e21', '/file/images/zop3vsgn0gjny9r.jpg', NULL, NULL, NULL, NULL, NULL, '#ffffff', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#191c1f', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#FFFFFF', '#E8E9EB', '#e6edf2', '#d5dfe6', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f117022d0006', '#191e21', '/file/images/zop3vsgn0gjny9r.jpg', NULL, NULL, NULL, NULL, NULL, '#ffffff', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#191c1f', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#131414', '#1b1d1f', '#e6edf2', '#d5dfe6', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f13f18ba0007', '#E8E9EB', '/file/images/r4vs89f647uvnyb.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#b5ba1a', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#000000', '#000000', '#d5dfe6', '#13171a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f141f3700008', '#E8E9EB', '/file/images/uj0dawpti7yd6nt.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#bac746', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#0b0c0d', '#0e1012', '#0d0e0f', '#111314', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f1441b000009', '#E8E9EB', '/file/images/j29vaz5uyjmymwa.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#08090a', '#020203', '#7EA800', '#0c0e0f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f1461613000a', '#E8E9EB', '/file/images/3pv3u4ns5by2uhd.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#b6ccdb', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#1c1f21', '#121314', '#101214', '#101314', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f147530d000b', '#E8E9EB', '/file/images/qfm55md6vp3jdoz.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', '', NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#0b0c0d', '#131414', '#0b0c0d', '#101112', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849f0f5e70149f1879a9f0029', '#191e21', '/file/images/iayamhh3qpnybc1.jpg', NULL, NULL, NULL, NULL, NULL, '#ffffff', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#191c1f', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#131414', '#1b1d1f', '#e6edf2', '#d5dfe6', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e849fa446b0149fb1ef12d0001', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 0, 1000, 0, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#333333', '#333333', '#333333', '#222222', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e84a0622a7014a062613280000', '#E8E9EB', '/file/images/qfsv0ui7nyxmtmu.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#111c36', '/file/images/ld6ut7c39l060yl.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880eb49a36bbe0149a3fb3efb0000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 1, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#16181a', '#131517', '#dadfe3', '#dde5eb', '#2b70a1', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880e94cacc77b014cacdf77730056', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880e94cacc77b014cacd11afa0041', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#12b0ee', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880e94e0e8e67014e0eaf4f08000a', '#E8E9EB', '/file/images/8g7ofg67wqpt88o.jpg', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/file/images/ap4z1952ch4x73a.gif', NULL, 150, NULL, NULL, 0, NULL, '402880e74df792d8014df79d0ae40002', NULL, NULL, NULL, NULL, 0, 950, 1, 0, 0, 1, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 0, 0, 1, 0);
INSERT INTO `t_survey_style` VALUES ('402880e94e2b1769014e2b63a44f0001', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f40f2727e0140f29bd1a50055', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880e94e5443fa014e5444d5ff0000', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, '402880e94e54296f014e5429fdbb0000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', 'rgb(99, 101, 102)', 'color: rgb(85, 87, 89)', 'rgb(112, 114, 115)', 'rgb(34, 34, 34)', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880e94e582ea9014e582f31710005', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402880e94e582ea9014e582eee740000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880e94e640383014e640849de0000', '#E8E9EB', '/file/images/t7c69ulxn9zi830.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#202426', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880e94e5e90b1014e5e918cf60000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#0a0c0d', '#06090a', '#a2b0ba', '#cbd2d6', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402880ea484021d10148402e42070000', '#E8E9EB', '/file/images/fm6s7rin7g9zryy.png', NULL, NULL, NULL, NULL, NULL, '#f0f3f5', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#063f5a', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#17191a', '#0e0f0f', '#f2f4f5', '#f0f3f5', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880ea484d83a801484e5a30bf0000', '#E8E9EB', '/file/images/grebumvosj3424f.jpg', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f412e65b601412e719c8c001c', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#16181a', '#0d0e0f', '#7EA800', '#1f2224', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880eb49a435870149a45b3d19002e', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880eb49a3598d0149a3673b2d0018', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, 0, 1);
INSERT INTO `t_survey_style` VALUES ('402880eb49dcabfd0149dcb359370000', '#E8E9EB', '/file/images/6x4otsq9t2uo7yp.png', NULL, NULL, NULL, NULL, NULL, '#f0f3f5', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#13396b', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#17191a', '#0e0f0f', '#f2f4f5', '#f0f3f5', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880eb49dcabfd0149dcbe2db10002', '#E8E9EB', '/file/images/dvnnttlj4g2vpon.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#e8eaeb', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '', NULL, NULL, NULL, NULL, 1, 900, 1, 0, 0, 0, NULL, 0, '/images/1279780388A5rVCG.jpg', '#12191f', '#020405', '#182229', '#0f151a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402880f34abaedac014abaf0e2610005', '#E8E9EB', '/file/images/ci3jgo1tyz76t0o.jpg', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402880f34abaedac014abaee7e6a0000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', NULL, NULL, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('402881e45605583a0156082c41060009', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402880e6548acdf001548ace786c0000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402881e655a6f14f0155a6f1e1770000', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402881e655828e930155859dc7460001', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402881e655aba9d80155abaa78510000', '#E8E9EB', '/file/images/kfe1375aus0sbih.jpg', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402881e94aa9d3e8014aaa00c1740008', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402881e655aba9d80155abac2f2e0002', '#E8E9EB', '/file/images/9fb98sbm9147pm8.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#159e3e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880eb49bd9b6a0149bda109eb0000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#0d1012', '#070b0d', '#0b1114', '#0a0d0f', '#12b0ee', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402881e755fe5e6e0156003b28f60002', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402881e755fe5cbc0155fe5d68940000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402881e858b5c0790158b5cb4b870000', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402881e858b097330158b097cb5b0000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402881e858c4767d0158c477c2a20000', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '402881e858b05b770158b064bd050000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('402881e94aa4d823014aa53e5d5f0002', '#E8E9EB', '/file/images/pj0ryqe045w1i6w.PNG', NULL, NULL, NULL, NULL, NULL, '#f0f3f5', NULL, '/file/images/f3lv18p1q2vq3al.JPG', NULL, NULL, NULL, NULL, NULL, NULL, '#13396b', '/file/images/ua46z3eslje245h.jpg', NULL, 0, NULL, NULL, 20, NULL, '402880eb4a86d272014a86f02bb30009', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 1, NULL, 0, '/diaowen3.0/images/logo/108-108.jpg', '#17191a', '#0e0f0f', '#f2f4f5', '#f0f3f5', '#12b0ee', 1, 1, 0, 0);
INSERT INTO `t_survey_style` VALUES ('4028c6864fda26dc014fda2cc82f0008', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, 'ff8080814fd9ab99014fd9abe2490000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d1a11a2014d1a13651a0000', '#E8E9EB', '/file/images/fm6s7rin7g9zryy.png', NULL, NULL, NULL, NULL, NULL, '#f0f3f5', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#063f5a', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f41044872014106068b620000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#17191a', '#0e0f0f', '#f2f4f5', '#f0f3f5', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d1a9a35014d1cbd780b0000', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f424b7b2801424c24c389004b', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d1d7b7c014d1d7ccbc60000', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f424b7b2801424c3414da0075', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d1d8735014d1d88acde0000', '#E8E9EB', '/file/images/twc94he4j7m0gj5.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#dfe4e8', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f424b7b2801424c1e413e0039', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#0c0f12', '#040608', '#090b0d', '#0b0e0f', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d1d9cf8014d1d9dd6f90004', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d1a1ce8014d1a436441001c', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d1db174014d1e27049e0001', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d1db388014d1e2679670000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d2997f8014d299eeaa30000', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f42081a3c01420914f6a500e7', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d5caf0e014d7aea29d40016', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d4d20c3014d507067750006', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d95b6c7014d95bb44be000c', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8a290d5e4d95b6c7014d95baa60e0002', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d9af3f3014da94ccafd0015', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#3badcc', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f42404933014240b4c84300de', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#fafbfc', '#ffffff', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d9af3f3014da95061300016', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#f7d383', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f424049330142406672de0000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d9af3f3014da95205300017', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#256a9c', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f4226fccc014228287dfc009f', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#000000', '#dfe7ed', '#cad4db', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d9af3f3014da95374e90018', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#10b529', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f42224aa601422318a66000dd', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#ffffff', '#edf5fa', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4d9af3f3014da954fb1f0019', '#07728a', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#1094c4', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f420365ac014204053529009b', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#fafbfc', '#f7f9fa', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4db9e5c2014dbedf254e0021', '#e3e6e8', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#ffffff', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#1b8dde', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f420365ac014204177db600e3', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#f5fbff', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4db9e5c2014dbee0e72f0022', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#2b9ef0', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f4226fccc014228190db9005e', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#fafafa', '#ffffff', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4db9e5c2014dbee26c390023', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#22ba6e', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f42224aa60142230b81d700a9', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#3c4952', '#485259', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a290d5e4db9e5c2014dbee519e10024', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#11cf60', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f42224aa6014222f351bf005d', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#e4e8eb', '#d1d8de', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d14debf014d17e885700038', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d14debf014d1595e6540000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d14debf014d189e09000039', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f4104487201410626f8bf0014', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d1a1ce8014d1a2125b90019', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d1a1ce8014d1a2026f50000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d1da1a8014d1da2ec490000', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#9fcc25', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8ab20e8f42081a3c014208e0a71b008c', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d5cb07b014d75c9a66f0069', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d5cb07b014d75c95561003b', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d8bc1d0014d8bc59b890010', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d8bc1d0014d8bc549370000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d8bff42014d8e4ab43b0034', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '8a29dbf24d8bff42014d8e4a07280007', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('8a29dbf24d9af187014d9f1c92f9000d', '#E8E9EB', '/images/style-model/5629512728352684315.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/style-model/1123/29153737.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#FFFFFF', '/images/style-model/5629512728352684315.png', NULL, 0, NULL, NULL, 0, NULL, '8a290d5e4d437bdb014d46bcb5920002', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/style-model/6608914805422388314.jpg', '#333333', '#333333', '#333333', '#222222', '#7EA800', 1, 1, NULL, NULL);
INSERT INTO `t_survey_style` VALUES ('ff8080814f598672014f59990c710000', '#E8E9EB', '/file/images/6x4otsq9t2uo7yp.png', NULL, NULL, NULL, NULL, NULL, '#f0f3f5', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#13396b', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, '402880e64f53adb1014f53b003a20000', NULL, NULL, NULL, NULL, 0, 900, 1, 0, 0, 0, NULL, 0, '/diaowen_pro/images/logo/108-108.jpg', '#17191a', '#0e0f0f', '#f2f4f5', '#f0f3f5', '#7EA800', 1, 1, 1, 1);
INSERT INTO `t_survey_style` VALUES ('ff8080815a5f028c015a5f490046000b', '#E8E9EB', '/file/images/6x1ml28olol8bo3.png', NULL, NULL, NULL, NULL, NULL, '#FFFFFF', NULL, '/images/1279780388A5rVCG.jpg', NULL, NULL, NULL, NULL, NULL, NULL, '#262a2e', '/images/1279780388A5rVCG.jpg', NULL, 0, NULL, NULL, 0, NULL, 'ff8080815a5f028c015a5f47165f0000', NULL, NULL, NULL, NULL, 0, 900, 0, 0, 0, 0, NULL, 0, '/images/logo/108-108.jpg', '#111314', '#0d0e0f', '#c5d5e0', '#bac4cc', '#7EA800', 1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_db_backup
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_db_backup`;
CREATE TABLE `t_sys_db_backup` (
  `id` varchar(55) NOT NULL,
  `backup_name` varchar(255) DEFAULT NULL,
  `backup_path` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_db_backup
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_sys_email
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_email`;
CREATE TABLE `t_sys_email` (
  `id` varchar(55) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_check` int DEFAULT NULL,
  `post` varchar(255) DEFAULT NULL,
  `send_email` varchar(255) DEFAULT NULL,
  `state` int DEFAULT NULL,
  `stmp_pwd` varchar(255) DEFAULT NULL,
  `stmp_server` varchar(255) DEFAULT NULL,
  `stmp_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_email
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(55) NOT NULL,
  `activation_code` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `edu_quali` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `find_pwd_code` varchar(255) DEFAULT NULL,
  `find_pwd_last_date` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `sha_password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', NULL, '2013-03-21 21:15:21', NULL, NULL, '2013-03-21 21:15:21', 1, 'service@diaowen.net', NULL, NULL, '2013-03-21 21:15:34', 'ics', 'jesse', NULL, 1, '7c4a8d09ca3762af61e59520943dc26494f8941b', 1, 1);
INSERT INTO `t_user` VALUES ('ff808081730df24101730e01ef680000', NULL, NULL, NULL, '', '2020-07-02 00:31:15', NULL, '1447907328@qq.com', NULL, NULL, NULL, 'jesse', 'jesse', NULL, NULL, '7c4a8d09ca3762af61e59520943dc26494f8941b', 1, 1);
INSERT INTO `t_user` VALUES ('ff808081730df24101730e074dd40001', NULL, NULL, NULL, '', '2020-07-02 00:37:07', NULL, '12233233@qq.co', NULL, NULL, NULL, 'jessely6', 'jesse', NULL, NULL, '7c4a8d09ca3762af61e59520943dc26494f8941b', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for tracker
-- ----------------------------
DROP TABLE IF EXISTS `tracker`;
CREATE TABLE `tracker` (
  `id` varchar(55) NOT NULL,
  `data_id` varchar(255) DEFAULT NULL,
  `data_type` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tracker
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
