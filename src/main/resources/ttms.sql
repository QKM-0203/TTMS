CREATE TABLE movie_user (
                            `id` bigint(20) NOT NULL,
                            `accounts` varchar(64) DEFAULT NULL COMMENT '用户名',
                            `password` varchar(64) DEFAULT NULL COMMENT '密码',
                            `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
                            `create_time` datetime DEFAULT NULL COMMENT '注册时间',
                            `icon` varchar(500) DEFAULT NULL COMMENT '头像',
                            `gender` varchar(5) DEFAULT NULL COMMENT '性别',
                            `birthday` date DEFAULT NULL COMMENT '生日',
                            `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
                            `job` varchar(100) DEFAULT NULL COMMENT '职业',
                            `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `idx_username` (`accounts`),
                            UNIQUE KEY `idx_phone` (`nickname`)
) DEFAULT CHARSET=utf8 COMMENT='用户表';
