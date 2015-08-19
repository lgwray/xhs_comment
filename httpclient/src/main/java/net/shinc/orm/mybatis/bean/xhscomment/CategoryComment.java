package net.shinc.orm.mybatis.bean.xhscomment;

import java.util.Date;

import net.shinc.orm.mybatis.bean.common.ResultBean;

/**
 * 
 * 基础评论信息表
 * sh_category_comment
 * @author zhangtaichao 2015年8月19日
 *
 */
public class CategoryComment implements ResultBean {

	
	private Integer id;
	
	private String content;
	
	private Date addTime;
	
	private Integer categoryId;
	
	private CommentCategory category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public CommentCategory getCategory() {
		return category;
	}

	public void setCategory(CommentCategory category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
