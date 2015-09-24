package net.shinc.controller.xhscomment;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.orm.mybatis.bean.common.Article;
import net.shinc.service.NewsService;
import net.shinc.service.impl.ArticleServiceImpl;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 
 * @ClassName ArticleController 
 * @Description TODO
 * @author zhonglinzhao 
 * @date 2015年8月18日 下午3:19:10
 */

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends AbstractBaseController {
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private ArticleServiceImpl articleService;
	
	@Value("${listUrl}")
	private String listUrl;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	// 用户id
	private static String userId = "0";
	
	/**
	 * 获取文章列表
	 * @param cid
	 * @param ctype
	 * @return
	 */
	@RequestMapping(value = "/refreshArticleList")
	@ResponseBody
	public IRestMessage refreshArticleList(String cid,String ctype){
		IRestMessage msg = getRestMessage();
		List list = null;
		List countsList = null;
		try {
			list = newsService.getNewsList(userId, listUrl,cid,ctype);
			countsList = newsService.getLocalArticleCommentsCounts(list);
			List newsCount = newsService.getMatchNewsCount(list);
			for(Iterator<Map> item = list.iterator(); item.hasNext();){
				Map map = item.next();
				map.put("commentsCount", "0");
				String id = (String)map.get("id");
				for(Iterator<Map> countItem = countsList.iterator(); countItem.hasNext();){
					Map countMap = countItem.next();
					String articlId = (String)countMap.get("articlId");
					if(id != null && id.equals(articlId)){
						map.put("commentsCount", countMap.get("commentsCounts"));
						break;
					}
				}
			}
			
			for(Iterator<Map> item = list.iterator(); item.hasNext();){
				Map map = item.next();
				map.put("matchNewsCount", "0");
				String id = (String)map.get("id");
				for(Iterator<Map> countItem = newsCount.iterator(); countItem.hasNext();){
					Map countMap = countItem.next();
					String articlId = (String)countMap.get("article_id");
					if(id != null && id.equals(articlId)){
						map.put("matchNewsCount", countMap.get("matchNewsCount"));
					}
				}
			}
			
			if(null != list) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
//				articleService.refreshArticleList(list);//
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error("查询失败==>" + ExceptionUtils.getStackTrace(e));
		}
		logger.info(">>>"+Helper.objToJson(list));
		return msg;
	}
	
	/**
	 * 自动刷新文章列表，记录文章发布频率
	 * @param cid
	 * @param ctype
	 * @return
	 */
	@RequestMapping(value = "/autoRefreshArticleList")
	@ResponseBody
	public IRestMessage autoRefreshArticleList(String cid,String ctype){
		IRestMessage msg = getRestMessage();
		List list = null;
		try {
			list = newsService.getNewsList(userId, listUrl,cid,ctype);
			if(null != list) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				articleService.refreshArticleList(list);//插库
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error("查询失败==>" + ExceptionUtils.getStackTrace(e));
		}
		logger.info(">>>"+Helper.objToJson(list));
		return msg;
	}
	
	
	/**
	 * 通过发布日期查询文章列表
	 * @param publisDate
	 * @return
	 */
	@RequestMapping(value = "/getArticleListByDate")
	@ResponseBody
	public IRestMessage getArticleListByDate(String publisDate){
		IRestMessage msg = getRestMessage();
		try {
			List list = articleService.getArticleListByDate(publisDate);
			
			List countsList = newsService.getLocalArticleCommentsCounts(list);
			for(Iterator<Article> item = list.iterator(); item.hasNext();){
				Article article = item.next();
				article.setCommentsCount("0");
				String id = (String)article.getId();
				for(Iterator<Map> countItem = countsList.iterator(); countItem.hasNext();){
					Map countMap = countItem.next();
					String articlId = (String)countMap.get("articlId");
					if(id != null && id.equals(articlId)){
						article.setCommentsCount(((Long)countMap.get("commentsCounts")).toString());
					}
				}
			}
			
			if(null != list) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		}catch (Exception e) {
			logger.error("查询失败==>" + ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
	/**
	 * 新闻标题查新闻列表
	 * @param type 固定传news
	 * @param title 新闻标题
	 * @param page
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "/getNewsListByTitle")
	@ResponseBody
	public IRestMessage getNewsListByTitle(String type,String title,String page,String num) {
		IRestMessage msg = getRestMessage();
		List list = newsService.getNewsListByTitle(type, title, page, num);
		logger.info("查出文章条数==>" + list.size());
		if(!CollectionUtils.isEmpty(list)) {
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			msg.setResult(list); 
		} else {
			msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
		}
		return msg;
	}
	
	/**
	 * 根据新闻id,查新闻下的评论
	 * @param type 固定传news
	 * @param newsid 新闻id
	 * @param page
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "/getCommentsByNews")
	@ResponseBody
	public IRestMessage getCommentsByNews(String type, String newsid, String page, String num) {
		IRestMessage msg = getRestMessage();
		List list = newsService.getCommentsByNews(type, newsid, page, num);
		logger.info("查出评论条数==>" + list.size());
		if(!CollectionUtils.isEmpty(list)) {
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			msg.setResult(list);
			int size = list.size();
			Integer integer = new Integer(size);
			msg.setDetail(integer.toString());
		} else {
			msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
		}
		return msg;
	}
	
}
