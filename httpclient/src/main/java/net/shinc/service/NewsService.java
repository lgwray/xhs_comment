package net.shinc.service;

import java.util.List;

import net.shinc.orm.mybatis.bean.common.News;

/**
 * @ClassName NewsService 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月13日 下午4:35:18
 */
public interface NewsService {
	
	/**
	 * 获取文章列表
	 * @return
	 */
	public List getNewsList(String userId,String listUrl);
	
	
	/**
	 * 发布评论
	 * @param url
	 * @param userId
	 * @param news
	 * @return
	 */
	public String sendComment(String url, String userId,News news);
	
}
