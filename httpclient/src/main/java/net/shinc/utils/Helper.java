package net.shinc.utils;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.controller.common.NewsController;

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

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);
	
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
			Map map = Collections.synchronizedMap((Map)gson.fromJson(str, HashMap.class));
			return map;
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
			List list = Collections.synchronizedList((List)gson.fromJson(str, ArrayList.class));
			return list;
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
			String str  = g.toJson(obj);
			return str;
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
}
