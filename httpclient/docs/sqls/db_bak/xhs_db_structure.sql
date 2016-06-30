SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `baiduzd_answer`;
CREATE TABLE `baiduzd_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catid` int(11) DEFAULT NULL,
  `newsid` int(11) DEFAULT NULL,
  `comment` text,
  `nick` varchar(24) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `news_type` varchar(64) NOT NULL DEFAULT 'baiduzd',
  PRIMARY KEY (`id`),
  KEY `news_idx` (`newsid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `baiduzd_answer_token`;
CREATE TABLE `baiduzd_answer_token` (
  `answer_token` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`answer_token`),
  UNIQUE KEY `answer_token_UNIQUE` (`answer_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `baiduzd_question`;
CREATE TABLE `baiduzd_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(24) DEFAULT NULL,
  `type` varchar(24) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` text,
  `url` varchar(255) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `last_comment_time` datetime NOT NULL,
  `content_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=785 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `baiduzd_question_token`;
CREATE TABLE `baiduzd_question_token` (
  `question_token` bigint(20) NOT NULL,
  PRIMARY KEY (`question_token`),
  UNIQUE KEY `question_token_UNIQUE` (`question_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT '0',
  `catname` varchar(24) DEFAULT NULL,
  `enabled` enum('1','0') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catid` int(11) DEFAULT NULL,
  `newsid` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `nick` varchar(24) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `news_type` varchar(64) DEFAULT NULL,
  `md5char` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `newsid_idx` (`newsid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5960008 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `common_comments`;
CREATE TABLE `common_comments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cate_id` int(11) unsigned DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `nick` varchar(64) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `news_type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=utf8 COMMENT='通用评论表';

DROP TABLE IF EXISTS  `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(24) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `modtime` datetime DEFAULT NULL,
  `key_t` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `http_proxy_info`;
CREATE TABLE `http_proxy_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `port` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `type` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `level` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ip` (`ip`,`port`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS  `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(24) DEFAULT NULL,
  `type` varchar(24) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` text,
  `url` varchar(255) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `last_comment_time` datetime NOT NULL COMMENT '上一次抓取评论的时间',
  `is_pic` varchar(45) NOT NULL,
  `content_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109371 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `nick`;
CREATE TABLE `nick` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(128) DEFAULT NULL,
  `flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '0=无效，1=有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  KEY `ix_nick_flag` (`flag`)
) ENGINE=InnoDB AUTO_INCREMENT=154720443 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `old_comments`;
CREATE TABLE `old_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catid` int(11) DEFAULT NULL,
  `newsid` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `nick` varchar(24) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `news_type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `newsid_idx` (`newsid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3217436 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `sh_admin_user`;
CREATE TABLE `sh_admin_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sh_company_id` int(11) unsigned NOT NULL,
  `realname` varchar(64) DEFAULT NULL COMMENT '管理用户真实姓名',
  `nickname` varchar(64) NOT NULL COMMENT '管理用户昵称（登陆名）',
  `password` varchar(45) NOT NULL COMMENT '登陆密码',
  `sex` enum('0','1') DEFAULT '1' COMMENT '性别：0-女，1-男，默认为1',
  `address` varchar(64) DEFAULT NULL COMMENT '通讯地址',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(24) DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `enabled` enum('0','1') NOT NULL DEFAULT '1' COMMENT '是否启用管理用户：0-不启用，1-启用',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `position` varchar(16) DEFAULT NULL COMMENT '职位',
  `head_pic` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  KEY `uix_sh_admin_user` (`sh_company_id`,`nickname`),
  KEY `fk_sh_admin_user_sh_company1_idx` (`sh_company_id`),
  CONSTRAINT `fk_sh_admin_user_sh_company1` FOREIGN KEY (`sh_company_id`) REFERENCES `sh_company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='后台管理用户表';

DROP TABLE IF EXISTS  `sh_admin_user_has_sh_authority_group`;
CREATE TABLE `sh_admin_user_has_sh_authority_group` (
  `sh_admin_user_id` int(11) unsigned NOT NULL,
  `sh_authority_group_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`sh_admin_user_id`,`sh_authority_group_id`),
  KEY `fk_sh_admin_user_has_sh_authority_group_sh_authority_group1_idx` (`sh_authority_group_id`),
  KEY `fk_sh_admin_user_has_sh_authority_group_sh_admin_user1_idx` (`sh_admin_user_id`),
  CONSTRAINT `fk_sh_admin_user_has_sh_authority_group_sh_admin_user1` FOREIGN KEY (`sh_admin_user_id`) REFERENCES `sh_admin_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sh_admin_user_has_sh_authority_group_sh_authority_group1` FOREIGN KEY (`sh_authority_group_id`) REFERENCES `sh_authority_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sh_article`;
CREATE TABLE `sh_article` (
  `id` varchar(32) NOT NULL COMMENT '新华社文章id',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `old_title` varchar(255) DEFAULT NULL COMMENT '原标题',
  `publish_date` datetime DEFAULT NULL COMMENT '文章发布时间',
  `comment_total` varchar(32) DEFAULT NULL COMMENT '评论数量',
  `xhs_channel` varchar(32) DEFAULT NULL COMMENT '频道名称',
  `detail_url` varchar(512) DEFAULT NULL,
  `category` varchar(6) DEFAULT '0' COMMENT '0 =要闻,1 =体育,2 =国际,3 =财经,4 =军事,5 =汽车,6 =图片,7 =视频,8 =评论,9 =社会,10=科技,11=动新闻',
  `cmt_num` int(10) unsigned NOT NULL DEFAULT '0',
  `news_num` int(10) unsigned NOT NULL DEFAULT '0',
  `auto_sum` int(10) unsigned NOT NULL DEFAULT '0',
  `shinc_sum` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '本地评论数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新华社新闻全集表';

DROP TABLE IF EXISTS  `sh_authority`;
CREATE TABLE `sh_authority` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sh_company_id` int(11) unsigned NOT NULL,
  `authority` varchar(32) NOT NULL COMMENT '权限描述',
  `remark` varchar(32) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `uix_sh_autority` (`authority`),
  KEY `fk_sh_authority_sh_company1_idx` (`sh_company_id`),
  CONSTRAINT `fk_sh_authority_sh_company1` FOREIGN KEY (`sh_company_id`) REFERENCES `sh_company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='管理用户权限表';

DROP TABLE IF EXISTS  `sh_authority_group`;
CREATE TABLE `sh_authority_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sh_company_id` int(11) unsigned NOT NULL,
  `name` varchar(45) NOT NULL COMMENT '组名代码',
  `remark` varchar(45) DEFAULT NULL COMMENT '组名解释',
  PRIMARY KEY (`id`),
  KEY `uix_authority_group` (`sh_company_id`,`name`),
  KEY `fk_sh_authority_group_sh_company1_idx` (`sh_company_id`),
  CONSTRAINT `fk_sh_authority_group_sh_company1` FOREIGN KEY (`sh_company_id`) REFERENCES `sh_company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='权限组';

DROP TABLE IF EXISTS  `sh_authority_group_has_sh_authority`;
CREATE TABLE `sh_authority_group_has_sh_authority` (
  `sh_authority_group_id` int(11) unsigned NOT NULL,
  `sh_authority_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`sh_authority_group_id`,`sh_authority_id`),
  KEY `fk_sh_authority_group_has_sh_authority1_sh_authority1_idx` (`sh_authority_id`),
  KEY `fk_sh_authority_group_has_sh_authority1_sh_authority_group1_idx` (`sh_authority_group_id`),
  CONSTRAINT `fk_sh_authority_group_has_sh_authority1_sh_authority1` FOREIGN KEY (`sh_authority_id`) REFERENCES `sh_authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sh_authority_group_has_sh_authority1_sh_authority_group1` FOREIGN KEY (`sh_authority_group_id`) REFERENCES `sh_authority_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sh_autosend_article`;
CREATE TABLE `sh_autosend_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) NOT NULL,
  `match_news_id` int(11) NOT NULL DEFAULT '-1',
  `enabled` varchar(1) NOT NULL COMMENT '0=无效，1=有效',
  `user_id` int(11) NOT NULL,
  `begindate` datetime NOT NULL,
  `enddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `idx_articleId_matchNewsId` (`article_id`,`match_news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7493 DEFAULT CHARSET=utf8 COMMENT='需要自动发送的新闻列表';

DROP TABLE IF EXISTS  `sh_base_sameword`;
CREATE TABLE `sh_base_sameword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(45) NOT NULL,
  `wordlike` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UIX_BASE_SAMEWORD` (`word`,`wordlike`)
) ENGINE=InnoDB AUTO_INCREMENT=17611 DEFAULT CHARSET=utf8 COMMENT='基础表，同义词库';

DROP TABLE IF EXISTS  `sh_category_comment`;
CREATE TABLE `sh_category_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sh_comment_catetory_id` int(11) unsigned NOT NULL,
  `content` varchar(1000) NOT NULL,
  `add_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sh_comment_category_id_idx` (`sh_comment_catetory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25580 DEFAULT CHARSET=utf8 COMMENT='分类评论表';

DROP TABLE IF EXISTS  `sh_comment_catetory`;
CREATE TABLE `sh_comment_catetory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '类别名字',
  `parent` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8 COMMENT='基础评论信息类别';

DROP TABLE IF EXISTS  `sh_comment_report`;
CREATE TABLE `sh_comment_report` (
  `create_time` datetime NOT NULL,
  `sh_admin_user_id` int(10) unsigned NOT NULL,
  `total` int(11) NOT NULL DEFAULT '0',
  `auto_sum` int(11) NOT NULL DEFAULT '0',
  `hand_sum` int(11) NOT NULL DEFAULT '0',
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sh_comment_statistic`;
CREATE TABLE `sh_comment_statistic` (
  `statistic_type` int(11) NOT NULL COMMENT '统计类型：1-评论数，2-要闻数',
  `divisor` int(11) NOT NULL DEFAULT '0' COMMENT '除数（本地数量）',
  `dividend` int(11) NOT NULL DEFAULT '0' COMMENT '被除数（新华社数量）',
  `percent` decimal(12,4) DEFAULT '0.0000' COMMENT '本地占新华社数量百分比',
  `insert_date` datetime DEFAULT NULL,
  `auto_num` int(11) NOT NULL DEFAULT '0',
  `article_num` int(11) NOT NULL DEFAULT '0',
  `xhs_sum` int(11) NOT NULL DEFAULT '0',
  KEY `insert_date_idx` (`insert_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论统计表';

DROP TABLE IF EXISTS  `sh_company`;
CREATE TABLE `sh_company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `company_name` varchar(64) NOT NULL COMMENT '公司名称',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `name` varchar(32) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(32) DEFAULT NULL COMMENT '电话',
  `enabled` enum('0','1') NOT NULL DEFAULT '1' COMMENT '是否启用该公司：0-禁用，1-启用',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='企业表';

DROP TABLE IF EXISTS  `sh_dirty_comment`;
CREATE TABLE `sh_dirty_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `match_news_id` int(10) unsigned NOT NULL COMMENT '匹配到的新闻的id',
  `cmt_uuid` char(32) NOT NULL COMMENT '评论的唯一id（md5的16位）',
  `comment` text NOT NULL COMMENT '评论内容',
  `nick` varchar(45) NOT NULL,
  `sensitive_word` varchar(128) NOT NULL DEFAULT '0',
  `addTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cmt_uuid_UNIQUE` (`cmt_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=116772 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sh_jnl_article_comment`;
CREATE TABLE `sh_jnl_article_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1653400143 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2015_08`;
CREATE TABLE `sh_jnl_article_comment_2015_08` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2015_09`;
CREATE TABLE `sh_jnl_article_comment_2015_09` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2498676 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2015_10`;
CREATE TABLE `sh_jnl_article_comment_2015_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7327543 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2015_11`;
CREATE TABLE `sh_jnl_article_comment_2015_11` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109292558 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2015_12`;
CREATE TABLE `sh_jnl_article_comment_2015_12` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=678581349 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2016_01`;
CREATE TABLE `sh_jnl_article_comment_2016_01` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1243410135 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2016_02`;
CREATE TABLE `sh_jnl_article_comment_2016_02` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1639505307 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2016_03`;
CREATE TABLE `sh_jnl_article_comment_2016_03` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1643388704 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2016_04`;
CREATE TABLE `sh_jnl_article_comment_2016_04` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1647107303 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_article_comment_2016_05`;
CREATE TABLE `sh_jnl_article_comment_2016_05` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:人工手动单条评论，1:人工手动批量评论，2:人工触发系统自动评论'',',
  `nickname` varchar(45) DEFAULT NULL,
  `send_flag` varchar(1) DEFAULT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明   5=测试不发送  6=取消发送',
  `sendtime` datetime DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_md5` (`md5`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `send_flag_idx` (`send_flag`) USING BTREE,
  KEY `idx_articleid` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1650598808 DEFAULT CHARSET=utf8 COMMENT='文章评论流水表';

DROP TABLE IF EXISTS  `sh_jnl_comment`;
CREATE TABLE `sh_jnl_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '''文章序号''',
  `adddate` datetime NOT NULL COMMENT '''评论时间''',
  `nickname` varchar(45) DEFAULT NULL,
  `content` varchar(2048) NOT NULL COMMENT '''评论内容''',
  `content_type` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论类型，0:中性评论，1:分类评论，2:关键字评论，3:精准评论''',
  `user_id` int(11) NOT NULL COMMENT ' ''评论者序号'',',
  `comment_way` char(1) CHARACTER SET latin1 DEFAULT NULL COMMENT '''评论方式，0:系统自动评论，1:人工手动评论，2:人工触发系统自动评论'',',
  `send_flag` varchar(1) NOT NULL COMMENT '0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明',
  `sendtime` datetime DEFAULT NULL COMMENT 'I',
  PRIMARY KEY (`id`),
  KEY `ix_jnl_article` (`user_id`,`adddate`),
  KEY `ix_send_comment_flag` (`send_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发送文章评论表';

DROP TABLE IF EXISTS  `sh_nick`;
CREATE TABLE `sh_nick` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='过滤后的昵称表';

DROP TABLE IF EXISTS  `sh_sensitive_comments`;
CREATE TABLE `sh_sensitive_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catid` int(11) DEFAULT NULL,
  `newsid` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `nick` varchar(24) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `news_type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `newsid_idx` (`newsid`) USING BTREE,
  KEY `catid_idx` (`catid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39369 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `sh_sensitive_vocabulary`;
CREATE TABLE `sh_sensitive_vocabulary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `vocabulary_name` varchar(128) DEFAULT NULL COMMENT '敏感词名称',
  `sh_vocabulary_category_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vocabulary_name_UNIQUE` (`vocabulary_name`)
) ENGINE=InnoDB AUTO_INCREMENT=18928 DEFAULT CHARSET=utf8 COMMENT='敏感词汇表';

DROP TABLE IF EXISTS  `sh_topic`;
CREATE TABLE `sh_topic` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(128) DEFAULT NULL COMMENT '话题内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='话题表';

DROP TABLE IF EXISTS  `sh_topic_comment`;
CREATE TABLE `sh_topic_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sh_topic_id` int(11) unsigned DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `fk_sh_topic_id_idx` (`sh_topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题评论表';

DROP TABLE IF EXISTS  `sh_vocabulary_category`;
CREATE TABLE `sh_vocabulary_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='词汇分类id';

DROP TABLE IF EXISTS  `sh_xhs_commentlist`;
CREATE TABLE `sh_xhs_commentlist` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `comment_id` varchar(45) NOT NULL,
  `article_id` varchar(45) NOT NULL,
  `nick` varchar(256) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `row_num` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uix_xhs_commentlist` (`article_id`,`comment_id`),
  KEY `comment_id_idx` (`comment_id`) USING BTREE,
  KEY `article_id_idx` (`article_id`) USING BTREE,
  KEY `user_id_idx` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=713260603 DEFAULT CHARSET=utf8 COMMENT='新华社新闻，对应评论表。从新华社抓取';

DROP TABLE IF EXISTS  `sh_xhs_nav`;
CREATE TABLE `sh_xhs_nav` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `columntype` varchar(45) DEFAULT NULL,
  `parentid` varchar(45) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `orderid` varchar(45) DEFAULT '0',
  `hasorder` varchar(45) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4056 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sina_weibo`;
CREATE TABLE `sina_weibo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` bigint(20) NOT NULL,
  `comment_id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `nick` varchar(50) NOT NULL,
  `article_id` varchar(32) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`),
  KEY `mid_idx` (`mid`),
  KEY `article_id_idx` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10559534 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sina_weibo_article`;
CREATE TABLE `sina_weibo_article` (
  `mid` bigint(20) unsigned NOT NULL,
  `article_id` varchar(32) CHARACTER SET latin1 NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sina_weibo_comment_id`;
CREATE TABLE `sina_weibo_comment_id` (
  `comment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_article_match_news`;
CREATE TABLE `sp2_article_match_news` (
  `article_id` varchar(32) NOT NULL,
  `match_news_id` int(10) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`,`match_news_id`),
  KEY `fk_match_news_id_idx` (`match_news_id`),
  CONSTRAINT `fk_article_id` FOREIGN KEY (`article_id`) REFERENCES `sh_article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_article_match_news_scrapy`;
CREATE TABLE `sp2_article_match_news_scrapy` (
  `article_id` varchar(32) NOT NULL,
  `match_news_id` int(10) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`,`match_news_id`),
  KEY `fk_match_news_id_idx` (`match_news_id`),
  CONSTRAINT `sp2_article_match_news_scrapy_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `sh_article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_comment`;
CREATE TABLE `sp2_match_comment` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT,
  `match_news_id` int(10) unsigned NOT NULL COMMENT '匹配到的新闻的id',
  `cmt_uuid` char(32) NOT NULL COMMENT '评论的唯一id（md5的16位）',
  `comment` text NOT NULL COMMENT '评论内容',
  `nick` varchar(45) NOT NULL,
  `publish_time` datetime NOT NULL,
  `is_hot` tinyint(1) NOT NULL DEFAULT '0',
  `send_count` int(11) NOT NULL DEFAULT '0' COMMENT '已发送次数',
  `addTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cmt_uuid_UNIQUE` (`cmt_uuid`),
  KEY `fk_sp2_match_comment_match_news_id_idx` (`match_news_id`),
  CONSTRAINT `fk_sp2_match_comment_tmp_match_news_id` FOREIGN KEY (`match_news_id`) REFERENCES `sp2_match_news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=387057886 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_comment_b1`;
CREATE TABLE `sp2_match_comment_b1` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `match_news_id` int(10) unsigned NOT NULL COMMENT '匹配到的新闻的id',
  `cmt_uuid` char(32) NOT NULL COMMENT '评论的唯一id（md5的16位）',
  `comment` text NOT NULL COMMENT '评论内容',
  `nick` varchar(45) NOT NULL,
  `publish_time` datetime NOT NULL,
  `is_hot` tinyint(1) NOT NULL DEFAULT '0',
  `send_count` int(11) NOT NULL DEFAULT '0' COMMENT '已发送次数',
  `addTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cmt_uuid_UNIQUE` (`cmt_uuid`),
  KEY `fk_sp2_match_comment_match_news_id_idx` (`match_news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_comment_bak`;
CREATE TABLE `sp2_match_comment_bak` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `match_news_id` int(10) unsigned NOT NULL COMMENT '匹配到的新闻的id',
  `cmt_uuid` char(32) NOT NULL COMMENT '评论的唯一id（md5的16位）',
  `comment` text NOT NULL COMMENT '评论内容',
  `nick` varchar(45) NOT NULL,
  `publish_time` datetime NOT NULL,
  `is_hot` tinyint(1) NOT NULL DEFAULT '0',
  `send_count` int(11) NOT NULL DEFAULT '0' COMMENT '已发送次数',
  `addTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_match_comment_bak_match_news_id_idx` (`match_news_id`),
  CONSTRAINT `fk_sp2_match_comment_match_news_id` FOREIGN KEY (`match_news_id`) REFERENCES `sp2_match_news_bak` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6826932 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_comment_id`;
CREATE TABLE `sp2_match_comment_id` (
  `cmt_uuid` int(10) unsigned NOT NULL COMMENT '评论的所有id，利用这个表过滤重复的已采集的其他网站的评论 id',
  PRIMARY KEY (`cmt_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS  `sp2_match_comment_scrapy`;
CREATE TABLE `sp2_match_comment_scrapy` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `match_news_id` int(10) unsigned NOT NULL COMMENT '匹配到的新闻的id',
  `cmt_uuid` char(32) NOT NULL COMMENT '评论的唯一id（md5的16位）',
  `comment` text NOT NULL COMMENT '评论内容',
  `nick` varchar(45) NOT NULL,
  `publish_time` datetime NOT NULL,
  `is_hot` tinyint(1) NOT NULL DEFAULT '0',
  `send_count` int(11) NOT NULL DEFAULT '0' COMMENT '已发送次数',
  `addTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cmt_uuid_UNIQUE` (`cmt_uuid`),
  KEY `match_news_id` (`match_news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_log`;
CREATE TABLE `sp2_match_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL,
  `runtime` datetime NOT NULL,
  `remark` varchar(255) NOT NULL,
  `parent_log_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_sp2_match_log_match_news_id` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=197776 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_news`;
CREATE TABLE `sp2_match_news` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `news_type` varchar(15) NOT NULL COMMENT '新闻网站类型',
  `news_uuid` char(32) NOT NULL COMMENT '新闻的唯一id（md5的16位，或者新闻本身16位id）',
  `title` varchar(100) NOT NULL,
  `url` varchar(255) NOT NULL,
  `parent_publish_date` datetime NOT NULL,
  `remark` varchar(150) NOT NULL,
  `hasFetch` tinyint(4) NOT NULL DEFAULT '0',
  `cmt_num` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `news_uuid_UNIQUE` (`news_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=498806 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_news_bak`;
CREATE TABLE `sp2_match_news_bak` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `news_type` varchar(15) NOT NULL COMMENT '新闻网站类型',
  `news_uuid` char(32) NOT NULL COMMENT '新闻的唯一id（md5的16位，或者新闻本身16位id）',
  `title` varchar(100) NOT NULL,
  `url` varchar(255) NOT NULL,
  `parent_publish_date` datetime NOT NULL,
  `remark` varchar(150) NOT NULL,
  `hasFetch` tinyint(4) NOT NULL DEFAULT '0',
  `cmt_num` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45135 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `sp2_match_news_scrapy`;
CREATE TABLE `sp2_match_news_scrapy` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `news_type` varchar(15) NOT NULL COMMENT '新闻网站类型',
  `news_uuid` char(32) NOT NULL COMMENT '新闻的唯一id（md5的16位，或者新闻本身16位id）',
  `title` varchar(100) NOT NULL,
  `url` varchar(255) NOT NULL,
  `parent_publish_date` datetime NOT NULL,
  `remark` varchar(150) NOT NULL,
  `hasFetch` tinyint(4) NOT NULL DEFAULT '0',
  `cmt_num` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `news_uuid_UNIQUE` (`news_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `temp_xhs_commentlist`;
CREATE TABLE `temp_xhs_commentlist` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `xhs_com_id` int(11) unsigned NOT NULL,
  `comment_id` varchar(45) CHARACTER SET utf8 NOT NULL,
  `article_id` varchar(45) CHARACTER SET utf8 NOT NULL,
  `nick` varchar(256) CHARACTER SET utf8 NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8 NOT NULL,
  `row_num` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6893 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS  `xhnews`;
CREATE TABLE `xhnews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xhid` int(11) DEFAULT NULL,
  `catid` int(11) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `is_pushed` enum('1','0') DEFAULT '0',
  `push_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `zhihu_answer`;
CREATE TABLE `zhihu_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catid` int(11) DEFAULT NULL,
  `newsid` int(11) DEFAULT NULL,
  `comment` text,
  `nick` varchar(24) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `news_type` varchar(64) NOT NULL DEFAULT 'zhihu',
  PRIMARY KEY (`id`),
  KEY `news_id_idx` (`newsid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=218340 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `zhihu_answer_token`;
CREATE TABLE `zhihu_answer_token` (
  `answer_token` int(10) unsigned NOT NULL,
  PRIMARY KEY (`answer_token`),
  UNIQUE KEY `answer_token_UNIQUE` (`answer_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `zhihu_keyword`;
CREATE TABLE `zhihu_keyword` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `parent_id` varchar(45) NOT NULL DEFAULT '0',
  `remark` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=284 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `zhihu_question`;
CREATE TABLE `zhihu_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(24) DEFAULT NULL,
  `type` varchar(24) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` text,
  `url` varchar(255) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `last_comment_time` datetime NOT NULL COMMENT '上一次抓取评论的时间',
  `is_pic` varchar(45) NOT NULL,
  `content_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7599 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS  `zhihu_question_token`;
CREATE TABLE `zhihu_question_token` (
  `question_token` int(10) unsigned NOT NULL,
  PRIMARY KEY (`question_token`),
  UNIQUE KEY `question_token_UNIQUE` (`question_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于知乎问题去重';

SET FOREIGN_KEY_CHECKS = 1;

/* PROCEDURES */;
DROP PROCEDURE IF EXISTS `pro_collect_sensitive_comment`;
DELIMITER $$
CREATE PROCEDURE `pro_collect_sensitive_comment`()
begin
    declare comment_id int(11);
    declare done int;
    
    
    declare cur_comments cursor for select id from comments where `comment` like '%妈了个逼%';
    declare continue handler for not found set done = 1;
    
    open cur_comments;
    tmp_loop:loop
		if done = 1 then
			leave tmp_loop;
        else
			fetch cur_comments into comment_id;
			insert into sh_sensitive_comments(catid, newsid, `comment`, nick, addtime, news_type) select catid, newsid, `comment`, nick, addtime, news_type from comments where id = comment_id;
		end if;
	end loop;

end
$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `pro_comment_statistic`;
DELIMITER $$
CREATE PROCEDURE `pro_comment_statistic`()
begin

declare comment_count int(11);
declare cur_content_count int(11);
declare cur_yesterday_count int(11);
declare cur_today_count int(11);
declare xhs_cur_con_count int(11);
declare xhs_cur_yesterday_count int(11);
declare xhs_cur_today_count int(11);
declare percent decimal(12,4);
declare auto_count int(11);
declare article_count int(11);
declare xhs_now_sum int(11);
declare xhs_yesterday_sum int(11);
declare xhs_today_sum int(11);

-- 减小误差
update sh_article set shinc_sum = comment_total where shinc_sum > comment_total and publish_date < date_add(curdate(),interval -7 day);
update sh_article set comment_total = shinc_sum where shinc_sum > comment_total and comment_total > 10000;			

-- 删除当天历史数据
-- delete from sh_comment_statistic where insert_date > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00') and insert_date < now();
-- shinc:
-- 求当前日期的评论总数
SELECT count(*) into comment_count FROM sh_jnl_article_comment 
where send_flag = '2'  
and adddate > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- 截止到昨天本地要闻总数
-- shinc:
SELECT count(a.content) into cur_yesterday_count FROM sh_jnl_article_comment a 
left join sh_article b
on a.article_id = b.id
where a.send_flag = '2'
and b.category in ('0','470')
and a.adddate < concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00')
and b.publish_date < concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- 截止到当前时间本地要闻总数
-- shinc:
SELECT count(a.content) into cur_today_count FROM sh_jnl_article_comment a 
left join sh_article b
on a.article_id = b.id
where a.send_flag = '2'
and b.category in ('0','470');

-- 今天本地要闻总数
-- shinc:
set cur_content_count := cur_today_count - cur_yesterday_count;

-- xhs:截止到昨天新华社要闻总数
select sum(comment_total) into xhs_cur_yesterday_count from sh_article 
where category in ('0','470')
and publish_date < concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- xhs:截止到当前时间新华社要闻总数
select sum(comment_total) into xhs_cur_today_count from sh_article where category in ('0','470');

-- xhs:今天新华社要闻总数
set xhs_cur_con_count := xhs_cur_today_count - xhs_cur_yesterday_count;

-- 当天自动评论数
SELECT count(*) into auto_count FROM sh_jnl_article_comment where send_flag='2' and comment_way='2' and adddate > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- 当天抓取的文章数
SELECT count(*) into article_count FROM spider_news.sh_article where publish_date > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- 截止到当前时间新华社的总评论数
select sum(comment_total) into xhs_now_sum from sh_article;

-- 截止到昨天新华社的总评论数
select cast(xhs_sum as SIGNED INTEGER) into xhs_yesterday_sum from sh_comment_statistic where statistic_type='1' and insert_date < concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00') order by insert_date desc limit 1;

set xhs_today_sum := xhs_now_sum - xhs_yesterday_sum;
set percent := comment_count / xhs_today_sum;
-- 插入总数
insert into sh_comment_statistic(statistic_type, divisor, dividend, percent, insert_date, auto_num, article_num, xhs_sum) values(1, comment_count, xhs_today_sum, percent, now(), auto_count, article_count, xhs_now_sum);

set percent := cur_content_count / xhs_cur_con_count;
-- 插入要闻
insert into sh_comment_statistic(statistic_type, divisor, dividend, percent, insert_date, auto_num, article_num) values(2, cur_content_count, xhs_cur_con_count, percent, now(), auto_count, article_count);

end
$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `pro_remove_duplicate`;
DELIMITER $$
CREATE PROCEDURE `pro_remove_duplicate`()
begin
	insert ignore into spider_news.new_comments(id, catid, newsid, comment, nick, addtime, news_type, md5char)
		select id, catid, newsid, comment, nick, addtime, news_type, md5(concat(comment, newsid)) from spider_news.comments;
end
$$
DELIMITER ;

/* TRIGGERS */;
DROP TRIGGER IF EXISTS `sh_jnl_article_comment_AFTER_UPDATE`;
DELIMITER $$
CREATE TRIGGER `sh_jnl_article_comment_AFTER_UPDATE` AFTER UPDATE ON `sh_jnl_article_comment` FOR EACH ROW
BEGIN
update sh_article set shinc_sum=shinc_sum+1 where id=new.article_id and new.send_flag='2';
update sh_article set auto_sum=auto_sum+1 where id=new.article_id and new.send_flag='2' and new.comment_way='2';
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `sp2_article_match_news_AFTER_INSERT`;
DELIMITER $$
CREATE TRIGGER `sp2_article_match_news_AFTER_INSERT`
AFTER INSERT ON `spider_news`.`sp2_article_match_news`
FOR EACH ROW
BEGIN
update sh_article set news_num=news_num+1 where id=new.article_id;
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `sp2_match_comment_AFTER_INSERT`;
DELIMITER $$
CREATE TRIGGER `sp2_match_comment_AFTER_INSERT` AFTER INSERT ON `sp2_match_comment` FOR EACH ROW
BEGIN
update sp2_match_news set cmt_num=cmt_num+1 where id=new.match_news_id;
update sh_article set cmt_num=cmt_num+1 where id in (
	select article_id from sp2_article_match_news where match_news_id=new.match_news_id
);
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `sp2_match_comment_bak_AFTER_INSERT`;
DELIMITER $$
CREATE TRIGGER `sp2_match_comment_bak_AFTER_INSERT`
AFTER INSERT ON `sp2_match_comment_bak` FOR EACH ROW
BEGIN
update sp2_match_news set cmt_num=cmt_num+1 where id=new.match_news_id;
update sh_article set cmt_num=cmt_num+1 where id in (
	select article_id from sp2_article_match_news where match_news_id=new.match_news_id
);
END
$$
DELIMITER ;

/* EVENTS */;
DROP EVENT IF EXISTS `event_pro_comment_statistic`;
DELIMITER $$
CREATE EVENT `event_pro_comment_statistic` ON SCHEDULE EVERY 10 MINUTE STARTS '2015-09-28 11:22:19' ON COMPLETION PRESERVE ENABLE DO CALL pro_comment_statistic
$$
DELIMITER ;

