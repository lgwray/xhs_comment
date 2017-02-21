SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE `sh_authority`;
insert into `sh_authority`(`id`,`sh_company_id`,`authority`,`remark`) values
('1','1','ArticleManage','发布管理'),
('2','1','AutoSendManage','自动发布管理'),
('3','1','Nickfilter','昵称筛选'),
('4','1','noFilterNick','未筛选的昵称'),
('5','1','passNick','通过的昵称'),
('6','1','noPassNick','未通过的昵称'),
('7','1','CommentManage','通用评论管理'),
('8','1','addCommentPre','添加通用评论'),
('9','1','tongji','统计'),
('10','1','everydayArticle','每天抓取的文章'),
('11','1','alreadySend','已发布的评论'),
('12','1','zongliang','总量'),
('13','1','persent','占比统计'),
('14','1','adminUserManage','管理员管理'),
('15','1','adminUserList','用户管理'),
('16','1','commentReport','每日绩效'),
('19','1','ArticleManage','发布管理'),
('20','1','AutoSendManage','自动发布评论'),
('21','1','Nickfilter','昵称筛选'),
('22','1','noFilterNick','未筛选的昵称'),
('23','1','passNick','通过的昵称'),
('24','1','noPassNick','未通过的昵称'),
('25','1','CommentManage','通用评论管理'),
('26','1','addCommentPre','添加通用评论'),
('27','1','tongji','统计'),
('28','1','everydayArticle','每天发送的文章'),
('29','1','alreadySend','已发布的评论'),
('30','1','zongliang','总量'),
('31','1','persent','占比统计'),
('32','1','adminUserManage','管理员管理'),
('33','1','adminUserList','用户管理'),
('36','1','ArticleManage','发布管理'),
('37','1','AutoSendManage','自动发布评论'),
('38','1','Nickfilter','昵称筛选'),
('39','1','noFilterNick','未筛选的昵称'),
('40','1','passNick','通过的昵称'),
('41','1','noPassNick','未通过的昵称'),
('42','1','CommentManage','通用评论管理'),
('43','1','addCommentPre','添加通用评论'),
('44','1','tongji','统计'),
('45','1','everydayArticle','每天发送的文章'),
('46','1','alreadySend','已发布的评论'),
('47','1','zongliang','总量'),
('48','1','persent','占比统计'),
('49','1','adminUserManage','管理员管理'),
('50','1','adminUserList','用户管理'),
('53','1','ArticleManage','发布管理'),
('54','1','AutoSendManage','自动发布评论'),
('55','1','Nickfilter','昵称筛选'),
('56','1','noFilterNick','未筛选的昵称'),
('57','1','passNick','通过的昵称'),
('58','1','noPassNick','未通过的昵称'),
('59','1','CommentManage','通用评论管理'),
('60','1','addCommentPre','添加通用评论'),
('61','1','tongji','统计'),
('62','1','everydayArticle','每天发送的文章'),
('63','1','alreadySend','已发布的评论'),
('64','1','zongliang','总量'),
('65','1','persent','占比统计'),
('66','1','adminUserManage','管理员管理'),
('67','1','adminUserList','用户管理');
TRUNCATE TABLE `sh_authority_group`;
insert into `sh_authority_group`(`id`,`sh_company_id`,`name`,`remark`) values
('1','1','admin','管理员'),
('2','1','test','测试组'),
('3','1','operators','运维组');
TRUNCATE TABLE `sh_authority_group_has_sh_authority`;
insert into `sh_authority_group_has_sh_authority`(`sh_authority_group_id`,`sh_authority_id`) values
('1','1'),
('2','1'),
('3','1'),
('1','2'),
('2','2'),
('3','2'),
('1','3'),
('2','3'),
('3','3'),
('1','4'),
('2','4'),
('3','4'),
('1','5'),
('2','5'),
('3','5'),
('1','6'),
('2','6'),
('3','6'),
('1','7'),
('2','7'),
('3','7'),
('1','8'),
('2','8'),
('3','8'),
('1','9'),
('2','9'),
('3','9'),
('1','10'),
('2','10'),
('3','10'),
('1','11'),
('2','11'),
('3','11'),
('1','12'),
('2','12'),
('3','12'),
('1','13'),
('2','13'),
('3','13'),
('1','14'),
('1','15'),
('1','16'),
('2','16'),
('3','16');
TRUNCATE TABLE `sh_company`;
insert into `sh_company`(`id`,`company_name`,`address`,`name`,`tel`,`enabled`,`create_time`,`update_time`) values
('1','新华社','北京市朝阳区融科望京中心B座1103','刘总','12345678913','1','2015-07-31 10:59:41','2015-07-31 10:59:41');
TRUNCATE TABLE `sh_admin_user`;
insert into `sh_admin_user`(`id`,`sh_company_id`,`realname`,`nickname`,`password`,`sex`,`address`,`email`,`tel`,`create_time`,`update_time`,`enabled`,`remark`,`position`,`head_pic`) values
('1','1','管理员','admin','shihe123456','1',null,null,null,'2015-08-18 00:00:00','2015-08-18 00:00:00','1',null,null,null),
('45','1',null,'taichao','taichao',null,null,null,null,'2015-08-18 15:31:03','2015-08-18 15:31:03','0',null,null,null),
('46','1',null,'zzl','zzl',null,null,null,null,'2015-08-18 18:45:47','2015-08-18 18:45:47','1',null,null,null),
('47','1',null,'许文','64700956',null,null,null,null,'2015-09-01 16:56:29','2015-09-01 16:56:29','0',null,null,null),
('48','1',null,'汪琦','19940706abc',null,null,null,null,'2015-09-01 18:05:55','2015-09-01 18:05:55','0',null,null,null),
('49','1',null,'方雪妮','sagusi',null,null,null,null,'2015-09-01 20:22:38','2015-09-01 20:22:38','0',null,null,null),
('50','1',null,'张双双','papamamasoso3',null,null,null,null,'2015-09-02 07:37:56','2015-09-02 07:37:56','1',null,null,null),
('51','1',null,'刘乔妍','1992227',null,null,null,null,'2015-09-02 07:58:24','2015-09-02 07:58:24','1',null,null,null),
('52','1',null,'张玥','zhang19921001',null,null,null,null,'2015-09-06 16:18:12','2015-09-06 16:18:12','0',null,null,null),
('53','1',null,'王科楠','123456',null,null,null,null,'2015-09-07 20:21:25','2015-09-07 20:21:25','1',null,null,null),
('54','1',null,'ztc','ztc',null,null,null,null,'2015-09-07 22:02:48','2015-09-07 22:02:48','1',null,null,null),
('55','1',null,'郭世杰','guoshijie',null,null,null,null,'2015-09-07 22:04:21','2015-09-07 22:04:21','1',null,null,null),
('56','1',null,'wanghaihong','123456',null,null,null,null,'2015-09-07 22:05:19','2015-09-07 22:05:19','1',null,null,null),
('57','1',null,'wh','123456',null,null,null,null,'2015-09-08 17:47:57','2015-09-08 17:47:57','1',null,null,null),
('58','1',null,'tongrenshun','123456',null,null,null,null,'2015-10-08 11:18:40','2015-10-08 11:18:40','1',null,null,null),
('61','1',null,'wangxinming','123456',null,null,null,null,'2015-10-08 11:21:37','2015-10-08 11:21:37','1',null,null,null),
('62','1',null,'test','123456',null,null,null,null,'2015-10-08 14:32:05','2015-10-08 21:20:06','0',null,null,null),
('65','1',null,'111','111111',null,null,null,null,'2015-10-08 19:45:46','2015-10-08 19:45:46','0',null,null,null),
('66','1',null,'111','111111',null,null,null,null,'2015-10-08 19:46:25','2015-10-08 19:46:25','0',null,null,null),
('67','1',null,'111','111111',null,null,null,null,'2015-10-08 19:46:25','2015-10-08 19:46:25','0',null,null,null),
('68','1',null,'111','111111',null,null,null,null,'2015-10-08 19:46:25','2015-10-08 19:46:25','0',null,null,null),
('69','1',null,'111','111111',null,null,null,null,'2015-10-08 19:46:25','2015-10-08 19:46:25','0',null,null,null),
('70','1',null,'test111','admin',null,null,null,null,'2015-10-08 19:50:38','2015-10-08 19:50:38','0',null,null,null),
('71','1',null,'test111','admin',null,null,null,null,'2015-10-08 19:50:38','2015-10-08 19:50:38','0',null,null,null),
('72','1',null,'test111','admin',null,null,null,null,'2015-10-08 19:50:38','2015-10-08 19:50:38','0',null,null,null),
('73','1',null,'test111','admin',null,null,null,null,'2015-10-08 19:50:38','2015-10-08 19:50:38','0',null,null,null),
('74','1',null,'丁鹏','123456',null,null,null,null,'2015-10-08 20:09:45','2015-10-08 20:09:45','1',null,null,null),
('75','1',null,'12','111111',null,null,null,null,'2015-10-08 21:15:25','2015-10-08 21:15:25','0',null,null,null),
('76','1',null,'董春超','987654',null,null,null,null,'2015-11-19 11:57:59','2015-11-19 11:57:59','1',null,null,null),
('77','1',null,'肇莹','000000',null,null,null,null,'2015-11-19 12:01:49','2015-11-19 12:01:49','1',null,null,null),
('78','1',null,'车阳倩','tt121314',null,null,null,null,'2015-11-19 12:02:48','2015-11-19 12:02:48','1',null,null,null),
('79','1',null,'张艳霞','521321',null,null,null,null,'2015-11-20 10:00:27','2015-11-20 10:00:27','0',null,null,null),
('80','1',null,'陈海静','123456',null,null,null,null,'2015-11-25 10:14:38','2015-11-25 10:14:38','0',null,null,null),
('81','1',null,'陈海静','123456',null,null,null,null,'2015-11-26 13:05:48','2015-11-26 13:05:48','1',null,null,null),
('82','1',null,'huahua','123456',null,null,null,null,'2016-04-06 10:58:10','2016-04-06 10:58:10','1',null,null,null);
TRUNCATE TABLE `sh_admin_user_has_sh_authority_group`;
insert into `sh_admin_user_has_sh_authority_group`(`sh_admin_user_id`,`sh_authority_group_id`) values
('1','1'),
('46','1'),
('50','1'),
('53','1'),
('54','1'),
('55','1'),
('56','1'),
('57','1'),
('74','1'),
('58','2'),
('61','2'),
('51','3'),
('76','3'),
('77','3'),
('78','3'),
('81','3'),
('82','3');
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

