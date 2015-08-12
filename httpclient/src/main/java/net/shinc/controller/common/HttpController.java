package net.shinc.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.shinc.common.AbstractBaseController;
import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.utils.HttpRequestTest;
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
	
	private static String url = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment";
	
	private static String list = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/indexlist";
	
	@RequestMapping(value = "/sendPost")
	public ModelAndView sendPost(News news,HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView("index");
		try {
			req.setCharacterEncoding("utf-8");
			String id = req.getParameter("id");
			String content = req.getParameter("content");
	        logger.info(id);
	        logger.info(content);
	        
	        modelAndView.addObject("id", id);
	        
//			String param = "id="+id+"&content="+content+"&userID=0&udid=0&clientApp=104"
//					+ "&clientBundleID=net.xinhuamm.mainclient&clientType=2&clientVer=2.0.2&clientMarket=337"
//					+ "&clientOS=4.4.4&clientModel=HM NOTE 1LTE&clientNet=wifi&"
//					+ "clientToken=1b1c92d10ac72c611ab9b5de96febb13&clientId=1b1c92d10ac72c611ab9b5de96febb13"
//					+ "&clientLable=866401023331302&clientDev=0&clientPrison=0&clientWidth=720&clientHeight=1280"
//					+ "&clientLongitude=116.482487&clientLatitude=39.997927"
//					+ "&clientDate=1439284413585"
//					+ "&province=北京市&address=北京市 朝阳区 阜通西大街 靠近保利院线电影(卜蜂莲花望京宝星店)";
//			logger.info(param);
//			
//			String res = HttpRequestTest.sendPost(url, param);
//			logger.info("result:\t"+res);
	        
	        long currentTimeMillis = System.currentTimeMillis();
	        String res = HttpXmlClient.post(url, getParamMap(id, content, Long.toString(currentTimeMillis)));
	        logger.info("result:\t"+res);
	        
			Gson gson  = new Gson();
			Map map = (Map)gson.fromJson(res, HashMap.class);
			modelAndView.addObject("msg", map.get("data"));
			
			Map map2 = (Map)gson.fromJson(getList(), HashMap.class);
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
		Map map2 = (Map)gson.fromJson(getList(), HashMap.class);
		List list = (List)map2.get("data_scroll");
		List list2 = (List)map2.get("data");
		list.addAll(list2);
		return list;
	}
	
	/**
	 * 发post请求，新闻列表
	 * @return
	 */
	public static String getList() {
//		String param = "cid=470&pn=1&ctype=4001&selids=461,462,463,464,502&userID=0&udid=0&clientApp=104&clientBundleID=net.xinhuamm.mainclient&clientType=2&clientVer=2.0.2&clientMarket=337&clientOS=4.4.4"
//				+ "&clientModel=HM NOTE 1LTE&clientNet=wifi&clientToken=1b1c92d10ac72c611ab9b5de96febb13&clientId=1b1c92d10ac72c611ab9b5de96febb13&clientLable=866401023331302&clientDev=0"
//				+ "&clientPrison=0&clientWidth=720&clientHeight=1280&clientLongitude=116.482975&clientLatitude=39.997814&clientDate=1439297341368&province=北京市&address=北京市 朝阳区 阜安路 靠近双塔电影器材租赁";
//		String res = HttpRequestTest.sendPost(list, param);
		
		String res = HttpXmlClient.post(list, getNewsListParamMap(getCurrentTimeMillis()));
		logger.info(res);
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
	 * 获取新闻列表请求参数map
	 * @param currentTime
	 * @return
	 */
	public static Map<String,String> getNewsListParamMap(String currentTime){
		Map<String,String> map = new HashMap<String,String>();
		map.put("cid", "470");
		map.put("pn", "1");
		map.put("ctype", "4001");
		map.put("selids", "461,462,463,464,502");
		map.put("userID", "0");
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
		map.put("clientLongitude", "116.482975");
		map.put("clientLatitude", "39.997814");
		map.put("clientDate", currentTime);
		map.put("province", "北京市");
		map.put("address", "北京市 朝阳区 阜安路 靠近双塔电影器材租赁");
		return map;
	}
	
	
	/**
	 * 发布评论参数map
	 * @param id
	 * @param content
	 * @param currentTime
	 * @return
	 */
	public Map<String,String> getParamMap(String id,String content,String currentTime){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("content", content);
		map.put("userID", "0");
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
		getList();
		//当前时间戳
		System.out.println(System.currentTimeMillis());
	}
	
}
