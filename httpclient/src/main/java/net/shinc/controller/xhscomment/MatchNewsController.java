package net.shinc.controller.xhscomment;

import java.util.ArrayList;
import java.util.List;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;
import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;
import net.shinc.service.ArticleService;
import net.shinc.service.xhscomment.MatchNewsService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @ClassName MatchNewsController 
 * @Description 新华社新闻匹配全网新闻
 * @author guoshijie 
 * @date 2015年9月18日 上午11:19:12  
 */
@Controller
@RequestMapping(value = "/matchNews")
public class MatchNewsController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MatchNewsService mnService;
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 根据新闻id查询匹配到的新闻列表（新华社新闻匹配全网新闻）
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "/getMatchNewsListByArticleId")
	@ResponseBody
	public IRestMessage getMatchNewsListByArticleId(@RequestParam(value="articleId",required=true) String articleId) {
		IRestMessage msg = getRestMessageWithoutUser();
		try {
			List<MatchNews> list = articleService.getMatchNewsByArticleId(Integer.parseInt(articleId));
			if(!CollectionUtils.isEmpty(list)) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
				msg.setDetail(new Integer(list.size()).toString());
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
	/**
	 * 查询匹配到的某条新闻下的评论
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "/getMatchCommentsByMatchNewsId")
	@ResponseBody
	public IRestMessage getMatchCommentsByMatchNewsId(@RequestParam(value="matchNewsId",required=true) String matchNewsId,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value = "num",defaultValue="50") Integer num) {
		
		IRestMessage msg = getRestMessageWithoutUser();
		try {
			PageBounds pb = new PageBounds(page,num);
			List<String> list = new ArrayList<String>();
			list.add(matchNewsId);
			List<MatchComment> withPagination = mnService.getMatchNewsCommentsBatchWithPagination(list, pb);
			if(!CollectionUtils.isEmpty(withPagination)) {
				PageList pagelist = (PageList)withPagination;
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(pagelist);
				msg.setPageInfo(pagelist.getPaginator());
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
	/**
	 * 根据新闻id查询匹配到的评论
	 * @param articleId 新闻id
	 * @return
	 */
	@RequestMapping(value = "/getMatchCommentsByArticleId")
	@ResponseBody
	public IRestMessage getMatchCommentsByArticleId(@RequestParam(value="articleId",required=true) String articleId,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value = "num",defaultValue="50") Integer num) {
		
		IRestMessage msg = getRestMessageWithoutUser();
		try {
			PageBounds pb = new PageBounds(page,num);
			List<String> list = articleService.getMatchNewsIdByArticleId(Integer.parseInt(articleId));
			List<MatchComment> withPagination = mnService.getMatchNewsCommentsBatchWithPagination(list, pb);
			PageList<MatchComment> pagelist = (PageList<MatchComment>)withPagination;
			if(!CollectionUtils.isEmpty(withPagination)) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(pagelist);
				msg.setPageInfo(pagelist.getPaginator());
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
}
