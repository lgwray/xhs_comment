package net.shinc.formbean.common;

import java.util.List;
import java.util.Map;

public class QueryCommentForm {
	
	private String id;
	private String title;
	private String condition;
	private List<String> commentList;
	private String newsType;
	private Integer page;
	private Integer pageSize;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public List<String> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<String> commentList) {
		this.commentList = commentList;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "QueryCommentForm [id=" + id + ", title=" + title
				+ ", condition=" + condition + ", commentList=" + commentList
				+ ", newsType=" + newsType + ", page=" + page + ", pageSize="
				+ pageSize + "]";
	}
	
	
	
}
