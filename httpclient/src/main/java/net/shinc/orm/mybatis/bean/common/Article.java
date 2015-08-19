package net.shinc.orm.mybatis.bean.common;

import java.sql.Date;
import java.text.MessageFormat;

public class Article {
	private String id;
	private String articleId;
	private Date showDate;
	private String title;
	private String type;
	private String publishDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return MessageFormat.format("id:{0}\tshowDate:{1}", this.id,this.showDate);
	}
}
