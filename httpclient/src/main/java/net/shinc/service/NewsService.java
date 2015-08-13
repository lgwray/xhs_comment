package net.shinc.service;

import java.util.List;
import java.util.Map;

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
	public String sendComment(String url, String userId, News news);
	
	public String sendComment(String url, String userId, String articleId, String content);
	
	/**
	 * 给某一文章批量发布评论
	 * @param map 某文章
	 * @param sendCommentUrl 发布评论链接
	 * @param userId 发布评论的用户id
	 * @param minNum 目标评论数,例如:1000条
	 * @param limitNum 每篇文章限制批量评论条数
	 * @param phpUrl 请求PHP接口地址
	 * @param randomMin 随机数最小值
	 * @param randomMax 随机数最大值
	 */
	public void sendCommentBatch(Map map,String sendCommentUrl, String userId, int minNum, int limitNum, String phpUrl, Integer randomMin, Integer randomMax);
	
}
