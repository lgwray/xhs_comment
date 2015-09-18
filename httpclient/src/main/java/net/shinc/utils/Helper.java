package net.shinc.utils;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.shinc.controller.xhscomment.NewsController;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @ClassName Helper 
 * @Description 工具类
 * @author guoshijie 
 * @date 2015年8月13日 下午4:12:01
 */
public class Helper {

	private static Logger logger = LoggerFactory.getLogger(Helper.class);
	
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
	 * 将json格式的字符串转成Map
	 * @param str
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map jsonToMap(String str){
		try {
			Gson gson  = new Gson();
			if(!StringUtils.isEmpty(str)){
				Map map = Collections.synchronizedMap((Map)gson.fromJson(str, HashMap.class));
				return map;
			}
			return null;
		} catch (JsonSyntaxException e) {
			logger.info(ExceptionUtils.getStackTrace(e));
			logger.info("不是标准的json串");
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List jsonToList(String str){
		try {
			Gson gson  = new Gson();
			if(!StringUtils.isEmpty(str)) {
				List list = Collections.synchronizedList((List)gson.fromJson(str, ArrayList.class));
				return list;
			}
			return null;
		} catch (JsonSyntaxException e) {
			logger.info(ExceptionUtils.getStackTrace(e));
			logger.info("不是标准的json串");
			return null;
		}
	}
	
	/**
	 * 将对象转成json串
	 * @param obj
	 * @return
	 */
	public static String objToJson(Object obj){
		try {
			Gson g = new Gson();
			if(null != obj) {
				String str  = g.toJson(obj);
				return str;
			}
			return null;
		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 处理URL,以防url中出现‘｜’‘&’等特殊字符
	 * @param urlPre
	 * @return
	 */
	public static URI dealUrl(String urlPre) {
		URI uri = null;
		try {
			URL url = new URL(urlPre);
			uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return uri;
	}
	
	/**	
	 * 计算需要自动评论的文章数
	 * @param all 拉取到的所有文章数目
	 * @param limit	限制评论文章数目,设置小于0代表不限制,例如只需刷前20篇文章
	 * @return
	 */
	public static int calNum(int all, int limit) {
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
	
	/**
	 * 将data按照指定pattern格式化成String
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date,String pattern) {
		try {
			DateFormat format = new SimpleDateFormat(pattern);
			String datetime = format.format(date);
			logger.info("formatDate: "+datetime);
			return datetime;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	/**
	 * 格式化带"万"的数字
	 * @param str
	 * @return
	 */
	public static String formatNumWithWords(String str) {
		String numStr = null;
		if(!StringUtils.isEmpty(str) && str.indexOf("万") != -1) {
			String strnum = str.substring(0,str.indexOf("万"));
			try {
				Double num = Double.parseDouble(strnum);
				num = num * 10000;
				numStr = String.valueOf(num.intValue());
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return numStr;
	}
	
	/**
	 * 遍历输出Map
	 * @param map
	 */
	public static void iteratorMap(Map map) {
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		for (Object object : set) {
			logger.info(object + ":"+ map.get(object));
		}
	}
	
	/**
	 * 计算a/b 的百分比
	 * @param a
	 * @param b
	 * @return
	 */
	public static String getPercent(String a, String b) {
		try {
			BigDecimal bd = new BigDecimal(Double.parseDouble(a)/Double.parseDouble(b));  
			Double f1 = bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
			DecimalFormat df = new DecimalFormat("######0.00");
			String format = df.format(f1*100);
			return format + "%";
		} catch (NumberFormatException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	public static void main(String[] args) {
//		String percent = getPercent("99998","140119");
//		System.out.println(percent);
		
		formatDate(new Date(), "yyyy-MM-dd");
	}
	
}
