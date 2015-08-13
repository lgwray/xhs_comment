package net.shinc.utils;

import net.shinc.controller.common.HttpController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName RandomUtils 
 * @Description 随机数工具类
 * @author guoshijie 
 * @date 2015年8月13日 下午3:31:48
 */
public class RandomUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpController.class);
	
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
	
	public static void main(String[] args) {
		Integer min = new Integer(1);
		Integer max = new Integer(10);
		Integer random = getRandom(min, max);
		logger.info("num:"+random.toString());
	}
}
