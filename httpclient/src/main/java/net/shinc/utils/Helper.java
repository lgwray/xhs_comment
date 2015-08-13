package net.shinc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	public static List jsonToList(String str){
		Gson gson  = new Gson();
		List list = gson.fromJson(str, ArrayList.class);
		return list;
	}
	
	/**
	 * 将对象转成json串
	 * @param obj
	 * @return
	 */
	public static String objToJson(Object obj){
		Gson g = new Gson();
		String str  = g.toJson(obj);
		return str;
	}
	
	
}
