package net.shinc.orm.mybatis.bean.common;

import java.text.MessageFormat;

public class Article {
	private String id;
	private String title;
	private String publishDate;
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
}
