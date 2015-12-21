package net.shinc.formbean.common;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * 对某条新闻进行评论
 * @author zhangtaichao 2015年8月19日
 *
 */
public class CommentItForm {
	
	
	@NotNull
	String articleId;
	
	@NotNull
	List<Map> commentList;
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public List<Map> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Map> commentList) {
		this.commentList = commentList;
	}

}
