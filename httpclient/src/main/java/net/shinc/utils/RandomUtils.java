package net.shinc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName RandomUtils 
 * @Description 随机数工具类
 * @author guoshijie 
 * @date 2015年8月13日 下午3:31:48
 */
public class RandomUtils {

	private static Logger logger = LoggerFactory.getLogger(RandomUtils.class);
	
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
	
	public static String getUserAgentRandom(){
		String[] user_agent_list = {
		                   "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1",
		                   "Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11",
		                   "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6",
		                   "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
		                   "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
		                   "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5",
		                   "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5",
		                   "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
		                   "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
		                   "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
		                   "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
		                   "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
		                   "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
		                   "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
		                   "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
		                   "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3",
		                   "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
		                   "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24"
		};
		int index = (int) Math.random() * user_agent_list.length;
		return user_agent_list[index];
	}
}
