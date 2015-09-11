package net.shinc.utils;

import net.shinc.controller.xhscomment.NewsController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName RandomUtils 
 * @Description 随机数工具类
 * @author guoshijie 
 * @date 2015年8月13日 下午3:31:48
 */
public class RandomUtils {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	/**
	 * 取min和max之间的随机数
	 * (数据类型)(最小值+Math.random()*(最大值-最小值+1))
	 * @param min
	 * @param max
	 * @return 
	 */
	public static Integer getRandom(Integer min,Integer max){
		Integer num = (int)(min + Math.random()*(max-min+1));
		return num;
	}
	
	public static String generateIp() {
		String ip =  firstNum() +"."+ getRandom(0, 255).toString() + "." +getRandom(0, 255).toString() + "." + getRandom(0, 255).toString();
		return ip;
	}
	
	/**
	 * 网络标识不能以数字127开头
	 * 网络标识的第一个字节不能为255。数字255作为广播地址
	 * 网络标识的第一个字节不能为“0”，“0”表示该地址是本地主机，不能传送
	 * @return
	 */
	public static String firstNum() {
		Integer random = getRandom(0, 5);
		boolean b = (0 == random || 1 == random || 127 == random || 255 == random);
		while (b) {
			random = getRandom(0, 255);
			b = (0 == random || 1 == random || 127 == random || 255 == random);
		}
		return random.toString();
	}
	
	public static void main(String[] args) {
		generateIp();
	}
}
