--20150924
ALTER TABLE `sh_autosend_article` 
ADD COLUMN `match_news_id` INT(11) NOT NULL DEFAULT -1 AFTER `article_id`;


--20150923
CREATE TABLE `sh_autosend_article` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` VARCHAR(32) NOT NULL,
  `enabled` VARCHAR(1) NOT NULL COMMENT '0=无效，1=有效',
  `user_id` INT(11) NOT NULL,
  `begindate` DATETIME NOT NULL,
  `enddate` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
DEFAULT CHARACTER SET = utf8
COMMENT = '需要自动发送的新闻列表';
ALTER TABLE `spider_news`.`sh_autosend_article` 
ADD UNIQUE INDEX `article_id_UNIQUE` (`article_id` ASC);

ALTER TABLE `spider_news`.`sp2_match_comment` 
ADD COLUMN `send_count` INT NOT NULL DEFAULT 0 COMMENT '已发送次数' AFTER `is_hot`;

--20150918
CREATE TABLE IF NOT EXISTS `sh_base_sameword` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(45) NOT NULL,
  `wordlike` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UIX_BASE_SAMEWORD` (`word` ASC, `wordlike` ASC))
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
COMMENT = '基础表，同义词库';


--20150917
ALTER TABLE `sh_xhs_commentlist` 
ADD COLUMN `row_num` VARCHAR(45) NULL AFTER `content`,
ADD COLUMN `user_id` VARCHAR(45) NULL AFTER `row_num`;

ALTER TABLE `sh_xhs_commentlist` 
CHANGE COLUMN `nick` `nick` VARCHAR(256) NOT NULL ;


--20150916
CREATE TABLE IF NOT EXISTS `sh_xhs_commentlist` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_id` VARCHAR(45) NOT NULL,
  `article_id` VARCHAR(45) NOT NULL,
  `nick` VARCHAR(45) NOT NULL,
  `content` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uix_xhs_commentlist` (`article_id` ASC, `comment_id` ASC))
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
COMMENT = '新华社新闻，对应评论表。从新华社抓取'



--20150914
ALTER TABLE `sh_article` 
ADD COLUMN `category` VARCHAR(6) NULL AFTER `detail_url`;
ALTER TABLE `sh_article` 
CHANGE COLUMN `category` `category` VARCHAR(6) NULL DEFAULT NULL COMMENT '0 =要闻,1 =体育,2 =国际,3 =财经,4 =军事,5 =汽车,6 =图片,7 =视频,8 =评论,9 =社会,10=科技,11=动新闻' ;
ALTER TABLE `spider_news`.`sh_article` 
CHANGE COLUMN `category` `category` VARCHAR(6) NULL DEFAULT 0 COMMENT '0 =要闻,1 =体育,2 =国际,3 =财经,4 =军事,5 =汽车,6 =图片,7 =视频,8 =评论,9 =社会,10=科技,11=动新闻' ;



