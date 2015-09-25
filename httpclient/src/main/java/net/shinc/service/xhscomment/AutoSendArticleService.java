package net.shinc.service.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.AutoSendArticle;

/** 
 * @ClassName AutoSendArticleService 
 * @Description 自动发送
 * @author guoshijie 
 * @date 2015年9月24日 下午8:11:28  
 */
public interface AutoSendArticleService {

	public Integer addAutoSendArticle(AutoSendArticle record,Integer days);
	
	public Boolean hasAutoSendArticle(Integer articleId);
	
	public Boolean isEnableAutoSendArticle(Integer articleId);
}
