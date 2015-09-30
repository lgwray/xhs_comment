package net.shinc.service.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.AutoSendArticle;

/** 
 * @ClassName AutoSendArticleService 
 * @Description 自动发送
 * @author guoshijie 
 * @date 2015年9月24日 下午8:11:28  
 */
public interface AutoSendArticleService {

	public Integer addAutoSendArticle(AutoSendArticle record,Integer days);
	
	public Boolean hasAutoSendArticle(Integer articleId,Integer matchNewsId);
	
	/**
	 * 是否已经启用自动评论 true:是  false:否
	 * @param articleId
	 * @param matchnewsId
	 * @return
	 */
	public Boolean isEnableAutoSendArticle(Integer articleId,Integer matchnewsId);
	
	public List<Map> getAutoSendList();
}
