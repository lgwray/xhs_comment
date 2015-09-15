--20150914
ALTER TABLE `sh_article` 
ADD COLUMN `category` VARCHAR(6) NULL AFTER `detail_url`;
ALTER TABLE `sh_article` 
CHANGE COLUMN `category` `category` VARCHAR(6) NULL DEFAULT NULL COMMENT '0 =要闻,1 =体育,2 =国际,3 =财经,4 =军事,5 =汽车,6 =图片,7 =视频,8 =评论,9 =社会,10=科技,11=动新闻' ;
ALTER TABLE `spider_news`.`sh_article` 
CHANGE COLUMN `category` `category` VARCHAR(6) NULL DEFAULT 0 COMMENT '0 =要闻,1 =体育,2 =国际,3 =财经,4 =军事,5 =汽车,6 =图片,7 =视频,8 =评论,9 =社会,10=科技,11=动新闻' ;
