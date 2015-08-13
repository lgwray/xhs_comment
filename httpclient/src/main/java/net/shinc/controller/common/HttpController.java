package net.shinc.controller.common;

import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.service.NewsService;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName HttpController 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月12日 上午10:08:47
 */
@Controller
public class HttpController extends AbstractBaseController {
	
	private static Logger logger = LoggerFactory.getLogger(HttpController.class);
	
	//发布评论_请求url
	private static String sendCommentUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment";
	
	//获取新闻列表_请求url
	private static String listUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/indexlist";
	
	//用户id
	private static String userId = "0";
	
	@Autowired
	private NewsService newsService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("list", newsService.getNewsList(userId,listUrl));
		return modelAndView;
	}
	
	/**
	 * 发布评论
	 * @param news
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/sendPost")
	public ModelAndView sendPost(News news) {
		ModelAndView modelAndView = new ModelAndView("index");
		try {
	        logger.info(news.toString());
	        String res = newsService.sendComment(sendCommentUrl, userId, news);
	        logger.info("result:\t"+res);
	        
			Map map = Helper.jsonToMap(res);
			modelAndView.addObject("msg", map.get("data"));
			modelAndView.addObject("id", news.getId());
			modelAndView.addObject("list", newsService.getNewsList(userId,listUrl));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return modelAndView;
	}
	
}
