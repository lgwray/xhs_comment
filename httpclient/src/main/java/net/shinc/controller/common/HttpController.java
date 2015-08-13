package net.shinc.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.utils.HttpXmlClient;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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
	private static String url = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment";
	
	//获取新闻列表_请求url
	private static String listUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/indexlist";
	
	//用户id
	private static String userId = "0";
	
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
	        String res = HttpXmlClient.post(url, getDiscussParamMap(news, userId, getCurrentTimeMillis()));
	        logger.info("result:\t"+res);
	        
			Gson gson  = new Gson();
			Map map = (Map)gson.fromJson(res, HashMap.class);
			
			modelAndView.addObject("msg", map.get("data"));
			modelAndView.addObject("id", news.getId());
			modelAndView.addObject("list", getNewsList());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("list", getNewsList());
		return modelAndView;
	}
	
	/**
	 * 获取文章列表
	 * @return
	 */
	public List getNewsList(){
		Gson gson  = new Gson();
		Map map2 = (Map)gson.fromJson(sendPostForNewsList(), HashMap.class);
		List list = (List)map2.get("data_scroll");
		List list2 = (List)map2.get("data");
		list.addAll(list2);
		return list;
	}
	
	/**
	 * 发post请求，新闻列表
	 * @return
	 */
	public static String sendPostForNewsList() {
		String res = HttpXmlClient.post(listUrl, getNewsListParamMap(getCurrentTimeMillis()));
		logger.info("\nnews list:\n"+res);
		return res;
	}
	
	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static String getCurrentTimeMillis(){
		long currentTimeMillis = System.currentTimeMillis();
		String str = Long.toString(currentTimeMillis);
		return str;
	}
	
	/**
	 * 准备获取新闻列表_请求参数map
	 * @param currentTime
	 * @return
	 */
	public static Map<String,String> getNewsListParamMap(String currentTime){
		Map<String,String> map = new HashMap<String,String>();
		map.put("cid", "470");
		map.put("pn", "1");
		map.put("ctype", "4001");
		map.put("selids", "461,462,463,464,502");
		Map<String, String> commonParamMap = getCommonParamMap(userId, currentTime);
		map.putAll(commonParamMap);
		return map;
	}
	
	
	/**
	 * 准备发布评论_请求参数map
	 * @param id  文章id
	 * @param content 评论内容
	 * @param userId 用户id
	 * @param currentTime 
	 * @return
	 */
	public static Map<String,String> getDiscussParamMap(News news,String userId,String currentTime){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", news.getId());
		map.put("content", news.getContent());
		Map<String, String> commonParamMap = getCommonParamMap(userId, currentTime);
		map.putAll(commonParamMap);
		return map;
	}
	
	/**
	 * 通用请求参数
	 * @param userId 用户id
	 * @param currentTime
	 * @return
	 */
	public static Map<String,String> getCommonParamMap(String userId,String currentTime){
		Map<String,String> map = new HashMap<String,String>();
		map.put("userID", userId);
		map.put("udid", "0");
		map.put("clientApp", "104");
		map.put("clientBundleID", "net.xinhuamm.mainclient");
		map.put("clientType", "2");
		map.put("clientVer", "2.0.2");
		map.put("clientMarket", "337");
		map.put("clientOS", "4.4.4");
		map.put("clientModel", "HM NOTE 1LTE");
		map.put("clientNet", "wifi");
		map.put("clientToken", "1b1c92d10ac72c611ab9b5de96febb13");
		map.put("clientId", "1b1c92d10ac72c611ab9b5de96febb13");
		map.put("clientLable", "866401023331302");
		map.put("clientDev", "0");
		map.put("clientPrison", "0");
		map.put("clientWidth", "720");
		map.put("clientHeight", "1280");
		map.put("clientLongitude", "116.482487");
		map.put("clientLatitude", "39.997927");
		map.put("clientDate", currentTime);
		map.put("province", "北京市");
		map.put("address", "北京市 朝阳区 阜通西大街 靠近保利院线电影(卜蜂莲花望京宝星店)");
		return map;
	}
	
	public static void main(String[] args) {
		sendPostForNewsList();
	}
	
}
