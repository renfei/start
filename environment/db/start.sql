SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_cms_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_tag`;
CREATE TABLE `t_cms_tag`
(
    `id`          bigint(20) NOT NULL,
    `en_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `zh_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `describe`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签表';

-- ----------------------------
-- Records of t_cms_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_start_secret_key
-- ----------------------------
DROP TABLE IF EXISTS `t_start_secret_key`;
CREATE TABLE `t_start_secret_key`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    `uuid`        varchar(36) NOT NULL COMMENT '全局唯一ID',
    `public_key`  text CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '公钥',
    `private_key` text CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '私钥',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秘钥表';

-- ----------------------------
-- Records of t_start_secret_key
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cms_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_post_tag`;
CREATE TABLE `t_cms_post_tag`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `tag_id`      bigint(20) unsigned NOT NULL,
    `target_id`   bigint(20) unsigned DEFAULT NULL,
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章与标签关系表';

-- ----------------------------
-- Records of t_cms_post_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cms_category
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_category`;
CREATE TABLE `t_cms_category`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `en_name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `zh_name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `featured_image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '特色图像',
    `create_time`    datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time`    datetime DEFAULT NULL COMMENT '更新时间',
    `is_deleted`     tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类表';

-- ----------------------------
-- Records of t_cms_category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_start_role
-- ----------------------------
DROP TABLE IF EXISTS `t_start_role`;
CREATE TABLE `t_start_role`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `create_time`  datetime    NOT NULL COMMENT '创建时间',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    `is_deleted`   tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    `uuid`         varchar(36) NOT NULL COMMENT '全局唯一ID',
    `role_en_name` varchar(255) DEFAULT NULL COMMENT '代码中角色名',
    `role_name`    varchar(255) DEFAULT NULL COMMENT '角色名称',
    `parent_uuid`  varchar(36)  DEFAULT NULL COMMENT '父级角色UUID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统角色（用户组）';

-- ----------------------------
-- Records of t_start_role
-- ----------------------------
BEGIN;
INSERT INTO `t_start_role`
VALUES (1, '2021-04-29 15:46:12', NULL, 0, '3F6D6191-E510-47E2-ACEA-BB2ACA1EF49C', 'ROLE_TESTER', '单元测试员', NULL);
INSERT INTO `t_start_role`
VALUES (2, '2021-04-29 15:56:44', NULL, 0, '629CB1F3-3F69-456A-BDEF-23267DD053C0', 'ROLE_TESTER_TWO', '单元测试员二号', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_cms_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_comments`;
CREATE TABLE `t_cms_comments`
(
    `id`             bigint(20) NOT NULL,
    `target_id`      bigint(20) unsigned NOT NULL COMMENT '目标ID',
    `author`         text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作者名称',
    `author_email`   text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者邮箱',
    `author_url`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者链接',
    `author_IP`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者IP',
    `author_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者物理地址',
    `addtime`        datetime NOT NULL COMMENT '评论时间',
    `content`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
    `is_delete`      tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    `parent_id`      bigint(20) unsigned DEFAULT NULL COMMENT '父级评论ID',
    `is_owner`       tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是官方回复',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章评论表';

-- ----------------------------
-- Records of t_cms_comments
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_cms_posts
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_posts`;
CREATE TABLE `t_cms_posts`
(
    `id`                    bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `category_id`           bigint(20) unsigned NOT NULL COMMENT '文章分类',
    `featured_image`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '特色图像',
    `title`                 text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
    `content`               longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
    `is_original`           tinyint(1) unsigned NOT NULL COMMENT '是否原创文章',
    `source_url`            text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原文链接',
    `source_name`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章来源名称',
    `views`                 bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
    `thumbs_up`             bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞',
    `thumbs_down`           bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩',
    `release_time`          datetime NOT NULL COMMENT '发布时间',
    `add_time`              datetime NOT NULL COMMENT '添加时间',
    `describes`             text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章简介用于SEO',
    `keyword`               text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关键字用于SEO',
    `is_delete`             tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '软删除',
    `is_comment`            tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许评论',
    `avg_views`             double   NOT NULL DEFAULT 0 COMMENT '日均浏览量',
    `avg_comment`           double   NOT NULL DEFAULT 0 COMMENT '日均评论量',
    `page_rank`             double   NOT NULL DEFAULT 10000 COMMENT '权重排序',
    `page_rank_update_time` datetime          DEFAULT NULL COMMENT '权重更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of t_cms_posts
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_start_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_start_operation_log`;
CREATE TABLE `t_start_operation_log`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `oper_date`      datetime NOT NULL COMMENT '操作时间',
    `oper_user_uuid` varchar(36)  DEFAULT NULL COMMENT '用户编号',
    `oper_user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
    `oper_type`      varchar(255) DEFAULT NULL COMMENT '操作类型',
    `oper_model`     varchar(255) DEFAULT NULL COMMENT '操作模块',
    `oper_ip`        varchar(255) DEFAULT NULL COMMENT '操作IP',
    `oper_describe`  text         DEFAULT NULL COMMENT '操作描述',
    `class_name`     varchar(255) DEFAULT NULL COMMENT '执行类名',
    `method_name`    varchar(255) DEFAULT NULL COMMENT '执行方法名',
    `params`         longtext     DEFAULT NULL COMMENT '执行参数',
    `returning`      longtext     DEFAULT NULL COMMENT '返回内容',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of t_start_operation_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_start_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_start_role_permission`;
CREATE TABLE `t_start_role_permission`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `create_time`     datetime    NOT NULL COMMENT '创建时间',
    `update_time`     datetime DEFAULT NULL COMMENT '更新时间',
    `is_deleted`      tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    `uuid`            varchar(36) NOT NULL COMMENT '全局唯一ID',
    `role_uuid`       varchar(36) NOT NULL COMMENT '角色UUID',
    `permission_uuid` varchar(36) NOT NULL COMMENT '权限UUID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色与权限关系表';

-- ----------------------------
-- Records of t_start_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_start_role_permission`
VALUES (1, '2021-04-29 15:46:46', NULL, 0, '5C460DFE-57A7-4E9B-AB18-09A60A3D3770',
        '3F6D6191-E510-47E2-ACEA-BB2ACA1EF49C', '93215229-E1BE-4F81-BE15-BF3A8B2D277B');
INSERT INTO `t_start_role_permission`
VALUES (2, '2021-04-29 15:58:12', NULL, 0, '7EE81321-468E-4E17-8AAC-5AA52158D134',
        '629CB1F3-3F69-456A-BDEF-23267DD053C0', 'FD177621-B15A-411A-9E9D-636ADBC37901');
COMMIT;

-- ----------------------------
-- Table structure for t_start_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_start_user_role`;
CREATE TABLE `t_start_user_role`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    `uuid`        varchar(36) NOT NULL COMMENT '全局唯一ID',
    `user_uuid`   varchar(36) NOT NULL COMMENT '用户UUID',
    `role_uuid`   varchar(36) NOT NULL COMMENT '角色UUID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色关系表';

-- ----------------------------
-- Records of t_start_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_start_user_role`
VALUES (1, '2021-04-29 15:55:51', NULL, 0, '48698FC9-6FB4-4E86-878A-3A00CDCB1FDA',
        'BBAD927D-CF9F-4C39-825F-A4935E4524AC', '3F6D6191-E510-47E2-ACEA-BB2ACA1EF49C');
COMMIT;

-- ----------------------------
-- Table structure for t_start_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_start_permission`;
CREATE TABLE `t_start_permission`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `create_time`    datetime     NOT NULL COMMENT '创建时间',
    `update_time`    datetime DEFAULT NULL COMMENT '更新时间',
    `is_deleted`     tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    `uuid`           varchar(36)  NOT NULL COMMENT '全局唯一ID',
    `resource_name`  varchar(255) NOT NULL COMMENT '资源名称',
    `request_method` varchar(255) NOT NULL COMMENT '请求方法',
    `resource_url`   varchar(255) NOT NULL COMMENT '资源路径',
    `resource_type`  varchar(255) NOT NULL COMMENT '资源类型，菜单、接口、按钮',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统资源（权限）';

-- ----------------------------
-- Records of t_start_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_start_permission`
VALUES (1, '2021-04-29 15:44:58', NULL, 0, '93215229-E1BE-4F81-BE15-BF3A8B2D277B', '测试用例', 'get', '/api/test/testOne',
        'API');
INSERT INTO `t_start_permission`
VALUES (2, '2021-04-29 15:57:33', NULL, 0, 'FD177621-B15A-411A-9E9D-636ADBC37901', '测试用例二', 'get', '/api/test/testTwo',
        'API');
COMMIT;

-- ----------------------------
-- Table structure for t_start_user
-- ----------------------------
DROP TABLE IF EXISTS `t_start_user`;
CREATE TABLE `t_start_user`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `create_time`       datetime    NOT NULL COMMENT '创建时间',
    `update_time`       datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `is_deleted`        tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    `uuid`              varchar(36) NOT NULL COMMENT '全局唯一ID',
    `user_name`         varchar(255)                                                  DEFAULT NULL COMMENT '用户名',
    `password`          varchar(255)                                                  DEFAULT NULL COMMENT '密码',
    `email`             varchar(255)                                                  DEFAULT NULL COMMENT '电子邮箱',
    `phone`             varchar(255)                                                  DEFAULT NULL COMMENT '手机号',
    `registration_date` datetime    NOT NULL COMMENT '注册时间',
    `totp`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '两步验证',
    `registration_ip`   varchar(255) CHARACTER SET utf8mb4                            DEFAULT NULL COMMENT '注册IP地址',
    `trial_error_times` int(11) NOT NULL DEFAULT 0 COMMENT '密码试错次数',
    `lock_time`         datetime                                                      DEFAULT NULL COMMENT '锁定时间',
    `state_code`        int(11) NOT NULL DEFAULT 0 COMMENT '状态码：0全都未验证；1邮箱验证；2手机验证；3邮箱和手机都验证',
    `last_name`         varchar(255) CHARACTER SET utf8mb4                            DEFAULT NULL COMMENT '姓氏',
    `first_name`        varchar(255) CHARACTER SET utf8mb4                            DEFAULT NULL COMMENT '名字',
    `last_login`        date                                                          DEFAULT NULL COMMENT '最后登录时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_start_user
-- ----------------------------
BEGIN;
INSERT INTO `t_start_user`
VALUES (1, '2021-04-28 11:42:57', NULL, 0, 'BBAD927D-CF9F-4C39-825F-A4935E4524AC', 'tester',
        'sha256:64000:18:vWLIwAgt1Q5SzrYDdIgQzTxi+PIpC08H:XddskjIqWV77/Yr5KtzjEPlw', 'i@renfei.net', NULL,
        '2021-04-28 11:44:05', NULL, '127.0.0.1', 0, NULL, 1, NULL, NULL, NULL);
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
