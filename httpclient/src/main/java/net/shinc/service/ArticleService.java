package net.shinc.service;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;
import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;

/** 
 * @ClassName ArticleService 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月17日 下午10:12:50  
 */
public interface ArticleService {

	public Map getArticlesById(Integer articleId);
	
	/**
	 * 根据新闻id查询匹配的文章id列表
	 * @param articleId
	 * @return
	 */
	public List<String> getMatchNewsIdByArticleId(Integer articleId);
	
	/**
	 * 根据新闻id查询匹配的文章列表
	 * @param articleId
	 * @return
	 */
	public List<MatchNews> getMatchNewsByArticleId(Integer articleId);
	
	/**
	 * 根据新华社新闻id查询匹配到的评论列表
	 * @param articleId
	 * @return
	 */
	public List<MatchComment> getMatchCommentsByArticleId(Integer articleId);
}
