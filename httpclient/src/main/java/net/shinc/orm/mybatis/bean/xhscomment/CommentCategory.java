package net.shinc.orm.mybatis.bean.xhscomment;

import net.shinc.orm.mybatis.bean.common.ResultBean;

/**
 * 基础评论信息分类表
 * sh_comment_catetory
 * @author zhangtaichao 2015年8月19日
 *
 */
public class CommentCategory implements ResultBean {
	
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
