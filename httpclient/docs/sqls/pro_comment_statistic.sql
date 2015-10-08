delimiter $

drop procedure if exists pro_comment_statistic$

create procedure pro_comment_statistic()
begin

declare comment_count int(11);
declare xhs_com_count int(11);
declare cur_content_count int(11);
declare xhs_cur_con_count int(11);
declare percent decimal(12,4);
declare auto_count int(11);
declare article_count int(11);
declare xhs_count int(11);

-- 删除当天历史数据
-- delete from sh_comment_statistic where insert_date > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00') and insert_date < now();
-- shinc:
-- 求当前日期的评论总数
SELECT count(*) into comment_count FROM sh_jnl_article_comment 
where send_flag = '2'  
and adddate > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- xhs:新华社当前评论总数
select sum(comment_total) into xhs_com_count from sh_article
where publish_date > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');


-- 当前要闻总数
-- shinc:
SELECT count(a.content) into cur_content_count FROM sh_jnl_article_comment a 
left join sh_article b
on a.article_id = b.id
where a.send_flag = '2'
and b.category= '0'
and a.adddate > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00')
and b.publish_date > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- xhs:新华社要闻总数
select sum(comment_total) into xhs_cur_con_count from sh_article 
where category='0' 
and publish_date > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- 当天自动评论数
SELECT count(*) into auto_count FROM sh_jnl_article_comment where send_flag='2' and comment_way='2' and adddate > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- 当天抓取的文章数
SELECT count(*) into article_count FROM spider_news.sh_article where publish_date > concat(date_format(now(), '%Y-%m-%d'), ' 00:00:00');

-- 截至到当前时间新华社的总评论数
select sum(comment_total) into xhs_count from sh_article;

set percent := comment_count / xhs_com_count;
insert into sh_comment_statistic(statistic_type, divisor, dividend, percent, insert_date, auto_num, article_num, xhs_sum) values(1, comment_count, xhs_com_count, percent, now(), auto_count, article_count, xhs_count);
set percent := cur_content_count / xhs_cur_con_count;
insert into sh_comment_statistic(statistic_type, divisor, dividend, percent, insert_date, auto_num, article_num) values(2, cur_content_count, xhs_cur_con_count, percent, now(), auto_count, article_count);

end$

delimiter ;

-- 调用存储过程
call pro_comment_statistic();

-- 查看数据库是否开启事件支持
show VARIABLES LIKE '%sche%';
SET GLOBAL event_scheduler = 1;

-- 创建事件的定时器
DROP EVENT IF EXISTS event_pro_comment_statistic;

CREATE EVENT IF NOT EXISTS event_pro_comment_statistic
ON SCHEDULE every 10 minute
on completion preserve enable
DO CALL pro_comment_statistic;

-- 开启事件test_event
alter event event_pro_comment_statistic on completion preserve enable;
-- 关闭事件test_event
alter event event_pro_comment_statistic on completion preserve disable;

-- 查看已经创建的事件
select * from  mysql.event;