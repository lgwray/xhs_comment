package net.shinc.controller.common;

import java.util.HashMap;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.NewsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName NewsDetailController
 * @Description TODO
 * @author zhaozhonglin
 * @date 2015年8月14日 下午7:26:28
 */
@Controller
public class NewsDetailController extends AbstractBaseController {

	private static Logger logger = LoggerFactory.getLogger(NewsDetailController.class);

	// 发布评论_请求url
	private static String sendCommentUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment";

	// 获取新闻列表_请求url
	private static String listUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/indexlist";
	
//	private static String phpUrl = "http://spider.localhost/";
	private static String phpUrl = "http://192.168.1.222/";

	// 用户id
	private static String userId = "0";

	@Autowired
	private NewsService newsService;

	// 目标评论数
	private int minNum = 150;

	// 每篇文章限制批量评论条数
	private int limitNum = 5;
	
	//限制评论文章数目,设置小于0代表不限制
	private int articleLimit = 20;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/newsDetail")
	public ModelAndView index(String id,String title) {
		ModelAndView modelAndView = new ModelAndView("newsDetail");
		modelAndView.addObject("id",id);
		modelAndView.addObject("title",title);
		return modelAndView;
	}

	
	
	/**
	 * 人工批量评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sendCommentBatchByPeople")
	public ModelAndView sendCommentBatchByPeople(String newsType,String newsCount,String id,String title) {
		Map map = new HashMap();
		map.put("newsType",newsType);
		map.put("newsCount",newsCount);
		map.put("id",id);
		map.put("title",title);
		int i = newsService.sendCommentBatchByPeople((Map) map, sendCommentUrl, userId, minNum, limitNum, phpUrl);
		ModelAndView modelAndView = new ModelAndView("newsDetail");
		modelAndView.addObject("count",i);
		modelAndView.addObject("newsType",newsType);
		modelAndView.addObject("newsCount",newsCount);
		modelAndView.addObject("id",id);
		modelAndView.addObject("title",title);
		return modelAndView;
	}

	
	
	public int calNum(int all, int limit) {
		if (limit < 0) {
			return all;
		}
		if (all > limit) {
			return limit;
		} else if (all < limit) {
			return all;
		}
		return all;
	}

}
