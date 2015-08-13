package net.shinc.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * @ClassName Helper 
 * @Description 工具类
 * @author guoshijie 
 * @date 2015年8月13日 下午4:12:01
 */
public class Helper {

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
	public static Map jsonToMap(String str){
		Gson gson  = new Gson();
		Map map = (Map)gson.fromJson(str, HashMap.class);
		return map;
	}
	
	
}
