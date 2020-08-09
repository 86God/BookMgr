/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : bookmgr

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 09/08/2020 13:48:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '123456', '20200727170642634.jpg');

-- ----------------------------
-- Table structure for t_books
-- ----------------------------
DROP TABLE IF EXISTS `t_books`;
CREATE TABLE `t_books`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float(10, 0) NULL DEFAULT NULL,
  `num` int(10) NULL DEFAULT NULL,
  `publishing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(3) NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_type`(`type`) USING BTREE,
  CONSTRAINT `fk_book_type` FOREIGN KEY (`type`) REFERENCES `t_type` (`type`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_books
-- ----------------------------
INSERT INTO `t_books` VALUES (18, '深度学习', 'Ian Goodfellow', 55, 100, '人民邮电出版社', '《深度学习》由全球知名的三位专家Ian Goodfellow、Yoshua Bengio 和Aaron Courville撰写，是深度学习领域奠基性的经典教材。', 1, 'static/img/065.PNG');
INSERT INTO `t_books` VALUES (19, '浪潮之巅', '吴军', 70, 100, '人民邮电出版社', '主要讲述了IT行业的发展', 1, 'static/img/066.PNG');
INSERT INTO `t_books` VALUES (21, 'Java编程思想', 'Bruce Eckel', 50, 100, '人民邮电出版社', '本书讲解Java程序设计的相关知识及其编程方法.', 2, 'static/img/068.PNG');
INSERT INTO `t_books` VALUES (22, '算法导论', '科尔曼', 45, 100, '机械工业出版社', '本书全面论述了算法的内容，从一定深度上涵盖了算法的诸多方面，同时其讲授和分析方法又兼顾了各个层次读者的接受能力。', 1, 'static/img/069.PNG');
INSERT INTO `t_books` VALUES (23, 'MySQL', '施瓦茨', 90, 5000, '电子工业出版社', '本书以当前最流行MySQL5.6作为平台，包含MySQL基础(含习题)、实验、综合应用实习和附录。', 1, 'static/img/070.PNG');
INSERT INTO `t_books` VALUES (24, '乡土中国', '费孝通', 21, 5000, '人民出版社', '作者用通俗、简洁的语言对中国基层传统社会的主要特征进行了概述和分析，全面展现了乡土社会面貌和内在精神气质.', 2, 'static/img/071.PNG');
INSERT INTO `t_books` VALUES (25, '阅读一本书 ', '[美]莫提默·  ', 30, 5000, '商务印书馆', '主要论述指导如何通过阅读增进理解力。', 2, 'static/img/072.PNG');
INSERT INTO `t_books` VALUES (26, ' 娱乐至死', '波兹曼　', 23, 5000, '中信出版社', '解析了美国社会由印刷统治转变为电视统治，得出了由此导致社会公共话语权的特征由曾经的理性、秩序、逻辑性，逐渐转变为脱离语境、肤浅、碎化，一切公共话语以娱乐的方式出现的现象.', 2, 'static/img/073.PNG');
INSERT INTO `t_books` VALUES (27, '自由在高处', '熊培云', 24, 5000, '新星出版社', '这是我的人生，我必让它自由。每个人都应该是自己人生的领导者。那些能够带领千军万马的人，未必能带领好自己。要么成为自己，要么一无所成。', 2, 'static/img/074.PNG');
INSERT INTO `t_books` VALUES (28, '埃博拉 ', '理查德·', 44, 5000, '上海译文出版社', '基于这一史料，采访大量亲历者后出版非虚构作品。', 2, 'static/img/075.PNG');
INSERT INTO `t_books` VALUES (29, ' 第二性', '西蒙娜', 83, 5000, '上海译文出版社', '从生物学、精神分析学、历史和女性神话在文学中的体现等方面来分析女性的处境', 2, 'static/img/076.PNG');
INSERT INTO `t_books` VALUES (30, '鱼翅与花椒', '邓洛普 ', 47, 5000, '上海译文出版社', '每个人的舌尖都是故乡。', 2, 'static/img/077.PNG');
INSERT INTO `t_books` VALUES (31, '中国社会', '李强', 41, 5000, '生活.读书.新知三联书店', '是由中国社会科学院创办并主管的以出版人文社会科学学术著作为主的国家级出版社。', 2, 'static/img/078.PNG');
INSERT INTO `t_books` VALUES (32, '大国大城 ', '陆铭', 48, 5000, '上海人民出版社', '《大国大城》关注的是中国当前城乡经济发展中切实存在的现实问题。', 2, 'static/img/079.PNG');
INSERT INTO `t_books` VALUES (33, '乡下人 ', 'J.D. 万斯 ', 36, 5000, '江苏凤凰文艺出版社', '真实讲述了社会、地区和阶层衰落会给一生下来就深陷其中的人带来什么样的影响。', 2, 'static/img/080.PNG');
INSERT INTO `t_books` VALUES (34, '我的抑郁症', '伊丽莎白', 25, 50000, '新星出版社', '《我的抑郁症》以独特个性的语言和极富感染力的素描，讲述了一个抑郁症患者如何与这种最常见的疾病作斗争的经历。全书真情流露，又不失幽默风趣，在美国出版后引起轰动，被誉为“一部让人摆脱抑郁的杰作”。', 4, 'static/img/041.PNG');
INSERT INTO `t_books` VALUES (35, '四圣心源', '黄元御', 16, 50000, '中国中医药出版社', '《四圣心源》，清·黄元御著。黄元御（公元1705—1758年），名玉路，字元御，一字坤载，号研农，別号玉楸子，山东昌邑人，清代著名医家。', 4, 'static/img/042.PNG');
INSERT INTO `t_books` VALUES (36, '疾病考古学', '夏洛特', 36, 50000, '山东画报出版社', '《疾病考古学》给我们呈献了如何运用古病理学最先进的科学方法鉴定古代人类曾经经受的常见疾病和损伤。夏洛特·罗伯茨和基思·曼彻斯特两位学者从来自世界各地的文献资料、艺术作品和古代人骨遗存中寻找证据。', 4, 'static/img/043.PNG');
INSERT INTO `t_books` VALUES (37, '洞穴奇案', '彼得·萨伯', 23, 50000, '生活新知出版社', ' 五名洞穴探险者受困山洞，水尽粮绝，无法在短期内获救。为了维生以待救援，大家决定抽签吃掉一人，牺牲他以救活其余四人。', 3, 'static/img/044.PNG');
INSERT INTO `t_books` VALUES (38, '心理研究', '李玫瑾', 40, 50000, '中国人民公安大学出版社', ' 《犯罪心理研究》共有13章。围绕着“犯罪心理研究与犯罪防控”的主题大致论述了以下内容：如何以人为对象开展犯罪防控研究；犯罪人的基本类型；犯罪心理的不同表现；相关的防控建议。', 3, 'static/img/045.PNG');
INSERT INTO `t_books` VALUES (39, '异乡人', '加缪', 28, 50000, '北京大学出版社', ' 加缪形塑的“现代荒谬英雄”默尔索于是诞生，深深影响了后世无数孤独的灵魂，让我们得以正视自己混乱、无依的处境。', 3, 'static/img/046.PNG');
INSERT INTO `t_books` VALUES (40, '聪明投资者', '本杰明', 58, 50000, '人民邮电出版社', ' 《聪明的投资者》首先明确了“投资”与“投机”的区别，指出聪明的投资者当如何确定预期收益。本书着重介绍防御型投资者与积极型投资者的投资组合策略，论述了投资者如何应对市场波动。', 6, 'static/img/047.PNG');
INSERT INTO `t_books` VALUES (41, '经济学原理', '曼昆', 56, 50000, '北京大学出版社', ' 《经济学原理》是目前国内市场上受欢迎的引进版经济学教材之一，其较大特点是它的“学生导向”，它更多地强调经济学原理的应用和政策分析，而非经济学模型。', 6, 'static/img/048.PNG');
INSERT INTO `t_books` VALUES (42, '货币战争', '宋鸿兵', 39, 50000, '中信出版社', ' 在全球经济发展的过程中，货币起到了举足轻重的作用。通胀、通缩和泡沫越来越频繁地影响到我们的生活，但是货币背后隐藏的集团利益之争，却在很长时间里不为人所知。', 6, 'static/img/049.PNG');
INSERT INTO `t_books` VALUES (43, '足球智商', '丹·布兰克', 59, 50000, '北京大学技术出版社', ' 这是一本关于球场上做决定的书；这是一本足球淘金的书，涉及的东西很细小，但足以左右比赛的胜负。作者将自己的经验通过对102个细节问题的阐述表达出来，帮助你全方位地审视自己可能存在的问题及其根源。', 7, 'static/img/050.PNG');
INSERT INTO `t_books` VALUES (44, '陈氏太极拳', '沈家桢', 15, 50000, '人民体育出版社', ' 陈式太极拳创始于明末清初的著名拳师陈王廷。陈王廷是河南温县陈家沟人。从陈王廷起，陈氏世代传习太极拳，不断对原有的拳套进行加工提炼，逐步开成近代所流传的一、二路拳套。', 7, 'static/img/051.PNG');
INSERT INTO `t_books` VALUES (45, '李小龙技击', '李小龙', 68, 50000, '北京联合出版公司', ' 《李小龙技击法》是一代功夫之王李小龙的存世遗稿，由李小龙遗孀琳达女士和《黑带》杂志创始人水户上原整理而成，凝聚了李小龙毕生的武学精要。', 7, 'static/img/052.PNG');
INSERT INTO `t_books` VALUES (46, '乔丹篮球', '肯特', 25, 50000, '人民体育出版社', ' 从1984年到1998年，对于爱好篮球的人们得很幸运的，因为，他们有幸亲眼目睹乔丹在创造奇观，在写下神话。', 7, 'static/img/053.PNG');
INSERT INTO `t_books` VALUES (54, '常见病处方', '宋学立', 32, 50000, '化工工业出版社', '本书版已出版五年，受到了专业读者的关注和信任。本次再版修订在保持版鲜明特色的同时，增加了十余种常见，并对全书所有处方进行了逐一审查，以便地体现临床规范的新理念、新方法和新技术', 4, 'static/img/000.JPG');
INSERT INTO `t_books` VALUES (55, '图解本草纲目', '组织编写', 30, 50000, '化学工业出版社', '本书将我国医药巨著《本草纲目》进行了精编整理，保留了日常能够购买使用的本草500 余种及切实可用的复方3000 余首，并经专家逐一考证审定，绘制了精美的本草图谱', 4, 'static/img/001.JPG');
INSERT INTO `t_books` VALUES (56, '皇帝内经', '江澄', 75, 50000, '北京联合出版公司', '黄帝内经全集正版原著白话版中医医学类中药书籍大全，四大经典名著自学入门，皇帝内经皇帝内径，中药学全套', 4, 'static/img/002.JPG');
INSERT INTO `t_books` VALUES (57, '儿童康复学', '李晓捷', 78, 50000, '人民卫生出版社', '人卫版，儿童康复学，李晓捷，十三五规划医学教材 ，儿童康复治疗儿童康复医学类专业书，儿童康复评定技巧书籍儿科医生用书', 4, 'static/img/003.JPG');
INSERT INTO `t_books` VALUES (58, '医学心理学', '季建林', 38, 50000, '复旦大学出版社', '《医学心理学》在第三版的基础上进行了全面修订，重点叙述了应激、医患关系、进食障碍、睡眠障碍、慢性疼痛、自杀预防等临床常见的医学心理学内容', 4, 'static/img/004.JPG');
INSERT INTO `t_books` VALUES (59, '中医临床思维', '柳文', 28, 50000, '人民卫生出版社', '《中医临床思维》主要针对刚进入临床进行规范化培训的住院医师而编写，同时对中医药院校实习医师、临床专业研究生也有一定的指导作用', 4, 'static/img/005.JPG');
INSERT INTO `t_books` VALUES (96, '编程之美', '张三', 50, 1, '陕西人民出版社', '这是一本关于编程的书籍', 1, 'static/img/065.PNG');
INSERT INTO `t_books` VALUES (97, 'python', '张三', 50, 1, '陕西理工大学', '关于python', 1, 'static/img/065.PNG');
INSERT INTO `t_books` VALUES (99, '斗罗大陆', '唐家三少', 50, 1, '清华大学出版社', '关于斗罗大陆的小说', 8, 'static/img\\20200727170332845.jpg');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL,
  `book_id` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `back_time` datetime(0) NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_id`(`user_id`) USING BTREE,
  INDEX `fk_book_id`(`book_id`) USING BTREE,
  CONSTRAINT `fk_book_id` FOREIGN KEY (`book_id`) REFERENCES `t_books` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES (3, 11, 34, '2020-07-16 14:53:22', '2020-07-29 14:53:22', 1);
INSERT INTO `t_log` VALUES (4, 12, 43, '2020-07-17 14:53:22', '2020-07-29 14:53:22', 1);
INSERT INTO `t_log` VALUES (6, 12, 35, '2020-07-14 19:01:51', '2020-07-22 20:55:20', 1);
INSERT INTO `t_log` VALUES (7, 11, 55, '2020-07-22 20:16:22', '2020-07-22 20:56:26', 1);
INSERT INTO `t_log` VALUES (8, 12, 54, '2020-07-22 20:16:36', '2020-07-22 22:23:59', 1);
INSERT INTO `t_log` VALUES (9, 10, 40, '2020-07-22 20:57:37', '2020-07-22 22:53:00', 1);
INSERT INTO `t_log` VALUES (10, 11, 39, '2020-07-22 20:57:48', '2020-07-24 09:37:31', 1);
INSERT INTO `t_log` VALUES (11, 12, 38, '2020-07-22 20:57:55', NULL, 0);
INSERT INTO `t_log` VALUES (12, 12, 38, '2020-07-22 20:57:56', NULL, 0);
INSERT INTO `t_log` VALUES (14, 12, 28, '2020-07-22 20:59:19', NULL, 0);
INSERT INTO `t_log` VALUES (15, 1, 34, '2020-07-22 22:27:16', '2020-07-22 22:57:06', 1);
INSERT INTO `t_log` VALUES (16, 1, 45, '2020-07-22 22:55:59', '2020-07-24 09:38:40', 1);
INSERT INTO `t_log` VALUES (17, 1, 43, '2020-07-22 23:18:54', NULL, 0);
INSERT INTO `t_log` VALUES (18, 1, 44, '2020-07-24 09:36:53', NULL, 0);
INSERT INTO `t_log` VALUES (19, 17, 40, '2020-07-24 15:16:15', NULL, 0);

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(255) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, 1, '计算机');
INSERT INTO `t_type` VALUES (2, 2, '社会科学');
INSERT INTO `t_type` VALUES (3, 3, '法律');
INSERT INTO `t_type` VALUES (4, 4, '医学');
INSERT INTO `t_type` VALUES (5, 5, '法律');
INSERT INTO `t_type` VALUES (6, 6, '经济');
INSERT INTO `t_type` VALUES (7, 7, '体育');
INSERT INTO `t_type` VALUES (8, 8, '小说');
INSERT INTO `t_type` VALUES (9, 9, '历史');
INSERT INTO `t_type` VALUES (10, 10, '期刊');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '周凡1', '18666666666', '男', 20);
INSERT INTO `t_user` VALUES (10, '张耿英', '1888888', '男', 18);
INSERT INTO `t_user` VALUES (11, '何文涛', '18888888888', '男', 18);
INSERT INTO `t_user` VALUES (12, '杨文涛', '18888888888', '女', 38);
INSERT INTO `t_user` VALUES (17, '梁佳坤', '18999999999', '男', 18);
INSERT INTO `t_user` VALUES (18, '李亮', '15566666666', '女', 18);
INSERT INTO `t_user` VALUES (21, '大胖', '18944445555', '男', 18);

SET FOREIGN_KEY_CHECKS = 1;
