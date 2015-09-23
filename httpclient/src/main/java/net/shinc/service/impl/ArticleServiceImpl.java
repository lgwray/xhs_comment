package net.shinc.service.impl;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.common.Article;
import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;
import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;
import net.shinc.orm.mybatis.mappers.comment.CommentMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.ArticleHasMatchNewsMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper;
import net.shinc.service.ArticleService;
import net.shinc.service.xhscomment.MatchNewsService;

import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleServiceImpl 
 * @Description TODO
 * @author zhonglinzhao 
 * @date 2015年8月18日 下午3:14:29
 */

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private CloseableHttpClient httpClient;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private MatchNewsService mnService;
	
	@Autowired
	private ArticleHasMatchNewsMapper articleHasMatchNewsMapper;
	
	public void refreshArticleList(List<Map> list){
		if(list != null){
			String id = "";
			for(int i=0;i<list.size();i++){
				Map map = list.get(i);
				id = (String)map.get("id");
				if(id != null && !"".equals(id)){
					try{
						commentMapper.insertArticle(list.get(i));
					}catch(Exception e){
						logger.error("SQLException => "+e);
						commentMapper.updateArticle(list.get(i));
					}
				}
			}
		}
	}
	
	public List<Article> getArticleListByDate(String publishDate){
		List<Article> list = commentMapper.selectArticleListByDate(publishDate);
		return list;
	}

	@Override
	public Map getArticlesById(Integer articleId) {
		Map map = articleMapper.selectArticleByPrimaryKey(articleId);
		return map;
	}

	@Override
	public List<String> getMatchNewsIdByArticleId(Integer articleId) {
		if(null != articleId) {
			List<String> list = articleHasMatchNewsMapper.selectMatchNewsIdByArticleId(articleId);
			return list;
		}
		return null;
	}
	
	@Override
	public List<MatchNews> getMatchNewsByArticleId(Integer articleId) {
		if(null != articleId) {
			List<String> list = getMatchNewsIdByArticleId(articleId);
			List<MatchNews> newsBatch = mnService.getMatchNewsBatch(list);
			return newsBatch;
		}
		return null;
	}

	@Override
	public List<MatchComment> getMatchCommentsByArticleId(Integer articleId) {
		if(null != articleId) {
			List<String> list = getMatchNewsIdByArticleId(articleId);
			List<MatchComment> matchCommentList = mnService.getMatchNewsCommentsBatch(list);
			return matchCommentList;
		}
		return null;
	}
}
