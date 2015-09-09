package net.shinc.controller.xhscomment;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.service.NewsService;
import net.shinc.utils.Helper;
import net.shinc.utils.UnicodeUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName NewsController
 * @Description TODO
 * @author guoshijie
 * @date 2015年8月13日 下午7:26:28
 */
@Controller
public class NewsController extends AbstractBaseController {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);

	// 发布评论_请求url
//	private static String sendCommentUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment";
	
	//发评论测试接口（邮件指定）
	@Value("${sendCommentUrl}")
	private static String sendCommentUrl = "http://xhpfm.news.zhongguowangshi.com:8091/v200/open/newscomment";

	// 获取新闻列表_请求url
	private static String listUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/indexlist";
	
	@Value("${phpUrl}")
	private static String phpUrl = "http://123.56.157.137:8002";

	// 用户id
	private static String userId = "0";

	@Autowired
	private NewsService newsService;

	// 目标评论数,例如1000条
	private int minNum = 100;

	// 每篇文章限制批量评论条数,设置小于0代表不限制,以目标评论数为基准
	private int limitNum = -1;
	
	//限制评论文章数目,设置小于0代表不限制,例如只需刷前20篇文章
	private int articleLimit = 2;
	
	private int randomMin = 0;
	private int randomMax = 5;
	

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("list", newsService.getNewsList(userId, listUrl));
		return modelAndView;
	}

	/**
	 * 发布评论
	 * 
	 * @param news
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/sendPost")
	public ModelAndView sendPost(News news) {
		ModelAndView modelAndView = new ModelAndView("index");
		try {
			logger.info(news.toString());
			String res = newsService.sendComment(sendCommentUrl, userId, news,"");
			logger.info("result:\t" + UnicodeUtils.decodeUnicode(res));

			Map map = Helper.jsonToMap(res);
			modelAndView.addObject("msg", map.get("message"));
			modelAndView.addObject("id", news.getId());
			modelAndView.addObject("list", newsService.getNewsList(userId, listUrl));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return modelAndView;
	}

	/**
	 * 批量评论
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/sendCommentBatch")
	@ResponseBody
	public IRestMessage sendCommentBatch() {
		IRestMessage msg = getRestMessage();
		List list = Collections.synchronizedList(newsService.getNewsList(userId, listUrl));
		logger.info("刷新出文章条数==>" + list.toString());

		int needSendNum = Helper.calNum(list.size(), articleLimit);
		for (int i = 0; i < needSendNum; i++) {
			Object obj = list.get(i);
			Map map = (Map) obj;
			newsService.sendCommentBatch((Map) obj, sendCommentUrl, userId, minNum, limitNum, phpUrl, randomMin, randomMax);
		}
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(list); 
		return msg;
	}
	
	/**
	 * 批量评论
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/sendCommentBatchWithPara")
	@ResponseBody
	public IRestMessage sendCommentBatch(int articleLimit,int minNum,int limitNum,int randomMin,int randomMax) {
		IRestMessage msg = getRestMessage();
		List list = Collections.synchronizedList(newsService.getNewsList(userId, listUrl));
		logger.info("刷新出文章条数==>" + list.toString());
		
		int needSendNum = Helper.calNum(list.size(), articleLimit);
		for (int i = 0; i < needSendNum; i++) {
			Object obj = list.get(i);
			Map map = (Map) obj;
			newsService.sendCommentBatch((Map) obj, sendCommentUrl, userId, minNum, limitNum, phpUrl, randomMin, randomMax);
		}
		msg.setCode(ErrorMessage.SUCCESS.getCode());
//		msg.setResult(list); 
		return msg;
	}
	

}
