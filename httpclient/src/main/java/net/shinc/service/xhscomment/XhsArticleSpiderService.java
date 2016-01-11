package net.shinc.service.xhscomment;

import java.util.Map;

/** 
 * @ClassName XhsArticleSpiderService 
 * @Description 新华社新闻内容抓取接口
 * @author Steven Guo 
 * @date 2016年1月11日 下午5:15:58  
 */
public interface XhsArticleSpiderService {
	
	/**
	 * 根据新闻id抓取新闻内容
	 * @param id
	 * @return
	 */
	public Map getArticleContentById(String docId,String showPic,String userId);
}
