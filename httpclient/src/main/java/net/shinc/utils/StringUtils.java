package net.shinc.utils;

public class StringUtils {
	
	/**
	 * 处理新华社返回的json串前面3个乱码问题
	 * @param pre
	 * @return
	 */
	public static String formatString(String pre){
		if(!org.springframework.util.StringUtils.isEmpty(pre)){
			int index = pre.indexOf("{");
			return pre.substring(index);
		}
		return pre;
	}

}
