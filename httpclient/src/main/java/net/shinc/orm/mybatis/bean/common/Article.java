package net.shinc.orm.mybatis.bean.common;

import java.text.MessageFormat;

public class Article {
	private String id;
	private String title;
	private String publishDate;
	private String comment;
	private String commentsCount;
	private String cmtNum;   //匹配评论数
	private String newsNum;	 //匹配新闻数
	private String autoNum;	 //自动评论数
	
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
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("id:{0}\ttitle{1}\tpublishDate:{2}", this.id,this.title,this.publishDate);
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(String commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getCmtNum() {
		return cmtNum;
	}

	public void setCmtNum(String cmtNum) {
		this.cmtNum = cmtNum;
	}

	public String getNewsNum() {
		return newsNum;
	}

	public void setNewsNum(String newsNum) {
		this.newsNum = newsNum;
	}

	public String getAutoNum() {
		return autoNum;
	}

	public void setAutoNum(String autoNum) {
		this.autoNum = autoNum;
	}
	
	
}
