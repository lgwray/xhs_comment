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
	 * 获取要闻文章列表
	 * @return
	 */
	public List getNewsList(String userId,String listUrl);
	
	/**
	 * 按分类获取文章列表
	 * @return
	 */
	public List getNewsList(String userId,String listUrl,String cid,String ctype);
	
	
	/**
	 * 发布评论
	 * @param url
	 * @param userId
	 * @param news
	 * @return
	 */
	public String sendComment(String url, String userId, News news, String username);
	
	public String sendComment(String url, String userId, String articleId, String content, String username);
	
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
	
	/**
	 * 人工给某一文章批量发布评论
	 * @param map 某文章
	 * @param sendCommentUrl 发布评论链接
	 * @param userId 发布评论的用户id
	 * @param minNum 目标评论数,例如:1000条
	 * @param limitNum 每篇文章限制批量评论条数
	 * @param phpUrl 请求PHP接口地址
	 * @return 
	 */
	public int sendCommentBatchByPeople(Map map,String sendCommentUrl, String userId, int minNum, int limitNum, String phpUrl);
	
	/**
	 * 获取本地评论条数
	 * @param list
	 * @return
	 */
	public List getLocalArticleCommentsCounts(List list);
	
	/**
	 * 根据新闻标题精准匹配其他网站新闻
	 * @param type
	 * @param str
	 * @param page
	 * @param num
	 * @return
	 */
	public List getNewsListByTitle(String type,String str,String page,String num);
	
	/**
	 * 根据新闻查询评论
	 * @param type
	 * @param newsid
	 * @param page
	 * @param num
	 * @return
	 */
	public List getCommentsByNews(String type, String newsid, String page, String num);
	
}
