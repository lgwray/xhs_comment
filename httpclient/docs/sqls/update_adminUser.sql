INSERT INTO `sh_company` (`id`,`company_name`,`address`,`name`,`tel`,`enabled`,`create_time`,`update_time`) VALUES (1,'新华社','北京市朝阳区融科望京中心B座1103','刘总','12345678913','1','2015-07-31 10:59:41','2015-07-31 10:59:41');

INSERT INTO `sh_authority_group` (`id`,`sh_company_id`,`name`,`remark`) VALUES (1,1,'admin','管理员');

INSERT INTO `sh_admin_user` (`id`,`sh_company_id`,`realname`,`nickname`,`password`,`create_time`,`update_time`,`enabled`)
	VALUES (1,1,'管理员','admin','admin',CURDATE(),CURDATE(),'1');


INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (1,1,'adminUserManage','管理员管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (2,1,'adminUserList','管理员管理');	
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (3,1,'ArticleManage','文章管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (4,1,'CommentManage','评论管理');
INSERT INTO `sh_authority` (`id`,`sh_company_id`,`authority`,`remark`) VALUES (5,1,'CommentPre','评论管理');


INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,1);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,2);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,3);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,4);
INSERT INTO `sh_authority_group_has_sh_authority` (`sh_authority_group_id`,`sh_authority_id`) VALUES (1,5);


INSERT INTO `sh_admin_user_has_sh_authority_group` (`sh_admin_user_id`,`sh_authority_group_id`) VALUES (1,1);


