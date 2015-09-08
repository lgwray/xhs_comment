package net.shinc.orm.mybatis.bean.common;

import java.text.MessageFormat;

/**
 * @ClassName News 
 * @Description 新闻文章
 * @author guoshijie 
 * @date 2015年8月13日 上午11:15:58
 */
public class News {

	private String id;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("id:{0}\tcontent:{1}", this.id,this.content);
	}

}
