SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源（权限）';

-- ----------------------------
-- Table structure for t_start_role
-- ----------------------------
DROP TABLE IF EXISTS `t_start_role`;
CREATE TABLE `t_start_role`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否删除',
    `uuid`        varchar(36) NOT NULL COMMENT '全局唯一ID',
    `role_name`   varchar(255) DEFAULT NULL COMMENT '角色名称',
    `parent_uuid` varchar(36)  DEFAULT NULL COMMENT '父级角色UUID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色（用户组）';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与权限关系表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关系表';

SET
FOREIGN_KEY_CHECKS = 1;
