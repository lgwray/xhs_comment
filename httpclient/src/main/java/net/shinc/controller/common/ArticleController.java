package net.shinc.controller.common;

import java.util.List;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.NewsService;
import net.shinc.service.impl.ArticleServiceImpl;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
	
	@RequestMapping(value = "/refreshArticleList")
	@ResponseBody
	public IRestMessage refreshArticleList(String cid,String ctype){
		IRestMessage msg = getRestMessage();
		List list = null;
		try {
			list = newsService.getNewsList(userId, listUrl,cid,ctype);
			if(null != list) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(Helper.objToJson(list));
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
			articleService.refreshArticleList(list);//查询数据库
		} catch (Exception e) {
			logger.error("查询失败==>" + ExceptionUtils.getStackTrace(e));
		}
		logger.info(">>>"+Helper.objToJson(list));
		return msg;
	}
}
