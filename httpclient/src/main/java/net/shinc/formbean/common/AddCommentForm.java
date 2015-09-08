package net.shinc.formbean.common;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * 对某条新闻进行评论
 * @author zhangtaichao 2015年8月19日
 *
 */
public class AddCommentForm {
	
	
	@NotNull
	String categoryId;
	
	@NotNull
	List<Map> commentList;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<Map> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Map> commentList) {
		this.commentList = commentList;
	}
}
