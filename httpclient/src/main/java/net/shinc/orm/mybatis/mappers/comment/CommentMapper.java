package net.shinc.orm.mybatis.mappers.comment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.common.Article;
import net.shinc.orm.mybatis.bean.common.News;

public interface CommentMapper {
	public List<News> selectNeuterComment(Integer count);
	
	public List<Article> selectArticleListByDate(String publishDate);
	
	public void insertArticleListBatch(List articleList);
	
	public void insertArticle(Map map);
	
	public void updateArticle(Map map);
	
	public List getLocalArticleCommentsCounts(List list);
	
	/**
	 * 查看本地每天的评论量
	 * @return
	 */
	public List getLocalCommentsNums();
	public List getLocalCommentsNums2();
	
	/**
	 * 根据日期查本地评论数
	 * @param date
	 * @return
	 */
	public Map getLocalCommentsNumsByDate(String date);
	public Map getLocalCommentsNumsByDate2(String date);
	
	/**
	 * 根据日期date和categoryid(栏目id)查本地评论数
	 * @param map
	 * @return
	 */
	public Map getLocalCommentsNumsByCategory(Map map);
	
	/**
	 * 获取抓取的文章数（根据日期）
	 * @param date
	 * @return
	 */
	public Map getArticlesNumByDate(String date);
	
	/**
	 * 获取新华社当日总评论数
	 * @return
	 */
	public Map getTodayRemoteNums();
	
	/**
	 * 获取新华社当日指定栏目总评论数
	 * @return
	 */
	public Map getTodayRemoteNumsByCategory(String categoryid);
	
	/**
	 * 获取新华社总评论数（根据日期）
	 * @param date
	 * @return
	 */
	public Map getXhsCommentSum(String date);
	
	/**
	 * 获取新华社总评论数（根据日期和categoryid(栏目id)）
	 * @param date
	 * @return
	 */
	public Map getXhsCommentSumByCategory(Map map);

}
