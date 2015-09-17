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



